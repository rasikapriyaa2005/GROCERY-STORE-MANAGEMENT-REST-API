package com.hello.rasikapriya.Repositories;

import com.hello.rasikapriya.Models.MealPlan;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {

    
    List<MealPlan> findByUserId(Long userId);

   
    @Query("SELECT m FROM MealPlan m ORDER BY " +
           "CASE WHEN :order = 'asc' THEN m.title END ASC, " +
           "CASE WHEN :order = 'desc' THEN m.title END DESC")
    List<MealPlan> findAllSorted(String order, Sort sort);
    @Modifying
 
    @Query("UPDATE MealPlan m SET m.title = :title, m.startDate = :startDate, m.endDate = :endDate, m.userId = :userId, m.recipeIds = :recipeIds WHERE m.id = :id")
    void updateMealPlan(Long id, String title, String startDate, String endDate, Long userId, List<Long> recipeIds);


    @Modifying

    @Query("DELETE FROM MealPlan m WHERE m.id = :id")
    void deleteMealPlanById(Long id);
}

