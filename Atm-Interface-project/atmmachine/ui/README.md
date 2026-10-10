# 🏦 Apex Quantum Bank - Atm Interface

A highly structured, robust command-line interface (CLI) ATM application built using Java. This project demonstrates clean architectural separation of concerns by breaking down features into distinct packages for models, user interfaces, services, exceptions, and logging frameworks.

---

## 📂 Project Architecture Layout

The codebase is organized modularly to enforce high cohesion and loose coupling:

```text
atmmachine/
│
├── Main.java                # Central entry-point controller & pipeline dispatcher
│
├── exception/
│   └── AtmException.java     # Custom exceptions handling business rule violations
│
├── model/
│   └── BankAccount.java      # State tracking data model for user accounts
│
├── service/
│   ├── AtmService.java       # Interface decoupling implementation details
│   └── AtmServiceImpl.java   # Core transactional database logic execution
│
├── ui/
│   └── AtmInterfaceScreen.java # UI layout rendering components & ASCII frames
│
└── util/
    └── ConsoleLogger.java    # Custom runtime trace telemetry buffer logger
