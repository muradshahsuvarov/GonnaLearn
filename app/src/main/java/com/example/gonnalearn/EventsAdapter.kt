package com.example.gonnalearn

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.gonnalearn.data.Event
import com.example.gonnalearn.data.User
import com.example.gonnalearn.details.DetailsFragment
import kotlinx.android.synthetic.main.custom_event_row.view.*
import kotlinx.android.synthetic.main.custom_row.view.*
import java.io.File
import java.lang.Exception
import kotlin.coroutines.coroutineContext


class EventsAdapter : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    private var eventList = emptyList<Event>()

    class EventViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventViewHolder {

        return EventViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.custom_event_row,
                parent,
                false
            )
        )
    }

    // Bind data to the recycler view
    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {


try{

    var curItem = eventList[position]

    // If the userId of the user is the id of the authenticated user , then show his events
    if(curItem.userId == MainActivity.rememberedUser?.id){
        holder.itemView.customEventPic.setBackgroundResource(R.drawable.ic_event)
        holder.itemView.customEventTitle.text = curItem.title
        holder.itemView.customEventDescription.text = curItem.description
        holder.itemView.customEventStartDate.text = curItem.start_date
        holder.itemView.customEventEndDate.text = curItem.end_date
    }


}catch(e : Exception){

    Toast.makeText(holder.itemView.context, "$e", Toast.LENGTH_SHORT).show()
}






    }

    // notify recycler view that there are some changes happened
    fun setData(event: List<Event>){
        this.eventList = event
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return eventList.size
    }
}