import org.junit.*;
import org.sql2o.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.DateFormat;
import java.util.Date;


public class LocationTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void location_instantiates_correctly(){
    Location location = new Location("Shanghai");
    assertTrue(location instanceof Location);
  }

  @Test
  public void location_save_and_all_works(){
    Location location = new Location("Gresham");
    location.save();
    assertEquals(true, location.all() instanceof List<?>);
    assertEquals("Portland", location.all().get(0).getName());
  }

  @Test
  public void location_find_works(){
    Location location = new Location("Portland");
    location.save();
    assertEquals(true, Location.find(location.getId()) instanceof Location);
  }


}
