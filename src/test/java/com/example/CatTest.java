package com.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static com.example.constans.ExpectedValues.*;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {
    @Mock
    Feline feline;

    @Spy
    Feline felineSpy = new Feline();

    @Test
    public void getSoundReturnsCorrectSound() {
        // Arrange
        Cat cat = new Cat(feline);

        // Act
        String actualCatSound = cat.getSound();

        // Assert
        Assert.assertEquals(String.format("Ожидалось значение [%s], получено [%s].",
                EXPECTED_CAT_SOUND, actualCatSound), EXPECTED_CAT_SOUND, actualCatSound);
    }

    @Test
    public void getFoodPredatorEatMeatCallsCorrectly() throws Exception {
        // Arrange
        Cat cat = new Cat(feline);
        Mockito.when(feline.eatMeat()).thenReturn(EXPECTED_PREDATOR_FOODS);

        // Act
        List<String> actualPredatorFoods = cat.getFood();

        //Assert
        Mockito.verify(feline, Mockito.times(1)).eatMeat();
        Assert.assertEquals(String.format("Ожидался следующий рацион хищника: [%s].\nАктуальный рацион питания: [%s].",
                String.join(", ", EXPECTED_PREDATOR_FOODS), String.join(", ", actualPredatorFoods)),
                EXPECTED_PREDATOR_FOODS, actualPredatorFoods);
    }

    @Test
    public void getFoodPredatorEatMeatReturnsCorrectlyFoodKinds() throws Exception {
        // Arrange
        Cat cat = new Cat(felineSpy);

        // Act
        List<String> actualPredatorFoods = cat.getFood();

        //Assert
        Mockito.verify(felineSpy, Mockito.times(1)).eatMeat();
        Assert.assertEquals(String.format("Ожидался следующий рацион хищника: [%s].\nАктуальный рацион питания: [%s].",
                        String.join(", ", EXPECTED_PREDATOR_FOODS), String.join(", ", actualPredatorFoods)),
                EXPECTED_PREDATOR_FOODS, actualPredatorFoods);
    }
}
