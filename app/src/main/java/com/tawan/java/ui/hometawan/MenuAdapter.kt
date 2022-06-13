package com.tawan.java.ui.hometawan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tawan.java.R
import com.tawan.java.data.remote.reqres.menu.MenuTawanResponsekt
import com.tawan.java.databinding.ItemMenuFullBinding

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.AdapterViewHolder>() {

    val data = mutableListOf<MenuTawanResponsekt.MenuTawanResponsektItem>()
    lateinit var adapterInterface: ItemInterface

    fun setWithNewData(data: MutableList<MenuTawanResponsekt.MenuTawanResponsektItem>) {
        this.data.clear()
        this.data.addAll(data)
    }

    fun setupAdapterInterface(obj: ItemInterface) {
        this.adapterInterface = obj
    }

    inner class AdapterViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var binding: ItemMenuFullBinding = ItemMenuFullBinding.bind(itemView)

        fun onBind(model: MenuTawanResponsekt.MenuTawanResponsektItem) {
            val mContext = binding.root.context

            binding.tvMain.text=model.name
            binding.tvDescription.text=model.desc

            binding.tvPrice.text=model.price

            Glide
                .with(binding.root)
                .load(model.thumbnailUrl)
                .skipMemoryCache(true)
                .dontAnimate()
                .centerCrop()
                .into(binding.ivMainImage)

            binding.root.setOnClickListener {
                if (adapterInterface != null) {
                    adapterInterface.onclick(model)
                }
            }

            binding.bottomContainer.visibility=View.GONE

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu_full, parent, false)
        return AdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface ItemInterface {
        fun onclick(model: MenuTawanResponsekt.MenuTawanResponsektItem)
    }
}