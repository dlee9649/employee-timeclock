
package teamthreeproject;

/**
 *
 * @author TeamThree
 */
public class Badge {
    private final String id;
    private final String description;
       
    //Constructor
    public Badge(String s, String d){
    
    this.id = s;
    this.description = d;
    }
    
    @Override
    public String toString() {
        return "#" + id + " (" + description + ")";
    }
}