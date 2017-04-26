package com.viravtech.discountcalculator;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    TextView  totalPriceTextView, salesTaxTextView, finalPricetextView, calculationsTextView;
    EditText discountTextView;
//    SeekBar discountSeekBar;
    Button calculateButton,resetButton,tenpercent,fifteenpercent,twentypercent,twentyfivepercent,fiftypercent,sixtypercent;
    String currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalPriceTextView = (TextView)findViewById(R.id.totalPriceTextField);
        salesTaxTextView = (TextView)findViewById(R.id.salesTaxTextVIew);
        calculationsTextView = (TextView)findViewById(R.id.calculationsText);
        finalPricetextView = (TextView)findViewById(R.id.finalPrice);

//        discountSeekBar = (SeekBar)findViewById(R.id.discountBar);

        tenpercent = (Button)findViewById(R.id.tenpercentbutton);
        tenpercent.setOnClickListener(this);

        fifteenpercent = (Button)findViewById(R.id.fifteenpercentbutton);
        fifteenpercent.setOnClickListener(this);

        twentypercent = (Button)findViewById(R.id.twentypercentbutton);
        twentypercent.setOnClickListener(this);

        twentyfivepercent = (Button)findViewById(R.id.twentyfivepercentbutton);
        twentyfivepercent.setOnClickListener(this);

        fiftypercent = (Button)findViewById(R.id.fiftypercentbutton);
        fiftypercent.setOnClickListener(this);

        sixtypercent = (Button)findViewById(R.id.sixtypercentbutton);
        sixtypercent.setOnClickListener(this);

        calculateButton = (Button)findViewById(R.id.calculatebutton);
        calculateButton.setOnClickListener(this);

        resetButton = (Button)findViewById(R.id.resetButton);
        resetButton.setOnClickListener(this);

        discountTextView = (EditText)findViewById(R.id.discountTextView);
//        discountTextView.addTextChangedListener(new TextWatcher() {
//
//            public void afterTextChanged(Editable s) {
//
//            }
//
//            public void beforeTextChanged(CharSequence s, int start,
//                                          int count, int after) {
//            }

//            public void onTextChanged(CharSequence s, int start,
//                                      int before, int count) {
//                discountTextView.setSelection(s.toString().length());
//                if (s.toString().length()>0)
//                    if (Integer.parseInt(s.toString()) >= 0 && Integer.parseInt(s.toString())<=100 ){
////                        discountSeekBar.setProgress(Integer.parseInt(s.toString()));
//                    }else{
//                        discountTextView.setText(String.valueOf(100));
////                        discountSeekBar.setProgress(100);
//                    }
//                else
////                    discountSeekBar.setProgress(0);
//            }
//        });

        totalPriceTextView = (TextView)findViewById(R.id.totalPriceTextField);
        salesTaxTextView = (TextView)findViewById(R.id.salesTaxTextVIew);
        calculationsTextView = (TextView)findViewById(R.id.calculationsText);
        finalPricetextView = (TextView)findViewById(R.id.finalPrice);

////        discountSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
////            int progressChanged = 0;
//
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
//                discountTextView.setText(String.valueOf(progress));
//            }
//
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                // TODO Auto-generated method stub
//            }
//
//            public void onStopTrackingTouch(SeekBar seekBar) {
////                discountTextView.setText(String.valueOf(discountSeekBar.getProgress()));
//                //Toast.makeText(this,"The dispalyed price should be greater than 0.",Toast.LENGTH_LONG).show();
//            }
//        });

        calculateButton = (Button)findViewById(R.id.calculatebutton);
        calculateButton.setOnClickListener(this);

        AdView mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest request = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        AdRequest Request = new AdRequest.Builder().build();
        mAdView.loadAd(Request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == findViewById(R.id.calculatebutton) ){
            String totalPriceInString = totalPriceTextView.getText().toString();
            String discountPriceInString = discountTextView.getText().toString();
            if (totalPriceInString.length() > 0){
                if (Float.parseFloat(totalPriceInString) > 0) {
                    if (discountPriceInString.length() > 0){
                        if (Float.parseFloat(discountPriceInString) >= 0 && Float.parseFloat(discountPriceInString) <= 100){
                            calculateTotalPrice();
                        }
                    }else{
                        Toast.makeText(this,"The discount should should be between 0 & 100.",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(this,"The displayed price should be greater than 0.",Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(this,"The displayed price should be entered in order to calculate your final price ",Toast.LENGTH_LONG).show();
            }
        }else if (v == findViewById(R.id.resetButton)){
            totalPriceTextView.setText("");
            discountTextView.setText("");
//            discountSeekBar.setProgress(0);
            salesTaxTextView.setText("");
            this.calculateTotalPrice();
        }else if(v == findViewById(R.id.tenpercentbutton)){
            discountTextView.setText("10");
        }
        else if(v == findViewById(R.id.fifteenpercentbutton)){
            discountTextView.setText("15");
        }
        else if(v == findViewById(R.id.twentypercentbutton)){
            discountTextView.setText("20");
        }
        else if(v == findViewById(R.id.twentyfivepercentbutton)){
            discountTextView.setText("25");
        }
        else if(v == findViewById(R.id.fiftypercentbutton)){
            discountTextView.setText("50");
        }
        else if(v == findViewById(R.id.sixtypercentbutton)){
            discountTextView.setText("60");
        }
    }

    private void calculateTotalPrice() {
        String totalPriceInString = totalPriceTextView.getText().toString();
        String discountInString = discountTextView.getText().toString();
        String taxInString = salesTaxTextView.getText().toString();
        float discount = 0;
        if (discountInString.length()>0){
            discount = Float.parseFloat(discountInString);
        }
        float tax = 0;
        if (taxInString.length() > 0){
            tax = Float.parseFloat(taxInString);
        }
        float totalPrice = 0;
        float roundedValue = 0;
        if (totalPriceInString.length()> 0){
            totalPrice = Float.parseFloat(totalPriceInString);
        }
        float discountedtotal = totalPrice - (totalPrice*(discount/100));
        float salesTaxAdded = discountedtotal + (discountedtotal*(tax/100));

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        currency = prefs.getString("example_list", "USD");
        Log.i("s : ",currency);

        calculationsTextView.setText("Displayed Price :" + String.format("%.2f",totalPrice) + " - Discounts :" + String.format("%.2f", totalPrice * (discount / 100)) + " + Tax :" + String.format("%.2f",(discountedtotal * (tax / 100))));
        finalPricetextView.setText(currency + " " + String.format("%.2f", salesTaxAdded));
    }
}
