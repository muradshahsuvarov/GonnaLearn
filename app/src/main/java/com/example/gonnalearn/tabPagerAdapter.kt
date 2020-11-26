package com.example.gonnalearn

import LoginFragment
import SignupFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class tabPagerAdapter(fragmentManager: FragmentManager)  : FragmentStatePagerAdapter(fragmentManager) {

    val tabArray : Array<String> = arrayOf("Sign Up", "Sign In")
    var tabNumber : Int = 2 // Total number of pages

    // Changes the page's title
    override fun getPageTitle(position: Int): CharSequence? {
        return tabArray[position]
    }

    // Tabs handler
    override fun getItem(position: Int): Fragment {

        var signUp = SignupFragment()

        when (position) {
            0 -> {
                var signUp = SignupFragment()
                return signUp
            }
            1-> {
                var signIn = LoginFragment()
                return signIn
            }
        }


        return signUp
    }

    // Specifies how many tabs you have
    override fun getCount(): Int {
        return tabNumber
    }

}