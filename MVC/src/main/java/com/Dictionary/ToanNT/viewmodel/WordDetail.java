package com.Dictionary.ToanNT.viewmodel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class WordDetail {

    private int id;
    private String key;
    private String mean;
    private String type;

    public WordDetail(int id, String key, String mean, String type) {
        this.id = id;
        this.key = key;
        this.mean = mean;
        this.type = type;
    }

    public WordDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
