package com.example.fragmentbasic

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class HomeFragment : Fragment(R.layout.fragment_home) {


    //START: interface for sending data to activity
    interface OnNameSendListener {
        fun onNameSend(name: String)
    }
    private var listener: OnNameSendListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnNameSendListener
    }
    //END: interface for sending data to activity


    // onCreateView is another function must return infater

    // onViewCreated function
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getInt("id")
        val name = arguments?.getString("name")
        val age = arguments?.getInt("age")
        val cgpa = arguments?.getFloat("cgpa")

        //sending data to MainActivity (Fragment -> Activity)
        listener?.onNameSend("Karim")
    }

    // companion object return an object
    companion object {
        fun newInstance(
            id: Int,
            name: String,
            age: Int,
            cgpa: Float
        ): HomeFragment {

            val fragment = HomeFragment()
            val bundle = Bundle()

            bundle.putInt("id", id)
            bundle.putString("name", name)
            bundle.putInt("age", age)
            bundle.putFloat("cgpa", cgpa)

            fragment.arguments = bundle

            return fragment
        }
    }

    // after detach Fragment should not hold any activity reference
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

}