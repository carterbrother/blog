package com.spring.history.demo_cache.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class MathUtil {
	public static final Random rand = new Random();

	public static final char[] chars={'1','2','3','4','5','6','7','8','9','0','Q','W','E','R','T','Y','U','I','O',
			'P','A','S','D','F','G','H','J','K','L','Z','X','C','V','B','N','M'};

	public static long rand(long min, long max) {
		if (max <= min){
			return min;
		}
		long l = rand.nextLong();
		if (l < 0) {
			l = -l;
		}
		return l % (max - min) + min;
	}

	public static byte[] randbytes(byte[] bytes){
		rand.nextBytes(bytes);
		return bytes;
	}

	/* 从数组中随机得到一个值    weight 权重加起来必须等于 10000
	 * 返回  index  下标
	 * range   可以随机得到的值
	 * weight  每个值对应的权重  */
	public static int randFromArrayBaseW(int[] weight) {
		int randNum = rand.nextInt(10000);
		if (weight.length == 1) {
			if (randNum < weight[0]) {
				return 0;
			}
			else {
				return -1;
			}
		}

		int resIndex = -1;
		int begInterval = 0;
		int endInterval = weight[0];
		for (int i = 0; i < weight.length; ++i) {
			if (randNum >= begInterval && randNum < endInterval) {
				resIndex = i;
				break;
			}
			if (i+1 < weight.length) {
				begInterval += weight[i];
				endInterval = begInterval + weight[i+1];
			}
		}

		return resIndex;
	}

	/* 从权重中  随机得到一个值   下标 index，权重加起来不需要等于10000
	 * 返回   随机得到的index
	 * */
	public static int randFromArray(int[] weight) {
		if (weight.length == 1) {
			return 0;
		}

		int index = -1;
		int sum = 0;
		int[] newWeight = new int[weight.length];
		for (int i = 0; i < weight.length; ++i) {
			sum += weight[i];
			newWeight[i] = sum;
		}

		int randNum = rand.nextInt(sum);
		for (int i = 0; i < newWeight.length; ++i) {
			if (randNum <= newWeight[i]) {
				index = i;
				break;
			}
		}

		return index;
	}

	public static int randFromArray(List<Integer> weight) {
		if (weight == null || weight.size() <= 0) {
			return -1;
		}
		int[] weightArr = new int[weight.size()];
		for (int i = 0; i < weight.size(); ++i) {
			weightArr[i] = weight.get(i);
		}

		return randFromArray(weightArr);
	}

	public static int getIncrementMaxLimit(int base, int add, int limitMax) {
		if (base+add > limitMax) {
			add = limitMax - base;
		}

		return add;
	}

	/**
	 * 从 n [1, n] 中 随机出 m 个不重复的数字,且不等于exc
	 * @param n
	 * @param m
	 * @param exc
	 * @return
	 */
	public static int[] randArrayExc(int n, int m, int exc){
		if (n < 1 || n < m ) {
			return null;
		}
		int[] resArray = new int[m];
		ArrayList<Integer> arrList = new ArrayList<Integer>();
		for (int i = 1; i <= n; ++i) {
			if(i == exc)
				continue;
			arrList.add(i);
		}

		for (int i = 0; i < m; ++i) {
			int rNum = rand.nextInt(arrList.size());  // nextInt(n)  [0, n)
			resArray[i] = arrList.get(rNum);
			arrList.remove(rNum);
		}

		Arrays.sort(resArray);

		return resArray;
	}

	/**
	 * 从 n [1, n] 中 随机出 m 个不重复的数字
	 * @param n
	 * @param m
	 * @return
	 */
	public static int[] randArray(int n, int m) {
		if (n < 1 || n < m ) {
			return null;
		}
		int[] resArray = new int[m];
		ArrayList<Integer> arrList = new ArrayList<Integer>();
		for (int i = 1; i <= n; ++i) {
			arrList.add(i);
		}

		for (int i = 0; i < m; ++i) {
			int rNum = rand.nextInt(arrList.size());  // nextInt(n)  [0, n)
			resArray[i] = arrList.get(rNum);
			arrList.remove(rNum);
		}

		Arrays.sort(resArray);

		return resArray;
	}

	/**
	 * 从 n [a, b] 中 随机出 n 个不重复的数字
	 * @param n
	 * @param m
	 * @return
	 */
	public static int[] randArray(int a, int b, int n) {
		if (a < 1 || b < 1 || b < a || n < 1 || b - a + 1 < n) {
			return null;
		}
		int[] resArray = new int[n];
		ArrayList<Integer> arrList = new ArrayList<Integer>();
		for (int i = a; i <= b; ++i) {
			arrList.add(i);
		}

		for (int i = 0; i < n; ++i) {
			int rNum = rand.nextInt(arrList.size());  // nextInt(n)  [0, n)
			resArray[i] = arrList.get(rNum);
			arrList.remove(rNum);
		}

		Arrays.sort(resArray);

		return resArray;
	}

	private static void dichotomiaRand(int min, int max, int maxCount, List<Integer> rs)
	{
		if (min > max || rs.size() >= maxCount)
			return;

		int randNum = MathUtil.rand(min, max);
		rs.add(randNum);
		dichotomiaRand(min, randNum - 1, maxCount, rs);
		dichotomiaRand(randNum + 1, max, maxCount, rs);
	}

	public static List<Integer> randByCount(int totalCount, int count){
		List<Integer> rs = new ArrayList<Integer>();
		dichotomiaRand(1, totalCount, count, rs);

		return rs;
	}

	public static Integer randFilter(int begin, int end, List<Integer> fs)
	{
		int n = 0;
		for (Integer f : fs)
		{
			if (f != null && f >= begin && f <=end)
				n++;
		}
		end -= n;
		if (end < begin)
		{
			return null;
		}

		int ret = MathUtil.rand(begin, end);
		for (Integer f : fs)
		{
			if (f != null && ret >= f && f >= begin)
				ret++;
		}

		return ret;
	}
	/**
	 * 从 每个区间（rs[0]~rs[1], rs[2]~rs[3]...） 中 随机一个数字,每个区间随机的数字不重复且不等于exc
	 * 区间必须从左到右 从小到大
	 * @param n
	 * @param m
	 * @param exc
	 * @return
	 */
	public static List<Integer> randArrayIntervalExc(int[] rs, int a)
	{
		List<Integer> fs = new ArrayList<Integer>();
		List<Integer> rets = new ArrayList<Integer>();
		fs.add(a);
		if (rs.length <= 0 && (rs.length % 2) != 0)
			return null;
		for (int i = 0; i < rs.length; i += 2)
		{
			Integer ret = randFilter(rs[i], rs[i+1], fs);
			if (ret != null)
			{
				fs.add(ret);
				Collections.sort(fs);
			}
			rets.add(ret);
		}

		return rets;
	}

	/**
	 * 生成min 到 max间随机数<br/>
	 * 包含min 不包含max
	 *
	 * @param min
	 * @param max
	 * @return
	 */
	public static int rand(int min, int max) {
		if (max == min){
			return min;
		}

		return rand.nextInt(max - min) + min;
	}

	/**
	 * 获得一个0-10000的随机数
	 *
	 * @return
	 */
	public static int getRand(){
		return rand(0, 10000);
	}


	/**
	 * 生成min 到 max间随机数<br/>
	 * 包含min 不包含max
	 *
	 * @param min
	 * @param max
	 * @return
	 */
	public static float rand(float min, float max) {
		if (max == min){
			return min;
		}
		float tmp = rand.nextFloat();
		float result = (max - min)* tmp + min;
		DecimalFormat format = new DecimalFormat("#0.0");
		return Float.parseFloat(format.format(result));
	}

	/**
	 * 在maxIndex里生成count个不重复的随机数
	 *
	 * @param count     个数
	 * @param maxIndex  最大索引
	 * @return
	 */
	public static int [] randList(int count , int maxIndex){
		int [] indexs = new int[count];
		int num = 0;
		if(count >= maxIndex){
			int max = 0;
			if(count == maxIndex)max = count;
			else max = maxIndex;
			for(int i = 0;i<max;i++){
				indexs[i] = i;
			}
			return indexs;
		}
		while(num < count){
			int i = rand(0, maxIndex);
			if(!ArrayUtils.checkItem(indexs, i)){
				indexs[num] = i;
				num++;
			}
		}
		return indexs;
	}

	public static int getIndex(int[] ranks){
		int sum = 0;
		for (int i = 0; i < ranks.length; i++) {
			sum += ranks[i];
		}
		return getIndex(ranks, sum);
	}

	/**
	 *
	 * 根据权重列表随机查找一个权重索引值
	 *
	 * @param ranks  权重概率列表
	 * @param sum    权重总和
	 * @return
	 */
	public static int getIndex(int [] ranks , int sum){
		int index = 0;
		int value = rand.nextInt(sum) + 1;
		int[] convers = conver(ranks);
		for (int m = 0; m < ranks.length; m++) {
			if (m == 0) {
				if (1 <= value && value < convers[0]) {
					index = m;
					break;
				}
			}
			if (m != 0) {
				if (convers[m - 1] < value && value <= convers[m]) {
					index = m;
					break;
				}
			}
		}
		return index;
	}

	private static int[] conver(int[] arrs) {
		int[] info = new int[arrs.length];
		for (int i = 0; i < arrs.length; i++) {
			int m = arrs[i];
			if (i == 0) {
				info[i] = m;
			} else {
				info[i] = add(arrs, i);
			}
		}
		return info;
	}

	private static int add(int[] info, int count) {
		int h = 0;
		for (int i = 0; i <= count; i++) {
			h += info[i];
		}
		return h;
	}

	/**
	 * 生成一个0-10000的随机数,并判断输入值是否在该随机数范围内
	 * @param rate
	 * @return
	 */
	public static boolean hit(int rate){
		return getRand() < rate;
	}

	public static boolean hit(int rand, int rate){
		return rand < rate;
	}

	public static String randIntString(int len){
		byte [] items = new byte[len];
		randbytes(items);
		StringBuffer sbs = new StringBuffer();
		for(byte b : items){
			sbs.append(b);
		}
		return sbs.toString();

	}

	public static String getRandomString(int len){
		StringBuffer sb=new StringBuffer();
		for(int i = 1; i  <= len; i++){
			sb.append(chars[rand.nextInt(chars.length)]);
		}
		return sb.toString().toLowerCase();
	}
	/**
	 * 元转分
	 * 9.9f转990
	 * float*100会有精度问题
	 */
	public static int yuanToPoint(float money) {
		int point = (int) (money *10000)/100;
		return point;
	}
	
	/**
	     * 分转元，转换为bigDecimal在toString
	* @return
	*/
	public static String changeF2Y(int price) {
		return BigDecimal.valueOf(Long.valueOf(price)).divide(new BigDecimal(100)).toString();
	}
	
	/**
	 * 元转分
	 * @param price
	 * @return
	 */
	public static String changeY2F(double price) {
        DecimalFormat df = new DecimalFormat("#.00");
        price = Double.valueOf(df.format(price));
        int money = (int)(price * 100);
        String strMoney = String.valueOf(money);
        return strMoney;
    }
	
	/**
	 * 减法,避免精度丢失
	 * @param minuend 被减数
	 * @param subtrahend 减数
	 * @return
	 */
	public static float subtract(float minuend,float subtrahend) {
		BigDecimal m = new BigDecimal(Float.toString(minuend));
		BigDecimal s = new BigDecimal(Float.toString(subtrahend));
		return m.subtract(s).floatValue();
	}
	/**
	 * 除法
	 */
	public static double divide(int dividend,int divisor) {
		if(divisor==0)
			return 0;
		return new BigDecimal((float)dividend/divisor).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}


	public static double divide(Long dividend,Long divisor) {
		if(divisor==0)
			return 0;
		DecimalFormat df = new DecimalFormat("0.00");
		String num = df.format((float)dividend/divisor);
		return Double.valueOf(num);
	}

	/**
	 * 数值转百分比,例:0.5转50%
	 */
	public static String num2Per(double num) {
		return (int) (num *10000)/100+"%";
	}
}
