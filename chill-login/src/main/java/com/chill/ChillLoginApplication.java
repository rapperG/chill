package com.chill;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SpringBootApplication
public class ChillLoginApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ChillLoginApplication.class, args);

        Long i= -100L;
        Long y = -199L;
        String y1 = "-1.2923";
        String y2 = "-234.102";

        System.out.println(i*y);

        long l = new BigDecimal(y1).multiply(new BigDecimal(y2)).multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP).longValue();

        System.out.println(l);

    }

}
