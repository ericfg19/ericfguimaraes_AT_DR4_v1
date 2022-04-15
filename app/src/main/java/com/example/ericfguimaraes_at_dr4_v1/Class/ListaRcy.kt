package com.example.ericfguimaraes_at_dr4_v1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ericfguimaraes_at_dr4_v1.Model.Anotacao
import java.text.SimpleDateFormat

class ListaRcy(private val dataSet: List<Anotacao>) :
    RecyclerView.Adapter<ListaRcy.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rcyData: TextView
        val rcyAnotacao: TextView
        val rcyTitulo: TextView
        val imgData: ImageView


        init {

            rcyData = view.findViewById(R.id.rcyData)
            rcyAnotacao = view.findViewById(R.id.rcyAnotacao)
            rcyTitulo = view.findViewById(R.id.rcyTitulo)
            imgData = view.findViewById(R.id.imgData)
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.activity_lista, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {


        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        viewHolder.rcyData.text = formatter.format(dataSet[position].data)
        viewHolder.rcyAnotacao.text = dataSet[position].anotacao
        viewHolder.rcyTitulo.text = dataSet[position].titulo
    }


    override fun getItemCount() = dataSet.size

}
