package com.web.demo.reposDev;

import com.web.demo.entities.LaserSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaserSearchRepository extends JpaRepository<LaserSearch, Long> {
    List<LaserSearch> findByServiceIdContaining(String title);
}
