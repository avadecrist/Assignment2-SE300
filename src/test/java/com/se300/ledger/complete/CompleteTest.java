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
import org.junit.jupiter.api.Test;
import com.se300.ledger.Transaction;
import static org.mockito.Mockito.*;

import java.util.Map;

import static org.mockito.ArgumentMatchers.*;


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

        // Testing getAccountBalance() throws exception for uncommitted accounts
        LedgerException exception = assertThrows(LedgerException.class, () -> ledger.getAccountBalance(value), "Should throw exception for uncommitted account");
        assertTrue(exception.getReason().contains("Account Is Not Committed to a Block"));

        // Testing getAccountBalances()
        Map<String, Integer> accBalances = ledger.getAccountBalances();
        assertNull(accBalances, "Map of account balances should be null when all blocks are uncommitted.");

        // Basic Assertions
        assertNotNull(account, "Account should be successfully created.");
        assertEquals(value, account.getAddress(), "Account address should match the provided value"); 
        assertEquals(0, account.getBalance(), "Account balance should be 0");  
    
        // Testing that the Ledger name is modifiable
        ledger.setName("newName");
        assertEquals("newName", ledger.getName(), "Name should be modifiable");

        // Testing that the Ledger description is modifiable
        ledger.setDescription("newDescription");
        assertEquals("newDescription", ledger.getDescription(), "Description should be modifiable");

        // Testing that the Ledger seed is modifiable
        ledger.setSeed("newSeed");
        assertEquals("newSeed", ledger.getSeed(), "Seed should be modifiable"); 

        // Testing getNumberOfBlocks
        int blockCount = ledger.getNumberOfBlocks();
        assertEquals(0, blockCount, "Should have 0 blocks initially.");

        // Testing getUncommittedBlock
        Block uncommittedBlock = ledger.getUncommittedBlock();
        assertEquals(1, uncommittedBlock.getBlockNumber(), "Should be block 1");

        // Resets ledger
        Ledger.reset();
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

        // Testing that the hash & previous hash are modifiable
        String testHash = String.valueOf(blockNum);
        block.setHash(testHash);
        assertEquals(testHash, block.getHash(), "Hash should be modifiable");

        String newprevHash = String.valueOf(Math.random());
        block.setPreviousHash(newprevHash);
        assertEquals(newprevHash, block.getPreviousHash(), "Previous hash number should be modifiable.");

        // Testing that the block number is modifiable
        int newblockNum = blockNum + 100;
        block.setBlockNumber(newblockNum);
        assertEquals(newblockNum, block.getBlockNumber(), "Block number should be modifiable.");

        // Testing adding an account
        // Creating a new account
        Account testAccount = new Account("abAccount", 750);
        // Adding to block
        block.addAccount(testAccount.getAddress(), testAccount);
        // Creating a variable to represent testAccount that was added being retrieved
        Account retrievedAccount = block.getAccount(testAccount.getAddress());
        // Checking if account was added
        assertNotNull(retrievedAccount, "Account should be added.");

        // Testing that a block is modifiable
        Block prevBlock = new Block(6, "granola");
        block.setPreviousBlock(prevBlock);
        assertEquals(prevBlock, block.getPreviousBlock(), "Block should match inputted values");
        
        // Basic Assertions
        assertNotNull(block, "Block should be created successfully");
        assertEquals(newblockNum, block.getBlockNumber(), "Block number should match inputted number.");
        assertEquals(newprevHash, block.getPreviousHash(), "Previous hash number should be modifiable");
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

    @Test
    void mockVerificationTest() {
        // Create mock transaction
        Transaction mockTransaction = mock(Transaction.class);
        
        // Create mock account payer object
        Account mockPayer = mock(Account.class);

        // Create mock account reciever object
        Account mockReceiver = mock(Account.class);

        // whenthenReturn statements
        when(mockTransaction.getTransactionId()).thenReturn("tx016");
        when(mockTransaction.getAmount()).thenReturn(600);
        when(mockTransaction.getFee()).thenReturn(6);
        when(mockTransaction.getNote()).thenReturn("Mock transaction note.");
        when(mockPayer.getAddress()).thenReturn("AB");
        when(mockReceiver.getAddress()).thenReturn("Ava");

        // Method calls 
        String transID = mockTransaction.getTransactionId();

        Integer transAmount1 = mockTransaction.getAmount();
        Integer transAmount2 = mockTransaction.getAmount();

        Integer transFee1 = mockTransaction.getFee();
        Integer transFee2 = mockTransaction.getFee();
        Integer transFee3 = mockTransaction.getFee();
        Integer transFee4 = mockTransaction.getFee();

        String payrAddress = mockPayer.getAddress();

        // Verifications
        // Verify mockTransaction was called 
        verify(mockTransaction).getTransactionId();

        // Verify transAmount was called twice
        verify(mockTransaction, times(2)).getAmount();

        // Verify transFee was called at least twice
        verify(mockTransaction, atLeast(2)).getFee();

        // Verify transaction note was never called
        verify(mockTransaction, never()).getNote();

        // Verify payrAddress was called once
        verify(mockPayer, times(1)).getAddress();

        // Verify receiver address was never called
        verify(mockReceiver, never()).getAddress();
    }

    void mockArgumentMatchersTest() {
        // TODO: Complete this test to demonstrate using argument matchers with mocks (any(), eq(), etc.)
        // TODO: At least 3 different argument matchers
    }

    void methodOrderTest() {
        // TODO: Complete this test to demonstrate test method ordering using @TestMethodOrder and @Order annotations
    }
}
