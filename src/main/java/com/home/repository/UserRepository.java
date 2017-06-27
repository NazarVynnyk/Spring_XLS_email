package com.home.repository;

import com.home.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Nazar on 25.06.2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    String FIND_ALL_ORDER_BY_COUNTRY_QUERY = "SELECT u " +
            "FROM User u  LEFT JOIN u.location l " +
            "ORDER BY l.country";

    @Query(FIND_ALL_ORDER_BY_COUNTRY_QUERY)
    List<User> getOrderedUsers();
}
