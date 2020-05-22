package com.example.hello

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class adaptadorAComprar(

    private val listaItemCantidad: MutableList<Pair<Int,String>>,
    //private val listaItemCantidad: MutableList<Producto>,
    var clickListener: OnCantidadItemClickListener
) :
    RecyclerView.Adapter<adaptadorAComprar.ViewHolderC>() {

    class ViewHolderC(itemView: View) : RecyclerView.ViewHolder(itemView){
        var ADescripcion: TextView = itemView.findViewById(R.id.tvDescripcionPlantilla)
        var APrecio: TextView = itemView.findViewById(R.id.tvPrecioPlantilla)
        var AArticulo: TextView = itemView.findViewById(R.id.tvArticuloPlantilla)
        var ACantidad: TextView = itemView.findViewById(R.id.tvCantidadPlantilla)

        val bttnMas: Button = itemView.findViewById(R.id.bttnAumentar)
        val bttnMenos: Button = itemView.findViewById(R.id.bttnDisminuir)

        fun initialize(item: Pair<Int, Producto>, action: OnCantidadItemClickListener){
            ADescripcion.text = item.second.descripcion
            APrecio.text = item.second.precioUnitario.toString()
            AArticulo.text = item.second.articulo
            ACantidad.text = item.first.toString()

            bttnMas.setOnClickListener{
                action.onMasClick(ACantidad,ACantidad.text.toString().toInt(), item.second.ID.toString())
            }
            bttnMenos.setOnClickListener{
                action.onMenosClick(ACantidad,ACantidad.text.toString().toInt(),item.second.ID.toString())
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): adaptadorAComprar.ViewHolderC {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.plantillacomprar, parent, false)
        return ViewHolderC(itemView)
    }

    override fun getItemCount() = listaItemCantidad.size

    override fun onBindViewHolder(holder: adaptadorAComprar.ViewHolderC, position: Int) {

        val currentItem = listaItemCantidad[position]
        val i = Inventario.listaProductos.find { it.ID.toString() == currentItem.second }

        holder.initialize(Pair(currentItem.first, i) as Pair<Int, Producto>, clickListener)
        /**holder.AArticulo.text = currentItem.articulo
        holder.ADescripcion.text = currentItem.descripcion
        holder.APrecio.text = currentItem.precioUnitario.toString()
        holder.ACantidad.text = "0"**/
    }
}

interface OnCantidadItemClickListener{
    fun onMasClick(textView: TextView, cantidad: Int, id: String)
    fun onMenosClick(textView: TextView, cantidad: Int, id: String)
}
