package com.biswatex.school;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private TakeExam context;
    private List<QuestionModel> questionUtils;
    private SubmitExamListner onClickListener;
    public interface SubmitExamListner{
        void SubmitExamOnClick(View v, int position);
    }
    public QuestionAdapter(TakeExam context, List<QuestionModel> personUtils, SubmitExamListner listner) {
        this.context = context;
        this.questionUtils = personUtils;
        this.onClickListener = listner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_questionview, parent, false);
        ViewHolder viewHolder;
        viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(questionUtils.get(position));
        QuestionModel pu = questionUtils.get(position);
        holder.question.setText(pu.getquestion());
        holder.marks.setText(pu.getMarks());
    }
    @Override
    public int getItemCount() {
        return questionUtils.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView question;
        public TextView marks;
        public ViewHolder(View itemView) {
            super(itemView);

            question = itemView.findViewById(R.id.textView_question);
            marks = itemView.findViewById(R.id.textView2_marks);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.SubmitExamOnClick(view, getAdapterPosition());
                }
            });

        }
    }

}
