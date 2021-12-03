package com.example.workoutProject.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.workoutProject.fragment.first.CalendarFragment

class BaseFragmentFactory(private val index: Int): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            CalendarFragment::class.java.name -> CalendarFragment(index)
            else -> super.instantiate(classLoader, className)
        }
    }
}