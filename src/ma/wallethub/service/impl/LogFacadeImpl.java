/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.wallethub.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.wallethub.bean.LogLine;
import ma.wallethub.bean.LogSynthese;
import ma.wallethub.service.LogLineService;
import ma.wallethub.service.LogFacade;
import ma.wallethub.service.LogSyntheseService;
import ma.wallethub.service.LogLineHourService;

/**
 *
 * @author YounesSama
 */
public class LogFacadeImpl implements LogFacade {

    private static final Logger LOG = Logger.getLogger(LogFacadeImpl.class.getName());

    private LogLineService logLineService = new LogLineServiceImpl();
    private LogLineHourService logLineSynthesService = new LogLineHourServiceImpl();
    private LogSyntheseService logSyntheseService = new LogSyntheseServiceImpl();

    @Override
    public List<LogSynthese> parseAndAnalyse(String filePath, boolean saveLog, boolean saveLogLineSynathese, boolean saveLogSynathese, String dateStart, Integer duration, Integer threshold) {
        List<LogSynthese> logSyntheses = new ArrayList<>();
        if (filePath != null) {
            List<LogLine> logLines = logLineService.parseFile(filePath, saveLog);
            logLineSynthesService.analyse(logLines, saveLogLineSynathese);
        }
        if (dateStart != null && duration != null && threshold != null) {
            logSyntheses = logSyntheseService.construct(dateStart, duration, threshold, saveLogSynathese);
            logSyntheses.forEach(System.out::println);
        }
        return logSyntheses;
    }

    public void clear(boolean logLine, boolean logLineSynthese, boolean logSynthese) {
        if (logLine) {
            LOG.info("Clearing Log Line Table .....");
            int res = logLineService.clear();
            LOG.log(Level.INFO, "Succussflly Clearing {0} element of Log Line Table", res);
        }
        if (logLineSynthese) {
            LOG.info("Clearing Log Line Syntese Table .....");
            int res = logLineSynthesService.clear();
            LOG.log(Level.INFO, "Succussflly Clearing {0} element of Log Line Syntese Table", res);
        }
        if (logSynthese) {
            LOG.info("Clearing Log  Syntese Table .....");
            int res = logSyntheseService.clear();
            LOG.log(Level.INFO, "Succussflly Clearing {0} element of Log  Syntese Table", res);
        }

    }

}
