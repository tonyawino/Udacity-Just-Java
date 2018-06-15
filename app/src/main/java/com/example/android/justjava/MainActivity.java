package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display(quantity);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameText=findViewById(R.id.name);
        String name=String.valueOf(nameText.getText());
        CheckBox checkBoxWhipped =findViewById(R.id.checkWhipped);
        CheckBox checkBoxChocolate =findViewById(R.id.checkChocolate);
        displayPrice(createOrderSummary(name, checkBoxWhipped.isChecked(), checkBoxChocolate.isChecked(), calculatePrice()));
    }

    /**
     * This method is called when the increment button is clicked.
     */
    public void increment(View view) {
        quantity++;
        display(quantity);
    }

    /**
     * This method is called when the decrement button is clicked.
     */
    public void decrement(View view) {
        if (quantity>1) {
            quantity--;
            display(quantity);
        }
    }

    /**
     * This method is called when the whipped cream checkbox is clicked.
     */
    public void whippedCream(View view) {
        display(quantity);
    }

    /**
     * This method is called when the chocolate checkbox is clicked.
     */
    public void chocolate(View view) {
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
        String theprice=getString(R.string.price, calculatePrice());
        TextView priceTextView=findViewById(R.id.price);
        priceTextView.setText(theprice);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(String message) {
        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /**
     * This method calculates the price of the order.
     */
    private int calculatePrice(){
        CheckBox checkBoxWhipped =findViewById(R.id.checkWhipped);
        CheckBox checkBoxChocolate =findViewById(R.id.checkChocolate);
        int price = 5;
        if (checkBoxWhipped.isChecked())
            price+=1;
        if (checkBoxChocolate.isChecked())
            price+=2;
        price*=quantity;
        return price;
    }

    /**
     * Creates summary of order
     *
     *
     */
    private String createOrderSummary(String name, boolean whipped, boolean chocolate, int price) {
        return "Name:"+name+"\nAdd whipped cream?"+whipped+"\nAdd chocolate?"+chocolate+"\nQuantity: 3\nTotal: "+price+"\nThank you!";
    }
}