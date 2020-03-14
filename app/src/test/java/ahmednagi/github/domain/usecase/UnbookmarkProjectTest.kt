package ahmednagi.github.domain.usecase

import ahmednagi.github.domain.executor.ExecutionThread
import ahmednagi.github.domain.mock.MockDataFactory
import ahmednagi.github.domain.repository.RepositoryProject
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UnbookmarkProjectTest {

    private lateinit var unbookmarkProject: UnbookmarkProject
    @Mock
    lateinit var repositoryProject: RepositoryProject
    @Mock
    lateinit var executionThread: ExecutionThread


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        unbookmarkProject = UnbookmarkProject(repositoryProject, executionThread)
    }

    @Test
    fun bookmarkedProjectCompletes() {
        stubBookmarkedProject(Completable.complete())
        val testCompletable = unbookmarkProject.buildCompletableUseCase(
            UnbookmarkProject.Params.project(MockDataFactory.randomUuid())
        ).test()
        testCompletable.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun bookmarkedProjectThrowsException() {
        unbookmarkProject.buildCompletableUseCase().test()
    }

    private fun stubBookmarkedProject(completable: Completable) {
        whenever(repositoryProject.unbookmarkProject(any())).thenReturn(completable)
    }
}