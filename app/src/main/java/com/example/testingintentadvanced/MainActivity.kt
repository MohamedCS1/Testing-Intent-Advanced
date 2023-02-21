package com.example.testingintentadvanced

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView


const val REQUEST_IMAGE_CAPTURE = 1234
const val KEY_IMAGE_DATA = "data"

class MainActivity : AppCompatActivity() {


    lateinit var buttonLaunchCamera:TextView
    lateinit var mainImageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonLaunchCamera = findViewById(R.id.buttonLaunchCamera)
        mainImageView = findViewById(R.id.mainImageView)

        buttonLaunchCamera.setOnClickListener {
            dispatchCameraIntent()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                REQUEST_IMAGE_CAPTURE -> {
                    data?.extras.let{ extras ->
                        if (extras == null || !extras.containsKey(KEY_IMAGE_DATA)) {
                            return
                        }
                        val imageBitmap = extras[KEY_IMAGE_DATA] as Bitmap?
                        mainImageView.setImageBitmap(imageBitmap)
                    }
                }
            }
        }
    }

    private fun dispatchCameraIntent() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }
}

