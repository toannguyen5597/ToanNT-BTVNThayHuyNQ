package com.Dictionary.ToanNT.data.repository;

import com.Dictionary.ToanNT.data.model.User;
import com.Dictionary.ToanNT.data.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {
    @Query("select use from tbl_user use where use.username = :username and use.password = :password")
    User login(@Param("username") String user, @Param("password") String pass);

    @Query("select wor from tbl_word wor where wor.type = : type")
    List<Word> getListWordByType(@Param("type") String value);

    @Query("select wor from tbl_word wor where wor.key = :key")
    Word getWordByKey(@Param("key") String key);

}
