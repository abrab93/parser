/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.wallethub.service;

import java.util.List;
import ma.wallethub.bean.LogLine;
import ma.wallethub.bean.LogLineHour;

/**
 *
 * @author YounesSama
 */
public interface LogLineHourService {

    int clear();

    List<LogLineHour> analyse(List<LogLine> logsAsLogLineObject, boolean save);

    List<LogLineHour> findByDateSynthes(String dateLogLineHour, int threshold);
    
    List<Object[]> findByDateMinDateMax(String dateMin, String dateMax,int threshold);

}
