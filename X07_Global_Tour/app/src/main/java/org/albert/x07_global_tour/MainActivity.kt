package org.albert.x07_global_tour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import org.albert.x07_global_tour.databinding.ActivityMainBinding
import kotlin.reflect.KFunction0


class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: MaterialToolbar
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var binding: ActivityMainBinding

    /**
     * DrawerLayout: A layout for creating a sliding drawer from the screen edge.
     *
     * NavigationView: (the sliding menu) A UI component for displaying navigation items in a sliding panel.
     *
     * FragmentComponentView: A view for displaying a fragment within a layout.
     *
     * NavigationComponent: NavHostFragment + NavigationController (also the name of the library)
     *
     * NavHostFragment: A container for managing navigation within a fragment.
     *
     * NavigationController: Manages app navigation within a NavHost.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = binding.activityMainToolbar
        drawerLayout = binding.drawerLayout
        navigationView = binding.navigationView

        // Get NavHostFragment and NavController
//        val navHostFrag = supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
        val fragmentContainerView = binding.navHostFrag
        val navHostFrag: NavHostFragment = fragmentContainerView.getFragment()
        navController = navHostFrag.navController

        // -> navController.graph is the nav_graph.xml
        // connect NavigationComponent with DrawerLayout
        val appBarConfig = AppBarConfiguration(navController.graph, drawerLayout)

        // Connect toolbar with navController
        toolbar.setupWithNavController(navController, appBarConfig)

        // Connect navigationView with navController
        navigationView.setupWithNavController(navController)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressedCustom()
            }
        })
    }

    private fun onBackPressedCustom() {
        if (drawerLayout.isOpen)
            drawerLayout.close()
        else if (navController.currentDestination?.id == R.id.fragmentCityList)
            finish()
        else
            navController.popBackStack()
    }
}
