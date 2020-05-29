package com.example.realtimedatabase;

public class Grades {
    private String namestu,reva,sub,grade;
    public Grades(){}

    public Grades(String namestu,String reva,String sub,String grade){
        this.grade=grade;
        this.namestu=namestu;
        this.reva=reva;
        this.sub=sub;
    }

    public String getNamestu() {
        return namestu;
    }

    public void setNamestu(String namestu) {
        this.namestu = namestu;
    }

    public String getReva() {
        return reva;
    }

    public void setReva(String reva) {
        this.reva = reva;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
