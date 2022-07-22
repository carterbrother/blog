package com.spring.story.demosleuth;

import com.spring.story.demosleuth.config.R;
import com.spring.story.demosleuth.service.IndexService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author Carter.li
 * @createtime 2022/7/22 16:13
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class IndexController {

    private final IndexService indexService;

    @GetMapping("/")
    public String home(){
        log.info("home api !");
        String name = "carter:".concat(LocalDateTime.now().toString());
        indexService.execute(name);
        return "hello world!";
    }

    @GetMapping("/index")
    public String index(){
        log.info("index api !");
        String name = "carter:".concat(LocalDateTime.now().toString());
        return name;
    }

    @GetMapping("/r")
    public R<String> r(){
        log.info("r api !");
        String name = "carter:".concat(LocalDateTime.now().toString());
        return R.ofData(name);
    }
}
