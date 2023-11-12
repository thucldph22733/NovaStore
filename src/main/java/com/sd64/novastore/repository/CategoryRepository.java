package com.sd64.novastore.repository;

import com.sd64.novastore.model.Brand;
import com.sd64.novastore.model.CartDetail;
import com.sd64.novastore.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllByAndStatusOrderByIdDesc(Integer status);

//    Page<Category> findAllByAndStatusOrderByIdDesc(Pageable pageable, Integer status);

    Page<Category> findAllByStatusOrderByUpdateDateDesc(Pageable pageable, Integer status);
    Page<Category> findAllByNameContainsAndStatusOrderByIdDesc(String name, Integer status, Pageable pageable);

    Category findByName(String name);
}
