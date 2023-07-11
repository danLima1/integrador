package com.projeto.integrador.model;

import com.projeto.integrador.Entity.Liker;

public class LikerModel {

    private String username;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static LikerModel mapLikerToModel(Liker liker){
        LikerModel likeModel = new LikerModel();
        likeModel.setUsername(liker.getUser().getUsername());
        return likeModel;
    }
}