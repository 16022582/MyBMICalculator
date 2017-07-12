package sg.edu.rp.c34.mybmicalculatorp08;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText weightText;
    EditText heightText;
    Button calculateButt;
    Button resetdataButt;
    TextView dateText;
    TextView bmiText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightText = (EditText)findViewById(R.id.editText);
        heightText = (EditText)findViewById(R.id.editText2);
        calculateButt = (Button)findViewById(R.id.button);
        resetdataButt = (Button)findViewById(R.id.button2);
        dateText = (TextView)findViewById(R.id.dateView);
        bmiText = (TextView)findViewById(R.id.BMIView);


        calculateButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float theWeight = Float.parseFloat(weightText.getText().toString());
                float theHeight = Float.parseFloat(heightText.getText().toString());

                float BMI = theWeight/(theHeight*theHeight);

                Calendar now = Calendar.getInstance();
                String datetime = now.get(Calendar.DAY_OF_MONTH)+ "/"+
                        (now.get(Calendar.MONTH)+1) + "/" +
                        now.get(Calendar.YEAR) + " " +
                        now.get(Calendar.HOUR_OF_DAY)+":"+
                        now.get(Calendar.MINUTE);

                dateText.setText(datetime);
                bmiText.setText(""+BMI);
                weightText.setText("");
                heightText.setText("");

            }
        });

        resetdataButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weightText.setText("");
                heightText.setText("");
                dateText.setText("");
                bmiText.setText("");
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        String theDate = dateText.getText().toString();
        String theText = bmiText.getText().toString();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putString("date", theDate);
        prefEdit.putString("bmi", theText);

        prefEdit.commit();


    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String theDate = prefs.getString("date", "");
        String theText = prefs.getString("bmi", "");


        dateText.setText(theDate);
        bmiText.setText(theText);

    }
}
