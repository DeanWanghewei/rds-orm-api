package org.wei.rds.orm.api.view;

/**
 * @description:
 * @author: deanwanghewei@gmail.com
 * @date: 2024年07月18日 10:43
 */

public class ImportConfigResultView implements java.io.Serializable {
    private String name;
    private String msg;


    public ImportConfigResultView(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
