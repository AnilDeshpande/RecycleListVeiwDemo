package codetutor.com.recyclelistveiwdemo;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    AppUtility appUtility;

    ListAdapterWithRecycleView listAdapterWithRecycleView;

    private EditText editTextFirstName, editTextLastName;
    private Spinner spinnerNationality;
    private RadioGroup radioGroup;
    private Button buttonAdd;

    List<Person> people;

    String firstName, lastName, nationality;
    Person.GENDER gender;

    LinearLayoutManager linearLayoutManager;
    GridLayoutManager gridLayoutManager;
    StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPersonInputForm();

        recyclerView =(RecyclerView) findViewById(R.id.recycleListView);

        appUtility=AppUtility.getAppUtility(getApplicationContext());
        people = appUtility.getPeople();

        listAdapterWithRecycleView=new ListAdapterWithRecycleView(this,people);

        linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        /*gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position%3==0?2:1);
            }
        });*/

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL);


        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listAdapterWithRecycleView);
    }

    private void initPersonInputForm(){
        editTextFirstName = (EditText)findViewById(R.id.editTextFirstName);
        editTextLastName = (EditText)findViewById(R.id.editTextLastName);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroupGender);
        spinnerNationality = (Spinner)findViewById(R.id.spinnerNationality);
        buttonAdd = (Button)findViewById(R.id.buttonAdd);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.radioButtonMale: gender = Person.GENDER.MALE; break;
                    case R.id.radioButtonFemale: gender = Person.GENDER.FEMALE; break;
                    default: gender=null;
                }
            }
        });

        spinnerNationality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nationality = getResources().getStringArray(R.array.nationalities)[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            firstName = editTextFirstName.getText().toString();
            lastName  = editTextLastName.getText().toString();
            if(isInputDataValid()){
                Person person=new Person(firstName,lastName,gender,nationality);
                people.add(person);
                listAdapterWithRecycleView.notifyDataSetChanged();

            }else {
                Toast.makeText(MainActivity.this,"Input Invalid",Toast.LENGTH_LONG).show();
            }
            }
        });
    }

    private boolean isInputDataValid(){
        if(AppUtility.isStringEmpty(firstName) || AppUtility.isStringEmpty(lastName) || AppUtility.isStringEmpty(nationality) || gender==null){
            return false;
        }else{
            return true;
        }
    }


}
