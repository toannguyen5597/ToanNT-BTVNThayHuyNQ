package com.JPAdata.ToanNT.application.data.service;

import com.JPAdata.ToanNT.application.data.model.Type;
import com.JPAdata.ToanNT.application.data.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {
    @Autowired
    TypeRepository typeRepository;

    //Get all Type by name
    public List<Type> getTypeByName(String name) {
        return typeRepository.findByName(name);
    }
}
