package com.projects.enzoftware.apiqrreader.utils

import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions
import com.google.firebase.ml.vision.common.FirebaseVisionImage

fun getQRCodeDetails(bitmap: Bitmap) {
    val options = FirebaseVisionBarcodeDetectorOptions.Builder()
            .setBarcodeFormats(
                    FirebaseVisionBarcode.FORMAT_QR_CODE)
            .build()
    val detector = FirebaseVision.getInstance().getVisionBarcodeDetector(options)
    val image = FirebaseVisionImage.fromBitmap(bitmap)
    detector.detectInImage(image)
            .addOnSuccessListener {
                Log.i("BARCODE SIZE","${it.size}")
                for (firebaseBarcode in it) {

                    val displayValue = firebaseBarcode.displayValue //Display contents inside the barcode

                    when (firebaseBarcode.valueType) {
                        //Handle the URL here
                        FirebaseVisionBarcode.TYPE_URL -> firebaseBarcode.url
                        // Handle the contact info here, i.e. address, name, phone, etc.
                        FirebaseVisionBarcode.TYPE_CONTACT_INFO -> firebaseBarcode.contactInfo
                        // Handle the wifi here, i.e. firebaseBarcode.wifi.ssid, etc.
                        FirebaseVisionBarcode.TYPE_WIFI -> firebaseBarcode.wifi
                        //Handle more type of Barcodes
                    }

                    Log.i("BARCODE VALUE","$displayValue ${firebaseBarcode.url}")

                }
            }
            .addOnFailureListener {
                it.printStackTrace()
                val error = "Something get wrong"
            }
            .addOnCompleteListener {

                Log.i("COMPLETADO","SE ACABO")
            }
}