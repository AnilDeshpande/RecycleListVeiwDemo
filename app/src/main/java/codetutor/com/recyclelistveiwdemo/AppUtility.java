package codetutor.com.recyclelistveiwdemo;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by anildeshpande on 8/14/17.
 */

public class AppUtility {
    private String [] names;
    private String[] lastnames;
    private String [] genders;
    private String [] nationalities;

    private String [] uniqueNationalitiesArray;

    private static final String EMPTY_STRING = "";

    private Context context;

    private List<Person> people;

    private static AppUtility appUtility;

    private AppUtility(Context context){
        this.context=context;
        names=context.getResources().getStringArray(R.array.names);
        lastnames = context.getResources().getStringArray(R.array.lastnames);
        genders = context.getResources().getStringArray(R.array.gender);
        nationalities=context.getResources().getStringArray(R.array.nationality);
        people=new ArrayList<Person>();
        for(int i=0;i<names.length;i++){
            Person person=new Person(names[i],lastnames[i],
                                     genders[i].equalsIgnoreCase("male")? Person.GENDER.MALE: Person.GENDER.FEMALE,
                                     nationalities[i]);
            people.add(person);
        }

        setUniqueNationalities();

    }

    public static AppUtility getAppUtility(Context context){
        if(appUtility==null){
            appUtility=new AppUtility(context);
        }
        return appUtility;
    }

    public List<Person> getPeople(){
        return this.people;
    }

    public String[] getNames() {
        return names;
    }

    public String[] getLastnames() {
        return lastnames;
    }

    public String[] getGenders() {
        return genders;
    }

    public String [] getNationalities(){
        return nationalities;
    }

    public static boolean isStringEmpty(String string){
        boolean isStringEmpty=false;
        if (string==null){
            isStringEmpty=true;
        }else{
            if(string.length()==0 || string.equals(EMPTY_STRING)){
                isStringEmpty=true;
            }
        }
        return isStringEmpty;
    }

    private void setUniqueNationalities(){
        List<String> uniqueNationalities = new ArrayList<String>();
        for(String nationality: getNationalities()){
            if(!uniqueNationalities.contains(nationality)){
                uniqueNationalities.add(nationality);
            }
        }
        uniqueNationalitiesArray = uniqueNationalities.toArray(new String[uniqueNationalities.size()]);
    }

    public String[] getUniqueNationalitiesArray(){
        return this.uniqueNationalitiesArray;
    }



    public int getNationalityForSelectedIndex(String nationality){
        int nationalityIndex=0;
        String [] nationalities = this.uniqueNationalitiesArray;
        do{
            if(nationalities[nationalityIndex].equals(nationality)){
                break;
            }
            nationalityIndex++;
        }while (nationalityIndex<nationalities.length);

        return nationalityIndex;
    }

}
