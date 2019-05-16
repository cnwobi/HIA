package au.com.hermitagewool.views;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.com.hwool.hermitageintelligenceagency.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import au.com.hermitagewool.models.News;
import au.com.hermitagewool.repository.FirebaseHelper;

public class NewsLetterAdapter extends RecyclerView.Adapter<NewsLetterAdapter.NewsViewHolder>  {
    private DatabaseReference newsLetterReference;
    private ChildEventListener childEventListener;

    public NewsLetterAdapter(){
        newsLetterReference = FirebaseHelper.getNewsLetterReference();
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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
    }


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
