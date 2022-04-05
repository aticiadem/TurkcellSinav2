package com.adematici.turkcellsinav2.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adematici.turkcellsinav2.databinding.PaymentTypeItemBinding
import com.adematici.turkcellsinav2.model.PaymentType
import com.adematici.turkcellsinav2.util.listener.PaymentTypeClickListener

class PaymentTypesAdapter(
    private val itemClick: PaymentTypeClickListener,
) : RecyclerView.Adapter<PaymentTypesAdapter.PaymentTypesViewHolder>() {

    class PaymentTypesViewHolder(val binding: PaymentTypeItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<PaymentType>() {
        override fun areItemsTheSame(oldItem: PaymentType, newItem: PaymentType): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PaymentType, newItem: PaymentType): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerDiffUtil = AsyncListDiffer(this, diffUtil)

    var paymentTypes: List<PaymentType>
        get() = recyclerDiffUtil.currentList
        set(value) = recyclerDiffUtil.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentTypesViewHolder {
        val binding =
            PaymentTypeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaymentTypesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentTypesViewHolder, position: Int) {
        val currentItem = paymentTypes[position]
        holder.binding.textViewTitle.text = currentItem.title
        holder.binding.textViewPeriod.text = currentItem.period ?: ""
        currentItem.periodDay?.let {
            holder.binding.textViewPeriodDay.text = it.toString()
        }
        holder.binding.buttonAddPayment.setOnClickListener { itemClick.onItemClick(currentItem.id) }
        holder.binding.root.setOnClickListener { itemClick.onViewClick(currentItem.id) }
    }

    override fun getItemCount(): Int = paymentTypes.size

}