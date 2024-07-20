package org.wei.rds.orm.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.wei.rds.orm.api.entity.DbStoreEntity;
import org.wei.rds.orm.api.repository.DbStoreRepository;
import org.wei.rds.orm.api.server.DbStoreServer;
import org.wei.rds.orm.api.view.ImportConfigResultView;

import java.util.List;
import java.util.Optional;

/**
 * @description:
 * @author: deanwanghewei@gmail.com
 * @date: 2024年04月29日 16:36
 */
@Controller
public class DbStoreController {
    @Resource
    private DbStoreRepository dbStoreRepository;
    @Resource
    private DbStoreServer dbStoreServer;

    @Parameters(
            @Parameter(name = "name", description = "数据库连接名称", required = true)
    )
    @Operation(summary = "更新数据库连接", description = "更新数据库连接")
    @PutMapping("/db/{name}")
    public ResponseEntity<DbStoreEntity> update(@PathVariable("name") String name, @RequestBody DbStoreEntity dbStoreEntity) {
        Optional<DbStoreEntity> tutorialData = dbStoreRepository.findById(name);
        if (tutorialData.isPresent()) {
            DbStoreEntity tutorial = tutorialData.get();
            tutorial.setJdbcType(dbStoreEntity.getJdbcType());
            tutorial.setJdbcUrl(dbStoreEntity.getJdbcUrl());
            tutorial.setJdbcUsername(dbStoreEntity.getJdbcUsername());
            tutorial.setJdbcPassword(dbStoreEntity.getJdbcPassword());
            tutorial.setJdbcDriverClassName(dbStoreEntity.getJdbcDriverClassName());
            return ResponseEntity.ok(dbStoreRepository.save(tutorial));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "插入数据库连接", description = "插入数据库连接")
    @PostMapping("/db")
    public ResponseEntity<DbStoreEntity> insert(@RequestBody DbStoreEntity dbStoreEntity) {
        try {
            return ResponseEntity.ok(dbStoreRepository.save(dbStoreEntity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "获取所有数据库连接", description = "获取注册过的所有数据库信息")
    @GetMapping("/db/all")
    public ResponseEntity<Iterable<DbStoreEntity>> getAll() {
        return ResponseEntity.ok(dbStoreRepository.findAll());
    }

    @Operation(summary = "导出配置", description = "导出所有的数据库配置信息")
    @GetMapping("/db/exportConfig")
    public ResponseEntity<Iterable<DbStoreEntity>> exportConfig() {
        return ResponseEntity.ok(dbStoreRepository.findAll());
    }

    @Operation(summary = "导入配置", description = "导入所有的数据库配置")
    @PostMapping("/db/importConfig")
    public ResponseEntity<List<ImportConfigResultView>> importConfig(@RequestBody List<DbStoreEntity> storeEntities) {
        return ResponseEntity.ok(dbStoreServer.importConfig(storeEntities));
    }


}
