package org.wei.rds.orm.api.model;

/**
 * @description:
 * @author: deanwanghewei@gmail.com
 * @date: 2024年07月18日 15:17
 */
public class DuplicateCheckModel implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String source;
    private String value;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
