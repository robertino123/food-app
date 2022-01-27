package com.food.api.repository;

import com.food.api.domain.FoodOrder;
import com.food.api.dto.FoodOrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
    Page<FoodOrderDTO> findByUserId(Long userId, Pageable pageable);
}