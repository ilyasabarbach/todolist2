# Grille d'évaluation - Projet Android TodoList

## 1. Fonctionnalité minimum requise (/7)

- Application avec authentification (Login/Register)
- Minimum 3 écrans fonctionnels avec navigation
- Accès à une base de données locale (Room ou SQLite)
- Appels à une API externe REST (GET, POST) (via Retrofit ou autre)
- Gestion du cycle de vie (ViewModel, LiveData…)

## 2. Architecture et bonnes pratiques (/3)

- Respect du pattern MVVM ou Clean Architecture
- Séparation claire des couches (UI, logique métier, données)
- Gestion d'erreurs et de cas non prévus (offline, erreurs API…)

## 3. Interface utilisateur (UI/UX) (/2)

- UI responsive et fluide
- Utilisation de RecyclerView, ConstraintLayout, etc.
- Thème clair/sombre (optionnel)
- Suivi des guidelines Material Design

## 4. Persistance des données (/2)

- Base de données locale (Room)
- Partage de données avec SharedPreferences si nécessaire
- Synchronisation locale / distante (optionnel)

## 5. Connexion réseau (/2)

- Accès à des APIs via Retrofit, Volley, ou autre
- Traitement des erreurs réseau, affichage de messages appropriés
- Chargement asynchrone (coroutines, RxJava…)

## 6. Qualité du code (/2)

- Lisibilité
- Code simple et efficace
- Commentaires
- Bonnes pratiques

## 7. Documentation et présentation du projet (/2)

- README détaillé 