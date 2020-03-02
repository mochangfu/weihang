package com.hg.gama.gamashop.jpadao;


import com.hg.gama.gamashop.model.GoodsCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface GoodsCaseDao extends JpaRepository<GoodsCase,Integer>, JpaSpecificationExecutor<GoodsCase> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update goods_case gc set gc.stock =gc.stock-?1 where gc.id = ?2",nativeQuery = true)
    Integer reduceAmount( Integer count,Integer id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update goods_case gc set gc.stock =gc.stock+?1 where gc.id = ?2",nativeQuery = true)
    Integer addAmount( Integer count,Integer id);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "select gc.stock from goods_case gc  where gc.id = ?1",nativeQuery = true)
    Integer getAmount(Integer id);
}
