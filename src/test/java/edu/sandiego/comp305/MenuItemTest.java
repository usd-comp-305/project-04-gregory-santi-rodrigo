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

    }
}
