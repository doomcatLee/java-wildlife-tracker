import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class NotEndangeredAnimal extends Animal{

  public NotEndangeredAnimal(String name, String health, String age) {
    this.name = name;
    this.id = id;
    this.health = health;
    this.age = age;
  }

  @Override
  public boolean equals(Object otherNotEndangeredAnimal) {
    if(!(otherNotEndangeredAnimal instanceof NotEndangeredAnimal)) {
      return false;
    } else {
      NotEndangeredAnimal newNotEndangeredAnimal = (NotEndangeredAnimal) otherNotEndangeredAnimal;
      return this.getName().equals(newNotEndangeredAnimal.getName());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO not_endangered_animals (name, health, age) VALUES (:name, :health, :age);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("health", this.health)
        .addParameter("age", this.age)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<NotEndangeredAnimal> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM not_endangered_animals;";
      return con.createQuery(sql)
        .executeAndFetch(NotEndangeredAnimal.class);
    }
  }

  public static NotEndangeredAnimal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM not_endangered_animals WHERE id=:id;";
      NotEndangeredAnimal animal = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(NotEndangeredAnimal.class);
      return animal;
    }
  }

  public void updateName(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE not_endangered_animals SET name=:name WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("name", name)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM not_endangered_animals WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public List<Sighting> getSightings() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE animal_id=:id;";
        List<Sighting> sightings = con.createQuery(sql)
          .throwOnMappingFailure(false)
          .addParameter("id", id)
          .executeAndFetch(Sighting.class);
      return sightings;
    }
  }

}
