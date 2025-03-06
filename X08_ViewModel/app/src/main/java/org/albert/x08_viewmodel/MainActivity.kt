package org.albert.x08_viewmodel

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import org.albert.x08_viewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // This makes the first title to be the name of the app, not the first fragment
//        setSupportActionBar(binding.toolbar)
        drawerLayout = binding.root

        navController = binding
            .fragmentComponentView // FragmentComponentView
            .getFragment<NavHostFragment>() // NavHostFragment
            .navController // NavController

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        binding.navigationView.setupWithNavController(navController)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab)
                .show()
        }

        this.onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                customHandleOnBackPressed()
            }
        })
    }

    private fun customHandleOnBackPressed() {
        if (drawerLayout.isOpen)
            drawerLayout.close()
        else if(navController.currentDestination?.id == R.id.FirstFragment)
            finish()
        else
            navController.popBackStack()
    }
}