package com.lyh.pojo;

import java.io.Serializable;

public class Number implements Serializable {
    double n1;
    double n2;
    public Number(){}
    public Number(double n1,double n2){
        this.n1 = n1;
        this.n2 = n2;
    }

    public double getN1() {
        return n1;
    }

    public void setN1(double n1) {
        this.n1 = n1;
    }

    public double getN2() {
        return n2;
    }

    public void setN2(double n2) {
        this.n2 = n2;
    }

    @Override
    public String toString() {
        return "Number{" +
                "n1=" + n1 +
                ", n2=" + n2 +
                '}';
    }
}
