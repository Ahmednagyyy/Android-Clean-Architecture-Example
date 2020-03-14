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

class BookmarkProjectTest {

    private lateinit var bookmarkProject: BookmarkProject
    @Mock
    lateinit var repositoryProject: RepositoryProject
    @Mock
    lateinit var executionThread: ExecutionThread


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        bookmarkProject = BookmarkProject(repositoryProject, executionThread)
    }

    @Test
    fun bookmarkedProjectCompletes() {
        stubBookmarkedProject(Completable.complete())
        val testCompletable = bookmarkProject.buildCompletableUseCase(
            BookmarkProject.Params.project(MockDataFactory.randomUuid())
        ).test()
        testCompletable.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun bookmarkedProjectThrowsException() {
        bookmarkProject.buildCompletableUseCase().test()
    }

    private fun stubBookmarkedProject(completable: Completable) {
        whenever(repositoryProject.bookmarkProject(any())).thenReturn(completable)
    }
}