package com.Dictionary.ToanNT.data.repository;

import com.Dictionary.ToanNT.data.model.User;
import com.Dictionary.ToanNT.data.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {
    @Query("select use from tbl_user use where use.username = :username and use.password = :password")
    User login(@Param("username") String user, @Param("password") String pass);
}
