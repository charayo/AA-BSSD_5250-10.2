package com.example.aa_bssd_5250_102

import androidx.lifecycle.ViewModel

class AudioViewModel: ViewModel() {
    private var currTime:Int = 0
    fun setCurrentTime(time:Int){
        currTime = time;
    }
    fun getCurrentTime():Int{
        return currTime;
    }
}