package id.tiooooo.myarticle.data.api.repo

import android.app.Activity
import id.tiooooo.myarticle.utils.wrapper.ResultState
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun executeLogin(activity: Activity): Flow<ResultState<Boolean>>
    fun executeLogout(activity: Activity): Flow<ResultState<Boolean>>
    suspend fun getProfileEmail(): Flow<String>
    suspend fun getProfileNickName(): Flow<String>
    fun checkIsLoggedIn(): Flow<Boolean>
}