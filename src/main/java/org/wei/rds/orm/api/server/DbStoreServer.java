package org.wei.rds.orm.api.server;

import org.wei.rds.orm.api.entity.DbStoreEntity;
import org.wei.rds.orm.api.view.ImportConfigResultView;

import java.util.List;

/**
 * @description:
 * @author: deanwanghewei@gmail.com
 * @date: 2024年07月18日 10:45
 */
public interface DbStoreServer {
    /**
     *  导入配置
     * @param storeEntities
     * @return
     */
    List<ImportConfigResultView> importConfig(List<DbStoreEntity> storeEntities);
}
