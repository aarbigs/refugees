package Controller;

import model.Query;
import model.RefugeesByYearCountry;
import model.WorldBankIndicators;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by aaron on 7/19/17.
 */
public class Controller {

    public static void main(String[] args) {


        boolean populate = false;
        DBAccess db = new DBAccess();
        String country = "Switzerland";
        //if(args.length > 0) {
            //country = args[0];
            ResultSet resultsOne = db.readDataBase(getCustomQuery(country));
            ArrayList<Query> finalOne = db.getCustomQuery(resultsOne);
            System.out.println("The size of the ArrayList is " + finalOne.size());
        //}

        if (populate){
            System.out.println("starting application...");
            ArrayList<RefugeesByYearCountry> refugees = ParseRefugeesByYearCountryCSV.parseFile();
            System.out.println("parsing complete");
            ResultSet worldResults = db.readDataBase(getWorldQuery());
            boolean success = db.writeToDb(refugees);

            System.out.println("starting application...");
            ArrayList<WorldBankIndicators> indicators = ParseWorldBankIndicators.parseFile();
            System.out.println("parsing complete");
            boolean success2 = db.writeToDbWorld(indicators);
        }

        ResultSet result = db.readDataBase(getQuery1());
        ArrayList<Query> query1ob = db.query1ToPojos(result);
        System.out.println("query 1 objects created: " + query1ob.size());

        ResultSet result2 = db.readDataBase(getQuery2());
        ArrayList<Query> query2ob = db.query2ToPojos(result2);
        System.out.println("query 2 objects created: " + query2ob.size());


    }

    private static String getWorldQuery(){
        return "insert into world_bank_tables " +
                "(Series_Name, Seires_Code, Country_Name, Country_Code, y2000, y2001, y2002, y2003, y2004, y2005, y2006, " +
                "y2007, y2008, y2009, y2010, y2011, y2012, y2013, y2014, y2015) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    private static String getQuery1(){
        return "select r.origin as origin, sum(r.refugees) as refugees, avg(w.y2002) as gdp " +
                "from world_bank.world_bank_tables w " +
                "join immigrants.refugees_all r " +
                "on w.Country_Name = r.origin " +
                "where Series_Name = 'GDP per capita (current US$)' " +
                "group by origin " +
                "order by refugees desc;";
    }

    public static String getQuery2(){
        return "SELECT Series_Name, Country_Name, y2000 " +
                "from world_bank.world_bank_tables " +
                "where Series_Name = 'GDP (current US$)' " +
                "order by y2000;";
    }

    public static String getCustomQuery(String country){
        return "select sum(refugees) as sumOfRefugees, country_territory " +
                "from immigrants.refugees_all " +
                "where country_territory = '"+ country+"'";
    }
}
