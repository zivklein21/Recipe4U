package com.cc.recipe4u.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cc.recipe4u.Adapters.RecipeAdapter
import com.cc.recipe4u.Objects.GlobalVariables
import com.cc.recipe4u.R
import com.cc.recipe4u.ViewModels.AuthViewModel
import com.cc.recipe4u.ViewModels.RecipeViewModel
import com.cc.recipe4u.ViewModels.UserViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [FavoritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritesFragment : Fragment() {
    private lateinit var recipeRecyclerView: RecyclerView

    private val recipeViewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        recipeRecyclerView = view.findViewById(R.id.recipesRecyclerView)
        recipeViewModel.setContextAndDB(requireContext())

        initRecipeRecyclerView()

        return view
    }

    private fun initRecipeRecyclerView() {
        recipeViewModel.getAllRecipes(lifecycleScope).observe(viewLifecycleOwner) { recipes ->
            if (recipes.isNotEmpty()) {
                val filteredRecipes = recipes.filter { GlobalVariables.currentUser?.favoriteRecipeIds!!.contains(it.recipeId) }
                val adapter = RecipeAdapter(filteredRecipes, this)
                recipeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                recipeRecyclerView.adapter = adapter
            }
        }
    }
}