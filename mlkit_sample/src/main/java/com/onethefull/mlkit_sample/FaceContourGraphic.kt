package com.onethefull.mlkit_sample

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceContour

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/11/25 2:49 오후
 * @desc
 */
class FaceContourGraphic(overlay: GraphicOverlay): GraphicOverlay.Graphic(overlay) {
    private val FACE_POSITION_RADIUS = 10.0f
    private val ID_TEXT_SIZE = 70.0f
    private val ID_Y_OFFSET = 80.0f
    private val ID_X_OFFSET = -70.0f
    private val BOX_STROKE_WIDTH = 5.0f
    private val COLOR_CHOICES = intArrayOf(
        Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.RED, Color.WHITE, Color.YELLOW
    )
    private var currentColorIndex = 0
    private lateinit var facePositionPaint: Paint
    private lateinit var idPaint: Paint
    private lateinit var boxPaint: Paint
    private lateinit var face: Face

    init {
        currentColorIndex = (currentColorIndex + 1) % COLOR_CHOICES.size
        val selectedColor = COLOR_CHOICES[currentColorIndex]

        facePositionPaint = Paint()
        facePositionPaint.color = selectedColor

        idPaint = Paint()
        idPaint.color = selectedColor
        idPaint.textSize = ID_TEXT_SIZE

        boxPaint = Paint()
        boxPaint.color = selectedColor
        boxPaint.style = Paint.Style.STROKE
        boxPaint.strokeWidth = BOX_STROKE_WIDTH
    }

    fun updateFace(face: Face){
        this.face = face
        postInvalidate()
    }

    override fun draw(canvas: Canvas) {
        val face = this.face
        if (!this::face.isInitialized){
            return
        }
        val x = translateX(face.boundingBox.centerX().toFloat())
        val y = translateY(face.boundingBox.centerY().toFloat())
        val xOffset = scaleX(face.boundingBox.width() / 2.0f)
        val yOffset = scaleY(face.boundingBox.height() / 2.0f)
        val left = x - xOffset
        val top = y - yOffset
        val right = x + xOffset
        val bottom = y + yOffset
        canvas.drawRect(left, top, right, bottom, boxPaint)
    }
}