package org.albert.x07_global_tour.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import org.albert.x07_global_tour.city.City
import org.albert.x07_global_tour.city.VacationSpots
import org.albert.x07_global_tour.databinding.FragmentFavoriteBinding
import java.util.Collections

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var favList: ArrayList<City>
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
//        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
//        return view
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
//        favList = VacationSpots.favoriteCityList.toCollection(ArrayList()) // It's a trap
        favList = VacationSpots.favoriteCityList as ArrayList<City>
        recyclerView = binding.cityRecyclerView
        setUpRecyclerView()

        return binding.root
    }

    private fun setUpRecyclerView() {
        recyclerView.adapter = FavoriteAdapter(
            requireContext(), favList
        )
        recyclerView.setHasFixedSize(true)

        val layoutM = LinearLayoutManager(requireContext())
        layoutM.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = layoutM

        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private val itemTouchHelper = ItemTouchHelper(object :
        ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val from = viewHolder.adapterPosition
            val to = target.adapterPosition

            Collections.swap(favList, from, to)

            recyclerView.adapter?.notifyItemMoved(from, to)

            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val city = favList[position]

            deleteFromFavList(position)
            updateIsFav(city, false)

            Snackbar.make(recyclerView, "city removed", Snackbar.LENGTH_LONG)
                .setAction("UNDO") {
                    undoDeleteFromFavList(position, city)
                    updateIsFav(city, true)
                }
                .show()
        }

        private fun updateIsFav(city: City, isFav: Boolean) {
            city.isFavorite = isFav
        }

        private fun deleteFromFavList(position: Int) {
            favList.removeAt(position)
            recyclerView.adapter?.notifyItemRemoved(position)
            recyclerView.adapter?.notifyItemRangeChanged(position, favList.size)
        }

        private fun undoDeleteFromFavList(position: Int, city: City) {
            favList.add(position, city)
            recyclerView.adapter?.notifyItemInserted(position)
            recyclerView.adapter?.notifyItemRangeChanged(position, favList.size)
        }
    })
}

