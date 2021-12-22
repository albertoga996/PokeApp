package com.example.pokeapp.utils

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

/**
 * NavController
 */
fun NavController.safeNavigate(direction: NavDirections) {
    currentDestination?.getAction(direction.actionId)?.run {
        navigate(direction)
    }
}

/**
 * Fragment
 */
fun Fragment.navigateTo(navDirection: NavDirections) {
    if (isAdded) {
        findNavController().safeNavigate(navDirection)
    }
}
