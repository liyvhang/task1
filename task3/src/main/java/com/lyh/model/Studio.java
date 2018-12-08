package com.lyh.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class Studio {
    private Long id;
    @NotBlank(message = "名称不能为空")
    private String name;
    @NotNull(message = "状态不能为空")
    private Boolean status;
    @NotBlank(message = "图片不能为空")
    private String picture;
    @NotBlank(message = "正文不能为空")
    private String about;
    @NotNull(message = "创建时间不能为空")
    private Long createAt;
    @NotNull(message = "修改时间不能为空")
    private Long updateAt;
    @NotBlank(message = "编辑人不能为空")
    private String updateBy;

    public Studio(Long id, String name, Boolean status, String picture, String about, Long createAt, Long updateAt, String updateBy) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.picture = picture;
        this.about = about;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.updateBy = updateBy;
    }

    public Studio() {
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about == null ? null : about.trim();
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
    public String toString(){
        return "studio[id= "+id+",name= "+name+",status="+status+",picture= "+picture+",about="+about+",createAt="+createAt+",updateAt="+updateBy+",updateBy="+updateBy+"]";
    }
}