package com.example.leboncoin.presentation.main


import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Advert
import com.example.leboncoin.App
import com.example.leboncoin.R
import com.example.leboncoin.databinding.SingleItemBinding
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import dagger.hilt.EntryPoint
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class RvAdapter @Inject constructor(
    var advertList: List<Advert>,
    var picasso: Picasso
) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: SingleItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(advertList[position]) {
                binding.title.text = this.title
                binding.url.text = this.url
                picasso.load(this.thumbnailUrl)
                    .error(R.drawable.ic_launcher_background)
                    .into(binding.thumbnail);
            }
        }
    }

    override fun getItemCount(): Int {
        return advertList.size
    }

    fun updateData(advertList: List<Advert>) {
        this.advertList = advertList
        notifyDataSetChanged()
    }
}