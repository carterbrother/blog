package com.spring.storey.demo.completeable_future.controller;

import com.spring.storey.demo.completeable_future.app.MoveDataExe;
import com.spring.storey.demo.completeable_future.app.MoveDataExe2;
import com.spring.storey.demo.completeable_future.client.dto.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final MoveDataExe moveDataExe;
    private final MoveDataExe2 moveDataExe2;

    @GetMapping("/moveData")
    public R<String> moveData(String appId){
        return moveDataExe.execute(appId);
    }


    @GetMapping("/moveData2")
    public R<String> moveData2(String appId){
        return moveDataExe2.execute(appId);
    }

}
