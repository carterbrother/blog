package com.spring.history.demo_cache.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.history.demo_cache.core.util.ArrayUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author relax
 */
public abstract class XMLKit{

	public abstract void parse(Document doc);
	
	protected int parseInt(Element element, String attrName) {
		if (!element.hasAttribute(attrName) 
				|| element.getAttribute(attrName).length() == 0) {
			return 0;
		}
		
		return Integer.parseInt(element.getAttribute(attrName));
	}
	
	protected short parseShort(Element element, String attrName) {
		if (!element.hasAttribute(attrName) 
				|| element.getAttribute(attrName).length() == 0) {
			return 0;
		}
		
		return Short.parseShort(element.getAttribute(attrName));
	}

	protected float parseFloat(Element element, String attrName) {
		if (!element.hasAttribute(attrName)
				|| element.getAttribute(attrName).length() == 0) {
			return 0;
		}
		
		return Float.parseFloat(element.getAttribute(attrName));
	}

	protected boolean parseBool(Element element, String attrName) {
		if (!element.hasAttribute(attrName)
				|| element.getAttribute(attrName).length() == 0) {
			return false;
		}
		
		return Boolean.parseBoolean(element.getAttribute(attrName));
	}
	
	protected int parseSingleInt(Document doc, String node, String attr) {
		Element element = (Element)doc.getElementsByTagName(node).item(0);
		return parseInt(element, attr);
	}
	
	protected int [] parseSingleIntArray(Document doc, String node, String attr,String delim) {
		Element element = (Element)doc.getElementsByTagName(node).item(0);
		String items = element.getAttribute(attr);
		return ArrayUtils.parseIntArray(items, delim);
	}
	
	protected long [] parseSingleLongArray(Document doc, String node, String attr) {
		Element element = (Element)doc.getElementsByTagName(node).item(0);
		String items = element.getAttribute(attr);
		return ArrayUtils.parseLongArray(items);
	}
	
	protected String parseSingleString(Document doc, String node, String attr) {
		Element element = (Element)doc.getElementsByTagName(node).item(0);
		return element.getAttribute(attr);
	}
	
	/**
	 * 解析xml属性, 返回int数组
	 * 
	 * @param element xml节点
	 * @param attrName 属性名
	 * @param delim 分隔符
	 * @return
	 */
	protected float[] parseFloatArray(Element element, String attrName, String delim) {
		if (!element.hasAttribute(attrName)
				|| element.getAttribute(attrName).equals("")) {
			return null;
		}

		String attribute = element.getAttribute(attrName);
		return parseFloatArray(attribute, delim);
	}

	protected float[] parseFloatArray(String value, String delim) {
		String[] strArray = value.split(delim);

		float[] result = new float[strArray.length];
		for (int i = 0; i < strArray.length; i++) {
			result[i] = Float.parseFloat(strArray[i]);
		}

		return result;
	}


	/**
	 * 解析xml属性, 返回int数组
	 * 
	 * @param element xml节点
	 * @param attrName 属性名
	 * @param delim 分隔符
	 * @return
	 */
	protected int[] parseIntArray(Element element, String attrName, String delim) {
		if (!element.hasAttribute(attrName)
				|| element.getAttribute(attrName).length() == 0) {
			return null;
		}

		String attrVal = element.getAttribute(attrName);
		return parseIntArray(attrVal, delim);
	}

	protected int[] parseIntArray(String arrayString, String delim) {
		String[] strArray = arrayString.split(delim);
		int[] result = new int[strArray.length];
		for (int i = 0; i < strArray.length; i++) {
			result[i] = Integer.parseInt(strArray[i]);
		}

		return result;
	}
	protected String[] parseStringArray(String arrayString, String delim) {
		String[] strArray = arrayString.split(delim);
		return strArray;
	}


	/**
	 * 解析xml属性, 返回List<Integer>
	 * 
	 * @param element xml节点
	 * @param attrName 属性名
	 * @param delim 分隔符
	 * @return
	 */
	protected List<Integer> parseIntList(Element element, String attrName, String delim) {
		if (!element.hasAttribute(attrName)
				|| element.getAttribute(attrName).length() == 0) {
			return null;
		}

		String attrVal = element.getAttribute(attrName);
		String[] strArray = attrVal.split(delim);
		List<Integer> result = new ArrayList<Integer>(strArray.length);
		for (String aStrArray : strArray) {
			result.add(Integer.parseInt(aStrArray));
		}

		return result;
	}

	/**
	 * 将XML的一个Element转换成对象的类<br/>
	 * XML 的属性名称一定要跟类属性名称相同
	 * @param element xml一行数据对象
	 */
	protected <T> T elementToClass(Class<T> clz, Element element){
		T instance = null;
		try {
			instance = clz.newInstance();
			Map<String,Object>  fileds = toMap(element);
			for(Method method : instance.getClass().getMethods()){
				String methodName = method.getName().toLowerCase(); 
				if(!fileds.containsKey(methodName))continue;
				method.invoke(instance, getParamType(fileds.get(methodName),method.getParameterTypes()[0].getName()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}

	protected Object getParamType(Object value , String paramType){
		if(int.class.getName().equals(paramType)){
			return Integer.parseInt(value.toString());
		}
		else if(short.class.getName().equalsIgnoreCase(paramType)){
			return Short.parseShort(value.toString());
		}
		else if(String.class.getName().equalsIgnoreCase(paramType)){
			return value.toString();
		}
		else if(boolean.class.getName().equalsIgnoreCase(paramType)){
			return Boolean.valueOf(value.toString());
		}
		else if(float.class.getName().equalsIgnoreCase(paramType)){
			return Float.valueOf(value.toString());
		}
		else if(long.class.getName().equalsIgnoreCase(paramType)){
			return Long.valueOf(value.toString());
		}
		else if(int[].class.getName().equals(paramType)){
			return parseIntArray(value.toString(),",| ");
		}
		else if(float[].class.getName().equals(paramType)){
			return parseFloatArray(value.toString(), ",| ");
		}
		else if(String[].class.getName().equals(paramType)) {
			return parseStringArray(value.toString(), ",| ");
		}
		return null;
	}
	
	protected Map<String,Object> toMap(Element element){
		NamedNodeMap nodes = element.getAttributes();
		Map<String,Object> maps = new HashMap<String,Object>();
		for(int i = 0 ; i<nodes.getLength() ; i++){
			Node node = nodes.item(i);
			maps.put("set"+node.getNodeName().toLowerCase(), node.getNodeValue());
		}
		return maps;
	}
	
	protected <T> List<T> parseXML(Class<T> clz,Document doc,String node){
		
		List<T> items = new ArrayList<>();
		NodeList list = doc.getElementsByTagName(node);
		for(int i=0;i<list.getLength();i++){
			Element element = (Element)list.item(i);
			items.add(elementToClass(clz, element));
		}
		return items;
	}
	
	protected <T> List<T> parseXML(Class<T> clz,Element ele,String node){
		
		List<T> items = new ArrayList<>();
		NodeList list = ele.getElementsByTagName(node);
		for(int i=0;i<list.getLength();i++){
			Element element = (Element)list.item(i);
			items.add(elementToClass(clz, element));
		}
		return items;
	}
	
	/**
	 * 将节点转换成HashMap
	 * 
	 * @param clz       转换类
	 * @param doc       文档
	 * @param node      节点
	 * @param keyType   Key类型
	 * @param keyMethod key方法
	 * @return getInsId
	 */
	@SuppressWarnings("unchecked")
	protected <V,T> Map<V, T> parseXML(Class<T> clz,Document doc,String node,V keyType,String keyMethod){
		HashMap<V, T> infos = new HashMap<V, T>();
		NodeList list = doc.getElementsByTagName(node);
		for(int i=0;i<list.getLength();i++){
			Element element = (Element)list.item(i);
			T t = elementToClass(clz, element);
			try {
				Method m = clz.getMethod("get"+keyMethod);
				V value = (V) m.invoke(t, new Object[0]);
				infos.put(value, t);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return infos;
	}
	
	/**
	 * 将节点转换成HashMap
	 * 
	 * @param doc       文档
	 * @param node      节点
	 * @return getInsId
	 */
	@SuppressWarnings("unchecked")
	protected <K,V> Map<K,V> parseXML(Document doc,String node){
		HashMap<K,V> infos = new HashMap<K,V>();
		NodeList list = doc.getElementsByTagName(node);
		for(int i=0;i<list.getLength();i++){
			Element element = (Element)list.item(i);
			K key = (K) (element.getAttribute("id"));
			V v = (V) element.getAttribute("value");
			try {
				infos.put(key, v);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return infos;
	}
	
	/**
	 * 获取某个节点
	 * 
	 * @param doc  文档对象
	 * @param node 节点名称
	 * @return
	 */
	protected Element getNode(Document doc,String node){
		NodeList list = doc.getElementsByTagName(node);
		return (Element)list.item(0);
	}
	

}
