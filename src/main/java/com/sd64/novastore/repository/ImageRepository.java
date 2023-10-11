package com.sd64.novastore.repository;

import com.sd64.novastore.model.Image;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findAllByStatus(Integer status);

    Page<Image> findAllByStatus(Pageable pageable, Integer status);
}
