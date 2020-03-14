package ahmednagi.github.domain.usecase

import ahmednagi.github.domain.entity.Project
import ahmednagi.github.domain.executor.ExecutionThread
import ahmednagi.github.domain.interactor.ObservableUseCase
import ahmednagi.github.domain.repository.RepositoryProject
import io.reactivex.Observable
import javax.inject.Inject

open class GetProjects @Inject constructor(
    private val repositoryProject: RepositoryProject,
    executionThread: ExecutionThread
) : ObservableUseCase<List<Project>, Nothing?>(executionThread) {

    public override fun buildObservableUseCase(params: Nothing?): Observable<List<Project>> {
        return repositoryProject.getProjects()
    }

}