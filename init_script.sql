CREATE SCHEMA DATABASE;
CREATE TABLE DATABASE.RELIGION (
    id integer not null primary key,
    name varchar(30) not null
);

CREATE TABLE DATABASE.LAND (
  kuerzel varchar(3) not null primary key ,
  name varchar(30) not null,
  vorwahl varchar(3) not null
);

CREATE TABLE DATABASE.PATIENT (
    svnr integer not null primary key,
    vn varchar(30) not null,
    nn varchar(30) not null,
    gn varchar(30),
    titel varchar(50),
    namenszusatz varchar(10),
    gebdatum date not null,
    gebort varchar(40) not null,
    geschlecht varchar(20) not null,
    familienstand varchar(20) not null,
    staatKuerzel varchar(3) not null,
    plz varchar(6) not null,
    ort varchar(30) not null,
    str varchar(20) not null,
    hausnr varchar(5) not null,
    vorwahl varchar(4) not null,
    tel varchar(20) not null,
    relID integer not null
);

CREATE VIEW DATABASE.GET_ALL_PATIENTS AS
SELECT P.SVNR, P.VN, P.NN, P.GN, P.TITEL, P.NAMENSZUSATZ, P.GEBDATUM, P.GEBORT, P.GESCHLECHT, P.FAMILIENSTAND, L.KUERZEL, L.NAME, L.VORWAHL, P.PLZ, P.ORT, P.STR, P.HAUSNR, P.TEL, R.ID, R.NAME AS RELNAME FROM PATIENT P
INNER JOIN LAND L ON P.STAATKUERZEL = L.KUERZEL
INNER JOIN RELIGION R on P.RELID = R.ID;
