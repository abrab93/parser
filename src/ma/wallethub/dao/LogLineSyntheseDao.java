/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.wallethub.dao;

import java.util.Date;
import java.util.List;
import ma.wallethub.bean.LogLineSynthese;

/**
 *
 * @author YounesSama
 */
public interface LogLineSyntheseDao extends AbstractDao<LogLineSynthese> {

    public List<Object[]> findByDateMinDateMax(String dateMin, String dateMax, int threshold);

    public List<LogLineSynthese> findByDateSynthes(String dateSynthese, int threshold);
}
