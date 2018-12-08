package com.lyh.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class Work {
    private Long id;
    @NotBlank(message = "不能为空")
    private String name;
    @NotNull(message = "状态不能为空")
    private Short status;
    @NotNull(message = "一级标题id不能为空")
    private Long firstId;
    @NotNull(message = "二级标题id不能为空")
    private Long secondId;
    @NotBlank(message = "作品简介不能为空")
    private String introduction;
    @NotBlank(message = "缩略图不能为空")
    private String thumbnail;
    @NotBlank(message = "URL不能为空")
    private String url;
    @NotBlank(message = "介绍文章不能为空")
    private String content;
    @NotBlank(message = "图片不能为空")
    private String picture;
    @NotNull(message = "创建时间不能为空")
    private Long createAt;
    @NotNull(message = "修改时间不能为空")
    private Long updateAt;
    @NotBlank(message = "编辑人不能为空")
    private String updateBy;
    @NotBlank(message = "一级标题名称不能为空")
    private String firstName;
    @NotBlank(message = "二级分类名称不能为空")
    private String secondName;

    public Work(Long id, String name, Short status, Long firstId, Long secondId, String introduction, String thumbnail, String url, String content, String picture, Long createAt, Long updateAt, String updateBy, String firstName, String secondName) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.firstId = firstId;
        this.secondId = secondId;
        this.introduction = introduction;
        this.thumbnail = thumbnail;
        this.url = url;
        this.content = content;
        this.picture = picture;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.updateBy = updateBy;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public Work() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Long getFirstId() {
        return firstId;
    }

    public void setFirstId(Long firstId) {
        this.firstId = firstId;
    }

    public Long getSecondId() {
        return secondId;
    }

    public void setSecondId(Long secondId) {
        this.secondId = secondId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail == null ? null : thumbnail.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public Long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName == null ? null : secondName.trim();
    }

    @Override
    public String toString() {
        return "Work{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", firstId=" + firstId +
                ", secondId=" + secondId +
                ", introduction='" + introduction + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                ", picture='" + picture + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", updateBy='" + updateBy + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }
}