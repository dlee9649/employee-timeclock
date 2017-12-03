# employee-timeclock
*Java project utilizing SQL database to accept time punches, adjust for shift rules, and tally for payroll.*

## Software Team Project -tools utilized:  Agile(SCRUM), NetBeans, Java, MySQL, JSON

*This project was developed in a software engineering class at Jacksonville State University, where we focused on Agile development concepts, specifically the SCRUM process methodology. We began with 6 team members, 5 being the development team and one being the SCRUM Master, and worked a total of 4 sprints, developing 5 features based on the product owner's needs. SCRUM approach to development is test-driven, and application features were developed based on unit tests given by the instructor, but ideally SCRUM looks for the developer to write the unit tests based on product owner's requirements, broken down into manageable features. These features are then developed in an order best-suited to the product owner's needs, considering urgency and importance (must-haves versus extras). Features are then chosen to be worked on in a specific frame of time, based on what the product development team believes they can complete within that specified time.* 

## Features

### Feature 1 - Commit hash: ddc6d8db1594398eb7104328e8f5b84807272907
### Goal - Allow badge, punch, and shift data to be retrieved from the database and encapsulated as Java objects

*This feature is where the connection to the TAS database is established and can also be disconnected. Factory methods for employee badge ID, shift specific data, and timeclock punches(original and adjusted) are provided within separate classes. A single punch will include information about the employee's badge, shift (shift number; rules for start, stop, dock(time adjustment intervals), grace period, lunch). Punch printouts provide description of punch ("CLOCK IN", "CLOCK OUT", "TIME OUT"(no clock out found after 16 hours)).*   

### Feature 2 - Commit hash: 092c349f5ffb1c32f633159424cf12b684b83cf3 (Best commit after competion, clean up, and addition of comments)
### Goal - Allow new punches to be inserted into the database.

*This feature introduces a method to insert punches into the database, and it accepts a Punch object as an argument. It retrieves the punch's properties using accessor methods, or getters. The data is then inserted into the database as a new punch. This method returns the database (hereafter "terminal") ID of the new punch as an integer. The punch class also got a new constructor for Punch which includes the badge ID as a string, terminal ID as integer, and punchtype ID (eventtype - clock in, clock out, time out).*

### Feature 3 - Commit hash: ba255200ed9ee9b935a2088610030ec529cce401 (Best commit after completion, clean up, addition of comments, and                   modification of unit tests.)
### Goal - Implement punch adjustment according to the parameters for the current shift.

 ***Shift Start and Stop** : The time of day at which the employee's shift is scheduled to begin and end.*

***Lunch Start and Stop**: The time of day at which the employee's lunch break is scheduled to start and stop.*

***Interval**: The amount of time before the start of a shift, and after the end of a shift, in which an employee's early "clock in" punches and late "clock out" punches are adjusted forward to the scheduled start of their shift or backward to the end of their shift, respectively.*

*Also, punches during or outside of a shift are rounded up or down to the nearest interval, if no other rules apply.  For all shifts, this value is currently set to 15 minutes.*

***Grace Period**: The amount of time after the start of a shift, and before the end of a shift, in which an employee's late "clock in" punches and early "clock out" punches are adjusted backward to the scheduled start of their shift or forward to the end of their shift, respectively.  For all shifts, this value is currently set to 5 minutes.*

***Dock**: If a "clock in" punch is entered too late to fall within the grace period, the punch is moved forward in time by this amount (to discourage excessive tardiness).  Similarly, if an early "clock out" punch is entered too early to fall within the grace period, the punch is moved backward in time.  For all shifts, this value is currently set to 15 minutes.*

***None**: If an employee's punch falls outside any of the rules outlined above, it is rounded up or down to the nearest Interval.  If the punch happens to have occurred at an even increment of the Interval (for example, if the Interval is set to 15 minutes and a first-shift employee clocks in at 9:00, 9:15, 9:30, or 9:45, disregarding the seconds), then no adjustment is necessary.*

***Lunch Deduct**: This shift parameter designates the minimum amount of time (in minutes) that an employee must work over the course of a day in order for their lunch break to be deducted from the number of minutes worked on that day.  If an employee neglects to clock out for lunch, and if they have worked the minimum number of minutes, the deduction is made; otherwise, their accrued time is unchanged.*

***Employees who work on weekends have no regularly scheduled hours, so with the exception of the interval rounding rule and lunch deduction rules described above, these rules are NOT applied to punches that are entered on weekends.***

*After the adjusted timestamp for a punch has been determined, that timestamp is saved to the database, in the "adjustedtimestamp" field of the original record, and a note is added which indicates the rule which triggered the adjustment.*

## Feature 4 - Commit hash: 8f1d2c7a3c5b062c30cfa0dd03619de37dfcce5f
## Goal - Compute the total number of minutes accrued by an employee within a single day.

*In this feature, we implement a method of the database class which accepts a single Punch as an argument, and determines the total number of minutes accrued by the employee within the day in which that punch was entered.  This total is computed after ALL of the punches for the day have been retrieved from the database and have been adjusted according to the employee's shift rules.*

*The method getMinutesAccrued(Punch p) in the database class finds from which shift the punch belongs, collects any other punches with the same badge ID and day (from timestamp), and stores them all into a new arrayList. It then adjusts all punches according to their shift rules and accumulates the total number of minutes for the day for that particular badge ID.*

## Feature 5 - Commit hash: 9910324e4d6db64661683e1db185110064fc223f
## Goal - Gather the list of punches accumulated by an employee over the course of a single day, and the total number of minutes accrued by an employee within that day, and parse this data into a JSON string.

*In this feature we combine the collectPunch() method (that used 2 ArrayLists) into one private method that returns an ArrayList of punches within that day. We also add getPunchListAsJSON() method to the database class which accepts a single Punch as an argument.  This new method retrieves the list of punches accumulated by the employee within the day in which the punch was created, adjusts the punches according to the employee's shift rules, computes the total number of minutes accrued by the employee within that day, parses this data into a nested data structure, parses this structure into a JSON string, and returns the string to the caller.*


