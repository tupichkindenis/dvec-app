package com.whitesoft.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

/**
 * FaxNumber
 * Created by tupichkindenis on 19.09.16.
 */
@Embeddable
@JsonSerialize(using = ToStringSerializer.class)
public class FaxNumber {

    private static final String FAX_REGEX = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
    private static final Pattern PATTERN = Pattern.compile(FAX_REGEX);

    @Column(name = "fax")
    private String value;

    /**
     * Creates a new {@link FaxNumber} from the given string source.
     *
     * @param faxNumber must not be {@literal null} or empty.
     */
    public FaxNumber(String faxNumber) {
        // Assert.isTrue(isValid(faxNumber), "Invalid phone number!");
        this.value = faxNumber;
    }

    protected FaxNumber() {
    }

    /**
     * Returns whether the given {@link String} is a valid {@link FaxNumber} which means
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
