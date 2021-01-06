package com.krytest.helper;

import com.krytest.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class Poller implements Runnable {

    @Autowired
    PollService pollService;

    public Poller(PollService pollService) {
        this.pollService = pollService;
    }

    public void getData() throws IOException {
        pollService.updateData();
    }

    @Override
    public void run() {
        try {
            getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
