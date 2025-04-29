# Shopping Cart App (MVI + Clean Architecture)

## Overview
A simple Shopping Cart application built using:
- MVI (Model-View-Intent) architecture
- Clean Architecture principles
- Kotlin and Jetpack Compose

The app allows users to:
- Add items to the cart
- Remove items from the cart
- Update item quantities
- View total price

---

## Architecture Layers

- **Presentation Layer**: UI (Jetpack Compose) and ViewModel
- **Domain Layer**: Use Cases and Repository interface
- **Data Layer**: In-memory Data Source and Repository implementation

---

## MVI Components

- **Intent**: Defines actions (AddItem, RemoveItem, UpdateQuantity, LoadCart)
- **State**: Represents UI state (Loading, Success, Error)
- **ViewModel**: Handles Intents and updates the State with StateFlow

---

## How to Run

1. Clone the project.
2. Open it in Android Studio.
3. Run the app on an emulator or device.

---

## Features

- Add, remove, update cart items
- Real-time UI updates with StateFlow
- Manual dependency injection (no Hilt)

---
