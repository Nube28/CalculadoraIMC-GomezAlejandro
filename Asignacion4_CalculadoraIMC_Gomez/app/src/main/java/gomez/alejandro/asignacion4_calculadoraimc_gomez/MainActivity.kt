package gomez.alejandro.asignacion4_calculadoraimc_gomez

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() 
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvPeso: EditText = findViewById(R.id.tvPeso) as EditText
        val tvEstatura: EditText = findViewById(R.id.tvEstatura) as EditText
        val btnCalcular: Button = findViewById(R.id.btnCalcular) as Button
        val imc: TextView = findViewById(R.id.imc) as TextView
        val range: TextView = findViewById(R.id.range) as TextView

        btnCalcular.setOnClickListener {
            val peso = tvPeso.text.toString().toFloatOrNull()
            val estatura = tvEstatura.text.toString().toFloatOrNull()

            if (peso != null && estatura != null && estatura > 0) {
                val resultadoIMC = peso / (estatura * estatura)
                imc.text = String.format("IMC: %.2f", resultadoIMC)

                if (resultadoIMC < 18.5 ){
                    range.text = "Bajo peso"
                    range.setBackgroundColor(R.color.colorYellow)
                }
                //me gusta kotlin puedo hacer esta locura, gracias señor de stackoverflow
                else if (resultadoIMC in 18.5..24.9 ){
                    range.text = "Saludable"
                    range.setBackgroundColor(R.color.colorGreen)
                }
                else if (resultadoIMC in 18.5..24.9){
                    range.text = "Sobrepeso"
                    range.setBackgroundColor(R.color.colorYellow)
                }
                else{
                    range.text = "Obesidad"
                    range.setBackgroundColor(R.color.colorOrange)
                }

            } else {
                imc.text = "Datos inválidos (╯°□°）╯︵ ┻━┻"
                range.text = ""
            }
        }
    }
}