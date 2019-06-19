/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.wallethub.dao.impl;

import ma.wallethub.bean.LogSynthese;
import ma.wallethub.dao.LogSyntheseDao;

/**
 *
 * @author YounesSama
 */
public class LogSyntheseDaoImpl extends AbstractDaoImpl<LogSynthese> implements LogSyntheseDao{

    public LogSyntheseDaoImpl() {
        super(LogSynthese.class);
    }

}
