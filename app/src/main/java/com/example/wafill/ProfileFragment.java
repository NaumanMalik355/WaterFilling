package com.example.wafill;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {
    TextView txtShowUserName,showEmailAddress,ShowPhoneNumber,ShowUserAddress;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        txtShowUserName=view.findViewById(R.id.showUsername);
        showEmailAddress=view.findViewById(R.id.showEmailAddress);
        ShowPhoneNumber=view.findViewById(R.id.showNumber);
        ShowUserAddress=view.findViewById(R.id.showAddress);

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.setMessage("Loading. please wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final String userId = currentFirebaseUser.getUid();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Users");
        databaseReference.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    progressDialog.dismiss();
                    Users users = dataSnapshot.getValue(Users.class);
                    txtShowUserName.setText("Name: "+users.username);
                    showEmailAddress.setText("Email: "+users.emailAddress);
                    ShowPhoneNumber.setText("Phone: "+users.number);
                    ShowUserAddress.setText("Address: "+users.address);
                } catch (Exception ex) {
                    Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Database error: " + databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
//        Bundle bundle=new Bundle();
//        bundle.getString("hellorKey");
//        Toast.makeText(getContext(),"rwn"+bundle,Toast.LENGTH_SHORT).show();

return view;

    }
}
