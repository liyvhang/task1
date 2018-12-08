package com.lyh.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class Banner {
    @NotNull(message = "id不能为空")
    private Long id;
    @NotBlank(message = "封面不能为空")
    private String cover;
    @NotBlank(message = "URL不能为空")
    private String url;
    @NotNull(message = "U状态不能为空")
    private Short status;
    @NotNull(message = "创建时间不能为空")
    private Long createAt;
    @NotNull(message = "更新时间不能为空")
    private Long updateAt;

    private String updateBy;

    public Banner(Long id, String cover, String url, Short status, Long createAt, Long updateAt, String updateBy) {
        this.id = id;
        this.cover = cover;
        this.url = url;
        this.status = status;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.updateBy = updateBy;
    }

    public Banner() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
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
    @Override
    public String toString(){
        return "banner[id= "+id+",cover= "+cover+",url= "+url+",status="+status+",createAt="+createAt+",updateAt="+updateAt+",updateBy="+updateBy+"]";
    }
}