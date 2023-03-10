/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.ressources;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
public class SMSSender {

    // Twilio API credentials
    public static final String ACCOUNT_SID = "AC8a5062f8663b7cfccc18d67f528e16fd";
    public static final String AUTH_TOKEN = "ef867f837dfe11ccb89bfcc5d8fffa5c";

    // Phone numbers for the sender and recipient
    public static final String FROM_NUMBER = "+13156023594";
    public static final String TO_NUMBER = "+21690001536";

    public static void sendSMS(String messageBody) {
        System.out.println("Sending SMS...");
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber(TO_NUMBER),
                new PhoneNumber(FROM_NUMBER), messageBody)
                .create();

        System.out.println("SMS sent successfully!");
    }

}
