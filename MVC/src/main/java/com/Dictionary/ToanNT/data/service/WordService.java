package com.Dictionary.ToanNT.data.service;

import com.Dictionary.ToanNT.data.model.User;
import com.Dictionary.ToanNT.data.model.Word;

import java.util.List;

public interface WordService {
    List<Word> getWordByKey(List<Word> word, String key);
}
