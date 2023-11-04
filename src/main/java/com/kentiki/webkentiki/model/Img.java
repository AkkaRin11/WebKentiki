package com.kentiki.webkentiki.model;

import lombok.Data;

@Data
public class Img {
    String date;

    public Img(){}

    public Img(String date){
        this.date = date;
    }

}
