/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.wallethub.service;

import java.util.List;
import ma.wallethub.bean.LogLine;

/**
 *
 * @author YounesSama
 */
public interface LogLineService {

    List<LogLine> parseFile(String filePath, boolean save);

    LogLine parseLine(String text);

    int clear();

}
