import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class NotEndangeredAnimalTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void animal_instantiatesCorrectly_false() {
    NotEndangeredAnimal testNotEndangeredAnimal = new NotEndangeredAnimal("Deer", "sick","12" );
    assertEquals(true, testNotEndangeredAnimal instanceof NotEndangeredAnimal);
  }

  @Test
  public void getName_animalInstantiatesWithName_Deer() {
    NotEndangeredAnimal testNotEndangeredAnimal = new NotEndangeredAnimal("Deer", "sick","12" );
    assertEquals("Deer",testNotEndangeredAnimal.getName());
  }

  @Test
  public void equals_returnsTrueIfNameIsTheSame_false() {
    NotEndangeredAnimal firstNotEndangeredAnimal = new NotEndangeredAnimal("Deer", "sick","12" );
    NotEndangeredAnimal anotherNotEndangeredAnimal = new NotEndangeredAnimal("Deer", "sick","12" );
    assertTrue(firstNotEndangeredAnimal.equals(anotherNotEndangeredAnimal));
  }

  @Test
  public void save_assignsIdToObjectAndSavesObjectToDatabase() {
    NotEndangeredAnimal testNotEndangeredAnimal = new NotEndangeredAnimal("Deer", "sick","15" );
    testNotEndangeredAnimal.save();
    NotEndangeredAnimal savedNotEndangeredAnimal = NotEndangeredAnimal.all().get(0);
    assertEquals(testNotEndangeredAnimal.getId(), savedNotEndangeredAnimal.getId());
  }

  @Test
  public void all_returnsAllInstancesOfNotEndangeredAnimal_false() {
    NotEndangeredAnimal firstNotEndangeredAnimal = new NotEndangeredAnimal("Deer", "sick","12" );
    firstNotEndangeredAnimal.save();
    NotEndangeredAnimal secondNotEndangeredAnimal = new NotEndangeredAnimal("Black Bear",
    "ill", "15");
    secondNotEndangeredAnimal.save();
    assertEquals(true, NotEndangeredAnimal.all().get(0).equals(firstNotEndangeredAnimal));
    assertEquals(true, NotEndangeredAnimal.all().get(1).equals(secondNotEndangeredAnimal));
  }

  @Test
  public void find_returnsNotEndangeredAnimalWithSameId_secondNotEndangeredAnimal() {
    NotEndangeredAnimal firstNotEndangeredAnimal = new NotEndangeredAnimal("Deer", "sick", "12");
    firstNotEndangeredAnimal.save();
    NotEndangeredAnimal secondNotEndangeredAnimal = new NotEndangeredAnimal("Black Bear", "ill", "50");
    secondNotEndangeredAnimal.save();
    assertEquals(NotEndangeredAnimal.find(secondNotEndangeredAnimal.getId()), secondNotEndangeredAnimal);
  }

  @Test
  public void delete_deletesNotEndangeredAnimalFromDatabase_0() {
    NotEndangeredAnimal testNotEndangeredAnimal = new NotEndangeredAnimal("Deer", "sick","12" );
    testNotEndangeredAnimal.save();
    testNotEndangeredAnimal.delete();
    assertEquals(0, NotEndangeredAnimal.all().size());
  }

  public void updateName_updatesNotEndangeredAnimalNameInDatabase_String() {
    NotEndangeredAnimal testNotEndangeredAnimal = new NotEndangeredAnimal("Deer", "sick", "12");
    testNotEndangeredAnimal.save();
    testNotEndangeredAnimal.updateName("Buck");
    assertEquals("Buck", testNotEndangeredAnimal.getName());
  }

  @Test
  public void find_returnsNullWhenNoNotEndangeredAnimalFound_null() {
    assertTrue(NotEndangeredAnimal.find(999) == null);
  }

}
