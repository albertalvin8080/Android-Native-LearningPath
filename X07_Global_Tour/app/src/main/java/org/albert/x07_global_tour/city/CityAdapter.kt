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

class CityAdapter(private val context: Context, private val cities: ArrayList<City>) :
    RecyclerView.Adapter<CityAdapter.CityViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val itemView = LayoutInflater
            .from(context)
            .inflate(R.layout.list_item_city, parent, false)
        return CityViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]
        holder.setItem(city, position)
    }

    // itemView == list_item_city.xml
    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
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
        val icDeleteImage = ResourcesCompat.getDrawable(
            context.resources,
            R.drawable.ic_delete,
            null
        )

        fun setItem(city: City, position: Int) {
            currentPosition = position
            currentCity = city

            txvCityName.text = city.name
            imvCityImage.setImageResource(city.imageId)
            imvDelImage.setImageDrawable(icDeleteImage)
            if (city.isFavorite)
                imvFavImage.setImageDrawable(icFavoriteFilledImage)
            else
                imvFavImage.setImageDrawable(icFavoriteBorderedImage)
        }
    }
}