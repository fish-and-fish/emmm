package org.example.user.controller;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private static final int COUNT_BITS = Integer.SIZE - 3;

    @GetMapping("")
    public String main() {
        return "hello";
    }

}
