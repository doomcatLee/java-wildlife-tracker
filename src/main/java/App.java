import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/dashboard", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("rangers", Ranger.all());
      model.put("stations", Station.all());
      model.put("NotEndangeredAnimal", NotEndangeredAnimal.all());
      model.put("endangeredAnimals", EndangeredAnimal.all());
      // model.put("stations", Station.all());
      model.put("template", "templates/dashboard.vtl");
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());

    get("/register", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/register.vtl");
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());

    post("/endangered_sighting", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String rangerName = request.queryParams("rangerName");
      String rangerContact = request.queryParams("contactNumber");
      String rangerBadge = request.queryParams("badgeNumber");

      Ranger ranger = new Ranger(rangerName, rangerContact, rangerBadge);
      int rangerId = ranger.getId();

      int animalIdSelected = Integer.parseInt(request.queryParams("endangeredAnimalSelected"));
      String locationName = request.queryParams("locationName");

      Location location = new Location(locationName);
      ranger.save();
      location.save();

      Sighting sighting = new Sighting(animalIdSelected, rangerId, location.getId());
      sighting.save();
      model.put("sighting", sighting);
      model.put("animals", EndangeredAnimal.all());
      String animal = EndangeredAnimal.find(animalIdSelected).getName();
      model.put("animal", animal);
      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/sighting", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String rangerName = request.queryParams("rangerName");
      String rangerContact = request.queryParams("contactNumber");
      String rangerBadge = request.queryParams("badgeNumber");

      String locationName = request.queryParams("locationName");
      Location location = new Location(locationName);

      Ranger ranger = new Ranger(rangerName, rangerContact, rangerBadge);

      //GRAB ANIMAL FROM SELECTBOX
      int animalIdSelected = Integer.parseInt(request.queryParams("animalSelected"));

      ranger.save();
      location.save();

      Sighting sighting = new Sighting(animalIdSelected, ranger.getId(), location.getId());
      sighting.save();

      model.put("sighting", sighting);
      model.put("animals", NotEndangeredAnimal.all());
      String animal = NotEndangeredAnimal.find(animalIdSelected).getName();
      model.put("animal", animal);
      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animal/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animals", NotEndangeredAnimal.all());
      model.put("endangeredAnimals", EndangeredAnimal.all());
      model.put("template", "templates/animal-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animal/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      boolean endangered = request.queryParamsValues("endangered")!=null;
      if (endangered) {
        String name = request.queryParams("name");
        String health = request.queryParams("health");
        String age = request.queryParams("age");
        EndangeredAnimal endangeredAnimal = new EndangeredAnimal(name, health, age);
        endangeredAnimal.save();
        model.put("animals", NotEndangeredAnimal.all());
        model.put("endangeredAnimals", EndangeredAnimal.all());
      } else {
        String name = request.queryParams("name");
        NotEndangeredAnimal animal = new NotEndangeredAnimal(name, "ill", "helc");
        animal.save();
        model.put("animals", NotEndangeredAnimal.all());
        model.put("endangeredAnimals", EndangeredAnimal.all());
      }
      response.redirect("/dashboard");
        return null;
      });

    get("/animal/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      NotEndangeredAnimal animal = NotEndangeredAnimal.find(Integer.parseInt(request.params("id")));
      model.put("animal", animal);
      model.put("template", "templates/animal.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/endangered_animal/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      EndangeredAnimal endangeredAnimal = EndangeredAnimal.find(Integer.parseInt(request.params("id")));
      model.put("endangeredAnimal", endangeredAnimal);
      model.put("template", "templates/endangered_animal.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/error", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/error.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/ranger/:id" ,(request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Ranger ranger = Ranger.find(Integer.parseInt(request.params("id")));
      model.put("ranger", ranger);
      model.put("template", "templates/ranger.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/sighting-form", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/sighting-form.vtl");
      model.put("endangeredAnimals", EndangeredAnimal.all());
      model.put("animals", NotEndangeredAnimal.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/station/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("locations", Location.all());
      model.put("template", "templates/station-form.vtl");
      return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

    post("/station", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String stationName = request.queryParams("stationName");
      Station station = new Station(stationName);
      station.save();
      Location location = Location.find(station.getId());
      location.setStationId(station.getId());
      location.updateStationId();
      response.redirect("/dashboard");
        return null;
    });

  }
}
