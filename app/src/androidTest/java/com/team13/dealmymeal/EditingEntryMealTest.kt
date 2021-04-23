package com.team13.dealmymeal

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import java.util.regex.Pattern.matches

class EditingEntryMealTest {

   // @get:Rule
    //val rule = ActivityScenarioRule(XXX::class.java)

    @Test
    fun checkClickEditingIcon()
    {
       onView(withId(R.id.edit_button_id)).check(matches(isDisplayed()))
        onView(withId(R.id.edit_button_id)).perform(click())
    }


}