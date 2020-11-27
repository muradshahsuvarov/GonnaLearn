package com.example.gonnalearn

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewParent
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.ui.*
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Added by me

        val tabLayout : TabLayout = findViewById(R.id.tabs)
        val Pager : ViewPager = findViewById(R.id.view_pager)
        val tabPagerAdapter : tabPagerAdapter = tabPagerAdapter(supportFragmentManager)
        Pager.adapter = tabPagerAdapter
        tabLayout.setupWithViewPager(Pager)

        // Added by me

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)

        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)

        // Navigation drawer items' event click listeners
        navView.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
                val id: Int = menuItem.getItemId()
                //it's possible to do more actions on several items, if there is a large amount of items I prefer switch(){case} instead of if()
                if (id == R.id.nav_home) {
                  //  Toast.makeText(applicationContext, "Home", Toast.LENGTH_SHORT).show()

                    //When nav_home item is pressed then unhide the tab layout
                    val ll_1 = findViewById<View>(R.id.tab_linear_layout) as LinearLayout
                    val ll_2 = findViewById<View>(R.id.content_linear_layout) as LinearLayout

                    ll_1.setVisibility(View.VISIBLE)
                    ll_2.setVisibility(View.VISIBLE)

                }else if(id == R.id.nav_gallery){

                  //  Toast.makeText(applicationContext, "Slide", Toast.LENGTH_SHORT).show()

                    //When nav_gallery item is pressed then hide the tab layout
                    val ll_1 = findViewById<View>(R.id.tab_linear_layout) as LinearLayout
                    val ll_2 = findViewById<View>(R.id.content_linear_layout) as LinearLayout

                    ll_1.setVisibility(View.GONE)
                    ll_2.setVisibility(View.GONE)

                }else if(id == R.id.nav_slideshow){
                    //  Toast.makeText(applicationContext, "Slide", Toast.LENGTH_SHORT).show()
                }
                //This is for maintaining the behavior of the Navigation view
                NavigationUI.onNavDestinationSelected(menuItem, navController)
                //This is for closing the drawer after acting on it

                return true
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}