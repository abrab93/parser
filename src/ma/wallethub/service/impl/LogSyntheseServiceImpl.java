/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.wallethub.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import ma.wallethub.bean.LogLineHour;
import ma.wallethub.bean.LogSynthese;
import ma.wallethub.dao.LogSyntheseDao;
import ma.wallethub.dao.impl.LogSyntheseDaoImpl;
import ma.wallethub.service.LogSyntheseService;
import ma.wallethub.util.DateUtil;
import ma.wallethub.service.LogLineHourService;

/**
 *
 * @author YounesSama
 */
public class LogSyntheseServiceImpl implements LogSyntheseService {

    private static final Logger LOG = Logger.getLogger(LogSyntheseServiceImpl.class.getName());
    private LogSyntheseDao logSyntheseDao = new LogSyntheseDaoImpl();
    private LogLineHourService logLineSyntheseService = new LogLineHourServiceImpl();

    public int clear() {
        return logSyntheseDao.clear();
    }

    public List<LogSynthese> construct(String dateStart, int duration, int threshold,boolean save) {
        List<LogSynthese> logSyntheses = new ArrayList();
        Date dateStartAsDate=DateUtil.parse(dateStart);
        if (duration == 1) {
            String dateEnd = DateUtil.addDay(dateStart, 1);
            List<Object[]> objects = logLineSyntheseService.findByDateMinDateMax(dateStart, dateEnd, threshold);
            for (Object[] object : objects) {
                logSyntheses.add(new LogSynthese(dateStartAsDate, duration, Integer.parseInt(object[1].toString()), object[0].toString()));
            }
        }else{
            List<LogLineHour> logLineSyntheses = logLineSyntheseService.findByDateSynthes(dateStart, threshold);
            for (LogLineHour logLineSynthese : logLineSyntheses) {
                logSyntheses.add(new LogSynthese(dateStartAsDate, duration, logLineSynthese.getSumPerHour(), logLineSynthese.getIp()));
            }
        }
        if(save){
            logSyntheseDao.create(logSyntheses);
        }

        return logSyntheses;
    }

    public void create(List<LogSynthese> logSyntheses) {
        logSyntheseDao.create(logSyntheses);
    }

    public LogSyntheseServiceImpl() {

    }

}
