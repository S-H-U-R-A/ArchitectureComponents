package com.essa.curso.jetpack.architecturecomponents.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.essa.curso.jetpack.architecturecomponents.R
import com.essa.curso.jetpack.architecturecomponents.data.database.CompanieroEntity
import kotlinx.android.synthetic.main.activity_architecture_comp.view.*

class CompanieroAdapter(
    val companieros: List<CompanieroEntity>?
): RecyclerView.Adapter<CompanieroAdapter.CompanieroViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CompanieroViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_architecture_comp, parent, false))

    override fun getItemCount(): Int = companieros?.size ?: 0

    override fun onBindViewHolder(holder: CompanieroViewHolder, position: Int) {
        companieros?.let {
            holder.bind( it[position] )
        }
    }

    inner class CompanieroViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var TAG = "CompanieroViewHolder"

        fun bind(item: CompanieroEntity) = with(itemView){
            architecture_comp_view_nombre.text =        item.nombre
            architecture_comp_view_compania.text =      item.compania
            architecture_comp_view_email.text =         item.email
            architecture_comp_compi_view_telf.text =    item.telf
            architecture_comp_view_contenedor.setOnClickListener {
                val mensaje = "${item.nombre} :: ${item.compania}"
                Log.d(TAG, mensaje)
            }
        }
    }

}



