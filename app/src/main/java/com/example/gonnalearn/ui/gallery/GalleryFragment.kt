package com.example.gonnalearn.ui.gallery

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gonnalearn.MainActivity
import com.example.gonnalearn.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.fragment_gallery.view.*

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel

    companion object {
        const val REQ_CODE = 201 // NEED TO REFER TO THIS NUM
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
                ViewModelProvider(this).get(GalleryViewModel::class.java)
        val myInflater = inflater.inflate(R.layout.fragment_gallery, container, false)

        myInflater.makePhotoButton.setOnClickListener {
            var takePic = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            var cameraExists : Boolean = getActivity()?.let { it1 -> takePic.resolveActivity(it1.getPackageManager()) } != null
            if(cameraExists){
                startActivityForResult(takePic, REQ_CODE)
            }else{
                val snackBar = Snackbar.make(
                    myInflater.findViewById(android.R.id.content),
                    "Unable to open camera", Snackbar.LENGTH_LONG
                )
                snackBar.show()
            }

            startActivityForResult(takePic, Companion.REQ_CODE)
        }

        return myInflater
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == REQ_CODE && resultCode == Activity.RESULT_OK)
        {
            var img = data?.extras?.get("data") as Bitmap
            takenImageView.setImageBitmap(img)
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


}