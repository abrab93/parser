/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.wallethub.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import ma.wallethub.util.DateUtil;

/**
 *
 * @author YounesSama
 */
@Entity
public class LogSynthese implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;//yyyy-MM-dd hh:00:00.000
    private int duration; // 1=daily and 2 hourly
    private int hreshold;
    private String ip;

    public LogSynthese() {
    }

    public LogSynthese(Date startDate, int duration, int total, String ip) {
        this.startDate = startDate;
        this.duration = duration;
        this.hreshold = total;
        this.ip = ip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getHreshold() {
        return hreshold;
    }

    public void setHreshold(int hreshold) {
        this.hreshold = hreshold;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LogSynthese other = (LogSynthese) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String durationAsTring = duration == 1 ? "Daily" : "Hourly";
        return "startDate=" + DateUtil.format(startDate) + ", duration=" + durationAsTring + ", hreshold=" + hreshold + ", ip=" + ip ;
    }

}
