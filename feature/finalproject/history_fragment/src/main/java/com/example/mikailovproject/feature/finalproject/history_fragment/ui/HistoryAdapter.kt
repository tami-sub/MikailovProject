package com.example.mikailovproject.feature.finalproject.history_fragment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mikailovproject.shared.finalproject.core.databinding.ItemLoanBinding
import com.example.mikailovproject.shared.finalproject.core.domain.entity.LoanEntity
import com.example.mikailovproject.shared.finalproject.core.utils.Utils.convertDateTimeToReadableFormat

class HistoryAdapter(private val findNavController: NavController, private val isRemote: Boolean) :
    RecyclerView.Adapter<HistoryAdapter.LoanViewHolder>() {

    var storageList: List<LoanEntity> = emptyList()
        set(value) {
            val reversedList = value.reversed()
            val diffResult = DiffUtil.calculateDiff(LoanDiffCallback(field, reversedList))
            field = reversedList
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanViewHolder {
        return LoanViewHolder(
            ItemLoanBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: LoanViewHolder, position: Int) {
        val itemLoan = storageList[position]
        holder.bind(itemLoan)
        if (isRemote) {
            holder.itemView.setOnClickListener {
                val id = itemLoan.id
                val request = NavDeepLinkRequest.Builder
                    .fromUri("android-app://detailedLoanFragment?id=$id".toUri())
                    .build()
                findNavController.navigate(request)
            }
        }
    }

    override fun getItemCount(): Int = storageList.size


    inner class LoanViewHolder(private val binding: ItemLoanBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loan: LoanEntity) = with(binding) {
            itemDate.text = convertDateTimeToReadableFormat(loan.date)
            itemName.text = loan.firstName
            itemLastname.text = loan.lastName
            itemAmount.text = loan.amount.toString()
            itemPercent.text = loan.percent.toString()
            itemPeriod.text = loan.period.toString()
            itemNumber.text = loan.phoneNumber
            itemStatus.text = loan.state
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