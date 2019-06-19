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
import ma.wallethub.bean.LogLineSynthese;
import ma.wallethub.dao.LogLineSyntheseDao;
import ma.wallethub.dao.impl.LogLineSyntheseDaoImpl;
import ma.wallethub.util.DateUtil;
import ma.wallethub.service.LogLineSyntheseService;

/**
 *
 * @author YounesSama
 */
public class LogLineSyntheseServiceImpl implements LogLineSyntheseService {

    private static final Logger LOG = Logger.getLogger(LogLineSyntheseServiceImpl.class.getName());
    private LogLineSyntheseDao logLineSyntheseDao = new LogLineSyntheseDaoImpl();

    @Override
    public List<LogLineSynthese> analyse(List<LogLine> logsAsLogLineObject, boolean save) {
        final double sizeOriginal = logsAsLogLineObject.size();

        LOG.log(Level.INFO, "Groupping LogLine data  by hour and ip : data size  $$$$ {0} $$$$.....", sizeOriginal);
        Map<String, Long> resultAsMap = logsAsLogLineObject.stream().collect(
                Collectors.groupingBy(LogLine::getDateHourConcatIp, Collectors.counting()));
        final double sizeGroupedData = resultAsMap.size();
        final double optimisation = (sizeOriginal - sizeGroupedData)*100 / sizeOriginal;
        LOG.log(Level.INFO, "Succussflly Groupping LogLine data by hour and ip: grouped data size  ******* {0} ********.....", sizeGroupedData);
        LOG.log(Level.INFO, "Succussflly Groupping LogLine data by hour and ip: optimisation   ******* {0} %  *******.....", optimisation);

        List<LogLineSynthese> logLineSyntheses = transform(resultAsMap);

        LOG.info("Sorting LogLineSynthese data by date and sum.....");
        Collections.sort(logLineSyntheses); // implementation of comparable in logLineSynthese bean 
        LOG.info("Succussflly Sorting LogLineSynthese data by date and sum.");
        if (save) {
            LOG.info("Saving data LogLineSynthese .....");
            logLineSyntheseDao.create(logLineSyntheses);
            LOG.info("Succussflly Saving LogLineSynthese data .....");
        }

        return logLineSyntheses;
    }

    @Override
    public int clear() {
        return logLineSyntheseDao.clear();
    }

    @Override
    public List<LogLineSynthese> findByDateSynthes(String datelogSynthese, int threshold) {
        return logLineSyntheseDao.findByDateSynthes(datelogSynthese, threshold);
    }

    @Override
    public List<Object[]> findByDateMinDateMax(String dateMin, String dateMax, int threshold) {
        return logLineSyntheseDao.findByDateMinDateMax(dateMin, dateMax, threshold);
    }

    private List<LogLineSynthese> transform(Map<String, Long> resultAsMap) {
        List<LogLineSynthese> logLineSyntheses = new ArrayList<>();
        resultAsMap.entrySet().forEach((entry) -> {
            String key = entry.getKey();
            Integer value = Integer.parseInt(entry.getValue().toString());
            String[] splitKey = key.split(";");
            Date date = DateUtil.parseFormatHour(splitKey[0]);
            logLineSyntheses.add(new LogLineSynthese(date, splitKey[1], value));
        });
        return logLineSyntheses;
    }

    public LogLineSyntheseServiceImpl() {

    }
}
