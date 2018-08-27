package com.projects.enzoftware.apiqrreader.utils

import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions
import com.google.firebase.ml.vision.common.FirebaseVisionImage


fun getQrCodeInformation(barcodeImage : Bitmap){
    val  options = FirebaseVisionBarcodeDetectorOptions.Builder()
            .setBarcodeFormats(
                    FirebaseVisionBarcode.FORMAT_QR_CODE,
                    FirebaseVisionBarcode.FORMAT_AZTEC)
            .build()

    val image = FirebaseVisionImage.fromBitmap(barcodeImage)

    val detector = FirebaseVision.getInstance().getVisionBarcodeDetector(options)

    detector.detectInImage(image)
            .addOnSuccessListener {
                Log.i("SIZE",it.size.toString())
                for (barcode in it) {
                    val rawValue = barcode.rawValue.toString()
                    Log.i("BARCODE VALUES", rawValue)
                }
            }
            .addOnFailureListener {
                Log.e("ERROR",it.message)
            }
}


fun getQRCodeDetails(bitmap: Bitmap) {
    val options = FirebaseVisionBarcodeDetectorOptions.Builder()
            .setBarcodeFormats(
                    FirebaseVisionBarcode.FORMAT_ALL_FORMATS)
            .build()
    val detector = FirebaseVision.getInstance().getVisionBarcodeDetector(options)
    val image = FirebaseVisionImage.fromBitmap(bitmap)
    detector.detectInImage(image)
            .addOnSuccessListener {
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

                    Log.i("BARCODE VALUE",displayValue)

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