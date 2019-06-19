/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.wallethub.service;

import java.util.Date;
import java.util.List;
import ma.wallethub.bean.LogLine;
import ma.wallethub.bean.LogLineSynthese;

/**
 *
 * @author YounesSama
 */
public interface LogLineSyntheseService {

    int clear();

    List<LogLineSynthese> analyse(List<LogLine> logsAsLogLineObject, boolean save);

    List<LogLineSynthese> findByDateSynthes(String datelogSynthese, int threshold);
    
    List<Object[]> findByDateMinDateMax(String dateMin, String dateMax,int threshold);

}
