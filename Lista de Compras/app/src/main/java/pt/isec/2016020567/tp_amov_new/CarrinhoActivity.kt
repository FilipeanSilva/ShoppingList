package pt.isec.a21270359.tp_amov_new

import android.content.Context
import android.icu.text.DateIntervalInfo
import android.icu.util.TimeZone
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class CarrinhoActivity : AppCompatActivity() {

    val historicos = mutableListOf<ArrayList<Produto>>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrinho)

        val listaProdutos: ListView = findViewById<ListView>(R.id.listaCarrinho_lista) as ListView
        val arrayProdutos: ArrayList<Produto> = DadosCarrinho.getListaProdutos()

        val adapterLista = ListaComprasActivity.ListaAdapter(this, arrayProdutos)
        listaProdutos.adapter = adapterLista
//        val listaCarrinho: ListView = findViewById<ListView>(R.id.listaCarrinho_lista) as ListView
//        var arrayProdutos: ArrayList<Produto> = DadosCarrinho.getListaProdutos()
//
//        val adapterLista = ListaComprasActivity.ListaAdapter(this, arrayProdutos)
//        listaCarrinho.adapter = adapterLista

        val arrayCompras: ArrayList<Produto> = DadosCarrinho.getListaProdutos()

        var finaliza: Button = findViewById(R.id.listaCompras_Adicionar)
        finaliza.setOnClickListener {

            /*


            for (i in arrayCompras.size - 1 downTo 0) {
                if (listaProdutos.getChildAt(i).findViewById(R.id.listaAdapter_CheckBox) as CheckBox != null) {
                    val ckbx : CheckBox = listaProdutos.getChildAt(i).findViewById<View>(R.id.listaAdapter_CheckBox) as CheckBox
                    if (ckbx.isChecked) {
                        DadosLista.removeProduto(i)
                        contador++
                    }
                }
            }
             */

            val current = LocalDateTime.now()

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val formatted = current.format(formatter)

            var carrinho:Carrinho = Carrinho((DadosCarrinho.getListaProdutos()))

            for (produto in DadosCarrinho.getListaProdutos()){
                DadosHistorico.adicionaProduto(produto)
            }
            DadosHistorico.setData(formatted)
//            DadosCarrinho.setData(formatted)
//
//            val dataX: String? = DadosCarrinho.getData()

            //Historico.AdicionaCarrinho(DadosCarrinho)
            DadosCarrinho.reset() // Limpa o carrinho apos finalizar a compra
            adapterLista.notifyDataSetChanged()

            ArrayHistorico.add(DadosHistorico)

            val data = formatted
            Toast.makeText(this, "Finalizou Compra às $data", Toast.LENGTH_LONG).show()
        }
    }

    class ListaAdapter(private val context: Context, private val dataSource: ArrayList<Produto>) :
        BaseAdapter() {

        private val originalData: List<String>? = null
        private val filteredData: List<String>? = null
//    private val mFilter : ItemFilter = ItemF

        override fun getCount(): Int {
            return dataSource.size
        }

        override fun getItem(position: Int): Any {
            return dataSource[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            val inflater: LayoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val convertView = inflater.inflate(R.layout.lista_adapter, parent, false)

            val nome = dataSource[position].nome
            val preco = dataSource[position].preco
            val qtd = dataSource[position].quantidade
            val ctg = dataSource[position].categoria

            val prod = nome + " (" + ctg + ")" + " - " + preco + "€"

            val txtProd: TextView = convertView.findViewById(R.id.listaAdapter_Produto) as TextView
            txtProd.text = prod;

            val editQtd: TextView = convertView.findViewById(R.id.listaAdapter_Qtd) as TextView
            editQtd.text = qtd.toString();

            return convertView
        }

//    override fun getFilter(): Filter {
//        TODO("Not yet implemented")
//    }
    }
}