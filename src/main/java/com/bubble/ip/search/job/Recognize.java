package com.bubble.ip.search.job;

import com.bubble.ip.search.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * 识别IP的地址
 *
 * @author wugang
 * date: 2019-03-13 17:08
 **/
public class Recognize {
    private static final Logger LOGGER = LoggerFactory.getLogger(Recognize.class);

    public static void main(String[] args) {
        List<String> ipList = new ArrayList<>(6);
        ipList.add("111.204.31.58");
        ipList.add("10.0.1.100");
        SearchService searchService = new SearchService();
        ipList.forEach(ip -> {
            Instant begin = Instant.now();
            String location = searchService.search(ip);
            LOGGER.info("{} recognize location: [{}], costs {} ms", ip, location, Duration.between(begin, Instant.now()).toMillis());
        });

    }

}
