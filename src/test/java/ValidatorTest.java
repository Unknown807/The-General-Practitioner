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
    public void testVerifyFirstNameSuccessBlankName() {
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

    


}
