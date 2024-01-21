package com.nhnacademy.certificate.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenerateRandom16Digits {
    public static long getNumber() {
        Random random = new Random();
        long randomValue = 0;

        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10);
            if (i == 0 && digit == 0) {
                digit = random.nextInt(9)+1;
            }
            randomValue = randomValue * 10 + digit;
        }
        log.info("1 -> {}",randomValue);

        randomValue += Long.parseLong(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")));
        log.info("2 -> {}",randomValue);
        if (randomValue > 10000000000000000L) {
            randomValue-=10000000000000000L;
        }
        log.info("3 -> {}",randomValue);

        return randomValue;
    }

    public static String getString(long number) {
        log.info("String 1 -> {}",number);
        String confirmString = String.valueOf(number).substring(0, 16);

        log.info("String 2 -> {}",confirmString);
        String first = confirmString.substring(0, 8);
        String second = confirmString.substring(8, 16);

        String resultString = first + "-" + second;
        log.info("String 3 -> {}",resultString);

        return resultString;
    }
}
