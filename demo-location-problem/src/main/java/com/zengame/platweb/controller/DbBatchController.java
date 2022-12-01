package com.zengame.platweb.controller;

import com.zengame.platweb.service.DbBatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据库批量执行问题模拟
 *
 * @author Carter.li
 * {@code @createdAt:}: 2022/11/30  14:30
 **/
@RestController
@RequiredArgsConstructor
public class DbBatchController {

    private final DbBatchService dbBatchService;

    @GetMapping("/db")
    public String dbBatch() {
        dbBatchService.db();
        return "db batch test !";
    }

}
