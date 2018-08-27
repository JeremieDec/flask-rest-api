package com.projects.enzoftware.apiqrreader.ui

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.projects.enzoftware.apiqrreader.R
import com.projects.enzoftware.apiqrreader.utils.getQrCodeInformation
import com.projects.enzoftware.apiqrreader.utils.requestPermission
import io.fotoapparat.Fotoapparat
import io.fotoapparat.log.fileLogger
import io.fotoapparat.log.logcat
import io.fotoapparat.log.loggers
import io.fotoapparat.parameter.ScaleType
import io.fotoapparat.selector.back
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var fotoapparat: Fotoapparat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissionRequest = requestPermission(this, Manifest.permission.CAMERA)

        if(permissionRequest){
            fotoapparat = Fotoapparat(
                    context = applicationContext,
                    view = camera_view,                   // view which will draw the camera preview
                    scaleType = ScaleType.CenterCrop,    // (optional) we want the preview to fill the view
                    lensPosition = back(),               // (optional) we want back camera
                    logger = loggers(                    // (optional) we want to log camera events in 2 places at once
                            logcat(),                   // ... in logcat
                            fileLogger(applicationContext)            // ... and to file
                    ),
                    cameraErrorCallback = { error ->
                        Log.e("CAMERA_ERROR",error.message)
                    }   // (optional) log fatal errors
            )

            fotoapparat.start()
        }

        fab_camera_btn.setOnClickListener {
            val photoResult = fotoapparat.autoFocus().takePicture()
            photoResult
                    .toBitmap()
                    .whenAvailable {bitmapPhoto ->
                        getQrCodeInformation(bitmapPhoto!!.bitmap,this)
                    }
        }

    }


    override fun onStop() {
        super.onStop()
        fotoapparat.stop()
    }
}
