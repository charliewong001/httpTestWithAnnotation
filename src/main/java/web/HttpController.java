package web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/http")
public class HttpController {

    @RequestMapping(value = "/request")
    public @ResponseBody String requestByHttp(HttpServletRequest request) {
        System.out.println("is secure = " + request.isSecure());
        System.out.println("http request proccessing...");
        if (request.isSecure()) {
            return "https response";
        } else {
            return "http response";
        }
    }
}
