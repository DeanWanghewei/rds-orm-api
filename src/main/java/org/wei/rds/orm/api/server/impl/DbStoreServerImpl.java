package org.wei.rds.orm.api.server.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.wei.rds.orm.api.entity.DbStoreEntity;
import org.wei.rds.orm.api.repository.DbStoreRepository;
import org.wei.rds.orm.api.server.DbStoreServer;
import org.wei.rds.orm.api.view.ImportConfigResultView;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: deanwanghewei@gmail.com
 * @date: 2024年07月18日 10:46
 */
@Service("dbStoreServer")
public class DbStoreServerImpl implements DbStoreServer {
    private static final Logger logger = LoggerFactory.getLogger(DbStoreServerImpl.class);
    @Resource
    private DbStoreRepository repository;

    @Override
    public List<ImportConfigResultView> importConfig(List<DbStoreEntity> storeEntities) {
        ArrayList<ImportConfigResultView> resultViews = new ArrayList<>();
        for (DbStoreEntity storeEntity : storeEntities) {
            ImportConfigResultView view = new ImportConfigResultView(storeEntity.getName());
            try {
                repository.save(storeEntity);
                view.setMsg("success");
            } catch (Exception e) {
                try {
                    logger.warn("importConfig item fail: {}", new ObjectMapper().writeValueAsString(storeEntity));
                } catch (JsonProcessingException ex) {
                    logger.warn("go some error", ex);
                }
                view.setMsg(e.getMessage());
            }
            resultViews.add(view);
        }
        return resultViews;
    }
}
