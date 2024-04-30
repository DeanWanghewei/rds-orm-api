package org.wei.rds.orm.api.server.impl;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.wei.rds.orm.api.db.ConnectionFactory;
import org.wei.rds.orm.api.model.CreateDataModel;
import org.wei.rds.orm.api.server.DataServer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

/**
 * @description:
 * @author: deanwanghewei@gmail.com
 * @date: 2024年04月29日 17:47
 */
@Service("dataServer")
public class DataServerImpl implements DataServer {
    private static final Logger logger = LoggerFactory.getLogger(DataServerImpl.class);

    @Resource
    private ConnectionFactory dbFactory;

    @Override
    public void insertData(String dbName, CreateDataModel data) {
        try {
            Connection connection = dbFactory.getConnection(dbName);
            StringBuilder sql = new StringBuilder();
            sql.append("insert into ").append(data.getTableName());
            sql.append("(");
            for (String key : data.getRow().keySet()) {
                sql.append(key).append(",");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(") values(");
            for (Object value : data.getRow().values()) {
                sql.append("'").append(value).append("',");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(")");
            connection.createStatement().execute(sql.toString());
            connection.close();
        } catch (ExecutionException | SQLException e) {
            logger.error(e.getMessage(), e);
        }

    }
}
