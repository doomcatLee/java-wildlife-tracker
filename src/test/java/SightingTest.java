import org.junit.*;
import org.sql2o.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.DateFormat;
import java.util.Date;

public class SightingTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void sighting_instantiatesCorrectly_true() {
    NotEndangeredAnimal testNotEndangeredAnimal = new NotEndangeredAnimal("Deer", "ill", "12");
    Ranger ranger = new Ranger("Bob", "123002", "1122");
    ranger.save();
    testNotEndangeredAnimal.save();
    Location location = new Location("Portland");
    location.save();
    Sighting testSighting = new Sighting(testNotEndangeredAnimal.getId(), ranger.getId(), location.getId(), "Ranger Avery");
    assertEquals(true, testSighting instanceof Sighting);
  }

  @Test
  public void equals_returnsTrueIfLocationAndDescriptionAreSame_true() {
    NotEndangeredAnimal testNotEndangeredAnimal = new NotEndangeredAnimal("Deer", "ill", "12");
    testNotEndangeredAnimal.save();
    Ranger ranger = new Ranger("Bob", "123002", "1122");
    ranger.save();
    Location location = new Location("Portland");
    location.save();
    Sighting testSighting = new Sighting(testNotEndangeredAnimal.getId(), ranger.getId(), location.getId(), "Ranger Avery");
    Sighting anotherSighting = new Sighting(testNotEndangeredAnimal.getId(), ranger.getId(), location.getId(), "Ranger Avery");
    assertTrue(testSighting.equals(anotherSighting));
  }

  @Test
  public void save_insertsObjectIntoDatabase_Sighting() {
    NotEndangeredAnimal testNotEndangeredAnimal = new NotEndangeredAnimal("Deer", "ill", "12");
    testNotEndangeredAnimal.save();
    Ranger ranger = new Ranger("Bob", "123002", "1122");
    ranger.save();
    Location location = new Location("Portland");
    location.save();
    Sighting testSighting = new Sighting (testNotEndangeredAnimal.getId(), ranger.getId() ,location.getId(), "Ranger Avery");
    testSighting.save();
    assertEquals(true, Sighting.all().get(0).equals(testSighting));
  }

  @Test
  public void all_returnsAllInstancesOfSighting_true() {
    NotEndangeredAnimal testNotEndangeredAnimal = new NotEndangeredAnimal("Deer", "ill", "12");
    testNotEndangeredAnimal.save();
    Ranger ranger = new Ranger("Bob", "123002", "1122");
    ranger.save();
    Location location = new Location("Portland");
    location.save();
    Sighting testSighting = new Sighting (testNotEndangeredAnimal.getId(), ranger.getId(), location.getId(), "Ranger Avery");
    testSighting.save();
    NotEndangeredAnimal secondTestNotEndangeredAnimal = new NotEndangeredAnimal("Badger", "ill", "14");
    secondTestNotEndangeredAnimal.save();
    Sighting secondTestSighting = new Sighting (testNotEndangeredAnimal.getId(), ranger.getId(), location.getId(), "Ranger Reese");
    secondTestSighting.save();
    assertEquals(true, Sighting.all().get(0).equals(testSighting));
    assertEquals(true, Sighting.all().get(1).equals(secondTestSighting));
  }

  @Test
  public void find_returnsSightingWithSameId_secondSighting() {
    NotEndangeredAnimal testNotEndangeredAnimal = new NotEndangeredAnimal("Deer", "ill", "12");
    testNotEndangeredAnimal.save();
    Ranger ranger = new Ranger("Bob", "123002", "1122");
    ranger.save();
    Location location = new Location("Portland");
    location.save();
    Sighting testSighting = new Sighting (testNotEndangeredAnimal.getId(), ranger.getId(), location.getId(), "Ranger Avery");
    testSighting.save();
    NotEndangeredAnimal secondTestNotEndangeredAnimal = new NotEndangeredAnimal("Badger", "sick", "22");
    secondTestNotEndangeredAnimal.save();
    Sighting secondTestSighting = new Sighting (testNotEndangeredAnimal.getId(), ranger.getId(), location.getId(), "Ranger Reese");
    secondTestSighting.save();
    assertEquals(Sighting.find(secondTestSighting.getId()), secondTestSighting);
  }

  @Test
  public void find_returnsNullWhenNoNotEndangeredAnimalFound_null() {
    assertTrue(NotEndangeredAnimal.find(999) == null);
  }



}
