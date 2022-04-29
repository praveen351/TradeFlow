package com.example.schedulingtasks.test;

import com.example.schedulingtasks.SchedulingTasksApplication;
import com.example.schedulingtasks.model.TradeFlowModel;
import com.example.schedulingtasks.services.TradeFlowService;
import org.junit.Assert;
import org.junit.Test;
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

    @Test
    public void testSaveTradeObject() {
        Date todayDate = new Date(System.currentTimeMillis());
        TradeFlowModel tradeFlowModel = new TradeFlowModel(1,"CP-1","B1",Date.valueOf("2022-05-20"),"N");
        tradeFlowModel.setCreated_date(todayDate);
        Map<String, Object> mapTradeService = tradeFlowService.saveTradeObject(tradeFlowModel);
        Assert.assertEquals("The Trade Persist Successfully !!!!!!!",mapTradeService.get("status"));
        Assert.assertNotNull(mapTradeService.get("tradeObj"));
    }



    @Test
    public void testGetlistTradeObject() {
        List<TradeFlowModel> tradeModelList = tradeFlowService.getlistTradeObject();
        Assert.assertEquals(1,tradeModelList.size());
    }

}
