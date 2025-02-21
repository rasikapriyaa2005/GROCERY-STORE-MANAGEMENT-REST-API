package com.hello.rasikapriya.Models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "meal_plans")
public class MealPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Date startDate;
    private Date endDate;
    private Long userId; // Storing userId directly instead of mapping

    @ElementCollection
    private List<Long> recipeIds; // Storing recipe IDs instead of mapping

    // Constructors
    public MealPlan() {}

    public MealPlan(String title, Date startDate, Date endDate, Long userId, List<Long> recipeIds) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.recipeIds = recipeIds;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public List<Long> getRecipeIds() { return recipeIds; }
    public void setRecipeIds(List<Long> recipeIds) { this.recipeIds = recipeIds; }
}
