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
-- Name: doctors; Type: TABLE; Schema: public; Owner: -
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
-- Name: doctors_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.doctors_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: doctors_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.doctors_id_seq OWNED BY public.doctors.id;


--
-- Name: patients; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.patients (
    id integer NOT NULL,
    givenname character varying(128) NOT NULL,
    familyname character varying(128) NOT NULL,
    phonenumber character varying(32),
    identitynumber character varying(50) NOT NULL,
    age character varying(3),
    notes text,
    admit_status boolean
);


--
-- Name: patients_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.patients_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: patients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.patients_id_seq OWNED BY public.patients.id;


--
-- Name: doctors id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.doctors ALTER COLUMN id SET DEFAULT nextval('public.doctors_id_seq'::regclass);


--
-- Name: patients id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.patients ALTER COLUMN id SET DEFAULT nextval('public.patients_id_seq'::regclass);


--
-- Data for Name: doctors; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.doctors (id, firstname, lastname, identitynumber, email, workload, availability) FROM stdin;
1	Derrick	Chan	12-1243-124-65	derrickchan@hospital.co.uk	5	t
2	Kate	Blanchett	21-234-2564-10	kateblanchett@hospital.co.uk	6	t
3	Julia	Kranski	12-4313-102-21	juliakranski@hospital.co.uk	8	t
4	Kelly 	Rinde	12-45322-102-12	kellyrinde@hospital.co.uk	5	t
\.


--
-- Data for Name: patients; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.patients (id, givenname, familyname, phonenumber, identitynumber, age, notes, admit_status) FROM stdin;
1	Vimalan	Vijayasekaran	07490201064	980517105507	21	Pain in stomach	t
2	Wen Li	Zhou	0749509954	343567764	20	Lactose Intolerance	t
3	Joe	Smith	0759304923	5483412043	23	Pain	t
4	John	Paul	07596403854	345312454	33	Accident	t
5	Andrew	Pete	07234503243	24212453	45	Poison	t
6	Josh	Peters	07495038233	534234124	23	Vomitting	\N
7	Micheal	Kool	07584502312	22321214	45	Heart Attack	\N
8	Russell	Peters	0749020845	12342564332	43	Accident	\N
9	Rizqin	Naim	0759384053	7236592143	22	Pain in arm	\N
\.


--
-- Name: doctors_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.doctors_id_seq', 4, true);


--
-- Name: patients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.patients_id_seq', 9, true);


--
-- Name: doctors doctors_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT doctors_pkey PRIMARY KEY (id);


--
-- Name: patients patients_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.patients
    ADD CONSTRAINT patients_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

