package com.projects.enzoftware.apiqrreader.utils

import android.app.Activity
import android.util.Log
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

fun requestPermission(activity: Activity, permission: String) : Boolean{

    var result  = false
    Dexter  .withActivity(activity)
            .withPermission(permission)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    result = true
                }

                override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {
                    Log.i("Permission",permission.toString())
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    result = false
                }
            }).check()

    return result
}