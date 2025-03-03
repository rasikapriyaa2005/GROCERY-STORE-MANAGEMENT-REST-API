package com.hello.rasikapriya.Services;

import com.hello.rasikapriya.Models.Recipe;
import com.hello.rasikapriya.Repositories.RecipeRepo;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepo recipeRepo;

    public RecipeService(RecipeRepo recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    public void addRecipes(List<Recipe> recipes) {
        recipeRepo.saveAll(recipes);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepo.getAllRecipes();
    }

    public List<Recipe> searchRecipesByName(String name) {
        return recipeRepo.searchByName(name);
    }

    public List<Recipe> getRecipesByCategory(String category) {
        return recipeRepo.findByCategory(category);
    }

    public List<Recipe> getRecipesByIngredient(String ingredient) {
        return recipeRepo.findByIngredient(ingredient);
    }

    public List<Recipe> getRecipesByCookingTime(int cookingTime) {
        return recipeRepo.findByCookingTimeLessThanEqual(cookingTime);
    }

    public List<Recipe> getSortedRecipes(String field) {
        return recipeRepo.findAll(Sort.by(field));
    }

    public Page<Recipe> getPaginatedRecipes(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return recipeRepo.findAll(pageable);
    }

    public Page<Recipe> getPaginatedAndSortedRecipes(int pageNumber, int pageSize, String field) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(field));
        return recipeRepo.findAll(pageable);
    }
    public Recipe updateRecipe(Long id, Recipe recipe) {
        return recipeRepo.findById(id)
                .map(existingRecipe -> {
                    existingRecipe.setName(recipe.getName());
                    existingRecipe.setCategory(recipe.getCategory());
                    existingRecipe.setIngredients(recipe.getIngredients());
                    existingRecipe.setCookingTime(recipe.getCookingTime());
                   
                    return recipeRepo.save(existingRecipe);
                })
                .orElse(null);
    }
    
    public void deleteRecipeById(Long id) {
        recipeRepo.deleteById(id);
    }
    
    
}
