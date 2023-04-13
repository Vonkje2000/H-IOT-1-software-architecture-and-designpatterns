CREATE TABLE Attractie
( naam        text  NOT NULL,
  X           integer,
  Y           integer,
  horeca      text,
  info        text,
  PRIMARY KEY (naam)
);

CREATE TABLE Fietstocht
( Tid         integer NOT NULL,
  Tijd        text    NOT NULL,
  PRIMARY KEY (Tid, Tijd)
);

CREATE TABLE Locatie
( Tid         integer NOT NULL,
  Tijd        text    NOT NULL,
  Tijdstip    text    NOT NULL,
  X           integer,
  Y           integer,
  FOREIGN KEY (Tid,Tijd) REFERENCES Fietstocht(Tid, Tijd),
  PRIMARY KEY (Tid,Tijd,Tijdstip)
);

INSERT INTO Attractie VALUES ( 'McDonalds',      1, 1,  'J', 'BigMac nu voor maar 4 Euro.');
INSERT INTO Attractie VALUES ( 'Madurodam',      5, 10, 'N', 'Kinderen tot 10 jaar voor de helft van de prijs.');
INSERT INTO Attractie VALUES ( 'Gemeentemuseum', 8, 15, 'N', 'Expositie Andy Warhol.');
INSERT INTO Attractie VALUES ( 'Tjendrawasih',   6, 20, 'J', 'Met de fiets? Dan 10% korting!');
INSERT INTO Attractie VALUES ( 'Vredespaleis',   10, 1, 'N', 'Elke dag een gratis tour van 11:00 tot 13:00.');
