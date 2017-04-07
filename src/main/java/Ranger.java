import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.DateFormat;
import java.util.Date;

public class Ranger{
  private int id;
  private String name;
  private String contact;
  private int badge_number;

  public Ranger(String name, String contact, int badgeNumber){
    this.id = id;
    this.name = name;
    this.contact = contact;
    this.badge_number = badge_number;
  }

  public String getName(){
    return name;
  }

  public String getContact(){
    return contact;
  }

  public int getBadgeNumber(){
    return badge_number;
  }

  public int getId(){
    return id;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO rangers (name, contact, badge_number) VALUES (:name, :contact, :badge_number);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("contact", this.contact)
        .addParameter("badge_number", this.badge_number)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Ranger> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM rangers;";
      return con.createQuery(sql)
        .executeAndFetch(Ranger.class);
    }
  }

  public static Ranger find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM rangers WHERE id=:id;";
      Ranger endangeredanimal = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Ranger.class);
      return endangeredanimal;
    }
  }



}
