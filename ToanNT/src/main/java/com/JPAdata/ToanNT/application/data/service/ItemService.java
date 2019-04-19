package com.JPAdata.ToanNT.application.data.service;

import com.JPAdata.ToanNT.application.data.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;
}
