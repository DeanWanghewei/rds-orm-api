package org.wei.rds.orm.api.controller;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wei.rds.orm.api.entity.DataDuplicateEntity;
import org.wei.rds.orm.api.model.CreateDataModel;
import org.wei.rds.orm.api.model.DuplicateCheckModel;
import org.wei.rds.orm.api.model.QueryDataModel;
import org.wei.rds.orm.api.repository.DataDuplicateRepository;
import org.wei.rds.orm.api.server.DataServer;
import org.wei.rds.orm.api.util.MD5Util;
import org.wei.rds.orm.api.view.DuplicateCheckView;
import org.wei.rds.orm.api.view.QueryDataView;
import org.wei.rds.orm.api.view.ResView;

/**
 * @description:
 * @author: deanwanghewei@gmail.com
 * @date: 2024年04月29日 17:20
 */
@Controller
public class DataController {
    private static final Logger logger = LoggerFactory.getLogger(DataController.class);
    @Resource
    private DataServer dataServer;
    @Resource
    private DataDuplicateRepository dataDuplicateRepository;


    /**
     * 插入数据
     *
     * @param dbName 存储的数据库别名
     * @param data   数据内容标签
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/data/{dbName}/insert")
    public ResponseEntity<ResView> insertData(@PathVariable("dbName") String dbName, @RequestBody CreateDataModel data) {
        return ResponseEntity.ok(dataServer.insertData(dbName, data));
    }

    /**
     * 查询表的数据
     *
     * @param dbName 存储的数据库别名
     * @param query  查询的标签
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/data/{dbName}/query")
    public ResponseEntity<QueryDataView> queryData(@PathVariable("dbName") String dbName, @RequestBody QueryDataModel query) {
        return ResponseEntity.ok(dataServer.queryData(dbName, query));
    }

    @ResponseBody
    @PostMapping(value = "/data/duplicateCheck")
    public ResponseEntity<DuplicateCheckView> duplicateCheck(@RequestBody DuplicateCheckModel checkModel) {
        DuplicateCheckView view = new DuplicateCheckView();
        try {
            DataDuplicateEntity newOne = new DataDuplicateEntity(checkModel.getSource(), MD5Util.getMD5(checkModel.getValue()));
            dataDuplicateRepository.save(newOne);
        } catch (Exception e) {
            logger.warn("duplicateCheck error ", e);
            view.setValid(false);
        }
        return ResponseEntity.ok(view);
    }
}
