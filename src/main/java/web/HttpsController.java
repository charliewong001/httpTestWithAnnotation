package web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/https")
public class HttpsController {

    @RequestMapping(value = "/request")
    public @ResponseBody String requestByHttps() {
        System.out.println("https request proccessing...");
        return "https response";
    }
}
