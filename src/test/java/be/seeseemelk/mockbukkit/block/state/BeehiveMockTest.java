package be.seeseemelk.mockbukkit.block.state;

import be.seeseemelk.mockbukkit.WorldMock;
import be.seeseemelk.mockbukkit.block.BlockMock;
import org.bukkit.Location;
import org.bukkit.Material;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BeehiveMockTest
{

	private WorldMock world;
	private BlockMock block;
	private BeehiveMock beehive;

	@BeforeEach
	void setUp()
	{
		this.world = new WorldMock();
		this.block = world.getBlockAt(0, 10, 0);
		this.block.setType(Material.BEEHIVE);
		this.beehive = new BeehiveMock(this.block);
	}

	@Test
	void constructor_DefaultValues()
	{
		assertEquals(3, beehive.getMaxEntities());
		assertEquals(0, beehive.getEntityCount());
		assertNull(beehive.getFlower());
	}

	@Test
	void constructor_Material()
	{
		assertDoesNotThrow(() -> new BeehiveMock(Material.BEEHIVE));
	}

	@Test
	void constructor_Material_NotBeehive_ThrowsException()
	{
		assertThrowsExactly(IllegalArgumentException.class, () -> new BeehiveMock(Material.BEDROCK));
	}

	@Test
	void constructor_Block()
	{
		assertDoesNotThrow(() -> new BeehiveMock(new BlockMock(Material.BEEHIVE)));
	}

	@Test
	void constructor_Block_NotBeehive_ThrowsException()
	{
		assertThrowsExactly(IllegalArgumentException.class, () -> new BeehiveMock(new BlockMock(Material.BEDROCK)));
	}

	@Test
	void constructor_Clone_CopiesValues()
	{
		beehive.setFlower(new Location(world, 1, 2, 3));
		beehive.setMaxEntities(5);
		beehive.setSedated(true);

		BeehiveMock cloned = new BeehiveMock(beehive);

		assertEquals(new Location(world, 1, 2, 3), cloned.getFlower());
		assertEquals(5, cloned.getMaxEntities());
		assertTrue(cloned.isSedated());
	}

	@Test
	void setFlower()
	{
		Location location = new Location(world, 0, 0, 0);

		beehive.setFlower(location);

		assertEquals(location, beehive.getFlower());
	}

	@Test
	void setFlower_Null()
	{
		beehive.setFlower(null);

		assertNull(beehive.getFlower());
	}

	@Test
	void setFlower_DifferentWorld_ThrowsException()
	{
		Location location = new Location(new WorldMock(), 0, 0, 0);

		assertThrowsExactly(IllegalArgumentException.class, () -> beehive.setFlower(location));
	}

	@Test
	void setSedated()
	{
		beehive.setSedated(true);

		assertTrue(beehive.isSedated());
	}

	@Test
	void setMaxEntities()
	{
		beehive.setMaxEntities(5);

		assertEquals(5, beehive.getMaxEntities());
	}

	@Test
	void setMaxEntities_LessThanZero_ThrowsException()
	{
		assertThrowsExactly(IllegalArgumentException.class, () -> beehive.setMaxEntities(-1));
	}

	@Test
	void getSnapshot_DifferentInstance()
	{
		assertNotSame(beehive, beehive.getSnapshot());
	}

	@Test
	void blockStateMock_Mock_CorrectType()
	{
		assertInstanceOf(BeehiveMock.class, BlockStateMock.mockState(block));
	}

}
