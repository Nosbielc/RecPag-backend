package br.com.hackmovile.recpag.cliente.dto;

import java.io.Serializable;
import java.util.StringJoiner;

public class TransacaoZoop implements Serializable {

    private String payment_type;
    private String currency;
    private String description;
    private String customer;
    private String on_behalf_of;
    private String amount;

    public TransacaoZoop(String payment_type, String currency, String description, String customer,
                         String on_behalf_of, String amount) {
        this.payment_type = payment_type;
        this.currency = currency;
        this.description = description;
        this.customer = customer;
        this.on_behalf_of = on_behalf_of;
        this.amount = amount;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getOn_behalf_of() {
        return on_behalf_of;
    }

    public void setOn_behalf_of(String on_behalf_of) {
        this.on_behalf_of = on_behalf_of;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TransacaoZoop.class.getSimpleName() + "[", "]")
                .add("payment_type='" + payment_type + "'")
                .add("currency='" + currency + "'")
                .add("description='" + description + "'")
                .add("customer='" + customer + "'")
                .add("on_behalf_of='" + on_behalf_of + "'")
                .add("amount='" + amount + "'")
                .toString();
    }
}
