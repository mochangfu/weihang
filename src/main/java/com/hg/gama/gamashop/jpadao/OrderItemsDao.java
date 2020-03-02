/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 25414
 * @create 2019/5/1
 * @since 1.0.0
 */
package com.hg.gama.gamashop.jpadao;

import com.hg.gama.gamashop.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemsDao extends JpaRepository<OrderItems, Integer> {

    List<OrderItems> findAllByOrderId(Integer goodsId);

}
