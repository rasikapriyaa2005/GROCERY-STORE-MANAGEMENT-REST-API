package com.hello.rasikapriya.Services;

import com.hello.rasikapriya.Models.MealPlan;
import com.hello.rasikapriya.Repositories.MealPlanRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealPlanService {

    private final MealPlanRepository mealPlanRepository;

    public MealPlanService(MealPlanRepository mealPlanRepository) {
        this.mealPlanRepository = mealPlanRepository;
    }

    // ✅ Save multiple meal plans
    public void addMealPlans(List<MealPlan> mealPlans) {
        mealPlanRepository.saveAll(mealPlans);
    }

    public List<MealPlan> getAllMealPlans() {
        return mealPlanRepository.findAll();
    }

    public Optional<MealPlan> getMealPlanById(Long id) {
        return mealPlanRepository.findById(id);
    }

    public List<MealPlan> getMealPlansByUserId(Long userId) {
        return mealPlanRepository.findByUserId(userId);
    }

    // ✅ Sorting logic
    public List<MealPlan> getSortedMealPlans(String field, String order) {
        Sort sort = order.equalsIgnoreCase("desc") ? Sort.by(field).descending() : Sort.by(field).ascending();
        return mealPlanRepository.findAll(sort);
    }

    // ✅ Pagination logic
    public Page<MealPlan> getMealPlansWithPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return mealPlanRepository.findAll(pageable);
    }

    // ✅ Pagination with Sorting
    public Page<MealPlan> getMealPlansWithPaginationAndSorting(int pageNumber, int pageSize, String field, String order) {
        Sort sort = order.equalsIgnoreCase("desc") ? Sort.by(field).descending() : Sort.by(field).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return mealPlanRepository.findAll(pageable);
    }

    public MealPlan updateMealPlan(Long id, MealPlan mealPlan) {
        return mealPlanRepository.findById(id)
                .map(existingPlan -> {
                    existingPlan.setTitle(mealPlan.getTitle());
                    existingPlan.setStartDate(mealPlan.getStartDate());
                    existingPlan.setEndDate(mealPlan.getEndDate());
                    existingPlan.setUserId(mealPlan.getUserId());
                    existingPlan.setRecipeIds(mealPlan.getRecipeIds());
                    return mealPlanRepository.save(existingPlan);
                })
                .orElse(null);
    }

    public void deleteMealPlan(Long id) {
        mealPlanRepository.deleteById(id);
    }
}
