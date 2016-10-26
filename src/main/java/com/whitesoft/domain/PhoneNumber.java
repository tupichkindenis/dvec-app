package com.whitesoft.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.util.Assert;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PhoneNumber
 * Created by tupichkindenis on 19.09.16.
 */
@Embeddable
@JsonSerialize(using = ToStringSerializer.class)
public class PhoneNumber {

    private static final String PHONE_REGEX = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
    private static final Pattern PATTERN = Pattern.compile(PHONE_REGEX);

    @Column(name = "phone")
    private String value;

    /**
     * Creates a new {@link PhoneNumber} from the given string source.
     *
     * @param phoneNumber must not be {@literal null} or empty.
     */
    public PhoneNumber(String phoneNumber) {
        // Assert.isTrue(isValid(phoneNumber), "Invalid phone number!");
        this.value = phoneNumber;
    }

    protected PhoneNumber() {
    }

    /**
     * Returns whether the given {@link String} is a valid {@link PhoneNumber} which means
     * you can safely instantiate the class.
     *
     * @param source
     * @return
     */
    public static boolean isValid(String source) {
        return PATTERN.matcher(source).matches();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return value;
    }

}
