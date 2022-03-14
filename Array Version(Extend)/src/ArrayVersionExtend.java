import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ArrayVersionExtend
{
    static String[][] vaccinationCentre = new String[2][6];
    static String[] sortBooth = new String[vaccinationCentre[0].length];

    static String patientName;
    static String patientFirstName;
    static String patientSurname;
    static String vaccType;

    static int boothNumber;
    static int vaccStock = 150;

    static boolean looper;

    static Scanner input = new Scanner(System.in);


    public static void main(String[] arg)
    {

        intialise();

        String option;

        looper = true;
        while (looper) {
            System.out.println("\n---------------------------------------------------------------------------------" +
                    "\n----------------------------------M E N U----------------------------------------" +
                    "\n---------------------------------------------------------------------------------");
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
     * Check the selected option from the menu and
     * executes the corresponding method.
     * @param option the option selected by the user from the menu
     */
    public static void processIt(String option) {

        if (option.equals("100") || option.equals("VVB")) { viewAllBooths(); }
        else if (option.equals("101") || option.equals("VEB")) { viewEmptyBooths(); }
        else if (option.equals("102") || option.equals("APB")) { addPatient(); }
        else if (option.equals("103") || option.equals("RPB")) { removePatient(); }
        else if (option.equals("104") || option.equals("VPS")) { sortPatient(); }
        else if (option.equals("105") || option.equals("SPD")) { storeInFile(); }
        else if (option.equals("106") || option.equals("LPD")) { loadFromFile(); }
        else if (option.equals("107") || option.equals("VRV")) { viewRemainingVacc(); }
        else if (option.equals("108") || option.equals("AVS")) { addVaccStock(); }
        else { System.out.println("Enter a Valid Input"); }
    }



    /**
     * Initiate the vaccinationCentre array elements
     */
    static void intialise() {
        for (int x = 0; x < vaccinationCentre[0].length; x++)
            vaccinationCentre[0][x] = "e";
        System.out.println("Initialise");
    }



    /**
     * Runs when the user inputs 100 or "VVB" as menu option.
     * Displays information contained in all booths to the user.
     */
    static void viewAllBooths()
    {
        for (int i = 0; i < vaccinationCentre[0].length; i++)
        {
            System.out.print("Booth No."+ (i + 1) +" : ");
            if (vaccinationCentre[0][i] == "e")
                System.out.println(" ---Empty---");
            else
                System.out.println(vaccinationCentre[0][i]+" "+vaccinationCentre[1][i]);
        }
    }



    /**
     * Runs when the user inputs 101 or "VVE" as menu option.
     * Displays all empty booths.
     */
    static void viewEmptyBooths()
    {
        for (int i = 0; i < vaccinationCentre[0].length; i++)
        {
            if (vaccinationCentre[0][i] == "e")
                System.out.println("Booth No."+ (i + 1) +" is Empty. ");
        }
    }



    /**
     *  Runs when the user inputs 102 or "APB" as menu option.
     *
     *  This adds patents to the booth and
     *  retrieves their details from the user.
     */
    static void addPatient()
    {
        System.out.println("Add Patient");

        System.out.println("Enter Patient's First Name : ");
        patientFirstName = input.next();

        System.out.println("Enter Patient's Surname : ");
        patientSurname = input.next();

        checkVaccType();

        vaccinationCentre[0][boothNumber - 1] = patientFirstName;
        vaccinationCentre[1][boothNumber - 1] = patientSurname;

        vaccStock--;            //reduce 1 vaccination per each patient
        System.out.println("Patient added into Booth no."+boothNumber);

        if(vaccStock<=20)       //check the vaccination count less than or equal 20, then print a warning.
            System.out.println("!!! WARNING : Vaccination Sock is Running Out !!!\n only "+vaccStock+" Vaccinations Remaining!");

        looper = true;

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
            System.out.println("Select Vaccination What You Want : ");          //ask for a vaccination type
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
        looper = true;
        while (looper)
        {                                                               //if requested AstraZeneca
            if(vaccType.equalsIgnoreCase("A"))
            {
                System.out.println("Select Your Booth 1 or 2");
                boothNumber = input.nextInt();
                if (boothNumber == 1 || boothNumber == 2)
                    looper = false;
                else
                    System.out.println("Please Enter a Valid Booth id");
            }

            if(vaccType.equalsIgnoreCase("S"))
            {                                                             //if requested Sinopharm
                System.out.println("Select Your Booth 3 or 4");
                boothNumber = input.nextInt();
                if (boothNumber == 3 || boothNumber == 4)
                    looper = false;
                else
                    System.out.println("Please Enter a Valid Booth id");
            }

            if(vaccType.equalsIgnoreCase("P"))
            {                                                              //if requested Pfizer
                System.out.println("Select Your Booth 5 or 6");
                boothNumber = input.nextInt();
                if (boothNumber == 5 || boothNumber == 6)
                    looper = false;
                else
                    System.out.println("Please Enter a Valid Booth id");
            }

        }

    }


    /**
     *  Runs when the user inputs 103 or "RPB" as menu option.
     *  This removes a patient from a given booth.
     */
    static void removePatient()
    {
        System.out.println("Remove Patient");
        System.out.println("Enter Booth Number(1-6) : ");
        boothNumber = input.nextInt();

        looper = true;
        while (looper)
        {
            if (boothNumber <= 6 && boothNumber >= 1)        //check the entered booth number in the range(1 - 6) or not
            {
                vaccinationCentre[0][boothNumber - 1] = "e";
                vaccinationCentre[1][boothNumber - 1] = "";
                System.out.println("Booth no."+boothNumber+" Patient Removed");
                //vaccStock++;
                looper = false;
            }
            else
            {
                System.out.println("Enter Valid Booth Number(1-6) : ");
                boothNumber = input.nextInt();
            }
        }
        looper = true;
    }



    /**
     * Runs when the user inputs 104 or "VPS" as menu option.
     *
     * This method arranges the names of the patients in the booth
     * in alphabetical order and displays them.
     */
    static void sortPatient()
    {
        for (int i = 0; i < vaccinationCentre[0].length; i++) {
            sortBooth[i] = vaccinationCentre[0][i]+" "+vaccinationCentre[1][i];     //copy patients' Full names into another array
        }

        for (int i=0; i<sortBooth.length; i++)                                      //sort patients; names using bubble sort algorithm
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

        for (int i=0; i<sortBooth.length; ++i)               //print the sorted names
        {
            if (!sortBooth[i].equals("e"))
                System.out.println(sortBooth[i]);
        }
    }



    /**
     * Runs when the user inputs 105 or "SPD" as menu option.
     *
     * This will record all the details contained
     * in the current booth in a file.
     */
    static void storeInFile()
    {
        try
        {
            FileWriter boothReg = new FileWriter("BoothRegArray.txt", true);
            boothReg.write("Booth no.\tPatient Name\n");
            for (int x = 0; x < vaccinationCentre[0].length; x++)
            {                                                               //write the booth details in file
                boothReg.write("   "+x+"\t\t");
                if (vaccinationCentre[0][x].equals("e"))
                    patientName = "---Empty---";
                else
                    patientName = vaccinationCentre[0][x]+" "+vaccinationCentre[1][x];

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
    }



    /**
     * Runs when the user inputs 106 or "LPD" as menu option.
     *
     * This takes the information recorded in the file and
     * writes it on the console for display to the user.
     */
    static void loadFromFile()
    {
        try
        {
            File boothReg = new File("BoothRegArray.txt");
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
        }
    }



    /**
     * Runs when the user inputs 107 or "VRV" as menu option.
     * This shows the number of vaccinations currently available.
     */
    static void viewRemainingVacc()
    {
        System.out.println(vaccStock+" Vaccinations Remaining");
    }



    /**
     * Runs when the user inputs 107 or "VRV" as menu option.
     *
     * This adds new vaccinations to the vaccination stock.
     * The amount of vaccination required to be collected is obtained from the user.
     */
    static void addVaccStock()
    {
        int vaccAmount;

        System.out.print("Enter Vaccination amount to add : ");
        vaccAmount = input.nextInt();

        vaccStock += vaccAmount;                //update the vaccination stock
        System.out.println(vaccAmount+" Vaccinations Added");
        System.out.println("Total Vaccinations : "+ vaccStock);
    }



}