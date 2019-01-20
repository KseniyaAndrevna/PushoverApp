package com.kseniyaa.pushoverapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.SparseArray
import com.google.android.gms.samples.vision.barcodereader.BarcodeCapture
import com.google.android.gms.samples.vision.barcodereader.BarcodeGraphic
import com.google.android.gms.vision.barcode.Barcode
import xyz.belvi.mobilevisionbarcodescanner.BarcodeRetriever

class QRActivity : AppCompatActivity(), BarcodeRetriever {

    private lateinit var barcodeCapture: BarcodeCapture

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr)

        barcodeCapture = (supportFragmentManager.findFragmentById(R.id.barcode) as BarcodeCapture?)!!
        barcodeCapture.setRetrieval(this)


    }

    override fun onRetrieved(barcode: Barcode?) {
        val intent = Intent()
        intent.putExtra("value", barcode?.displayValue)
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onRetrievedMultiple(closetToClick: Barcode?, barcode: MutableList<BarcodeGraphic>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBitmapScanned(sparseArray: SparseArray<Barcode>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRetrievedFailed(reason: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPermissionRequestDenied() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
