package com.example.wafill;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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

import java.util.ArrayList;

public class ChatFragment extends Fragment {
    int totalPrice;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final String userId = currentFirebaseUser.getUid();
        final ArrayList<OrderItem> orderItemsList=getArrayList();

        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Orders").child(currentFirebaseUser.getUid()).child("123454").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dsd:dataSnapshot.getChildren()){
                    Toast.makeText(getContext(), dsd.getValue().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final ListView orderListView=(ListView) view.findViewById(R.id.orderListView);
        orderListView.setAdapter(new OrderListAdapter(getContext(),orderItemsList));
        return view;
    }
    private ArrayList getArrayList(){
        ArrayList<OrderItem> orderItems=new ArrayList<>();
        OrderItem orderItem=new OrderItem(4,20,2);
        OrderItem orderItem1=new OrderItem(5,21,2);
        OrderItem orderItem2=new OrderItem(6,22,2);
        OrderItem orderItem3=new OrderItem(7,24,2);
        OrderItem orderItem4=new OrderItem(7,24,2);
        OrderItem orderItem5=new OrderItem(7,24,2);
        orderItems.add(orderItem);
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);
        orderItems.add(orderItem3);
        orderItems.add(orderItem4);
        orderItems.add(orderItem5);
        return orderItems;
    }


}

