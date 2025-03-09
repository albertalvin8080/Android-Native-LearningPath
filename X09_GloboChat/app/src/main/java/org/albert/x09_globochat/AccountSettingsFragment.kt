package org.albert.x09_globochat

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import androidx.preference.MultiSelectListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

class AccountSettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
//        setPreferencesFromResource(R.xml.account_settings, rootKey)
        val ctx = requireContext()

        val prefScreen =
            preferenceManager.createPreferenceScreen(ctx)

        val privacyPrefCat = PreferenceCategory(ctx).apply {
            title = getString(R.string.title_privacy)
            isIconSpaceReserved = false
        }

        val publicInfo = MultiSelectListPreference(ctx).apply {
            title = getString(R.string.title_public_info)
            key = getString(R.string.key_public_info)
            entries = resources.getStringArray(R.array.entries_public_info)
            entryValues = resources.getStringArray(R.array.values_public_info)
            isIconSpaceReserved = false
        }

        val securityPrefCat = PreferenceCategory(ctx).apply {
            title = getString(R.string.title_security)
            isIconSpaceReserved = false
        }

        val logoutPref = Preference(ctx).apply {
            key = getString(R.string.key_log_out)
            title = getString(R.string.title_log_out)
            isIconSpaceReserved = false
        }

        val delAccPref = Preference(ctx).apply {
            key = getString(R.string.key_delete_account)
            title = getString(R.string.title_delete_account)
            icon = ResourcesCompat.getDrawable(resources, android.R.drawable.ic_menu_delete, null)
        }

        // WARNING: This order MUST be followed when adding preferences.
        prefScreen.addPreference(privacyPrefCat)
        prefScreen.addPreference(securityPrefCat)

        privacyPrefCat.addPreference(publicInfo)

        securityPrefCat.addPreference(logoutPref)
        securityPrefCat.addPreference(delAccPref)

        this.preferenceScreen = prefScreen

        val pubInfo = PreferenceManager.getDefaultSharedPreferences(requireContext()).getStringSet(getString(R.string.key_public_info), null)
        Log.d(this::class.simpleName, "$pubInfo")
    }
}
