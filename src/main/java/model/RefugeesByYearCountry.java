package model;

/**
 * Created by aaron on 7/18/17.
 */
public class RefugeesByYearCountry {

    int id;
    int Year;
    String country_territory;
    String origin;
    double refugees;
    double asylum_seekers;
    double returnt_refugees;
    double internally_displaced_persons;
    double returned_idps;
    double stateless_persons;
    double others_of_concern;
    double total_population;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getCountry_territory() {
        return country_territory;
    }

    public void setCountry_territory(String country_territory) {
        this.country_territory = country_territory;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getRefugees() {
        return refugees;
    }

    public void setRefugees(double refugees) {
        this.refugees = refugees;
    }

    public double getAsylum_seekers() {
        return asylum_seekers;
    }

    public void setAsylum_seekers(double asylum_seekers) {
        this.asylum_seekers = asylum_seekers;
    }

    public double getReturnt_refugees() {
        return returnt_refugees;
    }

    public void setReturnt_refugees(double returnt_refugees) {
        this.returnt_refugees = returnt_refugees;
    }

    public double getInternally_displaced_persons() {
        return internally_displaced_persons;
    }

    public void setInternally_displaced_persons(double internally_displaced_persons) {
        this.internally_displaced_persons = internally_displaced_persons;
    }

    public double getReturned_idps() {
        return returned_idps;
    }

    public void setReturned_idps(double returned_idps) {
        this.returned_idps = returned_idps;
    }

    public double getStateless_persons() {
        return stateless_persons;
    }

    public void setStateless_persons(double stateless_persons) {
        this.stateless_persons = stateless_persons;
    }

    public double getOthers_of_concern() {
        return others_of_concern;
    }

    public void setOthers_of_concern(double others_of_concern) {
        this.others_of_concern = others_of_concern;
    }

    public double getTotal_population() {
        return total_population;
    }

    public void setTotal_population(double total_population) {
        this.total_population = total_population;
    }
}
