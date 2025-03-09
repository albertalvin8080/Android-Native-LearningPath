package org.albert.x08_viewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import org.albert.x08_viewmodel.databinding.FragmentFirstBinding
import org.albert.x08_viewmodel.viewmodel.FirstFragmentViewModel

class FirstFragment : Fragment()
{
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel by lazy {
//        ViewModelProviders.of() // Deprecated
        ViewModelProvider(this)[FirstFragmentViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(savedInstanceState != null)
            viewModel.restoreState(savedInstanceState)

        binding.textviewFirst.text = viewModel.msg
        binding.input.setText(viewModel.msg)

        binding.buttonFirst.setOnClickListener {
            val msg = binding.input.text.toString()
            binding.textviewFirst.text = msg
            viewModel.msg = msg
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        viewModel.saveState(outState)
    }

}