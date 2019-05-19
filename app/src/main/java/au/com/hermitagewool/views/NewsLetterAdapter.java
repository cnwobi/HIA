package au.com.hermitagewool.views;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.com.hwool.hermitageintelligenceagency.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import au.com.hermitagewool.models.News;
import au.com.hermitagewool.repository.FirebaseHelper;

public class NewsLetterAdapter extends RecyclerView.Adapter<NewsLetterAdapter.NewsViewHolder>  {
    private static final String TAG = "NewsLetterAdapter";
    private DatabaseReference  newsLetterReference;
    private ChildEventListener childEventListener;

    List<News> newsLetter = new ArrayList<>();


    public NewsLetterAdapter() {
        newsLetterReference = FirebaseHelper.getNewsLetterReference();
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                News news =  dataSnapshot.getValue(News.class);
                news.setId(dataSnapshot.getKey());
                newsLetterReference.child(dataSnapshot.getKey()).setValue(news);
                newsLetter.add(news);
                Log.d(TAG, "onChildAdded: "+ news);
                notifyItemInserted(newsLetter.size()-1);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        newsLetterReference.addChildEventListener(childEventListener);
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context =  viewGroup.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.rv_cardview,viewGroup,false);
        return new NewsViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {
        News news = newsLetter.get(i);
        newsViewHolder.bind(news);

    }


    @Override
    public int getItemCount() {
        return newsLetter.size();
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //    TextView tvTitle;
        TextView tvTitleCard;
        TextView tvDateCreated;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            //  tvTitle =  itemView.findViewById(R.id.tvTitle);
            tvTitleCard   = itemView.findViewById(R.id.tvTitleCard);
            tvDateCreated = itemView.findViewById(R.id.tvDateCreated);
            itemView.setOnClickListener(this);
        }


        public void bind(News news){
            //   tvTitle.setText(news.getmTitle());
            tvTitleCard.setText(news.getmTitle());
            tvDateCreated.setText(news.getmCreationDate());
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Log.d(TAG, "\n\n\n\n\nonClick Position: " + position);
            News   selectedNews = newsLetter.get(position);
            Intent intent       =  new Intent(v.getContext(), NewsDetailActivity.class);
            intent.putExtra("news", selectedNews);
            v.getContext().startActivity(intent);
        }
    }
}
