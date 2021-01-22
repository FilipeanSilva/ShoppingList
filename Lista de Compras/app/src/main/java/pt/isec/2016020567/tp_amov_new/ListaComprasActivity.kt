package pt.isec.a21270359.tp_amov_new

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class ListaComprasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_compras)

        val listaProdutos: ListView = findViewById<ListView>(R.id.listaCompras_lista) as ListView
        val arrayProdutos: ArrayList<Produto> = DadosLista.getListaProdutos()

        val adapterLista = ListaAdapter(this, arrayProdutos)
        listaProdutos.adapter = adapterLista

        var spinnerCategoria: Spinner = findViewById(R.id.listaCompras_Spinner)

        var adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.categorias, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
//        adapter.add("Todos")
        spinnerCategoria.adapter = adapter

//        spinnerCategoria.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//
//            }
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                adapterLista.
//
//            }
//        }

        val btnAdicionar: Button = findViewById<View>(R.id.listaCompras_Adicionar) as Button
        val btnRemover: Button = findViewById<View>(R.id.listaCompras_Remover) as Button

        btnRemover.setOnClickListener(){
            var contador : Int = 0
            for (i in arrayProdutos.size - 1 downTo 0) {
                if (listaProdutos.getChildAt(i).findViewById(R.id.listaAdapter_CheckBox) as CheckBox != null) {
                    val ckbx : CheckBox = listaProdutos.getChildAt(i).findViewById<View>(R.id.listaAdapter_CheckBox) as CheckBox
                    if (ckbx.isChecked) {
                        DadosLista.removeProduto(i)
                        contador++
                    }
                }
            }
            if(contador != 0){
                Toast.makeText(this, "Foram removidos " + contador + " produtos do carrinho.", Toast.LENGTH_LONG).show() // O I nao ta a funfar
                adapterLista.notifyDataSetChanged() // Diz ao adapter para atualizar a lista
            }
        }

        btnAdicionar.setOnClickListener(){
            var contador : Int = 0
            for (i in arrayProdutos.size - 1 downTo 0) {
                if (listaProdutos.getChildAt(i).findViewById(R.id.listaAdapter_CheckBox) as CheckBox != null) {
                    val ckbx : CheckBox = listaProdutos.getChildAt(i).findViewById<View>(R.id.listaAdapter_CheckBox) as CheckBox
                    if (ckbx.isChecked) {
                        DadosCarrinho.AdicionaProduto(DadosLista.getProduto(i)) //QUANDO ADICIONA AO CARRINHO REMOVE OS DA LISTA
                        DadosLista.removeProduto(i)
                        contador++
                    }
                }
            }
            if(contador != 0){
                Toast.makeText(this, "Foram adicionados " + contador + " produtos ao carrinho com sucesso!", Toast.LENGTH_LONG).show() // O I nao ta a funfar
                adapterLista.notifyDataSetChanged() // Diz ao adapter para atualizar a lista
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
    }

    class ListaAdapter(private val context: Context, private val dataSource: ArrayList<Produto>) : BaseAdapter(){

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

            val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val convertView = inflater.inflate(R.layout.lista_adapter, parent, false)

            val nome = dataSource[position].nome
            val preco = dataSource[position].preco
            val qtd = dataSource[position].quantidade
            val ctg =  dataSource[position].categoria



            val prod = nome + " (" + ctg + ")" + " - " + preco + context.resources.getString(R.string.moeda)

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