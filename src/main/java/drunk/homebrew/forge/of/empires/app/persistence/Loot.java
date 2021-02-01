package drunk.homebrew.forge.of.empires.app.persistence;

public class Loot {
	private long id;
	private float chance;
	private int amount;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public float getChance() {
		return chance;
	}
	public void setChance(float chance) {
		this.chance = chance;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
