package org.albert.x09_globochat

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        // rootKey -> key of the root element (PreferenceScreen)
        setPreferencesFromResource(R.xml.settings, rootKey)
        val accSett = findPreference<Preference>(getString(R.string.key_account_settings))

        accSett?.setOnPreferenceClickListener {
            val navHostFrag =
                requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
            val navController = navHostFrag.navController
            val action = SettingsFragmentDirections.actionSettingsToAccSettings()
            navController.navigate(action)
            true
        }

        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val status = sharedPreference.getString(getString(R.string.key_status), "")
        Log.i(this::class.simpleName, status!!)

        val statusPref = findPreference<EditTextPreference>(getString(R.string.key_status))
        statusPref?.setOnPreferenceChangeListener { preference, newValue ->
            val nv = newValue as String

            if (nv.contains("bad"))
            {
                Toast.makeText(context, "Inappropriate status. Please follow community guidelines", Toast.LENGTH_LONG)
                    .show()
                return@setOnPreferenceChangeListener false
            }

            true // true: accepts value change. false: rejects value change.
        }
    }
}
