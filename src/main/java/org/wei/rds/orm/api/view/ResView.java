package org.wei.rds.orm.api.view;

import java.io.Serializable;

/**
 * @description:
 * @author: deanwanghewei@gmail.com
 * @date: 2024年06月20日 16:53
 */
public class ResView implements Serializable {
    private boolean success = true;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
