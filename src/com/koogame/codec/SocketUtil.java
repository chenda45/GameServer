package com.koogame.codec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject; 

/**
 * @author Dark
 * Socket通讯帮助类
 */
public class SocketUtil {
  

	/**
	 * 解析包装请求数据
	 * @param message
	 * @return
	 */
	public static JSONObject parseRequest(Object message) {

		JSONObject json = null;
		String msg = (String) message;
		//		msg = string2Json(msg);
		json = JSONObject.fromObject(msg);

		return json;
	}

	/**
	 * JSON字符串特殊字符处理，比如：“\A1;1300”
	 * @param s
	 * @return String
	 */
	public static String string2Json(String s) {      
		StringBuffer sb = new StringBuffer();      
		for (int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			switch (c){
			case '\"':
				sb.append("\\\"");
				break;
			case '\\':
				//                 sb.append("\\\\");
				sb.append("\\\\");
				break;
			case '/':
				sb.append("\\/");      
				break;
			case '\b':
				sb.append("\\b");      
				break;
			case '\f':
				sb.append("\\f");      
				break;      
			case '\n':      
				sb.append("\\n");      
				break;      
			case '\r':      
				sb.append("\\r");      
				break;      
			case '\t':      
				sb.append("\\t");      
				break;      
			default:
				sb.append(c);   
			}
		}    
		return sb.toString();   
	}
   
	public static byte[] encode(String msg) throws UnsupportedEncodingException { 
		
		
		System.out.println("out data= "+msg);
		
		// 掩码开始位置 
		int masking_key_startIndex = 2; 

		byte[] msgByte = msg.getBytes("UTF-8"); 

		// 计算掩码开始位置 
		if (msgByte.length <= 125) { 
			masking_key_startIndex = 2; 
		} else if (msgByte.length > 65536) { 
			masking_key_startIndex = 10; 
		} else if (msgByte.length > 125) { 
			masking_key_startIndex = 4; 
		} 

		// 创建返回数据 
		byte[] result = new byte[msgByte.length + masking_key_startIndex]; 

		// 开始计算ws-frame 
		// frame-fin + frame-rsv1 + frame-rsv2 + frame-rsv3 + frame-opcode 
		result[0] = (byte) 0x81; // 129 

		// frame-masked+frame-payload-length 
		// 从第9个字节开始是 1111101=125,掩码是第3-第6个数据 
		// 从第9个字节开始是 1111110>=126,掩码是第5-第8个数据 
		if (msgByte.length <= 125) { 
			result[1] = (byte) (msgByte.length); 
		} else if (msgByte.length > 65536) { 
			result[1] = 0x7F; // 127 
		} else if (msgByte.length > 125) { 
			result[1] = 0x7E; // 126 
			result[2] = (byte) (msgByte.length >> 8 ); 
			result[3] = (byte) (msgByte.length % 256); 
		} 

		// 将数据编码放到最后 
		for (int i = 0; i < msgByte.length; i++) { 
			result[i + masking_key_startIndex] = msgByte[i]; 
		} 
		
		return result; 
	} 


}
