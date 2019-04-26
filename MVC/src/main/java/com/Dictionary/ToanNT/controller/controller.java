package com.Dictionary.ToanNT.controller;

import com.Dictionary.ToanNT.data.model.User;
import com.Dictionary.ToanNT.data.model.Word;
import com.Dictionary.ToanNT.data.service.WordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller("/")
public class controller {
    @Autowired
    WordServiceImpl wordService;
    private List<Word> allWord;
    private List<Word> allWordByType;

    //Import file
    @RequestMapping(value = "/importfile", method = RequestMethod.GET)
    public String importFile(Model model ,Word path){
        try {
            if(path.getKey() == null){
                model.addAttribute("path", path);
            }
            else{
                model.addAttribute("path",path);
                if (path.getKey().contains("EV")) wordService.importFileEV(path.getKey());
                else if (path.getKey().contains("VE")) wordService.importFileVE(path.getKey());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "import";
    }

    String key="";

    //update or insert word
    @RequestMapping(value = "/updateword", method = RequestMethod.GET)
    public String updateWord(Word word, Model model) {
        System.out.println(word);
        System.out.println("key: "+key);
        model.addAttribute("data",allWord);
        if(word.getKey() == null){
            key=word.getKey();
            model.addAttribute("word",word);
        }
        else if(word.getMean() != null){
            key = word.getKey().substring(key.length()+1, word.getKey().length());
            word.setKey(key);
            key="";
            updateOrInsertWord(word);
            getAllWord();
            allType();
            word=new Word();
            model.addAttribute("data", allWord);
            model.addAttribute("word",word);
        }
        else{
            key = word.getKey();
            model.addAttribute("word",wordService.getOneWordByKey( allWord,word.getKey()));
        }

        return "update";
    }

    //delete word
    @RequestMapping(value = "/deleteword", method = RequestMethod.GET)
    public String deleteWord(Word word, Model model) {
        System.out.println(word);
        model.addAttribute("data",allWord);
        if(word.getKey() == null){
            model.addAttribute("word",word);
        }
        else if(word.getMean() != null){
            wordService.deleteWord(word.getId());
            getAllWord();
            allType();
            word=new Word();
            model.addAttribute("data", allWord);
            model.addAttribute("word",word);
        }
        else{
            model.addAttribute("word",wordService.getOneWordByKey( allWord,word.getKey()));
        }

        return "delete";
    }

    //Insert word
    @RequestMapping(value = "/insertword", method = RequestMethod.GET)
    public String insertWord(Word word, Model model) {
        System.out.println(word);
        model.addAttribute("types",allType);
        if(word.getKey() == null){
            model.addAttribute("word",word);
        }
        else{
            wordService.updateOrInsertWord(word);
            word=new Word();
            getAllWord();
            model.addAttribute("word",word);
        }

        return "insert";
    }

    //Get page index
    @GetMapping("/")
    public String getIndex() {
        getAllWord();
        allType();
        return "index";
    }


    //Get all word
    public void getAllWord(){
        try {
            allWord = wordService.getAll();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //SearchWord
    public List<Word> searchWordByKey(List<Word> list, String key){
        List<Word> li = wordService.getWordByKey(list, key);
        List<Word> l = new ArrayList<>();
        for (int i = 0;i < li.size() && i < 10; i++){
            l.add(li.get(i));
        }
        return l;
    }

    //Get all word by type from db
    public List<Word> getWordByTypeFromDB(String type){
        return wordService.getWordByTypeFromDB(type);
    }

    //Get all word by list allword
    public List<Word> getWordByTypeFromAllWord(String type){
        return wordService.getWordByType(allWord, type);
    }

    //Page search word
    @RequestMapping(value = "adminsearch", method = RequestMethod.GET)
    public String adminSearch(Model model, Word word, String type, Word updateWord){
        System.out.println("user: "+nowUser);
        model.addAttribute("user", nowUser);
        if(type == null || type.equals("Tất cả")){
            System.out.println("den day");

            allWordByType = allWord;
            //map all word
            model.addAttribute("data", allWord);
            //map all type
            model.addAttribute("types", allType);
        }
        else{
            //map all type
            model.addAttribute("types", allType);
            //map all word by type
            allWordByType = getWordByTypeFromAllWord(type);
            model.addAttribute("data", getWordByTypeFromAllWord(type));
        }
        if(word.getKey() == null){
            //map word searched when word = null
            model.addAttribute("myword", word);
            //map result search when word = null
            model.addAttribute("words", null);
        }else{
            //map word searched when word != null
            model.addAttribute("myword", word);

            //map result search when word != null
            model.addAttribute("words", searchWordByKey(allWordByType,word.getKey()));
        }
        return "admin";
    }

    //Update or insert word
    public void updateOrInsertWord(Word word){
        wordService.updateOrInsertWord(word);
    }
    User nowUser = new User();
    List<String> allType;
    public void allType(){
        allType=wordService.getAllType(allWord);
    }

    //get page login
    @RequestMapping(value = "/log")
    public String getLogin(Model model, User user){
        if(nowUser != null) nowUser = new User();
        if(user.getUsername() == null || user.getPassword() == null){
            System.out.println("user null: "+nowUser);
            model.addAttribute("user",user);
            return "login";
        }
        else{
            nowUser = wordService.login(user.getUsername(), user.getPassword());
            System.out.println("user null: "+nowUser);
            if(nowUser != null){
                model.addAttribute("user",nowUser);
                model.addAttribute("data", allWord);
                model.addAttribute("types", allType);
                model.addAttribute("myword", new Word());
                return "admin";
            }
            else{
                model.addAttribute("user", user);
                return "login";
            }
        }
    }
}