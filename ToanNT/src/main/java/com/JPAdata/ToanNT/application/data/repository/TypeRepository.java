package com.JPAdata.ToanNT.application.data.repository;

import com.JPAdata.ToanNT.application.data.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {
    //Query get all type by type name
    @Query("select u from tbl_type u where u.name = :name")
    List<Type> findByName(@Param("name") String name);
}
