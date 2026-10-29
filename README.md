# War Card Game

A console-based War Card Game developed in Java using Object-Oriented Programming (OOP). The game supports 2–8 players, loads a standard 52-card deck from a text file, performs perfect shuffling, and determines the winner of each round based on card rank and suit priority.

## Features

- **File-Based Deck Loading** — Loads the 52-card deck from `input.txt` and validates the card entries.
- **Input Validation** — Ensures the number of players is between 2 and 8 and the shuffle count is non-negative.
- **Perfect Shuffling** — Splits the deck into two halves and interleaves their cards.
- **Multiple Shuffles** — Allows the user to specify how many times the deck should be shuffled.
- **Card Distribution** — Distributes cards among players in round-robin order.
- **Round-Based Gameplay** — Each active player plays one card per round.
- **Card Comparison** — Determines the highest card using rank priority first, followed by suit priority.
- **Card Collection** — The round winner collects the played cards in the specified order.
- **Player Elimination** — Players with no remaining cards are eliminated after the round is resolved.
- **Game Winner Detection** — The game ends when a player collects all 52 cards.
- **Replay Option** — Allows the user to start a new game without restarting the application.

## Game Rules

### Number of Players

- Minimum: 2 players
- Maximum: 8 players

### Card Ranking

Cards are compared by rank first, then by suit if their ranks are equal.

**Rank priority, highest to lowest:**

Ace > King > Queen > Jack > 10 > 9 > 8 > 7 > 6 > 5 > 4 > 3 > 2

**Suit priority, highest to lowest:**

Diamond > Heart > Spade > Club

### Round Execution

1. Each active player plays the top card from their deck.
2. The cards are compared to determine the round winner.
3. The winner collects the played cards, with the winning card placed on top in the collection order.
4. The collected cards are added to the bottom of the winner's remaining deck.
5. Players with no cards are eliminated after the round is resolved.
6. The game continues until one player has all 52 cards.

Tied ranks are resolved using suit priority, so two different cards cannot tie under the specified rules.

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Java Collections Framework
- File I/O
- IntelliJ IDEA

## Setup and Installation

### Prerequisites

- Java Development Kit (JDK) compatible with the project's configured Java version
- IntelliJ IDEA or another Java IDE

### 1. Clone the Repository

```bash
git clone <your-repository-url>
```

Replace `<your-repository-url>` with the URL of your GitHub repository.

### 2. Open the Project

Open the cloned project in IntelliJ IDEA and allow the IDE to configure the Java project.

### 3. Prepare the Deck File

Ensure that `input.txt` is located at:

```text
src/main/resources/input.txt
```

The file must contain all 52 unique cards using the required card format.

Example:

```text
D-A,D-K,D-Q,D-J,D-10,D-9,D-8,D-7,D-6,D-5,D-4,D-3,D-2
H-A,H-K,H-Q,H-J,H-10,H-9,H-8,H-7,H-6,H-5,H-4,H-3,H-2
S-A,S-K,S-Q,S-J,S-10,S-9,S-8,S-7,S-6,S-5,S-4,S-3,S-2
C-A,C-K,C-Q,C-J,C-10,C-9,C-8,C-7,C-6,C-5,C-4,C-3,C-2
```

The file parser supports comma-separated and whitespace-separated card entries.

### 4. Run the Application

Run `Main.java` from your IDE.

The application displays the game title, waits for the user to press Enter, validates the deck file, and then requests the number of players and shuffle count.

## Future Improvements

Potential enhancements include:

- Automated unit tests for deck validation, shuffling, distribution, and card comparison.
- Improved error reporting for invalid deck files.
- More detailed game statistics and round summaries.
- Additional game rules or alternative card-comparison strategies.

## Author

**JasonTheAlchemist**

Java development project focused on object-oriented design, algorithm implementation, and coding best practices.
