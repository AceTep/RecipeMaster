package com.example.recipemaster.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipemaster.Models.Step;
import com.example.recipemaster.R;

import java.util.List;

public class InstructionStepAdapter extends RecyclerView.Adapter<InstructionStepViewHolder> {

    Context context;
    List<Step> list;

    public InstructionStepAdapter(Context context, List<Step> list) {
        this.context = context;
        this.list = list;
    }

    // Kreiranje ViewHolder-a
    @NonNull
    @Override
    public InstructionStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating layout "list_instructions_steps" kako bi se prikazao jedan element u RecyclerView-u
        View view = LayoutInflater.from(context).inflate(R.layout.list_instructions_steps, parent, false);
        return new InstructionStepViewHolder(view);
    }

    // Povezivanje podataka s ViewHolder-om
    @Override
    public void onBindViewHolder(@NonNull InstructionStepViewHolder holder, int position) {
        // Postavljanje broja koraka
        holder.textView_instructions_step_number.setText(String.valueOf(list.get(position).number));
        // Postavljanje naslova koraka
        holder.textView_instructions_step_title.setText(list.get(position).step);

        // Konfiguracija i postavljanje RecyclerView-a za prikazivanje sastojaka koraka
        holder.recycler_instruction_ingredients.setHasFixedSize(true);
        holder.recycler_instruction_ingredients.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        InstructionsIngredientsAdapter instructionsIngredientsAdapter = new InstructionsIngredientsAdapter(context, list.get(position).ingredients);
        holder.recycler_instruction_ingredients.setAdapter(instructionsIngredientsAdapter);

        // Konfiguracija i postavljanje RecyclerView-a za prikazivanje opreme koraka
        holder.recycler_instruction_equipments.setHasFixedSize(true);
        holder.recycler_instruction_equipments.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        InstructionsEquipmentsAdapter instructionsEquipmentsAdapter = new InstructionsEquipmentsAdapter(context, list.get(position).equipment);
        holder.recycler_instruction_equipments.setAdapter(instructionsEquipmentsAdapter);
    }

    // Vraćanje ukupnog broja koraka
    @Override
    public int getItemCount() {
        return list.size();
    }
}

// ViewHolder klasa za prikazivanje elemenata u RecyclerView-u
class InstructionStepViewHolder extends RecyclerView.ViewHolder {

    TextView textView_instructions_step_number, textView_instructions_step_title;
    RecyclerView recycler_instruction_equipments, recycler_instruction_ingredients;

    public InstructionStepViewHolder(@NonNull View itemView) {
        super(itemView);
        // Inicijalizacija elemenata prikaza iz layout-a "list_instructions_steps"
        textView_instructions_step_number = itemView.findViewById(R.id.textView_instructions_step_number);
        textView_instructions_step_title = itemView.findViewById(R.id.textView_instructions_step_title);
        recycler_instruction_equipments = itemView.findViewById(R.id.recycler_instruction_equipments);
        recycler_instruction_ingredients = itemView.findViewById(R.id.recycler_instruction_ingredients);
    }
}
