package com.lyh.entity;

import java.io.Serializable;

public class Student implements Serializable {
    private Long id;

    private String name;

    private String img;

    private String intro;

    private Long salary;

    private Boolean employmentStatus;

    private Boolean excellenceDegree;

    private Long createAt;

    private Long updateAt;

    private String updateBy;

    private String position;

    private String professionName;
    public Student(){}
    public Student(Long id,String name,String img,String intro,
                   Long salary,Boolean employmentStatus,Boolean excellenceDegree,
                   Long createAt,Long updateAt,String updateBy,String position,String professionName){
        this.id = id;
        this.name = name;
        this.img = img;
        this.intro = intro;
        this.salary = salary;
        this.employmentStatus = employmentStatus;
        this.excellenceDegree = excellenceDegree;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.updateBy = updateBy;
        this.position = position;
        this.professionName = professionName;
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
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Boolean getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(Boolean employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public Boolean getExcellenceDegree() {
        return excellenceDegree;
    }

    public void setExcellenceDegree(Boolean excellenceDegree) {
        this.excellenceDegree = excellenceDegree;
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
        this.updateBy = updateBy;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", intro='" + intro + '\'' +
                ", salary=" + salary +
                ", employmentStatus=" + employmentStatus +
                ", excellenceDegree=" + excellenceDegree +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", updateBy='" + updateBy + '\'' +
                ", position='" + position + '\'' +
                ", professionName='" + professionName + '\'' +
                '}';
    }
}
