package com.app.ml.logger

import android.util.Log

class Logger {

    fun e(error: String) {
        Log.e(ERROR, error)
    }

    fun i(message: String) {
        Log.i(INFO, message)
    }

    companion object {
        private const val MAIN_KEY = "SearchApp"
        private const val ERROR = "$MAIN_KEY Error"
        private const val INFO = "$MAIN_KEY Info"
    }
}