package com.example.recipemaster.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipemaster.Listeners.RecipeClickListener;
import com.example.recipemaster.Models.Recipe;
import com.example.recipemaster.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder> {

    Context context;
    List<Recipe> list;
    RecipeClickListener listener;

    public RandomRecipeAdapter(Context context, List<Recipe> list, RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    // Kreiranje ViewHolder-a
    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating layout "list_random_recipe" kako bi se prikazao jedan element u RecyclerView-u
        View view = LayoutInflater.from(context).inflate(R.layout.list_random_recipe, parent, false);
        return new RandomRecipeViewHolder(view);
    }

    // Povezivanje podataka s ViewHolder-om
    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        // Postavljanje naslova recepta
        holder.textView_title.setText(list.get(position).title);
        holder.textView_title.setSelected(true);

        // Postavljanje healthScore-a recepta
        holder.textView_healthScore.setText(list.get(position).healthScore + " score");

        // Postavljanje broja porcija recepta
        holder.textView_servings.setText(list.get(position).servings + " Servings");

        // Postavljanje vremena pripreme recepta
        holder.textView_time.setText(list.get(position).readyInMinutes + " Minutes");

        // Učitavanje slike recepta korištenjem Picasso biblioteke
        Picasso.get().load(list.get(position).image).into(holder.imageView_food);

        // Postavljanje klika na pojedinačni element u RecyclerView-u
        holder.random_list_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pozivanje metode onRecipeClicked iz RecipeClickListener interfejsa
                // i proslijeđivanje ID-a recepta koji je kliknut
                listener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });
    }

    // Vraćanje ukupnog broja recepata
    @Override
    public int getItemCount() {
        return list.size();
    }
}

// ViewHolder klasa za prikazivanje elemenata u RecyclerView-u
class RandomRecipeViewHolder extends RecyclerView.ViewHolder {
    CardView random_list_container;
    TextView textView_title, textView_servings, textView_healthScore, textView_time;
    ImageView imageView_food;

    public RandomRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        // Inicijalizacija elemenata prikaza iz layout-a "list_random_recipe"
        random_list_container = itemView.findViewById(R.id.random_list_container);
        textView_title = itemView.findViewById(R.id.textView_title);
        textView_servings = itemView.findViewById(R.id.textView_servings);
        textView_healthScore = itemView.findViewById(R.id.textView_healthScore);
        textView_time = itemView.findViewById(R.id.textView_time);
        imageView_food = itemView.findViewById(R.id.imageView_food);
    }
}
