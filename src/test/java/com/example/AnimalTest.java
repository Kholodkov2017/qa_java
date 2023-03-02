package com.example;

import com.example.constans.AnimalKinds;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import java.util.List;
import static com.example.constans.ExceptionMessages.IncorrectAnimalKindExceptionMessage;
import static com.example.constans.ExpectedValues.*;

@RunWith(JUnitParamsRunner.class)
public class AnimalTest {

    @Test
    @Parameters({AnimalKinds.PREDATOR, AnimalKinds.HERBIVORE })
    public void getFoodReturnsCorrectFoodKindsForSpecificKindOfAnimal(String animalKind) throws Exception {
        // Arrange
        Animal animal = new Animal();

        // Action
        List<String> actualAnimalFoods = animal.getFood(animalKind);

        // Assert
        List<String> expectedAnimalFood = animalKind.equals(AnimalKinds.PREDATOR)
                ? EXPECTED_PREDATOR_FOODS
                : EXPECTED_HERBIVORE_FOODS;

        Assert.assertEquals(String.format("Ожидался следующий рацион %s: [%s].\nАктуальный рацион питания %s: [%s].",
                        animalKind, String.join(", ", expectedAnimalFood), animalKind,
                        String.join(", ", actualAnimalFoods)), expectedAnimalFood, actualAnimalFoods);

    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    @Parameters({"НеизвестноеЖивотное"})
    public void getFoodThrowsExceptionIfAnimalKindIsIncorrect(String animalKind) throws Exception {
        // Arrange
        expectedEx.expect(Exception.class);
        expectedEx.expectMessage(IncorrectAnimalKindExceptionMessage);

        // Arrange
        Animal animal = new Animal();

        // Action
        List<String> actualAnimalFoods = animal.getFood(animalKind);
    }

    @Test
    public void getFamilyReturnsCorrectValue() {
        // Arrange
        Animal animal = new Animal();

        // Action
        String actualFamilyTypes = animal.getFamily();

        // Assert
        Assert.assertEquals(String.format("Ожидалось следующее значение типа семейств: [%s]." +
                "\nФактическое значение семейств: [%s]", EXPECTED_FAMILY_TYPES, actualFamilyTypes),
                EXPECTED_FAMILY_TYPES, actualFamilyTypes);
    }
}
