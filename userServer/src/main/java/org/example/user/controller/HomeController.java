package org.example.user.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.example.user.utils.Http2Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private static final int COUNT_BITS = Integer.SIZE - 3;

    @GetMapping("/home")
    public String home(HttpServletRequest httpServletRequest) throws IOException {
        // \u000d System.out.println("coder Hydra");

        return "home8086";
    }

    @GetMapping("")
    public String main(HttpServletRequest httpServletRequest) throws IOException {
        // \u000d System.out.println("coder Hydra");

        System.out.println(Http2Utils.getRequestHeader(httpServletRequest));
        System.out.println(Http2Utils.getRequestBody(httpServletRequest));
        return "hello8086";
    }

    @PostMapping("/test/nginx")
    public String testNginx(HttpServletRequest httpServletRequest) throws IOException {
        // \u000d System.out.println("coder Hydra");

        System.out.println(Http2Utils.getRequestHeader(httpServletRequest));
        System.out.println(Http2Utils.getRequestBody(httpServletRequest));
        return Http2Utils.getRequestHeader(httpServletRequest).toString();
    }


}
