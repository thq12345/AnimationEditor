package cs3500.animator.model;

/**
 * Class representing a single duration between start time and end time.
 */
public class Duration {

  public int startTime;
  public int endTime;

  /**
   * Constructor for the duration class.
   *
   * @param startTime Starttime of the object (in ticks)
   * @param endTime   EndTime of the object (in ticks)
   */
  Duration(int startTime, int endTime) {
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * overriding toString method.
   *
   * @return String representing the current state of the duration
   */
  public String toString() {
    return "Model.Duration: " + this.startTime + " " + this.endTime + "\n";
  }

  /**
   * Checks whether a given timeframe is the same as this duration.
   *
   * @param start the start of the timeframe.
   * @param end   the end of the timeframe.
   * @return whether the two timeframes are equal.
   */
  public boolean sameTimeFrame(int start, int end) {
    return start == startTime && end == endTime;
  }
}
