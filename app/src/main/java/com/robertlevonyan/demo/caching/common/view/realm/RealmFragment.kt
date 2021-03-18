package com.robertlevonyan.demo.caching.common.view.realm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.robertlevonyan.demo.caching.common.rv.MoviesAdapter
import com.robertlevonyan.demo.caching.databinding.FragmentRealmBinding
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class RealmFragment : Fragment() {
  companion object {
    fun newInstance() = RealmFragment()
  }

  private val viewModel: RealmViewModel by viewModel()
  private val binding by lazy { FragmentRealmBinding.inflate(layoutInflater) }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    lifecycleScope.launchWhenCreated {
      val adapter = MoviesAdapter()
      binding.rvMovies.adapter = adapter
      viewModel.allMovies.collect {
        adapter.submitList(it)
      }
    }
  }
}
