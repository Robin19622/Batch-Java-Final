# Project Batch Java

Ce projet va permettre  le traitement par lots de fichiers CSV en une seule fois afin de les analyser et remplir une 
base de données PostgreSQL avec les données.

## Description

Le projet consiste à développer un programme batch en Java qui implémente les fonctionnalités suivantes :

- **Scrutation de Dossier** : Scrute un dossier particulier à la recherche de fichiers CSV nommés `users_<YYYYMMDDHHmmSS>.csv`.
- **Parsing des Fichiers** : Parse ces fichiers au format suivant : <Numero_Securite_Sociale>, <Nom>, <Prenom>, 
<Date_Naissance>, <Numero_Telephone>, <E_Mail>, <ID_Remboursement>, <Code_Soin>, <Montant_Remboursement>
- **Peuplement de la Base de Données** : Insère ou met à jour les entrées dans une base de données PostgreSQL.
- L'ID remboursement est un identifiant qui permet de déterminer s'il s'agit d'un insert ou d'un update.
- Une colonne `<Timestamp_fichier>` est extraite du nom du fichier.
- **Déplacement des Fichiers Traités** : Une fois traités, les fichiers sont déplacés dans un autre dossier.

## Prérequis

- [Java 21](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html)
- [Maven](https://maven.apache.org/install.html)

## Installation

### Cloner le dépôt en HTTPS

```sh
git clone <https://github.com/Robin19622/Batch-Java-Final.git>
```
### Cloner le dépôt en SSH
```sh
git@github.com:Robin19622/Batch-Java-Final.git
```
### Cloner le dépôt en GitHub CLI
```sh
gh repo clone Robin19622/Batch-Java-Final
```

### Installation de la base PostGreSQL

#### Sur pgAdmin4

1. Créer la base de données 'reimbursementdb'
2. Créer l'utilisateur admin avec son mdp 'admin'.
   - Pour cela un script est fourni à savoir **'user_reimbursementdb.sql'**

#### Autres méthodes

Le script **'create_reimbursementdb.sql'** comprend la création de la database.

### Dépendances
- Spring Boot
- Spring Data JPA
- Apache Commons CSV
- PostgreSQL
- JUnit 5
- Mockito
- JaCoCo

## Explication du projet

### Choix des dépendances

- **Spring Boot** : Spring Boot simplifie la configuration et le développement d'applications Java en fournissant des 
configurations par défaut et des intégrations faciles avec divers composants. Dans notre projet, Spring Boot permet de 
démarrer l'application avec une configuration minimale, en utilisant la classe `BatchJavaApplication` pour lancer le 
traitement batch des fichiers CSV.

- **Spring Data JPA** : Spring Data JPA facilite l'intégration avec les bases de données en fournissant une abstraction 
de haut niveau pour les opérations CRUD. Dans notre projet, cela réduit la quantité de code nécessaire pour interagir 
avec la base de données en utilisant des interfaces comme `UserRepository` pour les opérations CRUD sur l'entité `User`.

- **PostgreSQL** : PostgreSQL est un système de gestion de base de données relationnelle open-source réputé pour sa 
robustesse et ses fonctionnalités avancées. Dans notre projet, la configuration de PostgreSQL est définie dans le 
fichier `application.properties`, ce qui permet de gérer les transactions complexes et les opérations de base de données 
de manière efficace.

- **Apache Commons CSV** : Cette bibliothèque facilite le parsing des fichiers CSV, offrant une API simple et efficace 
pour lire et écrire des fichiers CSV. Dans notre projet, la classe `CustomCSVParser` utilise Apache Commons CSV pour 
les fichiers CSV et extraire les données nécessaires.

- **JUnit 5** : JUnit 5 est un framework de test pour Java qui permet de créer et d'exécuter des tests unitaires pour
vérifier que le code fonctionne comme prévu.

- **Mockito** : Mockito
  Mockito est une bibliothèque Java pour les tests unitaires qui permet de créer des objets simulés (mock) afin de 
tester les interactions et le comportement du code sans avoir à utiliser des instances réelles de dépendances.
  (déplacement de fichiers dans des dossier temporaires, etc.)

### Architecture du Projet

Le projet est structuré de manière à séparer les différentes responsabilités en utilisant une architecture en couches.
Voici les différentes couches et leur rôle :

1. **Config** :
- `AppConfig.java` : Contient la configuration de l'application, notamment la configuration de Spring.

2. **DAO (Data Access Object)** :
- `UserRepository.java` : Interface qui étend `CrudRepository` pour les opérations CRUD (Create, Read, Update, Delete) 
sur l'entité `User`.

3. **Model** :
- `User.java` : Classe représentant l'entité `User`, avec des annotations JPA pour le mapping de la base de données.

4. **Service** :
- `FileService.java` : Interface définissant les méthodes pour le traitement des fichiers.
- `FileServiceImpl.java` : Implémentation de l'interface `FileService`. Contient la logique pour scruter le dossier, 
parser les fichiers CSV, insérer ou mettre à jour les enregistrements dans la base de données et déplacer les fichiers 
traités.
- `UserService.java` : UserService.java : Classe de service pour la gestion des utilisateurs. Elle utilise UserRepository
pour les opérations CRUD sur l'entité User.

5. **Util** :
- `CustomCSVParser.java` : Classe utilitaire pour parser les fichiers CSV en utilisant Apache Commons CSV.

6. **Application** :
- `BatchJavaApplication.java` : Classe principale de l'application Spring Boot qui démarre l'application.

### Fonctionnalités

- **Scrutation de Dossier** : L'application scrute le dossier `input/` à la recherche de fichiers CSV nommés selon le 
format `users_<YYYYMMDDHHmmSS>.csv`.
- **Parsing des Fichiers** : Les fichiers CSV sont analysés et les données sont converties en objets `User`.
- **Peuplement de la Base de Données** : Les objets `User` sont insérés ou mis à jour dans la base de données PostgreSQL.
Si un enregistrement avec le même `ID_Remboursement` existe, il est mis à jour, sinon, un nouvel enregistrement est créé.
- **Déplacement des Fichiers Traités** : Une fois les fichiers traités, ils sont déplacés dans le dossier `processed/`.


## Tests unitaires

### Lancement des tests
Pour lancer ces tests il faut exécuter la commande :
```sh
mvn test
```

### Couverture de code avec JaCoCo
JaCoCo est un outil de couverture de code qui va nous permettre de voir quelles parties de notre code sont couvertes par
nos tests.
Pour générer le rapport il faut exécuter la commande :
```sh
mvn clean test jacoco:report
```