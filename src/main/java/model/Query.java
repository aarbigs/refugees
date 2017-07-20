package model;
import model.WorldBankIndicators;

import java.sql.*;
import java.util.ArrayList;
/**
 * Created by aaron on 7/19/17.
 */
public class Query {

    int id;
    String origin;
    int refugees;
    int credits;
    double gdp;
    double sumOfRefugees;
    String country_territory;
    String Series_name;
    String Country_Name;
    double y2000;
    double y2001;
    double y2002;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getRefugees() {
        return refugees;
    }

    public void setRefugees(int refugees) {
        this.refugees = refugees;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public double getGdp() {
        return gdp;
    }

    public void setGdp(double gdp) {
        this.gdp = gdp;
    }

    public double getSumOfRefugees() {
        return sumOfRefugees;
    }

    public void setSumOfRefugees(double sumOfRefugees) {
        this.sumOfRefugees = sumOfRefugees;
    }

    public String getCountry_territory() {
        return country_territory;
    }

    public void setCountry_territory(String country_territory) {
        this.country_territory = country_territory;
    }

    public String getSeries_name() {
        return Series_name;
    }

    public void setSeries_name(String series_name) {
        Series_name = series_name;
    }

    public String getCountry_Name() {
        return Country_Name;
    }

    public void setCountry_Name(String country_Name) {
        Country_Name = country_Name;
    }

    public double getY2000() {
        return y2000;
    }

    public void setY2000(double y2000) {
        this.y2000 = y2000;
    }

    public double getY2001() {
        return y2001;
    }

    public void setY2001(double y2001) {
        this.y2001 = y2001;
    }

    public double getY2002() {
        return y2002;
    }

    public void setY2002(double y2002) {
        this.y2002 = y2002;
    }
}
