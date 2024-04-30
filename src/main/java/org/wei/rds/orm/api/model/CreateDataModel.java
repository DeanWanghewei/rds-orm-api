package org.wei.rds.orm.api.model;

import java.util.HashMap;

/**
 * @description: 插入数据模型
 * @author: deanwanghewei@gmail.com
 * @date: 2024年04月29日 17:22
 */
public class CreateDataModel implements java.io.Serializable {
    private String tableName;
    private HashMap<String, Object> row;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public HashMap<String, Object> getRow() {
        return row;
    }

    public void setRow(HashMap<String, Object> row) {
        this.row = row;
    }
}
