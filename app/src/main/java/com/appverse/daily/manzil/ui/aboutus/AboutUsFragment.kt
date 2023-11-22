package com.appverse.daily.manzil.ui.aboutus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.appverse.daily.manzil.databinding.FragmentAboutUsBinding

class AboutUsFragment : Fragment() {

    private  val TAG = "AboutUsFragment"

    // This property is only valid between onCreateView and
    // onDestroyView.
    private var binding: FragmentAboutUsBinding? = null
    private var rootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val aboutUsViewModel =
            ViewModelProvider(this).get(AboutUsViewModel::class.java)

        if(rootView==null){
            binding = FragmentAboutUsBinding.inflate(inflater, container, false)
            rootView = binding?.root
        }
//        aboutUsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return rootView!!
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //_binding = null
    }
}