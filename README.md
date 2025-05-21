# TodoList

Une application Android de gestion de tâches simple et efficace.

## Fonctionnalités

- Authentification utilisateur (inscription/connexion)
- Création, modification et suppression de tâches
- Synchronisation avec une API REST
- Stockage local des données
- Interface utilisateur Material Design
- Mode hors ligne

## Technologies utilisées

- Java
- Android SDK
- Room Database
- Retrofit pour les appels API
- ViewModel et LiveData
- Material Design Components

## Architecture

L'application suit le pattern MVVM (Model-View-ViewModel) avec les composants suivants :

- **UI Layer**: Activities et Fragments
- **ViewModel**: Gestion du cycle de vie et de la logique métier
- **Repository**: Gestion des données (locale et distante)
- **Room Database**: Stockage local
- **Retrofit**: Communication avec l'API

## Installation

1. Cloner le repository
2. Ouvrir le projet dans Android Studio
3. Synchroniser avec Gradle
4. Lancer l'application

## Configuration requise

- Android Studio
- SDK minimum: API 21 (Android 5.0)
- SDK cible: API 34 (Android 14)

## API utilisée

L'application utilise l'API JSONPlaceholder pour la synchronisation des tâches :
https://jsonplaceholder.typicode.com/ 
