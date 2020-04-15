package training.weather.service;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateServiceTest {
  private DateService tested;

  @Before
  public void init() {
    tested = new DateService();
  }

  @Test
  public void given1DayFromToday_whenBefore_thenReturnTrue() {
    boolean result = tested.isBefore(LocalDate.now().plusDays(1));
    assertTrue(result);
  }

  @Test
  public void given6DaysFromToday_whenIsBefore_thenReturnTrue() {
    boolean result = tested.isBefore(LocalDate.now().plusDays(6));
    assertTrue(result);
  }

  @Test
  public void given7DaysFromToday_whenIsAfter_thenReturnFalse() {
    boolean result = tested.isBefore(LocalDate.now().plusDays(7));
    assertFalse(result);
  }
}