package ahmednagi.github.domain.usecase

import ahmednagi.github.domain.executor.ExecutionThread
import ahmednagi.github.domain.interactor.CompletableUseCase
import ahmednagi.github.domain.repository.RepositoryProject
import io.reactivex.Completable
import javax.inject.Inject

open class UnbookmarkProject @Inject constructor(
    private val repositoryProject: RepositoryProject,
    executionThread: ExecutionThread
) : CompletableUseCase<UnbookmarkProject.Params?>(executionThread) {

    data class Params constructor(val projectId: String) {
        companion object {
            fun project(projectId: String): Params {
                return Params(projectId)
            }
        }
    }

    public override fun buildCompletableUseCase(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null")
        return repositoryProject.unbookmarkProject(params.projectId)
    }

}