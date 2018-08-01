package sg.edu.rp.c346.reservation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Toast;

import java.nio.charset.MalformedInputException;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etTelephone;
    EditText etSize;
    EditText etDate;
    EditText etTime;
    CheckBox checkBox;

    Button btReserve;
    Button btReset;

    // for current date
    int theHour, theMin, theYear,theMonth,theDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etTelephone = findViewById(R.id.editTextTelephone);
        etSize = findViewById(R.id.editTextSize);
        etDate = findViewById(R.id.editTextDate);
        etTime = findViewById(R.id.editTextTime);
        checkBox = findViewById(R.id.checkBox);

        btReserve = findViewById(R.id.buttonReserve);
        btReset = findViewById(R.id.buttonReset);


        //Get Current date and time 55 - 60
        final Calendar now = Calendar.getInstance();
        theYear = now.get(Calendar.YEAR);
        theDay = now.get(Calendar.DAY_OF_MONTH);
        theMonth = now.get(Calendar.MONTH);
        theMin = now.get(Calendar.MINUTE);
        theHour = now.get(Calendar.HOUR);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        etDate.setText("Date: " + dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                        theYear = year;
                        theMonth = monthOfYear;
                        theDay = dayOfMonth;
                    }
                };
                //Create the Date Picker Dialog
                // if theYear theMonth theDay not initialize, use the fields in onDateSet()
                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this,
                        myDateListener,theYear,theMonth,theDay);
                myDateDialog.show();
            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create the Listener to set date
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        etTime.setText("Time: " + hourOfDay + ":" + minute);
                        theHour = hourOfDay;
                        theMin = minute;

                    }
                };
                //Create the Date Picker Dialog
                // if theYear theMonth theDay not initialize, use the fields in onDateSet() line 186
                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this,
                        myTimeListener,theHour,theMin,true);
                myTimeDialog.show();
            }
        });

        btReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String getName = etName.getText().toString().trim();
                String getTel = etTelephone.getText().toString().trim();
                String getSize = etSize.getText().toString().trim();
                String getDate = etDate.getText().toString().trim();
                String getTime = etTime.getText().toString().trim();
//                boolean idSmoking =
//
//                SharedPreferences prefs =
//                        PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
//                SharedPreferences.Editor prefEdit = prefs.edit();
//
//                prefEdit.putString("Name",getName);
//                prefEdit.putString("Size",getSize);
//                prefEdit.putString("Date",getDate);
//                prefEdit.putString("Time",getTime);

//                prefEdit.commit();

//                prefEdit.putBoolean(idSmoking,true);
                if (getName.length() != 0 || getTel.length() != 0 || getSize.length() != 0) {

                    //process the data
                    String isSmoke = "";
                    if (checkBox.isChecked()) {
                        isSmoke = "smoking";

                    } else {
                        isSmoke = "non-smoking";
                    }

                    AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                    myBuilder.setTitle("Confirm Your Order");
                    String msg ="New Reservation\n"+
                            "Name: "+getName+"\n"+
                            "Smoking: "+ isSmoke+"\n"+
                            "Size: " + getSize + "\n" +
                            "Date: " + getDate + "\n" +
                            "Time: " + getTime;
                    ;
                    myBuilder.setMessage(msg);
                    myBuilder.setCancelable(false);

                    // Configure the 'positive' button
                    myBuilder.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            Toast.makeText(MainActivity.this, "Your order has been confirmed!",
                                    Toast.LENGTH_LONG).show();

                        }
                    });

                    myBuilder.setNeutralButton("Cancel", null);
                    AlertDialog myDialog = myBuilder.create();
                    myDialog.show();


                } else {
                    Toast.makeText(MainActivity.this, "Please ensure all info are filled up!", Toast.LENGTH_LONG).show();
                }

            }
        });


        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etName.setText(null);
                etTelephone.setText(null);
                etSize.setText(null);
                checkBox.setChecked(false);
                etDate.setText(null);
                etTime.setText(null);

            }

    });
}
}
