drop database if exists occhiale;
create database occhiale;
use occhiale;

drop table if exists prodotto;

create table prodotto (
	id int(3) not null AUTO_INCREMENT,
    nome varchar(30) not null,
    descrizione varchar(100) not null,
    marca varchar(50) not null,
    sesso varchar(1) not null,
    primary key (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 default CHARSET=latin1;

INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Ray-Ban Burbank 0RB2283', 'Occhiali della Ray-Ban', 'Ray-Ban', 'M');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Ray-Ban 0RB3958', 'Occhiali della Ray-Ban', 'Ray-Ban', 'M');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Ray-Ban OCTAGON 0RB1972', 'Occhiali della Ray-Ban', 'Ray-Ban', 'M');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Ray-Ban BALORAMA RB4089', 'Occhiali della Ray-Ban', 'Ray-Ban', 'M');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Ray-Ban WAYFARER 0RB2180', 'Occhiali della Ray-Ban', 'Ray-Ban', 'F');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Ray-Ban RB3668', 'Occhiali della Ray-Ban', 'Ray-Ban', 'F');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Ray-Ban RB4640', 'Occhiali della Ray-Ban', 'Ray-Ban', 'F');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Ray-Ban STATE STREET RB2186', 'Occhiali della Ray-Ban', 'Ray-Ban', 'F');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Persol PO3019S', 'Occhiali della Persol', 'Persol', 'M');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Persol PO3255S', 'Occhiali della Persol', 'Persol', 'M');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Persol 0PO3272S', 'Occhiali della Persol', 'Persol', 'F');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Persol PO5004ST', 'Occhiali della Persol', 'Persol', 'F');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Prada PR 19WS', 'Occhiali di Prada', 'Prada', 'U');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Prada PR 63XS', 'Occhiali di Prada', 'Prada', 'M');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Prada PR 27ZS', 'Occhiali di Prada', 'Prada', 'M');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Prada PR 09WS', 'Occhiali di Prada', 'Prada', 'M');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Prada PR60XS', 'Occhiali di Prada', 'Prada', 'F');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Prada 0PR 08YS', 'Occhiali di Prada', 'Prada', 'F');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Prada PR66TS', 'Occhiali di Prada', 'Prada', 'F');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Prada PR 26ZS', 'Occhiali di Prada', 'Prada', 'F');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Gucci GG0341S', 'Occhiali di Gucci', 'Gucci', 'M');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Gucci GG0287S', 'Occhiali di Gucci', 'Gucci', 'M');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Gucci GG1038S', 'Occhiali di Gucci', 'Gucci', 'M');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Gucci GG0911S', 'Occhiali di Gucci', 'Gucci', 'M');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Gucci GG0808S', 'Occhiali di Gucci', 'Gucci', 'F');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Gucci GG0163S', 'Occhiali di Gucci', 'Gucci', 'F');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Gucci GG0978S', 'Occhiali di Gucci', 'Gucci', 'F');
INSERT INTO `occhiale`.`prodotto` (`nome`, `descrizione`, `marca`, `sesso`) VALUES ('Gucci GG0036S', 'Occhiali di Gucci', 'Gucci', 'F');

drop table if exists colore;

create table colore (
	id int not null AUTO_INCREMENT,
    idProdotto int(3) not null,
    colore varchar(100) not null,
    immagine varchar(1000) not null,
    prezzo double not null,
    quantità int(11) not null,
    codiceProdotto varchar(100) not null,
    primary key (id),
    foreign key (idProdotto) references Prodotto(Id) on update cascade on delete cascade
) ENGINE=InnoDB AUTO_INCREMENT=1 default CHARSET=latin1;

INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('1', 'Verde/Nero', 'Ray-Ban Burbank 0RB2283 VerdeNero.avif', '215', '10', '0RB2283 901/58');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('1', 'Verde/Tartarugato', 'Ray-Ban Burbank 0RB2283 VerdeTartarugato.avif', '165', '20', '0RB2283 902/31');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('2', 'Verde/Oro', 'Ray-Ban 0RB3958 VerdeOro.avif', '155', '50', '0RB3958 919631');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('3', 'Blu/Argento', 'Ray-Ban OCTAGON 0RB1972 BluArgento.avif', '165', '5', '0RB1972 91493F');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('4', 'Verde/Nero', 'Ray-Ban BALORAMA RB4089 VerdeNero.avif', '145', '25', 'RB4089 601/31');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('5', 'Marrone/Beige', 'Ray-Ban WAYFARER 0RB2180 MarroneBeige.avif', '145', '15', '0RB2180 616613');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('6', 'Marrone/Oro', 'Ray-Ban RB3668 MarroneOro.avif', '145', '30', 'RB3668 001/Q4');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('6', 'Blu/Oro', 'Ray-Ban RB3668 BluOro.avif', '145', '15', 'RB3668 001/Q2');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('7', 'Marrone/Tartarugato', 'Ray-Ban RB4640 MarroneTartarugato.avif', '145', '10', 'RB4640 710/33');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('8', 'Marrone/Tartarugato', 'Ray-Ban STATE STREET RB2186 MarroneTartarugato.avif', '145', '40', 'RB2186 1292B1');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('8', 'Verde/Nero', 'Ray-Ban STATE STREET RB2186 VerdeNero.avif', '165', '20', 'RB2186 901/31');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('9', 'Verde/Tartarugato', 'Persol PO3019S VerdeTartarugato.avif', '200', '10', 'PO3019S 24/31');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('9', 'Marrone/Marrone', 'Persol PO3019S MarroneMarrone.avif', '200', '20', 'PO3019S 108/51');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('9', 'Blu/Marrone', 'Persol PO3019S BluMarrone.avif', '200', '30', 'PO3019S 96/56');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('10', 'Verde/Nero', 'Persol PO3255S VerdeNero.avif', '210', '50', 'PO3255S 95/31');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('11', 'Verde/Nero', 'Persol 0PO3272S VerdeNero.avif', '220', '30', '0PO3272S 95/31');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('11', 'Blu/Rosso', 'Persol 0PO3272S BluRosso.avif', '220', '25', '0PO3272S 96/56');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('12', 'Marrone/Bronzo', 'Persol PO5004ST MarroneBronzo.avif', '390', '10', 'PO5004ST 800333');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('13', 'Grigio/Nero', 'Prada PR 19WS GrigioNero.avif', '310', '10', 'PR 19WS 1AB5S0');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('14', 'Grigio/Nero', 'Prada PR 63XS GrigioNero.avif', '270', '20', 'PR 63XS 1AB731');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('15', 'Grigio/Nero', 'Prada PR 27ZS GrigioNero.avif', '370', '30', 'PR 27ZS 16K08Z');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('16', 'Blu/Trasparente', 'Prada PR 09WS BluTrasparente.avif', '210', '15', 'PR 09WS 06M420');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('16', 'Grigio/Trasparente', 'Prada PR 09WS GrigioTrasparente.avif', '210', '20', 'PR 09WS 05M3D0');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('17', 'Marrone/Oro', 'Prada PR60XS MarroneOro.avif', '290', '20', 'PR60XS KOF3D0');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('18', 'Marrone/Tartarugato', 'Prada 0PR 08YS MarroneTartarugato.avif', '310', '5', '0PR 08YS 01V8C1');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('19', 'Grigio/Nero', 'Prada PR66TS GrigioNero.avif', '290', '10', 'PR66TS 1AB0A7');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('20', 'Marrone/Verde', 'Prada PR 26ZS MarroneVerde.avif', '370', '20', 'PR 26ZS 14L09Z');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('21', 'Grigio/Nero', 'Gucci GG0341S GrigioNero.avif', '300', '20', 'GG0341S 1');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('22', 'Marrone/Nero/Oro', 'Gucci GG0287S MarroneNeroOro.avif', '430', '30', 'GG0287S 1');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('23', 'Grigio/Nero', 'Gucci GG1038S GrigioNero.avif', '370', '15', 'GG1038S 1');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('24', 'Grigio/Nero', 'Gucci GG0911S GrigioNero.avif', '320', '50', 'GG0911S 1');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('24', 'Verde/Marrone', 'Gucci GG0911S VerdeMarrone.avif', '320', '20', 'GG0911S 3');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('25', 'Grigio/Nero', 'Gucci GG0808S GrigioNero.avif', '320', '30', 'GG0808S 1');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('26', 'Grigio/Nero', 'Gucci GG0163S GrigioNero.avif', '180.99', '25', 'GG0163S 3');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('26', 'Marrone/Tartarugato', 'Gucci GG0163S MarroneTartarugato.avif', '260', '20', 'GG0163S 2');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('27', 'Grigio/Nero', 'Gucci GG0978S GrigioNero.avif', '750', '10', 'GG0978S 4');
INSERT INTO `occhiale`.`colore` (`idProdotto`, `colore`, `immagine`, `prezzo`, `quantità`, `codiceProdotto`) VALUES ('28', 'Marrone/Nero', 'Gucci GG0036S MarroneNero.avif', '279.99', '18', 'GG0036S 2');


drop table if exists utente;

create table utente (
	id int(3) not null AUTO_INCREMENT,
    nome varchar(30) not null,
    cognome varchar(30) not null,
    codiceFiscale varchar(16) not null,
    dataNascita date not null,
    email varchar(30) not null,
    password varchar(45) not null,
    telefono varchar(15) not null,
    sesso varchar(7) not null,
    tipo varchar(100) not null,
    regione varchar(45) not null,
    provincia varchar(45) not null,
    comune varchar(45) not null,
    indirizzo varchar(45) not null,
    primary key (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 default CHARSET=latin1;

insert into utente (nome, cognome, codiceFiscale, dataNascita, email, password, telefono, sesso, tipo, regione, provincia, comune, indirizzo) values ("Simone", "Civale", "CVLSMN98P13L845K", "1998-09-13", "s.civale2@studenti.unisa.it", "Admin", "+39-3331007122", "Maschio", "Amministratore", "Campania", "Napoli", "Torre Annunziata", "Via Prota 69");
insert into utente (nome, cognome, codiceFiscale, dataNascita, email, password, telefono, sesso, tipo, regione, provincia, comune, indirizzo) values ("Simone", "Civale", "CVLSMN98P13L845K", "1998-09-13", "simone.civale@libero.it", "13Simone?", "+39-3331007122", "Maschio", "Utente", "Campania", "Napoli", "Torre Annunziata", "Via Prota 69");


drop table if exists pagamento;

create table pagamento (
	id int(3) not null AUTO_INCREMENT,
    nomeCliente varchar(100) not null,
    numeroCarta varchar(16) not null,
    scadenza char(7) not null,
    cvv varchar(4) not null,
    primary key (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 default CHARSET=latin1;

insert into pagamento (id, nomeCliente, numeroCarta, scadenza, cvv) values (1, "Simone Civale", "5205305621458997", "2023-07", "325");
insert into pagamento (id, nomeCliente, numeroCarta, scadenza, cvv) values (2, "Simone Civale", "5200506030814569", "2024-05", "384");
insert into pagamento (id, nomeCliente, numeroCarta, scadenza, cvv) values (3, "Simone Civale", "5299147520369546", "2025-12", "753");

drop table if exists dettagliOrdine;

create table dettagliOrdine (
	id int(3) not null AUTO_INCREMENT,
    importo double not null,
    iva double not null,
    data date not null,
    quantità int(11) not null,
    idUtente int(3) not null,
    idPagamento int(3) not null,
    stato varchar(50) not null,
    primary key (id),
    foreign key (idUtente) references utente(id) on update cascade on delete cascade,
    foreign key (idPagamento) references pagamento(id) on update cascade on delete cascade
) ENGINE=InnoDB AUTO_INCREMENT=1 default CHARSET=latin1;

insert into dettagliOrdine (id, importo, iva, data, quantità, idUtente, idPagamento, stato) values (1, 380.00, 22.00, '2023-11-23', 2, 2, 1, "Completato");
insert into dettagliOrdine (id, importo, iva, data, quantità, idUtente, idPagamento, stato) values (2, 210.00, 22.00, '2024-02-05', 1, 2, 2, "Spedito");
insert into dettagliOrdine (id, importo, iva, data, quantità, idUtente, idPagamento, stato) values (3, 640.00, 22.00, '2024-02-04', 1, 2, 3, "In elaborazione");

drop table if exists ordine;

create table ordine (
	id int(3) not null AUTO_INCREMENT,
    idOrdine int(3) not null,
    file varchar(1000),
    quantitàProdotto int(11) not null,
    prezzo double not null,
    immagineProdotto varchar(1000) not null,
    nomeProdotto varchar(30) not null,
    coloreProdotto varchar(100) not null,
    primary key (id),
    foreign key (idOrdine) references dettagliOrdine(id) on update cascade on delete cascade
) ENGINE=InnoDB AUTO_INCREMENT=1 default CHARSET=latin1;

insert into ordine (id, idOrdine, quantitàProdotto, prezzo, immagineProdotto, nomeProdotto, coloreProdotto) values (1, 1, 3, '165', 'Ray-Ban Burbank 0RB2283 VerdeTartarugato.avif', 'Ray-Ban Burbank 0RB2283', 'Verde/Tartarugato');
insert into ordine (id, idOrdine, quantitàProdotto, prezzo, immagineProdotto, nomeProdotto, coloreProdotto) values (2, 1, 1, '165', 'Ray-Ban OCTAGON 0RB1972 BluArgento.avif', 'Ray-Ban OCTAGON 0RB1972', 'Blu/Argento');
insert into ordine (id, idOrdine, quantitàProdotto, prezzo, immagineProdotto, nomeProdotto, coloreProdotto) values (3, 2, 1, '210', 'Persol PO3255S VerdeNero.avif', 'Persol PO3255S', 'Verde/Nero');
insert into ordine (id, idOrdine, quantitàProdotto, prezzo, immagineProdotto, nomeProdotto, coloreProdotto) values (4, 3, 1, '210', 'Prada PR 09WS GrigioTrasparente.avif', 'Prada PR 09WS', 'Grigio/Trasparente');
insert into ordine (id, idOrdine, quantitàProdotto, prezzo, immagineProdotto, nomeProdotto, coloreProdotto) values (5, 3, 1, '430', 'Gucci GG0287S MarroneNeroOro.avif', 'Gucci GG0287S', 'Marrone/Nero/Oro');