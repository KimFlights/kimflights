package com.kimgroup.kimflights.payment.model;

public enum CardBrand {
    VISA("4"),
    MASTERCARD("5"),
    AMEX("3"),
    OTHER(""),
    UNKNOWN("");

    private final String prefix;

    CardBrand(String prefix) {
        this.prefix = prefix;
    }

    public static CardBrand fromBin(String bin) {
        if (bin == null || bin.isEmpty()) {
            return UNKNOWN;
        }
        for (CardBrand brand : values()) {
            if (!brand.prefix.isEmpty() && bin.startsWith(brand.prefix)) {
                return brand;
            }
        }
        return OTHER;
    }
}
