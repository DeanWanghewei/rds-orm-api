package org.wei.rds.orm.api.view;

/**
 * @description:
 * @author: deanwanghewei@gmail.com
 * @date: 2024年07月18日 15:19
 */
public class DuplicateCheckView implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private boolean isValid = true;


    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
