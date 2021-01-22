package pt.isec.a21270359.tp_amov_new

import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList

object ArrayHistorico {

    private var lista = ArrayList<DadosHistorico>()

    fun add(produto: DadosHistorico) {
        lista.add(produto)
    }

    fun get(index: Int): DadosHistorico {
        return lista[index]
    }

    fun getLista(): ArrayList<DadosHistorico> {
        return lista
    }

    fun limpaLista() {
        lista.clear()
    }

    fun setLista(Lista: ArrayList<DadosHistorico>) {
        lista = Lista
    }

    fun removeProduto(index: Int) {
        lista.removeAt(index)
    }

    fun clean() {
        lista.clear()
    }

    override fun toString(): String {
        var str: StringBuilder = StringBuilder()
        for (dados in lista) {
            str.append(dados.getData() + ":\n")
            for (produto in dados.getListaProdutos()) {
                str.append(produto.nome + " " + produto.preco + " " + produto.categoria + " " + produto.quantidade + "\n")
            }
            str.append("\n")
        }
        return str.toString()
    }
}