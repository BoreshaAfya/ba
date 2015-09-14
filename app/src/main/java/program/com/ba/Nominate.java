package program.com.ba;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class Nominate extends Activity {
    EditText NomineeName, NomineeOccupation, NomineeRegNo, NomineeWorkplace, NomineeCounty;
    Context context=this;
    NomineeDBHelper nomineeDBHelper;
    SQLiteDatabase sqLiteDatabase;
    private int gravity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nominate);

        NomineeName=(EditText)findViewById(R.id.editText);
        NomineeOccupation=(EditText)findViewById(R.id.editText5);
        NomineeRegNo=(EditText)findViewById(R.id.editText2);
        NomineeWorkplace=(EditText)findViewById(R.id.editText3);
        NomineeCounty=(EditText)findViewById(R.id.editText7);



        Button button1=(Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Thank you for nominating a candidate, you can now vote! ", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Nominate.this, Vote.class));

            }
        });


        Spinner staticSpinner = (Spinner) findViewById(R.id.county_spinner);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this, R.array.counties_list,android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);
    }
        public void addContact(View view)
        {
            String name=NomineeName.getText().toString();
            String occupation=NomineeOccupation.getText().toString();
            String regno=NomineeRegNo.getText().toString();
            String workplace=NomineeWorkplace.getText().toString();
            String county=  NomineeCounty.getText().toString();
            nomineeDBHelper =new NomineeDBHelper(context);
            sqLiteDatabase=nomineeDBHelper.getWritableDatabase();
            nomineeDBHelper.addInformation(name, occupation, regno, workplace, county, sqLiteDatabase);
            nomineeDBHelper.close();

        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nominate, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
