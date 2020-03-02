/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 25414
 * @create 2019/5/1
 * @since 1.0.0
 */
package com.hg.gama.gamashop.jpadao;

import com.hg.gama.gamashop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryre extends JpaRepository<User, Integer> {
    public User findByUsername(String userName);
    public User findUserById(Integer id);
}
