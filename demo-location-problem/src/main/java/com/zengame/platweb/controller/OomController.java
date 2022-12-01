package com.zengame.platweb.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zengame.platweb.client.OomRequest;
import com.zengame.platweb.service.OomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 内存溢出接口模拟
 * @author Carter.li
 * {@code @createdAt:}: 2022/11/30  14:30
 **/
@RestController
@RequiredArgsConstructor
public class OomController {

    private final OomService oomService;

    @GetMapping("/oom")
    public JSONObject oom(@RequestBody OomRequest req){
        oomService.oom(req.getThreadNum(), req.getObjNum());
        JSONObject res = new JSONObject();
        res.putOnce("threadNum",req.getThreadNum());
        res.putOnce("objNum",req.getObjNum());
        return res;
    }

}
