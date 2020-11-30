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
import java.io.File
import java.lang.Exception
import kotlin.coroutines.coroutineContext


class StudentEventAdapter : RecyclerView.Adapter<StudentEventAdapter.StudentEventViewHolder>() {

    private var studentEventList = emptyList<Event>()

    class StudentEventViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentEventViewHolder {

        var myViewHolder =  StudentEventViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.custom_student_event_row,
                parent,
                false
            )
        )



        return myViewHolder
    }

    // Bind data to the recycler view
    override fun onBindViewHolder(holder: StudentEventViewHolder, position: Int) {


        try{

            var curItem = studentEventList[position]

            // If the userId of the user is the id of the authenticated user , then show his events
                holder.itemView.customStudentEventPic.setBackgroundResource(R.drawable.ic_event)
                holder.itemView.customStudentEventTitle.text = curItem.title
                holder.itemView.customStudentEventDescription.text = curItem.description
                holder.itemView.customStudentEventStartDate.text = curItem.start_date
                holder.itemView.customStudentEventEndDate.text = curItem.end_date

            if(curItem.status == "AVAILABLE"){
                holder.itemView.studentEventApplyButton.setImageResource(R.drawable.ic_event)
            }else if(curItem.status == "PENDING"){
                holder.itemView.studentEventApplyButton.setImageResource(R.drawable.ic_event_added)
            }

            holder.itemView.studentEventApplyButton.setOnClickListener{
                //TODO: Change the status of the event to "PENDING" in the database
                (holder.itemView.context as MainActivity?)?.requestEvent(curItem)
                holder.itemView.studentEventApplyButton.setImageResource(R.drawable.ic_event_added)
                Toast.makeText(holder.itemView.context, "Event ${curItem.title} requested", Toast.LENGTH_SHORT).show()
            }

        }catch(e : Exception){

            Toast.makeText(holder.itemView.context, "$e", Toast.LENGTH_SHORT).show()
        }






    }



    // notify recycler view that there are some changes happened
    fun setData(studentEvent: List<Event>){
        this.studentEventList = studentEvent
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return studentEventList.size
    }
}