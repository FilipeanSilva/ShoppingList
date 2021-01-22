package pt.isec.a21270359.tp_amov_new

import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        Log.i(TAG, "onCreate main: ")

       // deleteFile("listaCompras.txt");
       // deleteFile("carrinho.txt");
        //deleteFile("historico.txt");
        load()
    }

    fun onNovoProduto(view: View) {
        val intent = Intent(this, NovoProdutoActivity::class.java)
        startActivity(intent)
    }

    fun onListaCompras(view: View) {
        val intent = Intent(this, ListaComprasActivity::class.java)
        startActivity(intent)
    }

    fun onCarrinho(view: View) {
        val intent = Intent(this, CarrinhoActivity::class.java)
        startActivity(intent)
    }

    fun onHistorico(view: View) {
        val intent = Intent(this, HistoricoCompras::class.java)
        startActivity(intent)
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart main: ")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart main: ")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume main: ${DadosHistorico.getListaProdutos().toString()}")
        save()
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause main: ")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop main: ")
        save()
    }


    lateinit var listaString: ArrayList<String>
    fun converteListaParaStrings() {
        listaString = ArrayList<String>()
        listaString.clear()
        val lista: ArrayList<Produto> = DadosLista.getListaProdutos()

        for (produto in lista) {
            listaString.add(produto.nome + ";" + produto.preco + ";" + produto.categoria + ";" + produto.quantidade + "\n")
        }
    }

    fun saveLista() {
        converteListaParaStrings()
        val text: String =
            listaString.toString().replace(", ", "").replace("[", "").replace("]", "")
        var fos: FileOutputStream? = null

        try {
            deleteFile("listaCompras.txt");
            fos = openFileOutput("listaCompras.txt", MODE_PRIVATE)
            fos.write(text.toByteArray())
        } catch (e: FileNotFoundException) {
            Toast.makeText(this, "Nao foi possivel guardar a lista compras", Toast.LENGTH_LONG)
                .show()
        } finally {
            if (fos != null) {
                fos.close()
            }
        }

    }

    lateinit var carrinhoString: ArrayList<String>
    fun converteCarrinhoParaStrings() {
        carrinhoString = ArrayList<String>()
        carrinhoString.clear()
        val lista: ArrayList<Produto> = DadosCarrinho.getListaProdutos()

        for (produto in lista) {
            carrinhoString.add(produto.nome + ";" + produto.preco + ";" + produto.categoria + ";" + produto.quantidade + "\n")
        }
    }

    fun saveCarrinho() {
        converteCarrinhoParaStrings()
        val text: String =
            carrinhoString.toString().replace(", ", "").replace("[", "").replace("]", "")
        var fos: FileOutputStream? = null

        try {
            deleteFile("carrinho.txt");
            fos = openFileOutput("carrinho.txt", MODE_PRIVATE)
            fos.write(text.toByteArray())
        } catch (e: FileNotFoundException) {
            Toast.makeText(this, "Nao foi possivel guardar o carrinho", Toast.LENGTH_LONG)
                .show()
        } finally {
            if (fos != null) {
                fos.close()
            }
        }
    }

    lateinit var historicoString: ArrayList<String>
    fun converteHistoricoParaStrings() {
        historicoString = ArrayList<String>()
        historicoString.clear()
        val lista: ArrayList<DadosHistorico> = ArrayHistorico.getLista()

        var count: Int = 0
        for (i in lista.size - 1 downTo 0) {
            if (count <= 10) {
                for (produto in lista[i].getListaProdutos()) {
                    historicoString.add(
                        lista[i].getData().toString() + ";" + produto.nome + ";" + produto.preco + ";" + produto.categoria + ";" + produto.quantidade + "\n"
                    )
                }
                historicoString.add("+\n")
                count++;
            }
        }
    }


    fun saveHistorico() {
        converteHistoricoParaStrings()
        val text: String =
            historicoString.toString().replace(", ", "").replace("[", "").replace("]", "")
        var fos: FileOutputStream? = null

        try {
            deleteFile("historico.txt");
            fos = openFileOutput("historico.txt", MODE_PRIVATE)
            fos.write(text.toByteArray())
        } catch (e: FileNotFoundException) {
            Toast.makeText(this, "Nao foi possivel guardar o historico", Toast.LENGTH_LONG)
                .show()
        } finally {
            if (fos != null) {
                fos.close()
            }
        }
    }

    fun save() {
        saveLista()
        saveCarrinho()
        saveHistorico()
    }

    fun converteStringsParaLista(sb: ArrayList<String>) {

        val lista: ArrayList<Produto>
        DadosLista.limpaLista()

        for (Linha in sb) {
            var linha = Linha.toString()

            val nome: String = linha.toString().substringBefore(";")
            linha = linha.toString().substringAfter(";")
            linha.removePrefix(";")

            val preco: Double = linha.toString().substringBefore(";").toDouble()
            linha = linha.toString().substringAfter(";")
            linha.removePrefix(";")

            val categoria: String = linha.toString().substringBefore(";")
            linha = linha.toString().substringAfter(";")
            linha.removePrefix(";")

            val quantidade: Int = linha.toString().toInt()

            DadosLista.AdicionaProduto(Produto(nome, preco, categoria, quantidade))
        }
    }

    fun loadLista() {
        var fis: FileInputStream? = null

        try {
            val inputStream: InputStream =
                File("/data/user/0/pt.isec.a21270359.tp_amov_new/files/listaCompras.txt").inputStream()
            val lineList = mutableListOf<String>()

            inputStream.bufferedReader().forEachLine { lineList.add(it) }

            val immutableList = Collections.unmodifiableList(lineList)
            val arrayList: ArrayList<String> = ArrayList(immutableList)

            converteStringsParaLista(arrayList)
        } catch (e: FileNotFoundException) {
            Toast.makeText(
                this,
                "Nao foi possivel encontrar ficheiro listaCompras.txt",
                Toast.LENGTH_LONG
            ).show()
        } finally {
            try {
                if (fis != null) {
                    fis.close()
                }
            } catch (e: IOException) {
                Toast.makeText(
                    this,
                    "Nao foi possivel fechar ficheiro de leitura listaCompras.txt",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun converteStringsParaCarrinho(sb: ArrayList<String>) {

        val lista: ArrayList<Produto>
        DadosCarrinho.limpaLista()

        for (Linha in sb) {
            var linha = Linha.toString()

            val nome: String = linha.toString().substringBefore(";")
            linha = linha.toString().substringAfter(";")
            linha.removePrefix(";")

            val preco: Double = linha.toString().substringBefore(";").toDouble()
            linha = linha.toString().substringAfter(";")
            linha.removePrefix(";")

            val categoria: String = linha.toString().substringBefore(";")
            linha = linha.toString().substringAfter(";")
            linha.removePrefix(";")

            val quantidade: Int = linha.toString().toInt()

            DadosCarrinho.AdicionaProduto(Produto(nome, preco, categoria, quantidade))
        }
    }

    private fun loadCarrinho() {
        var fis: FileInputStream? = null

        try {
            val inputStream: InputStream =
                File("/data/user/0/pt.isec.a21270359.tp_amov_new/files/carrinho.txt").inputStream()
            val lineList = mutableListOf<String>()

            inputStream.bufferedReader().forEachLine { lineList.add(it) }

            val immutableList = Collections.unmodifiableList(lineList)
            val arrayList: ArrayList<String> = ArrayList(immutableList)

            converteStringsParaCarrinho(arrayList)
        } catch (e: FileNotFoundException) {
            Toast.makeText(
                this,
                "Nao foi possivel encontrar ficheiro carrinho.txt",
                Toast.LENGTH_LONG
            ).show()
        } finally {
            try {
                if (fis != null) {
                    fis.close()
                }
            } catch (e: IOException) {
                Toast.makeText(
                    this,
                    "Nao foi possivel fechar ficheiro de leitura carrinho.txt",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }


    fun converteStringsParaHistorico(sb: ArrayList<String>) {

        var lista: ArrayList<DadosHistorico> =  ArrayList<DadosHistorico>()
        ArrayHistorico.limpaLista()

        for (Linha in sb) {
            var linha = Linha.toString()
            var dados: DadosHistorico
            if (linha.get(0) != '+') {
                val data: String = linha.toString().substringBefore(";")
                linha = linha.toString().substringAfter(";")
                linha.removePrefix(";")

                val nome: String = linha.toString().substringBefore(";")
                linha = linha.toString().substringAfter(";")
                linha.removePrefix(";")

                val preco: Double = linha.toString().substringBefore(";").toDouble()
                linha = linha.toString().substringAfter(";")
                linha.removePrefix(";")

                val categoria: String = linha.toString().substringBefore(";")
                linha = linha.toString().substringAfter(";")
                linha.removePrefix(";")

                val quantidade: Int = linha.toString().toInt()

                DadosHistorico.adicionaProduto(Produto(nome,preco,categoria,quantidade))
                DadosHistorico.setData(data)
            }else if(linha.get(0) == '+'){
                lista.add(DadosHistorico)
            }
        }
        ArrayHistorico.setLista(lista)
    }

    fun loadHistorico() {
        var fis: FileInputStream? = null
        ArrayHistorico.limpaLista()

        try {
            val inputStream: InputStream =
                File("/data/user/0/pt.isec.a21270359.tp_amov_new/files/historico.txt").inputStream()
            val lineList = mutableListOf<String>()

            inputStream.bufferedReader().forEachLine { lineList.add(it) }

            val immutableList = Collections.unmodifiableList(lineList)
            val arrayList: ArrayList<String> = ArrayList(immutableList)

            converteStringsParaHistorico(arrayList)
        } catch (e: FileNotFoundException) {
            Toast.makeText(
                this,
                "Nao foi possivel encontrar ficheiro historico.txt",
                Toast.LENGTH_LONG
            ).show()
        } finally {
            try {
                if (fis != null) {
                    fis.close()
                }
            } catch (e: IOException) {
                Toast.makeText(
                    this,
                    "Nao foi possivel fechar ficheiro de leitura historico.txt",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun load() {
        loadLista()
        loadCarrinho()
        loadHistorico()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy main: ")
    }
}