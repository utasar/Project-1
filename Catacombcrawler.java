import java.util.*;

/* Actor class has been created to take the fields of the hero and monster  */
class Actor {
    private String name;
    private int health, rowNum, colNum;

    /**
     * method to set attributes of Actor using the constructor
     * 
     * @param name   takes the name of the Actor
     * @param health take sthe health of the actor
     * @param rowNum take the current row of actor
     * @param colNum take the current column of actor
     */
    public Actor(String name, int health, int rowNum, int colNum) {
        this.name = name;
        this.health = health;
        this.colNum = colNum;
        this.rowNum = rowNum;
    }

    /**
     * method to return the name of actor
     * 
     * @return Actor name
     */
    public String getName() {
        return name;
    }

    /**
     * method to return the current health of the Actor
     * 
     * @return current health of actor
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * method to decrease the health of the actor
     * 
     * @param damage passes the damage to the method
     */
    public void healthDamage(int damage) {
        this.health -= damage;
    }

    /**
     * method to give HP boost to the hero this method has been created for the
     * extra credits
     * it increases the HP of hero by random health bounded to 6 if the hero defeats
     * the mosnter
     * 
     * @param bonusHealth passes the bonus health to the method
     */
    public void addHealth(int bonusHealth) {
        this.health = this.health + bonusHealth;
    }

    /**
     * method to check if the Actor are alive or not
     * 
     * @return boolean value of actor living or not
     */
    public boolean isLiving() {
        boolean aLive = true;
        if (this.health > 0) {

        } else {
            aLive = false;
        }

        return aLive;
    }

    /**
     * method to get the current row of the Actor
     * 
     * @return the current row of actor
     */
    public int getRow() {
        return this.rowNum;
    }

    /***
     * method to get the current column of the acor
     * 
     * @return the current column of actor
     */
    public int getColumn() {
        return this.colNum;
    }

    /**
     * method to set the position of actor on the catacomb
     * 
     * @param rowNum passes the rownumberto the method
     * @param colNum passes the column number to the method
     */
    public void setPosition(int rowNum, int colNum) {
        this.rowNum = rowNum;
        this.colNum = colNum;
    }
}

class Hero extends Actor { /*
                            * this class has been created to set and pass the attributes of the hero and it
                            * extends from the class Actor
                            */
    /**
     * method to set the attribute of the hero
     * 
     * @param name   passes the name of the hero
     * @param health passes the health of the use
     * @param rowNum passes the row number of the hero
     * @param colNum passes the column number of the hero
     */
    public Hero(String name, int health, int rowNum, int colNum) {
        super(name, health, rowNum, colNum);/* constructor to set the attribute of the hero */
    }

    /**
     * method to attack the mnster health
     * 
     * @param monster takes the Actor monster from the Monster class
     */
    public void attack(Monster monster) {/* Created with help of chatgpt */
        Random r = new Random();
        int damageOnMonster = r.nextInt(10) + 1;
        System.out.printf("%s has hit the %s for %d damage \n", this.getName(), monster.getName(), damageOnMonster);
        monster.healthDamage(damageOnMonster);
    }
}

class Monster extends Actor {/*
                              * this class has been created to set and pass the attributes of the monster and
                              * it extends from the class Actor
                              */
    public Monster(String name, int health, int rowNum, int colNum) {
        super(name, 25, rowNum, colNum);
    }

    /**
     * method to attack the mnster health
     * 
     * @param hero takes the Actor hero from the Hero class
     */
    public void attack(Hero hero) {
        Random r = new Random();
        int damageOnHero = r.nextInt(5) + 1;
        System.out.printf("%s has hit the %s for %d damage \n", this.getName(), hero.getName(), damageOnHero);
        hero.healthDamage(damageOnHero);
    }
}

class Catacomb {
    int size;/* takes the sixe of catacomb */
    Actor[][] rooms;/* creates a two-d array of room for the catacomb */

    /**
     * method to create the catacomb for the game
     * 
     * @param size takes the size of Catacomb
     */
    public Catacomb(int size) {
        this.size = size;
        this.rooms = new Actor[size][size];
    }

    /**
     * method to set the position of actor in the game
     * 
     * @param rowNum passes the row number in the method
     * @param colNum passes the column number in the method
     * @param actor  passes the actor from Aactor in the method
     */
    public void setActor(int rowNum, int colNum, Actor actor) {
        rooms[rowNum][colNum] = actor;

    }

    /**
     * method to return the position of actor in the game
     * 
     * @param rowNum passes the row number in the method
     * @param colNum passes the column number in the method
     * @return returns the room of the actor
     */
    public Actor getActor(int rowNum, int colNum) {
        return rooms[rowNum][colNum];
    }

    /**
     * method to check if the actor is in valid room
     * 
     * @param rowNum passes the row number in the method
     * @param colNum passes the column number in the method
     * @return
     */
    public boolean roomCheck(int rowNum, int colNum) {
        boolean correctRoom = true;
        if (rowNum >= 0 && rowNum < size && colNum >= 0 && colNum < size) {
            correctRoom = true;
        } else {
            correctRoom = false;
        }
        return correctRoom;
    }

    /**
     * method to check if the monster is present or not and this method has been
     * created with the help of chatGpt
     * 
     * @param rowNum takes the current row of the hero
     * @param colNum takes the current column of the hero
     * @return it returns an integer value which counts the number of monster nearby
     */
    public int presenceOfMonster(int rowNum, int colNum) {
        int count = 0;
        int[] directions = { -1, 1 }; // Directions: north(-1) and south(+1), east(+1) and west(-1)

        // Check adjacent rooms (north, south, east, west)
        for (int i : directions) {
            if (roomCheck(rowNum + i, colNum) && rooms[rowNum + i][colNum] instanceof Monster) {
                count++;
            }
            if (roomCheck(rowNum, colNum + i) && rooms[rowNum][colNum + i] instanceof Monster) {
                count++;
            }
        }
        return count;
    }
}

public class Catacombcrawler {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);/* creates a scanner for themain class */
        System.out.print("Enter the name of hero: ");
        String heroName = s.nextLine();/* stores the heroName */
        int catacombSize; /* creates the size of catacomb */
        while (true) { /* loops until the condition is true */
            System.out.print("Enter the size of catacomb: ");
            catacombSize = s.nextInt();
            if (catacombSize >= 5 && catacombSize <= 10) { /* checks if the entered size is between 5 and 10 */
                break;

            } else {
                System.out.println("You have entered an invalid size please choose a size between 5 to 10 inclusive");
            }

        }
        Catacomb game = new Catacomb(catacombSize); /* creates a Catacomb object named game */
        Hero hero = new Hero(heroName, 100, catacombSize, catacombSize);/* creates a hero object from the class Hero */
        game.setActor(0, 0, hero);/* calls the method set actor */
        Random r = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();/* creates an arraylist of monster */
        for (int i = 0; i < (catacombSize * catacombSize) / 6; i++) {
            int monsterRow = r.nextInt(catacombSize);
            int monsterColumn = r.nextInt(catacombSize);
            if (monsterColumn == 0 && monsterRow == 0) {
                continue;

            } else {
                Monster monster = new Monster("Monster" + (i + 1), 25, monsterRow, monsterColumn);
                monsters.add(monster);
                game.setActor(monsterRow, monsterColumn, monster);
            }
        }

        int rowNum = 0;
        int colNum = 0;
        while (hero.isLiving()) { /* loops until hero is living */
            System.out.printf("%s is at %d,%d with %d health \n", hero.getName(), rowNum, colNum, hero.getHealth());
            int monsterPresent = game.presenceOfMonster(rowNum, colNum);/* stores the number of monster */
            System.out.printf("you smell %d monsters nearby \n", monsterPresent);
            System.out.println("Which way do you Want to go (east,west,north,south)?: ");
            String direction = s.next().toLowerCase();/* takes the direction from the user */

            if (direction.equals("east") && game.roomCheck(rowNum, colNum + 1)) {
                colNum++;

            } else if (direction.equals("west") && game.roomCheck(rowNum, colNum - 1)) {
                colNum--;

            } else if (direction.equals("north") && game.roomCheck(rowNum - 1, colNum)) {
                rowNum--;
            } else if (direction.equals("south") && game.roomCheck(rowNum + 1, colNum)) {
                rowNum++;
            } else {
                System.out.println("You can't move that way! please chhose correct direction");
                continue;
            }
            hero.healthDamage(2);/* reduceshero health by 2 for each move */
            if (!hero.isLiving()) {
                System.out.println(hero.getName() + " has died.");
                break;
            }
            Actor actor = game.getActor(rowNum, colNum);/* gets the position of actor */
            if (actor instanceof Monster) {
                Monster monster = (Monster) actor;
                System.out.printf("%s at %d,%d with %d health versus %s at %d, %d with %d health \n",
                        hero.getName(), rowNum, colNum, hero.getHealth(), monster.getName(), rowNum, colNum,
                        monster.getHealth());
                while (hero.isLiving() && monster.isLiving()) {
                    hero.attack(monster);
                    if (monster.isLiving()) {
                        monster.attack(hero);
                    }

                }
                if (!monster.isLiving()) { /* checks if the monster is dead */
                    System.out.printf("%s has been defeated \n", monster.getName());
                    game.setActor(rowNum, colNum, null); /* Remove the monster from the catacomb */
                    monsters.remove(monster);
                    System.out.printf("You killed the monster, %s current health is %d,  ", hero.getName(),
                            hero.getHealth());
                    int bonusHealth = r.nextInt(6);
                    hero.addHealth(bonusHealth);
                    ;/*
                      * This calls the method addHealth it has been created to get the extra credit
                      */
                    System.out.printf("you will get %d Health as bonus.", bonusHealth);
                    System.out.printf("%s has %d health now\n", hero.getName(), hero.getHealth());

                }
            }
            if (rowNum == catacombSize - 1 && colNum == catacombSize - 1) {
                System.out.printf("%s has escaped catacomb with %d health", hero.getName(), hero.getHealth());
                break;
            }

        }
        if (!hero.isLiving()) {
            System.out.printf("%s health is %d and you lose the game", hero.getName(), hero.getHealth());
        }
        s.close();
    }
}
