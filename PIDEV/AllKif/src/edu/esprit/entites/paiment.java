/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entites; 


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.Balance;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import com.stripe.model.ExternalAccount;
import com.stripe.model.ExternalAccountCollection;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.model.PaymentMethodCollection;
import com.stripe.model.PaymentSourceCollection;
import com.stripe.model.Payout;
import com.stripe.model.Token;
import com.stripe.model.Topup;
import com.stripe.model.Transfer;
import com.stripe.net.RequestOptions;
import com.stripe.param.AccountCreateParams;
import com.stripe.param.AccountCreateParams.BusinessProfile;
import com.stripe.param.AccountCreateParams.Individual;
import com.stripe.param.AccountCreateParams.Individual.Address;
import com.stripe.param.AccountCreateParams.Individual.Dob;
import com.stripe.param.AccountListParams;
import com.stripe.param.AccountUpdateParams;
import com.stripe.param.CustomerListParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentMethodListParams;
import com.stripe.param.PayoutCreateParams;
import edu.Allkif.entitiesUser.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author Atef
 */
public class paiment {

    private final static String apikey = "sk_test_51MieGQDPwMAH8lgL4C9sJV2VPBXEqDQUsSJdPAm2BgHDOmyv0llQ6c6fJ45AwphL5QYbRFfT8iKGrDH14OlLHLGJ008ADrhY8i";
    private User utilisateur;

    public paiment(User u) {
        this.utilisateur = u;
    }

    public void setUtilisateur(User utilisateur) {
        this.utilisateur = utilisateur;
    }

    public User getUtilisateur() {
        return utilisateur;
    }

    public boolean CreateCustomer() {
        try {
            Stripe.apiKey = apikey;
            Map<String, Object> params = new HashMap<>();
            CustomerCollection customerList = Customer.list(params);
            for (Customer customer : customerList.getData()) {
                if (customer.getEmail().equals(utilisateur.getEmail())) {
                    return false;
                }
            }
            Map<String, Object> customerParam = new HashMap<>();
            customerParam.put("email", utilisateur.getEmail());
            customerParam.put("name", utilisateur.getPrenom() + " " + utilisateur.getNom());
            Customer.create(customerParam);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return true;

    }


    public boolean AddCardCustomer(String[] infoCarte) {
        Stripe.apiKey = apikey;
        try {
            Customer customer1 = this.getCustomer(utilisateur);

            Map<String, Object> retrieveParams = new HashMap<String, Object>();
            List<String> expandList = new ArrayList<String>();
            expandList.add("sources");
            retrieveParams.put("expand", expandList);
            Customer customer = Customer.retrieve(customer1.getId(), retrieveParams, null);

            Map<String, Object> cardParam = new HashMap<String, Object>(); //add card details
            cardParam.put("number", infoCarte[0]);
            cardParam.put("exp_month", infoCarte[1]);
            cardParam.put("exp_year", infoCarte[2]);
            cardParam.put("cvc", infoCarte[3]);

            Map<String, Object> tokenParam = new HashMap<String, Object>();
            tokenParam.put("card", cardParam);
            Token token = Token.create(tokenParam); // create a token  

            PaymentSourceCollection allCardDetails = customer.getSources();
            for (Object obj : allCardDetails.getData()) {
                Card carte = (Card) obj;
                if (carte.getFingerprint().equals(token.getCard().getFingerprint())) {
                    return false;
                }
            }
            Map<String, Object> source = new HashMap<String, Object>();
            source.put("source", token.getId()); //add token as source
            customer.getSources().create(source);

        } catch (StripeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ObservableList<PaymentMethod> getCustomerCards() {
        Stripe.apiKey = apikey;
        ObservableList<PaymentMethod> cardList = FXCollections.observableArrayList();
        try {
            Customer customer = this.getCustomer(utilisateur);
            PaymentMethodListParams params1 = PaymentMethodListParams.builder()
                    .setCustomer(customer.getId())
                    .setType(PaymentMethodListParams.Type.CARD)
                    .build();
            for (PaymentMethod paymentMethod : PaymentMethod.list(params1).getData()) {
                cardList.add(paymentMethod);
            }

        } catch (StripeException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return cardList;
    }

    public Customer getCustomer(User u) {

        Stripe.apiKey = apikey;
        Customer customer = null;
        try {
            CustomerListParams params = CustomerListParams.builder().setEmail(u.getEmail()).build();
            List<Customer> customers = Customer.list(params).getData();
            if (!customers.isEmpty()) {
                customer = customers.get(0);
            } else {
                paiment pa = new paiment(u);
                pa.CreateCustomer();
            }

        } catch (StripeException e) {
            e.printStackTrace();
            return null;
        }
        return customer;
    }

    public boolean sendMoney(User receiver, long amount, String cardid) {
        Stripe.apiKey = apikey;

        try {
            Account recipient = this.getAccount(receiver);
            if (recipient == null) {
                paiment pa = new paiment(receiver);
                pa.createConnectedAccount();
                recipient = pa.getAccount(receiver);
            }
            Account sender = this.getAccount(utilisateur);
            if (!cardid.isEmpty()) {
                Customer c = this.getCustomer(this.utilisateur);
                Map<String, Object> params2 = new HashMap<>();
                params2.put("amount", amount);
                params2.put("currency", "usd");
                params2.put("customer", c.getId());
                params2.put("source", cardid);

                Map<String, Object> transferDataParams = new HashMap<>();
                transferDataParams.put("destination", recipient.getId());
                params2.put("transfer_data", transferDataParams);
                Charge charge = Charge.create(params2);
            } else {
                Map<String, Object> params3 = new HashMap<>();
                params3.put("amount", amount);
                params3.put("currency", "usd");
                params3.put("source", sender.getId());
                params3.put(
                        "description",
                        "Allkif app"
                );
                Charge charge = Charge.create(params3);
                Map<String, Object> transferParams = new HashMap<>();
                transferParams.put("amount", amount); // amount in cents
                transferParams.put("currency", "usd");
                transferParams.put("source_transaction", charge.getId()); // charge id to transfer funds from
                transferParams.put("destination", recipient.getId()); // destination account id
                Transfer transfer = Transfer.create(transferParams);
            }

       
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public Long getBalance() {
        Stripe.apiKey = apikey;
        try {
            Account acc = this.getAccount(utilisateur);
            RequestOptions requestOptions
                    = RequestOptions.builder().setStripeAccount(acc.getId()).build();

            Balance balance = Balance.retrieve(requestOptions);
            if (balance == null) 
                return 0l;
            return (balance.getInstantAvailable().get(0).getAmount()+balance.getAvailable().get(0).getAmount())/100;
        } catch (Exception e) {
            return null;
        }
    }

    public Account getAccount(User u) {
        Stripe.apiKey = apikey;
        Account acc = null;
        try {
            AccountListParams params = AccountListParams.builder().build();
            List<Account> accounts = Account.list(params).getData();
            for (Account account : accounts) {
                if (account.getEmail().equals(u.getEmail())) {
                    return account;
                }
            }

        } catch (StripeException e) {
            System.out.println(e.getMessage());
        }

        return acc;
    }

    public void createConnectedAccount() {
        Stripe.apiKey = apikey;
        try {
            if (this.getAccount(utilisateur) != null) {
                return;
            }
            Individual individual = new Individual.Builder()
                    .setFirstName(utilisateur.getPrenom())
                    .setLastName(utilisateur.getNom())
                    .setEmail(utilisateur.getEmail())
                    .setPhone("+1 (203) 456-7890")
                    .setAddress(new Address.Builder()
                            .setLine1("1240 Carl Sanders Dr")
                            .setCity("Acworth")
                            .setState("GA")
                            .setPostalCode("30101")
                            .setCountry("US")
                            .build())
                    .setDob(new Dob.Builder()
                            .setDay(01l)
                            .setMonth(01l)
                            .setYear(1990l)
                            .build()
                    )
                    .setSsnLast4("1234")
                    .build();
            // Create a connected account
            AccountCreateParams params = AccountCreateParams.builder()
                    .setType(AccountCreateParams.Type.CUSTOM)
                    .setCountry("US")
                    .setEmail(utilisateur.getEmail())
                    .setCapabilities(AccountCreateParams.Capabilities.builder()
                            .setCardPayments(AccountCreateParams.Capabilities.CardPayments.builder()
                                    .setRequested(true)
                                    .build())
                            .setTransfers(AccountCreateParams.Capabilities.Transfers.builder()
                                    .setRequested(true)
                                    .build())
                            .build())
                    // .setExternalAccount(token.getId())
                    .setBusinessType(AccountCreateParams.BusinessType.INDIVIDUAL)
                    .setBusinessProfile(BusinessProfile.builder()
                            .setName(utilisateur.getPrenom() + " " + utilisateur.getNom())
                            .setUrl("https://www.google.com")
                            .setMcc("0742")
                            .setProductDescription("Example product")
                            .setSupportPhone("202-555-0165")
                            .setSupportEmail("support@example.com")
                            .setSupportUrl("https://google.com")
                            .build())
                    .setIndividual(individual)
                    .build();
            Account account = Account.create(params);
            //  Account resource 
            AccountUpdateParams params2
                    = AccountUpdateParams
                    .builder()
                    .setTosAcceptance(
                            AccountUpdateParams.TosAcceptance
                            .builder()
                            .setDate(1609798905L)
                            .setIp("8.8.8.8")
                            .build()
                    )
                    .build();

            account.update(params2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}