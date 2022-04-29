/*
      This class is a Controller and contains all the end points to do various CRUD operations
      and consumed by only the end user
*/

package com.example.schedulingtasks.controller;

import com.example.schedulingtasks.model.TradeFlowModel;
import com.example.schedulingtasks.services.TradeFlowSchedulerService;
import com.example.schedulingtasks.services.TradeFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trade-flow")
public class TradeFlowController {

    private TradeFlowSchedulerService tradeSchedulerService;
    private TradeFlowService tradeService;

    public TradeFlowController() {
    }

    @Autowired
    public TradeFlowController(TradeFlowSchedulerService tradeSchedulerService, TradeFlowService tradeService) {
        this.tradeSchedulerService = tradeSchedulerService;
        this.tradeService = tradeService;
    }

    /* Is used to post the respective data using service */
    @PostMapping("/saveTradeFlow")
    public ResponseEntity<Map<String, Object>> saveTradeFlow(@RequestBody TradeFlowModel tradeModel) {
        Map<String, Object> maptradeModels = tradeService.saveTradeObject(tradeModel);
        return new ResponseEntity<Map<String, Object>>(maptradeModels, new HttpHeaders(), HttpStatus.OK);
    }

    /* Is used to list all the TradeFlowModel data from Service */
    @GetMapping("/listTradeFlow")
    public ResponseEntity<List<TradeFlowModel>> getlistTradeFlow() {
        List<TradeFlowModel> trade_list = tradeService.getlistTradeObject();
        return new ResponseEntity<List<TradeFlowModel>>(trade_list, new HttpHeaders(), HttpStatus.OK);
    }

    /* Is used to update the expire flag if in a store the trade crosses the maturity date  only for demo purpose*/

    @GetMapping("/changeDate")
    public int changeDate() {
        Date todayDate = Date.valueOf("2022-07-21");
        return tradeService.updateExpiryFlag(todayDate);
    }
}
