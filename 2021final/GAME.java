// IMPORT CLASSES
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class GAME {
	// This game is named Adventurer. You take the role of an unnamed adventure who takes down monsters 	
	
	// PLAYER STATS
	static int hp = 20; // base hp
	static int damage = 5; // base damage
	static int bBlock = 1; // base block
	static int money = 50; // starting money
	static String weapon = "Dull Sword";
	
	// Items
	static ITEM SharperSword = new ITEM("Sharper Sword", 10, 5, 150, "A sharper sword, sharper than yours.");
	static ITEM SteelSword = new ITEM("Steel Sword", 15, 5, 300, "A steel sword that glimmers in the sun.");
	static ITEM GoldSword = new ITEM("Gold Sword", 20, 16, 450, "A golden sword. Looks yellow and pure.");
	static ITEM MithrilSword = new ITEM("Mithril Sword", 30, 20, 600, "A sword made of mithril. Shiny and lightweight, used by veterans and heroes.");
	
	public static void main(String[] args) {
		// start game
		Game();
	}
	
	// -------- FUNCTIONS --------
	//
	
	public static void Game()
	{
		// -------- INITIALIZATION --------
		
		System.out.println("------ ADVENTURER ------");
		System.out.println("You are an adventurer who is trying to make a living by subjugating monsters. You applied to the adventurer's guild you are equipped with basic equipment.");
		
		// main menu
		Menu();
	}
	
	public static void Menu()
	{
		// Monster Names
		ArrayList<String> monsterNames = new ArrayList<String>();
		monsterNames.add("Cursed Bear");
		monsterNames.add("Zombie");
		monsterNames.add("Demon");
		monsterNames.add("Cursed Rabbit");
		monsterNames.add("Minotaurus");
		monsterNames.add("Cursed Bird");
		
		System.out.println("\n----- HOME -----");
		System.out.println("Enter 1 to hunt monsters. Enter 2 to visit the shop. Enter 3 to see your stats.");
		Scanner input = new Scanner(System.in);
		int check = input.nextInt();
		System.out.println();
		
		// FUNCTIONALITY
		while(check != 1 && check != 2 && check!= 3) // IGNORE ANYTHING OTHER THAN 1, 2, AND 3
		{
			System.out.println("Please enter either 1 to attack, 2 to open the shop, or 3 to see your stats.");
			check = input.nextInt();
		}
		
		String answer = AttackShopStats(check);
		
		if(answer == "Attack")
		{
			hp = 20;
			
			// Randomize stats --
			// name
			Random r = new Random();
			int rName = r.nextInt(monsterNames.size());
			
			int maxLevel = 10;
			int maxDamage = 5;
			int maxMoney = 20;
			
			// level
			int randomLevel = (int)(Math.random() * maxLevel + 1);
			
			// damage
			int randomDamage = (int)(Math.random() * maxDamage + 1);
			
			// health
			int randomHealth = (int)(Math.random() * ((randomLevel / 2) * 10 + 1));
			
			// money
			int randomMoney = (int)(Math.random() * maxMoney + 1);
			
			// --------------------------------
			
			// Instantiate monster - Name, level, damage, health, money
			MONSTER monster = new MONSTER(monsterNames.get(rName), randomLevel, randomDamage, randomHealth, randomMoney);
			
			System.out.println();
			
			// GET STATS
			String monsterName = monster.getKind();
			int monsterLevel = monster.getLevel();
			int monsterHP = monster.getHealth();
			int monsterDamage = monster.getDamage();
			int monsterMoney = (monster.getMoney() * 2) - 5;
			int block = 0;
			boolean turn = true;
			
			System.out.println("\n----- BATTLE -----");
			System.out.println("You are now attacking a level " + monsterLevel + " " + monsterName + "!");
			
			while(hp > 0 && monsterHP > 0)
			{
				if(turn) // PLAYER FIGHT
				{
					// CHECK FOR OFFENSE/DEFENSE
					System.out.println("Would you like to attack (1), block (2), or flee (3)?");
					check = input.nextInt();
					
					if(check == 1) // PLAYER ATTAC
					{
						monsterHP = AttackMonster(damage, monsterHP, monsterName);
						block = 0; // reset block
						turn = !turn;
					}
					else if(check == 2) // PLAYER BLOC
					{
						// The player blocks
						block = (int)(Math.random() * (bBlock) + 1);
						System.out.println("You prepare to block the " + monsterName + "'s attack...");
						turn = !turn;
					}
					else // PLAYER RUN
					{
						Random rng = new Random();
						int ableToFlee = rng.nextInt(2);
						if(ableToFlee == 1)
						{
							System.out.println("You have fled the battle!");
							Menu();
						}
						else
						{
							System.out.println("You tried to get away but failed!\n");
							turn = !turn;
						}
					}
				}
				else // MONSTER FIGHT
				{
					hp = AttackPlayer(monsterName, monsterDamage, hp, block);
					block = 0; // reset block
					turn = !turn;
				}
			}
			
			if(hp < 1)
			{
				System.out.println("\n\n ----- GAME OVER ----- \n");
				System.out.println("YOU DIED. Would you like to replay? (1 - yes | 0 - no)");
				check = input.nextInt();
				
				if(check == 1)
				{
					System.out.println("\n\n\n\n\n");
					Game();
				}
				else
				{
					System.out.println("Thanks for playing!");
					System.exit(0);
				}
			}
			else if(monsterHP < 1)
			{
				System.out.println("\nYou have successfully subjugated a level " + monsterLevel + " " + monsterName + "!");
				System.out.println("You have received " + monsterMoney + " coins for the monster.\n");
				money += monsterMoney;
				Menu();
			}
		}
		else if(answer == "Shop")
		{
			// shopping :)
			System.out.println("\n----- SHOP -----");
			System.out.println("Welcome to the shop! Enter the number of the item you would like to buy. Enter 0 to exit.");
			System.out.println("Your balance is " + money + " coins.\n");
			
			System.out.println("1. " + SharperSword.toString() + "\n");
			System.out.println("2. " + SteelSword.toString() + "\n");
			System.out.println("3. " + GoldSword.toString() + "\n");
			System.out.println("4. " + MithrilSword.toString() + "\n");
			check = input.nextInt();
			
			while(check != 0 && check != 1 && check != 2 && check != 3 && check != 4)
			{
				System.out.println("That is not a valid number!");
			}
			
			if(check == 0)
			{
				Menu();
			}
			else if(check == 1)
			{
				if(money >= SharperSword.getCost())
				{
					System.out.println("You have purchased a " + SharperSword.getName() + "!");
					damage = SharperSword.getDamage();
					bBlock = SharperSword.getBlock();
					money -= SharperSword.getCost();
					weapon = SharperSword.getName();
					System.out.println("You now have " + money + " coins.");
					Menu();
				}
				else
				{
					System.out.println("You do not have enough money to buy " + SharperSword.getName() + ".");
					System.out.println("You currently have " + money + " coins.");
					Menu(); // kick player to menu
				}
			}
			else if(check == 2)
			{
				if(money >= SteelSword.getCost())
				{
					System.out.println("You have purchased a " + SteelSword.getName() + "!");
					damage = SteelSword.getDamage();
					bBlock = SteelSword.getBlock();
					money -= SteelSword.getCost();
					weapon = SteelSword.getName();
					System.out.println("You now have " + money + " coins.");
					Menu();
				}
				else
				{
					System.out.println("You do not have enough money to buy " + SteelSword.getName() + ".");
					System.out.println("You currently have " + money + " coins.");
					Menu(); // kick player to menu
				}
			}
			else if(check == 3)
			{
				if(money >= GoldSword.getCost())
				{
					System.out.println("You have purchased a " + GoldSword.getName() + "!");
					damage = GoldSword.getDamage();
					bBlock = GoldSword.getBlock();
					money -= GoldSword.getCost();
					weapon = GoldSword.getName();
					System.out.println("You now have " + money + " coins.");
					Menu();
				}
				else
				{
					System.out.println("You do not have enough money to buy " + GoldSword.getName() + ".");
					System.out.println("You currently have " + money + " coins.");
					Menu(); // kick player to menu
				}
			}
			else if(check == 4)
			{
				if(money >= MithrilSword.getCost())
				{
					System.out.println("You have purchased a " + MithrilSword.getName() + "!");
					damage = MithrilSword.getDamage();
					bBlock = MithrilSword.getBlock();
					money -= MithrilSword.getCost();
					weapon = MithrilSword.getName();
					System.out.println("You now have " + money + " coins.");
					Menu();
				}
				else
				{
					System.out.println("You do not have enough money to buy " + MithrilSword.getName() + ".");
					System.out.println("You currently have " + money + " coins.");
					Menu(); // kick player to menu
				}
			}
		}
		else if(answer == "Stats")
		{
			System.out.println("Stats: " + 
							   "\nHealth: " + hp +
							   "\nDamage: " + damage + 
							   "\nBlock: " + bBlock + 
							   "\nMoney: " + money + 
							   "\nWeapon: " + weapon + 
							   "\n");
			Menu();
		}
	}
	
	public static String AttackShopStats(int check)
	{
		// Checks
		if(check == 1) // CHECK ATTACK
		{
			return "Attack";
		}
		else if(check == 2) // CHECK SHOP
		{
			return "Shop";
		}
		else
		{
			return "Stats";
		}
	}
	
	public static int AttackMonster(int damage, int monsterHP, String name)
	{
		int dmgDealt = (int)(Math.random() * damage); // get a random number of damage - max is ur stats and min is 0)
		System.out.println("You attack the " + name + " for " + dmgDealt + " damage!");
		monsterHP -= dmgDealt;
		
		if(dmgDealt > 0)
		{
			// if player dealt at least 1 damage
			System.out.println("The " + name + " has " + monsterHP + " HP.");
		}
		else
		{
			// if player did not deal damage
			System.out.println("Your attack missed! The " + name + " has " + monsterHP + " HP.");
		}
		System.out.println();
		return monsterHP; // return health of monster
	}
	
	public static int AttackPlayer(String name, int damage, int hp, int block)
	{
		int dmgDealt = (int)(Math.random() * damage) - block; // Get monster damage minus block
		
		// Set dmg to 0 if blocked for all damage
		if(dmgDealt < 0)
		{
			dmgDealt = 0;
		}
		
		System.out.println("The " + name + " attacks for " + dmgDealt + " damage!");
		hp -= dmgDealt;
		
		if(dmgDealt < 1 && block > 0)
		{
			System.out.println("You blocked the " + name + "'s entire attack!");
		}
		else if(dmgDealt > 0)
		{
			System.out.println("You have " + hp + " HP remaining.");
		}
		else
		{
			System.out.println("The " + name + " missed! You have " + hp + " HP remaining.");
		}
		return hp;
	}
}