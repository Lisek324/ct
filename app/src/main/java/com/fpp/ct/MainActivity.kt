package com.fpp.ct

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.fpp.ct.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var imageView: ImageView
    val REQUEST_IMAGE_CAPTURE = 111
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        button = findViewById(R.id.baton)
        imageView = findViewById(R.id.imydz)

        binding.baton.isEnabled = false

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 111)
        }
        else
        {
            binding.baton.isEnabled = true
        }

        binding.baton.setOnClickListener {
            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i,105)
            Toast.makeText(this,"pressed",Toast.LENGTH_SHORT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode==105) {
            val binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            var mojobraz: Bitmap?
            mojobraz = data?.getParcelableExtra<Bitmap>("data")
            //binding.imydz.setImageBitmap(mojobraz)
            Toast.makeText(this,"ok", Toast.LENGTH_SHORT)
            findViewById<ImageView>(R.id.imydz).setImageBitmap(mojobraz)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==111 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            val binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            binding.baton.isEnabled=true
        }
    }
}