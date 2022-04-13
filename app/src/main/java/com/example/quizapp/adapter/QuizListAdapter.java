package com.example.quizapp.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.model.QuizListModel;
import com.example.quizapp.views.activities.Landing_Page_Activity;
import com.example.quizapp.views.activities.QuizListActivity;

import java.util.List;

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.ViewHolder> {
    private static final String TAG = "Adapter";
    private List<QuizListModel>quizList;
    public QuizListAdapter.RecyclerViewClickListener listener;
    Context context;

    public QuizListAdapter(Context context, List<QuizListModel> quizList, RecyclerViewClickListener listener) {
        this.quizList = quizList;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        QuizListModel quizListModel = quizList.get(position);

        Log.d(TAG, "onBindViewHolder: "+quizListModel.getQuizname());

        holder.quizTitleTextView.setText(quizListModel.getQuizname());
        holder.quizTimeTextView.setText(String.valueOf(quizListModel.getTime())+" minutes");
        holder.quizNoOfQuestionTextView.setText(String.valueOf(quizListModel.getNumberofquestion()));
        holder.getQuizMarksTextView.setText(String.valueOf(quizListModel.getMarks()));

        boolean isExpanded = quizList.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

        holder.cardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.example.quizapp.model.QuizListModel movie = quizList.get(position);
                movie.setExpanded(!movie.isExpanded());
                notifyItemChanged(position);

            }
        });
        holder.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QuizListActivity.class);
                intent.putExtra("id", quizListModel.getId());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+quizList.size());
        return quizList.size();
    }
    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        TextView quizTitleTextView, quizTimeTextView, quizNoOfQuestionTextView,getQuizMarksTextView;
        CardView cardItem;
        ConstraintLayout expandableLayout;
        Button btnStart;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            cardItem = itemView.findViewById(R.id.cardRecycle);
            quizTitleTextView = itemView.findViewById(R.id.quizTitleTextView);
            quizTimeTextView = itemView.findViewById(R.id.quizTimeTextView);
            quizNoOfQuestionTextView = itemView.findViewById(R.id.quizNoOfQuestTextView);
            getQuizMarksTextView = itemView.findViewById(R.id.quizMarksTextView);
            btnStart = itemView.findViewById(R.id.btnStartQuiz);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);

        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }
    }
    public  interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}
