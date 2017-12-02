
package teamthreeproject;
import java.text.*;
import java.util.*;

/**
 * Shift class for TASDatabase.  Contains necessary shift information/rules
 * and methods for returning them in proper format.
 * 
 * @author TeamThree
 */

/* _IM suffix stands for (in milliseconds) - These are the timestamp rules
    for comparing to original timestamps for adjustments */
public class Shift {
    private final int shift_id;
    private final String desc;
    private Long start_time_IM = null;
    private Long start_time_grace_IM = null;
    private Long start_time_interval_IM = null;
    private Long start_time_dock_IM = null;
    private Long stop_time_IM = null;
    private Long stop_time_grace_IM = null;
    private Long stop_time_interval_IM = null;
    private Long stop_time_dock_IM = null;
    private final int shift_length = 510;
    private final int interval;
    private final int grace_period;
    private final int dock;
    private Long lunch_start_IM = null;
    private Long lunch_start_grace_IM = null;
    private Long lunch_start_interval_IM = null;
    private Long lunch_start_dock_IM = null;
    private Long lunch_stop_IM = null;
    private Long lunch_stop_grace_IM = null;
    private Long lunch_stop_interval_IM = null;
    private Long lunch_stop_dock_IM = null;
    private final int lunch_length = 30;
    private final int lunch_deduct;
    private final int max_time;
    private final int overtime_threshold;
    private GregorianCalendar shift_start = new GregorianCalendar();
    private GregorianCalendar shift_stop = new GregorianCalendar();
    private GregorianCalendar l_start = new GregorianCalendar();
    private GregorianCalendar l_stop = new GregorianCalendar();
    
    //Constructor
    public Shift(int id, String description, Long start, Long stop,
                 int interv, int grace_p, int dock, Long start_lunch, Long stop_lunch,
                 int l_deduct, int max, int overtime) {
        
        this.shift_id = id;
        this.desc = description;
        shift_start.setTimeInMillis(start*1000);
        shift_stop.setTimeInMillis(stop*1000);
        this.interval = interv;
        this.grace_period = grace_p;
        this.dock = dock;
        l_start.setTimeInMillis(start_lunch*1000);
        l_stop.setTimeInMillis(stop_lunch*1000);    
        this.lunch_deduct = l_deduct;
        this.max_time = max;
        this.overtime_threshold = overtime;
        
    }
    
    //retrieve Shift start time in Date format
    public Date getStartTime() {
        return shift_start.getTime();
    }
    
    //sets the year/month/date of Shift start rules to original timestamp's and returns as Long timestamp rule
    public Long getStartTimeInMillis(GregorianCalendar ots) {
        shift_start.set(ots.get(GregorianCalendar.YEAR), ots.get(GregorianCalendar.MONTH), ots.get(GregorianCalendar.DATE));
        start_time_IM = shift_start.getTimeInMillis();
        return start_time_IM;
    }
    
    //(Start time + grace period) timestamp rule
    public Long getStartTimeGraceInMillis(GregorianCalendar ots) {
        start_time_grace_IM = getStartTimeInMillis(ots) + (getGracePeriod() * 60000);
        return start_time_grace_IM;
    }
    
    //(Start time - interval) timestamp
    public Long getStartTimeIntervalInMillis(GregorianCalendar ots) {
        start_time_interval_IM = getStartTimeInMillis(ots) - (getInterval() * 60000);
        return start_time_interval_IM;
    }
    
    //(Start time + dock) timestamp rule
    public Long getStartTimeDockInMillis(GregorianCalendar ots) {
        start_time_dock_IM = getStartTimeInMillis(ots) + (getDock() * 60000);
        return start_time_dock_IM;
    }
    
    //retrieve Shift stop time in Date format
    public Date getStopTime() {
        return shift_stop.getTime();
    }
    
    //sets the year/month/date of Shift stop rules to original timestamp's and returns as Long timestamp rule
    public Long getStopTimeInMillis(GregorianCalendar ots) {
        shift_stop.set(ots.get(GregorianCalendar.YEAR), ots.get(GregorianCalendar.MONTH), ots.get(GregorianCalendar.DATE));
        stop_time_IM = shift_stop.getTimeInMillis();
        return stop_time_IM;
    }
    
    //(Stop time - grace period) timestamp rule
    public Long getStopTimeGraceInMillis(GregorianCalendar ots) {
        stop_time_grace_IM = getStopTimeInMillis(ots) - (getGracePeriod() * 60000);
        return stop_time_grace_IM;
    }
    
    //(Stop time + interval) timestamp rule
    public Long getStopTimeIntervalInMillis(GregorianCalendar ots) {
        stop_time_interval_IM = getStopTimeInMillis(ots) + (getInterval() * 60000);
        return stop_time_interval_IM;
    }
    
    //(Stop time - dock) timestamp rule
    public Long getStopTimeDockInMillis(GregorianCalendar ots) {
        stop_time_dock_IM = getStopTimeInMillis(ots) - (getDock() * 60000);
        return stop_time_dock_IM;
    }
    
    //retrieve Shift lunch start time in Date format
    public Date getLunchStart() {
        return l_start.getTime();
    }
    
    //sets the year/month/date of Shift lunch start rules to original timestamp's and returns as Long timestamp rule
    public Long getLunchStartInMillis(GregorianCalendar ots) {
        l_start.set(ots.get(GregorianCalendar.YEAR), ots.get(GregorianCalendar.MONTH), ots.get(GregorianCalendar.DATE));
        lunch_start_IM = l_start.getTimeInMillis();
        return lunch_start_IM;
    }
    
    //retrieve Shift lunch stop time in Date format
    public Date getLunchStop() {
        return l_stop.getTime();
    }
    
    //sets the year/month/date of Shift lunch stop rules to original timestamp's and returns as Long timestamp rule
    public Long getLunchStopInMillis(GregorianCalendar ots) {
        l_stop.set(ots.get(GregorianCalendar.YEAR), ots.get(GregorianCalendar.MONTH), ots.get(GregorianCalendar.DATE));
        lunch_stop_IM = l_stop.getTimeInMillis();
        return lunch_stop_IM;
    }
    
    //Returns the minimum amount of time an employee must work in a day 
    //to get their lunch time deducted from total time worked
    public int getLunchDeduct() {
        return lunch_deduct;
    }
    
    //Returns the interval of time that punches get adjusted to
    public int getInterval() {
        return interval;
    }
    
    //Returns the grace period after shift start and before shift stop that punches
    //get rolled back to or forward to, respectively
    public int getGracePeriod() {
        return grace_period;
    }
    
    //Returns the dock, the amount of time a late clock-in or early clock-out
    //gets adjusted to
    public int getDock() {
        return dock;
    }
    
    public int getMaxTime() {
        return max_time;
    }
    
    public int getOvertimeThreshold() {
        return overtime_threshold;
    }
    
    public int getLunchLength() {
        return lunch_length;
    }
    
    //Returns entire shift description in proper format string
    @Override
    public String toString() {
        
        return desc + ": " + (new SimpleDateFormat("HH:mm")).format(getStartTime())
                + " - " + (new SimpleDateFormat("HH:mm")).format(getStopTime()) + " ("
                + shift_length + " minutes); Lunch: " + (new SimpleDateFormat("HH:mm")).format(getLunchStart())
                + " - " + (new SimpleDateFormat("HH:mm")).format(getLunchStop()) + " (" + lunch_length + " minutes)";
        
    }
}
