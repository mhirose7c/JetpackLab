package com.example.jetpacklab.fragment_lab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.jetpacklab.R

class ThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_third, container, false)
        view.findViewById<Button>(R.id.first_page).setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_thirdFragment_to_firstFragment)
        }
        return view
    }
}
