package au.com.hermitagewool.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.com.hwool.hermitageintelligenceagency.R;

import au.com.hermitagewool.models.News;

public class NewsLetterAdapter extends RecyclerView.Adapter<NewsLetterAdapter.NewsViewHolder>  {
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle =  itemView.findViewById(R.id.tvTitle);
        }

        public void bind(News news){
            tvTitle.setText(news.getmTitle());
        }
    }
}
