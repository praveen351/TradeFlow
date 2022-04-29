/*
    Application Starting or Initiating point which will initiate the
    IOC/Spring container and start pushing the beans/Instances and into it
 */

package com.example.schedulingtasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchedulingTasksApplication {
    public static void main(String[] args) {
        SpringApplication.run(SchedulingTasksApplication.class);
    }
}