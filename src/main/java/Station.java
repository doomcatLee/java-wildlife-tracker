import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.DateFormat;
import java.util.Date;

public class Station{
  private int id;
  private String name;

  public Station(String name){
    this.name = name;
    this.id = id;
  }

  public int getId(){
    return id;
  }

  public String getName(){
    return name;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stations (name) VALUES (:name);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Station> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stations;";
      return con.createQuery(sql)
        .executeAndFetch(Station.class);
    }
  }

  public static Station find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stations WHERE id=:id;";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Station.class);
    }
  }


}
