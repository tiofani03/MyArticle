package id.tiooooo.myarticle.data.impl.repo

import id.tiooooo.myarticle.data.implementation.local.dao.SearchDao
import id.tiooooo.myarticle.data.implementation.local.entity.SearchEntity
import id.tiooooo.myarticle.data.implementation.repo.SearchRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
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
class SearchRepositoryImplTest {

    private lateinit var repository: SearchRepositoryImpl
    private val dao: SearchDao = mock()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = SearchRepositoryImpl(dao)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `insertSearch calls dao insert with correct entity`() = runTest {
        val keyword = "test"
        repository.insertSearch(keyword)

        verify(dao).insertSearch(SearchEntity(keyword = keyword))
    }

    @Test
    fun `getAllSearches delegates to dao`() {
        val flow = flowOf(listOf(SearchEntity(1, "a"), SearchEntity(2, "b")))
        whenever(dao.getAllSearches()).thenReturn(flow)

        val result = repository.getAllSearches()

        assertEquals(flow, result)
        verify(dao).getAllSearches()
    }

    @Test
    fun `deleteSearchById calls dao with correct id`() = runTest {
        repository.deleteSearchById(123)
        verify(dao).deleteSearchById(123)
    }

    @Test
    fun `clearAllSearches calls dao`() = runTest {
        repository.clearAllSearches()
        verify(dao).clearAllSearches()
    }
}
