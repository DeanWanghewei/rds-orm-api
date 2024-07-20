package org.wei.rds.orm.api.model;

import java.util.List;

/**
 * @description:
 * @author: deanwanghewei@gmail.com
 * @date: 2024年07月18日 11:03
 */
public class QueryDataFilterModel implements java.io.Serializable {
    /**
     * 表名称
     */
    private String tableName;

    /**
     * 查询的字段名，可以用 * , id,name,`desc` 等传入。
     * 默认就是所有的字段都会返回，相当与会进行返回字段的过滤
     */
    private String cols = "*";


    /**
     * 翻页
     */
    private Integer offset;
    private Integer limit;


    private List<QueryDataFilterItemModel> filters;


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCols() {
        return cols;
    }

    public void setCols(String cols) {
        this.cols = cols;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public List<QueryDataFilterItemModel> getFilters() {
        return filters;
    }

    public void setFilters(List<QueryDataFilterItemModel> filters) {
        this.filters = filters;
    }
}
