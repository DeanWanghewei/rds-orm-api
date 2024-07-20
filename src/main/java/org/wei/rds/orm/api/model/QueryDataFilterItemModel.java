package org.wei.rds.orm.api.model;

/**
 * @description:
 * @author: deanwanghewei@gmail.com
 * @date: 2024年07月18日 11:03
 */
public class QueryDataFilterItemModel implements java.io.Serializable {
    /**
     * 字段名
     */
    private String col;

    /**
     * 比较运算符
     */
    private String co;

    /**
     * 比较结果值
     */
    private Object val;


    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }
}
