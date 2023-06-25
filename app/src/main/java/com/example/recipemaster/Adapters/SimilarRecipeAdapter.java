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
import com.example.recipemaster.Models.SimilarRecipeResponse;
import com.example.recipemaster.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarRecipeAdapter extends RecyclerView.Adapter<SimilarRecipeViewHolder> {

    Context context;
    List<SimilarRecipeResponse> list;
    RecipeClickListener listener;

    public SimilarRecipeAdapter(Context context, List<SimilarRecipeResponse> list, RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    // Kreiranje ViewHolder-a
    @NonNull
    @Override
    public SimilarRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating layout "list_similar_recipe" kako bi se prikazao jedan element u RecyclerView-u
        View view = LayoutInflater.from(context).inflate(R.layout.list_similar_recipe, parent, false);
        return new SimilarRecipeViewHolder(view);
    }

    // Povezivanje podataka s ViewHolder-om
    @Override
    public void onBindViewHolder(@NonNull SimilarRecipeViewHolder holder, int position) {
        // Postavljanje naslova sličnog recepta
        holder.textView_similar_title.setText(list.get(position).title);
        holder.textView_similar_title.setSelected(true);

        // Postavljanje broja porcija sličnog recepta
        holder.textView_similar_serving.setText(list.get(position).servings + " persons");
        holder.textView_similar_serving.setSelected(true);

        // Učitavanje slike sličnog recepta korištenjem Picasso biblioteke
        Picasso.get().load("https://spoonacular.com/recipeImages/" + list.get(position).id + "-556x370." + list.get(position).imageType).into(holder.imageView_similar);

        // Postavljanje klika na pojedinačni element u RecyclerView-u
        holder.similar_recipe_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pozivanje metode onRecipeClicked iz RecipeClickListener interfejsa
                // i proslijeđivanje ID-a sličnog recepta koji je kliknut
                listener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });
    }

    // Vraćanje ukupnog broja sličnih recepata
    @Override
    public int getItemCount() {
        return list.size();
    }
}

// ViewHolder klasa za prikazivanje elemenata u RecyclerView-u
class SimilarRecipeViewHolder extends RecyclerView.ViewHolder {
    CardView similar_recipe_holder;
    TextView textView_similar_title, textView_similar_serving;
    ImageView imageView_similar;

    public SimilarRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        // Inicijalizacija elemenata prikaza iz layout-a "list_similar_recipe"
        similar_recipe_holder = itemView.findViewById(R.id.similar_recipe_holder);
        textView_similar_title = itemView.findViewById(R.id.textView_similar_title);
        textView_similar_serving = itemView.findViewById(R.id.textView_similar_serving);
        imageView_similar = itemView.findViewById(R.id.imageView_similar);
    }
}
