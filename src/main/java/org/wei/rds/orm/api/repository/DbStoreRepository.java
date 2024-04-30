package org.wei.rds.orm.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wei.rds.orm.api.entity.DbStoreEntity;

/**
 * @description:
 * @author: deanwanghewei@gmail.com
 * @date: 2024年04月29日 16:29
 */
public interface DbStoreRepository extends JpaRepository<DbStoreEntity, String> {
}
