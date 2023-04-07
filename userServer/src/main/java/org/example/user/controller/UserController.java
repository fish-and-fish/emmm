package org.example.user.controller;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private static final int COUNT_BITS = Integer.SIZE - 3;

    @GetMapping("/home")
    public String create() {
        return "hello";
    }
   // http://localhost:8080/user/home
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(2, 5, 10, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(10));
        for(int i = 0; i< 10; i++){
            int finalI = i;
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(3000);
                    System.out.println(finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
//        threadPoolExecutor.shutdownNow();
        threadPoolExecutor.shutdown();
        System.out.println("shutdown");
        for(int i = 10; i< 20; i++){
            int finalI = i;
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(3000);
                    System.out.println(finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
//        int a = -1 << COUNT_BITS;
//        int b = 0 << COUNT_BITS;
//        int c = 1 << COUNT_BITS;
//        int d = 2 << COUNT_BITS;
//        System.out.println(Integer.toBinaryString(COUNT_BITS));
//        System.out.println(Integer.toBinaryString( (1 << COUNT_BITS) - 1));
//        System.out.println(Integer.toBinaryString(-1));
//        System.out.println(Integer.toBinaryString(2));
//        System.out.println(Integer.toBinaryString(a));
////        System.out.println(Integer.toBinaryString(a | 0));
//        System.out.println(Integer.toBinaryString(b));
//        System.out.println(Integer.toBinaryString(c));
//        System.out.println(Integer.toBinaryString(d));
//
//        AtomicInteger ctl = new AtomicInteger();
//        System.out.println();
    }

}
