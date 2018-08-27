package com.projects.enzoftware.apiqrreader

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import io.fotoapparat.Fotoapparat
import io.fotoapparat.log.fileLogger
import io.fotoapparat.log.logcat
import io.fotoapparat.log.loggers
import io.fotoapparat.parameter.ScaleType
import io.fotoapparat.selector.back
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    // TODO : SEPARATE IN FILES

    lateinit var fotoapparat: Fotoapparat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Dexter.withActivity(this)
                .withPermission(android.Manifest.permission.CAMERA)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse) {
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

                    override fun onPermissionDenied(response: PermissionDeniedResponse) {

                    }

                    override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest, token: PermissionToken) {

                    }
                }).check()

        take_picture_btn.setOnClickListener {
            val photoResult = fotoapparat.autoFocus().takePicture()
            photoResult
                    .toBitmap()
                    .whenAvailable { bitmapPhoto ->  
                        Log.i("Success","Picture 2 Bitmap")
                    }
        }


    }


    override fun onStop() {
        super.onStop()
        fotoapparat.stop()
    }
}
