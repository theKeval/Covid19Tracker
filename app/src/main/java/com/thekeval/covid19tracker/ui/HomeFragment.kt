package com.thekeval.covid19tracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thekeval.covid19tracker.R
import com.thekeval.covid19tracker.databinding.FragmentHomeBinding
import com.thekeval.covid19tracker.databinding.SummaryItemBinding
import com.thekeval.covid19tracker.domain.DomainModel
import com.thekeval.covid19tracker.viewmodel.HomeViewModel

class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewmodel: HomeViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }

        ViewModelProvider(this, HomeViewModel.Factory(activity.application)).get(HomeViewModel::class.java)
    }

    private var summaryAdapter: CovidSummaryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewmodel = viewmodel

        summaryAdapter = CovidSummaryAdapter(SummaryClicked {
            Toast.makeText(context, "Incoming feature..", Toast.LENGTH_LONG)
        })

        binding.summaryList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = summaryAdapter
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel.summaries.observe(viewLifecycleOwner, Observer { summaries ->
            summaries.apply {
                summaryAdapter?.summaries = summaries
            }
        })
    }


}


class CovidSummaryAdapter(val callback: SummaryClicked) : RecyclerView.Adapter<CovidSummaryViewHolder>() {

    var summaries: List<DomainModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidSummaryViewHolder {
        // val withDataBinding = SummaryItemBinding.inflate(LayoutInflater.from(parent.context))
        val withDataBinding: SummaryItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CovidSummaryViewHolder.LAYOUT,
            parent,
            false)

        return CovidSummaryViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CovidSummaryViewHolder, position: Int) {
        holder.binding.also {
            it.summary = summaries[position]
            it.summaryCallback = callback
        }
    }

    override fun getItemCount(): Int {
        return summaries.size
    }

}

class CovidSummaryViewHolder(val binding: SummaryItemBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.summary_item
    }
}

class SummaryClicked(val block: (DomainModel) -> Unit) {
    fun onClick(model: DomainModel) = block(model)
}