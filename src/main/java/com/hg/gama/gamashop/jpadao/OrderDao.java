/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 25414
 * @create 2019/5/1
 * @since 1.0.0
 */
package com.hg.gama.gamashop.jpadao;

import com.hg.gama.gamashop.model.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

public interface OrderDao extends JpaRepository<OrderInfo, Integer> , JpaSpecificationExecutor<OrderInfo> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update order_info o set o.status =?1,o.updated= ?2 where p.id = ?3",nativeQuery = true)
    int updateStatusById(String status, Date updateTiem, Integer id);

}
