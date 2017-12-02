import teamthreeproject.*;

import org.junit.*;
import static org.junit.Assert.*;

public class Feature4 {
    
    private TASDatabase db;
    
    @Before
    public void setup() {
        db = new TASDatabase();
    }
    
    @Test
    public void testMinutesAccruedShift1Weekday() {
		
		/* Get Punch */
        
        Punch p = db.getPunch(3634);
		
		/* Compute Pay Period Total */
        
        int m = db.getMinutesAccrued(p);
		
		/* Compare to Expected Value */
        
        assertEquals(m, 480);
        
    }
    
    @Test
    public void testMinutesAccruedShift1Weekend() {
		
		/* Get Punch */
        
        Punch p = db.getPunch(1087);
		
		/* Compute Pay Period Total */
        
        int m = db.getMinutesAccrued(p);
		
		/* Compare to Expected Value */
        
        assertEquals(m, 330);
        
    }

    @Test
    public void testMinutesAccruedShift2Weekday() {
		
		/* Get Punch */
        
        Punch p = db.getPunch(4943);
		
		/* Compute Pay Period Total */
        
        int m = db.getMinutesAccrued(p);
		
		/* Compare to Expected Value */
        
        assertEquals(m, 540);
        
    }
    
}