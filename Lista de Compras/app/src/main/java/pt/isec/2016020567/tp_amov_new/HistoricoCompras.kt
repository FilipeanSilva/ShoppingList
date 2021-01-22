package pt.isec.a21270359.tp_amov_new

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class HistoricoCompras : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico_compras)

        val listaCarrinho: ListView = findViewById<ListView>(R.id.Historico_lista) as ListView
        val arrayCarrinhos: ArrayList<DadosHistorico> = ArrayHistorico.getLista()

        val adapterLista = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayCarrinhos)
        listaCarrinho.adapter = adapterLista

        //val expandableListView: ExpandableListView = findViewById(R.id.Historico_lista)

        val item = HashMap<String, List<String>>()


//        val linuxGroups = java.util.ArrayList<String>()
//        linuxGroups.add("Ubuntu")
//        linuxGroups.add("Linux Mint")
//        linuxGroups.add("Linux Lite")
//        linuxGroups.add("Android")
//
//        item["Linux"] = linuxGroups
//
//        val windowsGroups = java.util.ArrayList<String>()
//        windowsGroups.add("Windows")
//        windowsGroups.add("Windows NT")
//        windowsGroups.add("Windows RT")
//        windowsGroups.add("Windows Mobile")
//
//        item["Windows"] = windowsGroups
//
//        val adapter = HistoricoAdapter(this, item)
//        expandableListView.setAdapter(adapter)

    }

    class HistoricoAdapter(private val context: Context, private val mStringListHashMap: HashMap<Date, List<Produto>>) : BaseExpandableListAdapter() {

        private val mListHeaderGroup: Array<List<Produto>> = mStringListHashMap.values.toTypedArray()
        override fun getGroupCount(): Int {
            return mListHeaderGroup.size
        }

        override fun getChildrenCount(groupPosition: Int): Int {
            return mStringListHashMap[mStringListHashMap.get(groupPosition)]!!.size
        }

        override fun getGroup(groupPosition: Int): Any {
            return mListHeaderGroup[groupPosition]
        }

        override fun getChild(groupPosition: Int, childPosition: Int): Any {
            return mStringListHashMap[mListHeaderGroup[groupPosition]]!![childPosition]
        }

        override fun getGroupId(groupPosition: Int): Long {
            return groupPosition.toLong()
        }

        override fun getChildId(groupPosition: Int, childPosition: Int): Long {
            return (groupPosition * childPosition).toLong()
        }

        override fun hasStableIds(): Boolean {
            return false
        }

        override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {

            val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val convertView = inflater.inflate(R.layout.expandable_list_group, parent, false)


            val textView = convertView.findViewById(R.id.textView) as TextView
            textView.text = getGroup(groupPosition).toString()
            return convertView
        }

        override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {

            val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val convertView = inflater.inflate(R.layout.expandable_list_item, parent, false)

            val textView = convertView.findViewById<TextView>(R.id.textView) as TextView
            textView.text = getChild(groupPosition, childPosition).toString()
            return convertView
        }

        override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
            return false
        }

        init {
//            mListHeaderGroup = mStringListHashMap.keys.toTypedArray()
        }
    }



}