package com.hg.gama.gamashop.jpadao;

import com.hg.gama.gamashop.model.Goods;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods,Integer>, JpaSpecificationExecutor<Goods> {

    List<Goods> findAllByType(Pageable pageable, Integer type  );

}
