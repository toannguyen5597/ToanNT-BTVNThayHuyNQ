package com.JPAdata.ToanNT.application.data.repository;

import com.JPAdata.ToanNT.application.data.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
