import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.DateFormat;
import java.util.Date;

public class Location{
  private int id;
  private int station_id;
  private String name;

  public Location(String name){
    this.name = name;
    this.id = id;
  }

  public int getId(){
    return id;
  }

  public int getStationId(){
    return station_id;
  }

  public String getName(){
    return name;
  }

  public void setStationId(int id){
    station_id = id;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO locations (name, station_id) VALUES (:name, :station_id);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("station_id", this.station_id)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Location> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM locations;";
      return con.createQuery(sql)
        .executeAndFetch(Location.class);
    }
  }

  public static Location find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM locations WHERE id=:id;";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Location.class);
    }
  }

  public void updateStationId(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE locations SET station_id =:station_id WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("station_id", station_id)
        .executeUpdate();
    }
  }

}
