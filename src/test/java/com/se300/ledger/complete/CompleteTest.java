package com.se300.ledger.complete;

import com.se300.ledger.Ledger;
import com.se300.ledger.Account;
import com.se300.ledger.LedgerException;
import org.junit.jupiter.api.Test; //from commissions calc repo
import static org.junit.jupiter.api.Assertions.*; //from commissions calc repo


public class CompleteTest {

    /* TODO: The following
     * 1. Achieve 100% Test Coverage
     * 2. Produce/Print Identical Results to Command Line DriverTest
     * 3. Produce Quality Report
     */ 

    void parameterizedValueSourcesTest(String value) {
        // TODO: Complete this test to demonstrate parameterized testing with simple value sources
    }

    void parameterizedComplexSourcesTest(String str, int num) {
        // TODO: Complete this test to demonstrate parameterized testing with complex sources like CSV, method sources, etc.
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
            //Use @BeforeEach for Ledger.reset() before each test HERE or in methodOrderTest()?
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

    @Test
    void basicAssertionsTest() throws LedgerException {
        // TODO: Complete this test to demonstrate basic assertions (assertEquals, assertTrue, assertFalse, assertNull, assertNotNull)
        // TODO: At least 5 different basic assertions

        // Reset ledger to ensure test isolation
        Ledger.reset(); 
        Ledger ledger = Ledger.getInstance("test", "desc", "seed");

        // Create an account and check its properties
        Account acc = ledger.createAccount("alice");
        assertNotNull(acc, "Account should not be null"); //should be true: acc is not null
        assertEquals("alice", acc.getAddress(), "Account address should be equal to 'alice'"); //should be true: address is "alice"
        System.out.println("Account Address: " + acc.getAddress() + " which should be equal to 'alice'");
        assertEquals(0, acc.getBalance(), "Account balance should be equal to 0"); //should be true: initial balance is 0

        // Test duplicate account creation throws exception (maybe don't use in real test?)
        LedgerException ex = assertThrows(LedgerException.class, () -> ledger.createAccount("alice"));
        assertTrue(ex.getReason().contains("Account Already Exists"), "Exception message should contain 'Account Already Exists'");
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
