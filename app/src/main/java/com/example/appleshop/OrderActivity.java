package com.example.appleshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    String [] addresses = {"akomanov88@gmail.com"};
    String subject = "Order from Apple Shop ";
    String emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle("Your Order");


        Intent recievedOrderIntent = getIntent();
        String userName = recievedOrderIntent.getStringExtra("userNameForIntent");
        String goodsName = recievedOrderIntent.getStringExtra("goodsName");
        int quantity = recievedOrderIntent.getIntExtra("quantity", 0);
        double price = recievedOrderIntent.getDoubleExtra("price", 0);
        double orderPrice = recievedOrderIntent.getDoubleExtra("orderPrice", 0);

        emailText = "Customer name: " + userName + "\n" +
                "Goods name: " + goodsName +  "\n" +
                "Quantity: " + quantity + "\n" +
                "Price per unit: " + price + "\n" +
                "Total price: " + orderPrice;

        TextView orderTextView = findViewById(R.id.orderTextView);
        orderTextView.setText(emailText);

    }

    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
