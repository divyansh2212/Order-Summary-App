package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int numberOfCoffees =1;
    public void increase(View view)
    {
        numberOfCoffees++;
        display(numberOfCoffees);
    }
    public void decrease(View view)
    {
        numberOfCoffees--;
        if(numberOfCoffees<=0)
        {
            numberOfCoffees=1;
            Toast.makeText(this, "Minimum input is 1!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            display(numberOfCoffees);
        }
    }
    public void submitOrder(View view)
    {
        display(numberOfCoffees);
       int price =  calculatePrice(numberOfCoffees, 10);
        createOrderSummary(price);
    }
    private void display(int number)
    {
        TextView quantity = findViewById(R.id.quantity);
        quantity.setText(""+number);
    }

    private int calculatePrice(int quantity, int pricePerCup) {
        return quantity * pricePerCup;
    }
    private void createOrderSummary(int price)
    {
        TextView priceTextView = findViewById(R.id.price);
        priceTextView.setText("Name: Divyansh Mittal\n"+"Quantity: "+numberOfCoffees+"\n"+NumberFormat.getCurrencyInstance().format((long) price) + "\nThank You!");
    }
}