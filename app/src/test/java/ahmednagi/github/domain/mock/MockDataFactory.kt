package ahmednagi.github.domain.mock

import ahmednagi.github.domain.entity.Project
import java.util.*

object MockDataFactory {

    fun randomUuid(): String {
        return UUID.randomUUID().toString()
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun makeProject(): Project {
        return Project(
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomBoolean()
        )
    }

    fun makeProjectList(count: Int): List<Project> {
        val projects = mutableListOf<Project>()
        repeat(count) {
            projects.add(makeProject())
        }
        return projects
    }


}