package com.projects.enzoftware.apiqrreader.utils

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.util.SparseArray
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector

fun GetQrCodeInformation(barcodeImage : Bitmap, context: Context){
    val detector = BarcodeDetector.Builder(context)
            .setBarcodeFormats(Barcode.QR_CODE)
            .build()
    if (!detector.isOperational){
        Log.i("BARCODE_READER_FAILURE","READER IS NOT OPERATIONAL")
    }

    // TODO : MOBILE VISION IS NOT RECOGNIZE THE IMAGE PASSED
    val frame = Frame.Builder().setBitmap(barcodeImage).build()
    val barcodeList : SparseArray<Barcode> = detector.detect(frame)

    if (barcodeList.size() > 0){
        val value = barcodeList.valueAt(0)
        Log.i("BARCODE VALUE",value.rawValue)
    }else{
        Log.i("BARCODE VALUE","IS NOTHING")
    }
}