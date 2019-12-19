package com.example.wafill;

import android.app.Activity;
import android.content.Intent;
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

public class MessageFragment extends Fragment {
    FloatingActionButton imgView;
    String userId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userId = currentFirebaseUser.getUid();


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Products").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){

                        Products products=ds.getValue(Products.class);
                        System.out.println("jkhjkh "+products.getProductName());
//                        products.setProductName(dds.child("productName").getValue(Products.class).getProductName());
//                        Toast.makeText(getContext(), products., Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    for(DataSnapshot dataSnap : ds.getChildren()){
//                        Products getFirebaseData = new Products();
//                        getFirebaseData.setProductName(ds.child(userId).getValue(Products.class).getProductName());
//                        Toast.makeText(getContext(), getFirebaseData.getProductName(), Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


        MyListData[] myListData = new MyListData[]{
                new MyListData("Product Name", "Subtitle1", "18/12/2019", R.drawable.images1),
                new MyListData("Product Name 1", "Subtitle1", "18/12/2019", R.drawable.images2),
                new MyListData("Product Name 2", "Subtitle1", "18/12/2019", R.drawable.images3),
                new MyListData("Product Name 3", "Subtitle1", "18/12/2019", R.drawable.images1),
                new MyListData("Product Name 4", "Subtitle1", "18/12/2019", R.drawable.images2),
                new MyListData("Product Name 5", "Subtitle1", "18/12/2019", R.drawable.images3),
                new MyListData("Product Name 6", "Subtitle1", "18/12/2019", R.drawable.images1),
                new MyListData("Product Name 7", "Subtitle1", "18/12/2019", R.drawable.images2),
                new MyListData("Product Name 8", "Subtitle1", "18/12/2019", R.drawable.images3),
                new MyListData("Product Name 9", "Subtitle1", "18/12/2019", R.drawable.images1),
                new MyListData("Product Name 10", "Subtitle1", "18/12/2019", R.drawable.images2),
                new MyListData("Product Name 11", "Subtitle1", "18/12/2019", R.drawable.images3),
        };

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.ProductListRecycleriIew);
        ProductListAdapter productListAdapter = new ProductListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(productListAdapter);


        imgView = view.findViewById(R.id.addImgId);
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), addproduct.class);
                startActivity(intent);
            }
        });
        return view;
    }

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
