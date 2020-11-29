package com.example.gonnalearn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gonnalearn.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_tutors.view.*
import java.lang.Exception


class TutorsFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myInflater = inflater.inflate(R.layout.fragment_tutors, container, false)

        try{
            // Adapter and Recycler Views
            val adapter = ListAdapter()
            val recyclerView = myInflater.tutorsRecyclerView
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            //Initialize User View Model
            mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
                adapter.setData(user)
            })

            (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Tutors"

        }catch(e : Exception){
            Toast.makeText(context, "$e", Toast.LENGTH_SHORT).show()
        }

        return myInflater
    }

}