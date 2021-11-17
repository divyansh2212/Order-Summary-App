package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
        EditText name= findViewById(R.id.name);
        String value = name.getText().toString();
        numberOfCoffees++;
        display(numberOfCoffees);
        int price =  calculatePrice(numberOfCoffees, 10);
        createOrderSummary(price, value);
    }
    public void decrease(View view)
    {
        EditText name= findViewById(R.id.name);
        String value = name.getText().toString();
        numberOfCoffees--;
        if(numberOfCoffees<=0)
        {
            numberOfCoffees=1;
            Toast.makeText(this, "Minimum input is 1!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            display(numberOfCoffees);
            int price =  calculatePrice(numberOfCoffees, 10);
            createOrderSummary(price, value);
        }
    }
    String summary;
    public void submitOrder(View view)
    {
        EditText name= findViewById(R.id.name);
        String value = name.getText().toString();
        display(numberOfCoffees);
       int price =  calculatePrice(numberOfCoffees, 10);
        createOrderSummary(price, value);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for "+ value);
        intent.putExtra(Intent.EXTRA_TEXT, summary);
        startActivity(intent);
    }
    private void display(int number)
    {
        TextView quantity = findViewById(R.id.quantity);
        quantity.setText(""+number);
    }

    private int calculatePrice(int quantity, int pricePerCup)
    {
        CheckBox cream = findViewById(R.id.cream);
        CheckBox chocolate = findViewById(R.id.chocolate);
        if(cream.isChecked() && chocolate.isChecked()) return ((quantity * pricePerCup) + (20*quantity));
        else if(cream.isChecked() || chocolate.isChecked()) return ((quantity * pricePerCup) + (5*quantity));
        else return quantity*pricePerCup;
    }
    private void createOrderSummary(int price, String name)
    {
        CheckBox cream = findViewById(R.id.cream); CheckBox chocolate = findViewById(R.id.chocolate);
        TextView priceTextView = findViewById(R.id.order_bill);
        summary = "Name: "+name+"\n"+"Quantity: "+numberOfCoffees+"\n"+NumberFormat.getCurrencyInstance().format((long) price)+"\n";
        if (cream.isChecked() && chocolate.isChecked()) summary += "Whipped Cream and Chocolate Topping included!\nThank You!";
        else if (cream.isChecked()) summary += "Whipped Cream included!\nThank You!";
        else if (chocolate.isChecked())  summary += "Chocolate Topping included!\nThank You!";
        else  summary += "\nThank You!";
        priceTextView.setText(summary);

    }
}