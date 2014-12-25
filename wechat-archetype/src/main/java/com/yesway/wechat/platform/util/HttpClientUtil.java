package com.yesway.wechat.platform.util;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {
	private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

	public static String doSSLPost(String url, Map<String, String> headers, String body, String charset) {
		String response = "";
		try {
			TrustManager[] tm = { truseAllManager };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 创建HttpClientBuilder
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			httpClientBuilder.setSslcontext(sslContext);
			CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

			HttpPost httpPost = new HttpPost(url);
			if (headers != null) {
				for (Map.Entry<String, String> header : headers.entrySet()) {
					httpPost.addHeader(header.getKey(), header.getValue());
				}
			}
			httpPost.setEntity(new StringEntity(body, charset));
			log.debug("url:" + url);
			HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
			log.debug("status:" + httpResponse.getStatusLine());
			log.debug("headers:");
			HeaderIterator iterator = httpResponse.headerIterator();
			while (iterator.hasNext()) {
				log.debug("\t" + iterator.next());
			}

			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				// 响应内容
				response = EntityUtils.toString(httpEntity);
			}
			log.debug("response:" + response);
			closeableHttpClient.close();
		} catch (Exception e) {
			log.error("doSSLPost error:", e);
		}
		return response;
	}

	public static String doSSLGet(String url, Map<String, String> headers, String charset) {
		String response = "";
		try {
			TrustManager[] tm = { truseAllManager };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 创建HttpClientBuilder
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			httpClientBuilder.setSslcontext(sslContext);
			CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

			HttpGet httpGet = new HttpGet(url);
			if (headers != null) {
				for (Map.Entry<String, String> header : headers.entrySet()) {
					httpGet.addHeader(header.getKey(), header.getValue());
				}
			}
			log.debug("url:" + url);
			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
			log.debug("status:" + httpResponse.getStatusLine());
			log.debug("headers:");
			HeaderIterator iterator = httpResponse.headerIterator();
			while (iterator.hasNext()) {
				log.debug("\t" + iterator.next());
			}

			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				// 响应内容
				response = EntityUtils.toString(httpEntity, charset);
			}
			log.debug("response:" + response);
			closeableHttpClient.close();
		} catch (Exception e) {
			log.error("doSSLGet error:", e);
		}
		return response;
	}

	public static boolean doDownloadFile(String url, String toFile) {
		boolean result = false;
		try {
			// 创建HttpClientBuilder
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

			HttpGet httpGet = new HttpGet(url);
			log.debug("url:" + url);
			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
			log.debug("status:" + httpResponse.getStatusLine());
			log.debug("headers:");
			HeaderIterator iterator = httpResponse.headerIterator();
			while (iterator.hasNext()) {
				log.debug("\t" + iterator.next());
			}

			if (HttpStatus.SC_OK == httpResponse.getStatusLine().getStatusCode()) {
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					// 响应内容
					File targetFile = new File(toFile);
					FileOutputStream output = new FileOutputStream(targetFile);
					output.write(EntityUtils.toByteArray(httpEntity));
					output.flush();
					output.close();
				}
			}
			closeableHttpClient.close();
			result = true;
		} catch (Exception e) {
			log.error("doSSLGet error:", e);
		}
		return result;
	}

	public static String doFormPost(String url, Map<String, String> headers, Map<String, String> paras, String charset) {
		String response = "";
		try {
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

			HttpPost httpPost = new HttpPost(url);
			if (headers != null) {
				for (Map.Entry<String, String> header : headers.entrySet()) {
					httpPost.addHeader(header.getKey(), header.getValue());
				}
			}

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if (paras != null) {
				for (Map.Entry<String, String> para : paras.entrySet()) {
					nvps.add(new BasicNameValuePair(para.getKey(), para.getValue()));
				}
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, charset));

			log.debug("url:" + url);
			HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
			log.debug("status:" + httpResponse.getStatusLine());
			log.debug("headers:");
			HeaderIterator iterator = httpResponse.headerIterator();
			while (iterator.hasNext()) {
				log.debug("\t" + iterator.next());
			}

			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				// 响应内容
				response = EntityUtils.toString(httpEntity, charset);
			}
			closeableHttpClient.close();
		} catch (Exception e) {
			log.error("doSSLPost error:", e);
		}
		return response;
	}

	public static String doFileUpload(String url, Map<String, String> headers, String fileName, String charset) {
		String response = "";
		try {
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

			HttpPost httpPost = new HttpPost(url);
			if (headers != null) {
				for (Map.Entry<String, String> header : headers.entrySet()) {
					httpPost.addHeader(header.getKey(), header.getValue());
				}
			}
			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
			multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			multipartEntityBuilder.setCharset(Charset.forName(charset));
			multipartEntityBuilder.addBinaryBody("filename", new File(fileName));
			// 生成 HTTP 实体
			HttpEntity reqEntity = multipartEntityBuilder.build();
			httpPost.setEntity(reqEntity);

			log.debug("url:" + url);
			HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
			log.debug("status:" + httpResponse.getStatusLine());
			log.debug("headers:");
			HeaderIterator iterator = httpResponse.headerIterator();
			while (iterator.hasNext()) {
				log.debug("\t" + iterator.next());
			}

			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				// 响应内容
				response = EntityUtils.toString(httpEntity, charset);
			}
			closeableHttpClient.close();

		} catch (Exception e) {
			log.error("doFileUpload error:", e);
		}
		return response;
	}

	private static TrustManager truseAllManager = new X509TrustManager() {
		public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {
		}

		public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {
		}

		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

	};

	public static void main(String[] args) {

	}
}
