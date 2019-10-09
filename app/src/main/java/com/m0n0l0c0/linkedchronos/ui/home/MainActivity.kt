package com.m0n0l0c0.linkedchronos.ui.home

import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.m0n0l0c0.linkedchronos.R
import com.m0n0l0c0.linkedchronos.base.BaseActivity
import com.m0n0l0c0.linkedchronos.interfaces.CheckEmpty
import com.m0n0l0c0.linkedchronos.model.Chrono
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : BaseActivity(), CheckEmpty {

    private val data: ArrayList<Chrono> = ArrayList()
    private var chronoAdapter: ChronoAdapter? = null
    private var actualChrono = 0
    private var actualChronoTimeLeft: Long = 0

    private var ringtone: Ringtone? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        chronoAdapter = ChronoAdapter(data, this)

        recyclerView.adapter = chronoAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringtone = RingtoneManager.getRingtone(applicationContext, notification)

    }

    private fun createNewChrono(timeInMilis: Long) {

        val chrono = object : CountDownTimer(timeInMilis, 1000) {
            override fun onTick(l: Long) {

                Log.d("CHRONO", "Chrono $actualChrono----- Time left = $actualChronoTimeLeft")
                actualChronoTimeLeft = l
            }

            override fun onFinish() {

                Log.d("CHRONO", "Chrono $actualChrono Finished!")
                Toast.makeText(applicationContext, "Chrono number $actualChrono finished!", Toast.LENGTH_SHORT).show()

                actualChrono++
                actualChronoTimeLeft = 0

                if (actualChrono < data.size) {
                    data[actualChrono].countDownTimer!!.start()
                } else {
                    actualChrono = 0
                    data[0].countDownTimer!!.start()
                }

                ringtone!!.play()

                Handler().postDelayed({ ringtone!!.stop() }, 2000)

            }

        }

        val name = resources.getString(R.string.chrono) + " " + (data.size + 1)

        val newChrono = Chrono(timeInMilis, name, chrono)

        chronoAdapter!!.insertData(data.size, newChrono)
        checkEmptyView()

    }

    private fun startChronoChain() {

        if (data.size != 0) {
            data[0].countDownTimer!!.start()
        } else {
            Toast.makeText(this, R.string.no_chronos, Toast.LENGTH_LONG).show()
        }

    }

    private fun resetChronoChain() {

        if (data.size != 0) {
            data[actualChrono].countDownTimer!!.cancel()
            Toast.makeText(this, R.string.chain_stopped, Toast.LENGTH_LONG).show()
        }
    }

    fun createNewChrono(view: View) {
        getChronoTime()
    }

    fun startChronoChain(view: View) {
        startChronoChain()
    }

    private fun getChronoTime() {

        val alertDialog = AlertDialog.Builder(this)
        val editText = EditText(this)
        editText.setHint(R.string.time_in_seconds)
        editText.inputType = InputType.TYPE_CLASS_NUMBER

        alertDialog.setTitle(R.string.new_chrono)
                .setCancelable(true)
                .setTitle(R.string.new_chrono)
                .setView(editText)
                .setPositiveButton(R.string.ok) { dialogInterface, i ->
                    val time = Integer.parseInt(editText.text.toString()).toLong()
                    if (time > 359999000) {
                        Toast.makeText(applicationContext, R.string.number_too_high, Toast.LENGTH_LONG).show()
                    }
                    createNewChrono(time * 1000)
                }

        alertDialog.show()
    }

    fun resetChronoChain(view: View) {
        resetChronoChain()
    }

    override fun checkEmptyView() {
        if (data.size == 0) {
            recyclerView!!.visibility = View.GONE
            empty_view.visibility = View.VISIBLE
        } else {
            recyclerView!!.visibility = View.VISIBLE
            empty_view.visibility = View.GONE
        }
    }
}
