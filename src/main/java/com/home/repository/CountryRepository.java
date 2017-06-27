package com.home.repository;

import com.home.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nazar on 25.06.2017.
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
}
