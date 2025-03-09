package org.albert.x08_viewmodel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import org.albert.x08_viewmodel.databinding.FragmentFirstBinding
import org.albert.x08_viewmodel.databinding.FragmentThirdBinding
import org.albert.x08_viewmodel.helper.ThirdFragmentHelper
import org.albert.x08_viewmodel.viewmodel.FirstFragmentViewModel

class ThirdFragment : Fragment()
{
    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val thirdFragmentHelper by lazy {
        // WARN: requireContext() may ONLY be called after onAttach().
        ThirdFragmentHelper(requireContext(), this.lifecycle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        thirdFragmentHelper

        binding.sendMsg.setOnClickListener{
            thirdFragmentHelper.sendMessage()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}