import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ArrayVersion
{
    static String[] vaccinationCentre = new String[6];
    static String[] sortBooth = new String[vaccinationCentre.length];

    static String patientName;

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
    public static void processIt(String option)
    {

        if (option.equals("100") || option.equals("VVB")) { viewAllBooths(); }
        else if (option.equals("101") || option.equals("VEB")) { viewEmptyBooths(); }
        else if (option.equals("102") || option.equals("APB")) { addPatient(); }
        else if (option.equals("103") || option.equals("RPB")) { removePatient(); }
        else if (option.equals("104") || option.equals("VPS")) { sortPatient(); }
        else if (option.equals("105") || option.equals("SPD")) { storeInFile(); }
        else if (option.equals("106") || option.equals("LPD")) { loadFromFile(); }
        else if (option.equals("107") || option.equals("VRV")) { viewRemainingVacc(); }
        else if (option.equals("108") || option.equals("AVS")) { addVaccStock(); }
        else { System.out.println("Please Enter a Valid Input"); }
    }


    /**
     * Initiate the vaccinationCentre array elements
     */
    static void intialise() {
        for (int x = 0; x < 6; x++)
            vaccinationCentre[x] = "e";
        System.out.println("Initialise");
    }


    /**
     * Runs when the user inputs 100 or "VVB" as menu option.
     * Displays information contained in all booths to the user.
     */
    static void viewAllBooths()
    {
        for (int i = 0; i < vaccinationCentre.length; i++)
        {
            System.out.print("Booth No."+ (i + 1) +" : ");
            if (vaccinationCentre[i] == "e")
                System.out.println(" ---Empty---");
            else
                System.out.println(vaccinationCentre[i]);
        }
    }


    /**
     * Runs when the user inputs 101 or "VVE" as menu option.
     * Displays all empty booths.
     */
    static void viewEmptyBooths()
    {
        for (int i = 0; i < vaccinationCentre.length; i++)
        {
            if (vaccinationCentre[i] == "e")
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
        System.out.println("Enter Booth Number(1-6) : ");
        boothNumber = input.nextInt();

        looper = true;
        while (looper)
        {
            if (boothNumber <= 6 && boothNumber >= 1)
            {
                System.out.println("Enter Patient's Name : ");
                vaccinationCentre[boothNumber-1] = input.next();

                vaccStock--;                                       //reduce 1 vaccination per each patient

                System.out.println("Patient added into Booth"+ boothNumber);
                looper = false;
            }
            else
            {
                System.out.println("Please Enter a Valid Booth Number(1-6) : ");
                boothNumber = input.nextInt();
            }
        }

        if(vaccStock<=20)                                           //check the vaccination count less than or equal 20, then print a warning.
            System.out.println("!!! WARNING : Vaccination Stock is Running Out !!!\n only "+vaccStock+" Vaccinations Remaining!");

        looper = true;

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
            if (boothNumber <= 6 && boothNumber >= 1)               //check the entered booth number in the range(1 - 6) or not
            {
                vaccinationCentre[boothNumber - 1] = "e";
                System.out.println("Booth no."+boothNumber+" Patient Removed");
                //vaccStock++;
                looper = false;
            }
            else
            {
                System.out.println("Please Enter a Valid Booth Number(1-6) : ");
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
        for (int i = 0; i < vaccinationCentre.length; i++)
        {
            sortBooth[i] = vaccinationCentre[i];                    //copy patients' names into another array
        }


        for (int i=0; i<sortBooth.length; i++)                      //sort patients; names using bubble sort algorithm
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

        for (int i=0; i<sortBooth.length; ++i)                      //print the sorted names
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

            for (int x = 0; x < vaccinationCentre.length; x++)          //write the booth details in file
            {
                boothReg.write("   "+x+"\t\t");
                if (vaccinationCentre[x].equals("e"))
                    patientName = "---Empty---";
                else
                    patientName = vaccinationCentre[x];

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

        vaccStock += vaccAmount;                                         //update the vaccination stock
        System.out.println(vaccAmount+" Vaccinations Added");
        System.out.println("Total Vaccinations : "+ vaccStock);
    }
}

