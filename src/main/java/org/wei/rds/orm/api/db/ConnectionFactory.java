package org.wei.rds.orm.api.db;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.hash.Hashing;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.wei.rds.orm.api.entity.DbStoreEntity;
import org.wei.rds.orm.api.repository.DbStoreRepository;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * @description:
 * @author: deanwanghewei@gmail.com
 * @date: 2024年04月29日 17:35
 */
@Component
public class ConnectionFactory {
    @Resource
    private DbStoreRepository dbStoreRepository;
    Cache<String, HikariDataSource> cache = CacheBuilder.newBuilder().build();

    public Connection getConnection(String dbName) throws ExecutionException, SQLException {
        Optional<DbStoreEntity> dataSource = dbStoreRepository.findById(dbName);
        if (dataSource.isEmpty()) {
            throw new RuntimeException("not found data source");
        }
        DbStoreEntity dbStore = dataSource.get();
        String cacheKey = getCacheKey(dbStore);
        HikariDataSource source = cache.get(cacheKey, () -> {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbStore.getJdbcUrl());
            config.setUsername(dbStore.getJdbcUsername());
            config.setPassword(dbStore.getJdbcPassword());
            config.setDriverClassName(dbStore.getJdbcDriverClassName());
            config.setMaximumPoolSize(10);
            config.setMinimumIdle(1);
            config.setMaxLifetime(300000); // 300 秒
            config.setIdleTimeout(600000); // 600 秒
            // 创建 HikariDataSource 对象
            return new HikariDataSource(config);
        });


        return source.getConnection();
    }

    private String getCacheKey(DbStoreEntity entity) {
        return Hashing.sha256().hashString(entity.getJdbcUrl() + entity.getJdbcUsername() + entity.getJdbcPassword(),
                Charset.defaultCharset()).toString();
    }
}
