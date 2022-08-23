package com.spring.history.demo_cache.core.util;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;


/**
 * @author relax
 * @version 1.0
 * @since jdk1.7, Created on 2016-12-20 下午2:57:46
 **/
public class OperateTools {
    static NumberFormat format;

    static {
        format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
    }
    
    /**
     * 封装自回调调用参数
     * @param paymentId
     * @param requestData
     * @param CallBackUrl
     * @param requestType
     * @param dataType
     * @return
     */
    public static JSONObject RetryCallbackParams(String paymentId, String requestData, String CallBackUrl, String requestType, String dataType) {
    	JSONObject resendData = new JSONObject()
    			.fluentPut("paymentId", paymentId)
    			.fluentPut("requestUrl", CallBackUrl)
    			.fluentPut("requestData", requestData)
    			.fluentPut("requestType", requestType)
    			.fluentPut("dataType", dataType)
    			.fluentPut("resendNum", 0)
    			.fluentPut("status", 0);
    	return resendData;
    }

    /**
     * 将String装int
     *
     * @param value String值
     * @param def   默认值[当value=null 或者 ""是使用]
     * @return
     */
    public static int parseInt(String value, int def) {
        if (StringUtils.isEmpty(value)) return def;
        return Integer.parseInt(value);
    }

    /**
     * 将科学计数法转换为十进制
     *
     * @param value 如 2.5E7 -->  25000000.0
     * @return
     */
    public static float parseToDecimalism(float value) {
        return Float.parseFloat(format.format(value));
    }

    /**
     * 如果是小数点后面是5.0的转换成5其他不变
     *
     * @param value
     * @return
     */
    public static Object formatPriceData(float value) {
        if (value < 1) return value;
        int t = (int) value;
        if (value - t > 0) {
            return value;
        }
        return t;
    }

    /**
     * 对JSON key进行排序，然后组合成p=v&p1=v1&p2=v2&p3=v3形式
     *
     * @param data
     * @return
     */
    public static String getUrlData(JSONObject data) {
        Set<String> keySets = data.keySet();
        Object[] keys = keySets.toArray();
        Arrays.sort(keys);

        StringBuffer str = new StringBuffer();
        int i = 0;
        for (Object key : keys) {
            String value = data.getString(key.toString());
            if (i > 0) str.append("&");
            str.append(key).append("=").append(value);
            i++;
        }
        return str.toString();
    }
    
    /**
     * 对JSON key进行排序，然后组合成v1v2v3形式
     *
     * @param data
     * @return
     */
    public static String getUrlDataWithoutCharacter(JSONObject data) {
        Set<String> keySets = data.keySet();
        Object[] keys = keySets.toArray();
        Arrays.sort(keys);

        StringBuffer str = new StringBuffer();
        for (Object key : keys) {
            String value = data.getString(key.toString());
            if(!StringUtils.isEmpty(value)) str.append(value);
        }
        return str.toString();
    }
    
    /**
     * 对JSON key进行排序，然后组合成p=v&p1=v1&p2=v2&p3=v3形式,value做url编码
     */
    public static String getEncUrl(JSONObject data) {
    	Set<String> keySets = data.keySet();
    	Object[] keys = keySets.toArray();
    	Arrays.sort(keys);
    	
    	StringBuffer str = new StringBuffer();
    	int i = 0;
    	for (Object key : keys) {
    		String value = data.getString(key.toString());
    		if (i > 0) str.append("&");
    		str.append(key).append("=").append(URLOperate.encode(value));
    		i++;
    	}
    	return str.toString();
    }
    
    /**
     * @param urlStr    url 串
     * @param secretKey 秘钥
     * @return
     */
    public static String getMd5EncryptStr(String urlStr, String secretKey) {
        return MD5Util.md5Encode(urlStr + secretKey);
    }

    /**
     * 将URL参数转换成JSON格式
     *
     * @param url
     * @return
     */
    public static JSONObject parseUrlToJson(String url) {
    	if(StringUtils.isEmpty(url)) {
    		return new JSONObject();
    	}
        String[] items = url.split("&");
        if (items.length == 0) return new JSONObject();
        JSONObject result = new JSONObject();
        for (String item : items) {
            String[] tmps = item.split("=");
            result.put(tmps[0], tmps.length > 1 ? URLOperate.decode(tmps[1]) : "");
        }
        return result;
    }

    public static JSONObject parseUrlToJsonNoDecode(String url) {
        if(StringUtils.isEmpty(url)) {
            return new JSONObject();
        }
        String[] items = url.split("&");
        if (items.length == 0) return new JSONObject();
        JSONObject result = new JSONObject();
        for (String item : items) {
            String[] tmps = item.split("=");
            result.put(tmps[0],tmps.length>=2?tmps[1]:"");
        }
        return result;
    }

    /**
     * 将URL参数转换成JSON格式 先分割&然后再&里面分割=
     *
     * @param url
     * @return
     */
    public static JSONObject parseUrlToJson2(String url) {
        String[] items = url.split("&");
        if (StringUtils.isEmpty(url) || items.length == 0) return new JSONObject();
        JSONObject result = new JSONObject();
        for (String item : items) {
            String[] tmps = item.split("=");
            result.put(tmps[0], tmps.length > 1 ? item.substring(tmps[0].length() + 1) : "");
        }
        return result;
    }

    /**
     * 根据key字典序排序
     * @param url
     * @return
     */
    public static JSONObject parseUrlToJson3(String url) {
        String[] items = url.split("&");
        if (StringUtils.isEmpty(url) || items.length == 0) return new JSONObject();
        JSONObject result = new JSONObject(new TreeMap<String, Object>());
        for (String item : items) {
            String[] tmps = item.split("=");
            result.put(tmps[0], tmps.length > 1 ? item.substring(tmps[0].length() + 1) : "");
        }
        return result;
    }


    public static String getUrlDataExceptKey(JSONObject data, String exceptKey) {
        Set<String> keySets = data.keySet();
        Object[] keys = keySets.toArray();
        Arrays.sort(keys);

        String[] exceps = exceptKey.split(",");

        StringBuffer str = new StringBuffer();
        int i = 0;
        for (Object key : keys) {
            if (ArrayUtils.checkItem(exceps, key.toString())) continue;
            String value = data.getString(key.toString());
            if (i > 0) str.append("&");
            str.append(key).append("=").append(value);
            i++;
        }
        return str.toString();
    }

    public static String joinVal(JSONObject data, String exceptKey) {
        return joinVal(data, exceptKey, "&");
    }

    public static String joinVal(JSONObject data, String exceptKey, String joinKey) {
        Set<String> keySets = data.keySet();
        Object[] keys = keySets.toArray();
        Arrays.sort(keys);

        String[] exceps = exceptKey.split(",");


        StringBuffer str = new StringBuffer();
        int i = 0;
        for (Object key : keys) {
            if (ArrayUtils.checkItem(exceps, key.toString())) continue;
            String value = data.getString(key.toString());
//			if(!StringUtils.isEmpty(value)){
            if (i > 0) str.append(joinKey);
            str.append(value);
            i++;
//			}
        }
        return str.toString();
    }

    final static DecimalFormat fnum = new DecimalFormat("##0.00");

    /**
     * float 保留2位数
     *
     * @param value
     * @return
     */
    public static String getFloatValue(float value) {
        return fnum.format(value);
    }


    public static String joinValSortVal(JSONObject data, String exceptKey) {
        return joinValSortVal(data, exceptKey, "&");
    }

    public static String joinValSortVal(JSONObject data, String exceptKey, String joinKey) {
        Collection<Object> values = data.values();
        Object[] keys = values.toArray();
        Arrays.sort(keys);

        StringBuffer str = new StringBuffer();
        int i = 0;
        for (Object key : keys) {
            String value = key.toString();
            if (i > 0) str.append(joinKey);
            str.append(value);
            i++;
//			}
        }
        return str.toString();
    }

    public static String joinKeyValSortVal(JSONObject data, String exceptKey) {
        return joinKeyValSortVal(data, exceptKey, "=", "&");
    }

    public static String joinKeyValSortVal(JSONObject data, String exceptKey, String markKV, String markKK) {
        Map<String, String> sortData = SortUtil.sortMapByValue(data);
        String[] exceps = exceptKey.split(",");


        StringBuffer str = new StringBuffer();
        int i = 0;
        for (Object key : sortData.keySet()) {
            if (ArrayUtils.checkItem(exceps, key.toString())) continue;
            String value = sortData.get(key.toString());
            if (i > 0) str.append(markKK);
            str.append(key).append(markKV).append(value);
            i++;
        }
        return str.toString();
    }


    /**
     * 获取二级渠道
     *
     * @param channel
     * @return
     */
    public static String getSecondChannel(String channel) {
        if (channel == null) return "";

        String[] channels = channel.split("\\.");
        if (channels.length > 1) {
            return channels[0] + "." + channels[1];
        }
        return channel;
    }

    public static String getUrlParams(JSONObject params) {
        if (params.size() == 0) return "";
        Set<String> keys = params.keySet();
        StringBuffer buff = new StringBuffer();
        for (String key : keys) {
            buff.append(key).append("=").append(params.get(key)).append("&");
        }
        return buff.substring(0, buff.length() - 1);
    }

    public static String getUrlParams(Map<String, Object> params) {
        Set<String> keys = params.keySet();
        StringBuffer buff = new StringBuffer();
        for (String key : keys) {
            buff.append(key).append("=").append(params.get(key)).append("&");
        }
        return buff.substring(0, buff.length() - 1);
    }

    /**
     * 将省份限额 湖北=100,湖南=200 转换成JSON格式
     *
     * @param proLimit
     * @return
     */
    public static JSONObject parseProLimit(String proLimit) {
        JSONObject allProArr = new JSONObject();

        if (StringUtils.isEmpty(proLimit)) return allProArr;


        String[] proLimitArr = proLimit.split(",");
        for (String item : proLimitArr) {

            if (proLimit == null || proLimit.length() < 2) {
                continue;
            }

            String[] tmps = item.split("=");
            allProArr.put(tmps[0], tmps[1]);
        }
        return allProArr;
    }


    /**
     * 支付的时候, 替换支付描述里面的金钱和游戏币
     */
    public static String getPayRuleStr(String msg, float money, int coins) {
        msg = msg == null ? "" : msg;
        msg = msg.replace("{money}", String.valueOf(money));
        msg = msg.replace("{coins}", String.valueOf(coins));
        return msg;
    }

    /**
     * 取部分属性
     *
     * @param srcObj
     * @param keys
     * @return
     */
    public static JSONObject getSomeKey(JSONObject srcObj, String[] keys) {
        JSONObject obj = new JSONObject();
        for (String key : keys) {
            obj.put(key, srcObj.get(key));
        }
        return obj;
    }

    public static JSONObject getSomeKey(JSONObject srcObj, String[] keys, String[] keysset) {
        JSONObject obj = new JSONObject();
        for (int i = 0; i < keys.length; i++) {
            obj.put(keysset[i], srcObj.get(keys[i]));
        }
        return obj;
    }

    public static JSONObject parseObject(String extra) {
        if (StringUtils.isEmpty(extra) || extra.indexOf("{") == -1) return new JSONObject();
        return JSONObject.parseObject(extra);
    }

    public static String getExceptionStack(Exception e) {
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        String result = e.toString() + "\n";
        for (int index = stackTraceElements.length - 1; index >= 0; --index) {
            result += "at [" + stackTraceElements[index].getClassName() + ",";
            result += stackTraceElements[index].getFileName() + ",";
            result += stackTraceElements[index].getMethodName() + ",";
            result += stackTraceElements[index].getLineNumber() + "]\n";
        }
        return result;
    }

    public static String getExceptionStack(String name, StackTraceElement[] stackTraceElements) {
        String result = name + "\n";
        for (int index = stackTraceElements.length - 1; index >= 0; --index) {
            result += "at [" + stackTraceElements[index].getClassName() + ",";
            result += stackTraceElements[index].getFileName() + ",";
            result += stackTraceElements[index].getMethodName() + ",";
            result += stackTraceElements[index].getLineNumber() + "]\n";
        }
        return result;
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
		/*
		String items = "'POST /pay/vivo/callback HTTP/1.1' 200 12 '-' 'Java/1.6.0_26' '-' 'p[respCode=0000&signMethod=MD5&vivoOrder=148399683042882318420&storeOrder=0110052030r6phh&orderAmount=10.00&channelFee=0.00&respMsg=%E4%BA%A4%E6%98%93%E5%AE%8C%E6%88%90&storeId=20140920170418061986&channel=1007&signature=3744ab51a97d932d79eb557dcd774d4d]'";
		String [] tests = items.split(" ");
		
		for(String v : tests){
			System.out.println(v.replaceAll("'", "").replace("p[", "").replace("]", ""));
		}
		*/
		/*
		String infos = "'POST /pay/mmpay/callback HTTP/1.1' 200 195 '-' 'Apache-HttpClient/4.3.1 (java 1.5)' '-' 'p[<?xml version=\\x221.0\\x22 encoding=\\x22UTF-8\\x22?>\\x0A<SyncAppOrderReq xmlns=\\x22http://www.monternet.com/dsmp/schemas/\\x22>\\x0A<TransactionID>201701190431050002</TransactionID>\\x0A<MsgType>SyncAppOrderReq</MsgType>\\x0A<Version>1.0.0</Version>\\x0A<Send_Address>\\x0A<DeviceType>200</DeviceType>\\x0A<DeviceID>CSSP</DeviceID>\\x0A</Send_Address>\\x0A<Dest_Address>\\x0A<DeviceType>1002</DeviceType>\\x0A<DeviceID>f0_0</DeviceID>\\x0A</Dest_Address>\\x0A<OrderID>11170119043104065730</OrderID>\\x0A<CheckID>0</CheckID>\\x0A<ActionTime>20170119043104</ActionTime>\\x0A<ActionID>1</ActionID>\\x0A<MSISDN></MSISDN>\\x0A<FeeMSISDN>7B5A3532F052232B</FeeMSISDN>\\x0A<AppID>300009194663</AppID>\\x0A<PayCode>30000919466302</PayCode>\\x0A<TradeID>F6B351D913</TradeID>\\x0A<Price>1000</Price>\\x0A<TotalPrice>1000</TotalPrice>\\x0A<SubsNumb>1</SubsNumb>\\x0A<SubsSeq>1</SubsSeq>\\x0A<ChannelID>2200145410</ChannelID>\\x0A<ExData>0119043058cpb74</ExData>\\x0A<OrderType>1</OrderType>\\x0A<MD5Sign>4592A611922DC519DD80A473D8FD215F</MD5Sign>\\x0A<OrderPayment>1</OrderPayment>\\x0A<ReturnStatus>0</ReturnStatus>\\x0A<ProvinceID>12</ProvinceID>\\x0A</SyncAppOrderReq>]'";
		String sp = "\\x";
		int index = infos.indexOf(sp);
		while(index!=-1){
			//取出当前16进制数据，为\x后两位
			String str16 = infos.substring(index+2, index+4);
			String t2 = Integer.toBinaryString(Integer.parseInt(str16,16));
			char ch =(char) Integer.parseInt(t2,2);
			infos = infos.replace(sp+str16,String.valueOf(ch));
			index = infos.indexOf(sp);
		}
		System.out.println(infos);
		*/
        String t1 = "100015|1489057765|30031|147630385|0309190925af3yp|371|江苏|868808020692411|460026616730193|0.014|||200#success|vdd.wbl0djddz.2200169810|202210|898600f6101451422875|303011|303013||18261671503|||END";
        String[] tItems = t1.split("\\|");
        int i = 0;
        for (String item : tItems) {
            System.out.println(i + " - " + item);
            i++;
        }

        //System.out.println(new String(Base64Util.decode("eyJhcHBJZCI6IjEwMDg3IiwidXNlcklkIjoiNDE2MzkzNSIsIm9yZGVyIjoiY2UwYzVkMjMtMTBiYy0yYTI1LTUiLCJwcmljZSI6NiwicGF5VHlwZSI6MTIsInBheUNvZGUiOiIxMDAyNDMiLCJzdGF0ZSI6InN1Y2Nlc3MiLCJ0aW1lIjoiMjAxNy0yLTExIDE3OjI0OjAiLCJnYW1lT3JkZXIiOiIwMjExMTcyMzUyaXRpZWQiLCJzaWduIjoiZWFiOWJiYjdiMmJkNmYyNzQ4Zjc2Zjg1YjQ1OGMzMzQifQ==")));
		/*
		String ssss = "YX%2C260637%2C12%2CF7AB%2C1822051%2C05%2C806220170122055515968";
		System.out.println(ssss.substring(ssss.length()-17,ssss.length()));*/
        //System.out.println(JSONObject.toJSONString(tests));
        //System.out.println(URLDecoder.decode("GET&%2Fv3%2Fr%2Fmpay%2Fbuy_goods_m&app_metadata%3D0310121959e4iqv%26appid%3D1105874334%26appmode%3D1%26goodsmeta%3D%E8%B6%85%E5%80%BC%E7%A4%BC%E5%8C%85%2A%E8%B6%85%E5%80%BC%E7%A4%BC%E5%8C%85%26goodsurl%3D%26openid%3D1D656CAAFE38E2D59AC1A108BD969D6C%26openkey%3DE9FE16FA27FE13B8572BB9A9C1B85E1D%26payitem%3D133%2A100%2A1%26pf%3Ddesktop_m_qq-2002-android-2002-ysdk%26pfkey%3D10e81b25489d9f8fae56dd58c8565048%26ts%3D1489369924%26zoneid%3D1", Constants.CHARSET));

        //String str = "{'clientParams':{'ip':'183.240.20.24','uri':'pay/common'},'supportType':'507.420','chargeGameId':'30009','channel':'vivo.hlddzlhf.2200169810','imsi':'460031225265853','mcc':'460','embedGameId':'0','network':'WIFI','vivoSdkVersion':'2.0.9','itemNum':'1','itemName':'220000游戏豆','iccid':'89860316247101188008','egameSdkVersion':'4.2.3','apkVersion':'302046','osInfo':'5.0.2','carrierName':'中国电信','networkCarrier':'46011: CHN-CT','appId':'30009','365youSdkVersion':'1.0.0','eGameChannel':'83010010','dpi':'960*540','key':'582a1f868e5616fd904f441323ef315d','gameId':'30009','paySceneDetail':'11','mnc':'11','appName':'天天斗地主（真人版）','paySceneId':'200009','itemDesc':'220,000游戏豆','userId':'120831230','goodsType':'0','itemId':'4','carrier':'46011','money':'20.0','mfr':'vivo','gameVersion':'302041','imei':'860138037591614','sdkVersion':'202210','time':'1491379138','packName':'com.zengame.waddz.p365you','device':'vivo Y31A'}";

//		System.out.println(getUrlData(JSONObject.parseObject(str)));

        //System.out.println(DateUtils.dateToString(1490544000000L, DateUtils.FORMAT_TWO));
//		
//		JSONObject item = new JSONObject();
//		item.put("signMethod", "MD5");
//		item.put("txnType", "01");
//		item.put("txnSubType", "58");
//		item.put("txnFee", "1000");
//		item.put("merId", "A4585122245");
//		item.put("termId", "B12533552");


//		System.out.println(getUrlParams(item));
//		System.out.println(getUrlData(item));
//		
//		String urlData = "signMethod=MD5&signAture=ecae452e01ba8467b6aa19c99abddb13&txnType=1&txnSubType=wft_h5&txnFee=100&merId=N201704121450&termIp=192.168.1.76&outTradeNo=0412150753ax125&outTradeTime=201704121506&outTradeBody=游戏豆100000";


        String urlDatas = "itemId=362&itemNum=1&itemName=%E5%B9%B8%E8%BF%90%E7%A4%BC%E5%8C%85&itemDesc=%E5%B9%B8%E8%BF%90%E7%A4%BC%E5%8C%85&money=10.0&goodsType=0&paySceneId=400100&365youSdkVersion=1.0.0&mzSignVer=1.3.0&mzOfflineSdkVersion=1.4.5&eGameChannel=80020320&egameSdkVersion=4.2.8&hasWx=true&mnc=11&mcc=460&bId=8977&nId=2&sId=13844&gameId=30009&userId=217421305&key=3935fff104c063c1a81b3da3fe6e06df&time=1506573823&supportType=584.420.715&chargeGameId=30009&appId=30009&embedGameId=0&channel=meizu.ttddzzrb.2200163860&apkVersion=303162&sdkVersion=202264&gameVersion=303161&imei=863328032384282&imsi=460110415060402&iccid=89860316947554353760&carrier=46011&carrierName=%E4%B8%AD%E5%9B%BD%E7%94%B5%E4%BF%A1&networkCarrier=46011%3A%20&device=MX6&osInfo=6.0&mfr=Meizu&dpi=1920*1080&network=WIFI&appName=%E5%A4%A9%E5%A4%A9%E6%96%97%E5%9C%B0%E4%B8%BB%EF%BC%88%E9%AD%85%E6%97%8F%E7%89%88%EF%BC%89&packName=com.zengame.ttddzzrb.mz&guid=15b07fce2e15b3da3de7dd0d6313320f&addr=%E5%B9%BF%E4%B8%9C%E7%9C%81%E6%B7%B1%E5%9C%B3%E5%B8%82%E5%8D%97%E5%B1%B1%E5%8C%BA%E7%A7%91%E6%8A%80%E5%8D%97%E5%8D%81%E4%BA%8C%E8%B7%AF18%E5%8F%B7&radius=580.0&latitude=22.538228&lontitude=113.957504";

        JSONObject datas = OperateTools.parseUrlToJson(urlDatas);


        System.out.println(datas);

        //clientIp=192.168.1.76&merId=N201704121450&outTradeBody=游戏豆100000&outTradeNo=0412150753ax125&outTradeTime=201704121506&signMethod=MD5&termIp=192.168.1.76&txnFee=100&txnSubType=wft_h5&txnType=1


//		String signString = getUrlDataExceptKey(parseUrlToJson(urlData), "signAture");
//		System.out.println(signString);
//		
//		System.out.println(MD5Util.md5Encode(signString+"&key=a5e0a6dc1c7385ca806b4d8e5cf17d2b"));
    }

    /**
     * 使用:keysToStr("_",A,B,C);
     *
     * @param split 分隔符
     * @param args  入参
     * @return A_B_C
     */
    public static String keysToStr(String split, Object... args) {
        String str = "";
        int len = args.length;
        Object[] karr = new Object[len + 1];
        System.arraycopy(args, 0, karr, 0, len);
        if (args != null && args.length > 0) {
            int i = 0;
            for (Object item : args) {
                if (i > 0) str += split;
                str += item;
                i++;
            }
        }
        return str;
    }

    public static String getOsByChannel(String channel) {
        if (MatcherUtils.verify(channel, "ios")) {
            return "ios";
        } else {
            return "android";
        }
    }
    public static String getRandomStr(int num) {
		int len = Constants.chars.length - 1;
		String tmpStr = "";
		for (int i = 0; i < num; i++) {
			tmpStr += Constants.chars[MathUtil.rand(0, len)];
		}
		String str =  tmpStr;
		
		return str;
	}
    public static String getDateRandomStr(int num) {
    	return DateUtils.dateToString(new Date(),DateUtils.FORMAT_MDHMS)+getRandomStr(num);
    }
    
    public static void sort(List<JSONObject> sorts){
		Collections.sort(sorts,new Comparator<JSONObject>() {
			@Override
			public int compare(JSONObject o1, JSONObject o2) {
				if(o1.getIntValue("prior") > o2.getIntValue("prior")) return 1;
				else if(o1.getIntValue("prior") < o2.getIntValue("prior")) return -1;
				return 0;
			}
		});
	}
    /**
     * json升序
     */
    public static JSONObject jsonAsc(JSONObject data) {
        Set<String> keySets = data.keySet();
        Object[] keys = keySets.toArray();
        Arrays.sort(keys);
        JSONObject newJson = new JSONObject(true);
        for (Object key : keys) {
            String value = data.getString(key.toString());
            newJson.put(key.toString(), value);
        }
        return newJson;
    }
    /**
	 * 海外版本
	 * @return
	 */
	public static boolean hasOverseas(){
		Object platform = ServerResource.getProp("platform");
		return platform!= null && "overseas".equals(platform.toString());
	}

	public static String getDeviceTokenPlatTable(int appId, String plat){
        String table = "report_device_token_";
        if(!hasOverseas() && (appId == 32001 || appId == 30009)) {
            table += plat + "_" + appId;
        }else{
            table += plat;
        }
        return table;
    }
	
	/**
	 * 根据服务器标识区分
	 * @param host
	 * @return
	 */
	public static String getServiceArea(String host) {
		Object platform = ServerResource.getProp("platform");
		if(platform!= null && "overseas".equals(platform.toString())) return "美服";
		return "国服";
	}
	
	/**
	 * 判断该字符串是否是数字
	 * @param num
	 * @return
	 */
	public static boolean isNum(String num) {
		Pattern pattern = Pattern.compile("[0-9]*");
 		Matcher isNum = pattern.matcher(num);
 		if(isNum.matches()) return true;
 		return false;
	}

	public static JSONObject getTips(String language, int days){
	    String tips1 = "";
	    String tips2 = "";
        if(language.equals("en-CN") || language.equals("en-GB")){
            tips1 = "Confirm the cancellation, and we will process your application and delete the account information within "+days+" days";
            tips2 = "Please note that there is a "+days+" working day cooling off period (account locking period) for the cancellation of this account. " +
                    "Except as provided by laws and regulations and agreed in the cancellation agreement, we will complete the cancellation, deletion " +
                    "or anonymization of your personal information after the expiration of the cooling off period. 【during this period, please do not log " +
                    "in and use this account to ensure the smooth cancellation. Otherwise, it will be deemed that you have canceled the cancellation application.】";
        }else if(language.equals("ja-CN") || language.equals("ja-GB") || language.equals("ja-JP")){
            tips1 = "ログアウトを確認し、弊社は"+days+"日以内にお申し込みを処理し、アカウント情報を削除します";
            tips2 = "このアカウントのログアウトには"+days+"営業日の冷静期（アカウントロック期間）があることに注意してください。法律法規の規定と抹消協議の約束状況を除いて、" +
                    "私たちは冷静に期限が切れた後に抹消を完了し、個人情報を削除または匿名化して処理します。【その間、アカウントをログインしたり使用したりしないで、ログ" +
                    "アウトが順調に行われていることを確認してください。そうしないと、ログアウト申請を取り消したとみなされます。】";
        }else{
            tips1 = "确认注销，我司将在"+days+"天内处理您的申请并删除账号信息";
            tips2 = "请注意，该账号注销有"+days+"个工作日的冷静期（账号锁定期）。除法律法规规定和注销协议约定情形外，我们将在冷静期满后完成注销，" +
                    "删除或匿名化处理您的个人信息。【在此期间，请您不要登录和使用该账号，以确保注销的顺利，否则视为您撤销注销申请。】";
        }
        return new JSONObject().fluentPut("tips1", tips1).fluentPut("tips2", tips2).fluentPut("ret", 1);
    }

}
