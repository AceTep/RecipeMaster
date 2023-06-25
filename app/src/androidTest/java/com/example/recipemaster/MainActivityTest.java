package com.example.recipemaster;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.recipemaster.Listeners.RecipeClickListener;
import com.example.recipemaster.MainActivity;
import com.example.recipemaster.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    MainActivity mainActivity;
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testSpinnerText(){
        onView(withId(R.id.spinner_tags).matches(isDisplayed()));
    }

    @Test
    public void searchTestText() {
        onView(withId(R.id.searchView_home).matches(withText("Search your recipe")));
    }

    @Test
    public void testTitleText() {
        onView(withId(R.id.textViewTitle).matches(withText("RecipeMaster")));
    }



    private void onView(boolean search_your_recipe) {
        return;
    }

}
