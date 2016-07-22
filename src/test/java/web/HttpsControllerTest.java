package web;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import context.SpringRootConfig;
import utils.HttpClientUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringRootConfig.class })
public class HttpsControllerTest {

    @Test
    public void testRequestByHttp() {
        String url = "http://127.0.0.1:8080/httpsTestWithAnnotation/http/request";
        String param = "orderNo=123";
        String result = HttpClientUtils.sendRequest("POST", url, "http", param,
                "utf-8");
        System.out.println(result);
    }

    @Test
    public void testRequestByHttpsWithoutCer() {
        String url = "https://127.0.0.1:8443/httpsTestWithAnnotation/https/request";
        String param = "12345";
        Map<String, String> data = new HashMap<String, String>();
        data.put("orderNo", param);
        String result = HttpClientUtils.sendRequestWithoutCer("POST", url,
                "https", data, "utf-8");
        System.out.println(result);
    }

    @Test
    public void testRequestByHttpsWithCer() {
        String url = "https://127.0.0.1:8443/httpsTestWithAnnotation/https/request";
        String param = "12345";
        Map<String, String> data = new HashMap<String, String>();
        data.put("orderNo", param);
        String result = HttpClientUtils.sendRequestWithCer("POST", url, "https",
                data, "utf-8");
        System.out.println(result);
    }

}
