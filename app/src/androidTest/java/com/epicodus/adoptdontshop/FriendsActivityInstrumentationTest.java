package com.epicodus.adoptdontshop;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.core.IsNot.not;

public class FriendsActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<FriendsActivity> activityTestRule =
            new ActivityTestRule<>(FriendsActivity.class);

    @Test
    public void listItemClickDisplaysToastWithCorrectFriend() {
        View activityDecorView = activityTestRule.getActivity().getWindow().getDecorView();
        String friendName = "Harry - Cat";
        onData(anything())
                .inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .perform(click());
        onView(withText(friendName)).inRoot(withDecorView(not(activityDecorView)))
                .check(matches(withText(friendName)));
    }
}
