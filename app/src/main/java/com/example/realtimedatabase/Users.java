package com.example.realtimedatabase;

class Users {
    private String name,Mname,Dname,num,Mnum,Dnum,Hnum,address;
    public Users(){}

    public Users(String name,String Mname,String Dname,String num,String Mnum,String Dnum,String Hnum,String address){
        this.address=address;
        this.Dname=Dname;
        this.num=num;
        this.Mnum=Mnum;
        this.Dnum=Dnum;
        this.Hnum=Hnum;
        this.Mname=Mname;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMname() {
        return Mname;
    }

    public void setMname(String mname) {
        Mname = mname;
    }

    public String getDname() {
        return Dname;
    }

    public void setDname(String dname) {
        Dname = dname;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDnum() {
        return Dnum;
    }

    public void setDnum(String dnum) {
        Dnum = dnum;
    }

    public String getHnum() {
        return Hnum;
    }

    public void setHnum(String hnum) {
        Hnum = hnum;
    }

    public String getMnum() {
        return Mnum;
    }

    public void setMnum(String mnum) {
        Mnum = mnum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}


