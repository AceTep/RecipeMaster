package com.example.recipemaster.Listeners;

import com.example.recipemaster.Models.InstructionsResponse;

import java.util.List;

public interface IntructionsListener {
    void didFetch(List<InstructionsResponse> response, String message);
    void didError(String message);
}
