/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.wallethub.test;

import java.util.List;
import ma.wallethub.bean.LogLineHour;
import ma.wallethub.service.impl.LogLineHourServiceImpl;
import ma.wallethub.service.LogLineHourService;

/**
 *
 * @author YounesSama
 */
public class LogLineHourServiceTest {

    private static LogLineHourService logLineHourService = new LogLineHourServiceImpl();

    public static void main(String[] args) {
        //findByDateSynthes();
       findByDateMinDateMax();
    }

    public static void findByDateSynthes() {
        String datelogSynthese = "2017-01-01 15:00:00.000";
        int threshold = 200;
        List<LogLineHour> logLineHours = logLineHourService.findByDateSynthes(datelogSynthese, threshold);
        logLineHours.forEach(System.out::println);
    }

    public static void findByDateMinDateMax() {
        String dateMin = "2017-01-01 00:00:00.000";
        String dateMax = "2017-01-01 23:59:59.000";
        int threshold = 500;
        List<Object[]> logLineHours = logLineHourService.findByDateMinDateMax(dateMin, dateMax, threshold);
        logLineHours.forEach(e->System.out.println("ip : "+e[0]+" ==> "+e[1]));

    }
}
