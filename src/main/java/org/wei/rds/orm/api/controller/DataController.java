package org.wei.rds.orm.api.controller;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wei.rds.orm.api.model.CreateDataModel;
import org.wei.rds.orm.api.server.DataServer;

/**
 * @description:
 * @author: deanwanghewei@gmail.com
 * @date: 2024年04月29日 17:20
 */
@Controller
public class DataController {
    @Resource
    private DataServer dataServer;

    @ResponseBody
    @PostMapping(value = "/data/{dbName}/insert")
    public void insertData(@PathVariable("dbName") String dbName, @RequestBody CreateDataModel data) {
        dataServer.insertData(dbName, data);
    }
}
