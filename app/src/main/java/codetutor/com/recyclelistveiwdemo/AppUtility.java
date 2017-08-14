package codetutor.com.recyclelistveiwdemo;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anildeshpande on 8/14/17.
 */

public class AppUtility {
    private String [] names;
    private String[] lastnames;
    private String [] genders;
    private String [] nationalities;

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
        }

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

}
