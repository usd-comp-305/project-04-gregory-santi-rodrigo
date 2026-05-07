package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static org.junit.jupiter.api.Assertions.*;

public class MenuItemTest {
    final static int POSITIVE_PRICE = 0;
    final static double THOUSANDTH_DECIMAL = 0.001;

    @Test
    public void testBBQReturnsCorrectName(){
        assertEquals("Ribs", BBQMenuItem.RIBS.getName(),
                "RIBS should return its display name.");
        assertEquals("Brisket", BBQMenuItem.BRISKET.getName());
        assertEquals("Pulled Pork", BBQMenuItem.PULLED_PORK.getName());
    }

    @Test
    public void testBBQReturnsCorrectPrice(){
        assertEquals(22.99, BBQMenuItem.RIBS.getBasePrice(), THOUSANDTH_DECIMAL,
                "RIBS should return its base price.");
        assertEquals(18.99, BBQMenuItem.BRISKET.getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(16.99, BBQMenuItem.PULLED_PORK.getBasePrice(), THOUSANDTH_DECIMAL);
    }

    @Test
    public void testBurgerReturnsCorrectName(){
        assertEquals("Cheeseburger", BurgerMenuItem.CHEESEBURGER.getName(),
                "CHEESEBURGER should return its display name.");
        assertEquals("Double Cheeseburger", BurgerMenuItem.DOUBLE_CHEESEBURGER.getName());
        assertEquals("Triple Cheeseburger", BurgerMenuItem.TRIPLE_CHEESEBURGER.getName());
    }

    @Test
    public void testBurgerReturnsCorrectPrice(){
        assertEquals(8.99, BurgerMenuItem.CHEESEBURGER.getBasePrice(), THOUSANDTH_DECIMAL,
                "CHEESEBURGER should return its base price.");
        assertEquals(11.99, BurgerMenuItem.DOUBLE_CHEESEBURGER.getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(14.99, BurgerMenuItem.TRIPLE_CHEESEBURGER.getBasePrice(), THOUSANDTH_DECIMAL);
    }

    @Test
    public void testCafeReturnsCorrectName(){
        assertEquals("Coffee", CafeMenuItem.COFFEE.getName(),
                "COFFEE should return its display name.");
        assertEquals("Latte", CafeMenuItem.LATTE.getName());
        assertEquals("Pastry", CafeMenuItem.PASTRY.getName());
    }

    @Test
    public void testCafeReturnsCorrectPrice(){
        assertEquals(3.99, CafeMenuItem.COFFEE.getBasePrice(), THOUSANDTH_DECIMAL,
                "COFFEE should return its base price.");
        assertEquals(5.99, CafeMenuItem.LATTE.getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(4.49, CafeMenuItem.PASTRY.getBasePrice(), THOUSANDTH_DECIMAL);
    }

    @Test
    public void testFineDiningReturnsCorrectName(){
        assertEquals("Steak", FineDiningMenuItem.STEAK.getName(),
                "Steak should return its display name.");
        assertEquals("Pasta", FineDiningMenuItem.PASTA.getName());
        assertEquals("Caviar", FineDiningMenuItem.CAVIAR.getName());
    }

    @Test
    public void testFineDiningReturnsCorrectPrice(){
        assertEquals(45.99, FineDiningMenuItem.STEAK.getBasePrice(), THOUSANDTH_DECIMAL,
                "COFFEE should return its base price.");
        assertEquals(24.99, FineDiningMenuItem.PASTA.getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(89.99, FineDiningMenuItem.CAVIAR.getBasePrice(), THOUSANDTH_DECIMAL);
    }

    @Test
    public void testHotDogReturnsCorrectName(){
        assertEquals("Plain Hot Dog", HotDogMenuItem.PLAIN_HOT_DOG.getName(),
                "Plain should return its display name.");
        assertEquals("Chili Dog", HotDogMenuItem.CHILI_DOG.getName());
        assertEquals("Condiment Hot Dog", HotDogMenuItem.CONDIMENT_HOT_DOG.getName());
    }

    @Test
    public void testHotDogReturnsCorrectPrice(){
    }

    @Test
    public void testIceCreamReturnsCorrectName(){
    }

    @Test
    public void testIceCreamReturnsCorrectPrice(){
    }

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
        assertEquals(4.99, JuiceBarMenuItem.ORANGE_JUICE.getBasePrice(), THOUSANDTH_DECIMAL,
                "ORANGE_JUICE should return its base price.");
        assertEquals(7.99, JuiceBarMenuItem.STRAWBERRY_SMOOTHIE.getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(12.99, JuiceBarMenuItem.ACAI_BOWL.getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(3.99, JuiceBarMenuItem.GINGER_SHOT.getBasePrice(), THOUSANDTH_DECIMAL);
    }

    @Test
    public void testPizzaReturnsCorrectName(){
    }

    @Test
    public void testPizzaReturnsCorrectPrice(){
    }

    @Test
    public void testSushiReturnsCorrectName(){
    }

    @Test
    public void testSushiReturnsCorrectPrice(){
    }

    @Test
    public void testTacoReturnsCorrectName(){
    }

    @Test
    public void testTacoReturnsCorrectPrice(){
    }

    @ParameterizedTest
    @EnumSource(BBQMenuItem.class)
    public void testBBQItemsHavePositivePrice(BBQMenuItem item){
        assertTrue(item.getBasePrice() > POSITIVE_PRICE,
                item.getName() + " should have a positive base price.");
    }

    @ParameterizedTest
    @EnumSource(BBQMenuItem.class)
    public void testBBQItemsHaveNonEmptyName(BBQMenuItem item){
        assertFalse(item.getName().isBlank(),
                "Every menu item should have a non-empty display name.");
    }

    @ParameterizedTest
    @EnumSource(BurgerMenuItem.class)
    public void testBurgerItemsHavePositivePrice(BurgerMenuItem item){
        assertTrue(item.getBasePrice() > POSITIVE_PRICE);
    }

    @ParameterizedTest
    @EnumSource(BurgerMenuItem.class)
    public void testBurgerItemsHaveNonEmptyName(BurgerMenuItem item){
        assertFalse(item.getName().isBlank());
    }

    @ParameterizedTest
    @EnumSource(CafeMenuItem.class)
    public void testCafeItemsHavePositivePrice(CafeMenuItem item){
        assertTrue(item.getBasePrice() > POSITIVE_PRICE);
    }

    @ParameterizedTest
    @EnumSource(CafeMenuItem.class)
    public void testCafeItemsHaveNonEmptyName(CafeMenuItem item){
        assertFalse(item.getName().isBlank());
    }

    @ParameterizedTest
    @EnumSource(JuiceBarMenuItem.class)
    public void testJuiceBarItemsHavePositivePrice(JuiceBarMenuItem item){
        assertTrue(item.getBasePrice() > POSITIVE_PRICE);
    }

    @ParameterizedTest
    @EnumSource(JuiceBarMenuItem.class)
    public void testJuiceBarItemsHaveNonEmptyName(JuiceBarMenuItem item){
        assertFalse(item.getName().isBlank());
    }
}
