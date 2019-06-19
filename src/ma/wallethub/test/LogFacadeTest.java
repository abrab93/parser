/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.wallethub.test;

import ma.wallethub.service.impl.LogFacadeImpl;
import ma.wallethub.service.LogFacade;

/**
 *
 * @author YounesSama
 */
public class LogFacadeTest {

    private static  LogFacade logFacade = new LogFacadeImpl();

    public static void main(String[] args) {
        clear();
        testParseAndAnalyseDaily();
        //testParseAndAnalyseHourly();
    }

    public static void testParseAndAnalyseDaily() {
        String fileName = "C:\\Users\\YounesSama\\Downloads\\Java_MySQL_Test\\access.log";
        boolean saveLog = false;
        boolean saveLogLineSynathese=true;
        boolean saveLogSynathese=true;
        String dateStart="2017-01-01 00:00:00.000";
        int duration=1;
        int threshold=500;
        logFacade.parseAndAnalyse(fileName, saveLog, saveLogLineSynathese, saveLogSynathese, dateStart, duration, threshold);
        //logsAsLogLineObject.forEach(System.out::println);
    }
    public static void testParseAndAnalyseHourly() {
        String fileName = "C:\\Users\\YounesSama\\Downloads\\Java_MySQL_Test\\access.log";
        boolean saveLog = false;
        boolean saveLogLineSynathese=true;
        boolean saveLogSynathese=true;
        String dateStart="2017-01-01 15:00:00.000";
        int duration=2;
        int threshold=200;
        logFacade.parseAndAnalyse(fileName, saveLog, saveLogLineSynathese, saveLogSynathese, dateStart, duration, threshold);
        //logsAsLogLineObject.forEach(System.out::println);
    }

    public static void clear() {
        boolean logLine = true;
        boolean logLineSynthese = true;
        boolean logSynthese = true;
        logFacade.clear(logLine, logLineSynthese, logSynthese);
    }

}
