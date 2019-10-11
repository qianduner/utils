package com.qianduner.utils.gather.group;



import com.qianduner.utils.core.exception.UException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * 系列化工具类
 */
public class YmSerializeUtil {
	
	/**
	 * 序列化
	 * 
	 * @param obj
	 * @return
	 */
	public static byte[] serialize(Object obj){
		try{
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(byteOut);
			oos.writeObject(obj);
			byte[] bytes = byteOut.toByteArray();
			oos.close();
			byteOut.close();
			return bytes;
		}catch (Exception e) {
			throw new UException("对象序列化失败", e);
		}
	}
	
	/**
	 * 反序列化
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object unSerialize(byte[] bytes){
		if (bytes == null) {
			throw new UException("传入的反序列化对象不能为空");
		}
		ByteArrayInputStream in = null;
		try{
			in = new ByteArrayInputStream(bytes);
			ObjectInputStream objIn = new ObjectInputStream(in);
			return objIn.readObject();
		}catch (Exception e) {
			throw new UException("反序列化失败", e);
		}
	}
}
