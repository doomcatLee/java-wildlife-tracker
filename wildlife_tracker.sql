--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: endangered_animals; Type: TABLE; Schema: public; Owner: doomcat
--

CREATE TABLE endangered_animals (
    id integer NOT NULL,
    name character varying,
    health character varying,
    age character varying
);


ALTER TABLE endangered_animals OWNER TO doomcat;

--
-- Name: endangered_animals_id_seq; Type: SEQUENCE; Schema: public; Owner: doomcat
--

CREATE SEQUENCE endangered_animals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE endangered_animals_id_seq OWNER TO doomcat;

--
-- Name: endangered_animals_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: doomcat
--

ALTER SEQUENCE endangered_animals_id_seq OWNED BY endangered_animals.id;


--
-- Name: locations; Type: TABLE; Schema: public; Owner: doomcat
--

CREATE TABLE locations (
    id integer NOT NULL,
    name character varying,
    station_id integer
);


ALTER TABLE locations OWNER TO doomcat;

--
-- Name: locations_id_seq; Type: SEQUENCE; Schema: public; Owner: doomcat
--

CREATE SEQUENCE locations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE locations_id_seq OWNER TO doomcat;

--
-- Name: locations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: doomcat
--

ALTER SEQUENCE locations_id_seq OWNED BY locations.id;


--
-- Name: not_endangered_animals; Type: TABLE; Schema: public; Owner: doomcat
--

CREATE TABLE not_endangered_animals (
    id integer NOT NULL,
    name character varying,
    health character varying,
    age character varying
);


ALTER TABLE not_endangered_animals OWNER TO doomcat;

--
-- Name: not_endangered_animals_id_seq; Type: SEQUENCE; Schema: public; Owner: doomcat
--

CREATE SEQUENCE not_endangered_animals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE not_endangered_animals_id_seq OWNER TO doomcat;

--
-- Name: not_endangered_animals_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: doomcat
--

ALTER SEQUENCE not_endangered_animals_id_seq OWNED BY not_endangered_animals.id;


--
-- Name: rangers; Type: TABLE; Schema: public; Owner: doomcat
--

CREATE TABLE rangers (
    id integer NOT NULL,
    name character varying,
    contact character varying,
    badge_number character varying
);


ALTER TABLE rangers OWNER TO doomcat;

--
-- Name: rangers_id_seq; Type: SEQUENCE; Schema: public; Owner: doomcat
--

CREATE SEQUENCE rangers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE rangers_id_seq OWNER TO doomcat;

--
-- Name: rangers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: doomcat
--

ALTER SEQUENCE rangers_id_seq OWNED BY rangers.id;


--
-- Name: sightings; Type: TABLE; Schema: public; Owner: doomcat
--

CREATE TABLE sightings (
    id integer NOT NULL,
    animal_id integer,
    sight_time timestamp without time zone,
    ranger_id integer,
    location_id integer
);


ALTER TABLE sightings OWNER TO doomcat;

--
-- Name: sightings_id_seq; Type: SEQUENCE; Schema: public; Owner: doomcat
--

CREATE SEQUENCE sightings_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sightings_id_seq OWNER TO doomcat;

--
-- Name: sightings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: doomcat
--

ALTER SEQUENCE sightings_id_seq OWNED BY sightings.id;


--
-- Name: stations; Type: TABLE; Schema: public; Owner: doomcat
--

CREATE TABLE stations (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE stations OWNER TO doomcat;

--
-- Name: stations_id_seq; Type: SEQUENCE; Schema: public; Owner: doomcat
--

CREATE SEQUENCE stations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stations_id_seq OWNER TO doomcat;

--
-- Name: stations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: doomcat
--

ALTER SEQUENCE stations_id_seq OWNED BY stations.id;


--
-- Name: endangered_animals id; Type: DEFAULT; Schema: public; Owner: doomcat
--

ALTER TABLE ONLY endangered_animals ALTER COLUMN id SET DEFAULT nextval('endangered_animals_id_seq'::regclass);


--
-- Name: locations id; Type: DEFAULT; Schema: public; Owner: doomcat
--

ALTER TABLE ONLY locations ALTER COLUMN id SET DEFAULT nextval('locations_id_seq'::regclass);


--
-- Name: not_endangered_animals id; Type: DEFAULT; Schema: public; Owner: doomcat
--

ALTER TABLE ONLY not_endangered_animals ALTER COLUMN id SET DEFAULT nextval('not_endangered_animals_id_seq'::regclass);


--
-- Name: rangers id; Type: DEFAULT; Schema: public; Owner: doomcat
--

ALTER TABLE ONLY rangers ALTER COLUMN id SET DEFAULT nextval('rangers_id_seq'::regclass);


--
-- Name: sightings id; Type: DEFAULT; Schema: public; Owner: doomcat
--

ALTER TABLE ONLY sightings ALTER COLUMN id SET DEFAULT nextval('sightings_id_seq'::regclass);


--
-- Name: stations id; Type: DEFAULT; Schema: public; Owner: doomcat
--

ALTER TABLE ONLY stations ALTER COLUMN id SET DEFAULT nextval('stations_id_seq'::regclass);


--
-- Data for Name: endangered_animals; Type: TABLE DATA; Schema: public; Owner: doomcat
--

COPY endangered_animals (id, name, health, age) FROM stdin;
1	thehunkle	Ill	Newborn
\.


--
-- Name: endangered_animals_id_seq; Type: SEQUENCE SET; Schema: public; Owner: doomcat
--

SELECT pg_catalog.setval('endangered_animals_id_seq', 1, true);


--
-- Data for Name: locations; Type: TABLE DATA; Schema: public; Owner: doomcat
--

COPY locations (id, name, station_id) FROM stdin;
1	HAPPY VALLEY	0
2	Eugene	0
\.


--
-- Name: locations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: doomcat
--

SELECT pg_catalog.setval('locations_id_seq', 2, true);


--
-- Data for Name: not_endangered_animals; Type: TABLE DATA; Schema: public; Owner: doomcat
--

COPY not_endangered_animals (id, name, health, age) FROM stdin;
1	Dog	Ill	Newborn
\.


--
-- Name: not_endangered_animals_id_seq; Type: SEQUENCE SET; Schema: public; Owner: doomcat
--

SELECT pg_catalog.setval('not_endangered_animals_id_seq', 1, true);


--
-- Data for Name: rangers; Type: TABLE DATA; Schema: public; Owner: doomcat
--

COPY rangers (id, name, contact, badge_number) FROM stdin;
1	Alex	5033808555	123
2	Kim	5039983176	1132
\.


--
-- Name: rangers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: doomcat
--

SELECT pg_catalog.setval('rangers_id_seq', 2, true);


--
-- Data for Name: sightings; Type: TABLE DATA; Schema: public; Owner: doomcat
--

COPY sightings (id, animal_id, sight_time, ranger_id, location_id) FROM stdin;
1	1	2017-04-08 19:21:26.232262	1	1
2	1	2017-04-08 19:21:39.940153	2	2
\.


--
-- Name: sightings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: doomcat
--

SELECT pg_catalog.setval('sightings_id_seq', 2, true);


--
-- Data for Name: stations; Type: TABLE DATA; Schema: public; Owner: doomcat
--

COPY stations (id, name) FROM stdin;
\.


--
-- Name: stations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: doomcat
--

SELECT pg_catalog.setval('stations_id_seq', 1, false);


--
-- Name: endangered_animals endangered_animals_pkey; Type: CONSTRAINT; Schema: public; Owner: doomcat
--

ALTER TABLE ONLY endangered_animals
    ADD CONSTRAINT endangered_animals_pkey PRIMARY KEY (id);


--
-- Name: locations locations_pkey; Type: CONSTRAINT; Schema: public; Owner: doomcat
--

ALTER TABLE ONLY locations
    ADD CONSTRAINT locations_pkey PRIMARY KEY (id);


--
-- Name: not_endangered_animals not_endangered_animals_pkey; Type: CONSTRAINT; Schema: public; Owner: doomcat
--

ALTER TABLE ONLY not_endangered_animals
    ADD CONSTRAINT not_endangered_animals_pkey PRIMARY KEY (id);


--
-- Name: rangers rangers_pkey; Type: CONSTRAINT; Schema: public; Owner: doomcat
--

ALTER TABLE ONLY rangers
    ADD CONSTRAINT rangers_pkey PRIMARY KEY (id);


--
-- Name: sightings sightings_pkey; Type: CONSTRAINT; Schema: public; Owner: doomcat
--

ALTER TABLE ONLY sightings
    ADD CONSTRAINT sightings_pkey PRIMARY KEY (id);


--
-- Name: stations stations_pkey; Type: CONSTRAINT; Schema: public; Owner: doomcat
--

ALTER TABLE ONLY stations
    ADD CONSTRAINT stations_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

