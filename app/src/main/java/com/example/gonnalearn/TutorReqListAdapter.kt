package com.example.gonnalearn

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.gonnalearn.data.Event
import com.example.gonnalearn.data.EventViewModel
import com.example.gonnalearn.data.User
import com.example.gonnalearn.details.DetailsFragment
import kotlinx.android.synthetic.main.custom_event_row.view.*
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlinx.android.synthetic.main.custom_student_event_row.view.*
import kotlinx.android.synthetic.main.custom_student_event_row.view.customStudentEventPic
import kotlinx.android.synthetic.main.custom_tutor_request_row.view.*
import java.io.File
import java.lang.Exception
import kotlin.coroutines.coroutineContext


class TutorReqListAdapter : RecyclerView.Adapter<TutorReqListAdapter.TutorReqListViewHolder>() {

    private var tutorEventList = emptyList<Event>()

    class TutorReqListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }



    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): TutorReqListViewHolder {

        var myViewHolder =  TutorReqListViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.custom_tutor_request_row,
                        parent,
                        false
                )
        )



        return myViewHolder
    }

    // Bind data to the recycler view
    override fun onBindViewHolder(holder: TutorReqListViewHolder, position: Int) {


        try{

            var curItem = tutorEventList[position]


            // If the userId of the user is the id of the authenticated user , then show his events
            holder.itemView.customTutorReqPic.setBackgroundResource(R.drawable.ic_people)
            holder.itemView.customTutorReqEmail.text = curItem.subscriberEmail
            holder.itemView.customTutorReqSubject.text = curItem.title
            holder.itemView.customTutorReqStartDate.text = curItem.start_date
            holder.itemView.customTutorReqEndDate.text = curItem.end_date



                holder.itemView.customTutorReqAcceptButton.setOnClickListener {
                    Toast.makeText(holder.itemView.context, "${curItem.status}", Toast.LENGTH_SHORT).show()

                    if (curItem.status == "PENDING") {
                        (holder.itemView.context as MainActivity?)?.acceptEvent(curItem, curItem.subscriberEmail)
                        Toast.makeText(holder.itemView.context, "Event Accepted", Toast.LENGTH_SHORT).show()
                    }
                }

            holder.itemView.customTutorReqRejectButton.setOnClickListener {

                if (curItem.status == "PENDING") {
                    (holder.itemView.context as MainActivity?)?.rejectEvent(curItem)
                    Toast.makeText(holder.itemView.context, "Event Rejected", Toast.LENGTH_SHORT).show()
                }

            }




        }catch(e : Exception){

            Toast.makeText(holder.itemView.context, "$e", Toast.LENGTH_SHORT).show()
        }






    }



    // notify recycler view that there are some changes happened
    fun setData(tutorEvent: List<Event>){
        this.tutorEventList = tutorEvent
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return tutorEventList.size
    }
}