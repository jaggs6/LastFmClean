package uk.co.mgntech.last_fm_mvvm

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

class AppExecutors {
    companion object {
        val instance = AppExecutors()
    }

    val networkIO: ScheduledExecutorService = Executors.newScheduledThreadPool(3)
}
