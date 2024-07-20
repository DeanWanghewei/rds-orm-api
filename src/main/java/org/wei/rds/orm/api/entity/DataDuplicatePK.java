package org.wei.rds.orm.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: deanwanghewei@gmail.com
 * @date: 2024年07月18日 16:29
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataDuplicatePK implements java.io.Serializable {
    private String source;

    private String md5Value;
}
