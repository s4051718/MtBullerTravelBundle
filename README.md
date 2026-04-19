# Mt Buller Travel Bundle

A fully-featured Java console application designed for Mt Buller winter resort booking agents. This system allows agents to effortlessly manage customers, accommodations, and build comprehensive travel bundles including lift passes and ski lessons.

## Features

- **Dynamic Customer Management**:
  - Automatically generates unique, incrementing IDs for new customers.
  - Search for existing customers seamlessly by **ID** or **Name**.
  - Skier level tracking (Beginner, Intermediate, Expert) that dynamically affects lesson pricing.

- **Smart Travel Bundles**:
  - Validates all bundle dates (no past bookings allowed).
  - Automatically calculates and applies discounts (e.g., 10% off for 5+ day passes).
  - Automatically prevents illogical bookings (e.g., booking more lesson days or lift pass days than the total stay duration).

- **Robust Architecture**:
  - **Object-Oriented Design**: Utilizes Abstract classes (`Accommodation`) and Interfaces (`Pricable`) for clean, extensible code.
  - **Persistent Data**: Saves all bundles and customer data to secure local binary files (`.ser`) using Java Serialization, ensuring no data is lost between sessions.
  - **Crash-Proof UX**: Exhaustive custom error handling (`MtBullerException`) and `try-catch` blocks protect the application from incorrect inputs, typos, and missing data lookups.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) installed (Version 8 or higher).

### Compilation and Execution
1. Navigate to the root directory of the project in your terminal.
2. Compile the source code by compiling all java files in src into a bin directory.
3. Run the Admin driver application using `java -cp bin MtBullerAdmin`

## Usage Instructions

Upon launching the application, you will be greeted by the **Mt Buller Resort Admin** menu. You can navigate the menu using the corresponding numbers:

- **1 & 2**: View all accommodations or filter by availability. (Adding an accommodation to a bundle automatically marks it as booked!)
- **3 & 4**: Register new customers or view the current customer database.
- **5**: **Create a bundle**. The wizard will walk you through selecting/creating a customer, choosing an accommodation, setting dates, and adding lift passes/lessons.
- **6**: List all active travel bundles with a beautiful, hierarchical summary.
- **7 & 8**: Safely add additional lift passes or lessons to existing bundles.
- **9 & 10**: Persist your current session to a `.ser` database, or load an existing database.

Enjoy your stay at Mt Buller!

Repo Link https://github.com/s4051718/MtBullerTravelBundle