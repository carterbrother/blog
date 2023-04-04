package com.carter.demo.classload.service;

import com.alibaba.fastjson.JSONObject;
import com.carter.demo.classload.core.DynamicClassLoader;
import com.carter.demo.classload.pay.BasePay;
import com.carter.demo.classload.pay.PayCodeDesc;
import com.carter.demo.classload.pay.PayConfig;
import com.carter.demo.classload.pay.PayFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 重新加载jar，热更新，无需重启服务
 * @author Carter.li
 * @createtime 2023/4/4 16:02
 */
@Component
@Slf4j
public class LoadJarService {

    private DynamicClassLoader dyClassLoad;

    //pay class 的版本号，根据时间生产最后版本号[key-class value-时间]

    private Map<String, String> versions = new HashMap<>();
    private List<String> changes = new ArrayList<>();
    JarFile jarFile;
    long jarTime = 0;


    public void checkPayClzVersion(){
        log.info("开始版本检测!");
        //jar包重载
        try {
            boolean change = reloadJar();
            if(!change || jarFile == null){
                return;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            Map<String, String> items = loadPayVersion();
            if(items == null || items.size() == 0) {
                return;
            }
            changes.clear();

            //找出发生变更的class
            Set<String> newsVersions = items.keySet();
            for(String key : newsVersions){

                //对base类不做处理
                if("BasePay".equals(key)){
                    continue;
                }


                String newValue = items.get(key);
                String oldValue = this.versions.get(key);
                if(!newValue.equals(oldValue)){
                    changes.add(key);
                }
            }
            this.versions = items;


            if(dyClassLoad == null){
                dyClassLoad = new DynamicClassLoader();
            }

           PayFactory.getInstance().classChange(changes);

            //对变更的类重新加载
            if(changes.size() != 0){
                log.info("完成类的重载:"+ JSONObject.toJSONString(changes));
            }
        }finally{
            if(jarFile != null){
                try {
                    jarFile.close();
                    jarFile = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //同一个classLoad只能加载一次相同的类，用完即可销毁掉
            if(dyClassLoad != null){
                dyClassLoad = null;
            }
        }
    }


    private boolean reloadJar() throws IOException{
        String path = System.getProperty("main.jar.path","");
        File file = new File(path);
        if(jarTime != 0 && file.lastModified() == jarTime || !file.exists()){
            return false;
        }

        jarFile = new JarFile(file);

        if(jarTime == 0){
            jarTime = file.lastModified();
            versions = loadPayVersion();
            jarFile.close();
            return false;
        }

        jarTime = file.lastModified();

        return true;
    }

    private Map<String, String> loadPayVersion(){
        Map<String, String> versions = new HashMap<>(32);
        InputStreamReader read = null;
        InputStream is = null;
        BufferedReader bufferedReader = null;
        try{
            String encoding = "gbk";

            JarEntry entry = jarFile.getJarEntry("classInfo.txt");
            is = jarFile.getInputStream(entry);

            read = new InputStreamReader(is, encoding);
            bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                if(lineTxt.indexOf(".java") != -1){
                    String [] items = lineTxt.split(" ");
                    String version = items[2].replaceAll("/", "");
                    String clz = items[items.length - 1].replace(".java", "");
                    versions.put(clz,version);
                }
            }

        }catch(Exception e){
        }finally{
            try {
                if(read != null){
                    read.close();
                }
                if(is != null){
                    is.close();
                }
                if(bufferedReader != null){
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return versions;
    }





}
