/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.wallethub.service;

import java.util.List;
import ma.wallethub.bean.LogSynthese;

/**
 *
 * @author YounesSama
 */
public interface LogSyntheseService {

    int clear();

    List<LogSynthese> construct(String dateStart, int duration, int threshold, boolean save);
}
