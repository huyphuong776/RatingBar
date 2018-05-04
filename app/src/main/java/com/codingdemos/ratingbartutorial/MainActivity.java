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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RatingBar mRatingBar;
    TextView mRatingScale;
    EditText mFeedback;
    Button mSendFeedback;
    ListView lvPhanHoi;
    ArrayList<PhanHoi> arrayPhanHoi;
    DanhGiaApdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");


        Anhxa();
        adapter = new DanhGiaApdapter(this, R.layout.dong_phan_hoi, arrayPhanHoi);
        lvPhanHoi.setAdapter(adapter);



        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
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


    private void Anhxa() {
        mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        mRatingScale = (TextView) findViewById(R.id.tvRatingScale);
        mFeedback = (EditText) findViewById(R.id.etFeedback);
        mSendFeedback = (Button) findViewById(R.id.btnSubmit);

        lvPhanHoi = (ListView) findViewById(R.id.listviewPhanHoi);
        arrayPhanHoi = new ArrayList<>();
        arrayPhanHoi.add(new PhanHoi("Phuong", "rat tot"));
        arrayPhanHoi.add(new PhanHoi("Phuong", "rat tot"));
        arrayPhanHoi.add(new PhanHoi("Phuong", "rat tot"));
        arrayPhanHoi.add(new PhanHoi("Phuong", "rat tot"));
        arrayPhanHoi.add(new PhanHoi("Phuong", "rat tot"));
        arrayPhanHoi.add(new PhanHoi("Phuong", "rat tot"));

    }
}

