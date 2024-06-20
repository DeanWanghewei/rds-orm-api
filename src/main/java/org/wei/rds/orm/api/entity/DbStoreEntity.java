package org.wei.rds.orm.api.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @description:
 * @author: deanwanghewei@gmail.com
 * @date: 2024年04月29日 16:24
 */
@Schema(name = "DbStoreEntity", description = "数据库连接信息")
@Entity
@Table(name = "db_store")
public class DbStoreEntity {

    @Schema(description = "数据库连接名称,自定义名称")
    @Id
    private String name;

    @Schema(description = "数据库连接地址")
    private String jdbcUrl;

    @Schema(description = "数据库连接用户名")
    private String jdbcUsername;

    @Schema(description = "数据库连接密码")
    private String jdbcPassword;

    @Schema(description = "数据库连接驱动")
    private String jdbcDriverClassName;

    @Schema(description = "数据库连接类型;mysql,postgreSql")
    private String jdbcType;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUsername() {
        return jdbcUsername;
    }

    public void setJdbcUsername(String jdbcUsername) {
        this.jdbcUsername = jdbcUsername;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public String getJdbcDriverClassName() {
        return jdbcDriverClassName;
    }

    public void setJdbcDriverClassName(String jdbcDriverClassName) {
        this.jdbcDriverClassName = jdbcDriverClassName;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }
}
