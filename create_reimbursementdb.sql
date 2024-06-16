CREATE DATABASE reimbursementdb;

-- Connexion à la nouvelle base de données
\c reimbursementdb

-- Créer un utilisateur avec des privilèges
CREATE USER admin WITH ENCRYPTED PASSWORD 'admin';

-- Attribuer tous les privilèges sur la base de données à l'utilisateur
GRANT ALL PRIVILEGES ON DATABASE reimbursementdb TO admin;
