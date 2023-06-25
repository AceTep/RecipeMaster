package com.example.recipemaster.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipemaster.Models.ExtendedIngredient;
import com.example.recipemaster.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHolder> {

    Context context;
    List<ExtendedIngredient> list;

    public IngredientsAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    // Kreiranje ViewHolder-a
    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating layout "list_meal_ingredies" kako bi se prikazao jedan element u RecyclerView-u
        View view = LayoutInflater.from(context).inflate(R.layout.list_meal_ingredies, parent, false);
        return new IngredientsViewHolder(view);
    }

    // Povezivanje podataka s ViewHolder-om
    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        // Postavljanje naziva sastojka
        holder.textView_ingredients_name.setText(list.get(position).name);
        // Omogućavanje automatskog pomicanja teksta ako je tekst predugačak
        holder.textView_ingredients_name.setSelected(true);
        // Postavljanje količine sastojka
        holder.textView_ingredients_quantity.setText(list.get(position).original);
        // Omogućavanje automatskog pomicanja teksta ako je tekst predugačak
        holder.textView_ingredients_quantity.setSelected(true);
        // Učitavanje slike sastojka koristeći Picasso biblioteku i prikaz u ImageView-u
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/" + list.get(position).image).into(holder.imageView_ingredients);
    }

    // Vraćanje ukupnog broja stavki u popisu sastojaka
    @Override
    public int getItemCount() {
        return list.size();
    }
}

// ViewHolder klasa za prikazivanje elemenata u RecyclerView-u
class IngredientsViewHolder extends RecyclerView.ViewHolder {

    TextView textView_ingredients_quantity, textView_ingredients_name;
    ImageView imageView_ingredients;

    public IngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        // Inicijalizacija elemenata prikaza iz layout-a "list_meal_ingredies"
        textView_ingredients_quantity = itemView.findViewById(R.id.textView_ingredients_quantity);
        textView_ingredients_name = itemView.findViewById(R.id.textView_ingredients_name);
        imageView_ingredients = itemView.findViewById(R.id.imageView_ingredients);
    }
}
