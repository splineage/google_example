package com.onethefull.mlkit_sample

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.gms.tasks.OnSuccessListener
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceContour
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.onethefull.mlkit_sample.databinding.ActivityMainBinding
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding : ActivityMainBinding
    private lateinit var selectedImage: Bitmap
    private var imageMaxWidth = 0
    private var imageMaxHeight = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnFace.setOnClickListener {
            runFaceContourDetection()
        }

        val items = arrayOf(
            "Test Image 1 (Text)",
            "Test Image 2 (Face)"
        )
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            items
        )
        binding.spinner.apply {
            setAdapter(adapter)
            onItemSelectedListener = this@MainActivity
        }
    }

    private fun runFaceContourDetection(){
        val inputImage: InputImage = InputImage.fromBitmap(selectedImage, 0)
        val options = FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
            .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
            .build()
        binding.btnFace.isEnabled = false
        val detector = FaceDetection.getClient(options)
        detector.process(inputImage)
            .addOnSuccessListener { faces ->
                binding.btnFace.isEnabled = true
                processFaceContourDetectionResult(faces)
            }
            .addOnFailureListener { e ->
                binding.btnFace.isEnabled = true
                e.printStackTrace()
            }
    }

    private fun processFaceContourDetectionResult(faces: List<Face>){
        if (faces.isEmpty()){
            Toast.makeText(this,"No face found", Toast.LENGTH_SHORT).show()
            return
        }
        binding.graphicOverlay.clear()
        for (face in faces){
            val faceGraphic = FaceContourGraphic(binding.graphicOverlay)
            binding.graphicOverlay.add(faceGraphic)
            faceGraphic.updateFace(face)
        }
    }

    private fun getBitmapFromAsset(context: Context, filePath: String): Bitmap{
        val assetManager = context.assets
        lateinit var inputStream: InputStream
        lateinit var bitmap: Bitmap
        try {
            inputStream = assetManager.open(filePath)
            bitmap = BitmapFactory.decodeStream(inputStream)
        }catch (e: IOException){
            e.printStackTrace()
        }
        return bitmap
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
    {
        binding.graphicOverlay.clear()
        when(position){
            0 ->{
                selectedImage = getBitmapFromAsset(this, "Please_walk_on_the_grass.jpg")
            }
            1 ->{
                selectedImage = getBitmapFromAsset(this, "grace_hopper.jpg")
            }
        }
        if (this::selectedImage.isInitialized){
            val targetedSize = getTargetedWithHeight()
            val targetWidth = targetedSize.first
            val maxHeight = targetedSize.second
            val scaleFactor =
                Math.max(
                    selectedImage.width.toFloat() / targetWidth.toFloat(),
                    selectedImage.height.toFloat() / maxHeight.toFloat()
                )
            val resizedBitmap =
                Bitmap.createScaledBitmap(
                    selectedImage,
                    (selectedImage.width / scaleFactor).toInt(),
                    (selectedImage.height / scaleFactor).toInt(),
                    true
                )
            binding.imageView.setImageBitmap(resizedBitmap)
            selectedImage = resizedBitmap
        }
    }

    private fun getTargetedWithHeight(): Pair<Int, Int>{
        val maxWidthForPortraitMode = getImageMaxWidth()
        val maxHeightForPortraitMode = getImageMaxHeight()
        return Pair(maxWidthForPortraitMode, maxHeightForPortraitMode)
    }

    private fun getImageMaxHeight(): Int{
        if (imageMaxHeight == 0){
            imageMaxHeight = binding.imageView.height
        }
        return imageMaxHeight
    }

    private fun getImageMaxWidth(): Int{
        if (imageMaxWidth == 0){
            imageMaxWidth = binding.imageView.width
        }
        return imageMaxWidth
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}