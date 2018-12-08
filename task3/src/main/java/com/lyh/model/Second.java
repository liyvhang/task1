package com.lyh.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class Second {
    private Long id;
    @NotBlank(message = "二级分类不能为空")
    private String name;
    @NotNull(message = "一级标题不能为空")
    private Long firstId;
    @NotNull(message = "状态不能为空")
    private Short status;
    @NotNull(message = "创建时间不能为空")
    private Long createAt;
    @NotNull(message = "修改时间不能为空")
    private Long updateAt;
    @NotBlank(message = "编辑人不能为空")
    private String updateBy;

    private String firstName;

    public Second(Long id, String name, Long firstId, Short status, Long createAt, Long updateAt, String updateBy, String firstName) {
        this.id = id;
        this.name = name;
        this.firstId = firstId;
        this.status = status;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.updateBy = updateBy;
        this.firstName = firstName;
    }

    public Second() {
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

    public Long getFirstId() {
        return firstId;
    }

    public void setFirstId(Long firstId) {
        this.firstId = firstId;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    @Override
    public String toString() {
        return "Second{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstId=" + firstId +
                ", status=" + status +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", updateBy='" + updateBy + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}