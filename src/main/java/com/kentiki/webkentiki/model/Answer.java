package com.kentiki.webkentiki.model;

import lombok.Data;

@Data
public class Answer {

    public int day;
    public int month;
    public int year;

    public void setDayNull() {
        day = 0;
    }

    public void setMonthNull() {
        month = 0;
    }


    public void setYearNull() {
        year = 0;
    }

    public void resetDate(){
        setDayNull();
        setYearNull();
        setMonthNull();
    }
}