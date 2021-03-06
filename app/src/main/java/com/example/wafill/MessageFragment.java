package com.example.wafill;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends Fragment {
    FloatingActionButton floatingActionButton;
    String userId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_message, container, false);
        final ArrayList<Products> productsList = new ArrayList<>();

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userId = currentFirebaseUser.getUid();
        Toast.makeText(getContext(), userId, Toast.LENGTH_SHORT).show();

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.ProductListRecycleriIew);
        final ProductListAdapter productListAdapter = new ProductListAdapter(productsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(productListAdapter);

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading. please wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

        floatingActionButton = view.findViewById(R.id.addImgId);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), addproduct.class);
                startActivity(intent);
            }
        });

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Products").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    progressDialog.show();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Products updated = new Products();
                        updated.productName = ds.getValue(Products.class).productName;
                        updated.createdAt = ds.getValue(Products.class).createdAt;
                        updated.bottleSize = ds.getValue(Products.class).bottleSize;
                        updated.price = ds.getValue(Products.class).price;
                        productsList.add(updated);
//                        Toast.makeText(getContext(), ds.getValue(Products.class).imgURL, Toast.LENGTH_SHORT).show();
                    }
                    productListAdapter.notifyDataSetChanged();
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "Try to resolve this error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.toException());
            }
        });
        return view;
    }
//  Moving from one Fragment to new Fragment
//    public void clickButton(View view){
//
//        Intent intent=new Intent(getContext(),addproduct.class);
//        getActivity().startActivity(intent);

//        ((Activity)getActivity()).overridePendingTransition(0,0);
//        ProfileFragment profileFragment=new ProfileFragment();
//        Bundle bundle=new Bundle();
//        bundle.putString("hellokey","hello from fragment");
//
//        profileFragment.setArguments(bundle);
//        getChildFragmentManager().beginTransaction().replace(R.id.fragment_container, new MessageFragment()).commit();
//    }

}
