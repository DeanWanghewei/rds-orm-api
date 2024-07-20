package org.wei.rds.orm.api.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;

/**
 * @description:
 * @author: deanwanghewei@gmail.com
 * @date: 2024年04月29日 16:24
 */
@Schema(name = "DataDuplicateEntity", description = "数据去重")
@Entity
@Table(name = "data_duplicate", uniqueConstraints = @UniqueConstraint(columnNames = {"source", "md5_value"}))
@IdClass(DataDuplicatePK.class)
public class DataDuplicateEntity implements Persistable {

    @Id
    @Column(name = "source")
    private String source;

    @Id
    @Column(name = "md5_value")
    private String md5Value;

    public DataDuplicateEntity() {
    }

    public DataDuplicateEntity(String source, String md5Value) {
        this.source = source;
        this.md5Value = md5Value;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMd5Value() {
        return md5Value;
    }

    public void setMd5Value(String md5Value) {
        this.md5Value = md5Value;
    }

    @Override
    public Object getId() {
        return new DataDuplicatePK(source, md5Value);
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
