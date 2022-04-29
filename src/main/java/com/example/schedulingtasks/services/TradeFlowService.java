/*
      This class contain all the business logic which are required by the controller
      and consumed by the Controller
*/

package com.example.schedulingtasks.services;

import com.example.schedulingtasks.jpa.TradeFlowJpa;
import com.example.schedulingtasks.model.TradeFlowModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TradeFlowService {
    private TradeFlowJpa tradeJpa;

    public TradeFlowService() {
    }

    @Autowired
    public TradeFlowService(TradeFlowJpa tradeJpa) {
        this.tradeJpa = tradeJpa;
    }

    /* Is used to persist the respective data with some constraints */

    public Map<String, Object> saveTradeObject(TradeFlowModel tradeModel) {
        int status = 0;
        int minVersion = 0;
        String tradeid = "";
        Map<String, Object> mapStatusObject = new HashMap<String, Object>();
        Date todayDate = new Date(System.currentTimeMillis());
        int dateCompare = tradeModel.getMaturity_date().compareTo(todayDate);

        if(tradeModel.getCreated_date() == null)
            tradeModel.setCreated_date(todayDate);
        if(tradeModel.getExpired() == null || tradeModel.getExpired().equals(""))
            tradeModel.setExpired("N");

        Object minObjVersion = tradeJpa.getMinVersion();
        if (minObjVersion != null)
            minVersion = (Integer) minObjVersion;

        Object tradeObjid = tradeJpa.getTradeIdByVersion(tradeModel.getVersion());
        if (tradeObjid != null)
            tradeid = (String) tradeObjid;

        if (tradeModel.getVersion() < minVersion) {
            mapStatusObject.put("status", "The lower version of trade is being received and rejected");
            status = 1;
        } else if (!tradeid.equals("")) {
            if (dateCompare < 0)
                mapStatusObject.put("status", "The trade has less maturity date then today date");
            else {
                int updateStatus = tradeJpa.updateTrade(tradeModel.getVersion(), tradeModel.getCounter_party_id(), tradeModel.getBook_id(), tradeModel.getMaturity_date(), tradeModel.getCreated_date(), tradeModel.getExpired(), tradeid);
                if (updateStatus != 0) {
                    tradeModel.setTradeid(tradeid);
                    mapStatusObject.put("status", "The Existing Trade updated successfully !!!!!!");
                    mapStatusObject.put("tradeObj", tradeModel);
                } else
                    mapStatusObject.put("status", "The Existing Trade not updated");
            }
            status = 1;
        } else if (dateCompare < 0) {
            mapStatusObject.put("status", "The trade has less maturity date then today date");
            status = 1;
        }
        if (status == 0) {
            Optional<TradeFlowModel> exisTrade = Optional.ofNullable(tradeJpa.save(tradeModel));
            if (exisTrade.isPresent()) {
                mapStatusObject.put("status", "The Trade Persist Successfully !!!!!!!");
                mapStatusObject.put("tradeObj", exisTrade.get());
            } else
                mapStatusObject.put("status", "The Trade is not persist");
        }
        return mapStatusObject;
    }

    /* Is used to list all the TradeFlowModel data */

    public List<TradeFlowModel> getlistTradeObject() {
        return tradeJpa.findAll();
    }

    /* Is used to update the expire flag if in a store the trade crosses the maturity date  only for demo purpose*/

    public int updateExpiryFlag(Date todayDate) {
        return tradeJpa.updateExpiryFlag(todayDate);
    }

}
