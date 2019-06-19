/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.wallethub.test;

import java.util.List;
import ma.wallethub.bean.LogLineSynthese;
import ma.wallethub.service.LogLineSyntheseService;
import ma.wallethub.service.impl.LogLineSyntheseServiceImpl;

/**
 *
 * @author YounesSama
 */
public class LogLineSyntheseServiceTest {

    private static LogLineSyntheseService logLineSynthese = new LogLineSyntheseServiceImpl();

    public static void main(String[] args) {
        //findByDateSynthes();
       findByDateMinDateMax();
    }

    public static void findByDateSynthes() {
        String datelogSynthese = "2017-01-01 15:00:00.000";
        int threshold = 200;
        List<LogLineSynthese> logLineSyntheses = logLineSynthese.findByDateSynthes(datelogSynthese, threshold);
        logLineSyntheses.forEach(System.out::println);
    }

    public static void findByDateMinDateMax() {
        String dateMin = "2017-01-01 00:00:00.000";
        String dateMax = "2017-01-01 23:59:59.000";
        int threshold = 500;
        List<Object[]> logLineSyntheses = logLineSynthese.findByDateMinDateMax(dateMin, dateMax, threshold);
        logLineSyntheses.forEach(e->System.out.println("ip : "+e[0]+" ==> "+e[1]));

    }
}
