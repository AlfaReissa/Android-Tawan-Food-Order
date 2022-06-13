package com.tawan.java.customview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tawan.java.databinding.BottomSheetAddMenuBinding as MainBinding


class BottomSheetTaskFragment : BottomSheetDialogFragment() {

    private var mView: View? = null

    private lateinit var editClickListener: () -> Unit
    private lateinit var deleteClickListener: () -> Unit

    companion object {
        val TAG = "LINK_POPUP_EDIT"
        fun instance(
            deleteClickListener: () -> Unit = {},
            editClickListener: () -> Unit = {},
        ): BottomSheetTaskFragment {
            BottomSheetTaskFragment().apply {
                this.editClickListener = editClickListener
                this.deleteClickListener = deleteClickListener
                return this
            }
        }
    }

    private lateinit var binding: MainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainBinding.inflate(inflater, container, false)
        initUI()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mView = view
    }

    private fun initUI() = with(binding) {
        this@BottomSheetTaskFragment.apply {



        }
    }

    private fun openInBrowser(link: String) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(link)
        startActivity(i)
    }

    private fun copyLink(link: String) {

    }

}