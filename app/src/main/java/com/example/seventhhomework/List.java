package com.example.seventhhomework;

public class List {
    private String name;
    private String time;
    private String content;

    public List(String name, String time, String content) {
        this.name = name;
        this.time = time;
        this.content = content;
    }
    public void getName(String name){
        this.name = name;
    }
    public void getTime(String time){
        this.time = time;
    }
    public void getContent(String content){
        this.content = content;
    }
}
