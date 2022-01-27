package com.food.api.repository;

import com.food.api.domain.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

    @Query(value = "SELECT fi " +
                   "FROM FoodItem fi " +
                   "WHERE fi.name LIKE :searchTerm " +
                   "OR fi.description LIKE :searchTerm " +
                   "OR fi.vendor.name LIKE :searchTerm " +
                   "OR fi.price LIKE :searchTerm")
    List<FoodItem> searchByTerm(@Param("searchTerm") String searchTerm);
}
