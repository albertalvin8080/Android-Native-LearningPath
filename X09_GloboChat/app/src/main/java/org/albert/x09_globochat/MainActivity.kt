package org.albert.x09_globochat

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.preference.PreferenceManager
import org.albert.x09_globochat.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get NavHost and NavController
//        val navHostFrag = supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
        val navHostFrag = binding.navHostFrag.getFragment<NavHostFragment>()
        navController = navHostFrag.navController

        // Get AppBarConfiguration
        appBarConfiguration = AppBarConfiguration(navController.graph)

        // Link ActionBar with NavController
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    // We need this because we don't have a toolbar for doing
    //      "toolbar.setupWithNavController(navController, appBarConfig)"
    // NOTE: We need a Theme with an action bar.
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private val onSharedPreferenceListener =
        object : SharedPreferences.OnSharedPreferenceChangeListener {
            override fun onSharedPreferenceChanged(
                sharedPreferences: SharedPreferences?,
                key: String?
            ) {
                when (key) {
                    getString(R.string.key_status) -> {
                        Toast.makeText(this@MainActivity, "Status changed", Toast.LENGTH_LONG)
                            .show()
                    }

                    getString(R.string.key_auto_reply) -> {
                        val b = sharedPreferences?.getBoolean(key, false) ?: false
                        Toast.makeText(this@MainActivity, "Auto-reply: $b", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            }
        }

    // I think its better to use a LifecycleObserver instead of doing this
    override fun onResume() {
        super.onResume()
        PreferenceManager.getDefaultSharedPreferences(this)
            .registerOnSharedPreferenceChangeListener(onSharedPreferenceListener)
    }

    // I think its better to use a LifecycleObserver instead of doing this
    override fun onPause() {
        super.onPause()
        PreferenceManager.getDefaultSharedPreferences(this)
            .unregisterOnSharedPreferenceChangeListener(onSharedPreferenceListener)
    }
}
