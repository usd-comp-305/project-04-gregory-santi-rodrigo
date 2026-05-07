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
                "STEAK should return its display name.");
        assertEquals("Pasta", FineDiningMenuItem.PASTA.getName());
        assertEquals("Caviar", FineDiningMenuItem.CAVIAR.getName());
    }

    @Test
    public void testFineDiningReturnsCorrectPrice(){
        assertEquals(45.99, FineDiningMenuItem.STEAK.getBasePrice(), THOUSANDTH_DECIMAL,
                "STEAK should return its base price.");
        assertEquals(24.99, FineDiningMenuItem.PASTA.getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(89.99, FineDiningMenuItem.CAVIAR.getBasePrice(), THOUSANDTH_DECIMAL);
    }

    @Test
    public void testHotDogReturnsCorrectName(){
        assertEquals("Plain Hot Dog", HotDogMenuItem.PLAIN_HOT_DOG.getName(),
                "PLAIN_HOT_DOG should return its display name.");
        assertEquals("Chili Dog", HotDogMenuItem.CHILI_DOG.getName());
        assertEquals("Condiment Hot Dog", HotDogMenuItem.CONDIMENT_HOT_DOG.getName());
    }

    @Test
    public void testHotDogReturnsCorrectPrice(){
        assertEquals(3.99, HotDogMenuItem.PLAIN_HOT_DOG.getBasePrice(), THOUSANDTH_DECIMAL,
                "PLAIN_HOT_DOG should return its base price.");
        assertEquals(5.99, HotDogMenuItem.CHILI_DOG.getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(4.99, HotDogMenuItem.CONDIMENT_HOT_DOG.getBasePrice(), THOUSANDTH_DECIMAL);
    }

    @Test
    public void testIceCreamReturnsCorrectName(){
        assertEquals("Cone", IceCreamMenuItem.CONE.getName(),
                "CONE should return its display name.");
        assertEquals("Sundae", IceCreamMenuItem.SUNDAE.getName());
        assertEquals("Shake", IceCreamMenuItem.SHAKE.getName());
    }

    @Test
    public void testIceCreamReturnsCorrectPrice(){
        assertEquals(3.99, IceCreamMenuItem.CONE.getBasePrice(), THOUSANDTH_DECIMAL,
                "CONE should return its base price.");
        assertEquals(6.99, IceCreamMenuItem.SUNDAE.getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(7.99, IceCreamMenuItem.SHAKE.getBasePrice(), THOUSANDTH_DECIMAL);
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
        assertEquals("Pepperoni Slice", PizzaMenuItem.PEPPERONI_SLICE.getName(),
                "PEPPERONI_SLICE should return its display name.");
        assertEquals("Pepperoni Whole", PizzaMenuItem.PEPPERONI_WHOLE.getName());
        assertEquals("Margherita Slice", PizzaMenuItem.MARGHERITA_SLICE.getName());
        assertEquals("Margherita Whole", PizzaMenuItem.MARGHERITA_WHOLE.getName());
        assertEquals("Cheese Slice", PizzaMenuItem.CHEESE_SLICE.getName());
        assertEquals("Cheese Whole", PizzaMenuItem.CHEESE_WHOLE.getName());
    }

    @Test
    public void testPizzaReturnsCorrectPrice(){
        assertEquals(3.99, PizzaMenuItem.PEPPERONI_SLICE.getBasePrice(), THOUSANDTH_DECIMAL,
                "PEPPERONI_SLICE should return its base price.");
        assertEquals(18.99, PizzaMenuItem.PEPPERONI_WHOLE.getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(3.49, PizzaMenuItem.MARGHERITA_SLICE.getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(16.99, PizzaMenuItem.MARGHERITA_WHOLE.getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(2.99, PizzaMenuItem.CHEESE_SLICE.getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(14.99, PizzaMenuItem.CHEESE_WHOLE.getBasePrice(), THOUSANDTH_DECIMAL);
    }

    @Test
    public void testSushiReturnsCorrectName(){
        assertEquals("Salmon Roll", SushiMenuItem.SALMON_ROLL.getName(),
                "SALMON_ROLL should return its display name.");
        assertEquals("Tuna Roll", SushiMenuItem.TUNA_ROLL.getName());
        assertEquals("Chef's Nigiri Platter", SushiMenuItem.CHEFS_NIGIRI_PLATTER.getName());
        assertEquals("Hamachi Roll", SushiMenuItem.HAMACHI_ROLL.getName());
    }

    @Test
    public void testSushiReturnsCorrectPrice(){
        assertEquals(12.99, SushiMenuItem.SALMON_ROLL.getBasePrice(), THOUSANDTH_DECIMAL,
                "SALMON_ROLL should return its base price.");
        assertEquals(13.99, SushiMenuItem.TUNA_ROLL.getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(24.99, SushiMenuItem.CHEFS_NIGIRI_PLATTER.getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(14.99, SushiMenuItem.HAMACHI_ROLL.getBasePrice(), THOUSANDTH_DECIMAL);
    }

    @Test
    public void testTacoReturnsCorrectName(){
        assertEquals("Steak Taco", TacoMenuItem.STEAK_TACO.getName(),
                "STEAK_TACO should return its display name.");
        assertEquals("Chicken Taco", TacoMenuItem.CHICKEN_TACO.getName());
        assertEquals("Chicken Burrito", TacoMenuItem.CHICKEN_BURRITO.getName());
        assertEquals("Steak Burrito", TacoMenuItem.STEAK_BURRITO.getName());
    }

    @Test
    public void testTacoReturnsCorrectPrice(){
        assertEquals(4.99, TacoMenuItem.STEAK_TACO.getBasePrice(), THOUSANDTH_DECIMAL,
                "STEAK_TACO should return its base price.");
        assertEquals(3.99, TacoMenuItem.CHICKEN_TACO.getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(9.99, TacoMenuItem.CHICKEN_BURRITO.getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(11.99, TacoMenuItem.STEAK_BURRITO.getBasePrice(), THOUSANDTH_DECIMAL);
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
    @EnumSource(FineDiningMenuItem.class)
    public void testFineDiningItemsHavePositivePrice(FineDiningMenuItem item){
        assertTrue(item.getBasePrice() > POSITIVE_PRICE);
    }

    @ParameterizedTest
    @EnumSource(FineDiningMenuItem.class)
    public void testFineDiningItemsHaveNonEmptyName(FineDiningMenuItem item){
        assertFalse(item.getName().isBlank());
    }

    @ParameterizedTest
    @EnumSource(HotDogMenuItem.class)
    public void testHotDogItemsHavePositivePrice(HotDogMenuItem item){
        assertTrue(item.getBasePrice() > POSITIVE_PRICE);
    }

    @ParameterizedTest
    @EnumSource(HotDogMenuItem.class)
    public void testHotDogItemsHaveNonEmptyName(HotDogMenuItem item){
        assertFalse(item.getName().isBlank());
    }

    @ParameterizedTest
    @EnumSource(IceCreamMenuItem.class)
    public void testIceCreamItemsHavePositivePrice(IceCreamMenuItem item){
        assertTrue(item.getBasePrice() > POSITIVE_PRICE);
    }

    @ParameterizedTest
    @EnumSource(IceCreamMenuItem.class)
    public void testIceCreamItemsHaveNonEmptyName(IceCreamMenuItem item){
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

    @ParameterizedTest
    @EnumSource(PizzaMenuItem.class)
    public void testPizzaItemsHavePositivePrice(PizzaMenuItem item){
        assertTrue(item.getBasePrice() > POSITIVE_PRICE);
    }
}
