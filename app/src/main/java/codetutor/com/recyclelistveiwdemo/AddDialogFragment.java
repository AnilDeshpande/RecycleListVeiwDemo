package codetutor.com.recyclelistveiwdemo;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by anildeshpande on 2/21/18.
 */

public class AddDialogFragment extends DialogFragment {

    View rootView;

    private EditText editTextFirstName, editTextLastName;
    private Spinner spinnerNationality;
    private RadioGroup radioGroup;
    private Button buttonAdd;

    String firstName, lastName, nationality;
    Person.GENDER gender;

    Context context;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dialog_additem,container);

        return rootView;
    }

    private void initUI(){

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity().getApplicationContext();
    }

    private void initPersonInputForm(){
        editTextFirstName = (EditText)rootView.findViewById(R.id.editTextFirstName);
        editTextLastName = (EditText)rootView.findViewById(R.id.editTextLastName);
        radioGroup = (RadioGroup)rootView.findViewById(R.id.radioGroupGender);

        spinnerNationality = (Spinner)rootView.findViewById(R.id.spinnerNationality);
        AppUtility appUtility=AppUtility.getAppUtility(context);
        spinnerNationality.setAdapter(new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,appUtility.getUniqueNationalitiesArray()));
        buttonAdd = (Button)rootView.findViewById(R.id.buttonAdd);
        buttonAdd.setTag("Add");

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
                Person person=null;

                if(isInputDataValid()) {
                    person = new Person(firstName, lastName, gender, nationality);
                }else{
                    Toast.makeText(context,"Input Invalid",Toast.LENGTH_LONG).show();
                }

                String behaviour = (String)buttonAdd.getTag();
                /*if(behaviour.equalsIgnoreCase("Add")){
                    if(person!=null){
                        people.add(person);
                        listAdapterWithRecycleView.notifyDataSetChanged();
                        recyclerView.scrollToPosition(people.size()-1);
                        clearInputForm();
                    }
                }else if(behaviour.equalsIgnoreCase("modify")){
                    if(person!=null){
                        try{
                            people.get(modificationIndex).setName(person.getName());
                            people.get(modificationIndex).setLastName(person.getLastName());
                            people.get(modificationIndex).setGender(person.getGender());
                            people.get(modificationIndex).setNationality((String)spinnerNationality.getSelectedItem());

                            listAdapterWithRecycleView.notifyItemChanged(modificationIndex);
                            clearInputForm();
                            buttonAdd.setTag("Add");
                            buttonAdd.setText("Add");
                        }catch (IndexOutOfBoundsException exception){
                            Toast.makeText(MainActivity.this,"Can't modify, item moved",Toast.LENGTH_LONG ).show();
                            listAdapterWithRecycleView.notifyDataSetChanged();
                            clearInputForm();
                            buttonAdd.setTag("Add");
                            buttonAdd.setText("Add");
                        }
                    }
                }*/

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

    private void clearInputForm() {
        editTextFirstName.setText("");
        editTextLastName.setText("");
        radioGroup.clearCheck();
        spinnerNationality.setSelection(0);
    }


}
