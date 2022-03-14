package com.vaccinationcenter;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class VaccinationCentre
{

    static Booth[] booth = new Booth[6];
    static String [] sortBooth = new String[booth.length];
    static LinkedList <Booth> waitingList = new LinkedList<Booth>();

    static String firstName;
    static String surName;
    static String idNumber;
    static String city;
    static String vaccType;

    static int age;
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
            makeEmpty(x);
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
        else { System.out.println("Enter a Valid Input"); }
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
            System.out.print("Booth " + (x+1) +" : ");
            if (!(booth[x].getFirstName().equals("e")))
            {
                System.out.print(booth[x].getFirstName()+" ");
                System.out.print(booth[x].getSurName()+"\t");
                System.out.print(booth[x].getAge()+"\t");
                System.out.print(booth[x].getIdNumber()+"\t");
                System.out.println(booth[x].getCity());
            }
            else
                System.out.println("---Empty---");
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
            if(booth[x].getFirstName().equals("e"))
                System.out.println("Booth no."+  booth[x].getBoothNum() + " is Empty");
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

        System.out.println("Patient's First Name : ");
        firstName = input.next();

        System.out.println("Patient's Surname : ");
        surName = input.next();

        System.out.println("Patient's Age : ");
        age = input.nextInt();

        System.out.println("Your NIC/Passport Id : ");
        idNumber = input.next();

        System.out.println("Your City : ");
        city = input.next();

        checkVaccType();

        vaccStock--;            //reduce 1 vaccination per each patient

        if(vaccStock<=20)       //check the vaccination count less than or equal 20, then print a warning.
            System.out.println("!!! WARNING : Vaccination Stock is Running Out !!!\n only "+vaccStock+" Vaccinations Remaining!");

        System.out.println("---------------------------------------------------------------------------------");
    }


    /**
     * This allows the user to receive his requested vaccination
     * when a patient is added to a booth.
     */
    static void checkVaccType()
    {
        looper = true;
        while (looper)
        {
            System.out.println("Select Vaccination What You Want : ");      //ask for a vaccination type
            System.out.println("\t Press A for : AstraZeneca");
            System.out.println("\t Press S for : Sinopharm");
            System.out.println("\t Press P for : Pfizer");
            vaccType = input.next().toLowerCase();

            if (vaccType.equals("a") || vaccType.equals("s") || vaccType.equals("p"))
            {
                selectVaccBooth();
                looper = false;
            }
            else
                System.out.println("Enter a Valid Option");
        }
        looper = true;
    }


    /**
     * The booth number for that patient is selected according
     * to the type of vaccination obtained by checkVaccType.
     */
    static void selectVaccBooth()
    {
            if(vaccType.equalsIgnoreCase("a"))
            {                                                       //if requested AstraZeneca
                vaccType = "AstraZeneca";

                if (booth[0].getFirstName().equals("e"))
                {
                    boothNumber = 1;
                    addToBooth();
                }
                else if (booth[1].getFirstName().equals("e")) {
                    boothNumber = 2;
                    addToBooth();
                }
                else
                    addToWait();
            }
            else if(vaccType.equalsIgnoreCase("s"))
            {                                                        //if requested Sinopharm
                vaccType = "Sinopharm";

                if (booth[2].getFirstName().equals("e"))
                {
                    boothNumber = 3;
                    addToBooth();
                }
                else if (booth[3].getFirstName().equals("e")) {
                    boothNumber = 4;
                    addToBooth();
                }
                else
                    addToWait();
            }
            else if(vaccType.equalsIgnoreCase("p"))
            {                                                          //if requested Pfizer
                vaccType = "Pfizer";

                if (booth[4].getFirstName().equals("e"))
                {
                    boothNumber = 5;
                    addToBooth();
                }
                else if (booth[5].getFirstName().equals("e")) {
                    boothNumber = 6;
                    addToBooth();
                }
                else
                    addToWait();

            }
    }


    /**
     * This adds a patient to the booth according to the requested vaccination.
     */
    public static void addToBooth()
    {
        booth[boothNumber-1].setBoothNum(boothNumber);
        booth[boothNumber-1].setVaccType(vaccType);
        booth[boothNumber-1].setFirstName(firstName);
        booth[boothNumber-1].setSurName(surName);
        booth[boothNumber-1].setAge(age);
        booth[boothNumber-1].setIdNumber(idNumber);
        booth[boothNumber-1].setCity(city);

        System.out.println("Patient added into Booth"+ boothNumber);

    }


    /**
     * When a patient is not allowed to be added to the booths according to the requested vaccination,
     * this will add the patient to the waiting list automatically.
     */
    public static void addToWait()
    {
        Booth boothWait = new Booth(0 ,"e");

        boothWait.setBoothNum(boothNumber);
        boothWait.setVaccType(vaccType);
        boothWait.setFirstName(firstName);
        boothWait.setSurName(surName);
        boothWait.setAge(age);
        boothWait.setIdNumber(idNumber);
        boothWait.setCity(city);

        waitingList.add(boothWait);

        System.out.println("Patient added into the Waiting List");
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

        String vaccination;
        looper = true;
        while (looper)
        {
            if (boothNumber <= 6 && boothNumber >= 1)                   //check the entered booth number in the range(1 - 6) or not
            {
                vaccination = booth[boothNumber-1].getVaccType();

                makeEmpty(boothNumber - 1);
                System.out.println("Booth no."+boothNumber+" Patient Removed");
                //vaccStock++;

                searchNset(vaccination);

                looper = false;
            }
            else
            {
                System.out.println("Enter Valid Booth Number(1-6) : ");
                boothNumber = input.nextInt();
            }
        }

        System.out.println("---------------------------------------------------------------------------------");
        looper = true;
    }


    /**
     * Once a patient has been removed, this will automatically add a patient
     * to the removed booth from the waiting list.
     * @param vaccination vaccination type of the removed booth.
     */
    public static void searchNset(String vaccination)
    {
        int j = 0;
        while (j < waitingList.size())
        {
            if (waitingList.get(j).getVaccType().equals(vaccination))
            {
                booth[boothNumber - 1] = waitingList.get(j);
                System.out.println("Booth no." + boothNumber + " : Patient Added (" + waitingList.get(j).getFirstName() + ") from Waiting List");
                waitingList.remove(j);

                j = waitingList.size()-1; //break
            }
            j++;
        }
    }



    /**
     * Runs when the user inputs 104 or "VPS" as menu option.
     *
     * This method arranges the names of the patients in the booth
     * in alphabetical order and displays them.
     */
    public static void sortPatient()
    {

        for (int i=0; i<booth.length; i++)
        {
            sortBooth[i] = (booth[i].getFirstName()+" "+booth[i].getSurName());     //copy patients' Full names into another array
        }

        for (int i=0; i<sortBooth.length; i++)                  //sort patients; names using bubble sort algorithm
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
            if (!sortBooth[i].equals("e e"))
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
        String patientDetails;
        try
        {
            FileWriter boothReg = new FileWriter("BoothRegClass.txt", true);
            boothReg.write("Booth no.\tPatient Name\t\tAge\t\tNIC/Passport\tCity\n");
            for (int x=0; x<booth.length; x++)
            {                                                                       //write the booth details in file
                boothReg.write("   "+booth[x].getBoothNum()+"\t\t");
                if (booth[x].getFirstName().equals("e"))
                    patientDetails = "---Empty---";
                else
                    patientDetails = (booth[x].getFirstName()+" "+booth[x].getSurName()+"\t"+booth[x].getAge()+"\t\t"+booth[x].getIdNumber()+"\t"+booth[x].getCity());
                boothReg.write(patientDetails+"\n");
            }
            boothReg.write("-----------------------------------------------------------------------------------\n");
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


    /**
     * This empties all the details in the relevant booth
     * when initializing or removing a patient.
     *
     * @param i Obtain the relevant booth number to remove or initialize.
     */
    public static void makeEmpty(int i)
    {
        booth[i] = new Booth(i+1, "e");
        booth[i].setFirstName("e");
        booth[i].setSurName("e");
        booth[i].setAge(0);
        booth[i].setIdNumber("e");
        booth[i].setCity("e");
    }
}