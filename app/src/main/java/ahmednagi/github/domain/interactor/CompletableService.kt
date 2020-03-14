package ahmednagi.github.domain.interactor

import ahmednagi.github.domain.executor.ExecutionThread
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

abstract class CompletableService<in params>
constructor(private val executionThread: ExecutionThread) {

    private val compositeDisposable = CompositeDisposable()

    protected abstract fun buildCompletableService(params: params? = null): Completable

    open fun execute(completable: DisposableCompletableObserver, params: params? = null) {
        val observable = this.buildCompletableService(params)
            .subscribeOn(Schedulers.io())
            .observeOn(executionThread.scheduler)
        addDisposable(observable.subscribeWith(completable))
    }

    fun dispose() {
        compositeDisposable.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

}