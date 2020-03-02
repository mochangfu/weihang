/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 25414
 * @create 2019/5/2
 * @since 1.0.0
 */
package com.hg.gama.gamashop.jpadao;

import com.hg.gama.gamashop.model.GoodsCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialCombinationPriceRepostory  extends JpaRepository<GoodsCase,Integer> {

    List<GoodsCase> findAllByGoodsId(Integer goodsId);
}
