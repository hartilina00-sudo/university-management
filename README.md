#  University Manager

Application web complète de gestion académique universitaire.
 
**Module :** Advanced Web Development — 2026/2027

---

##  Description
University Manager automatise et centralise la gestion des ressources académiques pour trois types d'utilisateurs : **Administrateurs**, **Enseignants** et **Étudiants**.

---

##  Fonctionnalités

###  Administrateur
- Gestion complète des étudiants et enseignants
- Gestion des modules pédagogiques
- Planification des emplois du temps avec **détection automatique des conflits**
- Supervision globale des notes et résultats
- Génération de rapports

###  Enseignant
- Consultation des modules assignés et emploi du temps
- Saisie des notes (TP, DS, Projet) 
- Suivi académique des étudiants

###  Étudiant
- Consultation de l'emploi du temps
- Consultation des notes et moyennes


---

##  Architecture
Architecture **MVC (Model-View-Controller)** en 3 couches :
- **View** : Templates Thymeleaf + CSS personnalisé
- **Controller** : Spring Boot MVC (StudentController, EvaluationController...)
- **Model** : Entités JPA (Student, Teacher, Module, Session, Evaluation)

---

##  Tech Stack

| Couche | Technologie |
|---|---|
| Backend | Spring Boot, Spring MVC, Spring Security |
| ORM | Spring Data JPA / Hibernate |
| Frontend | Thymeleaf, CSS |
| Base de données | MySQL |
| Build | Maven |
| IDE | IntelliJ IDEA |

---

##  Base de Données
**MySQL** — `university_db`

Relations principales :
- `Student` ↔ `Evaluation` : One-to-Many
- `Teacher` ↔ `Module` : One-to-Many
- `Module` ↔ `Session` : One-to-Many

---

##  Lancer le projet

### Prérequis
- Java 17+
- Maven
- MySQL

##  Lancer le projet
- Java 17+, Maven, MySQL requis
- Créer une base `university_db`
- Configurer `application.properties` avec tes credentials
- `mvn spring-boot:run` → http://localhost:8080
