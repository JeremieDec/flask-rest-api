package com.projects.enzoftware.apiqrreader

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
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
        fotoapparat = Fotoapparat(
                context = this,
                view = camera_view,                   // view which will draw the camera preview
                scaleType = ScaleType.CenterCrop,    // (optional) we want the preview to fill the view
                lensPosition = back(),               // (optional) we want back camera
                logger = loggers(                    // (optional) we want to log camera events in 2 places at once
                        logcat(),                   // ... in logcat
                        fileLogger(this)            // ... and to file
                ),
                cameraErrorCallback = { error ->
                    Log.e("CAMERA_ERROR",error.message)
                }   // (optional) log fatal errors
        )
    }

    override fun onStart() {
        super.onStart()
        fotoapparat.start()
    }

    override fun onStop() {
        super.onStop()
        fotoapparat.stop()
    }
}
