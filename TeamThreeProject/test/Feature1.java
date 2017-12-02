import teamthreeproject.*;

import org.junit.*;
import static org.junit.Assert.*;

public class Feature1 {

    private TASDatabase db;
    
    @Before
    public void setup() {
        db = new TASDatabase();
    }
    
    @Test
    public void testGetBadges() {
		
		/* Retrieve Badges from Database */

        Badge b1 = db.getBadge("12565C60");
        Badge b2 = db.getBadge("08D01475");
        Badge b3 = db.getBadge("D2CC71D4");
		
		/* Compare to Expected Values */

        assertEquals(b1.toString(), "#12565C60 (Chapman, Joshua E)");
        assertEquals(b2.toString(), "#08D01475 (Littell, Amie D)");
        assertEquals(b3.toString(), "#D2CC71D4 (Lawson, Matthew J)");
        
    }
    
    @Test
    public void testGetPunches() {
		
		/* Retrieve Punches from Database */

	Punch p1 = db.getPunch(3433);
        Punch p2 = db.getPunch(3325);
        Punch p3 = db.getPunch(1963);
        
        Punch p4 = db.getPunch(5702);
        Punch p5 = db.getPunch(4976);
        Punch p6 = db.getPunch(2193);
        
        Punch p7 = db.getPunch(954);
        Punch p8 = db.getPunch(258);
        Punch p9 = db.getPunch(717);
		
		/* Compare to Expected Values */

        assertEquals(p1.printOriginalTimestamp(), "#D2C39273 CLOCKED IN: WED 09/06/2017 07:00:07");
        assertEquals(p2.printOriginalTimestamp(), "#DFD9BB5C CLOCKED IN: TUE 09/05/2017 08:00:00");
        assertEquals(p3.printOriginalTimestamp(), "#99F0C0FA CLOCKED IN: SAT 08/19/2017 06:00:00");
        
        assertEquals(p4.printOriginalTimestamp(), "#0FFA272B CLOCKED OUT: MON 09/25/2017 17:30:04");
        assertEquals(p5.printOriginalTimestamp(), "#FCE87D9F CLOCKED OUT: TUE 09/19/2017 17:34:00");
        assertEquals(p6.printOriginalTimestamp(), "#FCE87D9F CLOCKED OUT: MON 08/21/2017 17:30:00");
        
        assertEquals(p7.printOriginalTimestamp(), "#618072EA TIMED OUT: FRI 08/11/2017 00:12:35");
        assertEquals(p8.printOriginalTimestamp(), "#0886BF12 TIMED OUT: THU 08/03/2017 06:06:38");
        assertEquals(p9.printOriginalTimestamp(), "#67637925 TIMED OUT: TUE 08/08/2017 23:12:34");
        
    }
    
    @Test
    public void testGetShift() {
		
		/* Retrieve Shift Rulesets from Database */

	Shift s1 = db.getShift(1);
        Shift s2 = db.getShift(2);
        Shift s3 = db.getShift(3);
		
		/* Compare to Expected Values */

        assertEquals(s1.toString(), "Shift 1: 07:00 - 15:30 (510 minutes); Lunch: 12:00 - 12:30 (30 minutes)");
        assertEquals(s2.toString(), "Shift 2: 12:00 - 20:30 (510 minutes); Lunch: 16:30 - 17:00 (30 minutes)");
        assertEquals(s3.toString(), "Shift 1 Early Lunch: 07:00 - 15:30 (510 minutes); Lunch: 11:30 - 12:00 (30 minutes)");

    }

}







