package com.JPAdata.ToanNT.application.controller;

import com.JPAdata.ToanNT.application.data.model.Category;
import com.JPAdata.ToanNT.application.data.model.Item;
import com.JPAdata.ToanNT.application.data.model.ItemCategory;
import com.JPAdata.ToanNT.application.data.model.Type;
import com.JPAdata.ToanNT.application.data.service.CategoryService;
import com.JPAdata.ToanNT.application.data.service.ItemCategoryService;
import com.JPAdata.ToanNT.application.data.service.ItemService;
import com.JPAdata.ToanNT.application.data.service.TypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class Controller {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ItemService itemService;

    @Autowired
    TypeService typeService;

    @Autowired
    ItemCategoryService itemCategoryService;

    //get Category by type name
    @GetMapping("/listCateByType/{type}")
    public List<Category> list(@PathVariable("type") String type) {
        List<Type> type1 = typeService.getTypeByName(type);//Get all type by name
        List<Category> listCa = new ArrayList<>();
        for(Type myType: type1){
            List<Category> listCate = categoryService.findByType(myType.getId());//get all category by typeid
            for(Category myCate: listCate){
                listCa.add(myCate);
            }
        }

        listCa.forEach(System.out::println);
        return listCa;
    }

    //get all Item by category name
    @GetMapping("/listItemByCate/{name}")
    public List<Item> getByName(@PathVariable("name") String name) {
        ModelMapper modelMapper = new ModelMapper();
        List<Category> listCategory = categoryService.getByName(name);// get all category by name
        List<ItemCategory> listItemCate = new ArrayList<>();
        for(Category myCate: listCategory){
            List<ItemCategory> itemCategory = itemCategoryService.getByCat(myCate.getId());//get all itemcategory by category id
            for(ItemCategory itemCate: itemCategory){
                listItemCate.add(itemCate);
            }
        }
//        listItemCate.forEach(System.out::println);
        List<Item> list = new ArrayList<>();
        for(ItemCategory itemcate: listItemCate){
            list.add(modelMapper.map(itemcate.getItem(), Item.class));//get all Item by item of itemcate
        }
        list.forEach(System.out::println);
        return list;
    }


    //Update category
    @PostMapping("/update")
    public boolean update(@RequestBody Category category) {
        try {
            if(!String.valueOf(category.getId()).equals("") && !category.getName().equals("")){
                categoryService.update(category);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Get all category
    @GetMapping("/getAll")
    public List<Category> getOne() {
        return categoryService.getAll();
    }
}
