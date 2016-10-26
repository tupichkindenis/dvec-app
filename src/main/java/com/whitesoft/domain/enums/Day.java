package com.whitesoft.domain.enums;

/**
 * Created by tupichkindenis on 22.09.16.
 */
public enum Day {
    MON("MON"),
    TUE("TUE"),
    WED("WED"),
    THU("THU"),
    FRI("FRI"),
    SAT("SAT"),
    SUN("SUN")
    ;

    private final String text;

    /**
     * @param text
     */
    private Day(final String text) {
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