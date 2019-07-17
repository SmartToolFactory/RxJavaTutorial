package chapter2observables

import io.reactivex.Observable
import io.reactivex.functions.Function

fun main() {
//    testObservableCreate()
//    testObservableCreateWithTry()
    testObservableWithOperator()

}


private fun testObservableCreate() {
    val source = Observable.create<String> { emitter ->

        emitter.onNext("Alpha")
        emitter.onNext("Beta")
        emitter.onNext("Gamma")
        emitter.onNext("Delta")
        emitter.onNext("Epsilon")
        emitter.onComplete()
    }

    source.subscribe { s -> println("RECEIVED: $s") }
}

private fun testObservableCreateWithTry() {

    val source = Observable.create<String> { emitter ->

        try {
            emitter.onNext("Alpha")
            emitter.onNext("Beta")
            emitter.onNext("Gamma")
            emitter.onNext("Delta")
            emitter.onNext("Epsilon")
            emitter.onComplete()
        } catch (e: Exception) {
            emitter.onError(e)
        }

    }

    source.subscribe(
        { s ->
            // onNext
            println("RECEIVED: $s")
        },
        { throwable ->
            // onError
            println(throwable.stackTrace)
        })
}


private fun testObservableWithOperator() {
    val source = Observable.create<String> { emitter ->

        try {
            emitter.onNext("Alpha")
            emitter.onNext("Beta")
            emitter.onNext("Gamma")
            emitter.onNext("Delta")
            emitter.onNext("Epsilon")
            emitter.onComplete()
        } catch (e: Exception) {
            emitter.onError(e)
        }

    }

    val lengths = source.map<Int>(Function<String, Int> { s -> s.length })

    val filtered = lengths.filter({ i -> i >= 5 })

    filtered.subscribe({ s ->
        // onNext
        println("RECEIVED: $s")
    },
        { throwable ->
            // onError
            println(throwable.stackTrace)
        })

}

private fun testObservableJust() {
    val source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")

    source
        .map {
            it.length
        }
        .filter { i -> i >= 5 }
        .subscribe { s -> println("RECEIVED: " + s!!) }
}
