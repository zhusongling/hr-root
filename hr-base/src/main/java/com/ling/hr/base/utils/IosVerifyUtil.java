package com.ling.hr.base.utils;

import com.alibaba.fastjson.JSONObject;
import com.ling.hr.base.properties.IosProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

@Component
public class IosVerifyUtil {
	@Autowired
	private IosProperties iosProperties;

	/**
	 * 苹果服务器验证
	 * 
	 * @param receipt
	 *            账单
	 * @url 要验证的地址
	 * @return null 或返回结果 沙盒 https://sandbox.itunes.apple.com/verifyReceipt
	 * 
	 */
	public String buyAppVerify(String receipt, int type) {

		// 环境判断 线上/开发环境用不同的请求链接
		String url = "";
		if (type == 0) {
			url = iosProperties.getSandBox(); // 沙盒测试
		} else {
			url = iosProperties.getProd(); // 线上测试
		}

		HttpsURLConnection connection = null;
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuffer sb = new StringBuffer();
		try {
			URL console = new URL(url);
			// 打开和URL之间的连接
			connection = (HttpsURLConnection) console.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			connection.setDoOutput(true);
			connection.setDoInput(true);

			JSONObject data = new JSONObject();
			data.put("receipt-data", receipt);

			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(connection.getOutputStream());
			// 发送请求参数
			out.print(data);
			// flush输出流的缓冲
			out.flush();

			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				out.close();
			}
			if (connection != null) {
				connection.disconnect();
			}
		}
		return sb.toString();
	}
}
