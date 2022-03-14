package com.vaccinationcenter;

/**
 * The patient class represents each patient in the booths.
 *
 * @author Yasiru Luvishewa
 */
public class Patient
{
    String firstName;
    String surName;
    String city;
    String idNumber;

    int age;


    /**
     * This setter sets the first name for this patient
     * @param firstName First name of this patient
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }


    /**
     *This setter sets the Surname for this patient.
     * @param surName surname of this Patient
     */
    public void setSurName(String surName)
    {
        this.surName = surName;
    }


    /**
     * This setter sets the city for this patient.
     * @param city  the city of this Patient
     */
    public void setCity(String city)
    {
        this.city = city;
    }


    /**
     * This setter sets the NIC or Passport id for this patient.
     * @param idNumber the NIC or Passport id of this Patient
     */
    public void setIdNumber(String idNumber)
    {
        this.idNumber = idNumber;
    }


    /**
     * This setter sets the age for this patient.
     * @param age the age of this patient.
     */
    public void setAge(int age)
    {
        this.age = age;
    }


    /**
     * This returns the first name of this patient.
     * @return this patient's first name.
     */
    public String getFirstName()
    {
        return firstName;
    }


    /**
     * This returns the surname of this patient.
     * @return this patient's surname.
     */
    public String getSurName()
    {
        return surName;
    }


    /**
     * This returns the city of this patient.
     * @return this patient's city.
     */
    public String getCity()
    {
        return city;
    }


    /**
     * This returns the NIC or Passport id of this patient.
     * @return this patient's NIC or Passport id.
     */
    public String getIdNumber()
    {
        return idNumber;
    }


    /**
     * This returns the age of this patient.
     * @return this patient's age.
     */
    public int getAge()
    {
        return age;
    }

}
