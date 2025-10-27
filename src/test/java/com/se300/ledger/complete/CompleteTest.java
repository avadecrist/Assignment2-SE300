package com.se300.ledger.complete;

import com.se300.ledger.Ledger;
import com.se300.ledger.LedgerException;
import com.se300.ledger.MerkleTrees;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


// added these to prevent errors
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
//all from testing unit test to compare actual output to driver test output (which uses ledger.script)
import static org.junit.jupiter.api.Assertions.fail;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import com.se300.ledger.Account;
import com.se300.ledger.Block;
import com.se300.ledger.Ledger;
import com.se300.ledger.LedgerException;
import com.se300.ledger.Transaction;
import com.se300.ledger.MerkleTrees;
import org.junit.jupiter.api.Test; 
import static org.junit.jupiter.api.Assertions.*; 
import static org.junit.jupiter.api.Assumptions.*; 
import java.util.*;
import java.time.Duration;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

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
import static org.mockito.ArgumentMatchers.*;


public class CompleteTest {

    /* TODO: The following
     * 1. Achieve 100% Test Coverage
     * 2. Produce/Print Identical Results to Command Line DriverTest
     * 3. Produce Quality Report
     */ 


    Ledger testLedger;

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
        System.out.println("This method is executed before each test method");
        // Create a new user for each test; based on a selected class, create an object to test with

        // Let's test based on Ledger, and Name, Description and Seed
        // public testName = new name;
        // public testDescription;
        // public testSeed;

        // Declare here? Or at top? (at top)
        // Ledger testLedger;

        testLedger = new Ledger("Ava", "testDescription", "testSeed");

        // get Intsance Method, as well as getters


        // This class will be used in lifeCycleTest; so it keeps the same focus

    }

    
    void tearDown() {
        // TODO: Complete this teardown method for lifecycle demonstration

        System.out.println("Cleaning up Test Methods");

        // Make test object(s) null
        testLedger = null;
    }

    
    void lifeCycleTest() {
        // TODO: Complete this test to demonstrate test lifecycle with BeforeEach, AfterEach, BeforeAll, AfterAll
        //@BeforeEach
            // use setUp method to set up each test before
            

        //@BeforeAll
        // Prepare for all tests
        // setup a sharedLedger
        

        //@BeforeEach
        // use setUp method here! Setup tests
        setUp();
    

        //@Test
        // Run tests here!!
        // Primarily, make sure both testLedger and sharedLedger are initialized correctly

        // First, output the name, description and seed based on getters
        System.out.println("Testing Accessors for Ledger...");
        System.out.println("Test name: " + testLedger.getName());
        System.out.println("Description name: " + testLedger.getDescription());
        System.out.println("Seed name: " + testLedger.getSeed());

        // Second, test the mutators, and output the new values
        System.out.println("Testing Mutators for Ledger...");
        testLedger.setName("Kal");
        System.out.println("Test name: " + testLedger.getName());
        testLedger.setDescription("New description");
        System.out.println("Description name: " + testLedger.getDescription());
        testLedger.setSeed("newSeed");
        System.out.println("Seed name: " + testLedger.getSeed());


        //@AfterEach
        // use tearDown method to clean up specific test
        tearDown();


        //@AfterAll
        // Clean up all tests
        // set sharedLedger to null
        
            //Use @BeforeEach for Ledger.reset() before each test HERE or in methodOrderTest()?
    }

    
    void conditionalTest() {
        // TODO: Complete this test to demonstrate conditional test execution based on condition

        // Output a print statement for when test starts
        System.out.println("Running conditionalMerkleTreeTest...");

        // Create a transactions ArrayList for testing
        List<String> transactions = new ArrayList<>();
        transactions.add("tx1");
        transactions.add("tx2");

        if (transactions.isEmpty()) {
            System.out.println("Skipping test — transaction list is empty.");
            return;
        }

        // Create a test MerkleTrees tree for testing
        MerkleTrees tree = new MerkleTrees(transactions);
        tree.merkle_tree();

        // Test the tree
        String root = tree.getRoot();
        if (root == null || root.isEmpty()) {
            System.out.println("Test Error: Root was not generated.");
        } else {
            System.out.println("Test Success! Merkle root generated successfully: " + root);
        }

        // Set variables to null to clean up the test
        tree = null;
        transactions = null;
        System.out.println("Cleaned up test variables.");
    }

    
    void taggedTest() {
        // TODO: Complete this test to demonstrate test tagging for selective execution
    }

    
    class NestedTestClass {
        void nestedTest() {
            // TODO: Complete this test to demonstrate nested test classes
        }
    }

    /**
     * Test that the Block model can be created and used correctly.
     */
    @Test
    void basicAssertionsTest() {
        // TODO: Complete this test to demonstrate basic assertions (assertEquals, assertTrue, assertFalse, assertNull, assertNotNull)
        // TODO: At least 5 different basic assertions

        // Reset ledger to ensure test isolation
        System.out.println("\n==========================================================\nStarting basicAssertionsTest...");
        Ledger.reset(); 
        Ledger ledger = Ledger.getInstance("test", "desc", "seed");
        
    
    // Create a block and check its properties:
        System.out.println("Creating a block to test its properties");
        Block blockTest = new Block(2, "prev-hash-123"); 
        assertEquals(2, blockTest.getBlockNumber(), "Block number should be 2");
        assertEquals("prev-hash-123", blockTest.getPreviousHash(), "Previous hash should be 'prev-hash-123'");

        System.out.println("Testing the set and get hash methods");
        blockTest.setHash("hash-abc");
        assertNotEquals("prev-hash-123", blockTest.getHash(), "The current has was updated so it should not equal the previous hash");

        System.out.println("Adding accounts and verifying they are retrievable");
        Account kal = new Account("kal", 100);
        Account zach = new Account("zach", 250);
        blockTest.addAccount(kal.getAddress(), kal);
        blockTest.addAccount(zach.getAddress(), zach);

        System.out.println("Testing getAccount, getAddress, and getBalance based on Block");
        Account gotKal = blockTest.getAccount("kal");
        assertNotNull(gotKal, "The account gotKal should not be null");
        assertEquals("kal", gotKal.getAddress(), "Account address should be 'kal'");
        assertEquals(100, gotKal.getBalance(), "Account balance should be 100");

        System.out.println("Testing if map of accounts contains entries using getAccountBalanceMap");
        assertTrue(blockTest.getAccountBalanceMap().containsKey("kal"), "Account map should contain kal");
        assertTrue(blockTest.getAccountBalanceMap().containsKey("zach"), "Account map should contain zach");

        System.out.println("Testing previous block linking");
        Block prevBlock = new Block(1, "prev-prev");
        prevBlock.setHash("prevHashVal");
        blockTest.setPreviousBlock(prevBlock);
        assertSame(prevBlock, blockTest.getPreviousBlock(), "Previous block should be the same instance as prevBlock"); //Verifies that two references point to the same object

        System.out.println("Testing transaction list: add a transaction directly and verify content");
        Transaction transaction = new Transaction("1", 10, 1, "note", kal, zach);
        blockTest.getTransactionList().add(transaction);
        assertEquals(1, blockTest.getTransactionList().size());
        
        Transaction transactionToCheck = blockTest.getTransactionList().get(0);
        assertEquals("1", transactionToCheck.getTransactionId(), "Transaction ID should be '1'");
        assertEquals(10, transactionToCheck.getAmount(), "Transaction amount should be 10");

        System.out.println("Testing if toString includes the payer/receiver addresses");
        assertTrue(transactionToCheck.toString().contains("Payer: kal"), "Transaction string should include kal");
        assertTrue(transactionToCheck.toString().contains("Receiver: zach"), "Transaction string should include zach");
    }

    
    /**
     * Test that the Account model can be created and used correctly.
     */
    @Test
    void advancedAssertionsTest() throws LedgerException {
        System.out.println("\n==========================================================\nStarting advancedAssertionsTest...");
        Ledger.reset(); 
        Ledger ledger = Ledger.getInstance("test", "desc", "seed");

        // 1. Test duplicate account creation throws exception
        System.out.println("Testing if duplicate account creation throws LedgerException");
        Account acc1 = ledger.createAccount("ava");
        assertThrows(LedgerException.class, () -> {
            ledger.createAccount("ava");
        }, "Adding an account that already exist should throw an exception");
        
        // 2. Verify adding an account that doesn't already exist doesn't throw an exception
        System.out.println("Testing that adding a new account will not throw LedgerException");
        assertDoesNotThrow(() -> {
            ledger.createAccount("ab");
        }, "Adding an account that does not already exist should not throw an exception");

        // setting up accounts as blockchain
        System.out.println("Setting up accounts as blocks to further test account properties");
        Account acc2 = ledger.createAccount("kalyan");
        Block block = new Block(3, "prev-hash-number");
        block.addAccount(acc1.getAddress(), acc1);
        block.addAccount(acc2.getAddress(), acc2);
        // 3. Use assertAll to test multiple properties about Account
        System.out.println("Using assertAll to verify multiple account properties at once");
        assertAll("Account properties",
            () -> assertEquals("ava", acc1.getAddress(), "Account name should match"),
            () -> assertNotNull(acc1.getAddress(), "Account address should not be null"),
            () -> assertEquals(acc1.getBalance(), 0, "New account balance should be zero"),
            () -> assertNotEquals(acc1.getAddress(), acc2.getAddress(), "Different accounts should have different addresses"),
            () -> assertEquals(block.getAccount(acc1.getAddress()), acc1, "The account retrieved from block should match the one added")
        );

        // 4. Use assertIterableEquals() to verify two lists of transactions are the same
        System.out.println("Verifying two lists of transactions are the same:");
        // Create lists of transaction
        Transaction transaction1 = new Transaction("tx1", 50, 11, "transaction test", acc1, acc2);
        System.out.println("Created transaction1: " + transaction1);
        Transaction transaction2 = new Transaction("tx2", 100, 11, "transaction test 2", acc2, acc1);
        System.out.println("Created transaction2: " + transaction2);
        block.getTransactionList().add(transaction1);
        System.out.println("Added transaction1 to block");
        block.getTransactionList().add(transaction2);
        System.out.println("Added transaction2 to block");

        List<Transaction> expectedTransactions = Arrays.asList(transaction1, transaction2);
        System.out.println("Created expected transactions list");
        List<Transaction> actualtransactions = block.getTransactionList();
        System.out.println("Retrieved actual transactions list from block");

        // Test that the lists are equal
        assertIterableEquals(expectedTransactions, actualtransactions,
                "Transaction list should match expected transactions");
        System.out.println("Verified that expected and actual transaction lists are equal");

        // 5. Use assertTimeout to ensure processTransaction method completes within a time limit
        System.out.println("Testing that processTransaction completes within a time limit");
        acc1.setBalance(500); // Set initial balance for payer
        acc2.setBalance(300); // Set initial balance for receiver
        Transaction transactionToProcess = new Transaction("tx-process", 30, 15, "process test", acc1, acc2);

        System.out.println("Processing transaction: " + transactionToProcess.getTransactionId());
        System.out.println("Payer: " + transactionToProcess.getPayer().getAddress());
        System.out.println("Receiver: " + transactionToProcess.getReceiver().getAddress());
        System.out.println("Amount: " + transactionToProcess.getAmount());


        assertTimeout(Duration.ofSeconds(1), () -> {
            ledger.processTransaction(transaction1);
        }, "Transaction processing should complete within 1 second");
        System.out.println("Verified that processTransaction completes within the time limit of 1 second");

    }

    /**
     * Test demonstrating configuring mock behavior for Transactions, MerkleTree, and Ledger (when/then, doReturn/when, etc.)
     */
    @Test
    void mockBehaviorTest() throws LedgerException{
        // TODO: Complete this test to demonstrate configuring mock behavior (when/then, doReturn/when, etc.)
        // TODO: At least 3 different behaviors

        System.out.println("\n==========================================================\nStarting mockBehaviorTest...");
        // Create a mock Ledger
        Ledger.reset();
        Ledger ledger = Ledger.getInstance("Main", "Mock Test Ledger", "seed123");

        // 1: Mock a Transaction and define behavior
        Transaction mockTx = mock(Transaction.class);
        Account mockPayer = mock(Account.class);
        Account mockReceiver = mock(Account.class);

        // when(...) → thenReturn(...) behavior
        System.out.println("Setting up mock Transaction behavior using when/thenReturn");
        when(mockTx.getAmount()).thenReturn(100);
        when(mockTx.getFee()).thenReturn(10);
        when(mockTx.getNote()).thenReturn("Unit test transaction");
        when(mockTx.getTransactionId()).thenReturn("tx001");
        when(mockTx.getPayer()).thenReturn(mockPayer);
        when(mockTx.getReceiver()).thenReturn(mockReceiver);

        // Mock payer and receiver balances
        when(mockPayer.getBalance()).thenReturn(Integer.MAX_VALUE);
        when(mockReceiver.getBalance()).thenReturn(0);
        when(mockPayer.getAddress()).thenReturn("payer");
        when(mockReceiver.getAddress()).thenReturn("receiver");

        // Add accounts to uncommitted block to simulate real use
        ledger.getUncommittedBlock().addAccount("payer", mockPayer);
        ledger.getUncommittedBlock().addAccount("receiver", mockReceiver);

        // 2. Use doReturn(...).when(...) for method that would normally compute
        System.out.println("Setting up mock MerkleTrees behavior using doReturn/when");
        MerkleTrees mockMerkle = mock(MerkleTrees.class);
        doReturn("FAKE_MERKLE_ROOT_HASH").when(mockMerkle).getRoot();

        // 3. Spy on Ledger and override one of its methods
        System.out.println("Creating a spy Ledger to override getNumberOfBlocks method");
        Ledger spyLedger = spy(ledger);
        doReturn(42).when(spyLedger).getNumberOfBlocks();  // fake block count

        // Act
        String txId = spyLedger.processTransaction(mockTx);

        // Assert behavior
        assertEquals("tx001", txId);  // should match mocked ID
        assertEquals(42, spyLedger.getNumberOfBlocks()); // spy override works
        assertEquals("FAKE_MERKLE_ROOT_HASH", mockMerkle.getRoot()); // mocked root value

        // Verify interactions
        System.out.println("Using verify to check interactions with mock objects");
        verify(mockTx, times(2)).getTransactionId();
        verify(mockPayer, atLeastOnce()).getBalance();
        verify(mockReceiver, never()).setBalance(999); // ensure not misused

    }

    /**
     * Test LedgerExceptions in processTransaction and validate method in Ledger Class
     */
    @Test
    void assumptionsTest() throws LedgerException{
        // TODO: Complete this test to demonstrate using assumptions (assumeTrue, assumeFalse, assumingThat, etc.)
        // TODO: At least 3 different assumptions

        //ASSUMETRUE
        System.out.println("\n==========================================================\nStarting assumptionsTest...");
        Ledger.reset();
        Ledger ledger = Ledger.getInstance("test", "desc", "seed");

        System.out.println("Assuming it's true that a ledger instance is initialized:");
        assumeTrue(ledger.isInitialized(), 
                "Skipping test because ledger does not have an instance");

        // create test accounts
        Account user1 = ledger.createAccount("payer");
        Account user2 = ledger.createAccount("receiver");
        user1.setBalance(500);
        user2.setBalance(550);

        // create a valid transaction
        System.out.println("Creating a valid transaction between user1 and user2 to test processTransaction results:");
        Transaction transaction = new Transaction("tx1", 100, 20, "test payment", user1, user2);
        
        //Store the outcome of processing a transaction as a String
        String processedTransaction = ledger.processTransaction(transaction);
        System.out.println("Processed Transaction: " + processedTransaction);

        // Assertions will be executed if the assumption passes
        assertAll("Verify transaction processing results",
            () -> assertEquals("tx1", transaction.getTransactionId(), "Transaction ID should match"),
            () -> assertEquals(380, user1.getBalance(), "Payer balance should decrease by amount + fee"),
            () -> assertEquals(650, user2.getBalance(), "Receiver balance should increase by amount"),
            () -> assertTrue(transaction.getFee() >= 10, "Transaction fee must meet the minimum requirement")
        );
        System.out.println("Verified transaction processing results successfully.");

        //ASSUMEFALSE
        System.out.println("\n-----------------------------------------------------\nAssuming transaction list size from uncommitted block is NOT 10:");

        Block uncommittedBlock = ledger.getUncommittedBlock();
        assumeFalse(uncommittedBlock.getTransactionList().size()== 10,
            "Skipping test: uncommitted block gets added to blockMap only after getTransactionList() reaches 10");

        // executes if assumption is false
        //validate() will throw exceptions if called before the first block is committed, because they rely on blockMap.lastEntry()
        Transaction newTransaction = new Transaction("tx-new", 50, 15, "new test payment", user1, user2);
        ledger.processTransaction(newTransaction); //process another transaction to ensure uncommitted block exists
        
        System.out.println("After processing another transaction, test to see if an exception is thrown because of the blockMap being empty ");
        LedgerException blockMapException = assertThrows(LedgerException.class, () -> {
            ledger.validate();
        });

        System.out.println("Exception caught as expected when calling validate() with empty block map: '" + blockMapException.getReason() + "'");


        //ASSUMINGTHAT
        System.out.println("\n-----------------------------------------------------\nAssuming that user1 has insufficient funds to process another transaction:");
        
        //try on new transaction
        Transaction transaction2 = new Transaction("tx2", 450, 60, "test payment 2", user1, user2);
        assumingThat(user1.getBalance() < (transaction2.getAmount() + transaction2.getFee()), () -> {
            System.out.println("User1 balance: " + user1.getBalance() + " is less than transaction total: "
            + (transaction2.getAmount() + transaction2.getFee()) + ". Should throw a LedgerException.");

            LedgerException exception = assertThrows(LedgerException.class, () -> {
                ledger.processTransaction(transaction2);
            }, "Processing transaction with insufficient balance should throw LedgerException");
            //System.out.println("LedgerException caught as expected: " + exception.getReason());
            assertTrue(exception.getReason().contains("Payer Does Not Have Required Funds"),
            "Should throw LedgerException for insufficient balance");
            System.out.println("Real Reason: '" + exception.getReason() + "' matches Expected Reason: 'Payer Does Not Have Required Funds'.");
        });
        
        System.out.println("Verified assumingThat() LedgerException for insufficient balance successfully.");
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

    //kal
    void methodOrderTest() {
        // TODO: Complete this test to demonstrate test method ordering using @TestMethodOrder and @Order annotations
            // Use @TestMethodOrder(MethodOrderer.OrderAnnotation.class) at the top of CompleteTest Class level?
            // Order all the unit tests accordingly
            // use methodOrderTest to verify the ordering happened as planned (refer to verifyExecutionOrder method in ex. repo)
    }


    //-----------------------------------------------------------------------------------------
// @Test
//     void reproduceDriverOutput() throws Exception {
//         Ledger.reset();

//         // capture stdout
//         PrintStream originalOut = System.out;
//         ByteArrayOutputStream baos = new ByteArrayOutputStream();
//         System.setOut(new PrintStream(baos));

//         try {
//             new com.se300.ledger.command.DriverTest().testDriver();
//         } finally {
//             System.out.flush();
//             System.setOut(originalOut);
//         }

//         String actual = baos.toString(java.nio.charset.StandardCharsets.UTF_8);
//         String expected = new String(
//             getClass().getResourceAsStream("/results_ledger-1.txt").readAllBytes(),
//             java.nio.charset.StandardCharsets.UTF_8);

//         String normActual = normalize(actual);
//         String normExpected = normalize(expected);

//         // Always write actual output, even if test passes
//         java.nio.file.Path outDir = java.nio.file.Paths.get("target", "test-outputs");
//         java.nio.file.Files.createDirectories(outDir);
//         java.nio.file.Path actualFile = outDir.resolve("actual-results.txt");
//         java.nio.file.Files.writeString(actualFile, actual, java.nio.charset.StandardCharsets.UTF_8);

//         if (!normActual.equals(normExpected)) {
//             fail("Output differs from expected. Actual output written to: " + actualFile.toString());
//         }
//     }

//     // simple normalization helper example
//     private static String normalize(String s) {
//         return java.util.Arrays.stream(s.replace("\r\n", "\n").split("\n"))
//                      .map(String::trim)            // trim trailing/leading whitespace per line
//                      .filter(line -> !line.isEmpty()) // optionally drop empty lines
//                      .collect(java.util.stream.Collectors.joining("\n"))
//                      .trim();
//     }
    //-----------------------------------------------------------------------------------------

}

