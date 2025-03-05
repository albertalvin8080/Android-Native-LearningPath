package org.albert.x07_global_tour.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.albert.x07_global_tour.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
//        return view
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }
}
