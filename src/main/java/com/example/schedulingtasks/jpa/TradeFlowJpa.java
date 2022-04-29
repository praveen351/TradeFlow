/*
      This class is a Repository and used to run the sql native queries of
      respective table to fulfill the business logic and consumed by both
      TradeFlowSchedulerService and TradeFlowService
*/

package com.example.schedulingtasks.jpa;

import com.example.schedulingtasks.model.TradeFlowModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;

@Repository
public interface TradeFlowJpa extends JpaRepository<TradeFlowModel, String> {
    @Query(value = "SELECT max(version) FROM Trademodel", nativeQuery = true)
    Object getMaxVersion();

    @Query(value = "SELECT tradeid FROM Trademodel where version=:version", nativeQuery = true)
    Object getTradeIdByVersion(@Param("version") int version);

    @Transactional
    @Modifying
    @Query(value = "update Trademodel set version=:version,counter_party_id=:counter_party_id,book_id=:book_id,maturity_date=:maturity_date,created_date=:created_date,expired=:expired where tradeid=:tradeid", nativeQuery = true)
    int updateTrade(@Param("version") int version, @Param("counter_party_id") String counter_party_id, @Param("book_id") String book_id, @Param("maturity_date") Date maturity_date, @Param("created_date") Date created_date, @Param("expired") String expired, @Param("tradeid") String tradeid);

    @Transactional
    @Modifying
    @Query(value = "update Trademodel set expired='Y' where tradeid=:tradeid", nativeQuery = true)
    int updateExpiryFlag(@Param("tradeid") String tradeid);

    @Transactional
    @Modifying
    @Query(value = "update Trademodel set expired='Y' where maturity_date<=:created_date", nativeQuery = true)
    int updateExpiryFlag(@Param("created_date") Date created_date);
}
