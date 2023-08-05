package sg.edu.rp.c346.id22024848.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class MainActivity3 extends AppCompatActivity {
    EditText etID, etMovie, etGenre, etYear;
    Spinner spn;
    Button btnUpdate, btnDelete, btnCancel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intentReceived=getIntent();
        Movie data=(Movie) intentReceived.getSerializableExtra("data");
        etID=findViewById(R.id.editTextID);

        etMovie=findViewById(R.id.editTextMovieTitle);
        etGenre=findViewById(R.id.editTextGenre2);
        etYear=findViewById(R.id.editTextYear);
        spn=findViewById(R.id.spinner2);
        btnUpdate=findViewById(R.id.buttonUpdate);
        btnDelete=findViewById(R.id.buttonDelete);
        btnCancel=findViewById(R.id.buttonCancel);

        SharedPreferences prefs=getSharedPreferences("data", MODE_PRIVATE);
        int id=prefs.getInt("id", 0);
        String title=prefs.getString("title", "errorTitle");
        String genre=prefs.getString("genre", "errorGenre");
        int year=prefs.getInt("year", 0);
        String rating=prefs.getString("rating", "errorRating");

        etID.setText(Integer.toString(id));
        etMovie.setText(title);
        etGenre.setText(genre);
        etYear.setText(Integer.toString(year));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newRating=spn.getSelectedItem().toString();

                String newMovie=etMovie.getText().toString();
                String newGenre=etGenre.getText().toString();
                int newYear=Integer.parseInt(etYear.getText().toString());




                DBHelper dbh = new DBHelper(MainActivity3.this);
                data.setMovie(newMovie, newGenre, newYear, newRating);
                dbh.updateMovie(data);
                dbh.close();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity3.this);
                dbh.deleteMovie(data.getId());
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent);

            }
        });

    }

}