--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.3
-- Dumped by pg_dump version 9.2.3
-- Started on 2015-05-26 06:40:21

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 193 (class 3079 OID 11727)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2062 (class 0 OID 0)
-- Dependencies: 193
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 168 (class 1259 OID 16394)
-- Name: tbl_miembros; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbl_miembros (
    id integer NOT NULL,
    nombre text NOT NULL,
    ctacte integer NOT NULL,
    domicilio text,
    box integer
);


ALTER TABLE public.tbl_miembros OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 16539)
-- Name: tbl_pagos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbl_pagos (
    id integer NOT NULL,
    fechahora timestamp without time zone,
    id_miembro integer,
    id_remate integer,
    concepto text,
    monto integer,
    tipo integer
);


ALTER TABLE public.tbl_pagos OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 16443)
-- Name: tbl_remates_detalle; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbl_remates_detalle (
    fechahora timestamp without time zone,
    id integer NOT NULL,
    id_miembro integer NOT NULL,
    id_categoria integer NOT NULL,
    monto integer NOT NULL,
    observacion text DEFAULT ''::text,
    id_remate integer NOT NULL
);


ALTER TABLE public.tbl_remates_detalle OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 16593)
-- Name: pendientes_total; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW pendientes_total AS
    WITH remates AS (SELECT m.id, m.nombre, m.ctacte, sum(rd.monto) AS monto FROM (tbl_miembros m LEFT JOIN tbl_remates_detalle rd ON ((m.id = rd.id_miembro))) GROUP BY m.id, m.nombre, m.ctacte), pagos AS (SELECT m.id, m.nombre, m.ctacte, COALESCE(sum(p.monto), (0)::bigint) AS monto FROM (tbl_miembros m LEFT JOIN tbl_pagos p ON ((m.id = p.id_miembro))) GROUP BY m.id, m.nombre, m.ctacte) SELECT remates.nombre, remates.ctacte, remates.monto AS compras_total, pagos.monto AS pagos_total, (remates.monto - pagos.monto) AS saldo FROM remates, pagos WHERE ((remates.id = pagos.id) AND ((remates.monto - pagos.monto) <> 0)) ORDER BY remates.nombre;


ALTER TABLE public.pendientes_total OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 16558)
-- Name: recibo; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW recibo AS
    SELECT p.id, p.fechahora, d.nombre, p.concepto, p.monto FROM tbl_pagos p, tbl_miembros d WHERE ((p.id_miembro = d.id) AND (p.tipo = 1));


ALTER TABLE public.recibo OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 16410)
-- Name: tbl_remates_categorias; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbl_remates_categorias (
    id integer NOT NULL,
    descripcion text
);


ALTER TABLE public.tbl_remates_categorias OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 16416)
-- Name: tbl_categorias_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_categorias_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_categorias_id_seq OWNER TO postgres;

--
-- TOC entry 2063 (class 0 OID 0)
-- Dependencies: 171
-- Name: tbl_categorias_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_categorias_id_seq OWNED BY tbl_remates_categorias.id;


--
-- TOC entry 172 (class 1259 OID 16418)
-- Name: tbl_iglesia; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbl_iglesia (
    id integer NOT NULL,
    nombre text,
    ruc integer,
    ctacte integer,
    domicilio text,
    box integer
);


ALTER TABLE public.tbl_iglesia OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 16424)
-- Name: tbl_iglesia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_iglesia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_iglesia_id_seq OWNER TO postgres;

--
-- TOC entry 2064 (class 0 OID 0)
-- Dependencies: 173
-- Name: tbl_iglesia_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_iglesia_id_seq OWNED BY tbl_iglesia.id;


--
-- TOC entry 174 (class 1259 OID 16426)
-- Name: tbl_miembros_box_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_miembros_box_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_miembros_box_seq OWNER TO postgres;

--
-- TOC entry 2065 (class 0 OID 0)
-- Dependencies: 174
-- Name: tbl_miembros_box_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_miembros_box_seq OWNED BY tbl_miembros.box;


--
-- TOC entry 175 (class 1259 OID 16428)
-- Name: tbl_miembros_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_miembros_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_miembros_id_seq OWNER TO postgres;

--
-- TOC entry 2066 (class 0 OID 0)
-- Dependencies: 175
-- Name: tbl_miembros_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_miembros_id_seq OWNED BY tbl_miembros.id;


--
-- TOC entry 188 (class 1259 OID 16537)
-- Name: tbl_pagos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_pagos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_pagos_id_seq OWNER TO postgres;

--
-- TOC entry 2067 (class 0 OID 0)
-- Dependencies: 188
-- Name: tbl_pagos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_pagos_id_seq OWNED BY tbl_pagos.id;


--
-- TOC entry 169 (class 1259 OID 16400)
-- Name: tbl_recibos_bak; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbl_recibos_bak (
    id integer NOT NULL,
    fechahora time without time zone,
    id_miembro integer NOT NULL,
    monto integer,
    concepto text,
    id_remate integer
);


ALTER TABLE public.tbl_recibos_bak OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 16430)
-- Name: tbl_recibos_id_miembro_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_recibos_id_miembro_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_recibos_id_miembro_seq OWNER TO postgres;

--
-- TOC entry 2068 (class 0 OID 0)
-- Dependencies: 176
-- Name: tbl_recibos_id_miembro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_recibos_id_miembro_seq OWNED BY tbl_recibos_bak.id_miembro;


--
-- TOC entry 177 (class 1259 OID 16432)
-- Name: tbl_recibos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_recibos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_recibos_id_seq OWNER TO postgres;

--
-- TOC entry 2069 (class 0 OID 0)
-- Dependencies: 177
-- Name: tbl_recibos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_recibos_id_seq OWNED BY tbl_recibos_bak.id;


--
-- TOC entry 178 (class 1259 OID 16434)
-- Name: tbl_remates; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbl_remates (
    id integer NOT NULL,
    fecha timestamp without time zone,
    descripcion text
);


ALTER TABLE public.tbl_remates OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 16440)
-- Name: tbl_remates_cuotas; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbl_remates_cuotas (
    id_remate integer NOT NULL,
    fecha_1 timestamp without time zone,
    fecha_2 timestamp without time zone,
    fecha_3 timestamp without time zone,
    fecha_4 timestamp without time zone
);


ALTER TABLE public.tbl_remates_cuotas OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 16450)
-- Name: tbl_remates_id_categoria_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_remates_id_categoria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_remates_id_categoria_seq OWNER TO postgres;

--
-- TOC entry 2070 (class 0 OID 0)
-- Dependencies: 181
-- Name: tbl_remates_id_categoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_remates_id_categoria_seq OWNED BY tbl_remates_detalle.id_categoria;


--
-- TOC entry 182 (class 1259 OID 16452)
-- Name: tbl_remates_id_miembro_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_remates_id_miembro_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_remates_id_miembro_seq OWNER TO postgres;

--
-- TOC entry 2071 (class 0 OID 0)
-- Dependencies: 182
-- Name: tbl_remates_id_miembro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_remates_id_miembro_seq OWNED BY tbl_remates_detalle.id_miembro;


--
-- TOC entry 183 (class 1259 OID 16454)
-- Name: tbl_remates_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_remates_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_remates_id_seq OWNER TO postgres;

--
-- TOC entry 2072 (class 0 OID 0)
-- Dependencies: 183
-- Name: tbl_remates_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_remates_id_seq OWNED BY tbl_remates_detalle.id;


--
-- TOC entry 184 (class 1259 OID 16456)
-- Name: tbl_remates_id_seq1; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_remates_id_seq1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_remates_id_seq1 OWNER TO postgres;

--
-- TOC entry 2073 (class 0 OID 0)
-- Dependencies: 184
-- Name: tbl_remates_id_seq1; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_remates_id_seq1 OWNED BY tbl_remates.id;


--
-- TOC entry 185 (class 1259 OID 16458)
-- Name: tbl_transferencias_bak; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbl_transferencias_bak (
    fechahora timestamp without time zone NOT NULL,
    concepto text,
    monto integer NOT NULL,
    id integer NOT NULL,
    id_miembro integer NOT NULL,
    id_remate integer NOT NULL
);


ALTER TABLE public.tbl_transferencias_bak OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 16464)
-- Name: tbl_transferencias_id_miembro_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_transferencias_id_miembro_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_transferencias_id_miembro_seq OWNER TO postgres;

--
-- TOC entry 2074 (class 0 OID 0)
-- Dependencies: 186
-- Name: tbl_transferencias_id_miembro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_transferencias_id_miembro_seq OWNED BY tbl_transferencias_bak.id_miembro;


--
-- TOC entry 187 (class 1259 OID 16466)
-- Name: tbl_transferencias_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_transferencias_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_transferencias_id_seq OWNER TO postgres;

--
-- TOC entry 2075 (class 0 OID 0)
-- Dependencies: 187
-- Name: tbl_transferencias_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_transferencias_id_seq OWNED BY tbl_transferencias_bak.id;


--
-- TOC entry 191 (class 1259 OID 16562)
-- Name: transferencia; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW transferencia AS
    SELECT t.id, t.fechahora, d.ctacte AS cta_debito, i.ctacte AS cta_credito, d.nombre AS d_nombre, i.nombre AS c_nombre, d.domicilio AS d_domicilio, i.domicilio AS c_domicilio, d.box AS d_box, i.box AS c_box, t.monto, t.concepto FROM tbl_pagos t, tbl_miembros d, tbl_iglesia i WHERE ((t.id_miembro = d.id) AND (t.tipo = 0));


ALTER TABLE public.transferencia OWNER TO postgres;

--
-- TOC entry 1995 (class 2604 OID 16472)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_miembros ALTER COLUMN id SET DEFAULT nextval('tbl_miembros_id_seq'::regclass);


--
-- TOC entry 2002 (class 2604 OID 16542)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_pagos ALTER COLUMN id SET DEFAULT nextval('tbl_pagos_id_seq'::regclass);


--
-- TOC entry 1996 (class 2604 OID 16473)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_recibos_bak ALTER COLUMN id SET DEFAULT nextval('tbl_recibos_id_seq'::regclass);


--
-- TOC entry 1998 (class 2604 OID 16474)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_remates ALTER COLUMN id SET DEFAULT nextval('tbl_remates_id_seq1'::regclass);


--
-- TOC entry 1997 (class 2604 OID 16475)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_remates_categorias ALTER COLUMN id SET DEFAULT nextval('tbl_categorias_id_seq'::regclass);


--
-- TOC entry 2000 (class 2604 OID 16476)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_remates_detalle ALTER COLUMN id SET DEFAULT nextval('tbl_remates_id_seq'::regclass);


--
-- TOC entry 2001 (class 2604 OID 16477)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_transferencias_bak ALTER COLUMN id SET DEFAULT nextval('tbl_transferencias_id_seq'::regclass);


--
-- TOC entry 2076 (class 0 OID 0)
-- Dependencies: 171
-- Name: tbl_categorias_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tbl_categorias_id_seq', 11, true);


--
-- TOC entry 2037 (class 0 OID 16418)
-- Dependencies: 172
-- Data for Name: tbl_iglesia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tbl_iglesia (id, nombre, ruc, ctacte, domicilio, box) FROM stdin;
1	Asociacion Iglesia Beth El	80033423	402896	Loma Plata	312
\.


--
-- TOC entry 2077 (class 0 OID 0)
-- Dependencies: 173
-- Name: tbl_iglesia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tbl_iglesia_id_seq', 1, false);


--
-- TOC entry 2033 (class 0 OID 16394)
-- Dependencies: 168
-- Data for Name: tbl_miembros; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tbl_miembros (id, nombre, ctacte, domicilio, box) FROM stdin;
8	Bergen Toews Harold	21651	\N	522
9	Bergen Alwin	21899	\N	535
10	Bergen Erich	21907	\N	535
11	Bergen Delmer	22003	\N	492
12	Balzer Tidor	22069	\N	1102
13	Bergen Enns Albert	22230	\N	465
14	Bergen Albert	2223001	\N	\N
15	Bergen Harder Maiko	22456	\N	492
16	Braun Jeny	22735	\N	\N
17	Batista Vilson	22810	\N	1319
18	Bartel Lorenz	22843	\N	\N
19	Braatz Mario	22865	\N	\N
20	Bartel Margitta	23217	\N	1816
21	Bergen Matthias	23518	\N	\N
22	Bergen Darel	23693	\N	1740
23	Batista Gerson Matthias	23745	\N	1319
24	Braun de Doerksen Marilyn Patricia	23778	\N	1338
25	Bergen de Wiebe Jurline	26388	\N	708
26	Doerksen Katharina	40067	\N	1119
27	Doerksen Wiebe Gerhard	40175	\N	340
28	Dueck Froese Jacob	40603	\N	321
29	Dueck Maria	40722	\N	769
30	Doerksen Gerhard	40959	\N	241
31	Dueck Danny	41226	\N	\N
32	Dueck F David	41259	\N	\N
33	Doerksen Edward	41260	\N	931
34	Dueck Krahn Eldon	41431	\N	1260
35	Dueck Richard	41657	\N	573
36	Dueck Unrau Wilfried	41743	\N	898
37	Dueck Froese Jacob	41754	\N	5322
38	Doerksen Dueck Ruben	41958	\N	1644
39	Doerksen Franz	41981	\N	1213
40	Hiebert Martin	4225801	\N	\N
41	Dueck Hiebert Franz	42270	\N	1325
42	Dueck Braun Theodor	42344	\N	312
43	Dueck Braun Carlos Alberto	42407	\N	686
44	Dueck Viktor	42430	\N	1399
45	Doerksen Viktor	42689	\N	89
46	Doerksen Elma	42797	\N	1119
47	Doerksen Andreas	42850	\N	140
48	Doerksen Helmut	43008	\N	340
49	Doerksen Ferdinand	43183	\N	575
50	Dueck Alexander	43202	\N	312
51	Doerksen Thiessen Mariano	43376	\N	1338
52	Dueck Fabian	43730	\N	\N
53	Dueck Dueck Christine Amalia	44591	\N	831
54	Dueck Bergen Cliffort	44610	\N	1782
55	Dueck Kauenhowen Nikoreno	44728	\N	1003
56	Dueck Katharina	45125	\N	254
57	Doerksen de Hiebert Nancy	45170	\N	1213
58	Doerksen Bergen Charly	45233	\N	1131
59	Dueck Annemarie	45341	\N	254
60	Doerksen Franz	46049	\N	\N
61	Barros Elianda	4613501	\N	\N
62	Dueck Johann	46481	\N	\N
63	Dueck Milian Dueck Toews	47060	\N	1452
64	Dueck de Enns Norma	47093	\N	453
65	Dueck Stacey	47156	\N	1917
66	Doerksen Hedy	48230	\N	\N
67	Doerksen Hanna	48263	\N	859
68	Enns Bernhard	50026	\N	462
69	Enns Gerhard	50071	\N	77
70	Enns Hein	50093	\N	1306
71	Enns Willi	50134	\N	1350
72	Enns Wiebe Rodney	50435	\N	433
73	Enns Jason	50576	\N	\N
74	Enns Klassen Robby	50639	\N	1403
75	Enns Johan	50770	\N	453
76	Enns Heinrich	59003	\N	\N
77	Falk Gerhard	60085	\N	206
78	Martens de Falk Justina	60137	\N	275
79	Froese de Veron Adina	60223	\N	873
80	Fehr Hans	60289	\N	1248
81	Funk Andreas	60449	\N	345
82	Froese Cornelius	60944	\N	148
83	Froese Martin	61181	\N	403
84	Funk Harry	61556	\N	347
85	Falk Martens Rudi	61697	\N	275
86	Friesen Michael	61921	\N	\N
87	Froese Abram	62191	\N	474
88	Falk Dueck Wilhelm	62298	\N	\N
89	Froese Alfred	62328	\N	403
90	Froese Bernhard	62414	\N	304
91	Friesen Willi	62492	\N	327
92	Falk Siglinde	62555	\N	395
93	Funk Marius	62680	\N	\N
94	Froese Edward	62856	\N	\N
95	Froese Edward	62858	\N	\N
96	Froese Artur	63015	\N	1407
97	Froese Herman	63156	\N	783
98	Froese Arnold	63242	\N	403
99	Froese Johann	63372	\N	873
100	Froese N Johann	6337201	\N	\N
101	Froese Johann	63532	\N	409
102	Froese Jasch	63673	\N	148
103	Funk Albert	63684	\N	534
104	Froese Helmut	63930	\N	\N
105	Funk Ferdinand	63941	\N	534
106	Froese Erwin	63985	\N	448
107	Funk Doerksen Rendy	65143	\N	355
108	Froese Tommy	65370	\N	403
109	Funk Doerksen Rambert	65659	\N	355
110	Friesen Nadja	65745	\N	566
111	Funk Angelo	65820	\N	347
112	Froese Armin	65949	\N	518
113	Fariña Gabriela	65994	\N	5005
114	Funk Mariane	66313	\N	1449
115	Fehr Peter	66498	\N	832
116	Froese Gerhard	66573	\N	1079
117	Fariña Wilson	67011	\N	5005
118	Froese Adrean	67022	\N	967
119	Fehr Kehler Eddy	67129	\N	832
120	Friesen Stephanie	67130	\N	728
121	Unrau Gertruda	67486	\N	881
122	Sawatzky Lynette	67602	\N	\N
123	Friesen Alberto	67668	\N	544
124	Funk Dueck Marius Rafael	67680	\N	347
125	Funk Marius Rafael	67860	\N	0
126	Funk Ernst	68021	\N	\N
127	Funk Rudi	68032	\N	\N
128	Froese Arvid	68225	\N	967
129	Fehr Kehler Christian	69387	\N	1355
130	Froese Steffy Malaika	69398	\N	723
131	Froese Froese Govan Jason	69581	\N	132
132	Doerksen Sylvia	70185	\N	\N
133	Giesbrecht Hanns	70334	\N	932
134	Giesbrecht Hein	70356	\N	874
135	Giesbrecht Gino Dorian	70411	\N	0
136	Gerbrand Elmer	70583	\N	886
137	Giesbrecht Kehler Wilhelm	70680	\N	\N
138	Giesbrecht Giesbrecht Kellwin	70721	\N	1798
139	Guenther Jacob	70884	\N	854
5	Bergen Giesbrecht Fabian	20221	Loma Plata	535
7	Braun Wilbert	21457	Loma Plata	\N
140	Guenther Josef	70914	\N	124
141	Giesbrecht Wilfried	71021	\N	168
142	Giesbrecht Norina	71269	\N	507
143	Giesbrecht Edwin	71601	\N	507
144	Giesbrecht Harold	71708	\N	139
145	Guenther Gustav	71979	\N	\N
146	Giesbrecht Wilfried	72257	\N	1218
147	Giesbrecht Werner	72343	\N	542
148	Giesbrecht Funk Alfred	72633	\N	635
149	Giesbrecht R Anton	72763	\N	\N
150	Ginter Hirb	73234	\N	705
151	Giesbrecht Waldemar	73319	\N	932
152	Giesbrecht Levi	73364	\N	1311
153	Giesbrecht Norman	73568	\N	1292
154	Giesbrecht Carlos	73591	\N	932
155	Giesbrecht Adrian	73717	\N	1158
156	Giesbrecht Hirby Glenn	73900	\N	217
157	Giesbrecht de Sawatzky Erika	73988	\N	700
158	Giesbrecht Ewald	74404	\N	901
159	Giesbrecht Taylor	75276	\N	\N
160	Guenther Damaris	75414	\N	618
161	Giesbrecht Esteban	75629	\N	542
162	Giesbrecht Beanco	75652	\N	507
163	Giesbrecht Stanly	75845	\N	0
164	Ginther Adolf	76565	\N	618
165	Hiebert Abram	80025	\N	\N
166	Harder de Hiebert Susanna	80058	\N	270
167	Hiebert Ewald	80337	\N	\N
168	Hiebert Jacob	80553	\N	\N
169	Hiebert Jacob	80575	\N	444
170	Hiebert Jacob	80595	\N	\N
171	Harder Erdmann	81080	\N	809
172	Harder Helmut	81228	\N	80
173	Harder Peter	81460	\N	\N
174	Harder Peter	81466	\N	1221
175	Hiebert Wiebert Jacob	81927	\N	694
176	Harder Peter	81938	\N	\N
177	Dueck Harder Margaretha	82078	\N	333
178	Hiebert Abram	82506	\N	526
179	Anton Hiebert Fehr	82595	\N	\N
180	Hildebrand Peter	82625	\N	1271
181	Hiebert Kenny	82744	\N	270
182	Hiebert Sieghard	82874	\N	\N
183	Harder Cristian	82904	\N	669
184	Hiebert Werner	83011	\N	2222
185	Harder de Sawatzky Ludowika	83033	\N	\N
186	Hiebert Adolf	83118	\N	617
187	Hiebert Ronald	83259	\N	270
188	Hiebert Martin	83408	\N	95
189	Hiebert Isaak	83840	\N	1144
190	Hiebert Krahn Nathanael Sander	84797	\N	270
191	Hiebert Effendi	85019	\N	\N
192	Harder Waldemar	85149	\N	673
193	Zacharias de Hiebert Mathilde	85741	\N	1350
194	Harder Wilma	86524	\N	\N
195	Harder Siegmar	86739	\N	881
196	Harder Corinna	87040	\N	\N
197	Hiebert Manuela	87095	\N	1697
198	Hildebrand Vincent	87192	\N	\N
199	Hiebert Darius	87255	\N	738
200	Harder Selfia	87299	\N	1221
201	Hiebert Sandra	87318	\N	\N
202	Hiebert Toews Rudi	87341	\N	1394
203	Hiebert James	87437	\N	656
204	Hiebert Kauenhowen Erwin	87459	\N	1213
205	Herrera Mirian	87675	\N	\N
206	Hiebert Carlos	87965	\N	1748
207	Hiebert Martin	88038	\N	1748
208	Hiebert Neufeld Rudi	88306	\N	\N
209	Hiebert Manfred	88555	\N	2504
210	Harder Funk Rovan	88607	\N	1749
211	Hiebert Kauenhowen Elmer	89190	\N	1434
212	Harder Nelson	89220	\N	0
213	Hiebert Joel	89253	\N	526
214	Hiebert Angelino	89662	\N	1748
215	Hiebert Mirco	89974	\N	\N
216	Janzen Robert	100077	\N	\N
217	Kehler Jakob	110382	\N	783
218	Kehler Johann	110401	\N	1314
219	Kehler Peter	110564	\N	239
220	Kehler Peter	110575	\N	\N
221	Kehler Peter	110597	\N	\N
222	Bergen de Klassen Helena	110713	\N	346
223	Toews de Klassen Katharina	110843	\N	364
224	Peters de Klassen Aganetha	111035	\N	296
225	Kehler Wilfried	111110	\N	\N
226	Kauenhowen Erdmann F	111165	\N	1261
227	Kehler Kevin	111303	\N	1314
228	Krahn Peter	111626	\N	144
229	Krahn Maria	111659	\N	\N
230	Kauenhowen Eldon	111789	\N	368
231	Krahn Randy	111808	\N	182
232	Kehler Rainer	111949	\N	1057
233	Kehler Bernhard	111972	\N	1343
234	Klassen Anton	111994	\N	1185
235	Kehler Funk Werner	112108	\N	536
236	Krahn David	112120	\N	308
237	Klassen Jasch	112197	\N	204
238	Klassen Kelly	112443	\N	1267
239	Kauenhowen Friesen Benjamin	112573	\N	1771
240	Kauenhowen Martin	112647	\N	766
241	Krahn Adolf	112669	\N	374
242	Klassen Gustav	112733	\N	745
243	Klassen Ewald	112852	\N	364
244	Kehler Funk Helmut	113237	\N	\N
245	Krahn Hans	113312	\N	\N
246	Klassen Willi	113419	\N	1394
247	Kauenhowen Orlando	113668	\N	368
248	Klassen de Villalba Elvine	113798	\N	364
249	Kehler Roger	113969	\N	1057
250	Kehler Artur	115310	\N	1274
251	Karhn de Hiebert Vanessa	115406	\N	0
252	Krahn Stanly	115440	\N	374
253	Kehler Manuel	115495	\N	\N
254	Krahn Boris	115525	\N	374
255	Kehler Egon	115592	\N	1319
256	Krahn Leander	115774	\N	667
257	Krahn Fabian	117255	\N	374
258	Klassen Janie	117556	\N	\N
259	Klassen Larry	117653	\N	\N
260	Fehr Helmine	117932	\N	\N
261	Klassen Giesbrecht Andrea	119338	\N	1394
262	Kehler Debora	119510	\N	713
263	Kehler Enns Amalie	119985	\N	1058
264	Martens Hartmut	130106	\N	\N
265	Neufeld Eduard	140433	\N	690
266	Neufeld Leander	141056	\N	518
267	Neufeld Rolando	141391	\N	1315
268	Neudorf Willy	141539	\N	\N
269	Schroeder Mathias	145068	\N	511
270	Neufeld Ferdinand	146330	\N	1297
271	Peters Erdmann	160031	\N	351
272	Peters Witw..Heinrich	160064	\N	\N
273	Peters Johann K	160086	\N	113
274	Peters Alwin	161803	\N	999
275	Penner Robert	162169	\N	369
276	Penner Egon	162255	\N	426
277	Peters Maria	162471	\N	553
278	Penner Wiebe Haiko	163050	\N	191
279	Peters de Bergen Ruthy	163243	\N	\N
280	Reimer Jacob	180253	\N	\N
281	Reimer de Sawatzky Roswitha	180941	\N	\N
282	Reimer Jerry	181188	\N	\N
283	Reimer Wilhelm	181564	\N	1328
284	Rolli Gehrts Tobias Martin	181575	\N	515
285	Reckziegel Sawatzky Anton Rene	181605	\N	1440
286	Regier Harder Diana	184077	\N	1797
287	Sawatzky Anton	190063	\N	434
288	Sawatzky Adolf	190115	\N	378
289	Sawatzky Bernhard	190126	\N	466
290	Sawatzky Diedrich	190223	\N	427
291	Sawatzky Helmut	190397	\N	466
292	Sawatzky Johann	190472	\N	\N
293	Sawatzky Peter	190676	\N	136
294	Sawatzky Romeo	190698	\N	1626
295	Sawatzky Greta	190762	\N	877
296	Sawatzky Harry	191501	\N	1374
297	Sawatzky Kilian Marco	191512	\N	427
298	Schneider Federico	191653	\N	\N
299	Sawatzky Johann	191802	\N	7
300	Sawatzky Gustav	191846	\N	102
301	Sawatzky Werner	192050	\N	517
302	Sawatzky de Giesbrecht Rebeka	192061	\N	1798
303	Sawatzky de Giesbrecht Rebekka	192067	\N	1798
304	Sawatzky Kehler Rodney	192414	\N	1226
305	Sawatzky Raymond	192652	\N	160
306	Sawatzky de Hiebert Rita	192737	\N	\N
307	Sawatzky Wendelin	192782	\N	206
308	Sawatzky Dieter	192953	\N	684
309	Sawatzky Patricio	193060	\N	63
310	Sawatzky Rohwer Argelander	193134	\N	700
311	Schmitt Johann	193178	\N	696
312	Sawatzky Randy Carlos	193253	\N	1226
313	Sawatzky Charly	193305	\N	877
314	Sawatzky Ruediger	193383	\N	1146
315	Sawatzky Roy	193628	\N	700
316	Sawatzky de Gerbrand Jos.Roperta	193640	\N	886
317	Schroeder Derlis	193725	\N	\N
318	Sawatzky Patricia	193822	\N	877
319	Schroeder Mathias	195068	\N	\N
320	Schneider Raimund	195187	\N	0
321	Sawatzky de Wiens Esther	195648	\N	591
322	Sawatzky William	196313	\N	\N
323	Schmidt Abram	197055	\N	\N
324	Sawatzky Klassen Dilean	197066	\N	1226
325	Toews Giesbrecht Abram	200034	\N	668
326	(Colonos Neuland)Thiessen Ruth	200294	\N	\N
327	Toews Margareta	200357	\N	\N
328	Toews D Werner	201044	\N	432
329	Thiessen Jessica	201130	\N	1212
330	Thiessen Jakob	201323	\N	1380
331	Toews Jacob	201356	\N	\N
332	Toews Anton	201538	\N	436
333	Toews Simon	202009	\N	156
334	Thiessen Froese Franz	202076	\N	1212
335	Toews Hans	202203	\N	923
336	Toews Helmut	202225	\N	923
337	Toews David	202258	\N	953
338	Giesbrecht Dorothea	202612	\N	\N
339	Thiessen Eldon	202708	\N	1532
340	Toews de Reckziegel Daniela	202764	\N	1440
341	Toews Edgar	202850	\N	483
342	de Ginther Christel	202935	\N	\N
343	Toews Georg	203149	\N	\N
344	Toews Doreen	203257	\N	2247
345	Toews Doerksen Natalia	208348	\N	2528
346	Unrau Jakob	210112	\N	5008
347	Unrau David	210316	\N	881
348	Unrau Harold	210510	\N	602
349	Unrau de Harder Johanna	210792	\N	881
350	Unrau Leandro	210800	\N	602
351	Unrau Daniel	211100	\N	881
352	Vazquez Rober	220029	\N	1199
353	Wiebe Michael	230163	\N	381
354	Wiebe Wihliam	230390	\N	523
355	Wiebe Roger	230408	\N	71
356	Wiebe de Wiebe Netty	230420	\N	71
357	Wiebe Ivan	230635	\N	\N
358	Batista Hildegard	230969	\N	1319
359	Wiebe Cornelius	231032	\N	358
360	Gerbrand de Wiebe Maria	231098	\N	295
361	Wiebe Aganetha	231236	\N	\N
362	Wiebe Goertzen Gerhard	231560	\N	381
363	Wiebe de Enns Wanda	231849	\N	576
364	Wiebe Hildor	231850	\N	71
365	Wiens Edward	232161	\N	591
366	Wiebe Franz	232525	\N	21
367	Wiebe Erny	232699	\N	233
368	Wiebe Kehler Joe Jason	232796	\N	21
369	Wiebe Eldon	233074	\N	296
370	Wiebe Norbert	233160	\N	1371
371	Wiebe Gerold	233386	\N	650
372	Wiebe Nicky	233900	\N	\N
373	Wiens Wilhelm	234233	\N	1521
374	Wiebe Toews Eldon	234749	\N	\N
375	Wiebe Hiebert Manuel Sander	235317	\N	291
376	Zacharias Margaretha	260106	\N	187
377	Zacharias Erwin	260270	\N	942
378	Zacharias Daniel	268012	\N	\N
379	Doerksen Edward	301744	\N	931
380	Kehler Bernhard	301755	\N	1343
381	Balzer Tidor	302538	\N	1102
382	Rectiservice S.A.	302743	\N	182
383	Funk Rendy	303056	\N	355
384	Funk Doerksen Rendy	303065	\N	\N
385	Vazquez Rober	350751	\N	1199
386	Taller y Dos R S.R.L.	35075101	\N	1199
387	Althega S.A.	350900	\N	312
388	Fehr Falk Elmer	351233	\N	1252
389	Corporacion Chaco S.A.	351813	\N	1467
390	Heinrich Hiebert Dueck	352384	\N	\N
391	Norman Sawatzky Friesen	355239	\N	63
392	Kehler Kevin	355369	\N	\N
393	Toews Hans	355659	\N	923
394	Dueck Carlos	355778	\N	686
395	Asoc.Mision Indigena Menonita	402896	\N	436
396	Iglesia Evangelica Mennonita M.G.Osterwick	403657	\N	60
397	Doerksen Hiebert Janko	4119852	\N	\N
398	Ginter Bernhard	450161	\N	\N
399	Siemens Zacharias Brigitte	450644	\N	0
400	Doerksen Mariano	456422	\N	\N
401	Derksen Heinz	457119	\N	1378
402	Guenther Heinrich	458992	\N	\N
403	Regier Veronika	459935	\N	\N
405	En Proceso...	11111	\N	\N
406	Toews Abram G.	200034	Loma Plata	\N
4	Bergen Bernhard	20168	Loma Plata	1632
6	Bergen Penner Bernhard	21189	Loma Plata	508
407	Veron Sebastian	502949	\N	\N
408	Colonos Neuland - Johan Harms	65	\N	\N
\.


--
-- TOC entry 2078 (class 0 OID 0)
-- Dependencies: 174
-- Name: tbl_miembros_box_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tbl_miembros_box_seq', 1, false);


--
-- TOC entry 2079 (class 0 OID 0)
-- Dependencies: 175
-- Name: tbl_miembros_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tbl_miembros_id_seq', 408, true);


--
-- TOC entry 2054 (class 0 OID 16539)
-- Dependencies: 189
-- Data for Name: tbl_pagos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tbl_pagos (id, fechahora, id_miembro, id_remate, concepto, monto, tipo) FROM stdin;
18	2015-06-25 00:00:00	85	3	Erntedank Ausruf 2015	400000	0
19	2015-08-25 00:00:00	85	3	Erntedank Ausruf 2015	400000	0
20	2015-05-23 16:03:26.094	313	3	Erntedank Ausruf 2015	1800000	1
21	2015-06-25 00:00:00	370	3	Erntedank Ausruf 2015	1100000	0
22	2015-08-25 00:00:00	370	3	Erntedank Ausruf 2015	1100000	0
25	2015-06-25 00:00:00	364	3	Erntedank Ausruf 2015	600000	0
26	2015-08-25 00:00:00	364	3	Erntedank Ausruf 2015	600000	0
27	2015-06-25 00:00:00	210	3	Erntedank Ausruf 2015	1800000	0
28	2015-08-25 00:00:00	210	3	Erntedank Ausruf 2015	1800000	0
29	2015-06-25 00:00:00	92	3	Erntedank Ausruf 2015	500000	0
30	2015-08-25 00:00:00	92	3	Erntedank Ausruf 2015	500000	0
31	2015-06-25 00:00:00	360	3	Erntedank Ausruf 2015	600000	0
32	2015-08-25 00:00:00	360	3	Erntedank Ausruf 2015	600000	0
33	2015-05-23 17:12:59.977	313	3	Erntedank Ausruf 2015	1300000	1
34	2015-06-25 00:00:00	77	3	Erntedank Ausruf 2015	1150000	0
35	2015-08-25 00:00:00	77	3	Erntedank Ausruf 2015	1150000	0
36	2015-06-25 00:00:00	175	3	Erntedank Ausruf 2015	450000	0
37	2015-08-25 00:00:00	175	3	Erntedank Ausruf 2015	450000	0
38	2015-05-23 17:33:34.771	175	3	Erntedank Ausruf 2015	900000	1
39	2015-06-25 00:00:00	116	3	Erntedank Ausruf 2015	850000	0
40	2015-08-25 00:00:00	116	3	Erntedank Ausruf 2015	850000	0
41	2015-06-25 00:00:00	58	3	Erntedank Ausruf 2015	950000	0
42	2015-08-25 00:00:00	58	3	Erntedank Ausruf 2015	950000	0
43	2015-06-25 00:00:00	358	3	Erntedank Ausruf 2015	950000	0
44	2015-08-25 00:00:00	358	3	Erntedank Ausruf 2015	950000	0
45	2015-06-25 00:00:00	265	3	Erntedank Ausruf 2015	5800000	0
46	2015-08-25 00:00:00	265	3	Erntedank Ausruf 2015	5800000	0
47	2015-06-25 00:00:00	41	3	Erntedank Ausruf 2015	600000	0
48	2015-08-25 00:00:00	41	3	Erntedank Ausruf 2015	600000	0
49	2015-06-25 00:00:00	231	3	Erntedank Ausruf 2015	1500000	0
50	2015-08-25 00:00:00	231	3	Erntedank Ausruf 2015	1500000	0
51	2015-06-25 00:00:00	91	3	Erntedank Ausruf 2015	1700000	0
52	2015-08-25 00:00:00	91	3	Erntedank Ausruf 2015	1700000	0
53	2015-06-25 00:00:00	278	3	Erntedank Ausruf 2015	950000	0
54	2015-08-25 00:00:00	278	3	Erntedank Ausruf 2015	950000	0
55	2015-06-25 00:00:00	337	3	Erntedank Ausruf 2015	3800000	0
56	2015-08-25 00:00:00	337	3	Erntedank Ausruf 2015	3800000	0
57	2015-06-25 00:00:00	33	3	Erntedank Ausruf 2015	1150000	0
58	2015-08-25 00:00:00	33	3	Erntedank Ausruf 2015	1150000	0
59	2015-05-23 18:07:40.602	313	3	Erntedank Ausruf 2015	1200000	1
60	2015-06-25 00:00:00	150	3	Erntedank Ausruf 2015	2150000	0
61	2015-08-25 00:00:00	150	3	Erntedank Ausruf 2015	2150000	0
62	2015-06-25 00:00:00	208	3	Erntedank Ausruf 2015	3850000	0
63	2015-08-25 00:00:00	208	3	Erntedank Ausruf 2015	3850000	0
64	2015-06-25 00:00:00	4	3	Erntedank Ausruf 2015	800000	0
65	2015-08-25 00:00:00	4	3	Erntedank Ausruf 2015	800000	0
66	2015-06-25 00:00:00	81	3	Erntedank Ausruf 2015	1050000	0
67	2015-08-25 00:00:00	81	3	Erntedank Ausruf 2015	1050000	0
68	2015-06-25 00:00:00	347	3	Erntedank Ausruf 2015	650000	0
69	2015-08-25 00:00:00	347	3	Erntedank Ausruf 2015	650000	0
70	2015-06-25 00:00:00	349	3	Erntedank Ausruf 2015	700000	0
71	2015-08-25 00:00:00	349	3	Erntedank Ausruf 2015	700000	0
72	2015-06-25 00:00:00	87	3	Erntedank Ausruf 2015	250000	0
73	2015-08-25 00:00:00	87	3	Erntedank Ausruf 2015	250000	0
74	2015-06-25 00:00:00	6	3	Erntedank Ausruf 2015	3025000	0
75	2015-08-25 00:00:00	6	3	Erntedank Ausruf 2015	3025000	0
76	2015-06-25 00:00:00	285	3	Erntedank Ausruf 2015	400000	0
77	2015-08-25 00:00:00	285	3	Erntedank Ausruf 2015	400000	0
78	2015-06-25 00:00:00	82	3	Erntedank Ausruf 2015	2250000	0
79	2015-08-25 00:00:00	82	3	Erntedank Ausruf 2015	2250000	0
80	2015-06-25 00:00:00	376	3	Erntedank Ausruf 2015	500000	0
81	2015-08-25 00:00:00	376	3	Erntedank Ausruf 2015	500000	0
82	2015-06-25 00:00:00	50	3	Erntedank Ausruf 2015	5050000	0
83	2015-08-25 00:00:00	50	3	Erntedank Ausruf 2015	5050000	0
84	2015-06-25 00:00:00	33	3	Erntedank Ausruf 2015	600000	0
85	2015-08-25 00:00:00	33	3	Erntedank Ausruf 2015	600000	0
86	2015-06-25 00:00:00	44	3	Erntedank Ausruf 2015	3150000	0
87	2015-08-25 00:00:00	44	3	Erntedank Ausruf 2015	3150000	0
88	2015-06-25 00:00:00	227	3	Erntedank Ausruf 2015	2100000	0
89	2015-08-25 00:00:00	227	3	Erntedank Ausruf 2015	2100000	0
90	2015-06-25 00:00:00	218	3	Erntedank Ausruf 2015	1650000	0
91	2015-08-25 00:00:00	218	3	Erntedank Ausruf 2015	1650000	0
92	2015-06-25 00:00:00	69	3	Erntedank Ausruf 2015	350000	0
93	2015-08-25 00:00:00	69	3	Erntedank Ausruf 2015	350000	0
94	2015-06-25 00:00:00	27	3	Erntedank Ausruf 2015	650000	0
95	2015-08-25 00:00:00	27	3	Erntedank Ausruf 2015	650000	0
96	2015-06-25 00:00:00	366	3	Erntedank Ausruf 2015	750000	0
97	2015-08-25 00:00:00	366	3	Erntedank Ausruf 2015	750000	0
98	2015-05-23 18:46:13.174	310	3	Erntedank Ausruf 2015	6350000	1
99	2015-06-25 00:00:00	288	3	Erntedank Ausruf 2015	3900000	0
100	2015-08-25 00:00:00	288	3	Erntedank Ausruf 2015	3900000	0
103	2015-06-25 00:00:00	270	3	Erntedank Ausruf 2015	2800000	0
104	2015-08-25 00:00:00	270	3	Erntedank Ausruf 2015	2800000	0
105	2015-06-25 00:00:00	250	3	Erntedank Ausruf 2015	525000	0
106	2015-08-25 00:00:00	250	3	Erntedank Ausruf 2015	525000	0
107	2015-06-25 00:00:00	9	3	Erntedank Ausruf 2015	1400000	0
108	2015-08-25 00:00:00	9	3	Erntedank Ausruf 2015	1400000	0
109	2015-06-25 00:00:00	75	3	Erntedank Ausruf 2015	500000	0
110	2015-08-25 00:00:00	75	3	Erntedank Ausruf 2015	500000	0
111	2015-06-25 00:00:00	35	3	Erntedank Ausruf 2015	2200000	0
112	2015-08-25 00:00:00	35	3	Erntedank Ausruf 2015	2200000	0
113	2015-06-25 00:00:00	297	3	Erntedank Ausruf 2015	1400000	0
114	2015-08-25 00:00:00	297	3	Erntedank Ausruf 2015	1400000	0
115	2015-06-25 00:00:00	271	3	Erntedank Ausruf 2015	950000	0
116	2015-08-25 00:00:00	271	3	Erntedank Ausruf 2015	950000	0
117	2015-06-25 00:00:00	407	3	Erntedank Ausruf 2015	1000000	0
118	2015-08-25 00:00:00	407	3	Erntedank Ausruf 2015	1000000	0
119	2015-06-25 00:00:00	172	3	Erntedank Ausruf 2015	1700000	0
120	2015-08-25 00:00:00	172	3	Erntedank Ausruf 2015	1700000	0
121	2015-06-25 00:00:00	99	3	Erntedank Ausruf 2015	1200000	0
122	2015-08-25 00:00:00	99	3	Erntedank Ausruf 2015	1200000	0
123	2015-06-25 00:00:00	336	3	Erntedank Ausruf 2015	11500000	0
124	2015-08-25 00:00:00	336	3	Erntedank Ausruf 2015	11500000	0
125	2015-06-25 00:00:00	291	3	Erntedank Ausruf 2015	1500000	0
126	2015-08-25 00:00:00	291	3	Erntedank Ausruf 2015	1500000	0
127	2015-06-25 00:00:00	290	3	Erntedank Ausruf 2015	2300000	0
128	2015-08-25 00:00:00	290	3	Erntedank Ausruf 2015	2300000	0
129	2015-06-25 00:00:00	12	3	Erntedank Ausruf 2015	1000000	0
130	2015-08-25 00:00:00	12	3	Erntedank Ausruf 2015	1000000	0
131	2015-06-25 00:00:00	281	3	Erntedank Ausruf 2015	450000	0
132	2015-08-25 00:00:00	281	3	Erntedank Ausruf 2015	450000	0
133	2015-06-25 00:00:00	314	3	Erntedank Ausruf 2015	350000	0
134	2015-08-25 00:00:00	314	3	Erntedank Ausruf 2015	350000	0
135	2015-06-25 00:00:00	39	3	Erntedank Ausruf 2015	250000	0
136	2015-08-25 00:00:00	39	3	Erntedank Ausruf 2015	250000	0
137	2015-06-25 00:00:00	43	3	Erntedank Ausruf 2015	350000	0
138	2015-08-25 00:00:00	43	3	Erntedank Ausruf 2015	350000	0
139	2015-06-25 00:00:00	181	3	Erntedank Ausruf 2015	6900000	0
140	2015-08-25 00:00:00	181	3	Erntedank Ausruf 2015	6900000	0
141	2015-06-25 00:00:00	134	3	Erntedank Ausruf 2015	3950000	0
142	2015-08-25 00:00:00	134	3	Erntedank Ausruf 2015	3950000	0
143	2015-06-25 00:00:00	360	3	Erntedank Ausruf 2015	400000	0
144	2015-08-25 00:00:00	360	3	Erntedank Ausruf 2015	400000	0
145	2015-06-25 00:00:00	373	3	Erntedank Ausruf 2015	3450000	0
146	2015-08-25 00:00:00	373	3	Erntedank Ausruf 2015	3450000	0
147	2015-06-25 00:00:00	276	3	Erntedank Ausruf 2015	1200000	0
148	2015-08-25 00:00:00	276	3	Erntedank Ausruf 2015	1200000	0
149	2015-06-25 00:00:00	330	3	Erntedank Ausruf 2015	350000	0
150	2015-08-25 00:00:00	330	3	Erntedank Ausruf 2015	350000	0
151	2015-06-25 00:00:00	406	3	Erntedank Ausruf 2015	2650000	0
152	2015-08-25 00:00:00	406	3	Erntedank Ausruf 2015	2650000	0
153	2015-06-25 00:00:00	283	3	Erntedank Ausruf 2015	100000	0
154	2015-08-25 00:00:00	283	3	Erntedank Ausruf 2015	100000	0
155	2015-06-25 00:00:00	84	3	Erntedank Ausruf 2015	3700000	0
156	2015-08-25 00:00:00	84	3	Erntedank Ausruf 2015	3700000	0
157	2015-06-25 00:00:00	223	3	Erntedank Ausruf 2015	1100000	0
158	2015-08-25 00:00:00	223	3	Erntedank Ausruf 2015	1100000	0
159	2015-06-25 00:00:00	327	3	Erntedank Ausruf 2015	450000	0
160	2015-08-25 00:00:00	327	3	Erntedank Ausruf 2015	450000	0
161	2015-06-25 00:00:00	133	3	Erntedank Ausruf 2015	3400000	0
162	2015-08-25 00:00:00	133	3	Erntedank Ausruf 2015	3400000	0
163	2015-06-25 00:00:00	239	3	Erntedank Ausruf 2015	600000	0
164	2015-08-25 00:00:00	239	3	Erntedank Ausruf 2015	600000	0
165	2015-06-25 00:00:00	278	3	Erntedank Ausruf 2015	50000	0
166	2015-08-25 00:00:00	278	3	Erntedank Ausruf 2015	50000	0
167	2015-06-25 00:00:00	183	3	Erntedank Ausruf 2015	2150000	0
168	2015-08-25 00:00:00	183	3	Erntedank Ausruf 2015	2150000	0
169	2015-06-25 00:00:00	365	3	Erntedank Ausruf 2015	550000	0
170	2015-08-25 00:00:00	365	3	Erntedank Ausruf 2015	550000	0
171	2015-06-25 00:00:00	14	3	Erntedank Ausruf 2015	3050000	0
172	2015-08-25 00:00:00	14	3	Erntedank Ausruf 2015	3050000	0
173	2015-06-25 00:00:00	128	3	Erntedank Ausruf 2015	1200000	0
174	2015-08-25 00:00:00	128	3	Erntedank Ausruf 2015	1200000	0
175	2015-06-25 00:00:00	247	3	Erntedank Ausruf 2015	650000	0
176	2015-08-25 00:00:00	247	3	Erntedank Ausruf 2015	650000	0
177	2015-06-25 00:00:00	304	3	Erntedank Ausruf 2015	1650000	0
178	2015-08-25 00:00:00	304	3	Erntedank Ausruf 2015	1650000	0
179	2015-06-25 00:00:00	293	3	Erntedank Ausruf 2015	500000	0
180	2015-08-25 00:00:00	293	3	Erntedank Ausruf 2015	500000	0
181	2015-06-25 00:00:00	42	3	Erntedank Ausruf 2015	3500000	0
182	2015-08-25 00:00:00	42	3	Erntedank Ausruf 2015	3500000	0
\.


--
-- TOC entry 2080 (class 0 OID 0)
-- Dependencies: 188
-- Name: tbl_pagos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tbl_pagos_id_seq', 182, true);


--
-- TOC entry 2034 (class 0 OID 16400)
-- Dependencies: 169
-- Data for Name: tbl_recibos_bak; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tbl_recibos_bak (id, fechahora, id_miembro, monto, concepto, id_remate) FROM stdin;
\.


--
-- TOC entry 2081 (class 0 OID 0)
-- Dependencies: 176
-- Name: tbl_recibos_id_miembro_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tbl_recibos_id_miembro_seq', 1, false);


--
-- TOC entry 2082 (class 0 OID 0)
-- Dependencies: 177
-- Name: tbl_recibos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tbl_recibos_id_seq', 1, false);


--
-- TOC entry 2043 (class 0 OID 16434)
-- Dependencies: 178
-- Data for Name: tbl_remates; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tbl_remates (id, fecha, descripcion) FROM stdin;
3	2015-05-23 00:00:00	Erntedank Ausruf 2015
\.


--
-- TOC entry 2035 (class 0 OID 16410)
-- Dependencies: 170
-- Data for Name: tbl_remates_categorias; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tbl_remates_categorias (id, descripcion) FROM stdin;
1	Carne
3	Frutas
2	Varios
4	Confiteria
5	Muebles
6	Herramientas
7	Plantas
8	Sillas
9	Hechos a mano
10	Pollo-Gallina
11	Animal en Pie
\.


--
-- TOC entry 2044 (class 0 OID 16440)
-- Dependencies: 179
-- Data for Name: tbl_remates_cuotas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tbl_remates_cuotas (id_remate, fecha_1, fecha_2, fecha_3, fecha_4) FROM stdin;
3	2015-06-25 00:00:00	2015-08-25 00:00:00	\N	\N
\.


--
-- TOC entry 2045 (class 0 OID 16443)
-- Dependencies: 180
-- Data for Name: tbl_remates_detalle; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tbl_remates_detalle (fechahora, id, id_miembro, id_categoria, monto, observacion, id_remate) FROM stdin;
2015-05-23 15:13:55	28	337	4	600000	Torte + Shampoo	3
2015-05-23 15:15:19	29	271	1	500000	\N	3
2015-05-23 15:16:01	30	82	2	500000	Thermos	3
2015-05-23 15:16:40	31	314	1	700000	\N	3
2015-05-23 15:17:42	32	265	2	600000	Wurst + Lampe	3
2015-05-23 15:18:24	33	304	1	800000	\N	3
2015-05-23 15:19:18	34	336	2	50000	Pampers	3
2015-05-23 15:20:01	35	349	1	900000	Molido	3
2015-05-23 15:21:04	36	50	4	1300000	\N	3
2015-05-23 15:21:45	37	247	1	500000	\N	3
2015-05-23 15:22:15	38	337	2	1100000	Huhn + Nudeln	3
2015-05-23 15:23:04	39	4	1	400000	Cabra	3
2015-05-23 15:23:50	40	12	1	600000	Wurst	3
2015-05-23 15:27:01	43	336	2	50000	\N	3
2015-05-23 15:31:08	47	75	1	1000000	\N	3
2015-05-23 17:12:19	130	313	2	1300000	\N	3
2015-05-23 15:34:18	50	288	1	1500000	Gallina	3
2015-05-23 15:35:05	51	133	1	500000	Steak+Molido	3
2015-05-23 15:36:00	52	35	6	700000	Sarten	3
2015-05-23 15:36:51	53	44	2	1300000	Torta+Suesch	3
2015-05-23 15:37:39	54	77	1	800000	Paleta	3
2015-05-23 15:38:19	55	218	7	1300000	\N	3
2015-05-23 15:39:50	56	265	9	1600000	Kissenbezug	3
2015-05-23 15:41:32	58	288	1	1700000	Costilla+Molido	3
2015-05-23 15:42:55	59	337	1	1100000	Gallina	3
2015-05-23 15:43:43	60	181	1	1400000	Oveja	3
2015-05-23 15:47:56	66	91	2	800000	Mus	3
2015-05-23 15:48:23	67	288	8	1800000	+Cabra	3
2015-05-23 15:50:19	68	310	1	1300000	\N	3
2015-05-23 15:52:39	70	337	4	3000000	Torta	3
2015-05-23 15:53:30	71	278	1	800000	Molido	3
2015-05-23 15:54:14	72	85	2	800000	Zapatos+Roster	3
2015-05-23 15:56:55	74	172	1	1500000	Costilla+Molido	3
2015-05-23 15:57:55	75	336	1	1300000	\N	3
2015-05-23 15:58:52	77	336	2	1200000	3Fuss+Palo Santo	3
2015-05-23 16:02:10	78	313	1	1800000	\N	3
2015-05-23 15:26:47	42	169	1	1000000	\N	3
2015-05-23 15:26:14	41	406	1	1400000	\N	3
2015-05-23 15:44:20	61	172	1	900000	Rauchfleisch+Wurst	3
2015-05-23 15:51:47	69	406	2	1300000	Torta+Wurst+	3
2015-05-23 16:10:26	79	181	7	1900000	\N	3
2015-05-23 16:10:43	80	9	5	2000000	\N	3
2015-05-23 16:11:06	81	359	2	900000	\N	3
2015-05-23 16:11:29	82	91	2	500000	\N	3
2015-05-23 16:11:50	83	373	1	1700000	\N	3
2015-05-23 15:55:38	73	358	7	800000	+Cuadro	3
2015-05-23 16:18:57	85	336	2	1300000	\N	3
2015-05-23 16:19:27	86	14	1	2200000	\N	3
2015-05-23 16:19:48	87	181	4	2900000	wurst	3
2015-05-23 16:20:13	88	270	1	2300000	\N	3
2015-05-23 16:20:34	89	67	4	1200000	+Kuchen	3
2015-05-23 16:21:07	90	358	1	1100000	\N	3
2015-05-23 16:21:27	91	218	1	1500000	Pollo	3
2015-05-23 16:21:59	92	291	1	2000000	+picante	3
2015-05-23 16:22:35	93	265	7	2400000	Krotos	3
2015-05-23 16:22:53	94	218	2	500000	Olimpia Thermos	3
2015-05-23 16:23:21	95	82	1	2300000	\N	3
2015-05-23 16:24:46	97	133	1	1800000	Wurst+PaloSanto	3
2015-05-23 16:25:21	98	310	1	2000000	Wurst	3
2015-05-23 16:25:46	99	250	1	1050000	\N	3
2015-05-23 16:28:58	100	304	2	800000	Forever+Papas	3
2015-05-23 16:30:18	102	276	1	1100000	Oveja	3
2015-05-23 16:30:49	103	336	2	1100000	\N	3
2015-05-23 16:31:09	104	133	1	2000000	Molido	3
2015-05-23 16:31:34	105	44	1	1600000	Wurst	3
2015-05-23 16:32:39	106	406	1	1100000	\N	3
2015-05-23 16:33:01	107	134	9	1900000	Decke	3
2015-05-23 16:33:28	108	231	2	1500000	Holz	3
2015-05-23 17:15:07	131	77	2	1500000	\N	3
2015-05-23 15:32:03	48	327	1	900000	\N	3
2015-05-23 17:15:54	132	208	1	2400000	\N	3
2015-05-23 16:23:43	96	223	4	2200000	+Wurst	3
2015-05-23 16:29:27	101	41	1	1200000	Franz Dueck	3
2015-05-23 16:40:35	109	265	1	1700000	Cerdo	3
2015-05-23 16:40:53	110	58	1	900000	Gallina	3
2015-05-23 16:42:38	113	370	4	2200000	\N	3
2015-05-23 16:43:48	115	265	11	900000	Gallina	3
2015-05-23 16:44:05	116	364	1	500000	\N	3
2015-05-23 16:44:22	117	336	2	600000	\N	3
2015-05-23 16:44:52	118	116	1	500000	Oveja	3
2015-05-23 16:45:18	119	181	2	1800000	Gemuse+Tanka	3
2015-05-23 16:45:58	120	202	2	2600000	Puppen	3
2015-05-23 16:46:40	121	150	6	4300000	\N	3
2015-05-23 16:46:59	122	290	1	2000000	\N	3
2015-05-23 16:47:39	123	227	2	1400000	\N	3
2015-05-23 16:48:17	124	153	1	1400000	Wurst+Cerdo	3
2015-05-23 16:48:50	125	183	2	1900000	\N	3
2015-05-23 17:23:53	147	405	2	1300000	Tina Sawatzky	3
2015-05-23 17:02:35	126	364	1	700000	\N	3
2015-05-23 17:04:57	127	210	1	1800000	\N	3
2015-05-23 17:07:41	128	92	1	1000000	\N	3
2015-05-23 17:09:41	129	360	2	1200000	\N	3
2015-05-23 17:17:35	133	265	3	1300000	\N	3
2015-05-23 17:17:54	134	336	11	1600000	Zwerg	3
2015-05-23 17:18:20	135	14	1	1000000	\N	3
2015-05-23 17:19:50	137	50	10	1400000	\N	3
2015-05-23 17:20:41	139	87	1	500000	\N	3
2015-05-23 17:20:56	140	81	2	1500000	Wurst	3
2015-05-23 17:21:12	141	58	1	1000000	Cabra	3
2015-05-23 17:22:03	142	310	7	1900000	\N	3
2015-05-23 17:22:19	143	50	1	1600000	\N	3
2015-05-23 17:22:31	144	99	8	1400000	\N	3
2015-05-23 17:22:57	145	276	1	1300000	Obeja	3
2015-05-23 17:23:32	146	373	2	2600000	Eiskasten	3
2015-05-23 17:25:10	148	336	1	1500000	\N	3
2015-05-23 17:26:07	149	359	1	1800000	\N	3
2015-05-23 17:26:19	150	330	10	700000	\N	3
2015-05-23 17:26:36	151	42	1	2800000	\N	3
2015-05-23 17:26:58	152	81	1	600000	Cabra	3
2015-05-23 17:27:42	154	181	1	1900000	\N	3
2015-05-23 17:28:08	156	14	1	2000000	Cerdo	3
2015-05-23 17:28:25	157	336	2	1700000	\N	3
2015-05-23 17:28:39	158	270	1	1900000	\N	3
2015-05-23 15:28:01	45	6	1	900000	Pollo	3
2015-05-23 15:33:33	49	6	1	900000	Oveja	3
2015-05-23 15:40:34	57	6	2	1750000	\N	3
2015-05-23 15:58:09	76	6	4	600000	\N	3
2015-05-23 17:27:53	155	172	2	1300000	\N	3
2015-05-23 15:47:06	65	172	2	2000000	\N	3
2015-05-23 15:45:37	62	50	1	1000000	pollo	3
2015-05-23 15:46:49	64	290	1	500000	cabra	3
2015-05-23 16:12:11	84	210	1	1800000	Cabra	3
2015-05-23 17:28:50	159	227	4	1900000	\N	3
2015-05-23 17:29:03	160	133	4	1800000	\N	3
2015-05-23 17:29:27	161	183	1	1900000	cerdo	3
2015-05-23 17:35:27	163	42	5	2800000	\N	3
2015-05-23 17:37:45	164	208	8	1800000	\N	3
2015-05-23 17:38:30	165	134	1	2000000	\N	3
2015-05-23 17:38:45	166	283	2	200000	\N	3
2015-05-23 17:38:59	167	50	1	2500000	Oveja	3
2015-05-23 17:39:11	168	44	2	1800000	\N	3
2015-05-23 17:39:26	169	288	1	1300000	cerdo	3
2015-05-23 17:39:39	170	288	2	1500000	Hemd	3
2015-05-23 17:39:54	171	134	2	1600000	\N	3
2015-05-23 17:40:39	173	265	2	1600000	\N	3
2015-05-23 17:40:56	174	33	4	2300000	\N	3
2015-05-23 17:41:14	175	278	1	1100000	\N	3
2015-05-23 17:41:25	176	183	2	500000	\N	3
2015-05-23 17:41:38	177	39	1	500000	\N	3
2015-05-23 17:42:07	178	44	2	800000		3
2015-05-23 17:42:36	179	304	1	1700000	\N	3
2015-05-23 17:42:48	180	336	2	1300000	\N	3
2015-05-23 17:43:07	181	50	1	2000000	cerdo	3
2015-05-23 17:45:56	184	42	2	1400000	\N	3
2015-05-23 17:46:07	185	84	1	1900000	costilla	3
2015-05-23 17:46:22	186	336	2	600000	\N	3
2015-05-23 17:46:35	187	337	5	1800000	\N	3
2015-05-23 17:46:53	188	366	1	1500000	\N	3
2015-05-23 17:47:04	189	134	2	700000	\N	3
2015-05-23 17:47:17	190	35	7	1800000	\N	3
2015-05-23 17:47:29	191	91	4	2100000	\N	3
2015-05-23 17:47:43	192	373	1	1600000	\N	3
2015-05-23 17:48:05	194	116	1	1200000	\N	3
2015-05-23 17:48:21	195	310	2	1150000	\N	3
2015-05-23 17:48:34	196	336	1	1300000	\N	3
2015-05-23 17:48:50	197	265	2	1500000	\N	3
2015-05-23 17:48:58	198	84	4	1000000	\N	3
2015-05-23 17:49:10	199	347	1	1300000	\N	3
2015-05-23 17:49:21	200	365	7	1100000	\N	3
2015-05-23 17:56:39	201	12	1	1400000	\N	3
2015-05-23 17:56:51	202	406	2	1500000	\N	3
2015-05-23 17:57:09	203	271	1	500000	\N	3
2015-05-23 17:57:20	204	208	5	1500000	\N	3
2015-05-23 17:57:36	205	84	1	2100000	cerdo	3
2015-05-23 17:58:06	206	336	4	1300000	\N	3
2015-05-23 17:58:26	207	336	1	1300000	\N	3
2015-05-23 17:58:34	208	69	4	700000	\N	3
2015-05-23 17:58:54	209	181	1	2100000	cerdo	3
2015-05-23 17:59:11	210	231	1	1500000	\N	3
2015-05-23 17:59:21	211	285	2	800000	\N	3
2015-05-23 17:59:34	212	9	4	800000	\N	3
2015-05-23 17:59:48	213	290	2	900000	\N	3
2015-05-23 17:59:57	214	293	1	1000000	\N	3
2015-05-23 18:05:24	215	313	1	1200000	\N	3
2015-05-23 18:07:59	216	84	1	700000		3
2015-05-23 18:08:33	217	169	1	900000	\N	3
2015-05-23 18:09:07	218	44	2	800000	\N	3
2015-05-23 18:09:18	219	82	1	1700000	\N	3
2015-05-23 18:09:47	220	336	2	3300000	\N	3
2015-05-23 18:10:16	221	336	2	800000	\N	3
2015-05-23 18:10:26	222	4	1	1200000	\N	3
2015-05-23 18:10:47	223	208	1	2000000	\N	3
2015-05-23 18:10:59	224	43	2	700000	\N	3
2015-05-23 18:11:12	225	128	1	1100000	\N	3
2015-05-23 18:11:21	226	133	2	700000		3
2015-05-23 15:27:22	44	6	9	500000	\N	3
2015-05-23 17:47:54	193	6	2	700000	\N	3
2015-05-23 18:26:11	227	35	1	1200000	\N	3
2015-05-23 18:26:29	228	336	2	900000	\N	3
2015-05-23 18:27:03	231	99	1	1000000	\N	3
2015-05-23 18:27:35	233	35	2	700000	\N	3
2015-05-23 18:28:22	234	14	1	900000	\N	3
2015-05-23 18:28:57	235	291	2	1000000	\N	3
2015-05-23 18:29:12	236	349	2	500000	\N	3
2015-05-23 18:29:35	237	227	2	900000	\N	3
2015-05-23 18:29:49	238	239	1	1200000	\N	3
2015-05-23 18:30:01	239	6	2	700000	\N	3
2015-05-23 18:30:12	240	128	1	1300000	\N	3
2015-05-23 18:30:21	241	134	1	1700000	\N	3
2015-05-23 18:34:13	242	247	2	800000	\N	3
2015-05-23 18:34:26	243	270	1	1400000	\N	3
2015-05-23 18:34:36	244	153	1	1200000	\N	3
2015-05-23 18:34:45	245	373	1	1000000	\N	3
2015-05-23 18:34:54	246	297	2	1500000	\N	3
2015-05-23 18:35:28	248	181	1	800000	\N	3
2015-05-23 18:35:38	249	84	1	800000	\N	3
2015-05-23 18:35:56	251	181	1	1000000	\N	3
2015-05-23 18:36:04	252	376	1	1000000	\N	3
2015-05-23 18:36:25	254	336	1	900000	\N	3
2015-05-23 18:36:41	255	271	1	900000	\N	3
2015-05-23 18:36:52	256	33	1	1200000	\N	3
2015-05-23 18:37:05	257	336	1	900000	\N	3
2015-05-23 18:37:15	258	138	1	800000	\N	3
2015-05-23 18:37:23	259	290	1	400000	\N	3
2015-05-23 18:37:31	260	360	1	800000	\N	3
2015-05-23 18:37:44	261	84	1	900000	\N	3
2015-05-23 18:37:54	262	278	2	100000	\N	3
2015-05-23 18:26:48	230	27	1	1300000	\N	3
2015-05-23 18:51:20	263	297	1	1300000	\N	3
2015-05-23 18:35:47	250	407	1	800000	\N	3
2015-05-23 18:26:38	229	407	1	1200000	\N	3
2015-05-23 18:27:22	232	281	1	900000	\N	3
2015-05-23 18:36:12	253	408	1	1000000	\N	3
2015-05-23 17:40:06	172	408	1	1400000	cabra	3
2015-05-23 15:29:13	46	43	2	4600000	Sattel+Lasso  - Hatte das erst auf 35 Konto	3
2015-05-23 17:31:51	162	175	1	1800000	Opa Hiebert	3
\.


--
-- TOC entry 2083 (class 0 OID 0)
-- Dependencies: 181
-- Name: tbl_remates_id_categoria_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tbl_remates_id_categoria_seq', 1, false);


--
-- TOC entry 2084 (class 0 OID 0)
-- Dependencies: 182
-- Name: tbl_remates_id_miembro_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tbl_remates_id_miembro_seq', 1, false);


--
-- TOC entry 2085 (class 0 OID 0)
-- Dependencies: 183
-- Name: tbl_remates_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tbl_remates_id_seq', 263, true);


--
-- TOC entry 2086 (class 0 OID 0)
-- Dependencies: 184
-- Name: tbl_remates_id_seq1; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tbl_remates_id_seq1', 3, true);


--
-- TOC entry 2050 (class 0 OID 16458)
-- Dependencies: 185
-- Data for Name: tbl_transferencias_bak; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tbl_transferencias_bak (fechahora, concepto, monto, id, id_miembro, id_remate) FROM stdin;
\.


--
-- TOC entry 2087 (class 0 OID 0)
-- Dependencies: 186
-- Name: tbl_transferencias_id_miembro_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tbl_transferencias_id_miembro_seq', 1, false);


--
-- TOC entry 2088 (class 0 OID 0)
-- Dependencies: 187
-- Name: tbl_transferencias_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tbl_transferencias_id_seq', 1, true);


--
-- TOC entry 2010 (class 2606 OID 16479)
-- Name: pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_remates_categorias
    ADD CONSTRAINT pk PRIMARY KEY (id);


--
-- TOC entry 2012 (class 2606 OID 16481)
-- Name: tbl_iglesia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_iglesia
    ADD CONSTRAINT tbl_iglesia_pkey PRIMARY KEY (id);


--
-- TOC entry 2004 (class 2606 OID 16483)
-- Name: tbl_miembros_ctacte_nombre_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_miembros
    ADD CONSTRAINT tbl_miembros_ctacte_nombre_key UNIQUE (ctacte, nombre);


--
-- TOC entry 2006 (class 2606 OID 16485)
-- Name: tbl_miembros_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_miembros
    ADD CONSTRAINT tbl_miembros_pkey PRIMARY KEY (id);


--
-- TOC entry 2022 (class 2606 OID 16547)
-- Name: tbl_pagos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_pagos
    ADD CONSTRAINT tbl_pagos_pkey PRIMARY KEY (id);


--
-- TOC entry 2008 (class 2606 OID 16487)
-- Name: tbl_recibos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_recibos_bak
    ADD CONSTRAINT tbl_recibos_pkey PRIMARY KEY (id);


--
-- TOC entry 2016 (class 2606 OID 16489)
-- Name: tbl_remates_cuotas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_remates_cuotas
    ADD CONSTRAINT tbl_remates_cuotas_pkey PRIMARY KEY (id_remate);


--
-- TOC entry 2018 (class 2606 OID 16491)
-- Name: tbl_remates_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_remates_detalle
    ADD CONSTRAINT tbl_remates_pkey PRIMARY KEY (id);


--
-- TOC entry 2014 (class 2606 OID 16493)
-- Name: tbl_remates_pkey1; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_remates
    ADD CONSTRAINT tbl_remates_pkey1 PRIMARY KEY (id);


--
-- TOC entry 2020 (class 2606 OID 16495)
-- Name: tbl_transferencias_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_transferencias_bak
    ADD CONSTRAINT tbl_transferencias_pkey PRIMARY KEY (id);


--
-- TOC entry 2031 (class 2606 OID 16548)
-- Name: tbl_pagos_id_miembro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_pagos
    ADD CONSTRAINT tbl_pagos_id_miembro_fkey FOREIGN KEY (id_miembro) REFERENCES tbl_miembros(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2032 (class 2606 OID 16553)
-- Name: tbl_pagos_id_remate_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_pagos
    ADD CONSTRAINT tbl_pagos_id_remate_fkey FOREIGN KEY (id_remate) REFERENCES tbl_remates(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2023 (class 2606 OID 16599)
-- Name: tbl_recibos_id_miembro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_recibos_bak
    ADD CONSTRAINT tbl_recibos_id_miembro_fkey FOREIGN KEY (id_miembro) REFERENCES tbl_miembros(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2024 (class 2606 OID 16604)
-- Name: tbl_recibos_id_remate_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_recibos_bak
    ADD CONSTRAINT tbl_recibos_id_remate_fkey FOREIGN KEY (id_remate) REFERENCES tbl_remates(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2025 (class 2606 OID 16506)
-- Name: tbl_remates_cuotas_id_remate_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_remates_cuotas
    ADD CONSTRAINT tbl_remates_cuotas_id_remate_fkey FOREIGN KEY (id_remate) REFERENCES tbl_remates(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2026 (class 2606 OID 16578)
-- Name: tbl_remates_detalle_id_remate_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_remates_detalle
    ADD CONSTRAINT tbl_remates_detalle_id_remate_fkey FOREIGN KEY (id_remate) REFERENCES tbl_remates(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2027 (class 2606 OID 16583)
-- Name: tbl_remates_id_categoria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_remates_detalle
    ADD CONSTRAINT tbl_remates_id_categoria_fkey FOREIGN KEY (id_categoria) REFERENCES tbl_remates_categorias(id);


--
-- TOC entry 2028 (class 2606 OID 16588)
-- Name: tbl_remates_id_miembro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_remates_detalle
    ADD CONSTRAINT tbl_remates_id_miembro_fkey FOREIGN KEY (id_miembro) REFERENCES tbl_miembros(id);


--
-- TOC entry 2029 (class 2606 OID 16609)
-- Name: tbl_transferencias_id_miembro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_transferencias_bak
    ADD CONSTRAINT tbl_transferencias_id_miembro_fkey FOREIGN KEY (id_miembro) REFERENCES tbl_miembros(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2030 (class 2606 OID 16614)
-- Name: tbl_transferencias_id_remate_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_transferencias_bak
    ADD CONSTRAINT tbl_transferencias_id_remate_fkey FOREIGN KEY (id_remate) REFERENCES tbl_remates(id);


--
-- TOC entry 2061 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-05-26 06:40:21

--
-- PostgreSQL database dump complete
--

