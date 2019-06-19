/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.wallethub.dao.impl;

import java.util.List;
import ma.wallethub.bean.LogLineSynthese;
import ma.wallethub.dao.LogLineSyntheseDao;

/**
 *
 * @author YounesSama
 */
public class LogLineSyntheseDaoImpl extends AbstractDaoImpl<LogLineSynthese> implements LogLineSyntheseDao {

    public LogLineSyntheseDaoImpl() {
        super(LogLineSynthese.class);
    }

    @Override
    public List<Object[]> findByDateMinDateMax(String dateMin, String dateMax, int threshold) {
        final String query = "SELECT l.ip, SUM(l.sumPerHour) FROM LogLineSynthese l WHERE "
                + "l.dateLogLineSynthes >= '"+ dateMin + "' and l.dateLogLineSynthes<= '" + dateMax+ "'"
                + " GROUP BY l.ip HAVING SUM(l.sumPerHour) >= "+threshold+""
                + " ORDER BY SUM(l.sumPerHour) DESC";
        //System.out.println("query = " + query);
        return getEntityManager().createQuery(query).getResultList();
    }

    @Override
    public List<LogLineSynthese> findByDateSynthes(String dateSynthese, int threshold) {
        final String query = "SELECT l FROM LogLineSynthese l WHERE l.dateLogLineSynthes = '" + dateSynthese + "' and l.sumPerHour >= " + threshold;
       // System.out.println("query = " + query);
        return selectMultiple(query);
    }

}
