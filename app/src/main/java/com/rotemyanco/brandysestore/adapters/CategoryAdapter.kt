package com.rotemyanco.brandysestore.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.rotemyanco.brandysestore.R
import com.rotemyanco.brandysestore.databinding.CategoryInfoCardBinding
import com.rotemyanco.brandysestore.databinding.RcvLastBtnBinding
import com.rotemyanco.brandysestore.models.Category


class CategoryAdapter(
	private val categories: List<Category>,
	private val onItemClicked: OnItemClick<Category>,
	private val onLastBtnClick: OnItemClick<Int>
) :
	RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {


	private val logTag = "CategoryAdapter"
	private lateinit var mViewBinding: ViewBinding


	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
		mViewBinding = when (viewType) {
			R.layout.category_info_card -> {
				CategoryInfoCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
			}
			else -> {
				RcvLastBtnBinding.inflate(LayoutInflater.from(parent.context), parent, false)
			}
		}
		return CategoryVH(mViewBinding, categories, onItemClicked, onLastBtnClick)
	}


	override fun onBindViewHolder(holder: CategoryVH, position: Int) {

		when (holder.binding.javaClass) {
			RcvLastBtnBinding::class.java -> {
				val btnBrowseMore: Button = holder.itemView.findViewById(R.id.btn_browse_rcv_last_btn)
				btnBrowseMore.setOnClickListener {
					onLastBtnClick.onItemClick(it.id)
				}
			}
			CategoryInfoCardBinding::class.java -> {
				holder.itemView.findViewById<TextView>(R.id.tv_small_title_category_info_card).text = categories[position].categoryName
			}
			else -> {
				println("******      --  Error --      *****")
				println("******      --  ${this.javaClass == CategoryInfoCardBinding::class.java} --      *****")
				println("******      --  ${this.javaClass == RcvLastBtnBinding::class.java} --      *****")
			}
		}
	}

	// add 1 more spot in recycler at the last index for the button:
	override fun getItemCount(): Int = (categories.size) + 1

	override fun getItemViewType(position: Int): Int {
		return if (position == categories.size) R.layout.rcv_last_btn else R.layout.category_info_card
	}

	class CategoryVH(
		val binding: ViewBinding,
		private val categories: List<Category>,
		private val onItemClicked: OnItemClick<Category>,
		private val onLastBtnClick: OnItemClick<Int>
	) :
		RecyclerView.ViewHolder(binding.root) {
		init {
			binding.root.setOnClickListener {
				onItemClicked.onItemClick(categories[adapterPosition])
				onLastBtnClick.onItemClick(R.id.btn_browse_rcv_last_btn)
			}
		}
	}

}