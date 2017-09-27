package codetutor.com.recyclelistveiwdemo;

/**
 * Created by anildeshpande on 8/14/17.
 */

public class Person{

    private String name;
    private String lastName;
    private GENDER gender;
    private String nationality;

    public Person(){
        super();
    }

    public Person(String name,String lastName, GENDER gender, String nationality){
        this.name=name;
        this.lastName=lastName;
        this.gender=gender;
        this.nationality=nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public GENDER getGender() {
        return gender;
    }

    public void setGender(GENDER gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    enum GENDER{MALE, FEMALE};
}
