package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LuggageFactoryTest {

  private static final String CARRY_ON_TYPE = "carry-on";
  private static final String CHECKED_CATEGORY = "checked";
  private static final String CARRY_ON_CATEGORY = "standard";
  private static final String UNDEFINED_LUGGAGE = "undefined";
  private static final int LINEAR_DIMENSION = 142;
  private static final int WEIGHT = 25;

  private LuggageFactory luggageFactory;

  @Before
  public void initiateTest() {
    luggageFactory = new LuggageFactory();
  }

  @Test
  public void whenCreateLuggageWithTypeCheckedThenCreateCheckedLuggage() {
    Luggage luggage = luggageFactory.createLuggage(LINEAR_DIMENSION, WEIGHT, CHECKED_CATEGORY);

    assertEquals(CHECKED_CATEGORY, luggage.getCategory());
  }

  @Test
  public void whenCreateLuggageWithTypeCarryOnThenCreateCarryOnLuggage() {
    Luggage luggage = luggageFactory.createLuggage(LINEAR_DIMENSION, WEIGHT, CARRY_ON_TYPE);

    assertEquals(CARRY_ON_CATEGORY, luggage.getCategory());
  }

  @Test(expected = UndefinedTypeLuggageException.class)
  public void whenCreateLuggageWithUndefinedTypeThenReturnException() {
    luggageFactory.createLuggage(LINEAR_DIMENSION, WEIGHT, UNDEFINED_LUGGAGE);
  }
}
