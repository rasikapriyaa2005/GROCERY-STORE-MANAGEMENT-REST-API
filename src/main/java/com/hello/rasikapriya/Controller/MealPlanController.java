package com.hello.rasikapriya.Controller;

import com.hello.rasikapriya.Models.MealPlan;
import com.hello.rasikapriya.Services.MealPlanService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mealplans")
public class MealPlanController {

    private final MealPlanService mealPlanService;

    public MealPlanController(MealPlanService mealPlanService) {
        this.mealPlanService = mealPlanService;
    }

    
    @PostMapping
    public ResponseEntity<String> addMealPlans(@RequestBody List<MealPlan> mealPlans) {
        mealPlanService.addMealPlans(mealPlans);
        return ResponseEntity.status(HttpStatus.CREATED).body("Meal plans added successfully");
    }

    @GetMapping
    public ResponseEntity<List<MealPlan>> getAllMealPlans() {
        return ResponseEntity.ok(mealPlanService.getAllMealPlans());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealPlan> getMealPlanById(@PathVariable Long id) {
        Optional<MealPlan> mealPlan = mealPlanService.getMealPlanById(id);
        return mealPlan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MealPlan>> getMealPlansByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(mealPlanService.getMealPlansByUserId(userId));
    }

    
    @GetMapping("/sort/{field}")
    public ResponseEntity<List<MealPlan>> getSortedMealPlans(
            @PathVariable String field,
            @RequestParam(defaultValue = "asc") String order) {
        return ResponseEntity.ok(mealPlanService.getSortedMealPlans(field, order));
    }

    
    @GetMapping("/page/{pageNumber}/size/{pageSize}")
    public ResponseEntity<Page<MealPlan>> getMealPlansWithPagination(
            @PathVariable int pageNumber,
            @PathVariable int pageSize) {
        return ResponseEntity.ok(mealPlanService.getMealPlansWithPagination(pageNumber, pageSize));
    }

  
    @GetMapping("/page/{pageNumber}/size/{pageSize}/sort/{field}")
    public ResponseEntity<Page<MealPlan>> getMealPlansWithPaginationAndSorting(
            @PathVariable int pageNumber,
            @PathVariable int pageSize,
            @PathVariable String field,
            @RequestParam(defaultValue = "asc") String order) {
        return ResponseEntity.ok(mealPlanService.getMealPlansWithPaginationAndSorting(pageNumber, pageSize, field, order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealPlan> updateMealPlan(@PathVariable Long id, @RequestBody MealPlan mealPlan) {
        MealPlan updatedMealPlan = mealPlanService.updateMealPlan(id, mealPlan);
        return updatedMealPlan != null ? ResponseEntity.ok(updatedMealPlan) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMealPlan(@PathVariable Long id) {
        mealPlanService.deleteMealPlan(id);
        return ResponseEntity.ok("Meal plan deleted successfully");
    }
}

