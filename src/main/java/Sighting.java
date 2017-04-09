import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;

public class Sighting {
  private int animal_id;
  private int ranger_id;
  private int location_id;
  private int id;
  private Timestamp sightTime;

  public Sighting(int animal_id, int ranger_id, int location_id) {
    this.animal_id = animal_id;
    this.ranger_id = ranger_id;
    this.location_id = location_id;
    this.id = id;
    sightTime = new Timestamp(new Date().getTime());
  }

  public int getId() {
    return id;
  }

  public int getRangerId(){
    return ranger_id;
  }

  public int getLocationId(){
    return location_id;
  }

  public int getAnimalId() {
    return animal_id;
  }

  public String getSightTime(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT sight_time FROM sightings WHERE id=:id;";
      sightTime = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Timestamp.class);
    }
    String time = DateFormat.getDateTimeInstance().format(sightTime);
    return time;
  }

  @Override
  public boolean equals(Object otherSighting) {
    if(!(otherSighting instanceof Sighting)) {
      return false;
    } else {
      Sighting newSighting = (Sighting) otherSighting;
      return this.getAnimalId() == (newSighting.getAnimalId()) && this.getLocationId() == (newSighting.getLocationId()) && this.getRangerId() == (newSighting.getRangerId());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO sightings (animal_id, sight_time, ranger_id, location_id) VALUES (:animal_id, now(), :ranger_id, :location_id);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("animal_id", this.animal_id)
        .addParameter("ranger_id", this.ranger_id)
        .addParameter("location_id", this.location_id)
        .throwOnMappingFailure(false)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Sighting> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings;";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .executeAndFetch(Sighting.class);
    }
  }

  public static Sighting find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE id=:id;";
      Sighting sighting = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Sighting.class);
      return sighting;
    } catch (IndexOutOfBoundsException exception) {
      return null;
    }
  }


  public Ranger getRanger(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM rangers WHERE id=:id;";
      return con.createQuery(sql)
      .addParameter("id", ranger_id)
      .executeAndFetchFirst(Ranger.class);
    }
  }

  public Location getLocation(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM locations WHERE id=:id;";
      return con.createQuery(sql)
      .addParameter("id", location_id)
      .executeAndFetchFirst(Location.class);
    }
  }

}
