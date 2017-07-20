package Controller;

import model.Query;
import model.RefugeesByYearCountry;
import model.WorldBankIndicators;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by aaron on 7/18/17.
 */
public class DBAccess {

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public boolean writeToDb(ArrayList<RefugeesByYearCountry> refugees){
            try {
                connection = getConnection();

                preparedStatement = connection
                        .prepareStatement("insert into refugees_all " +
                            "(Year, country_territory, origin, refugees, asylum_seekers, returnt_refugees, internally_displaced_persons, returned_idps, stateless_persons, others_of_concern, total_population) " +
                            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                for (int i = 0; i < refugees.size(); i++){
                    // Parameters start with 1
                    //preparedStatement.setInt(1, refugees.get(i).getId());
                    preparedStatement.setInt(1, refugees.get(i).getYear());
                    preparedStatement.setString(2, refugees.get(i).getCountry_territory());
                    preparedStatement.setString(3, refugees.get(i).getOrigin());
                    preparedStatement.setDouble(4, refugees.get(i).getRefugees());
                    preparedStatement.setDouble(5, refugees.get(i).getAsylum_seekers());
                    preparedStatement.setDouble(6, refugees.get(i).getReturnt_refugees());
                    preparedStatement.setDouble(7, refugees.get(i).getInternally_displaced_persons());
                    preparedStatement.setDouble(8, refugees.get(i).getReturned_idps());
                    preparedStatement.setDouble(9, refugees.get(i).getStateless_persons());
                    preparedStatement.setDouble(10, refugees.get(i).getOthers_of_concern());
                    preparedStatement.setDouble(11, refugees.get(i).getTotal_population());

                    preparedStatement.executeUpdate();
                    System.out.println("record # " + i + " inserted in refugees_all table");
                }

            } catch (Exception e){
                return false;
            }
            System.out.println("all done");
            return true;
    }

    public boolean writeToDbWorld(ArrayList<WorldBankIndicators> indicators){
        try {

            connection = getConnection();

            preparedStatement = connection.prepareStatement("insert into world_bank_tables " +
                            "(Series_Name, Seires_Code, Country_Name, Country_Code, y2000, y2001, y2002, y2003, y2004, y2005, y2006, " +
                    "y2007, y2008, y2009, y2010, y2011, y2012, y2013, y2014, y2015) " +
                            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            for (int i = 0; i < indicators.size(); i++){
                // Parameters start with 1
                //preparedStatement.setInt(1, refugees.get(i).getId());
                preparedStatement.setString(1, indicators.get(i).getSeries_Name());
                preparedStatement.setString(2, indicators.get(i).getSeires_Code());
                preparedStatement.setString(3, indicators.get(i).getCountry_Name());
                preparedStatement.setString(4, indicators.get(i).getCountry_Code());
                preparedStatement.setDouble(5, indicators.get(i).getYr2000());
                preparedStatement.setDouble(6, indicators.get(i).getYr2001());
                preparedStatement.setDouble(7, indicators.get(i).getYr2002());
                preparedStatement.setDouble(8, indicators.get(i).getYr2003());
                preparedStatement.setDouble(9, indicators.get(i).getYr2004());
                preparedStatement.setDouble(10, indicators.get(i).getYr2005());
                preparedStatement.setDouble(11, indicators.get(i).getYr2006());
                preparedStatement.setDouble(12, indicators.get(i).getYr2007());
                preparedStatement.setDouble(13, indicators.get(i).getYr2008());
                preparedStatement.setDouble(14, indicators.get(i).getYr2009());
                preparedStatement.setDouble(15, indicators.get(i).getYr2010());
                preparedStatement.setDouble(16, indicators.get(i).getYr2011());
                preparedStatement.setDouble(17, indicators.get(i).getYr2012());
                preparedStatement.setDouble(18, indicators.get(i).getYr2013());
                preparedStatement.setDouble(19, indicators.get(i).getYr2014());
                preparedStatement.setDouble(20, indicators.get(i).getYr2015());

                preparedStatement.executeUpdate();
                System.out.println("record # " + i + " inserted in world bank tables");
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
        System.out.println("all done");
        return true;
    }

    public Connection getConnection() {

        try{
            System.out.println("creating connection");
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connection = DriverManager.getConnection("jdbc:mysql://localhost/world_bank?" +
                    "user=root&password=?&useSSL=false");
            System.out.println("connection succeeded");
        } catch (ClassNotFoundException cnf){
            cnf.printStackTrace();
        } catch (SQLException se){
            se.printStackTrace();
        }
        return connection;
    }

    public static ArrayList<Query> query1ToPojos(ResultSet resultSet) {

        ArrayList<Query> indics = new ArrayList<>();

        try {
            while (resultSet.next()) {

                Query wbi = new Query();
                wbi.setOrigin(resultSet.getString("origin"));
                wbi.setRefugees(resultSet.getInt("refugees"));
                wbi.setGdp(resultSet.getDouble("gdp"));

                WorldBankIndicators wb = new WorldBankIndicators();

                indics.add(wbi);
            }
        }
        catch(SQLException e){

        }
        return indics;
    }

    public static ArrayList<Query> query2ToPojos(ResultSet resultSet) {

        ArrayList<Query> indics = new ArrayList<>();

        try {
            while (resultSet.next()) {

                Query wbi = new Query();
                wbi.setSeries_name(resultSet.getString("Series_Name"));
                wbi.setCountry_Name(resultSet.getString("Country_Name"));
                wbi.setY2000(resultSet.getDouble("y2000"));

                indics.add(wbi);
            }
        }
        catch(SQLException e){

        }
        return indics;
    }
    public static ArrayList<Query> getCustomQuery(ResultSet resultSet) {
        ArrayList<Query> indic = new ArrayList<>();
        try {
            while (resultSet.next()) {

                Query wb = new Query();
                wb.setSumOfRefugees(resultSet.getDouble("sumOfRefugees"));
                wb.setCountry_territory(resultSet.getString("country_territory"));

                indic.add(wb);
            }
        } catch(SQLException s){

        }
        return indic;
    }

    public ResultSet readDataBase(String query){
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            System.out.println("Signing into database...");
            connection = DriverManager.getConnection("jdbc:mysql://localhost?" +
                    "user=root&password=?&useSSL=false");

            // Statements allow to issue SQL queries to the database
            statement = connection.createStatement();

            // Result set get the result of the SQL query
            resultSet = statement.executeQuery(query);

            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }
}
