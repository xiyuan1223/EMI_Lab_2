package com.emigroup.web.vo;

import java.util.List;

public class Cv {
    private int id;
    private String cardid;


    private String username;
    private String position;
    private String imgpath;
    private String email;
    private String userdes;
    private List<Paper> papers;
    private String award;

    private String interest;
    private String office;

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImgPath() {
        return imgpath;
    }

    public void setImgPath(String imgPath) {
        this.imgpath = imgPath;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserdes() {
        return userdes;
    }

    public void setUserdes(String userdes) {
        this.userdes = userdes;
    }

    public List<Paper> getPapers() {
        return papers;
    }

    public void setPapers(List<Paper> papers) {
        this.papers = papers;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
}
