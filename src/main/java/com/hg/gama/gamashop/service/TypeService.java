package com.hg.gama.gamashop.service;


import com.hg.gama.gamashop.jpadao.TypeRepositoryre;
import com.hg.gama.gamashop.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TypeService {

    @Autowired
    TypeRepositoryre typeRepositoryre;
    public List<Type> getAllGoodsType(){
       return typeRepositoryre.findAll();
    }

}
