package pt.isec.a21270359.tp_amov_new

import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList

object DadosHistorico {

    private val listaProdutos = ArrayList<Produto>()
    private var Data: String? = null


    fun adicionaProduto(produto: Produto) {
        listaProdutos.add(produto)
    }

    fun getProduto(index: Int): Produto {
        return listaProdutos[index]
    }

    fun getListaProdutos(): ArrayList<Produto> {
        return listaProdutos
    }

    fun limpaLista() {
        listaProdutos.clear()
        Data = ""
    }

    fun removeProduto(index: Int) {
        listaProdutos.removeAt(index)
    }

    fun reset() {
        listaProdutos.clear()
        //FALTA LIMPAR DATA
    }

    fun setData(data: String) {
        Data = data
    }

    fun getData(): String? {
        return Data
    }

    override fun toString(): String {
        var str: StringBuilder = StringBuilder()
        str.append(getData() + ":\n")
        for (produto in listaProdutos) {
            str.append(produto.nome + " " + produto.preco + " " + produto.categoria + " " + produto.quantidade + "\n")
        }
        str.append("\n")

        return str.toString()
    }
}