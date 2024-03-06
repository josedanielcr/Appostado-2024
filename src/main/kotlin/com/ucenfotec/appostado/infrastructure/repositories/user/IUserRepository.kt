package com.ucenfotec.appostado.infrastructure.repositories.user

import com.ucenfotec.appostado.core.domain.entities.User
import java.util.concurrent.CompletableFuture

interface IUserRepository {
    fun getUserById(userId: String): CompletableFuture<User>
    fun getUserByEmail(email: String): CompletableFuture<User>
    fun getUsers(): CompletableFuture<List<User>>
    fun createUser(user: User): CompletableFuture<User>
    fun updateUser(userId: String, user: User): CompletableFuture<User>
    fun deleteUser(userId: String): CompletableFuture<Boolean>
}