package com.spring.history.demo_cache.core;

import java.util.HashMap;
import java.util.Map;

import com.spring.history.demo_cache.core.util.ArrayUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class GlobalCache extends XMLKit{
	
	
	private static String [] CLOSE_CHANNEL;
	
	/** IMSI运营商标记 */
	private static Map<String, String> carriers = new HashMap<>();
	
	/** ICCID运营商标记 */
	private static Map<String, String> carrierIccids = new HashMap<>();

	/** 移动手机号省份标识 */
	private static Map<String, String> mobileProvinces = new HashMap<>();
	
	/** 省份对应号段ID */
	private static Map<String, String> provinceIdMobiles = new HashMap<>();
	
	/** 联通手机身份标识 */
	private static Map<String, String> unicomProvinces = new HashMap<>();
	
	/** 省份编码 */
	private static Map<String, String> provinces = new HashMap<>();
	
	private static long [] payUserBlacks;
	private static String [] payImeiBlacks;
	
	
	public static String cornet;//短信网关短号(移动)
	public static String mobileCornet;//短信网关短号(移动)
	public static String unicomCornet;//短信网关短号
	public static String telecomCornet;//短信网关短号
	
	//登陆AES加密开关
	public static boolean AesEncrypt = false; 
	
	
	@Override
	public void parse(Document doc) {
		Element element = (Element)doc.getElementsByTagName("close-channel").item(0);
		String values = element.getFirstChild().getNodeValue().replace("\n", "").replace("\t", "");
		CLOSE_CHANNEL = values.split(",");
		
		carriers =  parseXML(doc, "carrier");
		
		NodeList list = doc.getElementsByTagName("mobile-province");
		for(int i=0;i<list.getLength();i++){
			element = (Element)list.item(i);
			mobileProvinces.put(element.getAttribute("id"), element.getAttribute("value"));
			provinceIdMobiles.put(element.getAttribute("value"), element.getAttribute("id"));
		}
		
		
		unicomProvinces = parseXML(doc, "unicom-province");
		
		list = doc.getElementsByTagName("province");
		for(int i=0;i<list.getLength();i++){
			element = (Element)list.item(i);
			String dnsegs = element.getAttribute("dnsegs");
			String province = element.getAttribute("value");
			String [] d = dnsegs.split(",");
			for(String id : d) {
				provinces.put(id, province);
			}
		}
		
		carrierIccids = parseXML(doc, "carrier-iccid");
		payUserBlacks = parseSingleLongArray(doc, "pay-user-black", "id");
		payImeiBlacks = parseSingleString(doc, "pay-imei-black", "id").split(",");
		
		
		element=(Element)doc.getElementsByTagName("sms-gateway").item(0);
		
		cornet = element.getAttribute("cornet");
		mobileCornet = element.getAttribute("mobileCornet");
		unicomCornet = element.getAttribute("unicomCornet");
		telecomCornet = element.getAttribute("telecomCornet");
		
		
		AesEncrypt = Boolean.parseBoolean(parseSingleString(doc, "log-aes-encrypt", "value"));
	}
	
	/**
	 * 渠道是否关闭
	 * @param channel
	 * @return true / 关闭 false 未关闭
	 */
	public static boolean chnnnelIsClose(String channel){
		return ArrayUtils.getItem(CLOSE_CHANNEL, channel) > 0;
	}
	
	public static String getCarrier(String carrierId){
		return carriers.get(carrierId);
	}
	
	public static String getIccIdCarrier(String iccidCode){
		return carrierIccids.get(iccidCode);
	}
	
	public static String getMobileProvience(String id){
		if(id.length() == 1){
			id="0"+id;
		}
		
		return mobileProvinces.get(id);
	}
	
	public static String getMobileIdByProvince(String province){
		return provinceIdMobiles.get(province);
	}
	
	public static String getUnicomProvince(String id){
		return unicomProvinces.get(id);
	}
	
	public static String getProvince(String code){
		return provinces.get(code);
	}
	
	public static boolean isPayBlack(long userId , String imei){
		return ArrayUtils.checkItem(payUserBlacks, userId) || ArrayUtils.getItem(payImeiBlacks, imei) !=-1;
	}
	
	public static void main(String[] args) throws Exception {
	}
	
}
