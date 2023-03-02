package ru.samsung.itacademy.mdev.shareintentexample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val handler: View.OnClickListener = View.OnClickListener { v ->
            when (v.id) {
                R.id.buttonShareTextUrl -> shareTextUrl()
                R.id.buttonShareImage -> shareImage()
            }
        }

        findViewById<View>(R.id.buttonShareTextUrl).setOnClickListener(handler)
        findViewById<View>(R.id.buttonShareImage).setOnClickListener(handler)
    }

    private fun shareTextUrl() {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

        share.putExtra(Intent.EXTRA_SUBJECT, "Hello from my app")
        share.putExtra(Intent.EXTRA_TEXT, "http://myitschool.ru")
        startActivity(Intent.createChooser(share, "Share link!"))
        Toast.makeText(applicationContext, "Text shared", Toast.LENGTH_LONG).show()
    }

    private fun shareImage() {
        val share = Intent(Intent.ACTION_SEND)

        share.type = "image/*"

        val imagePath: String = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                .toString() + "/myImage.png"
        val imageFileToShare = File(imagePath)
        val uri: Uri = Uri.fromFile(imageFileToShare)
        share.putExtra(Intent.EXTRA_STREAM, uri)
        startActivity(Intent.createChooser(share, "Share Image!"))
        Toast.makeText(applicationContext, "Image shared", Toast.LENGTH_LONG).show()
    }

}

