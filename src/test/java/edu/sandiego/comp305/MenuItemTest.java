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
        assertEquals("Brisket", BBQMenuItem.BRISKET
                .getName());
        assertEquals("Pulled Pork", BBQMenuItem
                .PULLED_PORK.getName());
    }

    @Test
    public void testBBQReturnsCorrectPrice(){
        final double RIBS_PRICE = 22.99;
        final double BRISKET_PRICE = 18.99;
        final double PULLED_PORK_PRICE = 16.99;

        assertEquals(RIBS_PRICE, BBQMenuItem.RIBS.getBasePrice(),
                THOUSANDTH_DECIMAL,
                "RIBS should return its base price.");
        assertEquals(BRISKET_PRICE, BBQMenuItem.BRISKET
                .getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(PULLED_PORK_PRICE, BBQMenuItem.PULLED_PORK
                .getBasePrice(), THOUSANDTH_DECIMAL);
    }

    @Test
    public void testBurgerReturnsCorrectName(){
        assertEquals("Cheeseburger", BurgerMenuItem
                        .CHEESEBURGER.getName(),
                "CHEESEBURGER should return its " +
                        "display name.");
        assertEquals("Double Cheeseburger",
                BurgerMenuItem.DOUBLE_CHEESEBURGER.getName());
        assertEquals("Triple Cheeseburger",
                BurgerMenuItem.TRIPLE_CHEESEBURGER.getName());
    }

    @Test
    public void testBurgerReturnsCorrectPrice(){
        final double CHEESEBURGER_PRICE = 8.99;
        final double DOUBLE_BURGER_PRICE = 11.99;
        final double TRIPLE_BURGER_PRICE = 14.99;

        assertEquals(CHEESEBURGER_PRICE, BurgerMenuItem
                        .CHEESEBURGER.getBasePrice(), THOUSANDTH_DECIMAL,
                "CHEESEBURGER should return its base price.");
        assertEquals(DOUBLE_BURGER_PRICE, BurgerMenuItem.DOUBLE_CHEESEBURGER
                .getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(TRIPLE_BURGER_PRICE, BurgerMenuItem.TRIPLE_CHEESEBURGER
                .getBasePrice(), THOUSANDTH_DECIMAL);
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
        final double COFFEE_PRICE = 3.99;
        final double LATTE_PRICE = 5.99;
        final double PASTRY_PRICE = 4.49;

        assertEquals(COFFEE_PRICE, CafeMenuItem.COFFEE.getBasePrice(),
                THOUSANDTH_DECIMAL,
                "COFFEE should return its base price.");
        assertEquals(LATTE_PRICE, CafeMenuItem.LATTE.getBasePrice(),
                THOUSANDTH_DECIMAL);
        assertEquals(PASTRY_PRICE, CafeMenuItem.PASTRY.getBasePrice(),
                THOUSANDTH_DECIMAL);
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
        final double STEAK_PRICE = 45.99;
        final double PASTA_PRICE = 24.99;
        final double CAVIAR_PRICE = 89.99;

        assertEquals(STEAK_PRICE, FineDiningMenuItem.STEAK.getBasePrice(),
                THOUSANDTH_DECIMAL,
                "STEAK should return its base price.");
        assertEquals(PASTA_PRICE, FineDiningMenuItem.PASTA.getBasePrice(),
                THOUSANDTH_DECIMAL);
        assertEquals(CAVIAR_PRICE, FineDiningMenuItem.CAVIAR.getBasePrice(),
                THOUSANDTH_DECIMAL);
    }

    @Test
    public void testHotDogReturnsCorrectName(){
        assertEquals("Plain Hot Dog", HotDogMenuItem.PLAIN_HOT_DOG
                        .getName(),
                "PLAIN_HOT_DOG should return its display name.");
        assertEquals("Chili Dog", HotDogMenuItem.CHILI_DOG.getName());
        assertEquals("Condiment Hot Dog", HotDogMenuItem.CONDIMENT_HOT_DOG
                .getName());
    }

    @Test
    public void testHotDogReturnsCorrectPrice(){
        final double HOT_DOG_PRICE = 3.99;
        final double CHILI_DOG_PRICE = 5.99;
        final double CONDIMENT_DOG_PRICE = 4.99;

        assertEquals(HOT_DOG_PRICE, HotDogMenuItem.PLAIN_HOT_DOG.getBasePrice(),
                THOUSANDTH_DECIMAL,
                "PLAIN_HOT_DOG should return its base price.");
        assertEquals(CHILI_DOG_PRICE, HotDogMenuItem.CHILI_DOG.getBasePrice(),
                THOUSANDTH_DECIMAL);
        assertEquals(CONDIMENT_DOG_PRICE, HotDogMenuItem.CONDIMENT_HOT_DOG
                .getBasePrice(), THOUSANDTH_DECIMAL);
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
        final double CONE_PRICE = 3.99;
        final double SUNDAE_PRICE = 6.99;
        final double SHAKE_PRICE = 7.99;

        assertEquals(CONE_PRICE, IceCreamMenuItem.CONE.getBasePrice(),
                THOUSANDTH_DECIMAL,
                "CONE should return its base price.");
        assertEquals(SUNDAE_PRICE, IceCreamMenuItem.SUNDAE.getBasePrice(),
                THOUSANDTH_DECIMAL);
        assertEquals(SHAKE_PRICE, IceCreamMenuItem.SHAKE.getBasePrice(),
                THOUSANDTH_DECIMAL);
    }

    @Test
    public void testJuiceBarReturnsCorrectName(){
        assertEquals("Orange Juice", JuiceBarMenuItem
                        .ORANGE_JUICE.getName(),
                "ORANGE_JUICE should return its display name.");
        assertEquals("Strawberry Smoothie", JuiceBarMenuItem
                .STRAWBERRY_SMOOTHIE.getName());
        assertEquals("Acai Bowl", JuiceBarMenuItem.ACAI_BOWL
                .getName());
        assertEquals("Ginger Shot", JuiceBarMenuItem.GINGER_SHOT
                .getName());
    }

    @Test
    public void testJuiceBarReturnsCorrectPrice(){
        final double OJ_PRICE = 4.99;
        final double STRAWBERRY_SMOOTHIE_PRICE = 7.99;
        final double ACAI_BOWL_PRICE = 12.99;
        final double GINGER_SHOT_PRICE = 3.99;

        assertEquals(OJ_PRICE, JuiceBarMenuItem.ORANGE_JUICE.getBasePrice(),
                THOUSANDTH_DECIMAL,
                "ORANGE_JUICE should return its base price.");
        assertEquals(STRAWBERRY_SMOOTHIE_PRICE, JuiceBarMenuItem
                .STRAWBERRY_SMOOTHIE.getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(ACAI_BOWL_PRICE, JuiceBarMenuItem.ACAI_BOWL.getBasePrice(),
                THOUSANDTH_DECIMAL);
        assertEquals(GINGER_SHOT_PRICE, JuiceBarMenuItem.GINGER_SHOT
                .getBasePrice(), THOUSANDTH_DECIMAL);
    }

    @Test
    public void testPizzaReturnsCorrectName(){
        assertEquals("Pepperoni Slice", PizzaMenuItem.PEPPERONI_SLICE
                        .getName(),
                "PEPPERONI_SLICE should return its display name.");
        assertEquals("Pepperoni Whole", PizzaMenuItem.PEPPERONI_WHOLE
                .getName());
        assertEquals("Margherita Slice", PizzaMenuItem.MARGHERITA_SLICE
                .getName());
        assertEquals("Margherita Whole", PizzaMenuItem.MARGHERITA_WHOLE
                .getName());
        assertEquals("Cheese Slice", PizzaMenuItem.CHEESE_SLICE
                .getName());
        assertEquals("Cheese Whole", PizzaMenuItem.CHEESE_WHOLE
                .getName());
    }

    @Test
    public void testPizzaReturnsCorrectPrice(){
        final double PEPPERONI_SLICE_PRICE = 3.99;
        final double PEPPERONI_WHOLE_PRICE = 18.99;
        final double MARGHERITA_SLICE_PRICE = 3.49;
        final double MARGHERITA_WHOLE_PRICE = 16.99;
        final double CHEESE_SLICE_PRICE = 2.99;
        final double CHEESE_WHOLE_PRICE = 14.99;

        assertEquals(PEPPERONI_SLICE_PRICE, PizzaMenuItem.PEPPERONI_SLICE
                        .getBasePrice(), THOUSANDTH_DECIMAL,
                "PEPPERONI_SLICE should return its base price.");
        assertEquals(PEPPERONI_WHOLE_PRICE, PizzaMenuItem.PEPPERONI_WHOLE
                .getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(MARGHERITA_SLICE_PRICE, PizzaMenuItem.MARGHERITA_SLICE
                .getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(MARGHERITA_WHOLE_PRICE, PizzaMenuItem.MARGHERITA_WHOLE
                .getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(CHEESE_SLICE_PRICE, PizzaMenuItem.CHEESE_SLICE
                        .getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(CHEESE_WHOLE_PRICE, PizzaMenuItem.CHEESE_WHOLE
                        .getBasePrice(), THOUSANDTH_DECIMAL);
    }

    @Test
    public void testSushiReturnsCorrectName(){
        assertEquals("Salmon Roll", SushiMenuItem.SALMON_ROLL.getName(),
                "SALMON_ROLL should return its display name.");
        assertEquals("Tuna Roll", SushiMenuItem.TUNA_ROLL.getName());
        assertEquals("Chef's Nigiri Platter", SushiMenuItem
                .CHEFS_NIGIRI_PLATTER.getName());
        assertEquals("Hamachi Roll", SushiMenuItem.HAMACHI_ROLL.getName());
    }

    @Test
    public void testSushiReturnsCorrectPrice(){
        final double SALMON_ROLL_PRICE = 12.99;
        final double TUNA_ROLL_PRICE = 13.99;
        final double CHEFS_NIGIRI_PRICE = 24.99;
        final double HAMACHI_ROLL_PRICE = 14.99;

        assertEquals(SALMON_ROLL_PRICE, SushiMenuItem.SALMON_ROLL
                        .getBasePrice(), THOUSANDTH_DECIMAL,
                "SALMON_ROLL should return its base price.");
        assertEquals(TUNA_ROLL_PRICE, SushiMenuItem.TUNA_ROLL.getBasePrice(),
                THOUSANDTH_DECIMAL);
        assertEquals(CHEFS_NIGIRI_PRICE, SushiMenuItem.CHEFS_NIGIRI_PLATTER
                .getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(HAMACHI_ROLL_PRICE, SushiMenuItem.HAMACHI_ROLL
                        .getBasePrice(), THOUSANDTH_DECIMAL);
    }

    @Test
    public void testTacoReturnsCorrectName(){
        assertEquals("Steak Taco", TacoMenuItem.STEAK_TACO.getName(),
                "STEAK_TACO should return its display name.");
        assertEquals("Chicken Taco", TacoMenuItem.CHICKEN_TACO.getName());
        assertEquals("Chicken Burrito", TacoMenuItem.CHICKEN_BURRITO
                .getName());
        assertEquals("Steak Burrito", TacoMenuItem.STEAK_BURRITO.getName());
    }

    @Test
    public void testTacoReturnsCorrectPrice(){
        final double STEAK_TACO_PRICE = 4.99;
        final double CHICKEN_TACO = 3.99;
        final double CHICKEN_BURRITO_PRICE = 9.99;
        final double STEAK_BURRITO_PRICE = 11.99;

        assertEquals(STEAK_TACO_PRICE, TacoMenuItem.STEAK_TACO.getBasePrice(),
                THOUSANDTH_DECIMAL,
                "STEAK_TACO should return its base price.");
        assertEquals(CHICKEN_TACO, TacoMenuItem.CHICKEN_TACO.getBasePrice(),
                THOUSANDTH_DECIMAL);
        assertEquals(CHICKEN_BURRITO_PRICE, TacoMenuItem.CHICKEN_BURRITO
                .getBasePrice(), THOUSANDTH_DECIMAL);
        assertEquals(STEAK_BURRITO_PRICE, TacoMenuItem.STEAK_BURRITO
                .getBasePrice(), THOUSANDTH_DECIMAL);
    }

    @ParameterizedTest
    @EnumSource(BBQMenuItem.class)
    public void testBBQItemsHavePositivePrice(final BBQMenuItem item){
        assertTrue(item.getBasePrice() > POSITIVE_PRICE,
                item.getName() + " should have a positive base price.");
    }

    @ParameterizedTest
    @EnumSource(BBQMenuItem.class)
    public void testBBQItemsHaveNonEmptyName(final BBQMenuItem item){
        assertFalse(item.getName().isBlank(),
                "Every menu item should have a non-empty display name.");
    }

    @ParameterizedTest
    @EnumSource(BurgerMenuItem.class)
    public void testBurgerItemsHavePositivePrice(final BurgerMenuItem item){
        assertTrue(item.getBasePrice() > POSITIVE_PRICE);
    }

    @ParameterizedTest
    @EnumSource(BurgerMenuItem.class)
    public void testBurgerItemsHaveNonEmptyName(final BurgerMenuItem item){
        assertFalse(item.getName().isBlank());
    }

    @ParameterizedTest
    @EnumSource(CafeMenuItem.class)
    public void testCafeItemsHavePositivePrice(final CafeMenuItem item){
        assertTrue(item.getBasePrice() > POSITIVE_PRICE);
    }

    @ParameterizedTest
    @EnumSource(CafeMenuItem.class)
    public void testCafeItemsHaveNonEmptyName(final CafeMenuItem item){
        assertFalse(item.getName().isBlank());
    }

    @ParameterizedTest
    @EnumSource(FineDiningMenuItem.class)
    public void testFineDiningItemsHavePositivePrice(
            final FineDiningMenuItem item){
        assertTrue(item.getBasePrice() > POSITIVE_PRICE);
    }

    @ParameterizedTest
    @EnumSource(FineDiningMenuItem.class)
    public void testFineDiningItemsHaveNonEmptyName(
            final FineDiningMenuItem item){
        assertFalse(item.getName().isBlank());
    }

    @ParameterizedTest
    @EnumSource(HotDogMenuItem.class)
    public void testHotDogItemsHavePositivePrice(final HotDogMenuItem item){
        assertTrue(item.getBasePrice() > POSITIVE_PRICE);
    }

    @ParameterizedTest
    @EnumSource(HotDogMenuItem.class)
    public void testHotDogItemsHaveNonEmptyName(final HotDogMenuItem item){
        assertFalse(item.getName().isBlank());
    }

    @ParameterizedTest
    @EnumSource(IceCreamMenuItem.class)
    public void testIceCreamItemsHavePositivePrice(final IceCreamMenuItem item){
        assertTrue(item.getBasePrice() > POSITIVE_PRICE);
    }

    @ParameterizedTest
    @EnumSource(IceCreamMenuItem.class)
    public void testIceCreamItemsHaveNonEmptyName(final IceCreamMenuItem item){
        assertFalse(item.getName().isBlank());
    }

    @ParameterizedTest
    @EnumSource(JuiceBarMenuItem.class)
    public void testJuiceBarItemsHavePositivePrice(final JuiceBarMenuItem item){
        assertTrue(item.getBasePrice() > POSITIVE_PRICE);
    }

    @ParameterizedTest
    @EnumSource(JuiceBarMenuItem.class)
    public void testJuiceBarItemsHaveNonEmptyName(final JuiceBarMenuItem item){
        assertFalse(item.getName().isBlank());
    }

    @ParameterizedTest
    @EnumSource(PizzaMenuItem.class)
    public void testPizzaItemsHavePositivePrice(final PizzaMenuItem item){
        assertTrue(item.getBasePrice() > POSITIVE_PRICE);
    }

    @ParameterizedTest
    @EnumSource(PizzaMenuItem.class)
    public void testPizzaItemsHaveNonEmptyName(final PizzaMenuItem item){
        assertFalse(item.getName().isBlank());
    }

    @ParameterizedTest
    @EnumSource(SushiMenuItem.class)
    public void testSushiItemsHavePositivePrice(final SushiMenuItem item){
        assertTrue(item.getBasePrice() > POSITIVE_PRICE);
    }

    @ParameterizedTest
    @EnumSource(SushiMenuItem.class)
    public void testSushiItemsHaveNonEmptyName(final SushiMenuItem item){
        assertFalse(item.getName().isBlank());
    }

    @ParameterizedTest
    @EnumSource(TacoMenuItem.class)
    public void testTacoItemsHavePositivePrice(final TacoMenuItem item){
        assertTrue(item.getBasePrice() > POSITIVE_PRICE);
    }

    @ParameterizedTest
    @EnumSource(TacoMenuItem.class)
    public void testTacoItemsHaveNonEmptyName(final TacoMenuItem item){
        assertFalse(item.getName().isBlank());
    }
}
