package com.example.codeforceapi;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView pythonImage, cppImage, javaImage;
    TextView questionId, questionName, rating, pythonName, cppName, javaName, acceptance;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        pythonImage = itemView.findViewById(R.id.pythonImage);
        cppImage = itemView.findViewById(R.id.cppImage);
        javaImage = itemView.findViewById(R.id.javaImage);
        questionId = itemView.findViewById(R.id.questionId);
        questionName = itemView.findViewById(R.id.questionName);
        rating = itemView.findViewById(R.id.rating);
        pythonName = itemView.findViewById(R.id.pythonText);
        cppName = itemView.findViewById(R.id.cppText);
        javaName = itemView.findViewById(R.id.javaText);
        acceptance = itemView.findViewById(R.id.accept);
    }
}
