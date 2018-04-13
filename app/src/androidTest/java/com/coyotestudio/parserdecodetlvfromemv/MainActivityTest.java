package com.coyotestudio.parserdecodetlvfromemv;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by J. Carlos Medina Díaz @_CarlosMD on 4/13/18.
 * Coyote Dev Studio
 * carlos.medj@gmail.com
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickDecodeButton() {
        onView((withId(R.id.btn_decoder)))
                .perform(click());
    }
}
