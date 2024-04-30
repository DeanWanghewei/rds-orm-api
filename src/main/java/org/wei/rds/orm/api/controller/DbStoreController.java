package org.wei.rds.orm.api.controller;

import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.wei.rds.orm.api.entity.DbStoreEntity;
import org.wei.rds.orm.api.repository.DbStoreRepository;

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

    @PostMapping("/db")
    public ResponseEntity<DbStoreEntity> insert(@RequestBody DbStoreEntity dbStoreEntity) {
        try {
            return ResponseEntity.ok(dbStoreRepository.save(dbStoreEntity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/db/all")
    public ResponseEntity<Iterable<DbStoreEntity>> getAll() {
        return ResponseEntity.ok(dbStoreRepository.findAll());
    }


}
