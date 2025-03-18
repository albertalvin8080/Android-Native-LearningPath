package org.albert.x14_customviews.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.albert.x14_customviews.CustomViewActivity
import org.albert.x14_customviews.ExtendedViewActivity
import org.albert.x14_customviews.data.DataColorManager
import org.albert.x14_customviews.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.changeColor.setOnClickListener {
//            val intent = Intent(requireContext(), CompoundComponentActivity::class.java)
//            val intent = Intent(requireContext(), ExtendedViewActivity::class.java)
            val intent = Intent(requireContext(), CustomViewActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(this::class.simpleName, "onStart")
        binding.colorView.setBackgroundColor(DataColorManager.color)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}