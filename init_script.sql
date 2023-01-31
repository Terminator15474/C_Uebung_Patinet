CREATE SCHEMA DATABASE;
CREATE TABLE DATABASE.RELIGION (
    id integer not null,
    name varchar(30) not null
);

CREATE TABLE DATABASE.LAND (
  kuerzel varchar(3) not null,
  name varchar(30) not null,
  vorwahl varchar(3) not null
);

CREATE TABLE DATABASE.PATIENT (
    svnr integer not null,
    vn varchar(30) not null,
    nn varchar(30) not null,
    gn varchar(30),
    titel varchar(10),
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