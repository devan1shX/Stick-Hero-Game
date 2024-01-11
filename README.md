# Stick Hero Game

Stick Hero is a simple JavaFX game where the player needs to create a stick to cross platforms.

## Table of Contents
- [Introduction](#introduction)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
  - [Starting the Game](#starting-the-game)
  - [Loading Previous Game](#loading-previous-game)
  - [Game Controls](#game-controls)
- [Contributing](#contributing)
- [Game Classes](#game-classes)
  - [HelloApplication](#helloapplication)
  - [Main](#main)
  - [PauseMenu](#pausemenu)
  - [Pillar](#pillar)
  - [Stick](#stick)
- [License](#license)

## Introduction

Stick Hero is a game built in Java using JavaFX for the graphical interface. The game involves creating a stick to cross platforms and accumulate a score.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 20.0.2 or later
- IntelliJ IDEA or any Java IDE of your choice

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/stick-hero-game.git

## Assumptions

1. Please change the paths of the input files according to your system
2. JUnit testing is not running due to some dependencies, so u just have to uncomment the code in "Main.java" under "gameOver()" file to run and test junit testing and uncomment some import statement also
3. There's a flaw in the score update thing, as we got our score printed on console right, but we were not able to add it in Label and scene
