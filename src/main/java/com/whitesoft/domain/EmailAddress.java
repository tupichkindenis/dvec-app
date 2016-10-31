package com.whitesoft.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.whitesoft.dataimport.service.ImportFromJsonDataFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * A value object abstraction of an email address.
 *
 * Created by tupichkindenis on 19.09.16.
 */
@Embeddable
@JsonSerialize(using = ToStringSerializer.class)
public class EmailAddress {

    private static final Logger logger = LoggerFactory.getLogger(EmailAddress.class);


    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);

    @Column(name = "email")
    private String value;

    /**
     * Creates a new {@link EmailAddress} from the given string source.
     *
     * @param emailAddress must not be {@literal null} or empty.
     */
    public EmailAddress(String emailAddress) {
        if (!isValid(emailAddress)){
            logger.error("Email address doe not valid: " + emailAddress);
            Assert.isTrue(isValid(emailAddress), "Invalid email address!");
        }

        this.value = emailAddress;
    }

    protected EmailAddress() {

    }

    /**
     * Returns whether the given {@link String} is a valid {@link EmailAddress} which means you can safely instantiate the
     * class.
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
