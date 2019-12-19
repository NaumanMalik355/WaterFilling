package com.example.wafill;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class addproduct extends AppCompatActivity {
    ImageView showImg;
    Button chooseButton, uploadImage, btnCreateProduct;
    EditText productName, bottleSize, createdAt, price;
    Uri imageUri;
    String downloadUrl = "";
    private static final int pickImage = 100;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    DatabaseReference databaseReference;
    Calendar calendar;
    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);

        productName = findViewById(R.id.txtProduct);
        bottleSize = findViewById(R.id.txtBottleSize);
        createdAt = findViewById(R.id.txtCreatedAt);
        price = findViewById(R.id.txtPrice);
        showImg = findViewById(R.id.uploadImg);
        chooseButton = findViewById(R.id.uploadButton);
        uploadImage = findViewById(R.id.uploadImage);
        btnCreateProduct = findViewById(R.id.createProductId);
        uploadImage.setEnabled(false);
        btnCreateProduct.setEnabled(false);
        final Date da=new Date();

        createdAt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar=Calendar.getInstance();
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                int month=calendar.get(Calendar.MONTH);
                int year=calendar.get(Calendar.YEAR);
                datePickerDialog=new DatePickerDialog(addproduct.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        createdAt.setText(mDay+"/"+(mMonth+1)+"/"+mYear);
                    }
                },day,month,year);
                datePickerDialog.show();
            }
        });

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("Products").child(currentFirebaseUser.getUid());

        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
                uploadImage.setEnabled(true);
            }
        });
    }

    public void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, pickImage);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data.getData();
            showImg.setImageURI(imageUri);
        }
    }

    public void uploadingProduct(View view) {
        if (imageUri != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
//            progressDialog.setTitle("Creating new Product");
            progressDialog.setCancelable(false);
            progressDialog.show();

            final StorageReference reference = storageReference.child("images/" + UUID.randomUUID().toString());
            reference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl = uri.toString();
                                    //Toast.makeText(addproduct.this, "Created Successfully"+uri.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }
        btnCreateProduct.setEnabled(true);
    }

    public void createProduct(View view) {
        String productNameInput = productName.getText().toString().trim();
        int bottleSizeInput = Integer.parseInt(bottleSize.getText().toString().trim());
        String createdAtInput = createdAt.getText().toString().trim();
        int priceInput = Integer.parseInt(price.getText().toString().trim());
        String id = databaseReference.push().getKey();

        Products products = new Products();
        products.bottleSize=bottleSizeInput;
        products.price=priceInput;
        products.productId=id;
        products.productName=productNameInput;
        products.createdAt=createdAtInput;
        products.imgURL=downloadUrl;

        databaseReference.child(id).setValue(products);
        Toast.makeText(this, "New Product created", Toast.LENGTH_SHORT).show();
        productName.setText("");
        bottleSize.setText("");
        createdAt.setText("");
        price.setText("");
    }
}
