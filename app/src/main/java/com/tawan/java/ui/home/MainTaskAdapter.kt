package com.tawan.java.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tawan.java.R
import com.tawan.java.data.remote.reqres.TasksResponse
import com.tawan.java.databinding.ItemDoesBinding
import com.tawan.java.utils.DateUtils
import java.util.*

class MainTaskAdapter : RecyclerView.Adapter<MainTaskAdapter.AdapterViewHolder>() {

    val data = mutableListOf<TasksResponse.Task>()
    lateinit var adapterInterface: ItemInterface

    fun setWithNewData(data: MutableList<TasksResponse.Task>) {
        this.data.clear()
        this.data.addAll(data)
    }

    fun setupAdapterInterface(obj: ItemInterface) {
        this.adapterInterface = obj
    }

    inner class AdapterViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var binding: ItemDoesBinding = ItemDoesBinding.bind(itemView)

        fun onBind(model: TasksResponse.Task) {
            val mContext = binding.root.context

            binding.tvTaskTitle.text=model.title
            binding.tvTaskDesc.text=model.desc

            binding.deadline.text=DateUtils().getReadableDate(model.deadline)

            binding.root.setOnClickListener {
                if (adapterInterface != null) {
                    adapterInterface.onclick(model)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_does, parent, false)
        return AdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface ItemInterface {
        fun onclick(model: TasksResponse.Task)
    }
}