package org.albert.x09_globochat

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceDataStore
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreferenceCompat

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        // rootKey -> key of the root element (PreferenceScreen)
        setPreferencesFromResource(R.xml.settings, rootKey)

        val dataStore = CustomDataStore()
        // Enable PreferenceDataStore for the entire hierarchy and disable SharedPreferences
//        preferenceManager.preferenceDataStore = dataStore

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

        // Custom SummaryProvider
        val newMsgPref = findPreference<SwitchPreferenceCompat>(getString(R.string.key_new_msg_notif))
        newMsgPref?.summaryProvider = Preference.SummaryProvider<SwitchPreferenceCompat> { switchPref ->
            if (switchPref.isChecked)
                "Status: ON"
            else
                "Status: OFF"
        }

        // Opening a webpage (could also be inside the xml with <intent>)
        val policyPref = findPreference<Preference>(getString(R.string.key_privacy_policy))
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://www.google.com")
        policyPref?.intent = intent

        // PreferenceDataStore
        newMsgPref?.preferenceDataStore = dataStore
    }

    class CustomDataStore : PreferenceDataStore() {
        override fun getBoolean(key: String?, defValue: Boolean): Boolean {
            Log.d(this::class.simpleName, "getBoolean = $key : $defValue")
            return defValue
        }

        override fun putBoolean(key: String?, value: Boolean) {
            Log.d(this::class.simpleName, "putBoolean = $key : $value")
        }
    }
}
