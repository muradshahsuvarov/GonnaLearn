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
import com.example.gonnalearn.data.User
import com.example.gonnalearn.details.DetailsFragment
import kotlinx.android.synthetic.main.custom_row.view.*
import java.io.File
import java.lang.Exception
import kotlin.coroutines.coroutineContext


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



            var curItem = userList[position]
            holder.itemView.customProfilePic.setBackgroundResource(R.drawable.ic_people)
            holder.itemView.customFullName.text = curItem.fullName
            holder.itemView.customRole.text = curItem.role
            holder.itemView.customDateOfBirth.text = curItem.dateOfBirth

            // Handling recycler view item click
            holder.itemView.rowLayout.setOnClickListener{

                // TODO: Go to the tutor's details page
                DetailsFragment.fullName = curItem.fullName
                DetailsFragment.role = curItem.role
                DetailsFragment.dateOfBirth = curItem.dateOfBirth
                
                (holder.itemView.context as FragmentActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.tab_linear_layout, DetailsFragment())
                    .commit()




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