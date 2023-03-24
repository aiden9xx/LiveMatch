package com.aiden.soccer.espresso

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.aiden.soccer.presentation.match.WatchMatchActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TeamFragmentTest {

  @get:Rule
  val activityRule = ActivityScenarioRule(WatchMatchActivity::class.java)

  @Test
  fun listGoesOverTheFold() {
    onView(withText("Highlights")).check(matches(isDisplayed()))
  }
}