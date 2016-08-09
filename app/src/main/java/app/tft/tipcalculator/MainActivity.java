package app.tft.tipcalculator;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {


    private  static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();
    private double billAmount = 0.0; // this is the billed amount entered by the user
    private double percent = 0.15; // this is the initial tip percentage
    private TextView amountTextView; // this shows formatted bill amount
    private TextView percentTextView; // this shows the tip percentage
    private TextView totalTextView; // this shows calculated total bill amount
    private TextView tipTextView; // shows the calculated tips


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // this calls superclass's version
        setContentView(R.layout.activity_main); //inflate the GUI @D.W

        // this gets reference to the programmatically manipulated TextViews

        amountTextView = (TextView) findViewById(R.id.amountTextView);
        percentTextView = (TextView) findViewById(R.id.percentTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        tipTextView = (TextView) findViewById(R.id.tipTextView);
        tipTextView.setText(currencyFormat.format(0)); // set text to 0
        totalTextView.setText(currencyFormat.format(0)); // set this text to 0


        //set amountEits Text Watcher
        EditText amountEditText = (EditText) findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);

        SeekBar percentSeekBar = (SeekBar) findViewById(R.id.percentSeekBar);
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);






    }

    // this will calculate and display tip and total amounts
    private void calculate() {
        percentTextView.setText(percentFormat.format(percent));

        //this will calculate the tip and total
        double tip = billAmount * percent;
        double total = billAmount + tip;


        tipTextView.setText(currencyFormat.format(tip));
        totalTextView.setText(currencyFormat.format(total));


    }

    private final SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            percent = progress / 100.0;
            calculate(); // calculate and display tip and total.


        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private final TextWatcher amountEditTextWatcher = new TextWatcher() {


        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int after) {
            try { //get bill amount and display curreny formatted value
                billAmount = Double.parseDouble(charSequence.toString()) / 100.0;
                amountTextView.setText(currencyFormat.format(billAmount));
            }
            catch (NumberFormatException e ) {
                amountTextView.setText("");
                billAmount = 0.00;

            }

            calculate();

        }
        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };







}
