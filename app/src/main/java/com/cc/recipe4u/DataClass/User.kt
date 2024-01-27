package com.cc.recipe4u.DataClass

data class User(
    val userId: String,
    val name: String,
    val photoUrl: String,
    val recipeIds: List<String>,
    val favoriteRecipeIds: List<String>,
    val ratedRecipes: Map<String, Int>
)
