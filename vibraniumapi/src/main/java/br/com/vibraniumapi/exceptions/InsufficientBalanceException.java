package br.com.vibraniumapi.exceptions;

import java.text.NumberFormat;
import java.util.Locale;

public class InsufficientBalanceException extends RuntimeException {

    private final String insufficient;

    public InsufficientBalanceException(double userAmount, double orderTotalPrice) {

        Locale brazilLocale = new Locale("pt", "BR");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(brazilLocale);
        String amount = currencyFormatter.format(userAmount);
        String orderValue = currencyFormatter.format(orderTotalPrice);
        insufficient = String.format("Insufficient balance. User balance: %s, Order value: %s", amount, orderValue);
    }
    @Override
    public String getMessage() {
        return insufficient;
    }
}
