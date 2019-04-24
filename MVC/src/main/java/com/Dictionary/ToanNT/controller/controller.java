package com.Dictionary.ToanNT.controller;

import com.Dictionary.ToanNT.data.model.User;
import com.Dictionary.ToanNT.data.model.Word;
import com.Dictionary.ToanNT.data.service.WordServiceImpl;
import com.Dictionary.ToanNT.viewmodel.ListDetail;
import com.Dictionary.ToanNT.viewmodel.WordDetail;
import org.modelmapper.ModelMapper;
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

    List<Word> list;

    //@GetMapping("/getall")
    public List<Word> getAll(){
        list = wordService.getAll();
        return list;
    }
    User user;

//    @GetMapping("/import/{path}")
//    public void importFile(@PathVariable("path")String path){
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(path+"/"+"anhviet.txt"));
//            String line = br.readLine();
//            int dem = 0;
//            String key = "";
//            String mean = "";
//            while(line != null){

//                System.out.println(line);
//                try {
//                    if(line.contains("@")){
//                        if(dem > 2){
//                            Word word = new Word(key,mean,"Anh - Việt");
////                            wordRepository.save(word);
//                            System.out.println(word);
//                        }
//                        String ar[] = line.split(" ");
//                        key = ar[0].substring(1,ar[0].length());
//                        mean = line.substring(ar[0].length(), line.length());
//                    }
//                    mean += line;
//                    dem++;
//                    dem++;
//                    line = br.readLine();
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//            System.out.println(dem);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    //update word
    @PostMapping("/update")
    public boolean updateWord(@RequestBody Word word){
        try {
            if(word.getKey() != null && word.getMean() != null && word.getType() != null){
                wordService.updateOrInsertWord(word);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //Get page index
    @GetMapping("/")
    public String getIndex(Model model){
        getAll();
        ListDetail vm = listDetail();
        model.addAttribute("vm",vm);
        return "mydirect";
    }

    //Get listDetail (map WordDetail - Word)
    public ListDetail listDetail(){
        ModelMapper modelMapper = new ModelMapper();
        ListDetail vm = new ListDetail();
        ArrayList<WordDetail> wordDetails = new ArrayList<>();
        List<Word> word = list;
        for(Word word1 : word) {
            WordDetail wordDetail = new WordDetail();
            wordDetail = modelMapper.map(word1, WordDetail.class);
            wordDetails.add(wordDetail);
        }
        vm.setListWordDetail(wordDetails);
        return vm;
    }

    //get page login
    @RequestMapping(value = "/log", method = RequestMethod.POST)
    public String getLogin(){
        return "login";
    }

    //Get page search
    @RequestMapping(value = "/direct", method = RequestMethod.POST)
        public String login(Model model, @RequestParam("username") String username, @RequestParam("password") String password, Word word){
        user = wordService.login(username, password);
        if(user != null){
            ListDetail vm = listDetail();
            model.addAttribute("vm", vm);
            model.addAttribute("user",user);
            model.addAttribute("searchW",word);
            return "mydirect";
        }
        return "login";
    }
}
