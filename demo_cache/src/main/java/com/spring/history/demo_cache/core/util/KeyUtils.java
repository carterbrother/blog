package com.spring.history.demo_cache.core.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
@author      relax
@since       jdk1.7,Created on 2016-12-5 下午6:09:13
@version     1.0
 **/
public class KeyUtils {
	
	//key配置 （按如下格式加上前缀，services里调用方式：$this->keys()->getKey('test');）
	static Map<String, String> keys = new HashMap<String, String>();
	static{
		keys.put("pay01", "pay01Num");
		keys.put("test1", "gm_test1");
		keys.put("test2", "gm_test2");
		keys.put("17", "payMoneyLimit");
		keys.put("18", "UserIdProvince");
		keys.put("19", "limit");
		keys.put("20", "pay");
		keys.put("proLtd", "proLtd");//省份限额
		keys.put("gameLtd", "gameLtd");//游戏限额
		keys.put("uDayNum", "uDayNum");//用户月限次数
		keys.put("uMonthNum", "uMonthNum");//用户月限次数
		keys.put("WyGameNum", "WyGameNum");
		keys.put("Wygnlog", "Wygnlog");
		keys.put("WyGiftNum", "WyGiftNum");
		keys.put("bsinfo", "bsinfo");//设备基本信息
		keys.put("sobs", "sobs");//单机订单->设备基本信息
		keys.put("dailyChargeAmount", "dailyChargeAmount");//用户充值日限额
	}
	
	//获取key
	public static String getKey(String type,Object [] array){
		String prefix = keys.get(type);
		if(array != null && array.length > 0){
			for(Object item : array){
				prefix+="_"+item;
			}
		}
		return prefix;
	}
	
	public static String getLimitKey(String dataFormat,Object...args){
		String time = DateUtils.dateToString(new Date(), dataFormat);
		int len = args.length;
		Object[] karr = new Object[len+1];
    	System.arraycopy(args, 0, karr, 0, len);
    	karr[len] = time;
    	String key = KeyUtils.getKey("19", karr);
		return key;
	}
	
	public static String getKeyByType(String type,String dataFormat,Object [] args){
		String time = DateUtils.dateToString(new Date(), dataFormat);
		int len = args.length;
		Object[] karr = new Object[len+1];
    	System.arraycopy(args, 0, karr, 0, len);
    	karr[len] = time;
    	String key = KeyUtils.getKey(type, karr);
		return key;
	}
	
	
	
	public static String getKey(String type,List<Object> array){
		String prefix = keys.get(type);
		if(array != null && array.size() > 0){
			for(Object item : array){
				prefix+="_"+item;
			}
		}
		return prefix;
	}
	
	/**
	 * 用户所在省份 redis cache key
	 * @param userId
	 * @return
	 */
	public static String getUserProvinceKey(long userId){
		return "userProvinceKey-"+userId;
	}
	
	//记录最近30天注册用户
	public static String DAY_30_REGISTER="day_30_register_";
	
	/**
	 * 用户最近成功的第三方支付方式KEY
	 * @param userId
	 * @return
	 */
	public static String getThirdPaySucc(long userId){
		return "thirdPaySucc_"+userId;
	}
	
	

	/**
	 * 检查用户此支付的间隔时间
	 * @param payType 支付方式
	 * @param imsi
	 * @return
	 */
	public static String getNeedWaitTimeKey(int payType,String imsi){
		return "payType_"+payType+"_"+imsi+"_needWaitTime";
	}
	
	/**
	 * 用户尝试缓存支付方式 key
	 * @param userId
	 * @param itemId
	 * @param paySceneId
	 * @return
	 */
	public static String getCachePayType(long userId,int itemId,String paySceneId){
		return "cpt_"+userId+"_"+itemId+"_"+paySceneId;
	}
	
	/**
	 * 推荐道具 key
	 * @param userId
	 * @param itemId
	 * @return
	 */
	public static String recomLimitBuyKey(long userId,int itemId){
		return "p_UIRN_"+userId+"_"+itemId;
	}
	
	/**
	 * 限购道具 key
	 * @param userId
	 * @param itemId
	 * @return
	 */
	public static String limitBuyKey(long userId,int itemId){
		return "p_UIBN_"+userId+"_"+itemId;
	}
	

	/**
	 * 推荐聚划算道具 key
	 * @param userId
	 * @param itemId
	 * @return
	 */
	public static String recomHuanSuanBuyKey(long userId,int itemId){
		return "p_HS_IRN_"+userId+"_"+itemId;
	}
	
	/**
	 * 聚划算 key
	 * @param userId
	 * @param itemId
	 * @return
	 */
	public static String huanSuanBuyKey(long userId,int itemId){
		return "p_HS_IBN_"+userId+"_"+itemId;
	}
	public static String userMonthItemStatus(String imsi,int payType){
		return "p_uMonthI_"+imsi+"_"+payType;
	}
	/**
	 *
	 * @param api : "test"
	 * @param args : 1,"a"
	 * @return u_apiReqNum_test_2022-05-23_1_a
	 */
	public static String apiDayReqNum(String api,Object...args){
		String dayDate  =DateUtils.dateToString(new Date(), DateUtils.FORMAT_ONE);
		String pre =  OperateTools.keysToStr("_","u_apiReqNum",api,dayDate);
		String argsStr = OperateTools.keysToStr("_", args);
		return pre+"_"+argsStr;
	}
	/**
	 * 验证码缓存
	 *
	 * 当时为了兼容SDK,获取验证码的时候,可能会拿不到userId
	 * 代码里面拿不到userId的情况会写死0
	 *
	 * 这里统一写死,将userId固定为0
	 * by quine
	 */
	public static String smsCode(long userId,String mobile){
		userId = 0;
		return "u_smsCode_"+userId+"_"+mobile;
	}
	
}
