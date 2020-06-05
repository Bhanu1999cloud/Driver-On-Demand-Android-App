package com.example.driverondemandapp;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.driverondemandapp.com.beans.Driver;
import com.example.driverondemandapp.com.dbtask.DriverConstant;
import com.example.driverondemandapp.com.dbtask.DriverManager;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Feedback extends Fragment {
    EditText txtfeed,txtexp;
    TextView reaction;
    Button btnsubmit;
    int year;
    int month;
    int dayOfMonth;

  EditText cid,did;
    DriverManager manager;
    SQLiteDatabase sq;
    ArrayList<String> arrayList;

    ArrayAdapter<String> adapter;
    SmileRating smileRating;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = new DriverManager(getContext());
        sq = manager.OpenDb();
        arrayList = new ArrayList<>();



    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.feedback,null,false);
        manager = new DriverManager(getContext());
        sq = manager.OpenDb();

        txtfeed = view.findViewById(R.id.txtfeed);
     did=view.findViewById(R.id.did);
     cid=view.findViewById(R.id.cid);

        txtexp = view.findViewById(R.id.txtexp);
        reaction = view.findViewById(R.id.reaction);
        arrayList=new ArrayList<>();
        smileRating = view.findViewById(R.id.smile_rating);
      smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {

                switch (smiley) {
                    case SmileRating.BAD:
                        reaction.setText("BAD");
                        break;
                    case SmileRating.GOOD:
                        reaction.setText("GOOd");
                        break;
                    case SmileRating.GREAT:
                        reaction.setText("GREAT");
                        break;
                    case SmileRating.OKAY:
                        reaction.setText("OKAY");
                        break;
                    case SmileRating.TERRIBLE:
                        reaction.setText("TERRIBLE");
                        break;
                }
            }
        });
        btnsubmit =view.findViewById(R.id.btnsubmit);


        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtfeed.length() == 0 || did.length() == 0 || cid.length() == 0 || reaction.length() == 0) {
                    btnsubmit.setError("Please enter all the details");
                } else {
                    String fid = txtfeed.getText().toString();
                    String drid = did.getText().toString();
                    String clid = cid.getText().toString();

                    String text = txtexp.getText().toString();
                    String smile = reaction.getText().toString();

                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DriverConstant.F_ID, fid);
                    contentValues.put(DriverConstant.DR_ID, drid);
                    contentValues.put(DriverConstant.CLI_ID, clid);

                    contentValues.put(DriverConstant.COL_TEXT, text);
                    contentValues.put(DriverConstant.COL_SMILEY, smile);
                    long row = sq.insert(DriverConstant.TAB_NAME, null, contentValues);
                    if (row > 0) {
                        Toast.makeText(getContext(), "Thank you for your suggestions!", Toast.LENGTH_SHORT).show();
                    }
                    txtfeed.setText(null);
                    did.setText(null);
                    cid.setText(null);
                    txtexp.setText(null);
                    reaction.setText(null);


                }
            }
        });
        return view;
    }


}

