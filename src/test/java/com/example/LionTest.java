package com.example;

import com.example.constans.AnimalKinds;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import java.util.List;
import static com.example.constans.ExceptionMessages.IncorrectLionSexExceptionMessage;
import static com.example.constans.ExpectedValues.EXPECTED_PREDATOR_FOODS;

@RunWith(JUnitParamsRunner.class)
public class LionTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    Feline feline;

    @Test
    @Parameters({"Самец,true", "Самка,false"})
    public void Lion_Creation_With_Specific_Sex_Set_Correct_Lion_Sex(String sex, Boolean hasMane) throws Exception {
        // Arrange
        // Action
        Lion lion = new Lion(sex, feline);

        // Assert
        Assert.assertEquals(String.format("Ожидалось, что львёнок - самец [%b]. Фактически львёнок-самец [%b]",
                        hasMane, lion.doesHaveMane()), hasMane, lion.doesHaveMane());
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    @Test
    @Parameters({"НебинарныйЛьвенок"})
    public void lion_Creation_With_Specific_Sex_Throws_Exception(String sex) throws Exception {
        // Arrange
        expectedEx.expect(Exception.class);
        expectedEx.expectMessage(IncorrectLionSexExceptionMessage);

        // Action
        new Lion(sex, feline);
    }

    @Test
    @Parameters({"Самец", "Самка"})
    public void getKittens_Returns_Correct_Kittens_Count (String sex) throws Exception {
        // Arrange
        Mockito.when(feline.getKittens()).thenReturn(1);

        // Action
        Lion lion = new Lion(sex, feline);
        int kittensCount = lion.getKittens();

        // Assert
        Mockito.verify(feline, Mockito.times(1)).getKittens();
        Assert.assertEquals(String.format("Ожидаемое количество котят: [%d]." +
                " Фактическое количество котят: [%d]", 1, kittensCount), 1, kittensCount);
    }

    @Test
    @Parameters({"Самец", "Самка"})
    public void getFood_Returns_Correct_Kittens_Count (String sex) throws Exception {
        // Arrange
        Mockito.when(feline.getFood(AnimalKinds.PREDATOR)).thenReturn(EXPECTED_PREDATOR_FOODS);

        // Action
        Lion lion = new Lion(sex, feline);
        List<String> actualPredatorFoods = lion.getFood();

        // Assert
        Mockito.verify(feline, Mockito.times(1)).getFood(AnimalKinds.PREDATOR);
        Assert.assertEquals(String.format("Ожидался следующий рацион льва: [%s].\nАктуальный рацион питания льва: [%s].",
                        String.join(", ", EXPECTED_PREDATOR_FOODS), String.join(", ", actualPredatorFoods)),
                EXPECTED_PREDATOR_FOODS, actualPredatorFoods);
    }
}
