package org.albert.x07_global_tour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import org.albert.x07_global_tour.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: MaterialToolbar
    private lateinit var navController: NavController
    private lateinit var bottomNavView: BottomNavigationView
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
        bottomNavView = binding.bottomNavView

        // Get NavHostFragment and NavController
//        val navHostFrag = supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
        val fragmentContainerView = binding.navHostFrag
        val navHostFrag: NavHostFragment = fragmentContainerView.getFragment()
        navController = navHostFrag.navController

        // These fragments won't have the "return arrow" / "up button" in the toolbar
        val topLevelDestinations = setOf(R.id.fragmentCityList, R.id.fragmentFavoriteList)
        val appBarConfig = AppBarConfiguration(topLevelDestinations)

        // Connect toolbar with navController
        toolbar.setupWithNavController(navController, appBarConfig)

        // Connect BottomNavigationView with navController
        bottomNavView.setupWithNavController(navController)
    }
}
