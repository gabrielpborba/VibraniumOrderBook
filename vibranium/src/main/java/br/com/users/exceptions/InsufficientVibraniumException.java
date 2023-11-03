package br.com.users.exceptions;


public class InsufficientVibraniumException extends RuntimeException {

    public InsufficientVibraniumException(int userVibraniumQuantity, int orderVibraniumQuantity) {
        super(String.format("You do not have the required amount of Vibranium to Sell: " +
                "User Vibranium quanqity %s, Order Vibranium Quantity: %s", userVibraniumQuantity, orderVibraniumQuantity));
    }

}
