/*
    This class is a Model to store the status of scheduled thread i.e., locked_at and locked_until
    and is only consumed by the AppConfig to assign the customized name
*/

package com.example.schedulingtasks.model;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "Tradeshedlock")
public class TradeShedlock {

    @Id
    @Column(name = "name", unique = true, nullable = false)
    String name;

    @Column(name = "locked_at", nullable = false)
    @CreationTimestamp
    private Timestamp locked_at;

    @Column(name = "lock_until", nullable = false)
    @CreationTimestamp
    private Timestamp lock_until;

    @Column(name = "locked_by", nullable = false)
    String locked_by;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getLocked_at() {
        return locked_at;
    }

    public void setLocked_at(Timestamp locked_at) {
        this.locked_at = locked_at;
    }

    public Timestamp getLock_until() {
        return lock_until;
    }

    public void setLock_until(Timestamp lock_until) {
        this.lock_until = lock_until;
    }

    public String getLocked_by() {
        return locked_by;
    }

    public void setLocked_by(String locked_by) {
        this.locked_by = locked_by;
    }
}
