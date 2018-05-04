package com.codingdemos.ratingbartutorial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RatingBar mRatingBar;
    TextView mRatingScale;
    EditText mFeedback;
    Button mSendFeedback;
    ListView lvPhanHoi;
    ArrayList<PhanHoi> arrayPhanHoi;
    DanhGiaApdapter adapter;

    int memBer = 1;
    int raTe = 1;
    FirebaseDatabase mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mData = FirebaseDatabase.getInstance();

        Anhxa();
        testRatingBar();

        adapter = new DanhGiaApdapter(this, R.layout.dong_phan_hoi, arrayPhanHoi);
        lvPhanHoi.setAdapter(adapter);




        mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFeedback.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Xin điền vào phản hồi", Toast.LENGTH_LONG).show();
                } else {

                    //mFeedback.setText("");
                    //mRatingBar.setRating(0);
                    Toast.makeText(MainActivity.this, "Cám ơn bạn đã phản hồi", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void testRatingBar() {
        final DatabaseReference ref = mData.getReference("Rate").child("rating");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                    float rating = Float.parseFloat(dataSnapshot.getValue().toString());
                    mRatingBar.setRating(rating);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {



                if (fromUser) ref.setValue(rating);
                mRatingScale.setText(String.valueOf(rating));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScale.setText("Rất tệ");
                        break;
                    case 2:
                        mRatingScale.setText("Cần cải thiện");

                        break;
                    case 3:
                        mRatingScale.setText("Tốt");

                        break;
                    case 4:
                        mRatingScale.setText("Rất tốt");

                        break;
                    case 5:
                        mRatingScale.setText("Tuyệt vời");
                        break;
                    default:
                        mRatingScale.setText("");


                }
            }
        });

    }
    

    private void Anhxa() {
        mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        mRatingScale = (TextView) findViewById(R.id.tvRatingScale);
        mFeedback = (EditText) findViewById(R.id.etFeedback);
        mSendFeedback = (Button) findViewById(R.id.btnSubmit);

        lvPhanHoi = (ListView) findViewById(R.id.listviewPhanHoi);
        arrayPhanHoi = new ArrayList<>();
        arrayPhanHoi.add(new PhanHoi("Phuong", "rat tot"));
        arrayPhanHoi.add(new PhanHoi("Huy", "rat tot"));
        arrayPhanHoi.add(new PhanHoi("Phan", "rat tot"));
        arrayPhanHoi.add(new PhanHoi("An", "rat tot"));
        arrayPhanHoi.add(new PhanHoi("Manh", "rat tot"));
        arrayPhanHoi.add(new PhanHoi("Trinh", "rat tot"));

    }
}

