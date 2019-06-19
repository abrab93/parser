/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.wallethub.test;

import java.util.List;
import ma.wallethub.bean.LogLine;
import ma.wallethub.service.impl.LogLineServiceImpl;
import ma.wallethub.service.LogLineService;

/**
 *
 * @author YounesSama
 */
public class LogLineServiceTest {

    private static LogLineService logLineService = new LogLineServiceImpl();

    public static void main(String[] args) {
        //testParseLine();
        testParseFile();
    }

    public static void testParseLine() {
        String text = "2017-01-01 23:59:56.907|192.168.167.234|\"GET / HTTP/1.1\"|200|\"Mozilla/5.0 (Windows NT 10.0; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0\"";
        LogLine logLine = logLineService.parseLine(text);
        System.out.println("originatl text : " + text);
        System.out.println("logLine object : " + logLine);
    }
    public static void testParseFile() {
        boolean save=true;
        String fileName = "C:\\Users\\YounesSama\\Downloads\\Java_MySQL_Test\\access.log";
        List<LogLine> logsAsLogLineObject = logLineService.parseFile(fileName,save);
        logsAsLogLineObject.forEach(System.out::println);
    }
}
