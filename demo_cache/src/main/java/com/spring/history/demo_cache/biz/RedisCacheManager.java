package com.spring.history.demo_cache.biz;


import com.spring.history.demo_cache.core.GlobalCache;
import com.spring.history.demo_cache.core.RedisCache;
import com.spring.history.demo_cache.core.util.DateUtils;
import com.spring.history.demo_cache.core.util.KeyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
@author      relax
@since       jdk1.7,Created on 2017-3-2 上午11:43:43
@version     1.0
 **/
@Component
public class RedisCacheManager {
	@Autowired
	RedisCache cache;
	
	public RedisCache getCache(){
		return cache;
	}

	/**
	 * 根据userId获取所在省份
	 * 
	 * @param userId
	 */
	public String getProvinceByUserId(long userId) {
		if (userId <= 0)return null;
		String province = cache.get(KeyUtils.getUserProvinceKey(userId));
		return StringUtils.isEmpty(province) ? "" : province;
	}
	
	/**
	 * 保存用户省份
	 * 
	 * @param userId
	 * @param province
	 * @param type = 1 和缓存中的值不同时覆盖 type = N 缓存中不存在时覆盖
	 */
	public void setUserIdProvince(long userId, String province, int type) {
		if (StringUtils.isEmpty(province) || userId == 0)return;
		
		String userProvinceKey = KeyUtils.getUserProvinceKey(userId);
		String rVal = cache.get(userProvinceKey);
		if (type == 1 && !province.equals(rVal)) {
			cache.set(userProvinceKey, province, 86400 * 3);
		}
		if (StringUtils.isEmpty(rVal) && !StringUtils.isEmpty(province)) {
			cache.set(userProvinceKey, province, 86400);
		}
	}
	
	/**
	 * 记录用户游戏字支付类型日限额
	 * @param userId
	 * @param gameId
	 * @param payType
	 * @param money
	 */
	public void recordDayLimitByUserIdGameId(long userId,int gameId,String payType,float money){
		cahceOneDayIncrement(DateUtils.FORMAT_ONE,money,userId,gameId,payType);
	}
	
	/** 获取用户游戏字支付类型日限额 */
    public float getDayLimitByUserIdGameId(long userId,int gameId, String payType){
		return cache.get(KeyUtils.getLimitKey(DateUtils.FORMAT_ONE,userId,gameId,payType),0F);
    }
	
    
    /**
     * 记录用户ID-支付类型日限额
     * @param userId
     * @param payType
     * @param money
     */
    public void recordDayLimitByUserId(long userId,String payType,float money){
		cahceOneDayIncrement(DateUtils.FORMAT_ONE,money,userId,payType);
    }
    
    /** 获取用户-支付类型日充值额*/
    public float getDayLimitByUserId(long userId,String payType){
    	return cache.get(KeyUtils.getLimitKey(DateUtils.FORMAT_ONE,userId,payType),0F);
    }
    
	
    /**
     * 记录用户ID-支付类型月限额
     * @param userId
     * @param payType
     * @param money
     */
    public void recordMonthLimitByUserId(long userId,String payType,float money){
    	cahceOneMonthIncrement(money,userId,payType);
    }
    /**
     * 记录用户子代码月限
     */
    public void recordMonthLimitByUserIdSubPayType(float money,int payType,String subPayType,long userId){
    	cahceOneMonthIncrement(money,payType,subPayType,userId);
    }
    /**
     * 获取用户子代码月限
     */
    public float getMonthLimitByUserIdSubPayType(int payType,String subPayType,long userId){
    	return cache.get(KeyUtils.getLimitKey(DateUtils.FORMAT_THREE,payType,subPayType,userId), 0F);
    }
    
    /**
     * 获取用户ID-支付类型
     * @param userId
     * @param payType
     * @return
     */
    public float getMonthLimitByUserId(long userId,String payType){
    	return cache.get(KeyUtils.getLimitKey(DateUtils.FORMAT_THREE,userId,payType), 0F);
    }
    
   /**
    * 记录userid-gameid月限额
    * @param userId
    * @param gameId
    * @param payType
    * @param money
    */
    public void recordMonthLimitByUserIdGameId(long userId,int gameId,String payType,float money){
    	cahceOneDayIncrement(DateUtils.FORMAT_THREE,money,userId,gameId,payType);
    }
    
    /** 记录userid-gameid月限额*/
    public float getMonthLimitByUserIdGameId(long userId,int gameId, String payType){
    	return cache.get(KeyUtils.getLimitKey(DateUtils.FORMAT_THREE,userId,gameId,payType),0F);
    }
    
    /**
     * 
     * @param payType
     * @param money
     */
    public void recordDayLimitByPaytype(String payType,float money){
    	cahceOneDayIncrement(DateUtils.FORMAT_ONE,money,payType);
    }
    
    public void recordMonthLimitByPaytype(String payType,float money){
    	cahceOneDayIncrement(DateUtils.FORMAT_THREE,money,payType);
    }
    
    public void recordUserProLimit(String payType, String province, float money,long userId){
    	cahceOneDayIncrement(DateUtils.FORMAT_ONE,money,payType, GlobalCache.getMobileIdByProvince(province),userId);
    }
    
    /**
     * 记录支付类型-gameId 充值额度
     * @param payType
     * @param gameId
     * @param money
     */
    public void recordGameLimit(String payType, int gameId, float money){
    	cahceOneDayIncrement(DateUtils.FORMAT_ONE,money,payType,gameId);
    }
    
    /**
     * 获取支付类型-gameId 充值额度
     * @param payType
     * @param gameId
     */
    public float getGameLimit(String payType, int gameId){
    	return cache.get(KeyUtils.getLimitKey(DateUtils.FORMAT_ONE,payType,gameId), 0F);
    }
    
    /**
     * 支付类型日限额
     * @param keys
     * @return
     */
    public float getDayLimit(Object... keys){
    	String key = KeyUtils.getLimitKey(DateUtils.FORMAT_ONE,keys);
    	return cache.get(key, 0F);
    }
    
    /**
     * 省份限额
     * @param keys
     * @return
     */
    public float getLimitByType(String type,Object... keys){
    	String key = KeyUtils.getKeyByType(type,DateUtils.FORMAT_ONE,keys);
    	return cache.get(key, 0F);
    }
    
    
    /**
     * 对当天某个缓存进行原子操作
     * 
     * @param dataFormat 时间格式
     * @param money      操作值
     * @param keyArgs    cache 组合参数
     */
    public void cahceOneDayIncrement(String dataFormat,Float money,Object...keyArgs){
    	String key = KeyUtils.getLimitKey(dataFormat,keyArgs);
    	
    	Integer v = money.intValue();
    	
    	if(!cache.hasKey(key)){
    		cache.set(key, v, (int)DateUtils.getlessTime());
    	}else{
    		cache.increment(key, v);
    	}
    }
    /**
     * 对当天某个缓存进行原子操作
     * 
     * @param money      操作值
     * @param keyArgs    cache 组合参数
     */
    public void cahceOneMonthIncrement(Float money,Object...keyArgs){
    	String key = KeyUtils.getLimitKey(DateUtils.FORMAT_THREE,keyArgs);
    	if(!cache.hasKey(key)){
    		cache.set(key, money, DateUtils.getMonthLessTime());
    	}else{
    		cache.increment(key, money);
    	}
    }
    
    /**
     * 获取用户对局次数
     * @param userId
     * @param gameId
     * @return
     */
    public int getGamePlayCount(long userId,int gameId,int def){
    	String gpKey = userId+"_"+gameId+"_gp";
		int userPlayCount = cache.get(gpKey, def);
		return userPlayCount;
    }
    public void setGamePlayCount(long userId,int gameId,int value,int time){
    	String gpKey = userId+"_"+gameId+"_gp";
		cache.set(gpKey, (Integer)value, time);//大于就缓存10分钟, 小于缓存30秒
    }
    
    
    /**
	 * 获取不允许使用的支付方式
	 * @return
	 */
	public boolean isNotAllowedPayType(long userId,int payType){
		if(userId==0)
			return false;
		String key="p_userNotAllowPayType_"+userId+"_"+payType;
		String value=cache.get(key);
		if(!StringUtils.isEmpty(value))
			return true;
		return false;
	}
	
	public void setNotAllowedPayType(long userId,int payType,int expTime){
		if(userId==0)return;
		String key="p_userNotAllowPayType_"+userId+"_"+payType;
		System.out.println(key);
		cache.set(key, "1", expTime);
	}
	
	
	String getImsiMonthStatus = "p_uMonthS_";
	/**
	 * imsi 包月状态
	 */
	public int getImsiMonthStatus(String imsi){
		return cache.get(getImsiMonthStatus+imsi,0);
	}
	
	public void setImsiMonthStatus(String imsi){
		cache.set(getImsiMonthStatus+imsi,(Integer)1,60*60*24*60);
	}
	
	
	String shortTimeSmsReqKey="p_STSR_";//短时间内短代请求
	/**
	 * 短代请求时间限制
	 * @param userId
	 * @return
	 */
	public int getSmsReqTime(long userId){
		return cache.get(shortTimeSmsReqKey+userId,0);
	}
    
	String userCanNotUseSmsFailPayTypeKey="p_UCNUSFP_";//用户不能用的短代失败支付方式 10分钟
	/**
	 * 用户不能用的短代支付
	 * @param userId
	 * @return
	 */
	public String getCanNotUseSmsPayType(long userId){
		return cache.get(userCanNotUseSmsFailPayTypeKey+userId);
	}
	
	/**
	 * 获取成功支付的第三方支付方式
	 * @param userId
	 * @return
	 */
	public int getThirdSuccPayType(long userId){
		return cache.get(KeyUtils.getThirdPaySucc(userId),0);
	}
	final int time = 432000;
	
	/**
	 * 第三方支付成功缓存
	 * @param userId
	 * @param payType
	 */
	public void setThirdSuccPayType(long userId,Integer payType){
		cache.set(KeyUtils.getThirdPaySucc(userId), payType, time);
	}
	
	/**
	 * ??????
	 * @param userId
	 * @param payType
	 * @return
	 */
	public boolean checkPayType(long userId,int payType){
		return cache.hasKey("sbuy_"+userId+"_"+payType);
	}
	
	
	///////////////////////////////////支付实现相关////////////////////////
	public boolean check01Limit(String type,long userId, int gameId, int itemId){
		String key = type + userId + "_" + gameId + "_" + itemId;
		return cache.get(key,0) > 0;
	}
	
	/**
	 * 指令步骤数据缓存
	 * @param key
	 * @param data
	 */
	public void setCommandStepData(String key,String data){
		cache.set(key, data,120);
	}
	public String getCommandStepData(String key){
		return cache.get(key);
	}
	
	String interceptFailKey="interceptFail_";
	public int getInterceptFail(String imsi){
		return cache.get(interceptFailKey+imsi,0);
	}
	
	//////米玩订单号缓存
	String key  ="p_OTNB_";
	public String miWanPayment(String linkid){
		return cache.get(key+linkid);
	}
	public void setMiWanPayment(String linkid,String data){
		cache.set(key+linkid, data,60*60*24);
	}
	
	public boolean imsiPayTypeTimeLimit(int payType,String imsi){
		String key = "payType_"+payType + "_"+imsi+"_needWaitTime";
		return cache.hasKey(key);
	}
	
	final int HOUR = 60 * 60 * 2;
	public void setUserMobile(long userId,String moblie){
		if(userId==0) return;
		
		String userMbKey = "userMb_" + userId;
		cache.set(userMbKey, moblie ,HOUR);
	}
	
	public String getUserMobile(long userId){
		String userMbKey = "userMb_" + userId;
		return cache.get(userMbKey);
	}
	public void incryByRedisTime(String key,int incryVal,int expireTime ) {
		int val = cache.get(key, 0);
		val=val+incryVal;
		cache.set(key, (Integer)val, expireTime);
	}
	
	
}
