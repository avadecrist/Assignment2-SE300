package com.se300.ledger.complete;

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

import org.junit.jupiter.api.Test; //from commissions calc repo
import static org.junit.jupiter.api.Assertions.*; //from commissions calc repo
import java.util.Arrays;
import java.util.List;
import java.time.Duration;


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
    void advancedAssertionsTest() throws LedgerException { //look at slide 45 in Week 7
        // TODO: Complete this test to demonstrate advanced assertions (assertAll, assertThrows, assertTimeout, etc.)
        // TODO: At least 5 different advanced assertions


         //   } -------------------------------------*/
        //4. use assertTimeout to ensure some operation completes within a time limit
            /*----------example to follow----------
            @Test
            public void testAssertTimeout() {
                // Define a task that should complete quickly
                Executable task = () -> {
                    // Simulate some processing
                    Thread.sleep(50); // Simulate delay
                };

                // Assert that the task completes within 100 milliseconds
                assertTimeout(Duration.ofMillis(100), task, 
                        "Task should complete within 100 milliseconds");
            } -------------------------------------*/
    
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
        System.out.println("Verified that processTransaction completes within the time limit");



    }

    // test CommandProcessor, W8 or W9
    void mockBehaviorTest() {
        // TODO: Complete this test to demonstrate configuring mock behavior (when/then, doReturn/when, etc.)
        // TODO: At least 3 different behaviors
    }

    //ava test Transaction class?
    void assumptionsTest() {
        // TODO: Complete this test to demonstrate using assumptions (assumeTrue, assumeFalse, assumingThat, etc.)
        // TODO: At least 3 different assumptions
    }

    //AB
    void mockVerificationTest() {
        // TODO: Complete this test to demonstrate verifying mock interactions (verify, times, never, etc.)
        // TODO: At least 3 different interactions
    }

    //kal
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

