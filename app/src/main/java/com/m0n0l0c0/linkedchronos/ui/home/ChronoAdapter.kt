package com.m0n0l0c0.linkedchronos.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.m0n0l0c0.linkedchronos.R
import com.m0n0l0c0.linkedchronos.interfaces.CheckEmpty
import com.m0n0l0c0.linkedchronos.model.Chrono
import com.m0n0l0c0.linkedchronos.ui.home.ChronoAdapter.ChronoViewHolder
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by juanje on 22/05/16.
 */
class ChronoAdapter(private val data: ArrayList<Chrono>, private val context: Context) : RecyclerView.Adapter<ChronoViewHolder>() {

    class ChronoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.chrono_name)
        private val time: TextView = itemView.findViewById(R.id.chrono_time)

        fun bindChrono(chrono: Chrono) {
            name.text = chrono.name

            val millis = chrono.miliseconds

            time.text = String.format(FORMAT, TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(
                            TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(millis)))
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ChronoViewHolder {

        val itemView = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.list_item, viewGroup, false)

        val delete = itemView.findViewById<ImageView>(R.id.delete_chrono)
        delete.setOnClickListener {
            deleteData(i)
            (context as CheckEmpty).checkEmptyView()
        }

        return ChronoViewHolder(itemView)
    }

    override fun onBindViewHolder(chronoViewHolder: ChronoViewHolder, i: Int) {
        val item = data[i]
        chronoViewHolder.bindChrono(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun insertData(pos: Int, chrono: Chrono) {
        data.add(pos, chrono)
        notifyItemInserted(pos)
    }

    private fun deleteData(pos: Int) {
        data.removeAt(pos)
        notifyItemRemoved(pos)
    }

    companion object {
        private const val FORMAT = "%02d:%02d:%02d"
    }

}
