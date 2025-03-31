package com.hello.rasikapriya.Models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private int cookingTime;

    @Column(nullable = false, length = 1000)
    private String ingredients;

    @ManyToMany(cascade = CascadeType.ALL) // Ensure cascading for proper mapping
    @JoinTable(
        name = "recipe_grocery_item", 
        joinColumns = @JoinColumn(name = "recipe_id"), 
        inverseJoinColumns = @JoinColumn(name = "grocery_item_id")
    )
    private List<GroceryItem> groceryItems;

    @ManyToMany(mappedBy = "recipes", cascade = CascadeType.ALL)
    private List<MealPlan> mealPlans;

    // Constructors
    public Recipe() {}

    public Recipe(String name, String category, int cookingTime, String ingredients, List<GroceryItem> groceryItems, List<MealPlan> mealPlans) {
        this.name = name;
        this.category = category;
        this.cookingTime = cookingTime;
        this.ingredients = ingredients;
        this.groceryItems = groceryItems;
        this.mealPlans = mealPlans;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getCookingTime() { return cookingTime; }
    public void setCookingTime(int cookingTime) { this.cookingTime = cookingTime; }

    public String getIngredients() { return ingredients; }
    public void setIngredients(String ingredients) { this.ingredients = ingredients; }

    public List<GroceryItem> getGroceryItems() { return groceryItems; }
    public void setGroceryItems(List<GroceryItem> groceryItems) { this.groceryItems = groceryItems; }

    public List<MealPlan> getMealPlans() { return mealPlans; }
    public void setMealPlans(List<MealPlan> mealPlans) { this.mealPlans = mealPlans; }
}
