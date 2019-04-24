package com.Dictionary.ToanNT.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "tbl_word")
public class Word {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(name = "kei")
    private String key;

    @Column(name = "mean")
    private String mean;

    @Column(name = "type")
    private String type;

    public Word() {
    }

    public Word(String key, String mean, String type) {
        this.key = key;
        this.mean = mean;
        this.type = type;
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

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", mean='" + mean + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}