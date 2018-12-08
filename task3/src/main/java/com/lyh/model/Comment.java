package com.lyh.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class Comment {
    @NotNull(message = "id不能为空")
    private Long id;
    @NotBlank(message = "标题不能为空")
    private String title;
    @NotBlank(message = "状态不能为空")
    private Short status;
    @NotNull(message = "创建时间不能为空")
    private Long createAt;
    @NotNull(message = "修改时间不能为空")
    private Long updateAt;
    @NotBlank(message = "编辑人不能为空")
    private String updateBy;
    @NotBlank(message = "评论内容不能为空")
    private String content;

    public Comment(Long id, String title, Short status, Long createAt, Long updateAt, String updateBy, String content) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.updateBy = updateBy;
        this.content = content;
    }

    public Comment() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", updateBy='" + updateBy + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}