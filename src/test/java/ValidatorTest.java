import com.group15A.Utils.ErrorCode;
import com.group15A.Validator.Validator;
import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValidatorTest extends TestCase {
    private Validator validator;

    @Override
    protected void setUp() {
        this.validator = new Validator();
    }

    @Test
    public void testIsAlphaContainsDigits() {
        assertFalse(this.validator.isAlpha("helloworld123"));
    }

    @Test
    public void testIsAlphaContainsSpecialChars() {
        assertFalse(this.validator.isAlpha("hel$low£or%ld"));
    }

    @Test
    public void testIsAlphaCaseSensitive() {
        assertTrue(this.validator.isAlpha("HeLLoWorlD"));
    }

    @Test
    public void testIsAlphaAllCharacters() {
        assertTrue(this.validator.isAlpha("helloworld"));
    }

    @Test
    public void testIsNumContainsChars() {
        assertFalse(this.validator.isNum("12345a"));
    }

    @Test
    public void testIsNumContainsSpecialChars() {
        assertFalse(this.validator.isNum("123^42&34"));
    }

    @Test
    public void testIsAlphaAllDigits() {
        assertTrue(this.validator.isNum("12345"));
    }

    @Test
    public void testVerifyNameFailure() {
        assertTrue(this.validator.verifyName("    "));
    }

    @Test
    public void testVerifyNameSuccess() {
        assertFalse(this.validator.verifyName("John"));
    }

    @Test
    public void testVerifyFirstNameSuccess() {
        assertNull(this.validator.verifyFirstName("John"));
    }

    @Test
    public void testVerifyFirstNameFailureAndCorrectErrorCode() {
        assertEquals(this.validator.verifyFirstName("John2"), ErrorCode.WRONG_FIRST_NAME);
    }

    @Test
    public void testVerifyMiddleNameSuccessNonBlankName() {
        assertNull(this.validator.verifyMiddleName("John"));
    }

    @Test
    public void testVerifyMiddleNameSuccessBlankName() {
        assertNull(this.validator.verifyMiddleName(""));
    }

    @Test
    public void testVerifyMiddleNameFailureAndCorrectErrorCode() {
        assertEquals(this.validator.verifyMiddleName("John2"), ErrorCode.WRONG_MIDDLE_NAME);
    }

    @Test
    public void testVerifyLastNameSuccess() {
        assertNull(this.validator.verifyLastName("John"));
    }

    @Test
    public void testVerifyLastNameFailureAndCorrectErrorCode() {
        assertEquals(this.validator.verifyLastName("John2"), ErrorCode.WRONG_LAST_NAME);
    }

    @Test
    public void testVerifyDoBIncorrectFormatAndCorrectErrorCode() {
        assertEquals(this.validator.verifyDoB("03-03-2002"), ErrorCode.WRONG_DATE);
    }

    @Test
    public void testVerifyDoBFailureAndCorrectErrorCode() {
        assertEquals(this.validator.verifyDoB("not_a_date_at_all"), ErrorCode.WRONG_DATE);
    }

    @Test
    public void testVerifyDoBCorrectFormatNoZeroPadding() {
        assertNull(this.validator.verifyDoB("2002-1-5"));
    }

    @Test
    public void testVerifyDoBCorrectFormat() {
        assertNull(this.validator.verifyDoB("2002-01-05"));
    }

    @Test
    public void testVerifyGenderMale() {
        assertNull(this.validator.verifyGender("Male"));
    }

    @Test
    public void testVerifyGenderFemale() {
        assertNull(this.validator.verifyGender("Female"));
    }

    @Test
    public void testVerifyGenderOther() {
        assertNull(this.validator.verifyGender("Other"));
    }

    @Test
    public void testVerifyGenderFailreAndCorrectErrorCode() {
        assertEquals(this.validator.verifyGender("X"), ErrorCode.WRONG_GENDER);
    }

    @Test
    public void testVerifyPhoneNoNotIntAndCorrectErrorCode() {
        assertEquals(this.validator.verifyPhoneNo("079234a234"), ErrorCode.WRONG_PHONE_NO);
    }

    @Test
    public void testVerifyPhoneNoCorrectButTooShort() {
        assertEquals(this.validator.verifyPhoneNo("1234"), ErrorCode.WRONG_PHONE_NO);
    }

    @Test
    public void testVerifyPhoneNoCorrectButTooLong() {
        assertEquals(this.validator.verifyPhoneNo("1234567891234567"), ErrorCode.WRONG_PHONE_NO);
    }

    @Test
    public void testVerifyPhoneNoBlankAndCorrectErrorCode() {
        assertEquals(this.validator.verifyPhoneNo(""), ErrorCode.WRONG_PHONE_NO);
    }

    @Test
    public void testVerifyPhoneNoCorrectExactMinimum() {
        assertNull(this.validator.verifyPhoneNo("12345"));
    }

    @Test
    public void testVerifyPhoneNoCorrectExactMaximum() {
        assertNull(this.validator.verifyPhoneNo("123451234512345"));
    }

    @Test
    public void testVerifyEmailSpecialCharsAndCorrectErrorCode() {
        assertEquals(this.validator.verifyEmail("user@mail.c*om"), ErrorCode.WRONG_EMAIL);
    }

    @Test
    public void testVerifyEmailMisplacedAtAndCorrectErrorCode() {
        assertEquals(this.validator.verifyEmail("usermail.c@om"), ErrorCode.WRONG_EMAIL);
    }

    @Test
    public void testVerifyEmailNoDotAndCorrectErrorCode() {
        assertEquals(this.validator.verifyEmail("user@mailcom"), ErrorCode.WRONG_EMAIL);
    }

    @Test
    public void testVerifyEmailMissingAtAndCorrectErrorCode() {
        assertEquals(this.validator.verifyEmail("usermail.com"), ErrorCode.WRONG_EMAIL);
    }

    @Test
    public void testVerifyEmailSuccess() {
        assertNull(this.validator.verifyEmail("user@mail.com"));
    }

    @Test
    public void testVerifyPasswordTooShortAndCorrectErrorCode() {
        assertEquals(this.validator.verifyPassword("12eA5s!"), ErrorCode.WRONG_PASSWORD);
    }

    @Test
    public void testVerifyPasswordNoUppercaseAndCorrectErrorCode() {
        assertEquals(this.validator.verifyPassword("12ea5s!!"), ErrorCode.WRONG_PASSWORD);
    }

    @Test
    public void testVerifyPasswordNoLowercaesAndCorrectErrorCode() {
        assertEquals(this.validator.verifyPassword("12EA5S!"), ErrorCode.WRONG_PASSWORD);
    }

    @Test
    public void testVerifyPasswordNoDigitsAndCorrectErrorCode() {
        assertEquals(this.validator.verifyPassword("aBcDeFgH!JKLm"), ErrorCode.WRONG_PASSWORD);
    }

    @Test
    public void testVerifyPasswordNoSpecialsAndCorrectErrorCode() {
        assertEquals(this.validator.verifyPassword("12eA5s898588997866"), ErrorCode.WRONG_PASSWORD);
    }

    @Test
    public void testVerifyPasswordSuccess() {
        assertNull(this.validator.verifyPassword("12345678Aa!"));
    }

    @Test
    public void testMismatchingEmailsAndCorrectErrorCode() {
        assertEquals(this.validator.verifyMatchingEmails("email1@mail.com", "email2@mail.com"), ErrorCode.WRONG_CONFIRMED_EMAIL);
    }

    @Test
    public void testMismatchingPasswordsAndCorrectErrorCode() {
        assertEquals(this.validator.verifyMatchingPasswords("password1", "password2"), ErrorCode.WRONG_CONFIRMED_PASSWORD);
    }

    @Test
    public void testMatchingEmails() {
        assertNull(this.validator.verifyMatchingEmails("email1@mail.com", "email1@mail.com"));
    }

    @Test
    public void testMatchingPasswords() {
        assertNull(this.validator.verifyMatchingPasswords("password1", "password1"));
    }

}
