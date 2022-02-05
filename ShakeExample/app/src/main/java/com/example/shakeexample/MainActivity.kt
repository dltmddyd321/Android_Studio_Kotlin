package com.example.shakeexample

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import render.animations.Attention
import render.animations.Bounce
import render.animations.Render
import kotlin.math.sqrt

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var accel: Float = 0.0f
    private var accelCurrent : Float = 0.0f
    private var accelLast : Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        accel = 10f
        accelCurrent = SensorManager.GRAVITY_EARTH
        accelLast = SensorManager.GRAVITY_EARTH
    }

    override fun onSensorChanged(event: SensorEvent?) {
        Log.d("SensorTest", "onSensorChanged()!!")

        val x: Float = event?.values?.get(0) as Float
        val y: Float = event?.values?.get(1) as Float
        val z: Float = event?.values?.get(2) as Float

        accelLast = accelCurrent
        accelCurrent = sqrt((x*x + y*y + z*z).toDouble()).toFloat()

        val delta : Float = accelCurrent - accelLast
        accel = accel * 0.9f + delta

        if(accel > 30) {
            Log.d("SensorTest", "Shake!!!")

            val render = Render(this)

            render.setAnimation(Attention().Wobble(face))
            render.start()

            Handler(Looper.getMainLooper()).postDelayed({
                face.setImageResource(R.drawable.goodface)
            }, 1000L)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.d("SensorTest", "onAccuracyChanged()!!")
    }

    override fun onResume() {
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
        super.onResume()
    }

    override fun onPause() {
        sensorManager.unregisterListener(this)
        super.onPause()
    }
}