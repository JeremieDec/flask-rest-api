package com.projects.enzoftware.apiqrreader.utils

import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions
import com.google.firebase.ml.vision.common.FirebaseVisionImage


fun getQrCodeInformation(barcodeImage : Bitmap){
    FirebaseVisionBarcodeDetectorOptions.Builder()
            .setBarcodeFormats(
                    FirebaseVisionBarcode.FORMAT_QR_CODE,
                    FirebaseVisionBarcode.FORMAT_AZTEC)
            .build()

    val image = FirebaseVisionImage.fromBitmap(barcodeImage)

    val detector = FirebaseVision.getInstance().visionBarcodeDetector

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