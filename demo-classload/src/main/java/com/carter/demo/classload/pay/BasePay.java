package com.carter.demo.classload.pay;


//import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import com.zengame.platform.api.bean.RegisterInfo;
//import com.zengame.platform.api.bean.User;
//import com.zengame.platform.api.bean.config.ZgsdkAppInfo;
//import com.zengame.platform.api.bean.pay.*;
//import com.zengame.platform.api.bean.user.UserBindMobileBean;
//import com.zengame.platform.api.bean.user.UserDevInfo;
//import com.zengame.platform.api.bo.PayFilterBo;
//import com.zengame.platform.api.bo.UserAddressInfo;
//import com.zengame.platform.api.utils.Xor;
//import com.zengame.platform.common.tools.edc.MD5Util;
//import com.zengame.platform.common.tools.http.HttpUtil;
//import com.zengame.platform.common.tools.shell.ShellRuner;
//import com.zengame.platform.common.tools.utils.*;
//import com.zengame.platform.core.cache.xml.handle.core.GlobalCache;
//import com.zengame.platform.core.config.PayAttConfig;
//import com.zengame.platform.core.config.PropertiesConfig;
//import com.zengame.platform.core.memory.table.ZgsdkAppInfoMdb;
//import com.zengame.platform.core.utils.AccountLog;
//import com.zengame.platform.pay.common.PayFactory;
//import com.zengame.platform.pay.common.dao.*;
//import com.zengame.platform.pay.common.log.LogFactory;
//import com.zengame.platform.pay.common.manager.CacheSlaveManager;
//import com.zengame.platform.pay.common.manager.RedisCacheManager;
//import com.zengame.platform.pay.common.manager.RedisLocalManager;
//import com.zengame.platform.pay.common.utils.BeanManager;
//import com.zengame.platform.pay.common.utils.KeyUtils;
//import org.apache.log4j.Logger;

import java.util.*;

public abstract class BasePay {
	//spring 托管添加的bean保存[依赖代码，暂无好的解决方案]
	public Logger logger = LoggerFactory.getLogger("BasePay");
	public Logger errLog = LoggerFactory.getLogger("CustomExceptionFilter");

	/** 根据channel+gameId+payType查询pay initInfo信息 */
	public final int PAY_INITINFO_V1 = 1;

	/** 若channel+gameId+payType查询为空执行channel+gameId 查询获取pay initInfo信息 */
	public final int PAY_INITINFO_V2 = 2;

	/** 若channel+gameId+payType或channel+gameId 查询获取执行 payType查询获取 pay initInfo信息 */
	public final int PAY_INITINFO_V3 = 3;

	public static final int LIMIT_YES = 1; 
	public static final boolean LIMIT_NO = false;

	public static final int REPORT_SUCCESS = 1;// 客户端支付成功 结果码

	public RedisLocalManager redisLocal = BeanManager.getBeanClz(RedisLocalManager.class);
	public RedisCacheManager cacheManager = BeanManager.getBeanClz(RedisCacheManager.class);
	public CacheSlaveManager redisSlaveManager = BeanManager.getBeanClz(CacheSlaveManager.class);
	public PayConfigDao payConfigDao = BeanManager.getBeanClz(PayConfigDao.class);
	public PaySubCodeDao paySubCodeDao = BeanManager.getBeanClz(PaySubCodeDao.class);
	public GameSmsPayInfoConfigDao gameSmsPayInfoConfigDao = BeanManager.getBeanClz(GameSmsPayInfoConfigDao.class);
	public GmGiftDao gmGiftDao = BeanManager.getBeanClz(GmGiftDao.class);
	public GamePayInfoConfigDao gamePayInfoConfigDao = BeanManager.getBeanClz(GamePayInfoConfigDao.class);
	public UserDao userDao = BeanManager.getBeanClz(UserDao.class);
	public OrderDao orderDao = BeanManager.getBeanClz(OrderDao.class);
	public ProvinControlDao provinControlDao = BeanManager.getBeanClz(ProvinControlDao.class);
	public ViewShubCodeDao viewShubCodeDao = BeanManager.getBeanClz(ViewShubCodeDao.class);
	public MobileDao mobileDao = BeanManager.getBeanClz(MobileDao.class);
	public DeveloperCustomDao developerCustomDao = BeanManager.getBeanClz(DeveloperCustomDao.class);
	public ChannelAssignSubPayCodeDao chAsSubCodeDao = BeanManager.getBeanClz(ChannelAssignSubPayCodeDao.class);
	public PayGreepChDao payGreepChDao = BeanManager.getBeanClz(PayGreepChDao.class);
	public ZgsdkAppInfoMdb zgsdkAppInfoManager = BeanManager.getBeanClz(ZgsdkAppInfoMdb.class);
	public PayMmAppCfgDao payMmAppCfgDao = BeanManager.getBeanClz(PayMmAppCfgDao.class);
	public PropertiesConfig apollo = BeanManager.getBeanClz(PropertiesConfig.class);
	public UserInfoMeiTuanDao userInfoMeiTuanDao = BeanManager.getBeanClz(UserInfoMeiTuanDao.class);
	private CountryInfoDao countryDao = BeanManager.getBeanClz(CountryInfoDao.class);
	
	public JSONObject getExchangeRate(String country) {
		JSONObject findCurrency = countryDao.findCurrency(country);
		if(findCurrency == null) return ReturnUtils.fail("There is no exchange rate for this country:"+country);
		return ReturnUtils.success(findCurrency);
	}

	public JSONObject getExchangeRateByCurrency(String currency) {
		JSONObject findCurrency = countryDao.findByCurrency(currency);
		if(findCurrency == null) return ReturnUtils.fail("There is no exchange rate for this currency:"+currency);
		return ReturnUtils.success(findCurrency);
	}
	
	/**
     * 是否检测进行中订单
     * @return
     */
    public boolean isSupportPayingOrder(String sdkVersion,String channel) {
    	if(StringUtils.isEmpty(sdkVersion) || VerCompareUtil.verCompare(sdkVersion, "190311")<0) {
    		//此版本开始支持
    		return false;
    	}
    	int checkPayingOrder = apollo.getInt(PropertiesConfig.PAY, "checkPayingOrder", 0);
    	if(checkPayingOrder==0) {
    		//apollo开关
    		return false;
    	}
    	String checkPayingOrderCh = apollo.getString(PropertiesConfig.PAY, "checkPayingOrderCh");
    	if(StringUtils.isEmpty(checkPayingOrderCh)) {
    		return false;
    	}
    	if(!MatcherUtils.verify(channel, checkPayingOrderCh)) {
    		return false;
    	}
    	
    	return true;
    }

    public void writeBindThirdLoginLog(JSONObject param,int i ,long returnUserId,String third){

    	try {
			AccountLog.getInstance().account(103636,DateUtils.time(),param.getString("appId"),param.getString("channel"),param.getIntValue("apkVersion"),
					param.getString("sdkVersion"),i,param.getString("userId"),param.getString("guestUserId"),third,returnUserId);
		}catch (Exception e){
    		logger.error("三方绑定日志 error",e);
		}

	}
	
	/**
	 * 支付初始化[若有特殊，可自行重写]
	 * 
	 * @param requset
	 * @return
	 */
	public JSONObject gamePayInit(JSONObject request) {
		String channel = request.getString("channel");
		int appId = request.getIntValue("appId");
		int payType = request.getIntValue("payType");
		return getPayInitInfo(payType, appId, channel, PAY_INITINFO_V1);
	}
	
	public JSONObject checkSubCode(String channel, int payType, int gameId) {
		PaySubCode subCode = null;
		subCode = this.paySubCodeDao.findPaySubcodeInfo(payType,gameId, channel);
		//如果用三级渠道没查到就用二级渠道查
		if(subCode == null){
			//我司的游戏渠道号都是三级的(例:vivo.ttddz.123456),GM习惯配两级的,所以这里只取前两级渠道(例:vivo.ttddz)
			String prefix2Channel = OperateTools.getSecondChannel(channel);
			//从GM-充值管理-子代码  获取配置
			subCode = this.paySubCodeDao.findPaySubcodeInfo(payType,gameId, prefix2Channel);
		}
		if(subCode == null){
			return ReturnUtils.fail(2100, "gm子代码没配或开关没开" + " payType:" + payType + " gameId:" + gameId + " channel:" + channel);
		}
		JSONObject initInfo = PaySubCode.getJSONPayInitInfoFromSubCode(subCode);
		String subPayType = subCode.getSubCode();// GM-【子代码】中的子代码字段,必配主要用于财务对账
		if(initInfo == null){
			return ReturnUtils.fail(2103, "gm子代码初始化信息未配置" + " payType:" + payType + " gameId:" + gameId + " channel:" + channel);
		}
		if(StringUtils.isEmpty(subPayType)){
			return ReturnUtils.fail(2104, "gm子代码-子代码名 没配" + " payType:" + payType + " gameId:" + gameId + " channel:" + channel);
		}
		return initInfo;
	}

	/**
	 * 特殊数据进行预处理，自行重写
	 * 
	 * @param requset
	 * @return
	 */
	public void parseRequest(JSONObject request,JSONObject ext) {
	}

	/**
	 * 检查限额
	 * 
	 * @param userId
	 * @param gameId
	 * @param payType
	 * @param paymentMoney
	 * @return 0 未达到限制, 其他未达到限制
	 */
	public boolean checkIsLimit(PayFilterBo bo, long userId, int gameId,int payType, float paymentMoney) {
		return defalutCheckIsLimit(bo, bo.getUserId(), 0, payType, paymentMoney, true);
	}

	/**
	 * 检测更多限额
	 * 日月限,总限,省份限额,游戏限额,限额次数
	 * GM-充值管理-支付方式配置
	 * return false 超限
	 * return true 没超
	 */
	public boolean checkIsMoreLimit(PayFilterBo bo, int payType,	float paymentMoney,int gameId) {
		return defalutCheckIsMoreLimit(bo.getUserId(), gameId, payType, paymentMoney, bo.getInfo().getProvince());
	}
	/**
	 * 检测更多限额
	 * 日月限,总限,省份限额,游戏限额,限额次数
	 * return false 超限
	 * return true 没超
	 */
	public boolean defalutCheckIsMoreLimit(long userId,int gameId,int payType,float paymentMoney,String province){
		PayConfig payConfig = payConfigDao.findPayType(payType);
		if(payConfig == null) return false;//关闭或者没配,也等于超限
		
		//省份限额
		String proLimit = payConfig.getProLimit();
		//游戏限额
		String gameLimit = payConfig.getGameLimit();
		if(!StringUtils.isEmpty(proLimit)){
			boolean ret = checkProLtdByPayCfg(proLimit,payType,paymentMoney,province);
			if(!ret)return false ;
		}
		if(!StringUtils.isEmpty(gameLimit)){
			boolean ret = checkGameLtdByPayCfg(gameLimit,payType,paymentMoney,gameId);
			if(!ret)return false ;
		}
		
		//用户日月限次数
		boolean ret = checkDayMonthLtdNumBySmsPayCfg(userId, payType, paymentMoney);
		if(!ret) return false; 
		
		return true;
	}
	/**
	 * 检查短代配置中的用户日月限次数
	 * return false 超限
	 * return true 没超
	 */
	public Boolean checkDayMonthLtdNumBySmsPayCfg(long userId, int payType, float money){
		GameSmsPayInfoConfig smsPayCfg = gameSmsPayInfoConfigDao.getGameSmsPayInfoConfigByPayTypeMoney(payType, money);
		if(smsPayCfg == null || StringUtils.isEmpty(smsPayCfg.getPayInfo())){
			return true;
		}
		JSONObject initInfo = JSONObject.parseObject(smsPayCfg.getPayInfo());
		int dayLtdNumCfg = initInfo.getIntValue("dayLimit");
		int monthLtdNumCfg = initInfo.getIntValue("monthLimit");
		if(dayLtdNumCfg != 0){
			float num = redisSlaveManager.getLimitByType("uDayNum",payType,userId,money);
			if(num>=dayLtdNumCfg){
				return false;
			}
		}
		if(monthLtdNumCfg != 0 ){
			float num = redisSlaveManager.getLimitByType("uMonthNum",payType,userId,money);
			if(num>=monthLtdNumCfg){
				return false;
			}
		}
		return true;
	}
	/**
	 * 检查支付方式配置中的游戏限额
	 * return false 超限
	 * return true 没超
	 */
	public Boolean checkGameLtdByPayCfg(String gameLimit,int payType,float money,int gameId){
		if(!StringUtils.isEmpty(gameLimit)){
			JSONObject allGameArr = OperateTools.parseProLimit(gameLimit);
			
			if(allGameArr.containsKey(gameId+"")){
				float rm = redisSlaveManager.getLimitByType("gameLtd",payType,gameId);
				if(rm + money > allGameArr.getIntValue(gameId+"")){
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * 检查支付方式配置中的省份限额
	 * return false 超限
	 * return true 没超
	 */
	public Boolean checkProLtdByPayCfg(String proLimit,int payType,float money,String province){
		if(!StringUtils.isEmpty(proLimit)){//省份限额
			JSONObject allProArr = OperateTools.parseProLimit(proLimit);
			if(allProArr.containsKey(province)){
				String provinceNum = GlobalCache.getMobileIdByProvince(province);
				float rm = redisSlaveManager.getLimitByType("proLtd",payType,provinceNum);
				if(rm + money > allProArr.getIntValue(province)){
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 支付类型特殊订单号
	 * 
	 * @return
	 */
	public String getOrderNum() {
		return null;
	}

	/**
	 * 单机订单
	 * 
	 * @return
	 */
	public String getSingleOrderNum() {
		return null;
	}

	/**
	 * 支付类型自定义支付信息
	 * 
	 * @param gameId
	 * @param channel
	 * @param payType
	 * @param paymentMoney
	 * @param itemId
	 * @param extraInfo
	 * @return
	 */
	public JSONObject getPayInfo(PayFilterBo bo, int payType,int gameId) {
		return null;
	}

	/**
	 * 计费点列表 主要是单机短代会使用
	 * 
	 * @param bo
	 * @param payType
	 * @return
	 */
	public JSONArray getPayCodeList(JSONObject request,int payType) {
		return this.defGetPayCodeList(request, payType);
	}
	
	/**
	 * 订单过滤
	 * 
	 * @param requset
	 * @param orders
	 */
	public JSONObject fillOrder(JSONObject request, PayOrder order) {
		return null;
	}

	/**
	 * 解析回调参数
	 * 
	 * @param request
	 * @return
	 */
	public JSONObject decodeCallbackRequest(JSONObject request) {
		return request;
	}

	/**
	 * 订单校验
	 * 
	 * @param order
	 * @return
	 */
	public boolean verify(PayOrder order, JSONObject request,
			JSONObject thirdParam) {
		return false;
	}

	/**
	 * 
	 * @param order
	 * @param thirdParam
	 */
	public void recordLimit(PayOrder order, JSONObject request,
			JSONObject thirdParam) {

	}
	public void recordMoreLimit(PayOrder order, JSONObject request,JSONObject thirdParam) {
		//测试充值不记录限额(orderType=2或者绿色通道支付的订单)
		if(order.getOrderType() != 2 && !StringUtils.equals(order.getExtraInfo().getString("From"), "greenCh")){
		//redis存用户的充值日限额
		String dailyChargeAmount = KeyUtils.getKey("dailyChargeAmount", new Object[]{DateUtils.getMD(), order.getUserId(), order.getGameId()});
		float value = cacheManager.getCache().get(dailyChargeAmount, 0f);
		float paymentMoney = order.getPaymentMoney();
		value += paymentMoney;
		cacheManager.getCache().set(dailyChargeAmount, value, 86400);

	}
	}
	/**
	 * 记录某个支付的省份限额
	 */
	public void recordProvinceLtd(int payType,String province,float actualMoney){
		String dayDate=DateUtils.dateToString(new Date(), DateUtils.FORMAT_ONE);
		String proId = GlobalCache.getMobileIdByProvince(province);
		int redisTime=(int)DateUtils.getlessTime();
		String proLtdK = KeyUtils.getKey("proLtd",new Object[] {payType,proId,dayDate});
		float proLtdMoney=cacheManager.getCache().get(proLtdK, 0f);
		cacheManager.getCache().set(proLtdK, String.valueOf(proLtdMoney+actualMoney),redisTime);
	}
	/**
	 * 记录某个支付的游戏限额
	 */
	public void recordGameLtd(int payType,int gameId,float actualMoney){
		int redisTime=(int)DateUtils.getlessTime();
		String dayDate=DateUtils.dateToString(new Date(), DateUtils.FORMAT_ONE);
		String gameLtdK = KeyUtils.getKey("gameLtd",new Object[] {payType,gameId,dayDate});
		float gameLtdMoney=cacheManager.getCache().get(gameLtdK, 0f);
		cacheManager.getCache().set(gameLtdK, String.valueOf(gameLtdMoney+actualMoney), redisTime);
	}
	/**
	 * 记录用户日月限次数
	 */
	public void recordUserLtdNum(int payType,long userId,float paymentMoney){
		int redisDayTime=(int)DateUtils.getlessTime();
		String dayDate=DateUtils.dateToString(new Date(), DateUtils.FORMAT_ONE);
		String uDayNumK = KeyUtils.getKey("uDayNum",new Object[] {payType,userId,paymentMoney,dayDate});
		int uDayNum=cacheManager.getCache().get(uDayNumK, 0);
		cacheManager.getCache().set(uDayNumK, (Integer)(++uDayNum), redisDayTime);
		
		String newMonth=DateUtils.getNextMonth(DateUtils.FORMAT_ONE);
		int newMonthTimestamp = (int)DateUtils.strtotime(newMonth, DateUtils.FORMAT_ONE);
		int redisMonthTime=(int)(newMonthTimestamp-DateUtils.time());
		String monthDate=DateUtils.dateToString(new Date(), DateUtils.FORMAT_THREE);
		String uMonthNumK = KeyUtils.getKey("uMonthNum",new Object[] {payType,userId,paymentMoney,monthDate});
		int uMonthNum=cacheManager.getCache().get(uMonthNumK,0);
		cacheManager.getCache().set(uMonthNumK, (Integer)(++uMonthNum), redisMonthTime);
	}
	

	/**
	 * 第三方上报
	 * 
	 * @param state
	 */
	public String thirdResp(int state, PayOrder order, JSONObject request,JSONObject thirdParam) {
		return null;
	}

	/**
	 * 初始化第三方参数(解析回调参数)
	 * 
	 * @param requset
	 * @return
	 */
	public JSONObject thirdParamInit(JSONObject request) {
		return null;
	}
	public JSONObject thirdParamInit(JSONObject request,JSONObject ext){
		return null;
	};
	public JSONObject forwarding() {
		return null;
	}

	/**
	 * 是否重写getSerialCommand方法
	 * 
	 * @return
	 */
	public boolean hasSerialCommand() {
		return false;
	}
	
	/**
	 * 是否重写getUplinkCommand方法
	 * 
	 * @return
	 */
	public boolean hasUplinkCommand() {
		return false;
	}
	/**
	 * 是否重写onClientPayReport方法
	 * 
	 * @return
	 */
	public boolean hasClientPayReport() {
		return false;
	}

	/**
	 * 单次指令
	 * 
	 * @return
	 */
	public JSONObject getUplinkCommand(JSONObject request, PayOrder order) {
		return null;
	}

	/**
	 * 连环指令
	 * 
	 * @return
	 */
	public JSONObject getSerialCommand(JSONObject request, PayOrder order) {
		return null;
	}

	/**
	 * 客户端支付上报处理
	 * 
	 * @param reportRet
	 * @param request
	 * @param order
	 * @return
	 */
	public void onClientPayReport(int payRet, JSONObject request,PayOrder order) {
	}
	/**
	 * 支付上报时需求特殊处理的
	 * 
	 * @param reportRet
	 * @param request
	 * @param order
	 * @return
	 */
	public void payTypeNeedToSpecialControl(int payRet, JSONObject request,PayOrder order) {
	}
	public boolean hasPayNeedToSpecCtrl(){
		return false;
	}

	/**
	 * 第三方登陆
	 * 
	 * @param request
	 * @return
	 */
	public JSONObject checkThirdUser(JSONObject request) {
		return null;
	}
	/**
	 * 第三方登陆响应前修改数据
	 * 
	 * @param request
	 * @return
	 */
	public JSONObject beforeLoginRsp(JSONObject output, JSONObject thirdUserInfo, User userInfo,JSONObject request)
	{
		return output;
	}
	
	/**
	 * 修改参数
	 * @param extraInfo
	 */
	public void modifyExtraInfo(JSONObject extraInfo){
		
	}
	
	/**
	 * 修改订单返回
	 * @param payInfo
	 * @param paymentId
	 */
	public void modifyPaymentId(JSONObject payInfo,String paymentId){
		
	}

	/**
	 * 设置达到限额
	 */
	protected void setReachDayLimitByOrder(PayOrder orders) {

	}

	/**
	 * 对回调进行包装返回
	 * 
	 * @param newKey
	 *            多个以逗号分隔
	 * @param thirdKey
	 *            多个以逗号分隔
	 * @param source
	 * @return
	 */
	protected JSONObject copyFromJson(String newKey, String thirdKey,JSONObject source) {
		String[] nk = newKey.split(",");
		String[] tk = thirdKey.split(",");

		JSONObject nj = new JSONObject();
		for (int i = 0; i < tk.length; i++) {
			nj.put(nk[i], source.get(tk[i]));
		}
		return nj;
	}

	final String[] dfNk = { "paymentId", "outTradeNo", "actualMoney" };

	/**
	 * 对回调进行包装返回 默认包装新key[paymentId,outTradeNo,actualMoney]
	 * 
	 * @param thirdKey
	 *            多个以逗号分隔
	 * @param source
	 * @param coefficient
	 *            最后一个参数系数
	 * @return
	 */
	protected JSONObject copyFromJson(String thirdKey, JSONObject source,float coefficient) {
		String[] tk = thirdKey.split(",");

		JSONObject nj = new JSONObject();
		for (int i = 0; i < tk.length; i++) {
			if (i != 2) {
				nj.put(dfNk[i], source.get(tk[i]));
				
			}else {
				//290等乘于0.01F会变成2.899
				if(coefficient==0.01F) {
					nj.put(dfNk[i], source.getFloatValue(tk[i]) /100);
				}else {
					nj.put(dfNk[i], source.getFloat(tk[i]) * coefficient);
				}
			}
		}
		return nj;
	}

	/**
	 * 记录限额
	 *
	 */
	protected void defRecordLimitByOrder(PayOrder order, JSONObject request,JSONObject thirdParam) {
		float money = order.getActualMoney() == 0f ? order.getPaymentMoney(): order.getActualMoney();
		cacheManager.recordDayLimitByUserId(order.getUserId(),String.valueOf(order.getPayType()), money);
		cacheManager.recordMonthLimitByUserId(order.getUserId(),String.valueOf(order.getPayType()), money);
		String payType = String.valueOf(order.getPayType());
		if (!StringUtils.isEmpty(order.getPaySubType())) {
			payType = order.getPaySubType() + "_" + payType;
		}
		cacheManager.recordDayLimitByPaytype(payType, money);
		cacheManager.recordMonthLimitByPaytype(payType, money);
	}

	/**
	 * 获取支付pay initInfo 信息
	 * 
	 * @param payType 支付类型
	 * @param gameId  游戏ID
	 * @param channel 渠道
	 * @param level 筛选条件层级[1->(gameId+channel+payType) 2->[(gameId+channel+payType)或(payType+gameId)]3->[(gameId+channel+payType)或(payType+gameId)或(payType)]
	 * @return
	 */
	protected JSONObject getPayInitInfo(int payType, int gameId,String channel, int level) {
		channel = OperateTools.getSecondChannel(channel); // 获取二级渠道
		PaySubCode subCode = this.paySubCodeDao.findPaySubcodeInfo(payType,gameId, channel);

		if (level >= 2 && subCode == null)
			subCode = this.paySubCodeDao.findSubCodeByPayTypeAndGameId(payType,gameId);
		if (level >= 3 && subCode == null) {
			subCode = this.paySubCodeDao.findPaySubcode(payType);
		}
		
		JSONObject res = PaySubCode.getJSONPayInitInfoFromSubCode(subCode);
//		if (res == null) {
//			logGameInitIsNull(payType, gameId, channel);
//		}
		return res;
	}
	protected JSONObject getPayInitInfo(PayOrder order) {
		int payType = order.getPayType();
        int appId = order.getExtraInfo().getInteger("appId");
        String channel = order.getExtraInfo().getString("channel");
        JSONObject payInitInfo = getPayInitInfo(payType, appId, channel, PAY_INITINFO_V1);
        return payInitInfo;
	}

	protected JSONObject getSubCodeConfig(int payType, int gameId,String channel, int level) {
		channel = OperateTools.getSecondChannel(channel); // 获取二级渠道
		PaySubCode subCode = this.paySubCodeDao.findPaySubcodeInfo(payType,gameId, channel);

		if (level >= 2 && subCode == null)
			subCode = this.paySubCodeDao.findSubCodeByPayTypeAndGameId(payType,gameId);

		if (level >= 3 && subCode == null) {
			subCode = this.paySubCodeDao.findPaySubcode(payType);
		}
		return PaySubCode.getJSONSubCodeConfigFromSubCode(subCode);
	}

	/**
	 * 获取支付pay initInfo 信息
	 * 
	 * @param request 请求信息
	 * @param fromKey 返回initInfo中的哪些字段 若为空表示返回initInfo
	 * @param newKey  包装成新字段，若为空表示与fromKey一样
	 * @param level   筛选条件层级[1->(gameId+channel+payType) 2->[(gameId+channel+payType)或(payType+gameId)]3->[(gameId+channel+payType)或(payType+gameId)或(payType)]
	 * @return
	 */
	protected JSONObject getPayInitInfo(JSONObject request, String fromKey,String newKey, int level) {
		int gameId = request.getIntValue("gameId");
		String channel = request.getString("channel");
		int payType = request.getIntValue("payType");

		channel = OperateTools.getSecondChannel(channel); // 获取二级渠道
		PaySubCode subCode = this.paySubCodeDao.findPaySubcodeInfo(payType,gameId, channel);

		if (subCode == null && level >= 2)
			subCode = this.paySubCodeDao.findSubCodeByPayTypeAndGameId(payType,gameId);

		if (subCode == null && level >= 3) {
			subCode = this.paySubCodeDao.findPaySubcode(payType);
		}

		JSONObject res = PaySubCode.getJSONPayInitInfoFromSubCode(subCode);
		if (res != null && fromKey != null) {
			res = copyFromJson(newKey == null ? fromKey : newKey, fromKey, res);
		}
		return res;
	}

	// 支付类型默认检测
	/**
	 * 支付类型默认检测
	 * 
	 * @param userId
	 * @param gameId  为0表示用户日月限额
	 * @param payType
	 * @param money
	 * @return true 已达限额 false 无限额
	 */
	public boolean defalutCheckIsLimit(PayFilterBo bo, long userId, int gameId,int payType, float money,boolean checkPlayCount) {
		float dval = 0f;
		float mval = 0f;
		
		if(gameId == 0){
			dval = redisSlaveManager.getDayLimitByUserId(userId, String.valueOf(payType));
			mval = redisSlaveManager.getMonthLimitByUserId(userId, String.valueOf(payType));
		}else{
			return false;
		}
		
		float dayValue = dval + money;
		float monthValue = mval + money;

		PayConfig config = this.payConfigDao.findPayType(payType);

		int dayLimit = config.getDayLimit();
		if (dayLimit > 0 && dayValue > dayLimit) {
			return true;
		}
		
		int monthLimit = config.getMonthLimit();
		if (monthLimit > 0 && monthValue > monthLimit) {
			return true;
		}

		// 每日总量检测
		int dayTotalLimit = config.getDayTotalLimit();
		float dayPayMoney = redisSlaveManager.getDayLimit(payType);
		if (dayTotalLimit > 0 && dayPayMoney >= dayTotalLimit) {
			return true;
		}
		
		// 对局检测
		if(checkPlayCount){
			int minPlayCount = config.getMinPlayCount();
			if (minPlayCount > 0) {
				int count = this.getGamePlayCount(userId, gameId, minPlayCount);
				if (count < minPlayCount) {
					return true;
				}
			}
		}

		return false;
	}

	public JSONArray defGetPayCodeList(JSONObject request, int payType) {
		int gameId = request.getIntValue("gameId");
		List<GameSmsPayInfoConfig> gspcList = this.gameSmsPayInfoConfigDao.getGameSmsPayInfoConfigByPS(payType, 1);
		if (gspcList == null || gspcList.size() == 0)return null;
		
		String province = request.getString("province");
		
		List<GameSmsPayInfoConfig> canUseList = new ArrayList<GameSmsPayInfoConfig>();
		for (GameSmsPayInfoConfig gspc : gspcList) {
			if (gspc.getLocation() != null && !"".equals(gspc.getLocation())) {
				if (gspc.getLocation().indexOf(province) == -1)continue;
			}
			if(gspc.getGameId()==0 || ( payType==43 && gameId!=gspc.getGameId() ))continue;
			canUseList.add(gspc);
		}

		if (canUseList.size() == 0)
			return null;

		PayConfig pc = this.payConfigDao.findPayType(payType);
		if (pc == null)return null;

		JSONObject payInfo;
		JSONArray retCodeArr = new JSONArray();
		for (GameSmsPayInfoConfig gspc : canUseList) {
			float money = gspc.getMoney();
			String so = this.getSingleOrderNum();
			payInfo = JSONObject.parseObject(gspc.getPayInfo());
			String payCode = payInfo.getString("code");
			if(payType==43){
				payCode = payInfo.getString("code")+so+payInfo.getString("channelId");
			}
			payInfo.fluentPut("payCode", payCode);
			payInfo.fluentPut("money", money);
			payInfo.fluentPut("isMobile", true);
			payInfo.fluentPut("dayLimit", pc.getDayLimit());
			payInfo.fluentPut("monthLimit", pc.getMonthLimit());
			payInfo.remove("code");
			retCodeArr.add(payInfo);
		}
		return retCodeArr;
	}
	

	public boolean isPayBlackList(long userId,String imei){
		return ArrayUtils.checkItem(PayAttConfig.payUserBlackList, userId) || ArrayUtils.checkItem(PayAttConfig.payImeiBlackList, imei);
    }

	/**
	 * 是否是包月道具
	 * @param itemId
	 * @return
	 */
	public boolean isMonthlyItem(int itemId) {
		return ArrayUtils.checkItem(PayAttConfig.monthItems, itemId);
	}
	
	/**
	 * 获取对局数
	 * 
	 * @param userId
	 * @param gameId
	 * @param minCount
	 * @return
	 */
	public int getGamePlayCount(long userId, int gameId, int minCount) {
		int userPlayCount = cacheManager.getGamePlayCount(userId, gameId, -1);
		if (userPlayCount != -1)return userPlayCount;
		JSONObject ret = ShellRuner.getUserInfoByShell(userId, gameId);
		if (ret != null) {
			int dj = ret.getJSONArray("todayPlayCount").getIntValue(0);
			cacheManager.setGamePlayCount(userId, gameId, dj,dj > minCount ? 600 : 30);
			return dj;
		}
		return -1;
	}

	/**
	 * 是否微信限制
	 */
	protected boolean checkWXLimit(int itemId , PayFilterBo bo) {
		String gameVersion = bo.getGameVersion();
		boolean webSend = ( 31050==bo.getChargeGameId()||31073==bo.getChargeGameId()||31083==bo.getChargeGameId() ) && VerCompareUtil.verCompare(gameVersion, "305440")>=0;//服务端发货
		if(itemId >= 900 && !webSend) return true;
		return false;
	}

	/**
	 * 获取verifyInfo
	 * 
	 * @param gameId
	 * @param channel
	 * @param payType
	 */
	protected JSONObject getGamePayVerifyInfo(int gameId, String channel,int payType) {
		channel = OperateTools.getSecondChannel(channel); // 获取二级渠道
		JSONObject info = getSubCodeConfig(payType, gameId, channel,PAY_INITINFO_V1);
		return info;
	}

	/**
	 * 设置第三方结果信息
	 * 
	 * @param ret
	 * @param retMsg
	 */
	protected void setThirdRet(String ret, int payType, JSONObject thirdParam) {
		if (thirdParam != null) {
			thirdParam.put("thirdRet", ret);
			thirdParam.put("thirdRetMsg", getRetMsg(payType, ret));
		}
	}
	
	public JSONObject getCarrirShowCfg(String channel,String apkVersion,int payType,String lbsPro,String subCode,String andGamePJSdkVersion){
		int carrirShow = 0;
		String provider="";
		if(payType==410 && "26.004".equals(andGamePJSdkVersion)){//cowboy:基地自制框有问题,但是急着出包,该版本先不支持  2017/01/12
			return new JSONObject().fluentPut("carrirShow", 0).fluentPut("provider", "");
		}
		
		boolean needCarrirShow = false;
		if(payType==281 && ArrayUtils.checkItem(PayAttConfig.mmTskChannel, channel)){
			if(StringUtils.isEmpty(lbsPro) || PayAttConfig.mmTskPro.indexOf(lbsPro) != -1){
				needCarrirShow = true;
				logger.info(channel +  " , " + lbsPro);
			}
		}else if( (payType==281 && PayAttConfig.channelCarrierShowCfg.containsKey(channel)) || ArrayUtils.checkItem(String.valueOf(payType),PayAttConfig.carrirShowPayType)){
			needCarrirShow = true;
		}
		
		
		
		if(needCarrirShow){
			carrirShow = 1;
			String companyName = "";
			companyName=viewShubCodeDao.getCompanyNameBySubCode(subCode,payType);
			provider = StringUtils.isEmpty(companyName)?PayAttConfig.channelCarrierShowCfg.getString(channel):companyName;
			
			if(StringUtils.isEmpty(provider)){
				logError("channel:"+channel+",subCode:"+subCode+",公司名称为空!");
			}
		}
		
		
		return new JSONObject().fluentPut("carrirShow", carrirShow).fluentPut("provider", provider);
	}
	
	/**
	 * 判断是否为微信
	 * @param value
	 * @return
	 */
	public boolean hasWx(String value){
		return value != null && ("1".equals(value) || "true".equals(value));
	}
	
	/**
	 * 通过结果码获取结果描述
	 * 
	 * @param ret
	 */
	private String getRetMsg(int payType, String ret) {
		return "";
	}

	protected void putErrRet(JSONObject result) {
		result.put("err_ret", 1);
	}

	protected JSONObject getPayInitInfo(PaySubCode subCode) {
		return PaySubCode.getJSONPayInitInfoFromSubCode(subCode);
	}

	protected void logInfo(String log) {
		logger.info(this.getClass().getSimpleName() + ":" + log);
	}

	protected void logError(String log) {
		logger.error(this.getClass().getSimpleName() + ":" + log);
	}

	protected void logGameInitIsNull(int payType, int gameId, String channel) {
		logger.error(this.getClass().getSimpleName() + ":"+ " game init info is null :  " + payType + "-" + gameId + "-"+ channel);
	}

	/**
	 * 省份验证
	 * 
	 * @param data
	 * @param province
	 * @return true [包含省份]
	 */
	protected boolean smsProvinceVerify(GameSmsPayInfoConfig data,String province) {
		boolean result = data != null	&& !StringUtils.isEmpty(data.getLocation())&& data.getLocation().indexOf("province") != -1;
		return result;
	}

	/**
	 * md5验证
	 * 
	 * @param sign
	 *            md5值
	 * @param data
	 *            要验证的md5串
	 * @return
	 */
	protected boolean md5Verify(String sign, String data) {
		boolean res = sign.equals(MD5Util.md5Encode(data));
		if (!res) {
			logError(this.getClass().getSimpleName()	+ ": md5 verify error !,sign:" + sign + " , data:" + data);
		}
		return res;
	}

	/**
	 * md5验证
	 * 
	 * @param sign
	 *            md5值
	 * @param ourSign
	 *            我的秘钥值
	 * @param data
	 *            要验证的md5串
	 * @return
	 */
	protected boolean md5Verify1(String sign, String ourSign, String data) {
		boolean res = sign.equals(ourSign);
		if (!res) {
			logError(this.getClass().getSimpleName()+ ": md5 verify error !,sign:" + sign + " ourSign : "+ ourSign + " , data:" + data);
		}
		return res;
	}

	protected int getNowTime() {
		return (int) (System.currentTimeMillis() / 1000);
	}
	
	
	public String getModKey(String wwAppId,int payType){
		return wwAppId+"_"+payType;
	}
	
	//正序排列[小的在前大的在后]
	/**
	 * 子代码优先级排序
	 * @param sorts
	 */
	public void sort(List<JSONObject> sorts){
		Collections.sort(sorts,new Comparator<JSONObject>() {
			@Override
			public int compare(JSONObject o1, JSONObject o2) {
				if(o1.getIntValue("prior") > o2.getIntValue("prior")) return 1;
				else if(o1.getIntValue("prior") < o2.getIntValue("prior")) return -1;
				return 0;
			}
		});
	}
	public List<String> getChannelAsSubCode(int payType,String channel,int apkVersion){
		String [] tmpCas;
		List<String> oneForOneSubPayCode = new ArrayList<String>();
		List<ChannelAssignSubPayCode> channelAssignSubPayCode=chAsSubCodeDao.findChannelAssignSubPayCode(payType);
		for(ChannelAssignSubPayCode cas:channelAssignSubPayCode){
			if(!StringUtils.isEmpty(cas.getChannelMatch()) && MatcherUtils.verify(channel+"-"+apkVersion, cas.getChannelMatch())){
				tmpCas=cas.getSubPayCodeList().split("\\|");
				for(String t:tmpCas){
					oneForOneSubPayCode.add(t);
				}
			}
		}
		return oneForOneSubPayCode;
	}
	public JSONObject getMmAppCfgs(){
		JSONObject newData=new JSONObject();
		List<PayMmAppCfg> data = payMmAppCfgDao.findPayMmAppCfgs();
		for(PayMmAppCfg d:data){
			newData.put(d.getMmAppId(), d);
		}
		return newData;
	}
	/**
	 * 检查MM应用日限
	 * @return true超限 false没超
	 */
	public boolean checkMmAppDayLimit(String mmAppId,JSONObject mmAppCfgs,String province,float paymentMoney){
		if(!mmAppCfgs.containsKey(mmAppId))return false;
		PayMmAppCfg mmAPPCfg = mmAppCfgs.getObject(mmAppId, PayMmAppCfg.class);
		String day = DateUtils.dateToString(new Date(), "MMdd");
		int dayLimitCfg = mmAPPCfg.getDayTotalLimit();
		String provinceLimit = mmAPPCfg.getProvinceLimit();
		if(dayLimitCfg>0){//应用日限
			String key = "pLimit_mmApp_"+day+"_"+mmAppId;
			int limited = redisSlaveManager.getCache().get(key,0);
			if(limited+paymentMoney>=dayLimitCfg)return true;
		}
		if(!StringUtils.isEmpty(provinceLimit)){//应用分省限额
			JSONObject provinceLimitCfgs = JSONObject.parseObject(provinceLimit);
			if(provinceLimitCfgs.containsKey(province)){
				int provinceLimitCfg = provinceLimitCfgs.getIntValue(province);
				if(provinceLimitCfg>0){
					String provinceKey = "pLimit_mmApp_"+day+"_"+mmAppId+"_"+province;
					int limited = redisSlaveManager.getCache().get(provinceKey,0);
					if(limited+paymentMoney>=provinceLimitCfg)return true;
				}else if(provinceLimitCfg==0){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 获取审核头像
	 * @param user
	 * @return
	 */
	public static String getIconUrl(User user){
		String iconUrl = user.getIconUrl() == null ? "" : user.getIconUrl();
		if((iconUrl.indexOf(".jpg")!=-1 || iconUrl.indexOf(".png")!=-1) && iconUrl.indexOf("http")==-1){
			if (iconUrl.indexOf(user.getUserId()+"")!=-1){
				iconUrl= Constants.NEW_ICON_URL+iconUrl;
			}else {
				iconUrl=Constants.ICON_URL+iconUrl;
			}
		}
		return iconUrl;
	}


	/**
	 * 获取审核头像
	 * @param user
	 * @return
	 */
	public static String getUnauditedIconUrl(User user){
		String unauditedIconUrl = user.getUnauditedIconUrl() == null ? "" : user.getUnauditedIconUrl();
		if((unauditedIconUrl.indexOf(".jpg")!=-1 || unauditedIconUrl.indexOf(".png")!=-1) && unauditedIconUrl.indexOf("http")==-1){
			unauditedIconUrl = Constants.UNAUDITED_ICON_URL+user.getUnauditedIconUrl();
		}
		return unauditedIconUrl;
	}




	public JSONObject getoutput(User userInfo,JSONObject thirdUserInfo,String key,int expireTime,JSONObject request,UserAddressInfo address){
		String username = thirdUserInfo.getString("username");
		int appId = request.getIntValue("appId");
		if(StringUtils.isEmpty(username)){
			username = userInfo.getNickName();
		}
		
		String iconUrl=getIconUrl(userInfo);
		String unauditedIconUrl = getUnauditedIconUrl(userInfo);

	
		String tg = thirdUserInfo.getString("gender");
		
		String gender = "";
		if(!StringUtils.isEmpty(tg)){
			gender = "女".equals(tg)?"F":"M";
		}else{
			gender = StringUtils.isEmpty(userInfo.getGender())?"M":userInfo.getGender();
		}
		
		String ticon = thirdUserInfo.getString("iconurl");
		ticon = ticon == null ? "" : ticon;
		
		
		JSONObject arr = new JSONObject();
		String mobile = userInfo.getMobile();
		if (StringUtils.isEmpty(mobile)){
			UserBindMobileBean userBindMobile = userDao.findUserBindMobileByUserId(userInfo.getUserId());
			if (userBindMobile!=null){
				mobile = userBindMobile.getMobile();
			}else {
				mobile = "0";
			}
		}
		arr.put("mobile",mobile);
		arr.put("state", "Y");
		arr.put("ret", "1");
		arr.put("msg", "success");
		arr.put("userId", String.valueOf(userInfo.getUserId()));
		arr.put("key", key);
		arr.put("time", expireTime);
		arr.put("accountName", username);
		arr.put("nickName", username);
		arr.put("thirdUid", thirdUserInfo.get("userid"));
		arr.put("hallpoint", String.valueOf(userInfo.getHallpoint()));
		arr.put("platCoin", String.valueOf(OperateTools.formatPriceData(userInfo.getPlatCoin())));
		arr.put("gender", gender);
		arr.put("icon", StringUtils.isEmpty(userInfo.getIcon())?"1":userInfo.getIcon());
		arr.put("iconUrl",StringUtils.isEmpty(iconUrl)?ticon:iconUrl);
		arr.put("unauditedIconUrl", unauditedIconUrl);
		arr.put("unIconUrl", unauditedIconUrl);//原字段名unauditedIconUrl包含audit,为ios敏感词
		arr.put("hasRealName", !StringUtils.isEmpty(userInfo.getTrueName()) && !StringUtils.isEmpty(userInfo.getIdCard()) ? 1 : 0);
		
		ZgsdkAppInfo zgAppInfo = zgsdkAppInfoManager.findZgsdkAppInfoByAppId(appId);
		String secretKey=com.zengame.platform.common.tools.utils.Constants.tokenPriKey;
		secretKey=zgAppInfo==null?secretKey:zgAppInfo.getPaySecret();
		//加密秘钥
		long time = System.currentTimeMillis(); 
		String aseKey = MD5Util.md5Encode(userInfo.getUserId()+"|"+time+"|"+com.zengame.platform.common.tools.utils.Constants.asePriKey).substring(0, 16);
		arr.put("expireTime", time);
		arr.put("token", makeLoginToken(String.valueOf(userInfo.getUserId()),expireTime,secretKey));
		arr.put("signkey", aseKey);
		arr.put("province", address.getProvince());
		
        return arr;
	}
	private String makeLoginToken(String userId,int time,String appKey){
		String str=time+""+userId+""+appKey;
		String md5 = MD5Util.md5Encode(str);
		return md5;
	}
	/**
     * 获取订单信息
     * @param string paymentId
     * @return array
     */
    public PayOrder getRedisOrder(String paymentId){
    	if(StringUtils.isEmpty(paymentId))return null;
    	String info = cacheManager.getCache().get(paymentId);
    	Logger dPayNameLog = getPayClassLogger();
    	if(StringUtils.isEmpty(info) && isDevelopment()) {
    		info = getOrderByOutNet(paymentId);
    	}
    	PayOrder bill = null;
    	dPayNameLog.info(paymentId+"|orderInfo|"+info);
    	if(!StringUtils.isEmpty(info)){		
    		bill = PayOrder.toBean(info);
    	}
        return bill;
    }
    /**
     * 外网订单到内网调试用:从外网下载订单信息到内网
     * @param paymentId
     * @return
     */
    private String getOrderByOutNet(String paymentId) {
		String url = "http://81.69.227.57/test/tools/getPayRedisData?paymentId="+paymentId;
        String info = HttpUtil.doGet(url, null);
        cacheManager.getCache().set(paymentId, info, 60*60*24);
        return info;
	}
	public void syncCallback(String requestStr,JSONObject ext,JSONObject request) {
		String uri = ext.getString("uri");
		String requestType="get";
		String method=ext.getString("method");
    	if(StringUtils.equals(method,"POST")){
    		requestType="post";	
    	}
		httpRequestDevelopment(uri, requestStr,requestType,null,ext,request);
	}
	private String developmentUrl = "https://platweb.zgverse.com:23182";
	//国内外网回调域名
	private String domesticCbUrl = "http://pcb.365you.com";
	public void httpRequestDevelopment(String requestUrl,String requestData,String requestType,String dataType,JSONObject ext,JSONObject request){
        //如果是海外的空订单则回调到国内的服务器
        if(OperateTools.hasOverseas()) requestUrl = domesticCbUrl+requestUrl;
        else requestUrl = developmentUrl+requestUrl;
        HashMap<String,String> header=new HashMap<String,String>();
        String ret="";
        if(StringUtils.equals(requestType,"get")){
            JSONObject params = OperateTools.parseUrlToJson2(requestData);
            ret=HttpUtil.doGet(requestUrl,params);
        }else if(StringUtils.equals(requestType,"post") && StringUtils.equals(dataType,"xml")){
        	header.put("Content-Type","application/xml");
        	ret=HttpUtil.doPost(requestUrl, requestData, header);
        }else if(StringUtils.equals(requestType,"post") && StringUtils.equals(dataType,"json")){
        	header.put("Content-Type","application/json");
        	ret=HttpUtil.doPost(requestUrl, requestData, header);
        }else if(StringUtils.equals(requestType,"post")){
        	if(!StringUtils.isEmpty(request.getString("postBody"))) {
        		header.put("Content-Type","text/html");
        		ret=HttpUtil.doPost(requestUrl, requestData, header);
        	}else {
        		ret=HttpUtil.doPost(requestUrl, request);
        	}
        }
        Logger dPayNameLog = getPayClassLogger();
		dPayNameLog.info("httpRequestDevelopment|requestUrl:"+requestUrl+" requestData:"+requestData+" requestType:"+requestType+" dataType:"+dataType+" ret:"+ret);
    }
	public void checkSyncCallback(String paymentId,JSONObject thirdParam,String requestStr,JSONObject ext,JSONObject request) {
		
		if(!isDevelopment() && isDevelopmentOrder(paymentId))
		{
			syncCallback(requestStr,ext,request);
		}
	}
	private boolean isDevelopment() {
		String env = apollo.getString(PropertiesConfig.OPS, "environment");
		if(StringUtils.equals(env,"dev")){
			return true;
        }
		return false;
	}
	private boolean isDevelopmentOrder(String paymentId) {
		if(!StringUtils.isEmpty(paymentId) && (OperateTools.hasOverseas() || paymentId.substring(0, 1).equals("t")))
			return true;
		return false;
	}
	public void setCallbackMoney(JSONObject thirdParam,PayOrder order) {
		thirdParam.put("actualMoney", order.getExtraInfo().containsKey("actualMoney")?order.getExtraInfo().getFloat("actualMoney"):order.getPaymentMoney());
	}
	/**
	 * 回调验证错误
	 */
	public boolean cbVerifyFail(JSONObject thirdParam,String msg) {
		String paymentId = thirdParam.getString("paymentId");
		Logger dPayNameLog = getPayClassLogger();
		dPayNameLog.info(paymentId+"|verifyFail|"+msg);
		thirdParam.put("thirdRetMsg", "verifyFail:"+msg);
		return false;
	}
	public void payNameLogger(String paymentId,String info) {
		Logger dPayNameLog = getPayClassLogger();
		dPayNameLog.info(paymentId+"|"+info);
	}

	/**
	 * 注册中心:国内库
	 * 三方登录携带了国内的游客,会导致登录失败,把国内的游客同步到海外
	 * @author quine
	 */
	public JSONObject getRegCenterUserInfoByRegCenter(JSONObject r,String thirdUid,String plat) {
		String username = r.getString("username");
		String pwd = r.getString("pwd");

		if(OperateTools.hasOverseas()) {
			JSONObject params = new JSONObject();
			if (Utils.isExist(thirdUid)&&Utils.isExist(plat)){
				params.fluentPut("thirdUid", thirdUid);
				params.fluentPut("plat", plat);
			}else {
				params.fluentPut("username", username);
				params.fluentPut("pwd", pwd);
			}

			String url = "http://is.365you.com/v3/uroute/user/getRegUserInfoTable";
			String res = HttpUtil.doGet(url, params);
			if(StringUtils.isEmpty(res))
				return null;
			JSONObject regUserInfo = JSONObject.parseObject(res);
			if(regUserInfo==null || regUserInfo.size()==0)
				return null;
			RegisterInfo register = JSONObject.parseObject(regUserInfo.getString("register"), RegisterInfo.class);
			User user = JSONObject.parseObject(regUserInfo.getString("user"), User.class);
			JSONObject boundInfo = regUserInfo.getJSONObject("boundInfo");
			if(register!=null && user!=null) {//从国内查到后存到本地,避免后续再查国内
				String accountType = "tourist";
				userDao.addRegister(register);
				userDao.addUser(user);
				if (boundInfo !=null){
					accountType = "thirdAccount";
					userDao.syncThirdAccount(boundInfo.getLongValue("UserID"),boundInfo.getString("LoginName"),boundInfo.getString("PlatID"),
							boundInfo.getString("thirdUserName"),boundInfo.getIntValue("bindTime"),boundInfo.getString("extra"),plat);
				}
				LogFactory.getInstance().account(101016,"get",username,register.getUserId(),register.getRegtime(),r.getString("channel"),r.getIntValue("appId"),r.getString("apkVersion"),r.getString("imei"),accountType);
			}
			return regUserInfo;
		}
		return null;
	}


	/**
	 * 客户端本地账号,先根据账号密码取,没有则根据登录态取
	 * @param r 客户端请求参数
	 * @return userInfo
	 */
	public User getUserInfoByClient(JSONObject r){
		RegisterInfo register = null;
		User user = null;
		String userName = r.getString("username");
		String pwd = r.getString("pwd");
		//三方登录接口 新增guest***参数，防止老游客的key time与当前登录的key time冲突
		String key = r.containsKey("guestKey")?r.getString("guestKey"):r.getString("key");
		int time = r.containsKey("guestTime")?r.getIntValue("guestTime"):r.getIntValue("time");
		long userId = r.containsKey("guestUserId")?r.getIntValue("guestUserId"):r.getLongValue("userId");
		//账号密码
   		if(!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(pwd)) {
   			if(MatcherUtils.verify(userName, MatcherUtils.PHONE)){
   				register =userDao.findRegisterByMobile(userName);
   			}else{
   				register = userDao.findRegisterByLoginName(userName);
   			}
   			if(register==null) {

   				//海外三方登录携带国内游客,国内游客同步海外
				JSONObject regCenterUserInfoByRegCenter = getRegCenterUserInfoByRegCenter(r,"","");
				if (regCenterUserInfoByRegCenter!=null&&regCenterUserInfoByRegCenter.containsKey("user")){
					  return JSONObject.parseObject(regCenterUserInfoByRegCenter.getString("user"), User.class);
				}else {
					return null;
				}
   			}
   			user =userDao.findUserInfoByPwd(register.getUserId(),pwd);
   		}else if(userId>0 && time>0 && !StringUtils.isEmpty(key)){//登录态
			if(StringUtils.equals(key, getLoginKey(userId, time))) {
				user = userDao.findUser(userId);
			}
   		}else if(OperateTools.hasOverseas()) {//海外无法存账户信息,用设备匹配
   			BasePay guest = PayFactory.getInstance().getPay("pay/guest");
   			try {
				Object result = ClassUtil.invoke(guest, "getGuestInfo", r);
				if(result!=null) {
					JSONObject guestInfo = (JSONObject)result;
					if(guestInfo.getLongValue("userId")>0) {
						user = userDao.findUser(guestInfo.getLongValue("userId"));
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   		}
	   	return user;
	}
	public String getLoginKey(long userId,int time) {
		String enKey = "askdfjhakda3sdf4sd5f4sd3f4sdf" ;
		String key = userId + "|" + time + "|" + enKey;
		key =  MD5Util.md5Encode(key);
		return key;
	}
	/**
	 * 查询用户设备信息
	 * @param userId
	 * @return
	 */
	public UserDevInfo findUserDevInfo(long userId) {
		UserDevInfo info = userDao.findUserDevInfo(userId);
		if(info != null) {
			info.setMobile(Xor.decode(info.getMobile()));
			info.setImei(Xor.decode(info.getImei()));
			info.setImsi(Xor.decode(info.getImsi()));
			info.setIccid(Xor.decode(info.getIccid()));
		}
		
		return info;
	}
	public Logger getPayClassLogger() {
//		Annotation ann = this.getClass().getAnnotations()[0];
//		PayCodeDesc tmp = (PayCodeDesc) ann;
//		tmp.uri();
		String date = DateUtils.getYMD();
		String className = this.getClass().getSimpleName();
		String fileName = "pay/"+date+"/"+className;
		return LoggerUtil.getNameLogger(fileName);
	}
}