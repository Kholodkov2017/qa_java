package com.example;

import com.example.constans.AnimalKinds;
import com.example.constans.FamilyTypes;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;

import static com.example.constans.ExpectedValues.EXPECTED_PREDATOR_FOODS;

@RunWith(MockitoJUnitRunner.class)
public class FelineTest {
    @Test
    public void eatMeat_Returns_Predator_FoodKinds() throws Exception {
        // Arrange
        Feline feline = new Feline();

        // Action
        List<String> actualPredatorFoodKinds = feline.getFood(AnimalKinds.PREDATOR);

        // Assert
        Assert.assertEquals(String.format("Ожидался следующий рацион хищника: [%s].\nАктуальный рацион питания: [%s].",
                        String.join(", ", EXPECTED_PREDATOR_FOODS), String.join(", ", actualPredatorFoodKinds)),
                EXPECTED_PREDATOR_FOODS, actualPredatorFoodKinds);
    }

    @Test
    public void getFamily_ReturnsCorrectFamilyType() {
        // Arrange
        Feline feline = new Feline();

        // Action
        String actualFamilyType =  feline.getFamily();

        // Assert
        Assert.assertEquals(String.format("Ожидался следующий вид животного [&s]." +
                "\nАктуальный вид животного: [%s]", FamilyTypes.FELINE, actualFamilyType),
                FamilyTypes.FELINE, actualFamilyType);
    }

    @Test
    public void getKittens_ReturnsCorrectKittensCount() {
        // Arrange
        Feline feline = new Feline();

        // Action
        int actualKittens =  feline.getKittens();

        // Assert
        Assert.assertEquals(String.format("Ожидалось следующее количество котят[&d]." +
                        "\nАктуальное количество котят: [%d]", 1, actualKittens),
                1, actualKittens);
    }


    @Test
    public void getKittens_WithSpecificCount_ReturnsCorrectKittensCount() {
        // Arrange
        Feline feline = new Feline();

        // Action
        int actualKittens =  feline.getKittens(10);

        // Assert
        Assert.assertEquals(String.format("Ожидалось следующее количество котят[&d]." +
                        "\nАктуальное количество котят: [%d]", 10, actualKittens),
                10, actualKittens);
    }
}
