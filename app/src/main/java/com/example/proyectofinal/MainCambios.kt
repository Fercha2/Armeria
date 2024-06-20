package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainCambios : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    companion object {
        private const val PRODUCTOS_NODE = "productos"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_cambios)

        // Inicializa la referencia a la base de datos
        database = FirebaseDatabase.getInstance().reference

        // Referencias a las vistas
        val etCodigoMo = findViewById<EditText>(R.id.etCodigoMo)
        val etNombreN = findViewById<EditText>(R.id.etNombreN)
        val etCategoriaN = findViewById<EditText>(R.id.etCategoriaN)
        val etMarcaN = findViewById<EditText>(R.id.etMarcaN)
        val etPrecioN = findViewById<EditText>(R.id.etPrecioN)
        val etStockN = findViewById<EditText>(R.id.etStockN)
        val btnModificar = findViewById<Button>(R.id.btnModificar)

        btnModificar.setOnClickListener {
            // Obtener los valores de los EditText
            val codigoModificar = etCodigoMo.text.toString()
            val nuevoNombre = etNombreN.text.toString()
            val nuevaCategoria = etCategoriaN.text.toString()
            val nuevaMarca = etMarcaN.text.toString()
            val nuevoPrecio = etPrecioN.text.toString().toDoubleOrNull() ?: 0.0
            val nuevoStock = etStockN.text.toString().toIntOrNull() ?: 0

            // Verificar que se haya ingresado el código a modificar
            if (codigoModificar.isNotEmpty()) {
                // Crear el mapa con los nuevos valores del producto
                val productoActualizado = mapOf(
                    "nombre" to nuevoNombre,
                    "categoria" to nuevaCategoria,
                    "marca" to nuevaMarca,
                    "precio" to nuevoPrecio,
                    "stock" to nuevoStock
                )

                // Actualizar los datos en la base de datos
                database.child(PRODUCTOS_NODE).child(codigoModificar).updateChildren(productoActualizado)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Producto modificado", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { error ->
                        Toast.makeText(this, "Error al modificar el producto: ${error.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Por favor, ingresa el código del producto a modificar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun regresar(view: View) {
        val intent = Intent(this, MainMenu::class.java)
        startActivity(intent)
    }
}
