package com.se300.ledger.complete;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import com.se300.ledger.Ledger;
import com.se300.ledger.Account;
import com.se300.ledger.LedgerException;

public class CompleteTest {

    /* TODO: The following
     * 1. Achieve 100% Test Coverage
     * 2. Produce/Print Identical Results to Command Line DriverTest
     * 3. Produce Quality Report
     */

    @ParameterizedTest
    @ValueSource(strings = {"Awoh", "Ava", "Zach", "Kal"})
    void parameterizedValueSourcesTest(String value) {
        // Using createAccount() from Ledger class to test if accounts can be created with different names

        // Resets ledger
        Ledger.reset();

        // Creates new ledger
        Ledger ledger = Ledger.getInstance("myTest", "Testing createAccount", "randomSeed");

        // Creates an account and catches exception if thrown
        Account account = assertDoesNotThrow(() -> ledger.createAccount(value), "Should not throw exception for valid account names");

        // Assertions
        assertNotNull(account, "Account should be successfully created.");
        assertEquals(value, account.getAddress(), "Account address should match the provided value"); 
        assertEquals(0, account.getBalance(), "Account balance should be 0");  
    }   

    @ParameterizedTest
    @CsvSource({"Awoh, 650", "Ava, 475", "Zach, 560", "Kal, 970"})
    void parameterizedComplexSourcesTest(String str, int num) {
        // Using Account class

        // Resets ledger
        Ledger.reset();

        // Creates an account
        Account account = new Account(str, num);

        // Assertions
        assertEquals(str, account.getAddress(), "Account address should match the provided address");
        assertEquals(num, account.getBalance(), "Account balance should match the provided value");
        assertNotNull(account.getAddress(), "Account address should be inputted");
        assertNotNull(account.getBalance(), "Account balance should be inputted and of type Integer");
    }


    void repeatedTest() {
        // TODO: Complete this test to demonstrate repeated test execution
    }


    void setUp() {
        // TODO: Complete this setup method for lifecycle demonstration
    }


    void tearDown() {
        // TODO: Complete this teardown method for lifecycle demonstration
    }


    void lifeCycleTest() {
        // TODO: Complete this test to demonstrate test lifecycle with BeforeEach, AfterEach, BeforeAll, AfterAll
    }

    void conditionalTest() {
        // TODO: Complete this test to demonstrate conditional test execution based on condition
    }

    void taggedTest() {
        // TODO: Complete this test to demonstrate test tagging for selective execution
    }

    class NestedTestClass {
        void nestedTest() {
            // TODO: Complete this test to demonstrate nested test classes
        }
    }

    void basicAssertionsTest() {
        // TODO: Complete this test to demonstrate basic assertions (assertEquals, assertTrue, assertFalse, etc.)
        // TODO: At least 5 different basic assertions
    }

    void advancedAssertionsTest() {
        // TODO: Complete this test to demonstrate advanced assertions (assertAll, assertThrows, assertTimeout, etc.)
        // TODO: At least 5 different advanced assertions
    }

    void mockBehaviorTest() {
        // TODO: Complete this test to demonstrate configuring mock behavior (when/then, doReturn/when, etc.)
        // TODO: At least 3 different behaviors
    }

    void assumptionsTest() {
        // TODO: Complete this test to demonstrate using assumptions (assumeTrue, assumeFalse, assumingThat, etc.)
        // TODO: At least 3 different assumptions
    }


    void mockVerificationTest() {
        // TODO: Complete this test to demonstrate verifying mock interactions (verify, times, never, etc.)
        // TODO: At least 3 different interactions
    }

    void mockArgumentMatchersTest() {
        // TODO: Complete this test to demonstrate using argument matchers with mocks (any(), eq(), etc.)
        // TODO: At least 3 different argument matchers
    }

    void methodOrderTest() {
        // TODO: Complete this test to demonstrate test method ordering using @TestMethodOrder and @Order annotations
    }
}
