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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ListAdapterWithRecycleView.PersonModifier{

    private RecyclerView recyclerView;
    AppUtility appUtility;

    ListAdapterWithRecycleView listAdapterWithRecycleView;



    List<Person> people;
    int modificationIndex=-1;

    LinearLayoutManager linearLayoutManager;
    GridLayoutManager gridLayoutManager;
    StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appUtility=AppUtility.getAppUtility(getApplicationContext());
        recyclerView =(RecyclerView) findViewById(R.id.recycleListView);

        people = appUtility.getPeople();

        listAdapterWithRecycleView=new ListAdapterWithRecycleView(this,people);
        listAdapterWithRecycleView.setPersonModifier(this);

        linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listAdapterWithRecycleView);
    }

    @Override
    public void onPersonSelected(int position) {
        modificationIndex = position;
        Person person=people.get(position);

        if(person.getGender()== Person.GENDER.MALE){
            ((RadioButton)findViewById(R.id.radioButtonMale)).performClick();
        }else if(person.getGender()== Person.GENDER.FEMALE){
            ((RadioButton)findViewById(R.id.radioButtonFemale)).performClick();
        }

        //spinnerNationality.setSelection(appUtility.getNationalityForSelectedIndex(person.getNationality()));
    }

    @Override
    public void onPersonDeleted(int position) {
        /*buttonAdd.setTag("Add");
        buttonAdd.setText("Add");
        clearInputForm();*/
    }

}
