import org.junit.*;
import org.sql2o.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.DateFormat;
import java.util.Date;


public class RangerTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void ranger_instantiates_correctly(){
    Ranger ranger = new Ranger("Alex", "5039938293", "1143");
    assertTrue(ranger instanceof Ranger);
  }

  @Test
  public void ranger_save_all_find_works(){
    Ranger ranger = new Ranger("Alex", "5039938293", "1143");
    ranger.save();
    assertEquals(true, ranger.all() instanceof List<?>);
    assertEquals("Alex", ranger.all().get(0).getName());
  }


}
