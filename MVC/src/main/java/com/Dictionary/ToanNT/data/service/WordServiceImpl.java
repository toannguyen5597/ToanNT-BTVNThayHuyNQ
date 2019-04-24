package com.Dictionary.ToanNT.data.service;

import com.Dictionary.ToanNT.data.model.User;
import com.Dictionary.ToanNT.data.model.Word;
import com.Dictionary.ToanNT.data.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordServiceImpl implements WordService{
    @Autowired
    WordRepository wordRepository;

    public List<Word> getAll(){
        return wordRepository.findAll();
    }

    public boolean updateOrInsertWord(Word word){
        if(word.getKey() != null && word.getMean()!=null) wordRepository.save(word);
        return true;
    }

    public User login(String username, String password){
        try {
            return wordRepository.login(username, password);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

//    public boolean login(String user, String pass){
//        try {
//            if("admin".equals(user) && "admin".equals(pass)) return true;
//            else return false;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return false;
//    }

    public boolean deleteWord(Integer id){
        try {
            wordRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<Word> getWordByKey(List<Word> list, String key) {
        try {
            List<Word> myWord = list.stream().filter(e -> e.getKey().contains(key)).collect(Collectors.toList());
            return myWord;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
