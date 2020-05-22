package com.example.hello

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adaptadorAInventario(
    private val ListaItemProductos: MutableList<Producto>,
    var clickListener: OnInventarioItemClickListener
) :
    RecyclerView.Adapter<adaptadorAInventario.ViewHolder>() {

    //Este método solo se crea una vez
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // una variable que hace referencia al id de la plantillainventario
        var ADescripcion: TextView = itemView.findViewById(R.id.etCuarto)
        var APrecio: TextView = itemView.findViewById(R.id.etTercero)
        var AArticulo: TextView = itemView.findViewById(R.id.etSegundo)
        var AID: TextView = itemView.findViewById(R.id.etPrimero)

        val bttnEditar: Button = itemView.findViewById(R.id.bttnEditar)

        fun initialize(item: Producto, action: OnInventarioItemClickListener) {
            ADescripcion.text = item.descripcion
            APrecio.text = item.precioUnitario.toString()
            AArticulo.text = item.articulo
            AID.text = item.ID.toString()

            bttnEditar.setOnClickListener {
                action.onProductoClick(item, adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.plantillainventario, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = ListaItemProductos.size

    //esta funcion es el iterador, por eso lo del viewHolder no se pone aquí
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //este es el iterador, la lista es la lista de items a mostrar
        val currentItem = ListaItemProductos[position]
        //el holder cada plantillainventario, se cambia por la informacion de la clase Item


        holder.initialize(currentItem, clickListener)
        /**holder.AArticulo.text = currentItem.articulo
        holder.ADescripcion.text = currentItem.descripcion
        holder.APrecio.text = currentItem.precioUnitario.toString()
         **/
    }
}

interface OnInventarioItemClickListener {
    fun onProductoClick(item: Producto, position: Int)
    fun onCotizacionClick(item: Cotizacion, position: Int)
}




