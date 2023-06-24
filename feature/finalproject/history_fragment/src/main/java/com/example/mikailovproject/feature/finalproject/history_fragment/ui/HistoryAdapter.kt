package com.example.mikailovproject.feature.finalproject.history_fragment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mikailovproject.feature.finalproject.history_fragment.databinding.ItemLoanBinding
import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanEntity
import com.example.mikailovproject.shared.finalproject.core.utils.Utils.convertDateTimeToReadableFormat

class HistoryAdapter() : RecyclerView.Adapter<HistoryAdapter.LoanViewHolder>() {

    var storageList: List<LoanEntity> = emptyList()
        set(value) {
            val reversedList = value.reversed()
            val diffResult = DiffUtil.calculateDiff(LoanDiffCallback(field, reversedList))
            field = reversedList
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LoanViewHolder(
            ItemLoanBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: LoanViewHolder, position: Int) {
        holder.bind(storageList[position])
    }

    override fun getItemCount(): Int = storageList.size


    inner class LoanViewHolder(private val binding: ItemLoanBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loan: LoanEntity) = with(binding) {
            itemDate.text = convertDateTimeToReadableFormat(loan.date)
            itemName.setText(loan.firstName)
            itemLastname.setText(loan.lastName)
            itemAmount.setText(loan.amount.toString())
            itemPercent.setText(loan.percent.toString())
            itemPeriod.setText(loan.period.toString())
            itemNumber.setText(loan.phoneNumber)
            itemStatus.setText(loan.state)
        }
    }

    inner class LoanDiffCallback(
        private val oldList: List<LoanEntity>,
        private val newList: List<LoanEntity>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
        override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            return oldList[oldPosition].id == newList[newPosition].id
        }

        override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            return oldList[oldPosition] == newList[newPosition]
        }
    }
}