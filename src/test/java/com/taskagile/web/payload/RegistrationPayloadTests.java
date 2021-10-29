package com.taskagile.web.payload;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationPayloadTests {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validate_blankPayload_shouldFail() {
        RegistrationPayload payload = new RegistrationPayload();
        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(3, violations.size());
    }

    @Test
    public void validate_payloadWithInvalidEmail_shouldFail() {
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUsername("test");
        payload.setPassword("JestTest!");
        payload.setEmailAddress("test-taskAgile.com");

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(1, violations.size());
    }

    @Test
    public void validate_payloadWithEmailAddressLongerThan100_shouldFail() {
        int maxLocalParthLength = 64;
        String localPart = RandomStringUtils.random(maxLocalParthLength, true, true);
        int usedLength = maxLocalParthLength + "@".length() + ".com".length();
        String domain = RandomStringUtils.random(101 - usedLength, true, true);

        RegistrationPayload payload = new RegistrationPayload();
        payload.setUsername("test");
        payload.setPassword("JestTest!");
        payload.setEmailAddress(localPart + "@" + domain + ".com");

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(1, violations.size());
    }

    @Test
    public void validate_payloadWithUsernameShorterThan2_shouldFaile() {
        RegistrationPayload payload = new RegistrationPayload();
        String usernameTooShort = RandomStringUtils.random(1);
        payload.setUsername(usernameTooShort);
        payload.setPassword("JestTest!");
        payload.setEmailAddress("test@taskAgile.com");

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(1, violations.size());
    }

    @Test
    public void validate_payloadWithUsernameLongerThan50_shouldFail() {
        RegistrationPayload payload = new RegistrationPayload();
        String usernameTooLong = RandomStringUtils.random(51);

        payload.setUsername(usernameTooLong);
        payload.setPassword("JestTest!");
        payload.setEmailAddress("test@taskAgile.com");

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(1, violations.size());
    }

    @Test
    public void validate_payloadWithPasswordShorterThan6_shouldFail() {
        RegistrationPayload payload = new RegistrationPayload();
        String passwordTooShort = RandomStringUtils.random(5, true, true);

        payload.setUsername("test");
        payload.setPassword(passwordTooShort);
        payload.setEmailAddress("test@taskAgile.com");

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(1, violations.size());
    }

    @Test
    public void validate_payloadWithPasswordLongerThan30_shouldFail() {
        RegistrationPayload payload = new RegistrationPayload();
        String passwordTooLong = RandomStringUtils.random(31, true, true);

        payload.setUsername("test");
        payload.setPassword(passwordTooLong);
        payload.setEmailAddress("test@taskAgile.com");

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(1, violations.size());
    }
}
