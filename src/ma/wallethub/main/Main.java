/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.wallethub.main;

import java.util.logging.Level;
import java.util.logging.Logger;
import ma.wallethub.service.LogFacade;
import ma.wallethub.service.impl.LogFacadeImpl;

/**
 *
 * @author YounesSama
 */
public class Main {

    private static LogFacade logFacade = new LogFacadeImpl();
    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    private static final String HOURLY = "hourly";
    private static final String DAILY = "daily";

    public static void main(String[] args) {

        if (args == null || args.length < 3) {
            LOG.log(Level.SEVERE, "Argument number expected to be at least 3 but founded {0}", args == null ? 0 : args.length);
        }

        boolean logLine = extractArgAsBool(args, "--clearLogLine");
        boolean logLineSynthese = extractArgAsBool(args, "--clearLogLineSynthese");
        boolean logSynthese = extractArgAsBool(args, "--clearLogSynthese");

        logFacade.clear(logLine, logLineSynthese, logSynthese);

        String startDate = prepareDate(extractArg(args, "--startDate"));
        Integer duration = prepareDuration(extractArg(args, "--duration"));
        String threshold = extractArg(args, "--threshold");
        String accesslog = extractArg(args, "--accesslog");

        boolean saveLog = true;
        boolean saveLogLineSynathese = true;
        boolean saveLogSynathese = true;
        logFacade.parseAndAnalyse(accesslog, saveLog, saveLogLineSynathese, saveLogSynathese, startDate, duration, threshold!=null?Integer.parseInt(threshold):null);
    }

    private static Boolean extractArgAsBool(String[] args, String flag) {
        String extractArg = extractArg(args, flag);
        if (extractArg == null) {
            return true;
        } else {
            return Boolean.getBoolean(extractArg);
        }
    }

    private static String extractArg(String[] args, String flag) {
        if (args == null || args.length == 0) {
            return null;
        }
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.trim().startsWith(flag)) {
                return arg.split("=")[1];
            }
        }
        return null;
    }

    private static String prepareDate(String date) {
        if (date != null) {
            return date.replace('.', ' ') + ".000";
        }
        return null;
    }

    private static Integer prepareDuration(String duration) {
        if (duration == null) {
            return null;
        } else if (HOURLY.equalsIgnoreCase(duration)) {
            return 2;
        } else if (duration.equalsIgnoreCase(DAILY)) {
            return 1;
        } else {
            return null;
        }
    }
}
