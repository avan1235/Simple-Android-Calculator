package ml.physicsis.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    String dataString = "";
    String resultString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonPlus = findViewById(R.id.buttonPlus);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonMultiply = findViewById(R.id.buttonMulti);
        Button buttonDivide = findViewById(R.id.buttonDiv);
        Button buttonEqual = findViewById(R.id.buttonEqual);
        Button buttonComma = findViewById(R.id.buttonComma);
        Button buttonDelete = findViewById(R.id.buttonDelete);
        Button buttonClearAll = findViewById(R.id.buttonCA);
        Button buttonLeftBra = findViewById(R.id.buttonLeftBracket);
        Button buttonRightBra = findViewById(R.id.buttonRightBracket);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
        buttonComma.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonClearAll.setOnClickListener(this);
        buttonLeftBra.setOnClickListener(this);
        buttonRightBra.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewID = v.getId();

        if (viewID == R.id.button0){
            if (dataString.length() > 0){
                dataString += "0";
            }
        }
        else if (viewID == R.id.button1){
            dataString += "1";
        }
        else if (viewID == R.id.button2){
            dataString += "2";
        }
        else if (viewID == R.id.button3){
            dataString += "3";
        }
        else if (viewID == R.id.button4){
            dataString += "4";
        }
        else if (viewID == R.id.button5){
            dataString += "5";
        }
        else if (viewID == R.id.button6){
            dataString += "6";
        }
        else if (viewID == R.id.button7){
            dataString += "7";
        }
        else if (viewID == R.id.button8){
            dataString += "8";
        }
        else if (viewID == R.id.button9){
            dataString += "9";
        }
        else if (viewID == R.id.buttonPlus){
            if (dataString.length() > 0) {
                dataString += "+";
            }
        }
        else if (viewID == R.id.buttonMinus){
            if (dataString.length() > 0) {
                dataString += "-";
            }
        }
        else if (viewID == R.id.buttonMulti){
            if (dataString.length() > 0) {
                dataString += "×";
            }
        }
        else if (viewID == R.id.buttonDiv){
            if (dataString.length() > 0) {
                dataString += "÷";
            }
        }
        else if (viewID == R.id.buttonEqual){
            if(!resultString.equals("")){
                dataString = resultString;
                resultString = "";
            }
        }
        else if (viewID == R.id.buttonComma){
            if (dataString.length() > 0) {
                dataString += ".";
            }
        }
        else if (viewID == R.id.buttonDelete){
            if (dataString.length() > 0){
                dataString = dataString.substring(0, dataString.length()-1);
            }
            else {
                dataString = "";
            }
        }
        else if (viewID == R.id.buttonCA){
            dataString = "";
            resultString = "";
        }
        else if (viewID == R.id.buttonLeftBracket){
            dataString += "(";
        }
        else if (viewID == R.id.buttonRightBracket){
            if (dataString.length() > 0) {
                dataString += ")";
            }
        }

        final TextView dataTextView = findViewById(R.id.textViewData);
        final TextView resultTextView = findViewById(R.id.textViewResult);

        dataTextView.setText(dataString);
        dataTextView.setMovementMethod(new ScrollingMovementMethod());

        if(dataString.length() > 0 && SpecialChar.foundSpecialChar(dataString)){
            StringAnalyzer stringAnalyzer = new StringAnalyzer(dataString);
            resultString = stringAnalyzer.getResultWithoutQuotes();
        }

        resultTextView.setText(resultString);


    }
}