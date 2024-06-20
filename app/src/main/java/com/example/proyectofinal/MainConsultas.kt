package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainConsultas : AppCompatActivity() {

    private lateinit var rvProducto: RecyclerView
    private lateinit var productoAdapter: ProductoAdapter
    private lateinit var database: DatabaseReference

    companion object {
        private const val PRODUCTOS_NODE = "productos"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_consultas)

        rvProducto = findViewById(R.id.rvProductos)
        rvProducto.layoutManager = LinearLayoutManager(this)
        productoAdapter = ProductoAdapter(emptyList())
        rvProducto.adapter = productoAdapter

        database = FirebaseDatabase.getInstance().reference.child(PRODUCTOS_NODE)

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val productos = mutableListOf<Product>()
                for (productSnapshot in snapshot.children) {
                    val producto = productSnapshot.getValue(Product::class.java)
                    producto?.let {
                        productos.add(it)
                    }
                }
                productoAdapter.updateProductos(productos)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainConsultas, "Error al leer los datos: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun regresar(view: View) {
        val intent = Intent(this, MainMenu::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }


}
