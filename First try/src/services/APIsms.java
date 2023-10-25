package APImed;

import entite.medecin;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;


/**
 *
 * @author HP
 */
public class APIsms {
    public static final String ACCOUNT_SID = "ACb52a631086859e7defce047aa6be2c74";
    public static final String AUTH_TOKEN = "8ae56b4318d8d875294394f5ef81c948";
    //Your Twilio Password is : gnuFA^6L63DNs!j1234
    public static void sendSMS(medecin P) {
        Twilio.init("ACb52a631086859e7defce047aa6be2c74", "8ae56b4318d8d875294394f5ef81c948");

        String messageBody = " il y a un nouveau medecin sur IRON FIT ";
       com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(
    new PhoneNumber("+21653260443"),
    new PhoneNumber("+16209109906"),
    messageBody
       
).create();

        System.out.println(message.getSid());
    }
}