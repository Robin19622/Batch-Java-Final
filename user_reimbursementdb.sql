-- Créer l'utilisateur admin avec le mot de passe 'admin'
CREATE USER admin WITH PASSWORD 'admin';

-- Accorder tous les privilèges sur la base de données à l'utilisateur admin
GRANT ALL PRIVILEGES ON DATABASE reimbursementdb TO admin;