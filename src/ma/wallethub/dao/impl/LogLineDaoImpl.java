/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.wallethub.dao.impl;

import ma.wallethub.bean.LogLine;
import ma.wallethub.dao.LogLineDao;

/**
 *
 * @author YounesSama
 */
public class LogLineDaoImpl extends AbstractDaoImpl<LogLine> implements LogLineDao{

    public LogLineDaoImpl() {
        super(LogLine.class);
    }

}
