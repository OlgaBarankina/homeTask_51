package com.example.hometask_51

import android.os.SystemClock
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

class HeavyProcess {

    fun start() =
        Observable.create<String>{ emitter ->
            SystemClock.sleep(4000)
            emitter.onNext("")
        }
}

