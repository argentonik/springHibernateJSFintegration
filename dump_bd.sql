--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.8
-- Dumped by pg_dump version 9.6.8

-- Started on 2018-03-29 16:43:06 EEST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12427)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2199 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 188 (class 1259 OID 24946)
-- Name: coffee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.coffee (
    id bigint NOT NULL,
    disabled boolean NOT NULL,
    price real NOT NULL,
    type character varying(255) NOT NULL
);


ALTER TABLE public.coffee OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 24949)
-- Name: coffee_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.coffee_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.coffee_id_seq OWNER TO postgres;

--
-- TOC entry 2200 (class 0 OID 0)
-- Dependencies: 189
-- Name: coffee_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.coffee_id_seq OWNED BY public.coffee.id;


--
-- TOC entry 186 (class 1259 OID 24463)
-- Name: order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.order_id_seq OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 24444)
-- Name: ordercoffee_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ordercoffee_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ordercoffee_id_seq OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 24951)
-- Name: orderfull_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orderfull_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orderfull_id_seq OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 24957)
-- Name: orderfull; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orderfull (
    id bigint DEFAULT nextval('public.orderfull_id_seq'::regclass) NOT NULL,
    nameclient character varying(255),
    address character varying(255) NOT NULL,
    price real,
    date timestamp without time zone NOT NULL
);


ALTER TABLE public.orderfull OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 24862)
-- Name: orderposition_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orderposition_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orderposition_id_seq OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 24953)
-- Name: orderposition; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orderposition (
    id bigint DEFAULT nextval('public.orderposition_id_seq'::regclass) NOT NULL,
    idtype bigint NOT NULL,
    numofcups integer NOT NULL,
    idorderfull bigint
);


ALTER TABLE public.orderposition OWNER TO postgres;

--
-- TOC entry 2056 (class 2604 OID 24967)
-- Name: coffee id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coffee ALTER COLUMN id SET DEFAULT nextval('public.coffee_id_seq'::regclass);


--
-- TOC entry 2187 (class 0 OID 24946)
-- Dependencies: 188
-- Data for Name: coffee; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.coffee (id, disabled, price, type) FROM stdin;
1	t	2.5	Latte
2	t	3	Espresso\n
3	t	3.5	Double Espresso
4	t	1.5	Short Macchiato
5	t	2	Long Black
6	f	4	Affogato
\.


--
-- TOC entry 2201 (class 0 OID 0)
-- Dependencies: 189
-- Name: coffee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.coffee_id_seq', 1, false);


--
-- TOC entry 2202 (class 0 OID 0)
-- Dependencies: 186
-- Name: order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.order_id_seq', 35, true);


--
-- TOC entry 2203 (class 0 OID 0)
-- Dependencies: 185
-- Name: ordercoffee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ordercoffee_id_seq', 100, true);


--
-- TOC entry 2191 (class 0 OID 24957)
-- Dependencies: 192
-- Data for Name: orderfull; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orderfull (id, nameclient, address, price, date) FROM stdin;
39	name	address	12.5	2018-03-29 16:41:29.58
40	Имя	Адресс	6	2018-03-29 16:41:55.602
\.


--
-- TOC entry 2204 (class 0 OID 0)
-- Dependencies: 190
-- Name: orderfull_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orderfull_id_seq', 40, true);


--
-- TOC entry 2190 (class 0 OID 24953)
-- Dependencies: 191
-- Data for Name: orderposition; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orderposition (id, idtype, numofcups, idorderfull) FROM stdin;
99	2	2	39
100	1	1	39
101	5	3	39
102	2	2	40
\.


--
-- TOC entry 2205 (class 0 OID 0)
-- Dependencies: 187
-- Name: orderposition_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orderposition_id_seq', 102, true);


--
-- TOC entry 2060 (class 2606 OID 24969)
-- Name: coffee coffee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coffee
    ADD CONSTRAINT coffee_pkey PRIMARY KEY (id);


--
-- TOC entry 2064 (class 2606 OID 24971)
-- Name: orderfull orderfull_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderfull
    ADD CONSTRAINT orderfull_pkey PRIMARY KEY (id);


--
-- TOC entry 2062 (class 2606 OID 24973)
-- Name: orderposition orderposition_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderposition
    ADD CONSTRAINT orderposition_pkey PRIMARY KEY (id);


--
-- TOC entry 2066 (class 2606 OID 24984)
-- Name: orderposition orderposition_idorderfull_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderposition
    ADD CONSTRAINT orderposition_idorderfull_fkey FOREIGN KEY (idorderfull) REFERENCES public.orderfull(id);


--
-- TOC entry 2065 (class 2606 OID 24974)
-- Name: orderposition orderposition_idtype_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderposition
    ADD CONSTRAINT orderposition_idtype_fkey FOREIGN KEY (idtype) REFERENCES public.coffee(id);


-- Completed on 2018-03-29 16:43:06 EEST

--
-- PostgreSQL database dump complete
--

