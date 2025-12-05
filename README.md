# Koin Sample App

This sample is the exact same example of https://github.com/tezov/koin-sample but with Koin Annotations.

Check the medium story: 

This Android sample application demonstrates how to use:
- Clean Architecture
- Jetpack Compose
- Navigation Compose
- Koin dependency injection
- Koin scoped dependencies (user session example)

It shows how to structure a modern Compose application with:
- Separate Data, Domain, and Presentation layers
- ViewModels injected with Koin
- A user session handled through a DI scope
- A simple fake repository providing libraries and profile info

---

## Architecture Overview

### Data Layer
Implements repositories.  
In this sample:
- `LibraryRepository` returns fake libraries
- Provides featured library, favorites, and profile name

### Domain Layer
Contains business logic:
- Use cases:
    - `GetHomeFeaturedLibraryUseCase`
    - `GetFavoriteLibrariesUseCase`
    - `GetProfileNameUseCase`
- Domain models:
    - `Library`
    - `UserSession` (NormalUser, AdminUser)

### Presentation Layer
Jetpack Compose UI + ViewModels:
- Screens:
    - Home
    - Favorites
    - Profile
- Navigation:
    - Bottom navigation bar
    - Navigation host
- ViewModels injected via Koin:
    - `HomeViewModel`
    - `FavoritesViewModel`
    - `ProfileViewModel`

---

## Koin Configuration

### ViewModels

``viewModel { HomeViewModel(get()) }``  
``viewModel { FavoritesViewModel(get()) }``  
``viewModel { ProfileViewModel(get()) }``

### Use Cases

``factory { GetHomeFeaturedLibraryUseCase(get()) }``  
``factory { GetFavoriteLibrariesUseCase(get()) }``  
``factory { GetProfileNameUseCase(get()) }``

### Session Scope

``scope(named("userSession")) {
scope<UserSessionScope> {
    factory {
        val session = get<UserSession>()
        when (session) {
            is UserSession.AdminUser -> ScopedClass("admin from scope")
            is UserSession.NormalUser -> ScopedClass("normal user from scope")
        }
    }
}``

This scope is created only when logging in on the Profile screen. And then ScopedClass will be available.
!! If UserSession has not been fed to the scope, it happily crash.

---

## How the User Session Works

### 1. Not connected
The Profile screen shows:
- "Connect" button
- Clicking it opens a dialog to choose Normal or Admin user

### 2. User chooses a profile
The ViewModel creates the scope:

``
val scope = koin.createScope(
    scopeId = "currentSession",
    qualifier = named<UserSessionScope>()
)
``

Then declares the session:

``scope.declare<UserSession>(UserSession.NormalUser(name))``

The UI updates to show the profile card and session details.

### 3. Use the scoped instance

``scope?.get<ScopedClass>()``

### 4. Disconnect
The ViewModel closes the scope:

``sessionScope?.close()``

The UI returns to the "Connect" screen.

---

## Running the Project

``./gradlew installDebug``

---

## Summary

This sample demonstrates:
- Clean architecture layering
- Compose + Navigation setup
- Koin dependency injection
- ViewModels powered by Koin
- Scoped DI for user session handling
- Simple, testable data/use-case structure

It serves as a compact reference for integrating Koin scopes into a Compose app.
