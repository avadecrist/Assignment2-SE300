package com.se300.ledger.complete;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Answers.valueOf;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import com.se300.ledger.Ledger;
import com.se300.ledger.Account;
import com.se300.ledger.LedgerException;
import com.se300.ledger.Block;


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

        // Basic Assertions
        assertEquals(str, account.getAddress(), "Account address should match the provided address");
        assertEquals(num, account.getBalance(), "Account balance should match the provided value");
        assertNotNull(account.getAddress(), "Account address should be inputted");
        assertNotNull(account.getBalance(), "Account balance should be inputted and of type Integer");
        
        // Testing that the address is modifiable
        String newAddress = str + "_new";
        account.setAddress(newAddress);
        assertEquals(newAddress, account.getAddress(), "Address should be modifiable");

        // Testing that the balance is modifiable
        Integer newBalance = num + 100;
        account.setBalance(newBalance);
        assertEquals(newBalance, account.getBalance(), "Balance should be modifiable");

        // Testing that the clone method works by comparing account address and balance to the cloned ones
        Object cloned = account.clone();
        Account clonedAccount = (Account) cloned;
        assertEquals(account.getAddress(), clonedAccount.getAddress(), "Accounts should have the same address");
        assertEquals(account.getBalance(), clonedAccount.getBalance(), "Accounts should have the same balance"); 
    }

    @RepeatedTest(4)
    void repeatedTest() {
        // Resets Ledger
        Ledger.reset();

        // Necessary variables
        int blockNum = (int) (Math.random() * 100) + 1;
        String prevHash = String.valueOf(Math.random());

        // Creates new block
        Block block = new Block(blockNum, prevHash);

        // Testing that the hash is modifiable
        String testHash = String.valueOf(blockNum);
        block.setHash(testHash);
        assertEquals(testHash, block.getHash(), "Hash should be modifiable");

        // Testing adding an account

        // Creating a new account
        Account testAccount = new Account("abAccount", 750);
        // Adding to block
        block.addAccount(testAccount.getAddress(), testAccount);
        // Creating a variable to represent testAccount that was added being retrieved
        Account retrievedAccount = block.getAccount(testAccount.getAddress());
        // Checking if account was added
        assertNotNull(retrievedAccount, "Account should be added.");
        
        // Basic Assertions
        assertNotNull(block, "Block should be created successfully");
        assertEquals(blockNum, block.getBlockNumber(), "Block number should match inputted number.");
        assertEquals(prevHash, block.getPreviousHash(), "Hash number should match inputted hash number");
        assertEquals(testAccount.getAddress(), retrievedAccount.getAddress(), "Retrieved account should have same address as test account");
        assertEquals(testAccount.getBalance(), retrievedAccount.getBalance(), "Retrieved account balance should have the same balance as test account");
         

        

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
