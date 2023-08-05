package sg.edu.rp.c346.id22024848.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    ListView lw;
    Button btn;
    CustomAdapter aaSong;
    ArrayList<Movie> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);





    }
    @Override
    protected void onResume() {
        super.onResume();
        lw=findViewById(R.id.listView);
        btn=findViewById(R.id.buttonFive);

        Intent intentReceived=getIntent();
        DBHelper db = new DBHelper(MainActivity2.this);
        data=db.getMovie();
        db.close();
        aaSong=new CustomAdapter(this, R.layout.row, data);
        lw.setAdapter(aaSong);

        lw.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)  {
                Movie clicked=data.get(position);
                SharedPreferences prefs=getSharedPreferences("data", MODE_PRIVATE);
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putInt("id", clicked.getId());
                prefEdit.putString("title", clicked.getTitle());
                prefEdit.putString("genre", clicked.getGenre());
                prefEdit.putInt("year", clicked.getYear());
                prefEdit.putString("rating", clicked.getRating());
                prefEdit.commit();
                Intent intent= new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("data", clicked);

                startActivity(intent);





            }

        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity2.this);
                data.clear();

                String filterText = "PG13";

                data.addAll(dbh.getMovie(filterText));



                aaSong.notifyDataSetChanged();
            }
        });


    }


}