package ahmednagi.github.domain.interactor

import ahmednagi.github.domain.executor.ExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class ObservableService<T, in params>
constructor(private val executionThread: ExecutionThread) {

    private val compositeDisposable = CompositeDisposable()

    protected abstract fun buildObservableService(params: params? = null): Observable<T>

    open fun execute(observer: DisposableObserver<T>, params: params? = null) {
        val observable = this.buildObservableService(params)
            .subscribeOn(Schedulers.io())
            .observeOn(executionThread.scheduler)
        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose() {
        compositeDisposable.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

}