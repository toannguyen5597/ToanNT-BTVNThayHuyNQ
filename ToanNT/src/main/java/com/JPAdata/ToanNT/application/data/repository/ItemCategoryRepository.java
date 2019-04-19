package com.JPAdata.ToanNT.application.data.repository;

import com.JPAdata.ToanNT.application.data.model.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Integer> {
    //Query get all itemcate by category id
    @Query("select c from tbl_itemcat c where c.category.id = :catId")
    List<ItemCategory> findByCategoryId(@Param("catId") int catId);
}
