package com.thekeval.covid19tracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.thekeval.covid19tracker.databinding.FragmentHomeBinding
import com.thekeval.covid19tracker.domain.DomainModel

class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater)



        return binding.root
    }


}

class SummaryClicked(val block: (DomainModel) -> Unit) {
    fun onClick(model: DomainModel) = block(model)
}

//class CovidSummaryAdapter(val callback: SummaryClicked) : RecyclerView.Adapter<CovidSummaryViewHolder>() {
//
//}
//
//class CovidSummaryViewHolder: RecyclerView.ViewHolder {
//
//}
