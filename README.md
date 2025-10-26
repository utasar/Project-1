# Project-1
Helping session tried out
## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

# Catacombcrawler
- the name of the game

## Game Description

- This game create an Actor class which sets the name health and the position os the Character.
- The game has two Characters, hero and monster.hero moves around the catacomb to reach at the bottom of the catacomb and also fights the monster      while moving around. hero has a health point fo 100 while monster has a health point of 25.The hero is set at 0,0 at  the start of the game and the moster is set randomly in the catacomb. the number of monxter is determined by the square of the size divided by 6.if the hero kills a monstre the it gets an bonus health between 1 to6.

## classes

### `Actor`
- the `Actor` class represents the characters in the game.

- **Attributes**:
  - `name`: The name of the Character.
  - `health`: The current health of the Character.
  - `rowNum`: The row position of the Character in the catacomb.
  - `colNum`: The column position of the Character in the catacomb.

- **Methods**:
  - `healthDamage(int damage)`: Decreases the Character's health by the given damage.
  - `addHealth(int bonusHealth)`: Increases the Character's health by the given bonus.
  - `isLiving()`: Checks if the Character is still alive.
  - `getRow()` / `getColumn()`: Returns the current position of the Character.
  - `setPosition(int rowNum, int colNum)`: Sets the Character's position.

### `Hero`
The `Hero` class extends the `Actor` class and represents the player's character. It includes additional function for attacking monsters.

- **Methods**:
  - `attack(Monster monster)`: The hero attacks a monster, dealing random damage between  1 and 10.

### `Monster`
The `Monster` class extends the `Actor` class and represents a monster in the catacomb. It includes additional function for attacking the hero.

- **Methods**:
  - `attack(Hero hero)`: The monster attacks the hero, dealing random damage between 1 and 5.

### `Catacomb`
The `Catacomb` class represents the catacomb's structure. It manages the grid of rooms, Character positions, and checks for the presence of monsters.

- **Methods**:
  - `setActor(int rowNum, int colNum, Actor Character)`: Places an Character in a specific room.
  - `getActor(int rowNum, int colNum)`: Retrieves the Character at a given position.
  - `roomCheck(int rowNum, int colNum)`: Checks if the given room is valid (within bounds).
  - `presenceOfMonster(int rowNum, int colNum)`: Checks if there are monsters in adjacent rooms.

## Game Guide

### dependencies
- to run this code you should have Vs code as ypur IDE and have Java 21 or higher installed on your device

### how to run the project 
- if you have everythong installed on ypur device then you should go to the click on the run at the right corner of the VS window or by clicking on the run from the title bar of the window.

### How to play the Game
- When you run the program, the terminal will ask you for the name of the hero and the size of the Catacomb. then, it displays the position of the hero at 0,0 with 100 health.
- >now, it ask the user for the direction to move. you should enter east,west,north, and south as the direction for the game.and it moves according
to the given dirction.
>
- >>for north:row number decreases by 1.
- >>for south:row number increases by 1.
- >>for west:column number decreases by 1.
- >>for east:column number increases by 1.
- it continues to ask the direction until the hero is alive or has not escaped the catacomb.
- while moving around the catacomb, hero will get to know a monster is in the adjacent room.
> if the hero encounters a monster, the hero will fight the monster and they attack each other the hero damages monster between 1 to 10 while the monster damages the hero betwwn 1 to5.if the monster dies then monster gets removed from the catacomb.

hero wll get bonus haelth between 0 and 5 if the monster dies and this sunction has been added for extra credit.

## lessons learned
- for making this project, I practised the concepts of constructor, inheritance , encapsulation, 2-d arrays which helped me get better understanding of the OOPs.
