package com.example.proyectofinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Product(
    val codigo: String = "",
    val nombre: String = "",
    val categoria: String = "",
    val marca: String = "",
    val precio: Double = 0.0,
    val stock: Int = 0
)

class ProductoAdapter(private var productos: List<Product>) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val codigo: TextView = itemView.findViewById(R.id.txtCodigo)
        val nombre: TextView = itemView.findViewById(R.id.txtNombre)
        val categoria: TextView = itemView.findViewById(R.id.txtCategoria)
        val marca: TextView = itemView.findViewById(R.id.txtMarca)
        val precio: TextView = itemView.findViewById(R.id.txtPrecio)
        val stock: TextView = itemView.findViewById(R.id.txtStock)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_productos, parent, false)
        return ProductoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]
        holder.codigo.text = "Código: ${producto.codigo}"
        holder.nombre.text = "Nombre: ${producto.nombre}"
        holder.categoria.text = "Categoría: ${producto.categoria}"
        holder.marca.text = "Marca: ${producto.marca}"
        holder.precio.text = "Precio: ${producto.precio}"
        holder.stock.text = "Stock: ${producto.stock}"
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    // Nuevo método para actualizar la lista de productos
    fun updateProductos(nuevosProductos: List<Product>) {
        productos = nuevosProductos
        notifyDataSetChanged()
    }
}
