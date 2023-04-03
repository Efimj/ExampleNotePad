package com.example.examplenotepad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.examplenotepad.BottomSheetMenuDialogFragment

class BottomMainAppMenu : Fragment() {
    // Создание View фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_menu_button, container, false)
        val bottomSheet = BottomSheetMenuDialogFragment()
        val button = view.findViewById<Button>(R.id.button)

        button.setOnClickListener {
            bottomSheet.show(childFragmentManager, "BottomSheetDialog")
        }
        return view
    }
}
