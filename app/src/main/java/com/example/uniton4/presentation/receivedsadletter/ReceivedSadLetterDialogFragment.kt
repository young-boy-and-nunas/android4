package com.example.uniton4.presentation.receivedsadletter

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.uniton4.MainViewModel
import com.example.uniton4.NavigateScreenType
import com.example.uniton4.R
import com.example.uniton4.databinding.FragmentReceivedSadLetterDialogBinding
import com.example.uniton4.extensions.closeSelf
import com.example.uniton4.presentation.receivedsadletter.image.ReceivedWorryImageFragment
import com.example.uniton4.presentation.receivedsadletter.text.ReceivedWorryTextFragment

class ReceivedSadLetterDialogFragment private constructor() : DialogFragment(),
    View.OnClickListener {
    private lateinit var binding: FragmentReceivedSadLetterDialogBinding
    private val parentViewModel: MainViewModel by activityViewModels()
    private var isText = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.TransparentTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReceivedSadLetterDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFullScreen()
        binding.container.setOnTouchListener { _, _ ->
            return@setOnTouchListener true
        }
        binding.closeButton.setOnClickListener(this)
        binding.writeButton.setOnClickListener(this)
        initView()
    }

    private fun initView(){
        if(isText){
            parentFragmentManager.beginTransaction()
                .add(R.id.frame_layout, ReceivedWorryTextFragment.newInstance())
                .commit()
        }else{
            parentFragmentManager.beginTransaction()
                .add(R.id.frame_layout, ReceivedWorryImageFragment.newInstance())
                .commit()
        }
    }

    private fun setFullScreen() {
        val window = dialog?.window ?: return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ReceivedSadLetterDialogFragment()
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.closeButton -> {
                parentViewModel.navigateByReplace(NavigateScreenType.RECEIVED_CHEER_UP_LETTER)
            }
            binding.writeButton -> {
                parentViewModel.navigateByAdd(NavigateScreenType.WRITE_CHEER)
            }
        }
    }
}