package sg.edu.rp.c346.id22024848.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import sg.edu.rp.c346.id22024848.mymovies.R;


public class MainActivity extends AppCompatActivity {
    EditText etSong, etGenre, etYear;
    Spinner spn;
    Button btnInsert, btnShow;
    String newSong, newGenre,newRating;
    int newYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etSong=findViewById(R.id.editTextSong);
        etGenre=findViewById(R.id.editTextGenre);
        etYear=findViewById(R.id.editTextYear);
        spn=findViewById(R.id.spinner);
        btnInsert=findViewById(R.id.buttonInsert);
        btnShow=findViewById(R.id.buttonShow);


        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);
                RadioButton selectedRadioButton;





                newSong=etSong.getText().toString();
                newGenre=etGenre.getText().toString();
                newYear=Integer.parseInt(etYear.getText().toString());
                newRating=spn.getSelectedItem().toString();


                // Insert a task
                db.insertMovie(newSong, newGenre, newYear, newRating);

            }
        });
        btnShow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                Intent intent= new Intent(MainActivity.this, MainActivity2.class);

                startActivity(intent);

            }
        });
    }
}