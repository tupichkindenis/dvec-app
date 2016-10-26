package com.whitesoft.domain.enums;

/**
 * Created by tupichkindenis on 26.10.16.
 */
public enum PaymentSystems {
    CHRONOPAY("CHRONOPAY")
    ;

    private final String text;

    /**
     * @param text
     */
    private PaymentSystems(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
