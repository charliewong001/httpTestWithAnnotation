package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: HTTP工具类
 */
public class HttpClientUtils {

    private static Logger logger = LoggerFactory
            .getLogger(HttpClientUtils.class);

    /**
     * @Description 一句话描述方法用法
     * @param url
     * @param protocol
     * @param entity
     * @param charset
     * @return
     * @see 需要参考的类或方法
     */
    public static String sendRequest(String method, String url, String protocol,
            String requestData, String charset) {
        HttpClientBuilder httpClientBuilder = null;
        CloseableHttpClient closeableHttpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClientBuilder = HttpClientBuilder.create();
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(15 * 1000).setSocketTimeout(30 * 1000)
                    .build();
            httpClientBuilder.setDefaultRequestConfig(config);

            closeableHttpClient = httpClientBuilder.build();

            httpPost = new HttpPost(url);
            EntityBuilder entity = EntityBuilder.create();
            entity.setText(requestData);
            entity.setContentType(ContentType.APPLICATION_JSON);
            entity.setContentEncoding(charset);
            httpPost.setEntity(entity.build());

            HttpResponse response = closeableHttpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                } else {
                    logger.info("HttpEntity resEntity响应信息为空");
                }
            } else {
                logger.info("HttpResponse response 响应信息为空");
            }
        } catch (IOException ioe) {
            logger.error("Method sendRequest execute exception...");
            ioe.printStackTrace();
        } finally {
            try {
                // 关闭流并释放资源
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(
                        "Method sendRequest release resource exception...");
            }
        }
        return result;
    }

    /**
     * @Description 绕过证书发送https请求
     * @param url
     * @param protocol
     * @param entity
     * @param charset
     * @return
     * @see 需要参考的类或方法
     */
    public static String sendRequestTrustall(String method, String url,
            String protocol, Map<String, String> requestData, String charset) {
        CloseableHttpClient closeableHttpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {

            // closeableHttpClient = new SSLClientWithoutCerFactory();
            closeableHttpClient = SSLClientWithoutCerFactory.getTrustallClient();
            httpPost = new HttpPost(url);
            List<NameValuePair> data = MapToNameValuePair(requestData);
            if (data.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(data,
                        charset);
                httpPost.setEntity(entity);
            }

            HttpResponse response = closeableHttpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                } else {
                    logger.info("HttpEntity resEntity响应信息为空");
                }
            } else {
                logger.info("HttpResponse response 响应信息为空");
            }
        } catch (IOException ioe) {
            logger.error("Method sendRequest execute exception...");
            ioe.printStackTrace();
        } catch (Exception e) {
            logger.error("Method sendRequest execute exception...");
            e.printStackTrace();
        }

        finally {
            try {
                // 关闭流并释放资源
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(
                        "Method sendRequest release resource exception...");
            }
        }
        return result;
    }
    /**
     * @Description 绕过证书发送https请求
     * @param url
     * @param protocol
     * @param entity
     * @param charset
     * @return
     * @see 需要参考的类或方法
     */
    public static String sendRequestTrustself(String method, String url,
            String protocol, Map<String, String> requestData, String charset) {
        CloseableHttpClient closeableHttpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {

            closeableHttpClient = new SSLClientWithoutCerTrustself();
            httpPost = new HttpPost(url);
            List<NameValuePair> data = MapToNameValuePair(requestData);
            if (data.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(data,
                        charset);
                httpPost.setEntity(entity);
            }

            HttpResponse response = closeableHttpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                } else {
                    logger.info("HttpEntity resEntity响应信息为空");
                }
            } else {
                logger.info("HttpResponse response 响应信息为空");
            }
        } catch (IOException ioe) {
            logger.error("Method sendRequest execute exception...");
            ioe.printStackTrace();
        } catch (Exception e) {
            logger.error("Method sendRequest execute exception...");
            e.printStackTrace();
        }

        finally {
            try {
                // 关闭流并释放资源
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(
                        "Method sendRequest release resource exception...");
            }
        }
        return result;
    }
    /**
     * @Description 加载证书发送https请求
     * @param url
     * @param protocol
     * @param entity
     * @param charset
     * @return
     * @see 需要参考的类或方法
     */
    public static String sendRequestWithCer(String method, String url,
            String protocol, Map<String, String> requestData, String charset) {
        CloseableHttpClient closeableHttpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {

            closeableHttpClient = SSLClientWithCerFactory.getClient();

            httpPost = new HttpPost(url);
            List<NameValuePair> data = MapToNameValuePair(requestData);
            if (data.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(data,
                        charset);
                httpPost.setEntity(entity);
            }

            HttpResponse response = closeableHttpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                } else {
                    logger.info("HttpEntity resEntity响应信息为空");
                }
            } else {
                logger.info("HttpResponse response 响应信息为空");
            }
        } catch (IOException ioe) {
            logger.error("Method sendRequest execute exception...");
            ioe.printStackTrace();
        } catch (Exception e) {
            logger.error("Method sendRequest execute exception...");
            e.printStackTrace();
        }

        finally {
            try {
                // 关闭流并释放资源
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(
                        "Method sendRequest release resource exception...");
            }
        }
        return result;
    }

    private static List<NameValuePair> MapToNameValuePair(
            Map<String, String> param) {
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        Iterator<String> keys = param.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            String value = param.get(key);
            list.add(new BasicNameValuePair(key, value));
        }
        return list;
    }
}
