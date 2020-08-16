package uk.co.mgntech.lastfmclean

import java.util.concurrent.Executors

class AppExecutors {
    companion object {
        val instance = AppExecutors()
    }

    val networkIO = Executors.newScheduledThreadPool(3)
}
