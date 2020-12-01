package com.example.gonnalearn

import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import androidx.viewpager.widget.ViewPager
import com.example.gonnalearn.data.Event
import com.example.gonnalearn.data.EventViewModel
import com.example.gonnalearn.data.User
import com.example.gonnalearn.ui.gallery.GalleryFragment
import com.example.gonnalearn.ui.slideshow.SlideshowFragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var mEventViewModel: EventViewModel

    var role : String? = "Tutor" // Role of the authenticated user


    companion object{

        // Check whether to save the user session or not
        var userRemembered : Boolean = false

        // Can be used to check whether user authenticated or not
        var rememberedUser : User? = null

    }




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

        mEventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)



        // Added by me

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_schedule, R.id.nav_tutor_list,
                R.id.nav_requests, R.id.nav_tutor_search, R.id.nav_sign_out), drawerLayout)

        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)

        // Navigation drawer items' event click listeners
        navView.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
                val id: Int = menuItem.getItemId()
                //it's possible to do more actions on several items, if there is a large amount of items I prefer switch(){case} instead of if()
                if (id == R.id.nav_home && !userRemembered) {

                    // Hide "tabs" which is a "Tab Layout"
                    val tabs = findViewById<View>(R.id.tabs) as TabLayout
                    tabs.setVisibility(View.VISIBLE)

                    // Hide "content_linear_layout" which contains the "content_main"
                    val ll_2 = findViewById<View>(R.id.content_linear_layout) as LinearLayout
                    ll_2.setVisibility(View.VISIBLE)


                } else if (id == R.id.nav_home && userRemembered) {

                    // Hide "tabs" which is a "Tab Layout"
                    val tabs = findViewById<View>(R.id.tabs) as TabLayout
                    tabs.setVisibility(View.GONE)

                    // Hide "content_linear_layout" which contains the "content_main"
                    val ll_2 = findViewById<View>(R.id.content_linear_layout) as LinearLayout
                    ll_2.setVisibility(View.GONE)


                    // Replace fragment with ProfileFragment fragment
                    supportFragmentManager.beginTransaction().replace(R.id.tab_linear_layout, ProfileFragment()).commit()

                } else if (id == R.id.nav_gallery) {

                    try {

                        // Hide "tabs" which is a "Tab Layout"
                        val tabs = findViewById<View>(R.id.tabs) as TabLayout
                        tabs.setVisibility(View.GONE)

                        // Hide "content_linear_layout" which contains the "content_main"
                        val ll_2 = findViewById<View>(R.id.content_linear_layout) as LinearLayout
                        ll_2.setVisibility(View.GONE)


                        // Replace fragment with SlideShow fragment
                        supportFragmentManager.beginTransaction().replace(R.id.tab_linear_layout, GalleryFragment()).commit()

                    } catch (e: Exception) {
                        Toast.makeText(baseContext, "Hi there! This is a Toast.", Toast.LENGTH_SHORT).show()
                    }


                } else if (id == R.id.nav_slideshow) {

                    try {

                        // Hide "tabs" which is a "Tab Layout"
                        val tabs = findViewById<View>(R.id.tabs) as TabLayout
                        tabs.setVisibility(View.GONE)

                        // Hide "content_linear_layout" which contains the "content_main"
                        val ll_2 = findViewById<View>(R.id.content_linear_layout) as LinearLayout
                        ll_2.setVisibility(View.GONE)

                        // Replace fragment with SlideShow fragment
                        supportFragmentManager.beginTransaction().replace(R.id.tab_linear_layout, SlideshowFragment()).commit()

                    } catch (e: Exception) {
                        Toast.makeText(baseContext, "$e", Toast.LENGTH_SHORT).show()
                    }


                } else if (id == R.id.nav_schedule) {

                    if (role == "Tutor") {
                        try {

                            // Hide "tabs" which is a "Tab Layout"
                            val tabs = findViewById<View>(R.id.tabs) as TabLayout
                            tabs.setVisibility(View.GONE)

                            // Hide "content_linear_layout" which contains the "content_main"
                            val ll_2 = findViewById<View>(R.id.content_linear_layout) as LinearLayout
                            ll_2.setVisibility(View.GONE)

                            // Replace fragment with SlideShow fragment
                            supportFragmentManager.beginTransaction().replace(R.id.tab_linear_layout, TutorScheduleFragment()).commit()

                        } catch (e: Exception) {
                            Toast.makeText(baseContext, "$e", Toast.LENGTH_SHORT).show()
                        }
                    } else if (role == "Student") {
                        try {

                            // Hide "tabs" which is a "Tab Layout"
                            val tabs = findViewById<View>(R.id.tabs) as TabLayout
                            tabs.setVisibility(View.GONE)

                            // Hide "content_linear_layout" which contains the "content_main"
                            val ll_2 = findViewById<View>(R.id.content_linear_layout) as LinearLayout
                            ll_2.setVisibility(View.GONE)

                            // Replace fragment with SlideShow fragment
                            supportFragmentManager.beginTransaction().replace(R.id.tab_linear_layout, StudentScheduleFragment()).commit()

                        } catch (e: Exception) {
                            Toast.makeText(baseContext, "$e", Toast.LENGTH_SHORT).show()
                        }
                    }

                } else if (id == R.id.nav_tutor_list) {
                    try {

                        // Hide "tabs" which is a "Tab Layout"
                        val tabs = findViewById<View>(R.id.tabs) as TabLayout
                        tabs.setVisibility(View.GONE)

                        // Hide "content_linear_layout" which contains the "content_main"
                        val ll_2 = findViewById<View>(R.id.content_linear_layout) as LinearLayout
                        ll_2.setVisibility(View.GONE)

                        // Replace fragment with SlideShow fragment
                        supportFragmentManager.beginTransaction().replace(R.id.tab_linear_layout, TutorsFragment()).commit()

                    } catch (e: Exception) {
                        Toast.makeText(baseContext, "$e", Toast.LENGTH_SHORT).show()
                    }
                } else if (id == R.id.nav_add_event) {
                    try {

                        // Hide "tabs" which is a "Tab Layout"
                        val tabs = findViewById<View>(R.id.tabs) as TabLayout
                        tabs.setVisibility(View.GONE)

                        // Hide "content_linear_layout" which contains the "content_main"
                        val ll_2 = findViewById<View>(R.id.content_linear_layout) as LinearLayout
                        ll_2.setVisibility(View.GONE)

                        // Replace fragment with SlideShow fragment
                        supportFragmentManager.beginTransaction().replace(R.id.tab_linear_layout, AddEvent()).commit()

                    } catch (e: Exception) {
                        Toast.makeText(baseContext, "$e", Toast.LENGTH_SHORT).show()
                    }
                } else if (id == R.id.nav_events) {
                    try {

                        // Hide "tabs" which is a "Tab Layout"
                        val tabs = findViewById<View>(R.id.tabs) as TabLayout
                        tabs.setVisibility(View.GONE)

                        // Hide "content_linear_layout" which contains the "content_main"
                        val ll_2 = findViewById<View>(R.id.content_linear_layout) as LinearLayout
                        ll_2.setVisibility(View.GONE)

                        // Replace fragment with SlideShow fragment
                        supportFragmentManager.beginTransaction().replace(R.id.tab_linear_layout, EventFragment()).commit()

                    } catch (e: Exception) {
                        Toast.makeText(baseContext, "$e", Toast.LENGTH_SHORT).show()
                    }
                } else if (id == R.id.nav_student_events) {
                    try {

                        // Hide "tabs" which is a "Tab Layout"
                        val tabs = findViewById<View>(R.id.tabs) as TabLayout
                        tabs.setVisibility(View.GONE)

                        // Hide "content_linear_layout" which contains the "content_main"
                        val ll_2 = findViewById<View>(R.id.content_linear_layout) as LinearLayout
                        ll_2.setVisibility(View.GONE)

                        // Replace fragment with SlideShow fragment
                        supportFragmentManager.beginTransaction().replace(R.id.tab_linear_layout, StudentEventFragment()).commit()

                    } catch (e: Exception) {
                        Toast.makeText(baseContext, "$e", Toast.LENGTH_SHORT).show()
                    }
                } else if (id == R.id.nav_requests) {
                    try {

                        // Hide "tabs" which is a "Tab Layout"
                        val tabs = findViewById<View>(R.id.tabs) as TabLayout
                        tabs.setVisibility(View.GONE)

                        // Hide "content_linear_layout" which contains the "content_main"
                        val ll_2 = findViewById<View>(R.id.content_linear_layout) as LinearLayout
                        ll_2.setVisibility(View.GONE)

                        // Replace fragment with SlideShow fragment
                        supportFragmentManager.beginTransaction().replace(R.id.tab_linear_layout, SubmittedRequestsFragment()).commit()

                    } catch (e: Exception) {
                        Toast.makeText(baseContext, "$e", Toast.LENGTH_SHORT).show()
                    }
                } else if (id == R.id.nav_tutor_requests) {
                    try {

                        // Hide "tabs" which is a "Tab Layout"
                        val tabs = findViewById<View>(R.id.tabs) as TabLayout
                        tabs.setVisibility(View.GONE)

                        // Hide "content_linear_layout" which contains the "content_main"
                        val ll_2 = findViewById<View>(R.id.content_linear_layout) as LinearLayout
                        ll_2.setVisibility(View.GONE)

                        // Replace fragment with SlideShow fragment
                        supportFragmentManager.beginTransaction().replace(R.id.tab_linear_layout, TutorReqListFragment()).commit()

                    } catch (e: Exception) {
                        Toast.makeText(baseContext, "$e", Toast.LENGTH_SHORT).show()
                    }
                } else if (id == R.id.nav_tutor_search) {
                    try {

                        // Hide "tabs" which is a "Tab Layout"
                        val tabs = findViewById<View>(R.id.tabs) as TabLayout
                        tabs.setVisibility(View.GONE)

                        // Hide "content_linear_layout" which contains the "content_main"
                        val ll_2 = findViewById<View>(R.id.content_linear_layout) as LinearLayout
                        ll_2.setVisibility(View.GONE)

                        // Replace fragment with SlideShow fragment
                        supportFragmentManager.beginTransaction().replace(R.id.tab_linear_layout, TutorsSearchFragment()).commit()

                    } catch (e: Exception) {
                        Toast.makeText(baseContext, "$e", Toast.LENGTH_SHORT).show()
                    }
                } else if (id == R.id.nav_sign_out) {

                    userRemembered = false
                    rememberedUser = null
                    Toast.makeText(baseContext, "Successfully signed out!", Toast.LENGTH_SHORT).show()


                }
                //This is for maintaining the behavior of the Navigation view
                NavigationUI.onNavDestinationSelected(menuItem, navController)

                return true
            }
        })

    }

    fun requestEvent(event: Event, userEmail: String){

        // Create event object
        var updatedEvent = Event(
                event.id,
                event.title,
                event.description,
                event.start_date,
                event.end_date,
                event.userId,
                userEmail,
                "PENDING"
        )

        // Update event
        mEventViewModel.updateEvent(updatedEvent)

    }

    fun acceptEvent(event: Event, userEmail: String){

        var updatedEvent = Event(
                event.id,
                event.title,
                event.description,
                event.start_date,
                event.end_date,
                event.userId,
                userEmail,
                "ACCEPTED"
        )

        // Update event
        mEventViewModel.updateEvent(updatedEvent)
    }

    fun rejectEvent(event: Event){

        var updatedEvent = Event(
                event.id,
                event.title,
                event.description,
                event.start_date,
                event.end_date,
                event.userId,
                "",
                "AVAILABLE"
        )

        // Update event
        mEventViewModel.updateEvent(updatedEvent)
    }

    fun AuthenticateUser(){

        try{

            // Hide "tabs" which is a "Tab Layout"
            val tabs = findViewById<View>(R.id.tabs) as TabLayout
            tabs.setVisibility(View.GONE)

            // Hide "content_linear_layout" which contains the "content_main"
            val ll_2 = findViewById<View>(R.id.content_linear_layout) as LinearLayout
            ll_2.setVisibility(View.GONE)

            // Replace fragment with ProfileFragment fragment
            supportFragmentManager.beginTransaction().replace(R.id.tab_linear_layout, ProfileFragment()).commit()

        }catch (e: Exception){
            Toast.makeText(baseContext, "$e", Toast.LENGTH_SHORT).show()
        }
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