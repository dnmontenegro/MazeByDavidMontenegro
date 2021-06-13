package generation;

import org.junit.Before;

public class MazeBuilderKruskalTest extends MazeFactoryTest {
	
	/**
	 * This method setups the builder fields.
	 */
	@Before
	public void setUp() {
		builder = Order.Builder.Kruskal;
		builder2 = Order.Builder.Kruskal;
	}
}
