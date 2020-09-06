package com.biswatex.school;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class CustomRecycleAdapter extends RecyclerView.Adapter<CustomRecycleAdapter.ViewHolder> {

    private ExamsList context;
    private List<PersonUtils> personUtils;

    public CustomRecycleAdapter(ExamsList context, List<PersonUtils> personUtils) {
        this.context = context;
        this.personUtils = personUtils;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_list, parent, false);
        ViewHolder viewHolder;
        viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(personUtils.get(position));
        PersonUtils pu = personUtils.get(position);
        holder.pName.setText(String.format("%s %s", pu.getPersonFirstName(), pu.getPersonLastName()));
        holder.pJobProfile.setText(pu.getJobProfile());
    }
    @Override
    public int getItemCount() {
        return personUtils.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView pName;
        public TextView pJobProfile;

        public ViewHolder(View itemView) {
            super(itemView);

            pName = itemView.findViewById(R.id.exam_sub);
            pJobProfile = itemView.findViewById(R.id.exam_type);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PersonUtils cpu = (PersonUtils) view.getTag();
                    Toast.makeText(view.getContext(), cpu.getPersonFirstName()+" "+cpu.getPersonLastName()+" is "+ cpu.getJobProfile(), Toast.LENGTH_SHORT).show();

                }
            });

        }
    }

}