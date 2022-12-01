package com.zengame.platweb.controller;

import com.zengame.platweb.service.CpuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CPU打满接口模拟
 *
 * @author Carter.li
 * {@code @createdAt:}: 2022/11/30  14:30
 **/
@RestController
@RequiredArgsConstructor
public class Cpu100Controller {

    private final CpuService cpuService;

    @GetMapping("/cpu")
    public String cpu() {
        cpuService.cpu();
        return "cpu 100 test !";
    }

}
