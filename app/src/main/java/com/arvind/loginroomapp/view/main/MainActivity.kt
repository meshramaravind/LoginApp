package com.arvind.loginroomapp.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.arvind.loginroomapp.R
import com.arvind.loginroomapp.databinding.ActivityMainBinding
import com.arvind.notewakeup.utils.hide
import com.arvind.notewakeup.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews(binding)
        observeNavElements(binding, navHostFragment.navController)
    }

    private fun observeNavElements(binding: ActivityMainBinding, navController: NavController) {

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

                R.id.dashboardFragment -> {
                    supportActionBar!!.setDisplayShowTitleEnabled(false)
                    binding.tvToolbarTitle.show()

                }
                R.id.loginStaffFragment -> {
                    supportActionBar!!.setDisplayShowTitleEnabled(true)
                    binding.tvToolbarTitle.hide()
                }

                R.id.registerStaffFragment -> {
                    supportActionBar!!.setDisplayShowTitleEnabled(true)
                    binding.tvToolbarTitle.hide()
                }

                R.id.addStaffFragment -> {
                    supportActionBar!!.setDisplayShowTitleEnabled(true)
                    binding.tvToolbarTitle.hide()
                }

                else -> {
                    supportActionBar!!.setDisplayShowTitleEnabled(true)
                }
            }
        }


    }

    private fun initViews(binding: ActivityMainBinding) {

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
            ?: return

        with(navHostFragment.navController) {
            appBarConfiguration = AppBarConfiguration(graph)
            setupActionBarWithNavController(this, appBarConfiguration)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        navHostFragment.navController.navigateUp()
        return super.onSupportNavigateUp()
    }

}