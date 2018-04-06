
package FrontEnd;

/**
 *
 * @author Rylan
 */
public class Main {

    LoginWindow login;

    public Main() {
        Client client = new Client();
        client.runClient();
    }
    
    public static void main(String[] args){
        Main m = new Main();
        
    }
    
}
