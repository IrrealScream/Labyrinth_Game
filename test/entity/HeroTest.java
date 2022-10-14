package entity;

import entity.items.*;
import labyrinth.*;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class HeroTest {

    private Hero player;
    private Labyrinth maze;
    private Gem gem;

    @Before
    public void before() {
        this.maze = new LabyrinthChessType(1,1);
        this.player = new Hero("hero", 100, 1);
        this.player.setLocation(this.maze.getStartCell());
        this.gem = new Gem();
        this.gem.setLocation(this.maze.getStartCell());
    }

    @Test
    public void TestCollectItem() {
        assertEquals(this.player.getInventory().size(), 0);
        assertFalse(this.player.isInInventory(this.gem));
        
        this.player.collectAnItem(this.gem);
        assertEquals(this.player.getInventory().size(), 1);
        assertTrue(this.player.isInInventory(this.gem));
        assertEquals(this.gem.getLocation(), null);
    }

    @Test
    public void TestDropItem() {
        this.player.collectAnItem(this.gem);
        assertFalse(this.maze.getStartCell().getEntities().contains(this.gem));
        assertEquals(this.player.getInventory().size(), 1);
        assertTrue(this.player.isInInventory(this.gem));
        assertEquals(this.gem.getLocation(), null);
        
        this.player.dropAnItem(this.gem);
        assertTrue(this.maze.getStartCell().getEntities().contains(this.gem));
        assertEquals(this.player.getInventory().size(), 0);
        assertFalse(this.player.isInInventory(this.gem));
        assertEquals(this.gem.getLocation(), this.maze.getStartCell());
    }

    // ---Pour permettre l'exécution des test----------------------
    public static junit.framework.Test suite() {
    return new junit.framework.JUnit4TestAdapter(entity.HeroTest.class);
}
}
