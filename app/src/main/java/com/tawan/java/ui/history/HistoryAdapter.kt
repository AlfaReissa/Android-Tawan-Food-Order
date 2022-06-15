package com.tawan.java.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tawan.java.R
import com.tawan.java.databinding.ItemHistoryBinding
import com.tawan.java.data.remote.reqres.invoice.UserInvoiceResponsekt.UserInvoiceResponsektItem as DataModel

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.AdapterViewHolder>() {

    val data = mutableListOf<DataModel>()
    lateinit var adapterInterface: ItemInterface

    fun setWithNewData(data: MutableList<DataModel>) {
        this.data.clear()
        this.data.addAll(data)
    }

    fun setupAdapterInterface(obj: ItemInterface) {
        this.adapterInterface = obj
    }

    inner class AdapterViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var binding: ItemHistoryBinding = ItemHistoryBinding.bind(itemView)

        fun onBind(model: DataModel) {
            val mContext = binding.root.context

            var listItems = ""
            model.orderedItemNames.forEachIndexed { index, text ->
                listItems+= "${index+1}. $text\n\n";
            }

            binding.tvMain.text = model.totalPriceRupiahFormat
            binding.tvSecondary.text = model.totalPriceRupiahFormat
            binding.tvDescription.text=listItems
            binding.tvDate.text = model.createdAt

            binding.root.setOnClickListener {
                if (adapterInterface != null) {
                    adapterInterface.onclick(model)
                }
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return AdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface ItemInterface {
        fun onclick(model: DataModel)
        fun onDelete(model: DataModel)
        fun onEdit(model: DataModel)
    }
}