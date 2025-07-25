# **TP : API de gestion de tâches**

### 🎯 Objectif

Créer une API REST avec Spring Boot pour gérer des utilisateurs, projets et tâches. Les tests doivent être effectués via un fichier `test.http`.

---

### 📚 Fonctionnalités attendues

### 🔹 Utilisateur

- `POST /users` → Créer un utilisateur
- `GET /users/{id}` → Afficher un utilisateur
- `GET /users/{id}/projects` → Projets créés par l’utilisateur
- `GET /users/{id}/tasks` → Tâches assignées à l’utilisateur

### 🔹 Projet

- `POST /projects` → Créer un projet (avec ID du créateur)
- `GET /projects/{id}` → Détails d’un projet avec ses tâches

### 🔹 Tâche

- `POST /tasks` → Créer une tâche (avec ID du projet et de l’utilisateur assigné)
- `PATCH /tasks/{id}` → Modifier le statut (Enum : `TODO`, `IN_PROGRESS`, `DONE`)
- `GET /projects/{id}/tasks` → Lister les tâches d’un projet
