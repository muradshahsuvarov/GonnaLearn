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
import com.example.gonnalearn.data.EventViewModel
import com.example.gonnalearn.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_event.view.*
import kotlinx.android.synthetic.main.fragment_tutors.view.*
import java.lang.Exception


class EventFragment : Fragment() {

    private lateinit var mEventViewModel: EventViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val myInflater =  inflater.inflate(R.layout.fragment_event, container, false)


            // Adapter and Recycler Views
            val adapter = EventsAdapter()
            val recyclerView = myInflater.eventsRecyclerView
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            //Initialize User View Model

            mEventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)
            mEventViewModel.readAllData.observe(viewLifecycleOwner, Observer { event ->
                try{
                    adapter.setData(event)
                }catch(e : Exception){
                    Toast.makeText(context, "$e", Toast.LENGTH_SHORT).show()
                }

            })



            (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Tutor Events"



        return myInflater
    }

}