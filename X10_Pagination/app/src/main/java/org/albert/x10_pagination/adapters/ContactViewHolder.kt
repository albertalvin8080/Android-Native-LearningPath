package org.albert.x10_pagination.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import org.albert.x10_pagination.R
import org.albert.x10_pagination.models.Contact

// Using the same card as the CountryAdapter because why not?
class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleView = itemView.findViewById<TextView>(R.id.country_title)
    private val descView = itemView.findViewById<TextView>(R.id.country_desc)

    fun bind(contact: Contact){
        titleView.text = "${contact.name}"
        descView.text = contact.id.toString()
    }

    companion object {
        fun create(parent: ViewGroup): ContactViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_country, parent, false)

            return ContactViewHolder(view)
        }
    }
}