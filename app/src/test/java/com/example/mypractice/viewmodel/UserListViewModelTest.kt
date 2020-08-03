package com.example.mypractice.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mypractice.model.UserData
import com.example.mypractice.network.RemoteApi
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule


@RunWith(MockitoJUnitRunner::class)
class UserListViewModelTest {
    companion object {
        /** Query @param */
        const val SITE = "stackoverflow"
        const val SORT = "reputation"
        const val ORDER = "desc"

    }

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    /**android components used in our unit tests to be executed synchronously instead of using the background executor used normally*/
    private var userListViewModel = mock<UserListViewModel>()
    private val userData = mock<UserData>()
    private val remoteApi = mock<RemoteApi>()

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun setUp() {
        /** Due to the AndroidSchedulers initialization sequence, we need to set an override at the earliest possible stage; by calling setInitMainThreadSchedulerHandler*/
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        MockitoAnnotations.initMocks(this)
        userListViewModel = UserListViewModel()
    }

    var result = ""

    /** Example of Unit test -> Simple subscription to a fix value */
    @Test
    fun returnAValue() {
        result = ""
        val observer =
            Observable.just("Hello") // provides data
        observer.subscribe { s: String ->
            result = s
        } // Callable as subscriber
        assertTrue(result == "Hello")
    }

    @Test
    fun testUserListSuccess() {

        val testObserver = TestObserver.create<UserData>()
        val observer = Observable.just(userData)
        observer.subscribe(testObserver)
        testObserver.assertSubscribed();

        `when`(
            remoteApi.getData(
                ORDER,
                SORT,
                SITE
            )
        )
            .thenReturn(Observable.just(userData))

        userListViewModel.postApi.getData(
            ORDER,
            SORT,
            SITE
        ).subscribe()
        assertTrue(true)
    }

    @After
    fun tearDown() {
        userListViewModel==null
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }
}

