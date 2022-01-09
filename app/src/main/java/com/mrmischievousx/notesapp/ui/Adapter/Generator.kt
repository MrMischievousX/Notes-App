package com.mrmischievousx.notesapp.ui.Adapter

import com.mrmischievousx.notesapp.R

class Generator {
    private val colors = arrayOf(
        R.color.card1,
        R.color.card2,
        R.color.card3,
        R.color.card4,
        R.color.card5,
        R.color.card6,
        R.color.card7,
        R.color.card8,
        R.color.card9,
        R.color.card10,
    )

    public fun getCustomColor(position:Int): Int {
        val rands = (0..colors.size).random()
        return colors[rands % colors.size]
    }

}