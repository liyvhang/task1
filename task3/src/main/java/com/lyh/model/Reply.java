package com.lyh.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class Reply {
    private Long id;
    @NotBlank(message = "昵称不能为空")
    private String nickname;
    @NotNull(message = "评论id不能为空")
    private Long commentId;
    @NotBlank(message = "评论内容不能为空")
    private String messageContent;
    @NotNull(message = "更新时间不能为空")
    private Long createAt;
    @NotNull(message = "编辑人不能为空")
    private Long updateAt;

    public Reply(Long id, String nickname, Long commentId, String messageContent, Long createAt, Long updateAt) {
        this.id = id;
        this.nickname = nickname;
        this.commentId = commentId;
        this.messageContent = messageContent;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Reply() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
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

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", commentId=" + commentId +
                ", messageContent='" + messageContent + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}