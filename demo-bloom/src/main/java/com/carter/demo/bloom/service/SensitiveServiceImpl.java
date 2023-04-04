package com.carter.demo.bloom.service;

import cn.hutool.extra.pinyin.PinyinUtil;
import cn.hutool.extra.tokenizer.Result;
import cn.hutool.extra.tokenizer.TokenizerEngine;
import cn.hutool.extra.tokenizer.TokenizerUtil;
import cn.hutool.extra.tokenizer.Word;
import com.alibaba.fastjson.JSONObject;
import com.github.houbb.opencc4j.util.ZhConverterUtil;
import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author Carter.li
 * @createtime 2023/4/3 18:27
 */

@Component
@Slf4j
public class SensitiveServiceImpl {


    private static BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8),100000,0.00001);


    private static Integer CHARACTER = 1;
    private static Integer DIGITAL = 2;
    private static Integer LETTER = 3;
    private static Integer CHINESE_CHARACTER = 4;

    private static char[] SPECIAL_CHARACTER = {'|', '#', '=', '+', '/', '<', '>', ',', ';'};

    private static Set<String> worldsV2 = new HashSet<>();
    String[] worlds = null;


    /**
     * 从文件初始化
     */
    @PostConstruct
    public void init() {
        try {
            String ws = readSensitiveWordFile();
            worlds = ws.split("\\|");
            for (String world : worlds) {
                if (StringUtils.isNotBlank(world)) {
                    bloomFilter.put(world);
                    worldsV2.add(world);
                }
            }
            log.info("worlds size = [ " + worlds.length + "]");
        } catch (Exception e) {
            log.error("init error: {} ",e.getMessage(),e);
        }
    }

    /**
     * V2初始化 从数据库初始化
     */
    public void initV2() {
        try {
            long start = System.currentTimeMillis();
            List<JSONObject> sensitiveWords = new LinkedList<>();
//                    sensitiveWordsDao.querySensitiveWord();
            for (JSONObject sensitiveWord : sensitiveWords) {
                String keyword = sensitiveWord.getString("keyword");
                List<String> words = analyzeStrategy(sensitiveWord.getString("strategy"), keyword);
                words.add(keyword);
                for (String word : words) {
                    worldsV2.add(word);
                    bloomFilter.put(word);
                }
            }
            long end = System.currentTimeMillis();
            log.info("加载时间 = [ " + (end - start) + "]");
            log.info("worlds size = [ " + worldsV2.size() + "]");
        } catch (Exception e) {
            log.error("init v2 error: {} ",e.getMessage(),e);
        }
    }

    private List<String> analyzeStrategy(String strategy, String keyword) {
        List<String> words = new ArrayList<>();
        if (StringUtils.isNotBlank(strategy)) {
            String[] strategies = strategy.split(",");
            for (String s : strategies) {
                switch (Integer.valueOf(s)) {
                    case 1:
                        //转全拼
                        words.add(PinyinUtil.getPinyin(keyword, ""));
                        break;
                    case 2:
                        //拼音首字母
                        words.add(PinyinUtil.getFirstLetter(keyword, ""));
                        break;
                    case 3:
                        for (int i = 0; i < keyword.length(); i++) {
                            char c = keyword.charAt(i);
                            char tmp = PinyinUtil.getFirstLetter(c);
                            words.add(keyword.replace(c, tmp));
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return words;
    }
    /**
     * 判断是否有关键字 V2版本
     *
     * @param world
     * @return
     */
    public JSONObject hasSensitiveWordV2(String world) {
        world = world.toLowerCase();
        world = ZhConverterUtil.convertToSimple(world);

        //先走一边indexOf 如果命中关键字就没必要分词
        JSONObject res = checkWordByIndexOf(world, worldsV2);
        if (res != null) {
            return res;
        }

        /**
         * 1.字符
         * 2.数字
         * 3.字母
         * 4.汉字
         */
        Map<Integer, List<Integer>> charIndex = new HashMap<>();
        char[] chars = world.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if ((chars[i] >= 32 && chars[i] <= 47) || (chars[i] >= 58 && chars[i] <= 64) || (chars[i] >= 123 && chars[i] <= 126)) {
                //字符
                analyzeWord(CHARACTER, charIndex, i);
            } else if (chars[i] >= 48 && chars[i] <= 57) {
                //数字
                analyzeWord(DIGITAL, charIndex, i);
            } else if (chars[i] >= 97 && chars[i] <= 122) {
                //字母
                analyzeWord(LETTER, charIndex, i);
            } else if (chars[i] >= 19968 && chars[i] <= 40869) {
                //中国汉字
                analyzeWord(CHINESE_CHARACTER, charIndex, i);
            } else {
                //无法识别
            }
        }

        JSONObject ret = analyzeMap(charIndex, chars);
        return ret;
    }


    private String readSensitiveWordFile() throws Exception {
        String path = "/kw.txt";

        StringBuilder txt = new StringBuilder();
        String tmp = "";
        try (
                InputStream is =  new ClassPathResource(path).getInputStream();
                InputStreamReader read = new InputStreamReader(is);
                BufferedReader bufferedReader = new BufferedReader(read);
        ) {

            while ((tmp = bufferedReader.readLine()) != null) {
                txt.append(tmp);
            }
        } catch (Exception e) {
            throw e;
        }
        return txt.toString();
    }

    private static JSONObject checkWordByIndexOf(String word, Set<String> worlds) {
        JSONObject res = new JSONObject();
        for (String item : worlds) {
            if (word.indexOf(item) != -1) {
                res.put("hit", item);
                res.put("checkRes", 0);
                return res;
            }
        }
        return null;
    }



    /**
     * 分析输入词
     * 将输入词,分析成一个map
     * type - 索引
     *
     * @param key
     * @param charIndex
     * @param index
     */
    private static void analyzeWord(Integer key, Map<Integer, List<Integer>> charIndex, int index) {
        List<Integer> indexs;
        if (charIndex.containsKey(key)) {
            indexs = charIndex.get(key);
        } else {
            indexs = new ArrayList<>();
        }
        indexs.add(index);
        charIndex.put(key, indexs);
    }

    /**
     * 分析map
     *
     * @param charIndex
     * @param chars
     * @return
     */
    private static JSONObject analyzeMap(Map<Integer, List<Integer>> charIndex, char[] chars) {
        JSONObject res = new JSONObject();

        switch (charIndex.size()) {
            case 0:
//				res = res.fluentPut("checkRes","0").fluentPut("msg","字符无法识别");
                //日文无法识别,放行
                res = res.fluentPut("checkRes", "1").fluentPut("msg", "字符无法识别");
                break;
            case 1:
                //种类只有一个
                res = oneType(charIndex, chars);
                break;
            default:
                res = multipleTypes(charIndex, chars);
                break;
        }
        return res;
    }


    /**
     * 词语就一种类型
     *
     * @param charIndex
     * @param chars
     * @return
     */
    private static JSONObject oneType(Map<Integer, List<Integer>> charIndex, char[] chars) {
        JSONObject res = null;
        for (Map.Entry<Integer, List<Integer>> entry : charIndex.entrySet()) {
            Integer key = entry.getKey();
            List<Integer> value = entry.getValue();
            StringBuilder sb = new StringBuilder();
            for (Integer i : value) {
                sb.append(chars[i]);
            }
            res = isKeyword(sb.toString(), key);
        }
        return res;
    }

    /**
     * 多种类型一起存在
     *
     * @param charIndex
     * @param chars
     * @return
     */
    private static JSONObject multipleTypes(Map<Integer, List<Integer>> charIndex, char[] chars) {
        JSONObject res = null;
        List<Integer> indexList;
        String word = "";
        if (charIndex.containsKey(1)) {
            //字符
            StringBuilder sb = new StringBuilder();
            indexList = charIndex.get(1);
            for (Integer index : indexList) {
                sb.append(chars[index]);
            }
            word = sb.toString();
            res = isKeyword(word, 1);
            if (res.getIntValue("checkRes") == 0) {
                return res;
            }

        }
        if (charIndex.containsKey(2)) {
            //数字
            indexList = charIndex.get(2);
            StringBuilder sb = new StringBuilder();
            for (Integer index : indexList) {
                sb.append(chars[index]);
            }
            word = sb.toString();
            res = isKeyword(word, 2);
            if (res.getIntValue("checkRes") == 0) {
                return res;
            }
        }
        if (charIndex.containsKey(3)) {
            //字母
            indexList = charIndex.get(3);
            StringBuilder sb = new StringBuilder();
            if (indexList.size() >= 1) {
                int start = indexList.get(0);
                for (int i = 0; i < indexList.size(); i++) {
                    if (i - start <= 1) {
                        sb.append(chars[indexList.get(i)]);
                    } else {
                        sb.append(",").append(chars[indexList.get(i)]);
                    }
                    start = i;
                }
                word = dealLetterWordCombine(sb.toString());
                res = isKeyword(word, 3);
                if (res.getIntValue("checkRes") == 0) {
                    return res;
                }
            }
        }
        if (charIndex.containsKey(4)) {
            //汉字
            indexList = charIndex.get(4);
            StringBuilder sb = new StringBuilder();
            if (indexList.size() >= 1) {
                for (Integer index : indexList) {
                    sb.append(chars[index]);
                }
                word = sb.toString();
                res = isKeyword(word, 4);
                if (res.getIntValue("checkRes") == 0) {
                    return res;
                }
            }
        }
        return res;
    }


    /**
     * 处理字母的合并
     *
     * @param str
     * @return
     */
    private static String dealLetterWordCombine(String str) {
        Set<String> words = new HashSet<>();
        String word = str;
        String[] split = str.split(",");
        if (split.length > 1) {
            String pre = split[0];
            for (int i = 0; i < split.length; i++) {
                words.add(split[i]);
                if (i != 0) {
                    words.add(pre + split[i]);
                    pre = split[i];
                }
            }
            word = listToString(words, ",");
        }
        return word;
    }

    /**
     * 将Array 转换为1，1格式
     *
     * @param arrays
     * @return
     */
    private static String listToString(Set<String> arrays,String separator){
        String t="";
        for(String i : arrays){
            t+=i+separator;
        }
        if(StringUtils.isEmpty(t)){
            return "";
        }
        return t.substring(0, t.length() - 1);
    }

    /**
     * 判断是否是关键字
     *
     * @param word
     * @param key
     * @return
     */
    private static JSONObject isKeyword(String word, Integer key) {
        JSONObject res = null;
        switch (key) {
            case 1:
                res = checkCharacter(word);
                break;
            case 2:
                res = checkDigital(word);
                break;
            case 3:
                res = checkLetter(word);
                break;
            case 4:
                res = checkChineseCharacter(word);
                break;
        }
        return res;
    }

    /**
     * 检查字符
     *
     * @param word
     * @return
     */
    private static JSONObject checkCharacter(String word) {
        JSONObject res = new JSONObject();
        res.put("checkRes", 1);
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            boolean checkItem = checkItem(SPECIAL_CHARACTER, chars[i]);
            if (checkItem == true) {
                res.put("checkRes", 0);
                res.put("hit", chars[i]);
                return res;
            }
        }
        return res;
    }

    private static boolean checkItem(char [] array , char key){
        if(array == null || array.length == 0) {
            return false;
        }
        for(int k : array){
            if(k == key){
                return true;
            }
        }
        return false;
    }

    /**
     * 检查数字
     *
     * @param word
     * @return
     */
    private static JSONObject checkDigital(String word) {
        JSONObject res = new JSONObject();
        res.put("checkRes", 1);
        return res;
    }

    /**
     * 检查字母 走indexOf
     *
     * @param word
     * @return
     */
    private static JSONObject checkLetter(String word) {
        JSONObject res = null;
        res = checkWordByIndexOf(word, worldsV2);
        if (res == null) {
            return new JSONObject().fluentPut("checkRes", 1);
        }
        return res;
    }
    /**
     * 检查汉字是否是关键词
     * 走布隆过滤器
     *
     * @param word
     * @return
     */
    private static JSONObject checkChineseCharacter(String word) {
        Set<String> words = new HashSet<>();
        JSONObject res = new JSONObject();
        res.put("checkRes", 1);
        TokenizerEngine engine = TokenizerUtil.createEngine();
        Result result = engine.parse(word);
        while (result.hasNext()) {
            Word next = result.next();
            String text = next.getText();
            if (text.length() > 1) {
                words.add(text);
            }
        }
        words.add(word);
        Iterator<String> iterator = words.iterator();
        List<String> pinyinList = new ArrayList<>();
        while (iterator.hasNext()) {
            String next = iterator.next();
            String pinyin = PinyinUtil.getPinyin(next, "");
            pinyinList.add(pinyin);
        }
        words.addAll(pinyinList);
        for (String s : words) {
            if (bloomFilter.mightContain(s)) {
                res.put("hit", s);
                res.put("checkRes", 0);
                return res;
            }
        }
        return res;
    }

}
