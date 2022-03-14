package com.vaccinationcenter;

import java.io.*;
import java.util.Scanner;

public class VaccinationCentre
{

    static Booth[] booth = new Booth[6];
    static String [] sortBooth = new String[booth.length];

    static int boothNumber;
    static int vaccStock = 150;

    static boolean looper;

    static Scanner input = new Scanner(System.in);


    public static void main(String[] args)
    {

        initialise();

        String option;

        looper =true;
        while(looper)
        {
            System.out.println( "\n---------------------------------------------------------------------------------\n"+
                    "----------------------------------M E N U----------------------------------------\n"+
                    "---------------------------------------------------------------------------------");
            System.out.println("100 or VVB : View all Vaccination Booths\n" +
                    "101 or VEB : View all Empty Booths\n" +
                    "102 or APB : Add Patient to a Booth\n" +
                    "103 or RPB : Remove Patient from a Booth\n" +
                    "104 or VPS : View Patients Sorted in alphabetical order\n" +
                    "105 or SPD : Store Program Data into file\n" +
                    "106 or LPD : Load Program Data from file\n" +
                    "107 or VRV : View Remaining Vaccinations\n" +
                    "108 or AVS : Add Vaccinations to the Stock\n" +
                    "999 or EXT : Exit the Program");
            System.out.println("---------------------------------------------------------------------------------");
            System.out.print("Select Option : ");
            option = input.next().toUpperCase();
            System.out.println("---------------------------------------------------------------------------------");

            if (option.equals("999") || option.equals("EXT"))
                looper = false;
            else
                processIt(option);
        }

    }


    /**
     * Initiate the vaccinationCentre array elements
     */
    static void initialise()
    {
        for (int x=0; x<booth.length ; x++)
        {
            booth[x] = new Booth("e",x+1);
        }
        System.out.println("Initialized");
    }



    /**
     * Check the selected option from the menu and
     * executes the corresponding method.
     * @param option the option selected by the user from the menu
     */
    public static void processIt(String option)
    {
        if (option.equals("100") || option.equals("VVB")) { viewAllBooths(); }
        else if (option.equals("101") || option.equals("VEB")) { viewEmptyBooths(); }
        else if (option.equals("102") || option.equals("APB")) { addPatient(); }
        else if (option.equals("103") || option.equals("RPB")) { removePatient(); }
        else if (option.equals("104") || option.equals("VPS")) { sortPatient(); }
        else if (option.equals("105") || option.equals("SPD")) { storeOnFile(); }
        else if (option.equals("106") || option.equals("LPD")) { loadFromFile(); }
        else if (option.equals("107") || option.equals("VRV")) { remVacc(); }
        else if (option.equals("108") || option.equals("AVS")) { addVacc(); }
        else { System.out.println("Please Enter a Valid Input"); }
    }



    /**
     * Runs when the user inputs 100 or "VVB" as menu option.
     * Displays information contained in all booths to the user.
     */
    public static void viewAllBooths()
    {
        System.out.println("All Vaccination Booths");
        for (int x=0; x<booth.length; x++)
        {
            if (!(booth[x].getPatientName().equals("e")))
            {
                System.out.print("Booth " + booth[x].getBoothNum() +" : ");
                System.out.println(booth[x].getPatientName());
            }
            else
                System.out.println("Booth "+ booth[x].getBoothNum() +" : ---Empty---");
        }
        System.out.println("---------------------------------------------------------------------------------");
    }



    /**
     * Runs when the user inputs 101 or "VVE" as menu option.
     * Displays all empty booths.
     */
    public static void viewEmptyBooths()
    {
        System.out.println("All Empty Booths");
        for (int x=0; x<booth.length; x++)
        {
            if(booth[x].getPatientName().equals("e"))
                System.out.println("Booth "+  booth[x].getBoothNum() + " : Empty");
        }
        System.out.println("---------------------------------------------------------------------------------");
    }



    /**
     *  Runs when the user inputs 102 or "APB" as menu option.
     *
     *  This adds patents to the booth and
     *  retrieves their details from the user.
     */
    public static void addPatient()
    {
        System.out.println("Add Patient");

        System.out.println("Enter Booth Number(1-6) : ");
        boothNumber = input.nextInt();

        looper = true;
        while (looper)
        {
            if (boothNumber <= 6 && boothNumber >= 1)
            {
                System.out.println("Enter Patient's Name : ");
                booth[boothNumber-1].setPatientName(input.next());
                booth[boothNumber - 1].setBoothNum(boothNumber);
                looper = false;
            }
            else
            {
                System.out.println("Please Enter a Valid Booth Number(1-6) : ");
                boothNumber = input.nextInt();
            }
        }
        vaccStock--;            //reduce 1 vaccination per each patient
        System.out.println("Patient added into Booth"+ boothNumber);

        if(vaccStock<=20)        //check the vaccination count less than or equal 20, then print a warning.
            System.out.println("!!! WARNING : Vaccination Stock is Running Out !!!\n only "+vaccStock+" Vaccinations Remaining!");

        System.out.println("---------------------------------------------------------------------------------");
        looper = true;
    }



    /**
     *  Runs when the user inputs 103 or "RPB" as menu option.
     *  This removes a patient from a given booth.
     */
    public static void removePatient()
    {
        System.out.println("Remove Patient");
        System.out.println("Enter Booth Number(1-6) : ");
        boothNumber = input.nextInt();

        looper = true;
        while (looper)
        {
            if (boothNumber <= 6 && boothNumber >= 1)       //check the entered booth number in the range(1 - 6) or not
            {
                booth[boothNumber - 1].setPatientName("e");
                System.out.println("Booth no."+boothNumber+" Patient Removed");
                looper = false;
            }
            else
            {
                System.out.println("Please Enter a Valid Booth Number(1-6) : ");
                boothNumber = input.nextInt();
            }
        }

        System.out.println("---------------------------------------------------------------------------------");
        looper = true;
    }



    /**
     * Runs when the user inputs 104 or "VPS" as menu option.
     *
     * This method arranges the names of the patients in the booth
     * in alphabetical order and displays them.
     */
    public static void sortPatient()
    {

        for (int i=0; i<booth.length; i++) {
            sortBooth[i] = booth[i].getPatientName();       //copy patients' Full names into another array
        }

        for (int i=0; i<sortBooth.length; i++)              //sort patients; names using bubble sort algorithm
        {
            for (int j = 1; j < sortBooth.length-i ; j++)
            {
                if (sortBooth[j-1].compareToIgnoreCase(sortBooth[j]) > 0)
                {
                    String temp = sortBooth[j-1];
                    sortBooth[j-1] =sortBooth[j];
                    sortBooth[j] = temp;

                }
            }
        }

        for (int i=0; i<sortBooth.length; ++i)              //print the sorted names
        {
            if (!sortBooth[i].equals("e"))
                System.out.println(sortBooth[i]);
        }
        System.out.println("---------------------------------------------------------------------------------");
    }



    /**
     * Runs when the user inputs 105 or "SPD" as menu option.
     *
     * This will record all the details contained
     * in the current booth in a file.
     */
    public static void storeOnFile()
    {
        String patientName;

        try
        {
            FileWriter boothReg = new FileWriter("BoothRegClass.txt", true);
            boothReg.write("Booth no.\tPatient Name\n");
            for (int x=0; x<booth.length; x++)
            {                                                                   //write the booth details in file
                boothReg.write("   "+booth[x].getBoothNum()+"\t\t");
                if (booth[x].getPatientName().equals("e"))
                    patientName = "---Empty---";
                else
                    patientName = booth[x].getPatientName();
                boothReg.write(patientName+"\n");
            }
            boothReg.write("---------------------------------------\n");
            boothReg.close();
            System.out.println("Booth Register Updated.");
        }
        catch (IOException e)
        {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
        System.out.println("---------------------------------------------------------------------------------");
    }



    /**
     * Runs when the user inputs 106 or "LPD" as menu option.
     *
     * This takes the information recorded in the file and
     * writes it on the console for display to the user.
     */
    public static void loadFromFile()
    {
        System.out.println("Program Data From File\n---------------------------------------");

        try
        {
            File boothReg = new File("BoothRegClass.txt");
            Scanner register = new Scanner(boothReg);

            while (register.hasNextLine())
            {
                String data = register.nextLine();
                System.out.println(data);
            }
            register.close();

        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found!!");
            e.printStackTrace();
            System.out.println("---------------------------------------------------------------------------------");
        }
    }



    /**
     * Runs when the user inputs 107 or "VRV" as menu option.
     * This shows the number of vaccinations currently available.
     */
    public static void remVacc()
    {
        System.out.println("View Remaining Vaccinations");
        System.out.println(vaccStock+" Vaccinations Remaining");
        System.out.println("---------------------------------------------------------------------------------");
    }



    /**
     * Runs when the user inputs 107 or "VRV" as menu option.
     *
     * This adds new vaccinations to the vaccination stock.
     * The amount of vaccination required to be collected is obtained from the user.
     */
    public static void addVacc()
    {
        int vaccAmount;

        System.out.println("Add Vaccinations");
        System.out.print("Enter Vaccination amount to add : ");
        vaccAmount = input.nextInt();

        vaccStock += vaccAmount;                                //update the vaccination stock
        System.out.println(vaccAmount+" Vaccinations Added");
        System.out.println("Total Vaccinations : "+ vaccStock);

        System.out.println("---------------------------------------------------------------------------------");
    }
}