package com.example.nikonorov.gleb.simplecalculator;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.action.ViewActions.click;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class CalcTest {

    //Launch App
    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void verifyAddTwoNumbers() throws Exception{
        //2+2=4
        onView(withId(R.id.twoButton)).perform(click());
        onView(withId(R.id.adButton)).perform(click());
        onView(withId(R.id.twoButton)).perform(click());
        onView(withId(R.id.equalsButton)).perform(click());

        //Result is Four
        onView(withId(R.id.outputLabel)).check(matches(withText("4")));
        //Espresso -> work with view
        //ViewMatchers -> Identify the element
        //ViewActions -> perform actions on the element
        //ViewAssertions -> validating

        //100 Lines of source Code
        //100(Max) Lines of Test Coverage
    }

    @Test
    public void mulTwoNumbers(){
        //4*4 =16
        onView(withId(R.id.fourButton)).perform(click());
        onView(withId(R.id.multiplyButton)).perform(click());
        onView(withId(R.id.fourButton)).perform(click());
        onView(withId(R.id.equalsButton)).perform(click());

        //Result is 16
        onView(withId(R.id.outputLabel)).check(matches(withText("16")));
    }

    @Test
    public void divTwoNumbers(){
        //4*4 =16
        onView(withId(R.id.eightButton)).perform(click());
        onView(withId(R.id.divideButton)).perform(click());
        onView(withId(R.id.fourButton)).perform(click());
        onView(withId(R.id.equalsButton)).perform(click());

        //Result is 16
        onView(withId(R.id.outputLabel)).check(matches(withText("2")));
    }

    @Test
    public void subTwoNumbers(){
        //4*4 =16
        onView(withId(R.id.eightButton)).perform(click());
        onView(withId(R.id.subtractButton)).perform(click());
        onView(withId(R.id.fourButton)).perform(click());
        onView(withId(R.id.equalsButton)).perform(click());

        //Result is 16
        onView(withId(R.id.outputLabel)).check(matches(withText("4")));
    }

}
