package com.example.recipemaster;

import android.content.Context;

import com.example.recipemaster.Listeners.IntructionsListener;
import com.example.recipemaster.Listeners.RandomRecipeResponseListener;
import com.example.recipemaster.Listeners.RecipeDetailsListener;
import com.example.recipemaster.Listeners.SimilarRecipesListener;
import com.example.recipemaster.Models.InstructionsResponse;
import com.example.recipemaster.Models.RandomRecipeApiResponse;
import com.example.recipemaster.Models.RecipeDetailsResponse;
import com.example.recipemaster.Models.SimilarRecipeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    // Metoda za dohvaćanje slučajno odabranih recepata
    public void getRandomRecipes(RandomRecipeResponseListener listener, List<String> tags){
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        Call<RandomRecipeApiResponse> call = callRandomRecipes.callRandomRecipe(context.getString(R.string.rapidapikey), "10", tags);
        call.enqueue(new Callback<RandomRecipeApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    // Metoda za dohvaćanje detalja recepta
    public void getRecipeDetails(RecipeDetailsListener listener, int id){

        CallRecipeDetails callRecipeDetails = retrofit.create(CallRecipeDetails.class);
        Call<RecipeDetailsResponse> call = callRecipeDetails.callRecipeDetails(id,context.getString(R.string.rapidapikey));
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailsResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });

    }

    // Metoda za dohvaćanje sličnih recepata
    public void getSimilarRecipes(SimilarRecipesListener listener,int id){
        CallSimilarRecipes callSimilarRecipes = retrofit.create(CallSimilarRecipes.class);
        Call<List<SimilarRecipeResponse>> call = callSimilarRecipes.callSimilarRecipes(id,"10", context.getString(R.string.rapidapikey));
        call.enqueue(new Callback<List<SimilarRecipeResponse>>() {
            @Override
            public void onResponse(Call<List<SimilarRecipeResponse>> call, Response<List<SimilarRecipeResponse>> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<SimilarRecipeResponse>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    // Metoda za dohvaćanje instrukcija za pripremu recepta
    public void getInstructions(IntructionsListener listener, int id){
        CallInstructions callInstructions = retrofit.create(CallInstructions.class);
        Call<List<InstructionsResponse>> call = callInstructions.callInstructions(id, context.getString(R.string.rapidapikey));
        call.enqueue(new Callback<List<InstructionsResponse>>() {
            @Override
            public void onResponse(Call<List<InstructionsResponse>> call, Response<List<InstructionsResponse>> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<InstructionsResponse>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    // Sučelje za API poziv za dohvaćanje slučajno odabranih recepata
    private interface CallRandomRecipes{
        @GET("/recipes/random")
        Call<RandomRecipeApiResponse> callRandomRecipe(
                @Query("rapidapi-key") String rapidapikey,
                @Query("number") String number,
                @Query("tags") List<String> tags
        );
    }

    // Sučelje za API poziv za dohvaćanje detalja recepta
    private interface  CallRecipeDetails{
        @GET("recipes/{id}/information")
        Call<RecipeDetailsResponse> callRecipeDetails(
                @Path("id") int id,
                @Query("rapidapi-key") String rapidapikey
        );
    }

    // Sučelje za API poziv za dohvaćanje sličnih recepata
    private interface  CallSimilarRecipes{
        @GET("recipes/{id}/similar")
        Call<List<SimilarRecipeResponse>> callSimilarRecipes(
                @Path("id") int id,
                @Query("number") String number,
                @Query("rapidapi-key") String rapidapikey
        );
    }

    // Sučelje za API poziv za dohvaćanje instrukcija za pripremu recepta
    private interface  CallInstructions{
        @GET("recipes/{id}/analyzedInstructions")
        Call<List<InstructionsResponse>> callInstructions(
                @Path("id") int id,
                @Query("rapidapi-key") String rapidapikey
        );
    }
}
