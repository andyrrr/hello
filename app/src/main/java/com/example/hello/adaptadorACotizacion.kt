package com.example.hello

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adaptadorACotizacion(
    private val ListaCotizaciones: MutableList<Cotizacion>,
    var clickListener: OnInventarioItemClickListener
) : RecyclerView.Adapter<adaptadorACotizacion.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var AID: TextView = itemView.findViewById(R.id.etPrimero)
        var AFecha: TextView = itemView.findViewById(R.id.etSegundo)
        var ACliente: TextView = itemView.findViewById(R.id.etTercero)
        var AProductos: TextView = itemView.findViewById(R.id.etCuarto)

        val bttnEditar: Button = itemView.findViewById(R.id.bttnEditar)

        var tvID: TextView = itemView.findViewById(R.id.tvPrimero)
        var tvFecha: TextView = itemView.findViewById(R.id.tvSegundo)
        var tvCliente: TextView = itemView.findViewById(R.id.tvTercero)
        var tvProductos: TextView = itemView.findViewById(R.id.tvCuarto)


        fun initialize(item: Cotizacion, action: OnInventarioItemClickListener) {
            tvID.text = "ID"
            tvFecha.text = "Fecha"
            tvCliente.text = "Cliente"
            tvProductos.text = "Total de productos"

            AID.text = item.ID.toString()
            AFecha.text = item.fecha
            ACliente.text = item.nombre
            AProductos.text = item.listaProductos.size.toString()

            bttnEditar.setOnClickListener {
                action.onCotizacionClick(item, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): adaptadorACotizacion.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.plantillainventario, parent, false)
        return ViewHolder(itemView)

    }

    override fun getItemCount(): Int = ListaCotizaciones.size

    override fun onBindViewHolder(holder: adaptadorACotizacion.ViewHolder, position: Int) {
        //este es el iterador, la lista es la lista de items a mostrar
        val currentItem = ListaCotizaciones[position]
        //el holder cada plantillainventario, se cambia por la informacion de la clase Item

        holder.initialize(currentItem, clickListener)

    }
}