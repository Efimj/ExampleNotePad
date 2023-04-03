package com.example.examplenotepad

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class BottomSheetMenuDialogFragment : BottomSheetDialogFragment() {
    private var activeButtonIndex = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet_main_menu_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonListeners(view)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), theme)
        return bottomSheetDialog
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog as BottomSheetDialog
        dialog.behavior.peekHeight = requireActivity().windowManager.defaultDisplay.height
        dialog.setOnShowListener {
            val bottomSheet = dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            val behavior = BottomSheetBehavior.from(bottomSheet!!)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun setButtonListeners(view: View) {
        val buttonNotes = view.findViewById<Button>(R.id.buttonNotes)
        val buttonArchive = view.findViewById<Button>(R.id.buttonArchive)
        val buttonBasket = view.findViewById<Button>(R.id.buttonBasket)
        val buttonSettings = view.findViewById<Button>(R.id.buttonSettings)

        setActiveButton(buttonNotes, 0, view)

        buttonNotes.setOnClickListener {
            setActiveButton(buttonNotes, 0, view)
            closeBottomSheet()
        }

        buttonArchive.setOnClickListener {
            setActiveButton(buttonArchive, 1, view)
            closeBottomSheet()
        }

        buttonBasket.setOnClickListener {
            setActiveButton(buttonBasket, 2, view)
            closeBottomSheet()
        }

        buttonSettings.setOnClickListener {
            setActiveButton(buttonSettings, 3, view)
            closeBottomSheet()
        }
    }

    private fun setActiveButton(button: Button, index: Int, view: View) {
        if (index == activeButtonIndex) {
            return
        }

        if (activeButtonIndex != -1) {
            val previousButton = getButtonByIndex(activeButtonIndex)
            previousButton?.setTextColor(ContextCompat.getColor(view.getContext(), R.color.text_color));
            previousButton?.isSelected = false
        }

        button.isSelected = true
        button.setTextColor(ContextCompat.getColor(view.getContext(), R.color.white));
        activeButtonIndex = index
    }

    private fun closeBottomSheet() {
        Handler(Looper.getMainLooper()).postDelayed({
            dismiss()
        }, 0)
    }

    private fun getButtonByIndex(index: Int): Button? {
        return when (index) {
            0 -> view?.findViewById(R.id.buttonNotes)
            1 -> view?.findViewById(R.id.buttonArchive)
            2 -> view?.findViewById(R.id.buttonBasket)
            3 -> view?.findViewById(R.id.buttonSettings)
            else -> null
        }
    }
}
