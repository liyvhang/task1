package com.lyh.entity;

public class COSBean {
    private String secretId;
    private String secretKey;
    private String bucketName;
    private String regionName;

    public COSBean() {
    }

    public COSBean(String secretId, String secretKey, String bucketName,String regionName) {
        this.secretId = secretId;
        this.secretKey = secretKey;
        this.bucketName = bucketName;
        this.regionName = regionName;
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public String toString() {
        return "COSBean{" +
                "secretId='" + secretId + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", bucketName='" + bucketName + '\'' +
                ", regionName='" + regionName + '\'' +
                '}';
    }
}
