/*
    This class contain the business logic which are related to scheduling
    and Consumed by JVM to call a thread at a specific time to execute the statements
*/

package com.example.schedulingtasks.services;

import com.example.schedulingtasks.jpa.TradeFlowJpa;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.text.SimpleDateFormat;


@Service
public class TradeFlowSchedulerService {
    private static final Logger log = LoggerFactory.getLogger(TradeFlowSchedulerService.class);
    private TradeFlowJpa tradeJpa;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public TradeFlowSchedulerService() {
    }

    @Autowired
    public TradeFlowSchedulerService(TradeFlowJpa tradeJpa) {
        this.tradeJpa = tradeJpa;
    }

    /* Is used to update the expire flag if in a store the trade crosses the maturity date only */

    @Async
    @Scheduled(cron = "${interval-in-cron}")
    @SchedulerLock(name = "updateExpiryFlag", lockAtLeastFor = "1s", lockAtMostFor = "30s")
    public void updateExpiryFlag() {
        Date todayDate = new Date(System.currentTimeMillis());
        int updateStatus = tradeJpa.updateExpiryFlag(todayDate);
        if(updateStatus != 0){
            log.info("Updated Successfully!!!!!!!");
        }
    }

    /* Is used to update the expire flag if in a store the trade crosses the maturity date only for dummy purpose only*/

    @Async
    @Scheduled(cron = "${interval-in-cron}")
    @SchedulerLock(name = "updateExpiryFlag", lockAtLeastFor = "1s", lockAtMostFor = "30s")
    public void updateExpiryFlagSummy() {
        Date todayDate = Date.valueOf("2022-07-21");
        int updateStatus = tradeJpa.updateExpiryFlag(todayDate);
        if(updateStatus != 0){
            log.info("Updated Successfully!!!!!!!");
        }
    }
}