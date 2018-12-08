package com.lyh.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class First {
    @NotNull(message = "id不能为空")
    private Long id;
    @NotBlank(message = "一级标题名称不能为空")
    private String name;
    @NotNull(message = "状态不能为空")
    private Boolean status;
    @NotNull(message = "创建时间不能为空")
    private Long createAt;
    @NotNull(message = "编辑人不能为空")
    private Long updateAt;

    private String updateBy;

    public First(Long id, String name, Boolean status, Long createAt, Long updateAt, String updateBy) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.updateBy = updateBy;
    }

    public First() {
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
    public String toString() {
        return "First{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }
}