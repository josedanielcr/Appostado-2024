package com.ucenfotec.appostado.infrastructure.repositories.team

import com.google.cloud.firestore.Firestore
import com.ucenfotec.appostado.core.application.common.exceptions.DogNotFoundException
import com.ucenfotec.appostado.core.application.common.exceptions.SportNotFoundException
import com.ucenfotec.appostado.core.application.common.exceptions.TeamNotFoundException
import com.ucenfotec.appostado.core.domain.entities.Team
import com.ucenfotec.appostado.infrastructure.repositories.team.ITeamRepository
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Repository
import java.util.concurrent.CompletableFuture

@Async
@Repository
class TeamRepository(private val firestore : Firestore) : ITeamRepository {

    private val TEAMS_COLLECTION: String = "teams"

    override fun getTeamById(teamId : String): CompletableFuture<Team> {
        return CompletableFuture.supplyAsync {
            val documentSnapshot = firestore.collection(TEAMS_COLLECTION).document(teamId).get().get()
            if (!documentSnapshot.exists()) {
                throw TeamNotFoundException(additionalDetails = mapOf("teamId" to teamId));
            }
            Team.fromDocumentSnapshot(documentSnapshot)
        }
    }

    override fun getTeams(): CompletableFuture<List<Team>> {
        return CompletableFuture.supplyAsync {
            val querySnapshot = firestore.collection(TEAMS_COLLECTION).get().get()
            val teams = mutableListOf<Team>()
            for (document in querySnapshot.documents) {
                teams.add(Team.fromDocumentSnapshot(document))
            }
            teams
        }
    }

    override fun getTeamsBySport(sportId : String): CompletableFuture<List<Team>> {
        return CompletableFuture.supplyAsync {
            val querySnapshot = firestore.collection(TEAMS_COLLECTION)
                .whereEqualTo("sportId", sportId) // Filter teams by sportId
                .get().get()
            val teams = mutableListOf<Team>()
            for (document in querySnapshot.documents) {
                teams.add(Team.fromDocumentSnapshot(document))
            }
            teams
        }
    }

    override fun createTeam(team: Team): CompletableFuture<Team> {
        return CompletableFuture.supplyAsync {
            val documentReference = firestore.collection(TEAMS_COLLECTION).document(team.id)
            documentReference.set(team).get()
            getTeamById(team.id).join()
        }
    }

    override fun updateTeam(teamId: String, team: Team): CompletableFuture<Team> {
        return CompletableFuture.supplyAsync {
            firestore.collection(TEAMS_COLLECTION).document(teamId).set(team).get()
            getTeamById(teamId).join()
        }
    }

    override fun deleteTeam(teamId: String): CompletableFuture<Boolean> {
        return CompletableFuture.supplyAsync {
            val documentReference = firestore.collection(TEAMS_COLLECTION).document(teamId)
            val deleteResult = documentReference.delete().get()
            deleteResult != null
        }
    }
}