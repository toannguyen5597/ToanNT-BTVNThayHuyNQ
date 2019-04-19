package com.JPAdata.ToanNT.application.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "tbl_itemcat")
public class ItemCategory {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    public ItemCategory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @JsonIgnore
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ItemCategory{" +
                "id=" + id +
                ", item.id=" + item.getId() +
                ", item.name=" + item.getName() +
                ", category.id=" + category.getId() +
                ", category.name=" + category.getName() +
                '}';
    }
}
