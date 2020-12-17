// The monsters have different stats including their name, their level, and how much damage they deal.

public class MONSTER {
	// Variables
	private String kind;
	private int level;
	private int damage;
	private int health;
	private int money;
	
	// Constructors
	public MONSTER()
	{
		kind = "none";
		level = 0;
		damage = 0;
		health = 0;
		money = 0;
	}
	
	public MONSTER(String newKind, int newLevel, int newDamage, int newHealth, int newMoney)
	{
		kind = newKind;
		level = newLevel;
		damage = newDamage;
		health = newHealth;
		money = newMoney;
	}
	
	public MONSTER(String newKind, int newLevel)
	{
		kind = newKind;
		level = newLevel;
		damage = 0;
		health = 0;
		money = 0;
	}
	
	// Getters
	public String getKind()
	{
		return kind;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public int getMoney()
	{
		return money;
	}
	
	// Setters
	public void setKind(String newKind)
	{
		kind = newKind;
	}
	
	public void setLevel(int newLevel)
	{
		level = newLevel;
	}
	
	public void setDamage(int newDamage)
	{
		damage = newDamage;
	}
	
	public void setHealth(int newHealth)
	{
		health = newHealth;
	}
	
	public void setMoney(int newMoney)
	{
		money = newMoney;
	}
}
