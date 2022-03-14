package com.vaccinationcenter;

/**
 * The booth class represents each booth in the vaccination center.
 *
 * @author Yasiru Luvishewa
 */
public class Booth
{

    private String patientName;
    private int boothNum;


    /**
     * This constructs a booth with a Patient's name,
     * and a booth number
     * @param patientName the patient's name of the booth
     * @param boothNum the booth number of the booth
     */
    public Booth(String patientName, int boothNum)
    {
        this.patientName = patientName;
        this.boothNum = boothNum;
    }


    /**
     * This setter sets the patient's name for this booth.
     * @param patientName patient name of this booth.
     */
    public void setPatientName(String patientName)
    {
        this.patientName = patientName;
    }


    /**
     * Booth number setter method
     * @param boothNum booth number of this booth
     */
    public void setBoothNum(int boothNum)
    {
        this.boothNum = boothNum;
    }


    /**
     * This returns the current Patient's name of this booth.
     * @return this booth's patient's name.
     */
    public String getPatientName()
    {
        return patientName;
    }


    /**
     * This returns the current Booth number of this booth.
     * @return this booth's booth number
     */
    public int getBoothNum()
    {
        return boothNum;
    }


}
