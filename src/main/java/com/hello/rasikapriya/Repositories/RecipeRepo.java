package com.hello.rasikapriya.Repositories;

import com.hello.rasikapriya.Models.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepo extends JpaRepository<Recipe, Long> {

    @Query("SELECT r FROM Recipe r")
    List<Recipe> getAllRecipes();

    @Query("SELECT r FROM Recipe r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Recipe> searchByName(String name);

    @Query("SELECT r FROM Recipe r WHERE LOWER(r.category) = LOWER(?1)")
    List<Recipe> findByCategory(String category);

    @Query("SELECT r FROM Recipe r WHERE LOWER(r.ingredients) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Recipe> findByIngredient(String ingredient);

    @Query("SELECT r FROM Recipe r WHERE r.cookingTime <= ?1")
    List<Recipe> findByCookingTimeLessThanEqual(int cookingTime);
    @Query("UPDATE Recipe r SET r.name = ?2, r.category = ?3, r.ingredients = ?4, r.cookingTime = ?5 WHERE r.id = ?1")
    void updateRecipe(Long id, String name, String category, String ingredients, int cookingTime);

    Page<Recipe> findAll(Pageable pageable);
}
