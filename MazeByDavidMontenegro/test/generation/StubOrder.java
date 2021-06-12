package generation;

public class StubOrder implements Order{
	
	private int seed;
	private int skillLevel;
	private Builder builder;
	private boolean perfect;
	private Maze mazeConfig;
	private int percentdone;
	
	public StubOrder() {
		this.seed = 13;
		this.skillLevel = 0;
		this.builder = Order.Builder.DFS;
		this.perfect = false;
	}
	
	public StubOrder(int seed, int skillLevel, Builder builder, boolean perfect) {
		this.seed = seed;
		this.skillLevel = skillLevel;
		this.builder = builder;
		this.perfect = perfect;
	}
	
	@Override
	public int getSkillLevel() {
		return skillLevel;
	}

	@Override
	public Builder getBuilder() {
		return builder;
	}

	@Override
	public boolean isPerfect() {
		return perfect;
	}

	@Override
	public int getSeed() {
		return seed;
	}

	@Override
	public void deliver(Maze mazeConfig) {
		this.mazeConfig = mazeConfig;
	}

	@Override
	public void updateProgress(int percentage) {
		 if (this.percentdone < percentage && percentage <= 100)
	            this.percentdone = percentage;
	}
	
	public Maze getMaze() {
		return mazeConfig;
	}

}
