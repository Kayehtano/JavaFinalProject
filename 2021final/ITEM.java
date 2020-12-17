// Items have different damage and block stats

public class ITEM {
	// Variables
	private String name;
	private int damage;
	private int shield;
	private int cost;
	private String info;
	
	// Constructors
	public ITEM()
	{
		name = "none";
		damage = 0;
		shield = 0;
		cost = 0;
		info = "none";
	}
	
	public ITEM(String newName, int newDamage, int newShield, int newCost, String newInfo)
	{
		name = newName;
		damage = newDamage;
		shield = newShield;
		cost = newCost;
		info = newInfo;
	}
	
	public ITEM(String newName, String newInfo)
	{
		name = newName;
		damage = 0;
		shield = 0;
		cost = 0;
		info = newInfo;
	}
	
	// Getters
	public String getName()
	{
		return name;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public int getBlock()
	{
		return shield;
	}
	
	public int getCost()
	{
		return cost;
	}
	public String getInfo()
	{
		return info;
	}
	
	// Setters
	public void setName(String newName)
	{
		name = newName;
	}
	
	public void setDamage(int newDamage)
	{
		damage = newDamage;
	}
	
	public void setBlock(int newShield)
	{
		shield = newShield;
	}
	
	public void setCost(int newCost)
	{
		cost = newCost;
	}
	public void setInfo(String newInfo)
	{
		info = newInfo;
	}
	
	public String toString()
	{
		String output = "Name: " + name + 
						"\nDamage: " + damage +
						"\nBlock: " + shield + 
						"\nCost: " + cost + 
						"\nInfo: " + info;
		return output;
	}
}
