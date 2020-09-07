package com.biswatex.school;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class CustomRecycleAdapter extends RecyclerView.Adapter<CustomRecycleAdapter.ViewHolder> {

    private ExamsList context;
    private List<ExamModel> personUtils;
    private TakeExamListner onClickListener;
    public interface TakeExamListner{
        void TakeExamOnClick(View v, int position);
    }
    public CustomRecycleAdapter(ExamsList context, List<ExamModel> personUtils, TakeExamListner listner) {
        this.context = context;
        this.personUtils = personUtils;
        this.onClickListener = listner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exams, parent, false);
        ViewHolder viewHolder;
        viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(personUtils.get(position));
        ExamModel pu = personUtils.get(position);
        holder.examName.setText(pu.getExamName());
        holder.examClass.setText(pu.getExamClass());
    //    holder.examFullMarks.setText(pu.getexamFullMarks());
        holder.examSec.setText(pu.getexamSection());
       // holder.examTeacher.setText(pu.getExamTeacher());
        holder.examStartTime.setText(pu.getexamDuration());
    }
    @Override
    public int getItemCount() {
        return personUtils.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView examName;
        public TextView examClass;
        public TextView examSec;
        public TextView examTeacher;
        public TextView examFullMarks;
        public TextView examStartTime;
        public Button takeExam;
        public ViewHolder(View itemView) {
            super(itemView);

            examName = itemView.findViewById(R.id.txt_subject_item_exams);
            examClass = itemView.findViewById(R.id.txt_dept_class_item_exams);
            examSec = itemView.findViewById(R.id.txt_exam_sec_item_exams);
          //  examTeacher = itemView.findViewById(R.id.);
          //  examFullMarks = itemView.findViewById(R.id.txt_);
            examStartTime = itemView.findViewById(R.id.txt_deadline_item_exams);
            takeExam = itemView.findViewById(R.id.btn_take_exam_item_exams);
            takeExam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.TakeExamOnClick(v, getAdapterPosition());
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ExamModel cpu = (ExamModel) view.getTag();
                    Toast.makeText(view.getContext(), cpu.getExamId()+" "+cpu.getExamName()+" is "+ cpu.getExamClass(), Toast.LENGTH_SHORT).show();

                }
            });

        }
    }

}