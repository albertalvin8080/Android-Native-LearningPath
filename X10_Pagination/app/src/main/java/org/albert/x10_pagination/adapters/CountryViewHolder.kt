package org.albert.x10_pagination.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.albert.x10_pagination.R
import org.albert.x10_pagination.models.Country

class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleView = itemView.findViewById<TextView>(R.id.country_title)
    private val descView = itemView.findViewById<TextView>(R.id.country_desc)

    fun bind(country: Country){
        titleView.text = "${country.name} - ${country.shortCode}"
        descView.text = "Population: ${country.population}"
    }

    companion object {
        fun create(parent: ViewGroup): CountryViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_country, parent, false)

            return CountryViewHolder(view)
        }
    }
}