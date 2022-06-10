package com.spring.story.demo.cos.web;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Carter.li
 * @createtime 2022/6/10 11:25
 */
@Slf4j
@RestController
public class CosController {


    @GetMapping(path = "/test/bucket")
    public String linkCosBucket() {

        //we
        initCosClient("zg-1251x124017", "ap-shanghai", "xxx", "xxx");
//        initCosClient("x-1251124017","ap-shanghai","xxx","xxx");

        //relex
//        initCosClient("zg-12511x24017","ap-shanghai","xxx","xxx");

        //yeting
//        initCosClient("sleyoar1231-1308588921","ap-guangzhou","xxx","xxx");

        return "success";

    }

    @GetMapping(path = "/test/bucketDir")
    public String linkCosBucketDir() {

        initCosClient("zg-1251124017/cycube/", "ap-shanghai", "xxxxxxxxx", "yyyyyyyy");

        return "success";

    }


    private COSClient initCosClient(String bucketName, String region, String securityId, String secretKey) {
        // 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(securityId, secretKey);
        // 设置bucket的区域, COS地域的简称请参照
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        // 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        // 判断bucket是否存在
        boolean b = cosClient.doesBucketExist(bucketName);
        if (!b) {
            log.info("bucket不存在！");
//            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
//            // 设置 bucket 的权限为 Private(私有读写), 其他可选有公有读私有写, 公有读写
//            createBucketRequest.setCannedAcl(CannedAccessControlList.Private);
//            Bucket bucketResult = cosClient.createBucket(createBucketRequest);
//            try {
//                log.info("初始化cosClient-创建bucket完成，bucket信息：{}", new ObjectMapper().writeValueAsString(bucketResult));
//            } catch (JsonProcessingException e) {
//                throw new RuntimeException(e);
//            }
        }
        return cosClient;
    }

}
