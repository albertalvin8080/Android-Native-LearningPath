package org.albert.x07_global_tour.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.albert.x07_global_tour.R
import org.albert.x07_global_tour.databinding.FragmentCityListBinding

class CityListFragment : Fragment() {

    private lateinit var binding: FragmentCityListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val view = inflater.inflate(R.layout.fragment_city_list, container, false)
//        return view

        binding = FragmentCityListBinding.inflate(inflater, container, false)
        setRecyclerView()

        return binding.root
    }

    private fun setRecyclerView() {
//        val view = binding.root
        val ctx = requireContext()
        val adapter = CityAdapter(ctx, VacationSpots.cityList!!)

        val recyclerView = binding.cityRecyclerView
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(ctx)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = layoutManager
    }
}