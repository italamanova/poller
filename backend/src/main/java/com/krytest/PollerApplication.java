package com.krytest;

import com.krytest.helper.Poller;
import com.krytest.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class PollerApplication {

    @Autowired
    private PollService pollService;

    @Scheduled(fixedRate = 2)
    public void scheduleTaskWithFixedRate() {
        Poller poller = new Poller(pollService);
        poller.run();
    }

    public static void main(String[] args) {
        SpringApplication.run(PollerApplication.class, args);
    }

}
