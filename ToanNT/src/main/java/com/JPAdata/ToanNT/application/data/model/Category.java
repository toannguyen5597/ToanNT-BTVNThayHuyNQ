package com.JPAdata.ToanNT.application.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "tbl_category")
public class Category {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    private String name;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name="type_id")
    private Type type;

    @OneToMany(mappedBy = "category")
    private List<ItemCategory> itemCategories = new ArrayList<ItemCategory>();

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @JsonIgnore
    public List<ItemCategory> getItemCategories() {
        return itemCategories;
    }

    public void setItemCategories(List<ItemCategory> itemCategories) {
        this.itemCategories = itemCategories;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", \nname='" + name + '\'' +
                ", \ntype=" + type +
                '}';
    }
}
