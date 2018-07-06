package com.axelor.apps.base.service.timer;

import com.axelor.apps.base.db.Timer;
import com.axelor.apps.base.db.TimerHistory;
import com.axelor.db.Model;
import com.axelor.exception.AxelorException;
import java.time.Duration;
import java.time.LocalDateTime;

public interface TimerService {

  /**
   * Find timer start date.
   *
   * @param timer The timer.
   * @return Timer start date.
   */
  LocalDateTime findStartDate(Timer timer);

  /**
   * Find timer end date.
   *
   * @param timer The timer.
   * @return Timer end date.
   */
  LocalDateTime findEndDate(Timer timer);

  /**
   * Compute the total duration of timer.
   *
   * @param timer The timer.
   * @return Timer total duration.
   */
  Duration compute(Timer timer);

  /**
   * Find the timer related to model.
   *
   * @param model The model link to timer.
   * @return The timer related to model.
   */
  Timer find(Model model) throws AxelorException;

  /**
   * Start the timer for model. If timer is null, create a new one.
   *
   * @param model Model related to timer.
   * @param timer Timer to start.
   * @param dateTime Date to start the timer.
   * @return The timer history generated.
   * @throws AxelorException If timer is already started.
   */
  TimerHistory start(Model model, Timer timer, LocalDateTime dateTime) throws AxelorException;

  /**
   * Resume the timer that has been stopped.
   *
   * @param model Model related to timer.
   * @param timer Timer to resume.
   * @param dateTime Date to resume the timer.
   * @return The timer history generated.
   * @throws AxelorException If timer is already started.
   */
  default TimerHistory resume(Model model, Timer timer, LocalDateTime dateTime)
      throws AxelorException {
    return start(model, timer, dateTime);
  }

  /**
   * Stop the timer.
   *
   * @param model Model related to timer.
   * @param timer Timer to stop.
   * @param dateTime Date to stop the timer.
   * @return The timer history generated.
   * @throws AxelorException If timer is not started.
   */
  TimerHistory stop(Model model, Timer timer, LocalDateTime dateTime) throws AxelorException;

  /**
   * Pause the timer.
   *
   * @param model Model related to timer.
   * @param timer Timer to pause.
   * @param dateTime Date to pause the timer.
   * @return The timer history generated.
   * @throws AxelorException If timer is not started.
   */
  default TimerHistory pause(Model model, Timer timer, LocalDateTime dateTime)
      throws AxelorException {
    return stop(model, timer, dateTime);
  }

  /**
   * Remove all timer history associated to timer and set timer state to stop.
   *
   * @param timer Timer to cancel.
   */
  void cancel(Timer timer);
}
