package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

data class Producto(
    val codigo: String,
    val nombre: String,
    val categoria: String,
    val marca: String,
    val precio: Double,
    val stock: Int
)

class MainAltas : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_altas)

        // Inicializa la referencia a la base de datos
        database = FirebaseDatabase.getInstance().reference

        val etCodigo = findViewById<EditText>(R.id.etCodigoMo)
        val etNombre = findViewById<EditText>(R.id.etNombreN)
        val etCategoria = findViewById<EditText>(R.id.etCategoriaN)
        val etMarca = findViewById<EditText>(R.id.etMarcaN)
        val etPrecio = findViewById<EditText>(R.id.etPrecioN)
        val etStock = findViewById<EditText>(R.id.etStockN)
        val btnGuardar = findViewById<Button>(R.id.btnModificar)

        btnGuardar.setOnClickListener {
            val codigo = etCodigo.text.toString()
            val nombre = etNombre.text.toString()
            val categoria = etCategoria.text.toString()
            val marca = etMarca.text.toString()
            val precio = etPrecio.text.toString().toDoubleOrNull() ?: 0.0
            val stock = etStock.text.toString().toIntOrNull() ?: 0

            if (codigo.isNotEmpty() && nombre.isNotEmpty() && categoria.isNotEmpty() && marca.isNotEmpty()) {
                try {
                    val producto = Producto(codigo, nombre, categoria, marca, precio, stock)
                    database.child("productos").child(codigo).setValue(producto)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Producto registrado", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Error al registrar el producto", Toast.LENGTH_SHORT).show()
                        }
                } catch (e: Exception) {
                    Log.e("MainAltas", "Error al registrar el producto", e)
                    Toast.makeText(this, "Error al registrar el producto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun regresar(view: View) {
        val intent = Intent(this, MainMenu::class.java)
        startActivity(intent)
    }
}
