package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static org.junit.jupiter.api.Assertions.*;

public class MenuItemTest {

    @Test
    public void testJuiceBarReturnsCorrectName(){
        assertEquals("Orange Juice", JuiceBarMenuItem.ORANGE_JUICE.getName(),
                "ORANGE_JUICE should return its display name.");
        assertEquals("Strawberry Smoothie", JuiceBarMenuItem.STRAWBERRY_SMOOTHIE.getName());
        assertEquals("Acai Bowl", JuiceBarMenuItem.ACAI_BOWL.getName());
        assertEquals("Ginger Shot", JuiceBarMenuItem.GINGER_SHOT.getName());
    }

    @Test
    public void testJuiceBarReturnsCorrectPrice(){
        assertEquals(4.99, JuiceBarMenuItem.ORANGE_JUICE.getBasePrice(), 0.001,
                "ORANGE_JUICE should return its base price.");
        assertEquals(7.99, JuiceBarMenuItem.STRAWBERRY_SMOOTHIE.getBasePrice(), 0.001);
        assertEquals(12.99, JuiceBarMenuItem.ACAI_BOWL.getBasePrice(), 0.001);
        assertEquals(3.99, JuiceBarMenuItem.GINGER_SHOT.getBasePrice(), 0.001);
    }

    @ParameterizedTest
    @EnumSource(JuiceBarMenuItem.class)
    public void testJuiceBarItemsHavePositivePrice(JuiceBarMenuItem item){
        assertTrue(item.getBasePrice() > 0,
                item.getName() + " should have a positive base price.");
    }

    @ParameterizedTest
    @EnumSource(JuiceBarMenuItem.class)
    public void testJuiceBarItemsHaveNonEmptyName(JuiceBarMenuItem item){
        assertFalse(item.getName().isBlank(),
                "Every menu item should have a non-empty display name.");
    }
}
