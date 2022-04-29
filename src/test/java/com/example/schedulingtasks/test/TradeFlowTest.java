package com.example.schedulingtasks.test;

import com.example.schedulingtasks.SchedulingTasksApplication;
import com.example.schedulingtasks.model.TradeFlowModel;
import com.example.schedulingtasks.services.TradeFlowService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchedulingTasksApplication.class)
@TestPropertySource(locations = "classpath:application-unit.properties")
public class TradeFlowTest {

    @Autowired
    private TradeFlowService tradeFlowService;

    /* To check whether by calling the respective service method the general Trade will save or not */

    @Test
    @Order(1)
    public void testSaveTradeObject() {
        Date todayDate = new Date(System.currentTimeMillis());

        TradeFlowModel tradeFlowModel = new TradeFlowModel(2,"CP-2","B1",Date.valueOf("2022-05-20"),"N");
        Map<String, Object> mapTradeService = tradeFlowService.saveTradeObject(tradeFlowModel);

        TradeFlowModel tradeFlowModel1 = new TradeFlowModel(3,"CP-3","B2",Date.valueOf("2023-02-04"),todayDate);
        Map<String, Object> mapTradeService1 = tradeFlowService.saveTradeObject(tradeFlowModel1);

        Assert.assertEquals("The Trade Persist Successfully !!!!!!!",mapTradeService.get("status"));
        Assert.assertNotNull(mapTradeService.get("tradeObj"));

        Assert.assertEquals("The Trade Persist Successfully !!!!!!!",mapTradeService1.get("status"));
        Assert.assertNotNull(mapTradeService1.get("tradeObj"));
    }

    /* To check whether by calling the respective service method Trade with Lower version will reject or not */

    @Test
    @Order(2)
    public void testSaveTradeLowerVersion() {
        TradeFlowModel tradeFlowModel = new TradeFlowModel(1,"CP-1","B1",Date.valueOf("2022-05-20"),"N");
        Map<String, Object> mapTradeService = tradeFlowService.saveTradeObject(tradeFlowModel);
        Assert.assertEquals("The lower version of trade is being received and rejected",mapTradeService.get("status"));
    }

    /* To check whether by calling the respective service method Trade with same version is Override or not */

    @Test
    @Order(3)
    public void testSaveTradeSameVersion() {
        TradeFlowModel tradeFlowModel = new TradeFlowModel(2,"CP-8","B3",Date.valueOf("2022-06-09"));
        Map<String, Object> mapTradeService = tradeFlowService.saveTradeObject(tradeFlowModel);
        Assert.assertEquals("The Existing Trade updated successfully !!!!!!",mapTradeService.get("status"));
        Assert.assertNotNull(mapTradeService.get("tradeObj"));
    }

    /* To check whether by calling the respective service method Trade with Lower maturity than today date will reject or not */

    @Test
    @Order(4)
    public void testSaveTradeLowerMaturity() {
        TradeFlowModel tradeFlowModel = new TradeFlowModel(4,"CP-4","B13",Date.valueOf("2021-02-07"),"Y");
        Map<String, Object> mapTradeService = tradeFlowService.saveTradeObject(tradeFlowModel);
        Assert.assertEquals("The trade has less maturity date then today date",mapTradeService.get("status"));
    }

    /* To check whether by calling the respective service method the expired flag will update or not */

    @Test
    @Order(5)
    public void testUpdateExpiredFlag() {
        Date todayDate = Date.valueOf("2023-05-20");
        int tradeUpdate = tradeFlowService.updateExpiryFlag(todayDate);
        Assert.assertNotEquals(0,tradeUpdate);
    }

    /* To check whether by calling the respective service method to list out the trades */

    @Test
    @Order(5)
    public void testGetlistTradeObject() {
        List<TradeFlowModel> tradeModelList = tradeFlowService.getlistTradeObject();
        Assert.assertNotEquals(0,tradeModelList.size());
    }
}
