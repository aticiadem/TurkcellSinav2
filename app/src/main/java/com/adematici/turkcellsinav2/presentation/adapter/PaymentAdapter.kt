package com.adematici.turkcellsinav2.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adematici.turkcellsinav2.databinding.PaymentItemBinding
import com.adematici.turkcellsinav2.model.Payment

class PaymentAdapter : RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<Payment>() {
        override fun areItemsTheSame(oldItem: Payment, newItem: Payment): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Payment, newItem: Payment): Boolean {
            return oldItem == newItem
        }
    }

    private var recyclerDiffUtil = AsyncListDiffer(this, diffUtil)

    var paymentList: List<Payment>
        get() = recyclerDiffUtil.currentList
        set(value) = recyclerDiffUtil.submitList(value)

    class PaymentViewHolder(val binding: PaymentItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val binding = PaymentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaymentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val currentData = paymentList[position]
        holder.binding.apply {
            textViewAmount.text = currentData.amount.toString()
            textViewDate.text = currentData.date
        }
    }

    override fun getItemCount(): Int = paymentList.size

}