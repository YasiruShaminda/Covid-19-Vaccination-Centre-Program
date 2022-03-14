package com.vaccinationcenter;

/**
 * The booth class represents each booth in the vaccination center.
 *
 * @author Yasiru Luvishewa
 */
public class Booth extends Patient
{

    private String vaccType;
    private int boothNum;


    /**
     * This constructs a booth with a specified booth number,
     * and a vaccination type
     * @param boothNum the booth number of the booth
     * @param vaccType the vaccination type of the booth
     */
    public Booth( int boothNum, String vaccType)
    {
        this.boothNum = boothNum;
        this.vaccType = vaccType;
    }


    /**
     * vaccination type setter method
     * @param vaccType this booth's vaccination type
     */
    public void setVaccType(String vaccType)
    {
        this.vaccType = vaccType;
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
     * This returns the current Booth number of this booth.
     * @return this booth's booth number
     */
    public int getBoothNum()
    {
        return boothNum;
    }


    /**
     * This Returns the current vaccination type of this booth
     * @return this booth's vaccination type
     */
    public String getVaccType()
    {
        return vaccType;
    }
}
