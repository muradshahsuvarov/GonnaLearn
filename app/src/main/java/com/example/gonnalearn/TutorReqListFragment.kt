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
import com.example.gonnalearn.data.Event
import com.example.gonnalearn.data.EventViewModel
import kotlinx.android.synthetic.main.fragment_student_event.view.*
import kotlinx.android.synthetic.main.fragment_student_event.view.eventStudentRecyclerView
import kotlinx.android.synthetic.main.fragment_tutor_req_list.view.*
import java.lang.Exception


class TutorReqListFragment : Fragment() {

    private lateinit var mEventViewModel: EventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myInflater = inflater.inflate(R.layout.fragment_tutor_req_list, container, false)

        try{
            // Adapter and Recycler Views
            val adapter = TutorReqListAdapter()
            val recyclerView = myInflater.tutorReqRecyclerView
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            //Initialize User View Model

            mEventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)
            mEventViewModel.readAllData.observe(viewLifecycleOwner, Observer { event ->

                var tutorEventList: MutableList<Event> = mutableListOf()
                var index : Int = 0
                for(i in event){
                    if(i.userId == MainActivity.rememberedUser?.id && i.status == "PENDING"){
                        tutorEventList.add(event[index])
                    }
                    index++
                }

                adapter.setData(tutorEventList)


            })
        }catch(e : Exception){
            Toast.makeText(context, "$e", Toast.LENGTH_SHORT).show()
        }


        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Request List"

        return myInflater
    }

}