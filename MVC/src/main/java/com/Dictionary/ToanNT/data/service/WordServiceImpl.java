package com.Dictionary.ToanNT.data.service;

import com.Dictionary.ToanNT.data.model.User;
import com.Dictionary.ToanNT.data.model.Word;
import com.Dictionary.ToanNT.data.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WordServiceImpl implements WordService{
    @Autowired
    WordRepository wordRepository;

    //get all word
    public List<Word> getAll(){
        return wordRepository.findAll();
    }

    //update or insert word
    public boolean updateOrInsertWord(Word word){
        try {
            wordRepository.save(word);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //Login
    public User login(String username, String password){
        try {
            return wordRepository.login(username, password);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //delete word
    public boolean deleteWord(Integer id){
        try {
            wordRepository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //List word by type
    public List<Word> getWordByTypeFromDB(String type){
        try {
            List<Word> li = wordRepository.getListWordByType(type);
            System.out.println("Service: getWordByTypeDB");
            li.forEach(System.out::println);
            return li;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    List<String> myType = new ArrayList<>();

    //List type
    public List<String> getAllType(List<Word> list){
        try {
            Map<String, Long> myMap = list.parallelStream().collect(Collectors.groupingBy(Word::getType, Collectors.counting()));
            Set<String> set = myMap.keySet();
            for(String type: set){
                System.out.println(type);
                myType.add(type);
            }
            System.out.println("Service: getAllType");
            myType.forEach(System.out::println);

        } catch (Exception e){
            e.printStackTrace();
        }
        return myType;
    }

    //Import file Việt - Anh
    public boolean importFileVE(String path){
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = br.readLine();
            int dem = 0;
            while (line != null){
                try{
                    String arr[] = line.split(":");
                    if(arr.length > 1){
                        wordRepository.save(new Word(arr[0].trim(), arr[1].trim(), "Việt - Anh"));
                    }
                } catch (Exception ex){
                    ex.printStackTrace();
                }
                dem++;
                line = br.readLine();
            }
            System.out.println(dem);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //Import file Anh - Việt
    public boolean importFileEV(String path){
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = br.readLine();
            int dem = 0;
            String key = "";
            String mean = "";
            while (line != null){
                try {
                    if(line.contains("@")){
                        String ar[] = line.split("/");
                        if(ar.length > 1){
                            key = ar[0].substring(1,ar[0].length()).trim();
                            mean = ar[1];
                        }
                    }else{
                        mean+=line;
                    }

                    dem++;
                    line = br.readLine();
                    if(line.contains("@")) wordRepository.save(new Word(key,mean,"Anh - Việt"));
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            wordRepository.save(new Word(key,mean,"Anh - Việt"));
            System.out.println(dem);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //List word by key
    @Override
    public List<Word> getWordByKey(List<Word> listWord, String key) {
        try {
            List<Word> li = listWord.parallelStream().filter(e -> e.getKey().contains(key)).collect(Collectors.toList());
            return li;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //List word by type (from list of controller
    @Override
    public List<Word> getWordByType(List<Word> listWord, String type) {
        try {
            List<Word> li =  listWord.parallelStream().filter(e -> e.getType().equals(type)).collect(Collectors.toList());
            System.out.println("Service: getWordByType");
            li.forEach(System.out::println);
            return li;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //Get word by key (Admin)
    @Override
    public Word getOneWordByKey(List<Word> word, String key) {
        try {
            for(Word o : word){
                if(o.getKey().equals(key)) return o;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}