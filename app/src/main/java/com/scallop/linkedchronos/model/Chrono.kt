package com.scallop.linkedchronos.model

import android.os.CountDownTimer

/**
 * Created by juanje on 22/05/16.
 */
class Chrono(milisecond: Long, name: String, countDownTimer: CountDownTimer) {

    var miliseconds: Long = milisecond
        private set
    var name: String? = name
    var countDownTimer: CountDownTimer? = countDownTimer

}
