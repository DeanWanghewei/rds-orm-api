package org.wei.rds.orm.api.server.impl;

import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.wei.rds.orm.api.db.ConnectionFactory;
import org.wei.rds.orm.api.model.CreateDataModel;
import org.wei.rds.orm.api.model.QueryDataFilterItemModel;
import org.wei.rds.orm.api.model.QueryDataFilterModel;
import org.wei.rds.orm.api.model.QueryDataModel;
import org.wei.rds.orm.api.server.DataServer;
import org.wei.rds.orm.api.view.QueryDataView;
import org.wei.rds.orm.api.view.ResView;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResView insertData(String dbName, CreateDataModel data) {
        ResView resView = new ResView();
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
            resView.setSuccess(false);
            resView.setMessage(e.getMessage());
        }
        return resView;
    }

    @Override
    public QueryDataView queryData(String dbName, QueryDataModel query) {

        try {
            if (StringUtils.isNotBlank(query.getQuerySql())) {
                return new QueryDataView(sqlQuery(dbName, query.getQuerySql()));
            } else {
                return new QueryDataView(simpleQuery(dbName, query.getSimple()));
            }
        } catch (Exception e) {
            logger.warn("queryData error,", e);
        }
        return new QueryDataView(new ArrayList<>());
    }

    public List<Map<String, Object>> simpleQuery(String dbName, QueryDataFilterModel simple) throws SQLException, ExecutionException {

        StringBuilder querySql = new StringBuilder();
        querySql.append(" select ")
                .append(simple.getCols())
                .append(" from ")
                .append(simple.getTableName());

        if (!CollectionUtils.isEmpty(simple.getFilters())) {
            querySql.append(" where 1=1 ");
            for (QueryDataFilterItemModel filter : simple.getFilters()) {
                if (filter.getCol() != null && filter.getCo() != null && filter.getVal() != null) {
                    querySql.append(" and ")
                            .append(filter.getCol()).append(" ")
                            .append(filter.getCo()).append(" ")
                            .append("'").append(filter.getVal()).append("' ");
                }
            }
        }

        if (simple.getLimit() != null && simple.getOffset() != null) {
            querySql.append(" limit ").append(simple.getLimit())
                    .append(" offset ").append(simple.getOffset());
        }
        return sqlQuery(dbName, querySql.toString());
    }

    public List<Map<String, Object>> sqlQuery(String dbName, String sql) throws SQLException, ExecutionException {
        Connection connection = dbFactory.getConnection(dbName);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        List<Map<String, Object>> records = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        ArrayList<String> colNameList = new ArrayList<>();
        for (int i = 0; i < metaData.getColumnCount(); i++) {
            colNameList.add(metaData.getColumnLabel(i + 1));
        }
        while (resultSet.next()) {
            Map<String, Object> row = new HashMap<>(colNameList.size());
            colNameList.forEach(item -> {
                try {
                    row.put(item, resultSet.getObject(item));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            records.add(row);
        }
        return records;
    }

}
