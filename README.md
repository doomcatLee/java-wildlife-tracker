## Wildlife Tracker


### Description

The Forest Service is considering a proposal from a timber company to clearcut a nearby forest of Douglas Fir. Before this proposal may be approved, they must complete an environmental impact study. This application was developed to allow Rangers to track wildlife sightings in the area.

### Specs


| Behavior                   | Input Example     | Output Example    |
| -------------------------- | -----------------:| -----------------:|
| Each instances of sighting adds a timestamp | none | none |


### Setup

To create the necessary databases, launch postgres, then psql, and run the following commands:

* `CREATE DATABASE wildlife_tracker;`
* `\c wildlife_tracker;`

* `CREATE TABLE not_endangered_animals (id serial PRIMARY KEY, name varchar, health varchar, age varchar);`

* `CREATE TABLE stations (id serial PRIMARY KEY, name varchar)`

* `CREATE TABLE endangered_animals (id serial PRIMARY KEY, name varchar, health varchar, age varchar);`

* `CREATE TABLE sightings (id serial PRIMARY KEY, animal_id int, sight_time timestamp, ranger_id int, location_id int);`

* `CREATE TABLE rangers (id serial PRIMARY KEY, name varchar, contact varchar, badge_number varchar);`

* `CREATE TABLE locations (id serial PRIMARY KEY, name varchar, station_id int);`

* `CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;`


### License

Copyright (c) 2017 **_MIT License_**
