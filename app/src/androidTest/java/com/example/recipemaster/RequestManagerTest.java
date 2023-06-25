package com.example.recipemaster;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.recipemaster.Listeners.RandomRecipeResponseListener;
import com.example.recipemaster.Models.RandomRecipeApiResponse;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class RequestManagerTest {

    @Test
    public void testRandomRecipesApiCall() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        RequestManager requestManager = new RequestManager(context);

        List<String> tags = new ArrayList<>();
        // Add valid tags to the list

        RandomRecipeResponseListener listener = new RandomRecipeResponseListener() {
            @Override
            public void didFetch(RandomRecipeApiResponse response, String message) {
                // Assert the response and perform necessary checks
                // You can use assertions like assertEquals, assertTrue, etc.
            }

            @Override
            public void didError(String error) {
                // Handle error cases if needed
            }
        };

        // Make the API call
        requestManager.getRandomRecipes(listener, tags);
        // Use Espresso's synchronization mechanism to wait for the API call to complete
        // You can use IdlingRegistry and IdlingResource for this purpose
        // For example, you can use CountingIdlingResource to wait for the callback to be invoked

        // Perform assertions and verifications as needed
    }

}
