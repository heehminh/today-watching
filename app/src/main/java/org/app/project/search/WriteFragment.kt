package org.app.project.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import org.app.project.R
import org.app.project.databinding.FragmentHomeBinding
import org.app.project.databinding.FragmentSearchBinding
import org.app.project.databinding.FragmentWriteBinding

class WrtieFragment : Fragment() {
    private lateinit var binding: FragmentWriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWriteBinding.inflate(layoutInflater)

//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
//        val mActivity = activity as MainActivity
//
//        binding.searchIconIv.setOnClickListener {
//            var searchMovie: String = binding.searchTitleEt.text.toString()
//            mActivity.callFragment(SearchResultFragment(searchMovie))
//        }
//
//        binding.searchTitleEt.setOnEditorActionListener{v, actionId, event ->
//            var handled = false
//            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                var searchMovie: String = binding.searchTitleEt.text.toString()
//                mActivity.callFragment(SearchResultFragment(searchMovie))
//                handled = true
//            }
//            handled
//        }

        // Inflate the layout for this fragment
        return binding.root

    }
}