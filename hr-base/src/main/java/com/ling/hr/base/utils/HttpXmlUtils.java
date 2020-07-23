package com.ling.hr.base.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.ling.hr.base.model.UnifiedOrder;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * post提交xml格式的参数
 *
 * @author iYjrg_xiebin
 * @date 2016年10月25日下午3:33:38
 */
public class HttpXmlUtils {

	/**
	 * 开始post提交参数到接口 并接受返回
	 *
	 * @param url
	 * @param xml
	 * @param method
	 * @param contentType
	 * @return
	 */
	public static String xmlHttpProxy(String url, String xml, String method, String contentType) {
		InputStream is = null;
		OutputStreamWriter os = null;

		try {
			URL _url = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-type", "text/xml");
			conn.setRequestProperty("Pragma:", "no-cache");
			conn.setRequestProperty("Cache-Control", "no-cache");
			conn.setRequestMethod("POST");
			os = new OutputStreamWriter(conn.getOutputStream());
			os.write(new String(xml.getBytes(contentType)));
			os.flush();

			// 返回值
			is = conn.getInputStream();
			return getContent(is, "utf-8");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 解析返回的值
	 *
	 * @param is
	 * @param charset
	 * @return
	 */
	public static String getContent(InputStream is, String charset) {
		String pageString = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuffer sb = null;
		try {
			isr = new InputStreamReader(is, charset);
			br = new BufferedReader(isr);
			sb = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			pageString = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (isr != null) {
					isr.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			sb = null;
		}
		return pageString;
	}

	/**
	 * post请求并得到返回结果
	 *
	 * @param requestUrl
	 * @param requestMethod
	 * @param output
	 * @return
	 */
	public static String httpsRequest(String requestUrl, String requestMethod, String output) {
		try {
			URL url = new URL(requestUrl);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod(requestMethod);
			if (null != output) {
				OutputStream outputStream = connection.getOutputStream();
				outputStream.write(output.getBytes("UTF-8"));
				outputStream.close();
			}
			// 从输入流读取返回内容
			InputStream inputStream = connection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			connection.disconnect();
			return buffer.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "";
	}

	/**
	 * XML格式字符串转换为Map
	 *
	 * @param strXML
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> xmlToMap(String strXML) throws Exception {
		try {
			Map<String, String> data = new HashMap<String, String>();
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
			org.w3c.dom.Document doc = documentBuilder.parse(stream);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getDocumentElement().getChildNodes();
			for (int idx = 0; idx < nodeList.getLength(); ++idx) {
				Node node = nodeList.item(idx);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					org.w3c.dom.Element element = (org.w3c.dom.Element) node;
					data.put(element.getNodeName(), element.getTextContent());
				}
			}
			try {
				stream.close();
			} catch (Exception ex) {
				// do nothing
			}
			return data;
		} catch (Exception ex) {
			//
			throw ex;
		}
	}

	/**
	 * json转xml
	 */
	public static String jsonToXml(UnifiedOrder unifiedOrder) {
		JSONObject json = JSONObject.fromObject(unifiedOrder);

		Iterator<String> it = json.keys();
		while (it.hasNext()) {
			//获取map的key
			String key = it.next();
			if (StringUtil.isBlank(json.getString(key)) || StringUtil.equals("0",json.getString(key))) {
				json.remove(key);
				it = json.keys();
			}


		}
		XMLSerializer serializer = new XMLSerializer();
		serializer.setRootName("xml");
		serializer.setTypeHintsEnabled(false);
		serializer.setRemoveNamespacePrefixFromElements(true);
		serializer.setSkipNamespaces(true);
		serializer.setTypeHintsCompatibility(true);
		String xml = serializer.write(json);
		return xml;
	}

}