package com.example.gonnalearn

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gonnalearn.data.User
import kotlinx.android.synthetic.main.custom_row.view.*
import java.io.File


class ListAdapter : RecyclerView.Adapter<ListAdapter.TutorViewHolder>() {

    private var userList = emptyList<User>()

    class TutorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TutorViewHolder {
        return TutorViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.custom_row,
                parent,
                false
            )
        )
    }

    // Bind data to the recycler view
    override fun onBindViewHolder(holder: TutorViewHolder, position: Int) {

        try{
            var curItem = userList[position]
            holder.itemView.customProfilePic.setBackgroundResource(R.drawable.ic_people)
            holder.itemView.customFullName.text = curItem.fullName.toString()
            holder.itemView.customRole.text = curItem.role.toString()
            holder.itemView.customDateOfBirth.text = curItem.dateOfBirth.toString()
        }catch (e: Exception){
            Toast.makeText(
                holder.itemView.context, "$e",
                Toast.LENGTH_LONG
            ).show();
        }

    }

    // notify recycler view that there are some changes happened
    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
 
    override fun getItemCount(): Int {
        return userList.size
    }
}