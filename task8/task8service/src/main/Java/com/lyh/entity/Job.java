package com.lyh.entity;

import java.io.Serializable;

public class Job implements Serializable {

    private Long id;

    private String developmentDirection;

    private String professionName;

    private Integer threshold;

    private Integer complexity;

    private String  growthCycle;

    private  String img;

    private  String intro;

    private Integer scarcity;

    private String salaryOne;

    private String salaryTwo;

    private String salaryFive;

    private String hint;

    private Long createAt;

    private Long updateAt;

    private String updateBy;

    private Integer learners;

    public Job(){}
    public Job( Long id,String developmentDirection,String professionName,Integer threshold,

                Integer complexity,String  growthCycle, String img,String intro,String salaryOne,

                String salaryTwo,String salaryFive,String hint,Long createAt, Long updateAt,String updateBy,
                Integer scarcity,Integer learners){
        this.id =id;
        this.developmentDirection = developmentDirection;
        this.professionName = professionName;
        this.threshold = threshold;
        this.complexity = complexity;
        this.growthCycle = growthCycle;
        this.img = img;
        this.intro = intro;
        this.salaryOne = salaryOne;
        this.salaryTwo= salaryTwo;
        this.salaryFive = salaryFive;
        this.hint = hint;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.updateBy = updateBy;
        this.scarcity = scarcity;
        this.learners  =learners;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDevelopmentDirection() {
        return developmentDirection;
    }

    public void setDevelopmentDirection(String developmentDirection) {
        this.developmentDirection = developmentDirection;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Integer getComplexity() {
        return complexity;
    }

    public void setComplexity(Integer complexity) {
        this.complexity = complexity;
    }

    public String getGrowthCycle() {
        return growthCycle;
    }

    public void setGrowthCycle(String growthCycle) {
        this.growthCycle = growthCycle;
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

    public Integer getScarcity() {
        return scarcity;
    }

    public void setScarcity(Integer scarcity) {
        this.scarcity = scarcity;
    }

    public String getSalaryOne() {
        return salaryOne;
    }

    public void setSalaryOne(String salaryOne) {
        this.salaryOne = salaryOne;
    }

    public String getSalaryTwo() {
        return salaryTwo;
    }

    public void setSalaryTwo(String salaryTwo) {
        this.salaryTwo = salaryTwo;
    }

    public String getSalaryFive() {
        return salaryFive;
    }

    public void setSalaryFive(String salaryFive) {
        this.salaryFive = salaryFive;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
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

    public Integer getLearners() {
        return learners;
    }

    public void setLearners(Integer learners) {
        this.learners = learners;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", developmentDirection='" + developmentDirection + '\'' +
                ", professionName='" + professionName + '\'' +
                ", threshold=" + threshold +
                ", complexity=" + complexity +
                ", growthCycle='" + growthCycle + '\'' +
                ", img='" + img + '\'' +
                ", intro='" + intro + '\'' +
                ", scarcity=" + scarcity +
                ", salaryOne='" + salaryOne + '\'' +
                ", salaryTwo='" + salaryTwo + '\'' +
                ", salaryFive='" + salaryFive + '\'' +
                ", hint='" + hint + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", updateBy=" + updateBy +
                ", learners=" + learners +
                '}';
    }
}
