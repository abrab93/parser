/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.wallethub.service;

import java.util.List;
import ma.wallethub.bean.LogSynthese;

/**
 *
 * @author YounesSama
 */
public interface LogFacade {

    void clear(boolean logLine, boolean logLineSynthese, boolean logSynthese);

    List<LogSynthese> parseAndAnalyse(String filePath, boolean saveLog, boolean saveLogLineSynathese, boolean saveLogSynathese, String dateStart, Integer duration, Integer threshold);
}
