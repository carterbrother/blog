package com.springbootpractice.demo.data.dict.service;

import com.springbootpractice.demo.data.dict.util.ShellExecutorUtil;
import org.springframework.stereotype.Service;

@Service
public class LocalDebugService {


    /**
     * 部署服务
     * @param appName 应用名字
     * @param imageName  镜像路径
     * @param envName 环境名称
     */
    public void deployService(String appName, String imageName, String envName) {

        int nodePort = startNodePortService(appName,envName);

        String nodeIP = getNodeIP(envName);

       String yamlPath = generateYaml(appName,imageName,nodeIP,nodePort);

       deployOnK8s(envName,yamlPath);

    }

    private void deployOnK8s(String envName, String yamlPath) {

    }

    private String generateYaml(String appName, String imageName, String nodeIP, int nodePort) {
        return null;
    }

    private String getNodeIP(String envName) {

        ShellExecutorUtil.executeShell("",false);


        return null;
    }

    private int startNodePortService(String appName, String envName) {
        return 0;
    }
}
