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

class GetBookmarkedProjectsTest {

    private lateinit var getBookmarkedProjects: GetBookmarkedProjects
    @Mock
    lateinit var repositoryProject: RepositoryProject
    @Mock
    lateinit var executionThread: ExecutionThread


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getBookmarkedProjects = GetBookmarkedProjects(repositoryProject, executionThread)
    }

    @Test
    fun getBookmarkedProjectsCompletes() {
        stubBookmarkedProjects(Observable.just(MockDataFactory.makeProjectList(4)))
        val testObserver = getBookmarkedProjects.buildObservableUseCase().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBookmarkedProjectsReturnsData() {
        val projectsList = MockDataFactory.makeProjectList(4)
        stubBookmarkedProjects(Observable.just(projectsList))
        val testObserver = getBookmarkedProjects.buildObservableUseCase().test()
        testObserver.assertValue(projectsList)
    }

    private fun stubBookmarkedProjects(observable: Observable<List<Project>>) {
        whenever(repositoryProject.getBookmarkedProjects()).thenReturn(observable)
    }
}