package id.tiooooo.myarticle.data.impl.repo

import com.auth0.android.Auth0
import id.tiooooo.myarticle.data.implementation.local.datastore.AppDatastore
import id.tiooooo.myarticle.data.implementation.repo.UserRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class UserRepositoryImplTest {

    private lateinit var repository: UserRepositoryImpl
    private val appDatastore: AppDatastore = mock()
    private val account: Auth0 = mock()

    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = CoroutineScope(testDispatcher)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = UserRepositoryImpl(appDatastore, account, testScope)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `checkIsLoggedIn returns true when logged in and email is not empty`() = runTest {
        whenever(appDatastore.isLoggedIn).thenReturn(flowOf(true))
        whenever(appDatastore.userEmail).thenReturn(flowOf("fanitio21@gmail.com"))

        val result = repository.checkIsLoggedIn().first()

        assertTrue(result)
        verify(appDatastore).isLoggedIn
        verify(appDatastore).userEmail
    }

    @Test
    fun `checkIsLoggedIn returns false when email is empty`() = runTest {
        whenever(appDatastore.isLoggedIn).thenReturn(flowOf(true))
        whenever(appDatastore.userEmail).thenReturn(flowOf(""))

        val result = repository.checkIsLoggedIn().first()

        assertFalse(result)
        verify(appDatastore).isLoggedIn
        verify(appDatastore).userEmail
    }

    @Test
    fun `getProfileEmail delegates to appDatastore`() = runTest {
        val emailFlow = flowOf("fanitio21@gmail.com")
        whenever(appDatastore.userEmail).thenReturn(emailFlow)

        val result = repository.getProfileEmail()

        assertEquals(emailFlow, result)
        verify(appDatastore).userEmail
    }

    @Test
    fun `getProfileNickName delegates to appDatastore`() = runTest {
        val nameFlow = flowOf("TestUser")
        whenever(appDatastore.userNickName).thenReturn(nameFlow)

        val result = repository.getProfileNickName()

        assertEquals(nameFlow, result)
        verify(appDatastore).userNickName
    }
}

