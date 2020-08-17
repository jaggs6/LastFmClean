package uk.co.mgntech.lastfmclean

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

class AppExecutors {
    companion object {
        val instance = AppExecutors()
    }

    val networkIO: ScheduledExecutorService = Executors.newScheduledThreadPool(3)
}
