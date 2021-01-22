package pt.isec.a21270359.tp_amov_new

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import java.text.NumberFormat

const val TAG = "logs"

class NovoProdutoActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate novoProduto: ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_produto)


        var btnAddProduto: Button = findViewById(R.id.novoProduto_btnAdiciona)
        btnAddProduto.setOnClickListener(adicionaProduto())

        var quantidade: NumberPicker = findViewById(R.id.novoProduto_Qtd)
        quantidade.minValue = 1
        quantidade.maxValue = 99

        var categoria: Spinner = findViewById(R.id.novoProduto_spinner)
        var adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.categorias,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        categoria.adapter = adapter

//        if (savedInstanceState != null) {
//            var nome: String? = savedInstanceState.getString("nome")
//            var preco: Double = savedInstanceState.getDouble("preco")
//            var categoria: Int = savedInstanceState.getInt("categoria Index")
//            var quantidade: Int = savedInstanceState.getInt("quantidade")
//
//
//            restauraApp(nome, preco, categoria, quantidade)
//        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val nomeET = findViewById<EditText>(R.id.novoProduto_Nome) as EditText
        var nome = nomeET.text.toString()
        if (nome.length < 1) {
            nome = ""
        }

        val precoET = findViewById<EditText>(R.id.novoProduto_Preco) as EditText
        var precoStr = precoET.text.toString()
        var preco: Double
        if (precoStr.length < 1) {
            precoStr = ""
            preco = 0.0
        } else {
            try {
                preco = precoStr.toDouble()
            } catch (e: NumberFormatException) {
                preco = 0.0
            }
        }
        val categoriaSp = findViewById<EditText>(R.id.novoProduto_spinner) as Spinner
        val categoria = categoriaSp.selectedItemPosition

        val quantidadeNp = findViewById<EditText>(R.id.novoProduto_Qtd) as NumberPicker
        val quantidade = quantidadeNp.value

        outState.putString("nome", nome)
        outState.putDouble("preco", preco)
        outState.putInt("categoria Index", categoria)
        outState.putInt("quantidade", quantidade)
    }

    private fun restauraApp(nomeX: String?, precoX: Double, categoriaX: Int, quantidadeX: Int) {
        var btnAddProduto: Button = findViewById(R.id.novoProduto_btnAdiciona)
        btnAddProduto.setOnClickListener(adicionaProduto())

        var quantidade: NumberPicker = findViewById(R.id.novoProduto_Qtd)
        quantidade.minValue = 1
        quantidade.maxValue = 99
        quantidade.value = quantidadeX

        var categoria: Spinner = findViewById(R.id.novoProduto_spinner)
        var adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.categorias,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        categoria.setSelection(categoriaX)
        categoria.adapter = adapter

        val nomeET = findViewById<EditText>(R.id.novoProduto_Nome) as EditText
        if (nomeX != null) {
            if (nomeX.length >= 1) {
                nomeET.setText(nomeX)
            } else {
                nomeET.setText("");
            }
        }

        val precoET = findViewById<EditText>(R.id.novoProduto_Preco) as EditText
        val formatter: NumberFormat = DecimalFormat("#0")
        if (precoX > 0) {
            precoET.setText(formatter.format(precoX))
        } else {
            precoET.setSelection(0)
        }
    }


    fun refazAtividade() {
        var btnAddProduto: Button = findViewById(R.id.novoProduto_btnAdiciona)
        btnAddProduto.setOnClickListener(adicionaProduto())

        var quantidade: NumberPicker = findViewById(R.id.novoProduto_Qtd)
        quantidade.minValue = 1
        quantidade.maxValue = 99
        quantidade.value = 1

        var categoria: Spinner = findViewById(R.id.novoProduto_spinner)
        var adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.categorias,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        categoria.setSelection(0)
        categoria.adapter = adapter

        val nomeET = findViewById<EditText>(R.id.novoProduto_Nome) as EditText
        nomeET.setText("");

        val precoET = findViewById<EditText>(R.id.novoProduto_Preco) as EditText
        precoET.setText("");
    }

    inner class adicionaProduto : View.OnClickListener {
        override fun onClick(v: View?) {
            val nomeET = findViewById<EditText>(R.id.novoProduto_Nome) as EditText
            val precoET = findViewById<EditText>(R.id.novoProduto_Preco) as EditText

            val nome = nomeET.text.toString()

            if (nome.length < 1) {
                AlertDialog.Builder(this@NovoProdutoActivity)
                    .setTitle("Erro no nome introduzido")
                    .setMessage("Necessita de inserir um nome") // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
                return
            }

            val precoStr = precoET.text.toString()
            if (precoStr.length < 1) {
                AlertDialog.Builder(this@NovoProdutoActivity)
                    .setTitle("Erro no preco introduzido")
                    .setMessage("Necessita de inserir um preco") // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
                return
            }

            var preco: Double
            try {
                preco = precoStr.toDouble()
            } catch (e: NumberFormatException) {
                AlertDialog.Builder(this@NovoProdutoActivity)
                    .setTitle("Erro no preco introduzido")
                    .setMessage("Necessita de inserir um preco valido") // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
                return
            }

            val categoriaSp = findViewById<EditText>(R.id.novoProduto_spinner) as Spinner
            val categoria = categoriaSp.selectedItem

            val quantidadeNp = findViewById<EditText>(R.id.novoProduto_Qtd) as NumberPicker
            val quantidade = quantidadeNp.value

            val produto = Produto(nome, preco, categoria.toString(), quantidade.toInt())
            DadosLista.AdicionaProduto(produto)
            Toast.makeText(this@NovoProdutoActivity, "Produto adicionado", Toast.LENGTH_LONG).show()
            refazAtividade()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart novoProduto: ")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart novoProduto: ")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume novoProduto: ")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause novoProduto: ")
    }

    override fun onStop() {
        super.onStop()
        finish()
        Log.i(TAG, "onStop novoProduto: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy novoProduto: ")
    }
}