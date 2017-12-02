import teamthreeproject.*;
import java.text.SimpleDateFormat;

import org.junit.*;
import static org.junit.Assert.*;

public class Feature2 {
    
    private TASDatabase db;
    
    @Before
    public void setup() {
        db = new TASDatabase();
    }
    
    @Test
    public void testInsertCheckPunch() {
		
		/* Create New Punch Object */

        Punch p1 = new Punch("021890C0", 101, 1);
		
		/* Get Punch Properties */
        
        String badgeid = p1.getBadgeID();
        String originaltimestamp = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(p1.getOriginalTimestamp().getTime());
        int terminalid = p1.getTerminalID();
        int eventtypeid = p1.getEventTypeID();
		
		/* Insert Punch Into Database */
        
        int punchid = db.insertPunch(p1);
		
		/* Retrieve New Punch */
        
        Punch p2 = db.getPunch(punchid);
		
		/* Compare Punches */

        assertEquals(badgeid, p2.getBadgeID());
        assertEquals(originaltimestamp, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(p2.getOriginalTimestamp().getTime()));
        assertEquals(terminalid, p2.getTerminalID());
        assertEquals(eventtypeid, p2.getEventTypeID());
        
    }

}







