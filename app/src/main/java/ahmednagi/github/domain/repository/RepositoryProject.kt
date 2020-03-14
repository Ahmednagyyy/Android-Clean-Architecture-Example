package ahmednagi.github.domain.repository

import ahmednagi.github.domain.entity.Project
import io.reactivex.Completable
import io.reactivex.Observable

interface RepositoryProject {

    fun getProjects(): Observable<List<Project>>

    fun getBookmarkedProjects(): Observable<List<Project>>

    fun bookmarkProject(projectId: String): Completable

    fun unbookmarkProject(projectId: String): Completable
}