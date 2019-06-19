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
import ma.wallethub.dao.LogLineDao;
import ma.wallethub.dao.impl.LogLineDaoImpl;
import ma.wallethub.util.DateUtil;
import ma.wallethub.util.FileUtil;
import ma.wallethub.util.StringUtil;
import ma.wallethub.service.LogLineService;

/**
 *
 * @author YounesSama
 */
public class LogLineServiceImpl implements LogLineService {

    private static final Logger LOG = Logger.getLogger(LogLineServiceImpl.class.getName());
    private static final String DELIMITOR = "\\|";
    private LogLineDao logLineDao = new LogLineDaoImpl();

    public int clear() {
        return logLineDao.clear();
    }

    @Override
    public List<LogLine> parseFile(String filePath, boolean save) {
        LOG.info("Reading Log File .....");
        List<String> logsAsString = FileUtil.read(filePath);
        LOG.log(Level.INFO, "Succussflly read {0} element of Log File", logsAsString.size());

        LOG.info("Transforming  Log File to  List<LogLine>.....");
        List<LogLine> logLines = new ArrayList<>();
        logsAsString.forEach(e -> logLines.add(parseLine(e)));
        LOG.info("Succussflly Transforming  Log File to  List<LogLine>.");

        if (save) {
            LOG.info("Saving LogLine data .....");
            logLineDao.create(logLines);
            LOG.log(Level.INFO, "Succussflly Saving LogLine {0} element.", logLines.size());
        }
        return logLines;
    }

    @Override
    public LogLine parseLine(String text) {
        if (StringUtil.isNull(text)) {
            LOG.log(Level.SEVERE, "Line Text is Null, thus it can't be converted");
            return null;
        } else {
            String[] myLoglineSplited = text.split(DELIMITOR);
            if (myLoglineSplited.length != 5) {
                LOG.log(Level.SEVERE, "Number of Paramater is not valid : expected 5 founded {0}", myLoglineSplited.length);
                return null;
            } else {
                return constructlogLine(myLoglineSplited);
            }
        }
    }

    private LogLine constructlogLine(String[] myLoglineSplited) throws NumberFormatException {
        LogLine logLine = new LogLine();
        logLine.setDateLog(DateUtil.parse(myLoglineSplited[0]));
        logLine.setIp(myLoglineSplited[1]);
        logLine.setRequest(StringUtil.eliminateDoubleQuote(myLoglineSplited[2]));
        logLine.setStatus(Integer.parseInt(myLoglineSplited[3]));
        logLine.setUserAgent(StringUtil.eliminateDoubleQuote(myLoglineSplited[4]));
        logLine.setDateHourConcatIp(DateUtil.formatFormatHour(logLine.getDateLog()) + ";" + logLine.getIp());
        return logLine;
    }

    public LogLineServiceImpl() {

    }

}
