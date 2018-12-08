package com.lyh.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class User {
    private Long id;
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "角色权限不能为空")
    private String role;
    @NotNull(message = "创建时间不能为空")
    private Long createAt;
    @NotNull(message = "修改时间不能为空")
    private Long updateAt;
    @NotBlank(message = "创建人不能为空")
    private String founder;

    public User(Long id, String userName, String password, String role, Long createAt, Long updateAt, String founder) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.founder = founder;
    }

    public User() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
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

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder == null ? null : founder.trim();
    }
    @Override
    public String toString(){
        return "user[id= "+id+",userName= "+userName+",password= "+password+",role="+role+",createAt="+createAt+",updateAt="+updateAt+",founder="+founder+"]";
    }
}