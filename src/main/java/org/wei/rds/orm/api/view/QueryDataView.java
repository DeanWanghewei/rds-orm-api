package org.wei.rds.orm.api.view;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: deanwanghewei@gmail.com
 * @date: 2024年07月18日 11:26
 */
@Data
public class QueryDataView implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 返回的结果
     */
    private List<Map<String, Object>> records;
    /**
     * 返回条数
     */
    private int size = 0;

    public QueryDataView(List<Map<String, Object>> records) {
        this.records = records;
        if (this.records != null) {
            this.size = this.records.size();
        }
    }
}
