package ahmednagi.github.domain.executor

import io.reactivex.Scheduler


interface ExecutionThread {
    val scheduler: Scheduler
}