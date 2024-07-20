package org.wei.rds.orm.api.model;

/**
 * @description: 插入数据模型
 * @author: deanwanghewei@gmail.com
 * @date: 2024年04月29日 17:22
 */
public class QueryDataModel implements java.io.Serializable {
    /**
     * 高级查询，直接使用sql 进行查询
     */
    private String querySql;


    /**
     * 简单单表查询
     */
    private QueryDataFilterModel simple;

    public String getQuerySql() {
        return querySql;
    }

    public void setQuerySql(String querySql) {
        this.querySql = querySql;
    }

    public QueryDataFilterModel getSimple() {
        return simple;
    }

    public void setSimple(QueryDataFilterModel simple) {
        this.simple = simple;
    }
}
