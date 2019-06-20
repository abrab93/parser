/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.wallethub.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import ma.wallethub.bean.LogLine;
import ma.wallethub.bean.LogLineHour;
import ma.wallethub.dao.impl.LogLineHourDaoImpl;
import ma.wallethub.util.DateUtil;
import ma.wallethub.dao.LogLineHourDao;
import ma.wallethub.service.LogLineHourService;

/**
 *
 * @author YounesSama
 */
public class LogLineHourServiceImpl implements LogLineHourService {

    private static final Logger LOG = Logger.getLogger(LogLineHourServiceImpl.class.getName());
    private LogLineHourDao logLineHourDao = new LogLineHourDaoImpl();

    @Override
    public List<LogLineHour> analyse(List<LogLine> logsAsLogLineObject, boolean save) {
        final double sizeOriginal = logsAsLogLineObject.size();

        LOG.log(Level.INFO, "Groupping LogLine data  by hour and ip : data size  $$$$ {0} $$$$.....", sizeOriginal);
        Map<String, Long> resultAsMap = logsAsLogLineObject.stream().collect(
                Collectors.groupingBy(LogLine::getDateHourConcatIp, Collectors.counting()));
        final double sizeGroupedData = resultAsMap.size();
        final double optimisation = (sizeOriginal - sizeGroupedData)*100 / sizeOriginal;
        LOG.log(Level.INFO, "Succussflly Groupping LogLine data by hour and ip: grouped data size  ******* {0} ********.....", sizeGroupedData);
        LOG.log(Level.INFO, "Succussflly Groupping LogLine data by hour and ip: optimisation   ******* {0} %  *******.....", optimisation);

        List<LogLineHour> logLineHours = transform(resultAsMap);

        LOG.info("Sorting LogLineSynthese data by date and sum.....");
        Collections.sort(logLineHours); // implementation of comparable in logLineSynthese bean 
        LOG.info("Succussflly Sorting LogLineHour data by date and sum.");
        if (save) {
            LOG.info("Saving data LogLineHour .....");
            logLineHourDao.create(logLineHours);
            LOG.info("Succussflly Saving LogLineHour data .....");
        }

        return logLineHours;
    }

    @Override
    public int clear() {
        return logLineHourDao.clear();
    }

    @Override
    public List<LogLineHour> findByDateSynthes(String datedateLogLineHour, int threshold) {
        return logLineHourDao.findByDateSynthes(datedateLogLineHour, threshold);
    }

    @Override
    public List<Object[]> findByDateMinDateMax(String dateMin, String dateMax, int threshold) {
        return logLineHourDao.findByDateMinDateMax(dateMin, dateMax, threshold);
    }

    private List<LogLineHour> transform(Map<String, Long> resultAsMap) {
        List<LogLineHour> logLineHours = new ArrayList<>();
        resultAsMap.entrySet().forEach((entry) -> {
            String key = entry.getKey();
            Integer value = Integer.parseInt(entry.getValue().toString());
            String[] splitKey = key.split(";");
            Date date = DateUtil.parseFormatHour(splitKey[0]);
            logLineHours.add(new LogLineHour(date, splitKey[1], value));
        });
        return logLineHours;
    }

    public LogLineHourServiceImpl() {

    }
}
