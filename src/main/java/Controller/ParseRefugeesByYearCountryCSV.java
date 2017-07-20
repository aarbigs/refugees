package Controller;

/**
This class is designed to parse the refugees_all_years.csv file from the UN. This file can be found at https://data.world/nripper
 /refugee-host-nations
 */
import model.RefugeesByYearCountry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ParseRefugeesByYearCountryCSV {



    public static ArrayList<RefugeesByYearCountry> parseFile(){
        System.out.println("parseFile called");
        //location of csv file
        // TODO: this should be a command line argument for flexibility
        String csvFile = "/home/aaron/Documents/codingnomads/nrippner-refugee-host-nations/refugees_all_years.csv";

        // instantiate "line" which will hold each line read from the csv file
        String line = "";
        String cvsSplitBy = ",";

        //create Arraylist to hold RefugeesByYearCounty objects that are created in while loop below
        ArrayList<RefugeesByYearCountry> fugees = new ArrayList<>();

        // use Try-with-resources to open csv file
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            //discard first line of file which is the header
            line = br.readLine();

            // this while loop assigns the next line of the csv file to the variable line
            // while it is not null it continues through the loop
            while ((line = br.readLine()) != null) {
                System.out.println("parsing..");
                //create empty pojo of RefugeesByYearCountry to populate from csv file, each line
                // is a new object
                RefugeesByYearCountry ref = new RefugeesByYearCountry();

                //use the "split" metho to split the line on each comma
                //the results wll automatically be stored in an array called data
                String[] data = line.split(",");

                //begin setting RefugeesByYearCountry (ref) instance variables
                //using the data read from the csv file, which is now in the data array
                ref.setId(Integer.parseInt(data[0]));
                ref.setYear(Integer.parseInt(data[1]));
                ref.setCountry_territory(data[2]);
                ref.setOrigin(data[3]);

                //nested try catch to catch rows that have nothing but empty values after index 3. The split
                //method does not store the empty values if all are empty after a given index
                try {
                    ref.setRefugees(data[4].equalsIgnoreCase("") ? 0 : Double.parseDouble(data[4]));
                    ref.setAsylum_seekers(data[5].equalsIgnoreCase("") ? 0 : Double.parseDouble(data[5]));
                    ref.setReturnt_refugees(data[6].equalsIgnoreCase("") ? 0 : Double.parseDouble(data[6]));
                    ref.setInternally_displaced_persons(data[7].equalsIgnoreCase("") ? 0 : Double.parseDouble(data[7]));
                    ref.setReturned_idps(data[8].equalsIgnoreCase("") ? 0 : Double.parseDouble(data[8]));
                    ref.setStateless_persons(data[9].equalsIgnoreCase("") ? 0 : Double.parseDouble(data[9]));
                    ref.setOthers_of_concern(data[10].equalsIgnoreCase("") ? 0 : Double.parseDouble(data[10]));
                    ref.setTotal_population(data[11].equalsIgnoreCase("") ? 0 : Double.parseDouble(data[11]));
                } catch (IndexOutOfBoundsException e){
                    // if exception is caught set all remaining values to 0.
                    ref.setRefugees(0);
                    ref.setAsylum_seekers(0);
                    ref.setReturnt_refugees(0);
                    ref.setInternally_displaced_persons(0);
                    ref.setReturned_idps(0);
                    ref.setStateless_persons(0);
                    ref.setOthers_of_concern(0);
                    ref.setTotal_population(0);
                }

                // add RefugeesByYearCountry object created in this loop to the Arraylist called refugees.
                fugees.add(ref);

                //loop through until all the lines of csv file have been processed.
            }
            //catch IOexception
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return fugees;
    }

}
