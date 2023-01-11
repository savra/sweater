package com.example.sweater.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MyScheduler {
    private static final Logger log = LoggerFactory.getLogger(MyScheduler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron="*/5 * * * * MON-FRI")
    public void doSomething() {
        log.info("Current Time      = {}", dateFormat.format(new Date()));
    }
}
