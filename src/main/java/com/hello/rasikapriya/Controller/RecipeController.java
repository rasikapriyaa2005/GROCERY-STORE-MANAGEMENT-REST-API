package com.hello.rasikapriya.Controller;

import com.hello.rasikapriya.Models.Recipe;
import com.hello.rasikapriya.Services.RecipeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    // ✅ Add multiple recipes
    @PostMapping
    public ResponseEntity<String> addRecipes(@RequestBody List<Recipe> recipes) {
        recipeService.addRecipes(recipes);
        return ResponseEntity.ok("Recipes added successfully");
    }

    // ✅ Get all recipes
    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    // ✅ Get sorted recipes
    @GetMapping("/sort/{field}")
    public ResponseEntity<List<Recipe>> getSortedRecipes(@PathVariable String field) {
        return ResponseEntity.ok(recipeService.getSortedRecipes(field));
    }

    // ✅ Get paginated recipes
    @GetMapping("/page/{pageNumber}/size/{pageSize}")
    public ResponseEntity<Page<Recipe>> getPaginatedRecipes(
            @PathVariable int pageNumber, @PathVariable int pageSize) {
        return ResponseEntity.ok(recipeService.getPaginatedRecipes(pageNumber, pageSize));
    }

    // ✅ Get paginated & sorted recipes
    @GetMapping("/page/{pageNumber}/size/{pageSize}/sort/{field}")
    public ResponseEntity<Page<Recipe>> getPaginatedAndSortedRecipes(
            @PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String field) {
        return ResponseEntity.ok(recipeService.getPaginatedAndSortedRecipes(pageNumber, pageSize, field));
    }

    // ✅ Search recipes by name (JPQL)
    @GetMapping("/search")
    public ResponseEntity<List<Recipe>> searchRecipesByName(@RequestParam String name) {
        return ResponseEntity.ok(recipeService.searchRecipesByName(name));
    }

    // ✅ Filter by category (JPQL)
    @GetMapping("/category")
    public ResponseEntity<List<Recipe>> getRecipesByCategory(@RequestParam String category) {
        return ResponseEntity.ok(recipeService.getRecipesByCategory(category));
    }

    // ✅ Filter by ingredient (JPQL)
    @GetMapping("/ingredient")
    public ResponseEntity<List<Recipe>> getRecipesByIngredient(@RequestParam String ingredient) {
        return ResponseEntity.ok(recipeService.getRecipesByIngredient(ingredient));
    }

    // ✅ Filter by cooking time (JPQL)
    @GetMapping("/cooking-time")
    public ResponseEntity<List<Recipe>> getRecipesByCookingTime(@RequestParam int time) {
        return ResponseEntity.ok(recipeService.getRecipesByCookingTime(time));
    }
}
