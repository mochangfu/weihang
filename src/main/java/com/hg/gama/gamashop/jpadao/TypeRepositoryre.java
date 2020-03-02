package com.hg.gama.gamashop.jpadao;


import com.hg.gama.gamashop.model.Type;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TypeRepositoryre extends CrudRepository<Type, Integer> {

     List<Type> findAll();
}
