-- SCRIPT DE CREATION DE L'ENTREPOT DE DONNEES POUR LES SCHEMAS EN ETOILES DES FAITS DE VENTES ET D'ACHATS
-- author : Djebien Tarik
-- date : 29/10/2011

-- DIMENSION TEMPS
create table temps (
id_temps number(20) constraint temps_pkey primary key,
semaine number(1) constraint semaine_enum check (semaine in (1,2,3,4)),
mois number(2) constraint mois_enum check (mois in (1,2,3,4,5,6,7,8,9,10,11,12)),
trimestre number(1) constraint trimestre_enum check (trimestre in (1,2,3,4)),
annee number(4)
);


-- DIMENSION VENDEUR
create table vendeur (
code_vendeur varchar2(20) constraint vendeur_pkey primary key
);


-- DIMENSION CATEGORIE
create table categorie (
code_categorie varchar2(20) constraint categorie_pkey primary key,
nom_categorie varchar2(30),
description varchar2(255),
illustration_url varchar2(255)
);


-- DIMENSION PRODUIT
create table produit (
ref_produit varchar2(20) constraint produit_pkey primary key,
nom_produit varchar2(30),
code_categorie varchar2(20) constraint produit_categorie_fkey references categorie(code_categorie),
quantite_unite varchar2(20),
prix_unite number(10),
unite_stock number(10),
unite_commandee number(10),
niveau_reappro number(10),
indisponible number(1) constraint indispo_enum check (indisponible in (0,1))
);

-- DIMENSION COMMANDE
create table commande (
num_commande number(10) constraint commande_pkey primary key,
ref_client varchar2(5) constraint commande_client_fkey references client(code_client),
date_commande date not null,
date_lim_livraison date,
date_envoi date,
port number(10),
destinataire varchar2(32),
adresse varchar2(255),
ville varchar2(32),
region varchar2(32),
code_postal varchar2(8),
pays varchar2(15)
);

-- DIMENSION CLIENT
create table client (
code_client varchar2(5) constraint client_pkey primary key,
societe varchar2(255),
contact varchar2(255),
fonction varchar2(255),
adresse varchar2(255),
ville varchar2(32),
region varchar2(32),
code_postal varchar2(8),
pays varchar2(15),
telephone varchar2(20),
fax varchar2(20)
);


-- DIMENSION FOURNISSEUR
create table fournisseur (
code_fournisseur varchar2(20) constraint fournisseur_pkey primary key
);

-- FAIT DE VENTES

create table fait_ventes (
id_temps number(20) constraint fait_ventes_temps_fkey references temps(id_temps),
code_client varchar2(5) constraint fait_ventes_client_fkey references client(code_client),
ref_produit varchar2(20) constraint fait_ventes_produit_fkey references produit(ref_produit),
code_vendeur varchar2(20) constraint fait_ventes_vendeurs_fkey references vendeur(code_vendeur),
num_commande varchar2(20) constraint fait_ventes_commande_fkey references commande(num_commande)
);

-- FAIT ACHATS

create table fait_achat (
ref_produit varchar2(20) constraint fait_achats_produit_fkey references produit(ref_produit),
id_temps number(20) constraint fait_achats_temps_fkey references temps(id_temps),
code_fournisseur varchar2(20) constraint fait_achats_fournisseurs_fkey references fournisseur(code_fournisseur)
);


