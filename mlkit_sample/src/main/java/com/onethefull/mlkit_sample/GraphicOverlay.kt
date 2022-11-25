package com.onethefull.mlkit_sample

import android.content.Context
import android.graphics.Canvas
import android.hardware.camera2.CameraCharacteristics
import android.util.AttributeSet
import android.view.View

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/11/25 10:31 오전
 * @desc
 */
class GraphicOverlay(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val lock = Any()
    private var previewWidth: Int = 0
    private var widthScaleFactor: Float = 1.0f
    private var previewHeight: Int = 0
    private var heightScaleFactor = 1.0f
    private var facing = CameraCharacteristics.LENS_FACING_BACK
    private var graphics = HashSet<Graphic>()

    abstract class Graphic(private val overlay: GraphicOverlay) {
        abstract fun draw(canvas: Canvas)

        fun scaleX(horizontal: Float): Float {
            return horizontal * overlay.widthScaleFactor
        }

        fun scaleY(vertical: Float): Float {
            return vertical * overlay.heightScaleFactor
        }

        fun getApplicationContext(): Context {
            return overlay.context.applicationContext
        }

        fun translateX(x: Float): Float {
            if (overlay.facing == CameraCharacteristics.LENS_FACING_FRONT) {
                return overlay.width - scaleX(x)
            } else {
                return scaleX(x)
            }
        }

        fun translateY(y: Float): Float {
            return scaleY(y)
        }

        fun postInvalidate() {
            overlay.postInvalidate()
        }
    }

    fun clear() {
        synchronized(lock) {
            graphics.clear()
        }
        postInvalidate()
    }

    fun add(graphic: Graphic) {
        synchronized(lock) {
            graphics.add(graphic)
        }
        postInvalidate()
    }

    fun remove(graphic: Graphic) {
        synchronized(lock) {
            graphics.remove(graphic)
        }
        postInvalidate()
    }

    fun setCameraInfo(previewWidth: Int, previewHeight: Int, facing: Int) {
        synchronized(lock) {
            this.previewWidth = previewWidth
            this.previewHeight = previewHeight
            this.facing = facing
        }
        postInvalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        synchronized(lock) {
            if ((previewWidth != 0) && (previewHeight != 0)) {
                widthScaleFactor = (canvas.width.toFloat()) / (previewWidth.toFloat())
                heightScaleFactor = (canvas.height.toFloat()) / previewHeight.toFloat()
            }

            graphics.forEach { graphic ->
                graphic.draw(canvas)
            }
        }
    }
}