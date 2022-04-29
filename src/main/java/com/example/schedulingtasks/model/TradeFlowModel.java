/*
    This class is a Model to store the entity details of
    TradeFlow to process with the transmissions and consumed by All the Classes/Beans/Instances
*/

package com.example.schedulingtasks.model;

import com.example.schedulingtasks.StringPrefixedSequenceIdGenerator;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Trademodel")
@Check(constraints = "expired IN ('Y','N')")
public class TradeFlowModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trade_seq")
    @GenericGenerator(name = "trade_seq", strategy = "com.example.schedulingtasks.StringPrefixedSequenceIdGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
            @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "T"),
            @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%d") })

    @Column(name = "tradeid")
    private String tradeid;

    @Column(name = "version")
    private int version;

    @Column(name = "counter_party_id")
    private String counter_party_id;

    @Column(name = "book_id")
    private String book_id;

    @Column(name = "maturity_date")
    private Date maturity_date;

    @Column(name = "created_date")
    private Date created_date;

    @Column(name = "expired")
    private String expired;

    public TradeFlowModel() {
    }

    public TradeFlowModel(int version, String counter_party_id, String book_id, Date maturity_date, String expired) {
        this.version = version;
        this.counter_party_id = counter_party_id;
        this.book_id = book_id;
        this.maturity_date = maturity_date;
        this.expired = expired;
    }

    public String getTradeid() {
        return tradeid;
    }

    public void setTradeid(String tradeid) {
        this.tradeid = tradeid;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getCounter_party_id() {
        return counter_party_id;
    }

    public void setCounter_party_id(String counter_party_id) {
        this.counter_party_id = counter_party_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public Date getMaturity_date() {
        return maturity_date;
    }

    public void setMaturity_date(Date maturity_date) {
        this.maturity_date = maturity_date;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }
}
