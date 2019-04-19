package com.JPAdata.ToanNT.application.data.service;

import com.JPAdata.ToanNT.application.data.model.ItemCategory;
import com.JPAdata.ToanNT.application.data.repository.ItemCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCategoryService {
    @Autowired
    ItemCategoryRepository repository;

    //Get all ItemCategory by category id
    public List<ItemCategory> getByCat(int id) {
        return repository.findByCategoryId(id);
    }
}
