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
import javax.persistence.Transient;

/**
 *
 * @author YounesSama
 */
@Entity
public class LogLine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateLog;
    private String ip;
    private String request;
    private int status;
    private String userAgent;
    @Transient
    private String dateHourConcatIp; // this is a Vo/Dto in order to group element

    public LogLine() {
    }

    public LogLine(Long id, Date dateLog, String ip, String request, int status, String userAgent) {
        this.dateLog = dateLog;
        this.ip = ip;
        this.request = request;
        this.status = status;
        this.userAgent = userAgent;
        this.id = id;
    }

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateLog() {
        return dateLog;
    }

    public void setDateLog(Date dateLog) {
        this.dateLog = dateLog;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getDateHourConcatIp() {
        return dateHourConcatIp;
    }

    public void setDateHourConcatIp(String dateHourConcatIp) {
        this.dateHourConcatIp = dateHourConcatIp;
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
        final LogLine other = (LogLine) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LogLine{" + "id=" + id + ", dateLog=" + dateLog + ", ip=" + ip + ", request=" + request + ", status=" + status + ", userAgent=" + userAgent + '}';
    }

}
