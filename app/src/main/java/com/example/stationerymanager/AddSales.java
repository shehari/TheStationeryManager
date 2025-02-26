package com.example.stationerymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddSales extends AppCompatActivity{

    public static StationarySalesDatabaseHelper myDb;

    EditText etDate;
    EditText sCode, sName, sPrice, sQty;
    Button addSalesBtn;
    Button listSalesBtn;
    DatePickerDialog.OnDateSetListener setListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales);
        getSupportActionBar().setTitle("Add Sales");
        myDb = new StationarySalesDatabaseHelper(this);


        etDate = findViewById(R.id.editText3);
        sCode = findViewById(R.id.SAfill10);
        sName = findViewById(R.id.SAfill20);
        sPrice = findViewById(R.id.SAfill30);
        sQty = findViewById(R.id.SAfill40);
        addSalesBtn = findViewById(R.id.SAbtn1);
        listSalesBtn = findViewById(R.id.SAbtn);

        AddData();
        viewAll();


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddSales.this,android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = day+"/"+month+"/"+year;
                etDate.setText(date);
            }
        } ;

    }

    public void AddData(){
        addSalesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDb.insertData(etDate.getText().toString(),
                                sCode.getText().toString(),
                                sName.getText().toString(),
                                sPrice.getText().toString(),
                                sQty.getText().toString());

                if (isInserted == true){
                    Toast.makeText(AddSales.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(AddSales.this, "Data not Inserted", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void viewAll(){
        listSalesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(AddSales.this, SalesListActivity.class));
            }

        });
    }


}
