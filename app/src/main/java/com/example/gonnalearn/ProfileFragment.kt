package com.example.gonnalearn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment() {

    private val roles = arrayOf("Student", "Tutor")

    companion object{
        var fullName : String? = null
        var email : String? = null
        var dateOfBirth : String? = null
        var role : String? = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myInflater = inflater.inflate(R.layout.fragment_profile, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = fullName

        myInflater.profileFullName.setText(fullName)
        myInflater.profileEmail.setText(email)
        myInflater.profileDateOfBirth.setText(dateOfBirth)

        val spinner = myInflater.findViewById<Spinner>(R.id.profileRoleSpinner)
        spinner?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, R.layout.support_simple_spinner_dropdown_item, roles) } as SpinnerAdapter
        var roleIndex : Int = 0
        if(role == "Student"){
            roleIndex = 0
        }else if(role == "Tutor"){
            roleIndex = 1
        }
        spinner?.setSelection(roleIndex)
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("nothing selected")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val type = parent?.getItemAtPosition(position).toString()
                println(type)
            }

        }


        return myInflater
    }

}