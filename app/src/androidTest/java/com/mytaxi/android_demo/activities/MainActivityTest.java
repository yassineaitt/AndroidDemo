package com.mytaxi.android_demo.activities;


import android.os.SystemClock;
import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.mytaxi.android_demo.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    private MainActivity mActivity = null;

    @Before
    public void setActivity() {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void mainActivityTest() {

        /** Ensuring that the EditText username is displayed */
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.edt_username),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        /**  Typing the username in the field identified above */
        appCompatEditText.perform(replaceText("whiteelephant261"), closeSoftKeyboard());

        /** Ensuring that the EditText username is displayed  */
        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.edt_password),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        /**  Typing the password in the field identified above */
        appCompatEditText2.perform(replaceText("video1"), closeSoftKeyboard());

        /**  Searching for the LOGIN button */
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btn_login), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        /**  Click on the LOGIN button */
        appCompatButton.perform(click());

        /**  Waiting for the next screen to be displayed */
        SystemClock.sleep(3000);

        /** Ensuring that the text search EditText is displayed  */
        ViewInteraction appCompatAutoCompleteTextView = onView(
                allOf(withId(R.id.textSearch),
                        childAtPosition(
                                allOf(withId(R.id.searchContainer),
                                        childAtPosition(
                                                withClassName(is("android.support.design.widget.CoordinatorLayout")),
                                                1)),
                                0),
                        isDisplayed()));

        /** Typing the value "sa" */
        appCompatAutoCompleteTextView.perform(typeText("sa"), closeSoftKeyboard());
        /**  Waiting for the options list to be displayed */
        SystemClock.sleep(3000);

      /*
      // The code below was generated by ESPRESSO recorder, however it didn't work for me.
      // I replaced onData by onView (See code block below)

       DataInteraction appCompatTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(2);
        appCompatTextView.perform(click());*/


        /*onData(allOf(is(instanceOf(String.class)), is("Sarah Friedrich"))) // Use Hamcrest matchers to match item
                .inAdapterView(withId(R.id.searchContainer)) // Specify the explicit id of the ListView
                .perform(click());*/
        /** Ensuring that the search option is displayed  */
        onView(withText("Sarah Friedrich"))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));

        /** Selecting the search option is displayed  */
        onView(withText("Sarah Friedrich"))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .perform(click());


        /**  Waiting for the next screen to be displayed */
        SystemClock.sleep(3000);;

        /**  Ensuring that the callbutton is displayed */
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        /**  Selecting the call button */
        floatingActionButton.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
