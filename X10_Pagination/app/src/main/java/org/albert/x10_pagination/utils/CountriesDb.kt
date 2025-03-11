package org.albert.x10_pagination.utils

import android.content.Context
import org.albert.x10_pagination.models.Country
import com.google.gson.Gson

class CountriesDb {
    companion object {
        private lateinit var countries: ArrayList<Country>

        fun initialize(context: Context) {
//            val content = context.assets.open("countries.json")
            val content = context.assets.open("countries_paged.json")
                    .bufferedReader()
                    .use { it.readText() }
            countries = ArrayList(Gson()
                            .fromJson(content, Array<Country>::class.java)
                            .toList())
            getCountries()
        }

        fun getCountries(): List<Country> {
            return countries
        }

        fun deleteCountry(countryCode: String) {
            countries.filter { countryCode != countryCode }
        }

        fun addCountry(country: Country) {
            countries.add(country)
        }

        fun deleteCountryById(id: Int) {
            countries.removeAll { c -> c.id == id }
        }
    }
}