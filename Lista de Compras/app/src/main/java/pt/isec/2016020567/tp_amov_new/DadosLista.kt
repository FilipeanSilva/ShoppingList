package pt.isec.a21270359.tp_amov_new

object DadosLista {

    private val listaProdutos = ArrayList<Produto>()


    fun AdicionaProduto(produto: Produto) {
        listaProdutos.add(produto)
    }
    fun getProduto(index: Int) : Produto {
        return listaProdutos[index]
    }

    fun getListaProdutos(): ArrayList<Produto>{
        return listaProdutos
    }

    fun limpaLista(){
        listaProdutos.clear()
    }

    fun removeProduto(index: Int){
        listaProdutos.removeAt(index)
    }

}