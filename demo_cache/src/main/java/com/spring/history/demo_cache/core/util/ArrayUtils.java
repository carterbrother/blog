package com.spring.history.demo_cache.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

/**
 * @author  relax  
 *
 * @Email   songqinghulove@163.com
 *
 * @version Created on 2013-11-14 上午10:48:02
 * 
 * @Describe 数组工具类 
 */
public class ArrayUtils {
	
	/**
	 * 判断对list集合中是否存在item对象
	 * 
	 * @param list
	 * @param item
	 * @return
	 */
	public static boolean checkItem(List<?> list, Object item){
		return list.indexOf(item) >= 0;
	}
	
	/**
	 * 判断target对象存在 item 字符
	 * 
	 * @param target  数据源
	 * @param item    查找对象
	 * @param split   分隔符
	 * @return true / false
	 */
	public static boolean checkItem(String target , String item , String split){
		if(StringUtils.isEmpty(target) || StringUtils.isEmpty(item)) return false;
		 String [] sources = target.split(split);
		return checkItem(sources, item);
	}
	
	/**
	 * 判断target对象存在 item 字符
	 * 
	 * @param items    查找对象
	 * @return true / false
	 */
	public static boolean checkItem(String item , String items){
		if(StringUtils.isEmpty(items)) return false;
		String [] sources = items.split(",");
		return checkItem(sources, item);
	}
	/**
	 * 判断item是否在items里,逗号分割
	 * @param item
	 * @param items
	 * @return
	 */
	public static boolean checkItem(long item , String items) {
		return checkItem(String.valueOf(item), items);
	}

	public static boolean checkItem(int item , String items) {
		return checkItem(String.valueOf(item), items);
	}
	
	public static boolean checkItem(String [] keys , String key){
		if(keys == null || keys.length == 0) return false;
		for(String k : keys){
			if(k.equals(key))return true;
		}
		return false;
	}
	
	public static boolean checkItem(int [] array , long key){
		if(array == null || array.length == 0) return false;
		for(int k : array){
			if(k == key)return true;
		}
		return false;
	}

	public static boolean checkItem(int [] array , int key){
		if(array == null || array.length == 0) return false;
		for(int k : array){
			if(k == key)return true;
		}
		return false;
	}

	public static boolean checkItem(char [] array , char key){
		if(array == null || array.length == 0) return false;
		for(int k : array){
			if(k == key)return true;
		}
		return false;
	}

	

	public static boolean checkItem(long [] array , long key){
		if(array == null || array.length == 0) return false;
		for(long k : array){
			if(k == key)return true;
		}
		return false;
	}
	
	/** 
	 * 查询某一个对象在数组里的index
	 * 
	 * @param keys
	 * @param key
	 * @return
	 */
	public static int getItem(String [] keys , String key){
		if(keys == null || keys.length == 0) return -1;
		for(String k : keys){
			if(k.equals(key))return 1;
		}
		return -1;
	}
	
	/** 
	 * 查询某一个对象在数组里的index
	 * 
	 * @param keys
	 * @param key
	 * @return
	 */
	public static int getItem(int [] keys , int key){
		if(keys == null || keys.length == 0) return -1;
		for(int i = 0;i<keys.length;i++){
			if(keys[i] == key)return i;
		}
		return -1;
	}

	/**
	 * 解析都好分隔形式的数组
	 * @param arrayString
	 * @return
	 */
	public static float[] parseFloatArray(String arrayString) {
		String [] f = arrayString.split(",");
		float [] result = new float[f.length];
		for(int i=0;i<f.length;i++){
			result[i] = Float.parseFloat(f[i]);
		}
		return result;
	}
	
	
	/**
	 * 解析都好分隔形式的数组
	 * @param arrayString
	 * @return
	 */
	public static long [] parseLongArray(String arrayString) {
		String [] f = arrayString.split(",");
		long [] result = new long[f.length];
		for(int i=0;i<f.length;i++){
			result[i] = Long.parseLong(f[i]);
		}
		return result;
	}
	
	/**
	 * 解析都好分隔形式的数组
	 * @param arrayString
	 * @return
	 */
	public static int[] parseIntArray(String arrayString) {
		return parseIntArray(arrayString, ",");
	}
	
	/**
	 * 解析都好分隔形式的数组
	 * @param arrayString
	 * @return
	 */
	public static String [] parseStringArray(String arrayString) {
		if(StringUtils.isEmpty(arrayString)) return new String[0];
		return arrayString.split(",");
	}
	/**
	 * 解析都好分隔形式的数组
	 * @param arrayString
	 * @param split 分隔符
	 * @return
	 */
	public static String [] parseStringArray(String arrayString,String split) {
		if(StringUtils.isEmpty(arrayString)) return new String[0];
		return arrayString.split(split); //特殊符号
	}
		/**
		 * 将分隔符形式的字符串解析为数组
		 * @param arrayString
		 * @param separator
		 * @return
		 */
	public static int[] parseIntArray(String arrayString, String separator){
		if(StringUtils.isEmpty(arrayString)){
			return new int[0];
		}
		String[] list = arrayString.split(separator);
		int[] array = new int[list.length];
		for (int i = 0; i < array.length; i++) {
			array[i] = Integer.parseInt(list[i].trim());
		}
		return array;
	}

	/**
	 * 将分隔符形式的字符串解析为数组
	 * @param arrayString
	 * @return
	 */
	public static Integer[] parseIntegerArray(String arrayString){
		if(StringUtils.isEmpty(arrayString)){
			return new Integer[0];
		}

		arrayString = arrayString.replaceAll("\\[","").replaceAll("\\]","");
		if(arrayString.length() == 0)
			return new Integer[0];

		String[] list = arrayString.split(",");
		Integer[] array = new Integer[list.length];
		for (int i = 0; i < array.length; i++) {
			String s = list[i].trim();
			if(s.length() == 0){
				array[i] = 0;
			}else {
				array[i] = Integer.parseInt(s);
			}
		}
		return array;
	}
	
	/**
	 * 将Array 转换为1，1格式
	 * 
	 * @param keys
	 * @return
	 */
	public static List<Integer> stringToList(String keys){
		List<Integer> items = new ArrayList<Integer>();
		if(StringUtils.isEmpty(keys)) return items;
		String [] infos = keys.split(",");
		for(String key : infos){
			if(StringUtils.isEmpty(key))continue;
			items.add(Integer.parseInt(key));
		}
		return items;
	}
	public static int[] stringToIntArr(String keys){
		if (StringUtils.isEmpty(keys)){
			return new int[1];
		}
		String [] infos = keys.split(",");
		int[] arr = new int[infos.length];
		for (int i = 0; i <infos.length ; i++) {
			arr[i] = Integer.valueOf(infos[i]);
		}
		return arr;
	}
	
	/**
	 * 将Array 转换为1，1格式
	 * 
	 * @return
	 */
	public static List<Integer> stringToList(String values,String separator){
		List<Integer> items = new ArrayList<Integer>();
		if(StringUtils.isEmpty(values)) return items;
		String [] infos = values.split(separator);
		for(String key : infos){
			if(StringUtils.isEmpty(key))continue;
			items.add(Integer.parseInt(key));
		}
		return items;
	}
	
	/**
	 * 将Array 转换为1，1格式
	 * 
	 * @param keys
	 * @return
	 */
	public static List<Long> stringToLongList(String keys){
		List<Long> items = new ArrayList<Long>();
		
		if(StringUtils.isEmpty(keys)) return items;
		
		
		String [] infos = keys.split(",");
		for(String key : infos){
			if(StringUtils.isEmpty(key))continue;
			items.add(Long.parseLong(key));
		}
		return items;
	}
	
	/**
	 * 将Array 转换为1，1格式
	 * 
	 * @param arrays
	 * @return
	 */
	public static String arrayToString(Object [] arrays,String separator){
		if(arrays == null) return "";
		StringBuffer t=new StringBuffer();
		if(arrays.length == 0) return t.toString();
		for(Object i : arrays){
			t.append(i).append(separator);
		}
		return t.substring(0, t.length() - 1);
	}
	
	/**
	 * 将Array 转换为1，1格式
	 * 
	 * @param arrays
	 * @return
	 */
	public static String listToString(List<Object> arrays,String separator){
		String t="";
		for(Object i : arrays){
			t+=i+separator;
		}
		if(StringUtils.isEmpty(t))return "";
		return t.substring(0, t.length() - 1);
	}

    /**
     * 将Array 转换为1，1格式
     *
     * @param arrays
     * @return
     */
    public static String listToString(Set<String> arrays,String separator){
        String t="";
        for(String i : arrays){
            t+=i+separator;
        }
        if(StringUtils.isEmpty(t))return "";
        return t.substring(0, t.length() - 1);
    }

	/**
	 * 将Array 转换为1，1格式
	 * 
	 * @param arrays
	 * @return
	 */
	public static String listToString(List<Integer> arrays){
		String t="";
		for(int i : arrays){
			t+=i+",";
		}
		if(StringUtils.isEmpty(t))return "";
		return t.substring(0, t.length() - 1);
	}
	
	/**
	 * 将Array 转换为1，1格式
	 * 
	 * @param arrays
	 * @return
	 */
	public static String listToString1(List<Long> arrays){
		String t="";
		for(long i : arrays){
			t+=i+",";
		}
		if(StringUtils.isEmpty(t))return "";
		return t.substring(0, t.length() - 1);
	}
	
	/**
	 * 将Array 转换为1，1格式
	 * 
	 * @param arrays
	 * @return
	 */
	public static String arrayToString(String [] arrays){
		if(arrays == null || arrays.length == 0) return "";
		String t="";
		for(String i : arrays){
			t+=i+",";
		}
		return t.substring(0, t.length() - 1);
	}
	
	/**
	 * 将Array 转换为1，1格式
	 * 
	 * @param arrays
	 * @return
	 */
	public static String arrayToString(int [] arrays){
		if(arrays == null) return "";
		String t="";
		for(int i : arrays){
			t+=i+",";
		}
		return t.substring(0, t.length() - 1);
	}


	public static long hash(long h) {
        h ^= (h >>> 21) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 3);
    }
	
	public static <T> boolean isEmpty(List<T> list){
		return list == null || list.isEmpty();
	}
	
	//求两个字符串数组的并集，利用set的元素唯一性   
    public static String[] union(String[] arr1, String[] arr2) {   
        Set<String> set = new HashSet<String>();   
        for (String str : arr1) {   
            set.add(str);   
        }   
        for (String str : arr2) {   
            set.add(str);   
        }   
        String[] result = {};   
        return set.toArray(result);   
    }   
  
    //求两个数组的交集   
    public static String[] intersect(String[] arr1, String[] arr2) {   
        Map<String, Boolean> map = new HashMap<String, Boolean>();   
        LinkedList<String> list = new LinkedList<String>();   
        for (String str : arr1) {   
            if (!map.containsKey(str)) {   
                map.put(str, Boolean.FALSE);   
            }   
        }   
        for (String str : arr2) {   
            if (map.containsKey(str)) {   
                map.put(str, Boolean.TRUE);   
            }   
        }   
  
        for (Entry<String, Boolean> e : map.entrySet()) {   
            if (e.getValue().equals(Boolean.TRUE)) {   
                list.add(e.getKey());   
            }   
        }   
  
        String[] result = {};   
        return list.toArray(result);   
    }   
  
    //求两个数组的差集   
    public static String[] minus(String[] arr1, String[] arr2) {   
        LinkedList<String> list = new LinkedList<String>();   
        LinkedList<String> history = new LinkedList<String>();   
        String[] longerArr = arr1;   
        String[] shorterArr = arr2;   
        //找出较长的数组来减较短的数组   
        if (arr1.length > arr2.length) {   
            longerArr = arr2;   
            shorterArr = arr1;   
        }   
        for (String str : longerArr) {   
            if (!list.contains(str)) {   
                list.add(str);   
            }   
        }   
        for (String str : shorterArr) {   
            if (list.contains(str)) {   
                history.add(str);   
                list.remove(str);   
            } else {   
                if (!history.contains(str)) {   
                    list.add(str);   
                }   
            }   
        }   
  
        String[] result = {};   
        return list.toArray(result);   
    } 
    
    /**
     * list去除重复元素【不保证原来顺序】
     * @param list
     * @return
     */
    public static <T> List<T> removeDuplicate(List<T> list) {   
    	HashSet<T> h = new HashSet<T>(list);   
    	list.clear();   
    	list.addAll(h);   
    	return list;   
    }   
    
    /**
     * list去除重复元素【保证顺序】
     * @param list
     * @return
     */
    public static <T> List<T> removeDuplicateWithOrder(List<T> list){  
        List<T> listTemp = new ArrayList<T>();  
        for(int i=0;i<list.size();i++){  
            if(!listTemp.contains(list.get(i))){  
                listTemp.add(list.get(i));  
            }  
        }
        return listTemp;  
    }  
	
	
	public static void main(String[] args) throws Exception {
//		String items = "a2,a3,a4,a5,a11,a12";
//		String [] infos = items.split(",");
		
//		System.out.println(binarySearch(infos, 0, infos.length, "a1"));
		
		
		String [] item1 = {"red","blue","green","yellow"};
		String [] item2 = {"red","green","blue"};
		System.out.println(JSONObject.toJSON(minus(item1,item2)));
	}
	
}
