
package teamthreeproject;

import java.text.SimpleDateFormat;

/**
 *
 * @author TeamThree
 */
public class TeamThreeProject {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        TASDatabase db = new TASDatabase();
        
        Punch p = db.getPunch(4017);
        
        int total = db.getMinutesAccrued(p);
        
        db.closeConnection();
    
    }
}
