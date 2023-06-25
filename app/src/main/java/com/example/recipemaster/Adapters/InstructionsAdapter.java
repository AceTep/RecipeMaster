package com.example.recipemaster.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipemaster.Models.InstructionsResponse;
import com.example.recipemaster.R;

import java.util.List;

public class InstructionsAdapter extends RecyclerView.Adapter<InstructionsViewHolder> {

    Context context;
    List<InstructionsResponse> list;

    public InstructionsAdapter(Context context, List<InstructionsResponse> list) {
        this.context = context;
        this.list = list;
    }

    // Kreiranje ViewHolder-a
    @NonNull
    @Override
    public InstructionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating layout "list_instructions" kako bi se prikazao jedan element u RecyclerView-u
        View view = LayoutInflater.from(context).inflate(R.layout.list_instructions, parent, false);
        return new InstructionsViewHolder(view);
    }

    // Povezivanje podataka s ViewHolder-om
    @Override
    public void onBindViewHolder(@NonNull InstructionsViewHolder holder, int position) {
        // Postavljanje naziva uputa
        holder.textView_instruction_name.setText(list.get(position).name);

        // Postavljanje RecyclerView-a unutar kojeg se prikazuju koraci uputa
        holder.recycler_instructions_steps.setHasFixedSize(true);
        holder.recycler_instructions_steps.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        // Kreiranje i postavljanje adaptera za korake uputa
        InstructionStepAdapter stepAdapter = new InstructionStepAdapter(context, list.get(position).steps);
        holder.recycler_instructions_steps.setAdapter(stepAdapter);
    }

    // Vraćanje ukupnog broja stavki u popisu uputa
    @Override
    public int getItemCount() {
        return list.size();
    }
}

// ViewHolder klasa za prikazivanje elemenata u RecyclerView-u
class InstructionsViewHolder extends RecyclerView.ViewHolder {

    TextView textView_instruction_name;
    RecyclerView recycler_instructions_steps;

    public InstructionsViewHolder(@NonNull View itemView) {
        super(itemView);
        // Inicijalizacija elemenata prikaza iz layout-a "list_instructions"
        textView_instruction_name = itemView.findViewById(R.id.textView_instruction_name);
        recycler_instructions_steps = itemView.findViewById(R.id.recycler_instructions_steps);
    }
}
