package org.wei.rds.orm.api.server;

import org.wei.rds.orm.api.model.CreateDataModel;
import org.wei.rds.orm.api.view.ResView;

/**
 * @description:
 * @author: deanwanghewei@gmail.com
 * @date: 2024年04月29日 17:47
 */
public interface DataServer {

    ResView insertData(String dbName, CreateDataModel data);
}
