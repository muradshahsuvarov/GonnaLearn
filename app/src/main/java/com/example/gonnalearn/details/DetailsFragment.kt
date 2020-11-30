package com.example.gonnalearn.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.gonnalearn.MainActivity
import com.example.gonnalearn.R
import com.example.gonnalearn.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_details.view.*
import java.lang.Exception


class DetailsFragment : Fragment() {

    companion object{
        var fullName : String? = null
        var role : String? = null
        var dateOfBirth : String? = null
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val myInflater = inflater.inflate(R.layout.fragment_details, container, false)

        try{
            myInflater.detailsProfilePicture.setImageResource(R.drawable.ic_face)
            myInflater.detailsFullName.setText(fullName)
            myInflater.detailsRole.setText(role)
            myInflater.detailsBirthday.setText(dateOfBirth)
        }catch(e : Exception){
            Toast.makeText(context, "$e", Toast.LENGTH_SHORT).show()
        }


        return myInflater
    }



}