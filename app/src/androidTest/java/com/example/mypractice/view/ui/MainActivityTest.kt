package com.example.mypractice.view.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.rule.ActivityTestRule
import com.example.mypractice.view.adapter.UserListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@MediumTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    /** for activity test we need activity rule */
    @get:Rule
    val mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    var mManiActivity: MainActivity? = null

    @Test
    fun launch() {
        val view: View = mManiActivity!!.user_list
        assertNotNull(view)
        /** if view is not null then activity is successfully launched */
    }

    @Test
    fun ensureRecyclerViewIsPresentAndAdapterIsAttached() {
        val viewById: View = mManiActivity!!.user_list as RecyclerView
        assertThat(viewById, notNullValue())
        assertThat(viewById, instanceOf(RecyclerView::class.java))
        val listView: RecyclerView = viewById as RecyclerView
        val adapter: UserListAdapter = listView.adapter as UserListAdapter
        assertThat(adapter, instanceOf(UserListAdapter::class.java))
    }

    @Before
    fun setUp() {
        /** get the instance for activity*/
        mManiActivity = mActivityRule.activity
    }

    @After
    fun tearDown() {
        mManiActivity = null
        /** Do activity null at the end*/
    }
}