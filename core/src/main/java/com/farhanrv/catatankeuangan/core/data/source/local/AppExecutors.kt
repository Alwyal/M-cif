package com.farhanrv.catatankeuangan.core.data.source.local

import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutors constructor(
    private val diskIO: Executor
) {
    constructor() : this(
        Executors.newSingleThreadExecutor()
    )

    fun diskIO(): Executor = diskIO

}