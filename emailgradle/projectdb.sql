--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: beds; Type: TABLE; Schema: public; Owner: Vimalan
--

CREATE TABLE public.beds (
    availability boolean,
    id integer NOT NULL,
    bed_id integer NOT NULL,
    patient_id character varying[],
    doctor_id character varying[]
);



--
-- Name: beds_id_seq; Type: SEQUENCE; Schema: public; Owner: Vimalan
--

CREATE SEQUENCE public.beds_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: beds_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Vimalan
--

ALTER SEQUENCE public.beds_id_seq OWNED BY public.beds.id;


--
-- Name: doctors; Type: TABLE; Schema: public; Owner: Vimalan
--

CREATE TABLE public.doctors (
    id integer NOT NULL,
    firstname character varying(128) NOT NULL,
    lastname character varying(128) NOT NULL,
    identitynumber character varying(32) NOT NULL,
    email character varying(128) NOT NULL,
    workload character varying(10) NOT NULL,
    availability boolean
);



--
-- Name: doctors_id_seq; Type: SEQUENCE; Schema: public; Owner: Vimalan
--

CREATE SEQUENCE public.doctors_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: doctors_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Vimalan
--

ALTER SEQUENCE public.doctors_id_seq OWNED BY public.doctors.id;


--
-- Name: patients; Type: TABLE; Schema: public; Owner: Vimalan
--

CREATE TABLE public.patients (
    id integer NOT NULL,
    givenname character varying(128) NOT NULL,
    familyname character varying(128) NOT NULL,
    phonenumber character varying(32),
    identitynumber character varying(50) NOT NULL,
    age character varying(3),
    notes text,
    admit_status boolean,
    bednumber character varying(5)
);


--
-- Name: patients_id_seq; Type: SEQUENCE; Schema: public; Owner: Vimalan
--

CREATE SEQUENCE public.patients_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: patients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Vimalan
--

ALTER SEQUENCE public.patients_id_seq OWNED BY public.patients.id;


--
-- Name: beds id; Type: DEFAULT; Schema: public; Owner: Vimalan
--

ALTER TABLE ONLY public.beds ALTER COLUMN id SET DEFAULT nextval('public.beds_id_seq'::regclass);


--
-- Name: doctors id; Type: DEFAULT; Schema: public; Owner: Vimalan
--

ALTER TABLE ONLY public.doctors ALTER COLUMN id SET DEFAULT nextval('public.doctors_id_seq'::regclass);


--
-- Name: patients id; Type: DEFAULT; Schema: public; Owner: Vimalan
--

ALTER TABLE ONLY public.patients ALTER COLUMN id SET DEFAULT nextval('public.patients_id_seq'::regclass);

--
-- Name: beds_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Vimalan
--

SELECT pg_catalog.setval('public.beds_id_seq', 10, true);


--
-- Name: doctors_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Vimalan
--

SELECT pg_catalog.setval('public.doctors_id_seq', 11, true);


--
-- Name: patients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Vimalan
--

SELECT pg_catalog.setval('public.patients_id_seq', 116, true);


--
-- Name: doctors doctors_pkey; Type: CONSTRAINT; Schema: public; Owner: Vimalan
--

ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT doctors_pkey PRIMARY KEY (id);


--
-- Name: beds id; Type: CONSTRAINT; Schema: public; Owner: Vimalan
--

ALTER TABLE ONLY public.beds
    ADD CONSTRAINT id PRIMARY KEY (id);


--
-- Name: patients patients_pkey; Type: CONSTRAINT; Schema: public; Owner: Vimalan
--

ALTER TABLE ONLY public.patients
    ADD CONSTRAINT patients_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--