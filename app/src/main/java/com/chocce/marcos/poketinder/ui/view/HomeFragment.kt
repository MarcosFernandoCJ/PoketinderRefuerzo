package com.chocce.marcos.poketinder.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.chocce.marcos.poketinder.R
import com.chocce.marcos.poketinder.data.model.PokemonResponse
import com.chocce.marcos.poketinder.databinding.FragmentHomeBinding
import com.chocce.marcos.poketinder.ui.adapter.PokemonAdapter
import com.chocce.marcos.poketinder.ui.viewmodel.HomeViewModel


class HomeFragment : Fragment() {

    private var listPokemon: List<PokemonResponse> = emptyList()

    private val adapter by lazy { PokemonAdapter(listPokemon) }

    private lateinit var binding : FragmentHomeBinding

    private val viewModel by lazy { HomeViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.rvTinderPokemon.adapter = adapter
        observeValues()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    private fun observeValues() {
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }

        viewModel.pokemonList.observe(this) { pokemonList ->
            adapter.list = pokemonList
            adapter.notifyDataSetChanged()
        }

        viewModel.errorApi.observe(this) { errorMessage ->
            showMessage(errorMessage)
        }
    }

    private fun showMessage(message: String) {
        activity?.runOnUiThread {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }
    }
}

