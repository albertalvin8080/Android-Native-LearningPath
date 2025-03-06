package org.albert.x07_global_tour.city

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import org.albert.x07_global_tour.R
import org.albert.x07_global_tour.city.GenericCityAdapter.CityViewHolder

/*
* WARNING: It would be better to create different adapters for each ViewHolder.
* */
class GenericCityAdapter<T : CityViewHolder>(
    private val context: Context,
    private val layoutId: Int,
    internal val cities: ArrayList<City>,
    private val viewHolderFactory: (GenericCityAdapter<T>, Context, View) -> T,
) :
    RecyclerView.Adapter<T>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        val itemView = LayoutInflater
            .from(context)
            .inflate(layoutId, parent, false)
        return viewHolderFactory.invoke(this, context, itemView)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        val city = cities[position]
        holder.drawItem(city, position)
    }

    abstract class CityViewHolder(
        protected val adapter: GenericCityAdapter<CityViewHolder>,
        protected val context: Context,
        itemView: View
    ) :
        RecyclerView.ViewHolder(itemView) {
        abstract fun drawItem(city: City, position: Int): Unit
    }
}

// ex: itemView == list_item_city.xml
class HomeCityViewHolder(adapter: GenericCityAdapter<CityViewHolder>, context: Context, itemView: View) :
    CityViewHolder(adapter, context, itemView), View.OnClickListener {
    var currentPosition = -1
    var currentCity: City? = null

    val txvCityName = itemView.findViewById<TextView>(R.id.txv_city_name)
    val imvCityImage = itemView.findViewById<ImageView>(R.id.imv_city)
    val imvFavImage = itemView.findViewById<ImageView>(R.id.imv_favorite)
    val imvDelImage = itemView.findViewById<ImageView>(R.id.imv_delete)

    val icFavoriteFilledImage = ResourcesCompat.getDrawable(
        context.resources,
        R.drawable.ic_favorite_filled,
        null
    )
    val icFavoriteBorderedImage = ResourcesCompat.getDrawable(
        context.resources,
        R.drawable.ic_favorite_bordered,
        null
    )
//    val icDeleteImage = ResourcesCompat.getDrawable(
//        context.resources,
//        R.drawable.ic_delete,
//        null
//    )

    private fun setListeners() {
        this.imvDelImage.setOnClickListener(this@HomeCityViewHolder)
        this.imvFavImage.setOnClickListener(this@HomeCityViewHolder)
    }

    override fun drawItem(city: City, position: Int) {
        currentPosition = position
        currentCity = city

        txvCityName.text = city.name
        imvCityImage.setImageResource(city.imageId)
//        imvDelImage.setImageDrawable(icDeleteImage)
        if (city.isFavorite)
            imvFavImage.setImageDrawable(icFavoriteFilledImage)
        else
            imvFavImage.setImageDrawable(icFavoriteBorderedImage)

        setListeners()
    }

    override fun onClick(v: View?) {
//        Log.d("${HomeCityViewHolder::class}", "HELLO ${v!!.id}")
        when (v!!.id) {
            R.id.imv_delete -> deleteItem()
            R.id.imv_favorite -> favoriteItem()
        }
    }

    private fun deleteItem() {
        adapter.cities.removeAt(currentPosition)
        adapter.notifyItemChanged(currentPosition)
        // Redraw ViewHolder objects from this position onwards.
        adapter.notifyItemRangeChanged(currentPosition, adapter.cities.size)
        // Also remove from fav list
        VacationSpots.favoriteCityList.removeAt(currentPosition)
    }

    private fun favoriteItem() {
        currentCity?.isFavorite = !(currentCity?.isFavorite!!)

        if (currentCity!!.isFavorite) {
            VacationSpots.favoriteCityList.add(currentCity!!)
            imvFavImage.setImageDrawable(icFavoriteFilledImage)
        } else {
            VacationSpots.favoriteCityList.remove(currentCity!!)
            imvFavImage.setImageDrawable(icFavoriteBorderedImage)
        }
    }
}

// ex: itemView == list_item_fav.xml
class FavCityViewHolder(
    adapter: GenericCityAdapter<CityViewHolder>,
    context: Context,
    itemView: View
) : CityViewHolder(adapter, context, itemView) {
    var currentPosition = -1
    var currentCity: City? = null

    val txvCityName = itemView.findViewById<TextView>(R.id.txv_city_name)
    val imvCityImage = itemView.findViewById<ImageView>(R.id.imv_city)

    override fun drawItem(city: City, position: Int) {
        currentPosition = position
        currentCity = city

        txvCityName.text = city.name
        imvCityImage.setImageResource(city.imageId)
    }
}