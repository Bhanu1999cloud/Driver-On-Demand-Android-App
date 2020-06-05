package com.example.driverondemandapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class AdminLoginFragement extends Fragment {
    AdminLoginFragement adminLoginFragement;
    EditText adminid,adminpass;
    Button btnsubmit;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.adminloginfragment,null,false);

      adminid=view.findViewById(R.id.adminid);
      adminpass=view.findViewById(R.id.adminpass);
      btnsubmit=view.findViewById(R.id.btnsubmit);
      btnsubmit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String aid = "Bhanu";
              String apass ="Bhanu";
              if (adminid.length() == 0 && adminpass.length() == 0) {
                  adminid.setError("Please enter the Id");
                  adminpass.setError("Please enter the Password");
              } else if (adminid.length() == 0) {
                  adminid.setError("ID REQUIRED!!!");
              } else if (adminpass.length() == 0) {
                  adminpass.setError("PASSWORD REQUIRED!!!");
              } else {
                  String id = adminid.getText().toString();
                  String pass = adminpass.getText().toString();
                  if (aid.equals(id) && apass.equals(pass)) {
                      Toast.makeText(getContext(), "Successfully Logged In...", Toast.LENGTH_LONG).show();
                      Intent intent = new Intent(getContext(), MainActivity.class);
                      startActivity(intent);
                      adminid.setText(null);
                      adminpass.setText(null);
                  } else {
                      showDlg("INVALID USER ID OR PASSWORD", "DO YOU WANT TO CONTINUE?");
                      adminid.setText("");
                      adminpass.setText("");

                  }
              }

          }
      });
      return view;

    }
   public void showDlg(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle(title);
        builder.setMessage(message).setCancelable(false);
        builder.setIcon(R.drawable.alert);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
getActivity().getSupportFragmentManager().beginTransaction().remove(adminLoginFragement).commit();
            }
        });


        AlertDialog dialog=builder.create();
        dialog.show();
    }
}
