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
public class LogLineSynthese implements Comparable<LogLineSynthese>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLogLineSynthes;//yyyy-MM-dd hh:00:00.000
    private String ip;
    private int sumPerHour;

    public LogLineSynthese() {
    }

    public LogLineSynthese(Date dateLogLineSynthes, String ip, int sumPerHour) {
        this.dateLogLineSynthes = dateLogLineSynthes;
        this.ip = ip;
        this.sumPerHour = sumPerHour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getDateLogLineSynthes() {
        return dateLogLineSynthes;
    }

    public void setDateLogLineSynthes(Date dateLogLineSynthes) {
        this.dateLogLineSynthes = dateLogLineSynthes;
    }

    public int getSumPerHour() {
        return sumPerHour;
    }

    public void setSumPerHour(int sumPerHour) {
        this.sumPerHour = sumPerHour;
    }

    @Override
    public String toString() {
        return "dateLogLineSynthes=" + DateUtil.formatFormatHour(dateLogLineSynthes) + ", ip=" + ip + ", sumPerHour=" + sumPerHour;
    }

    @Override
    public int compareTo(LogLineSynthese o) {
        int diffTime = (int) (this.getDateLogLineSynthes().getTime() - o.getDateLogLineSynthes().getTime());
        if (diffTime != 0) {
            return diffTime;
        } else {
            return (int) (o.getSumPerHour() - this.getSumPerHour());
        }
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
        final LogLineSynthese other = (LogLineSynthese) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }


}
