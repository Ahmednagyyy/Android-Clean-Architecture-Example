package ahmednagi.github.domain.usecase

import ahmednagi.github.domain.entity.Project
import ahmednagi.github.domain.executor.ExecutionThread
import ahmednagi.github.domain.mock.MockDataFactory
import ahmednagi.github.domain.repository.RepositoryProject
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetProjectsTest {

    private lateinit var getProjects: GetProjects
    @Mock
    lateinit var repositoryProject: RepositoryProject
    @Mock
    lateinit var executionThread: ExecutionThread


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getProjects = GetProjects(repositoryProject, executionThread)
    }

    @Test
    fun getProjectsCompletes() {
        stubGetProjects(Observable.just(MockDataFactory.makeProjectList(4)))
        val testObserver = getProjects.buildObservableUseCase().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val projectsList = MockDataFactory.makeProjectList(4)
        stubGetProjects(Observable.just(projectsList))
        val testObserver = getProjects.buildObservableUseCase().test()
        testObserver.assertValue(projectsList)
    }

    private fun stubGetProjects(observable: Observable<List<Project>>) {
        whenever(repositoryProject.getProjects()).thenReturn(observable)
    }
}