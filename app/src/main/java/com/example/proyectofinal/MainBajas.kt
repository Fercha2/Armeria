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

class MainBajas : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    companion object {
        private const val PRODUCTOS_NODE = "productos"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_bajas)

        // Inicializa la referencia a la base de datos
        database = FirebaseDatabase.getInstance().reference

        val etCodigoBaja = findViewById<EditText>(R.id.etCodigoBaja)
        val btnBorrar = findViewById<Button>(R.id.btnBorrar)

        btnBorrar.setOnClickListener {
            val codigoEliminar = etCodigoBaja.text.toString()

            if (codigoEliminar.isNotEmpty()) {
                // Verifica si el producto existe antes de eliminarlo
                database.child(PRODUCTOS_NODE).child(codigoEliminar).get().addOnSuccessListener {
                    if (it.exists()) {
                        database.child(PRODUCTOS_NODE).child(codigoEliminar).removeValue()
                            .addOnSuccessListener {
                                Toast.makeText(this, "Producto eliminado", Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener { error ->
                                Toast.makeText(this, "Error al eliminar el producto: ${error.message}", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(this, "El producto no existe", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener { error ->
                    Toast.makeText(this, "Error al verificar el producto: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, ingresa el código del producto a eliminar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun regresar(view: View) {
        val intent = Intent(this, MainMenu::class.java)
        startActivity(intent)
    }
}
