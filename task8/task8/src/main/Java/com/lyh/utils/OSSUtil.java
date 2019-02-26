package com.lyh.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.AccessControlList;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectResult;


import java.io.File;

public class OSSUtil {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    public OSSUtil() {
    }

    public OSSUtil(String endpoint, String accessKeyId, String accessKeySecret, String bucketName) {
        this.endpoint = endpoint;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.bucketName = bucketName;
    }

    //    上传文件
    public void OSSUpd(File file) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//        权限为私有或者私有写入的话需要设置权限
        AccessControlList acl = ossClient.getBucketAcl(bucketName);
        String name = file.getName();
//        bucketName  文件名 文件
        PutObjectResult pr = ossClient.putObject(bucketName, name, file);
//        获取对象，如果有Acl权限需要Acl权限参数
        createBucket(ossClient, bucketName, acl.getCannedACL());
        ossClient.shutdown();
    }

    //    获取对象
    public static void createBucket(OSSClient client, String bucketName, CannedAccessControlList acl) {

//         通过一个Bucket对象来创建
        CreateBucketRequest bucketObj = new CreateBucketRequest(bucketName);
        bucketObj.setBucketName(bucketName);
//        设置bucketObj访问权限acl
        bucketObj.setCannedACL(acl);
//        创建对象
        client.createBucket(bucketObj);

    }


}