package edu.birzeit.fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final  String NAME = "NAME";
    public static final  String HEIGHT = "HEIGHT";
    public static final  String WEIGHT = "WEIGHT";
    public static final  String BMI = "BMI";
    public static final  String GENDER = "GENDER";
    public static final  String FLAG = "FLAG";

    private Spinner spinner;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private TextView textView;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        populateSpinner();
        editText1 = findViewById(R.id.name);
        editText2 = findViewById(R.id.height);
        editText3 = findViewById(R.id.weight);
        textView = findViewById(R.id.bmi);
        setupSharedPrefs();
        checkPrefs();
        setupViews();

    }
    private void checkPrefs(){
        boolean flag = prefs.getBoolean(FLAG, false);

        if (flag){
            String name = prefs.getString(NAME, "");
            String height = prefs.getString(HEIGHT, "");
            String weight = prefs.getString(WEIGHT, "");
            String gender = prefs.getString(GENDER, "");
            String bmi = prefs.getString(BMI, "");
            editText1.setText(name);
            editText2.setText(height);
            editText3.setText(weight);
            textView.setText(bmi);

          spinner.setPrompt(gender);
        }
    }
    private void setupSharedPrefs(){
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }
    private void setupViews(){
        spinner = findViewById(R.id.spinner);
        editText1 = findViewById(R.id.name);
        editText2 = findViewById(R.id.height);
        editText3 = findViewById(R.id.weight);
        textView = findViewById(R.id.bmi);
    }
    private void populateSpinner() {
        PlayerFactory factory = new PlayerFactory();
        IFitnessData objPlay = factory.getModel();

        String[] gender = objPlay.getGenders();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, gender);
        spinner.setAdapter(adapter);}

    public void btnGetBMIOnClick (View view) {

        PlayerFactory factory = new PlayerFactory();
        IFitnessData objBook = factory.getModel();

        Player target = new Player();

        target.setGender(spinner.getSelectedItem().toString());
        target.setHeight(Double.valueOf(editText2.getText().toString()));
        target.setWeight(Double.valueOf(editText3.getText().toString()));
        target.setName(editText1.getText().toString());

        int b=0;
        b = (int) ((target.getWeight() ) / (target.getHeight() * target.getHeight()));
        textView.setText(Integer.toString(b));
    }
    public void btnSaveOnClick (View view) {

        PlayerFactory factory = new PlayerFactory();
        IFitnessData objBook = factory.getModel();

        Player target = new Player();

        target.setGender(spinner.getSelectedItem().toString());
        target.setHeight(Double.valueOf(editText2.getText().toString()));
        target.setWeight(Double.valueOf(editText3.getText().toString()));
        target.setName(editText1.getText().toString());
        target.setBMI(textView.getText().toString());

        editor.putString(NAME,target.getName() );
        editor.putBoolean(FLAG, true);
        editor.putString(HEIGHT, Double.toString(target.getHeight() ));
        editor.putString(WEIGHT, Double.toString(target.getWeight() ));
        editor.putString(GENDER,target.getGender() );
        editor.putString(BMI, target.getBMI());
        editor.commit();


    }
    public void btnTimerOnClick (View view) {
        String msg = "yes it's working";
        Intent intent = new Intent(this, Timer.class);
        intent.putExtra("data", msg);
        startActivity(intent);


    }
    }