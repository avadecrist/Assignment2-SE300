package com.se300.ledger.complete;

//all from testing unit test to compare actual output to driver test output (which uses ledger.script)
import static org.junit.jupiter.api.Assertions.fail;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.se300.ledger.Account;
import com.se300.ledger.Block;
import com.se300.ledger.Ledger;
import com.se300.ledger.LedgerException;
import com.se300.ledger.Transaction;
import org.junit.jupiter.api.Test; //from commissions calc repo
import static org.junit.jupiter.api.Assertions.*; //from commissions calc repo


public class CompleteTest {

    /* TODO: The following
     * 1. Achieve 100% Test Coverage
     * 2. Produce/Print Identical Results to Command Line DriverTest
     * 3. Produce Quality Report
     */ 

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


    //AB
    void parameterizedValueSourcesTest(String value) {
        // TODO: Complete this test to demonstrate parameterized testing with simple value sources
    }

    //AB
    void parameterizedComplexSourcesTest(String str, int num) {
        // TODO: Complete this test to demonstrate parameterized testing with complex sources like CSV, method sources, etc.
    }

    //AB
    void repeatedTest() {
        // TODO: Complete this test to demonstrate repeated test execution
    }

    //zach
    void setUp() {
        // TODO: Complete this setup method for lifecycle demonstration
    }

    //zach
    void tearDown() {
        // TODO: Complete this teardown method for lifecycle demonstration
    }

    //zach
    void lifeCycleTest() {
        // TODO: Complete this test to demonstrate test lifecycle with BeforeEach, AfterEach, BeforeAll, AfterAll
            //Use @BeforeEach for Ledger.reset() before each test HERE or in methodOrderTest()?
    }

    //zach
    void conditionalTest() {
        // TODO: Complete this test to demonstrate conditional test execution based on condition
    }

    //kal
    void taggedTest() {
        // TODO: Complete this test to demonstrate test tagging for selective execution
    }

    //kal
    class NestedTestClass {
        void nestedTest() {
            // TODO: Complete this test to demonstrate nested test classes
        }
    }

    
    /**
     * Test that the Block model can be created and used correctly.
     */
    @Test
    void basicAssertionsTest() {//throws LedgerException {
        // TODO: Complete this test to demonstrate basic assertions (assertEquals, assertTrue, assertFalse, assertNull, assertNotNull)
        // TODO: At least 5 different basic assertions

        // Reset ledger to ensure test isolation
        Ledger.reset(); 
        Ledger ledger = Ledger.getInstance("test", "desc", "seed");


        // Account acc = ledger.createAccount("alice");
        // assertNotNull(acc, "Account should not be null"); //should be true: acc is not null
        // assertEquals("alice", acc.getAddress(), "Account address should be equal to 'alice'"); //should be true: address is "alice"
        // System.out.println("Account Address: " + acc.getAddress() + " which should be equal to 'alice'");
        // assertEquals(0, acc.getBalance(), "Account balance should be equal to 0"); //should be true: initial balance is 0

        // // Test duplicate account creation throws exception (maybe don't use in real test?)
        // LedgerException ex = assertThrows(LedgerException.class, () -> ledger.createAccount("alice"));
        // assertTrue(ex.getReason().contains("Account Already Exists"), "Exception message should contain 'Account Already Exists'");
    
    // Create a block and check its properties:
        // create a block
        Block blockTest = new Block(2, "prev-hash-123"); //test parameters
        assertEquals(2, blockTest.getBlockNumber());
        assertEquals("prev-hash-123", blockTest.getPreviousHash());

        // testing the set and get hash methods
        blockTest.setHash("hash-abc");
        assertNotEquals("prev-hash-123", blockTest.getHash());

        // add accounts and verify they are retrievable
        Account kal = new Account("kal", 100);
        Account zach = new Account("zach", 250);
        blockTest.addAccount(kal.getAddress(), kal);
        blockTest.addAccount(zach.getAddress(), zach);

        // testing getAccount, getAddress, and getBalance based on Block
        Account gotKal = blockTest.getAccount("kal");
        assertNotNull(gotKal);
        assertEquals("kal", gotKal.getAddress());
        assertEquals(100, gotKal.getBalance());

        // testing if map of accounts contains entries using getAccountBalanceMap
        assertTrue(blockTest.getAccountBalanceMap().containsKey("kal"));
        assertTrue(blockTest.getAccountBalanceMap().containsKey("zach"));

        // testing previous block linking
        Block prevBlock = new Block(1, "prev-prev");
        prevBlock.setHash("prevHashVal");
        blockTest.setPreviousBlock(prevBlock);
        assertSame(prevBlock, blockTest.getPreviousBlock()); //Verifies that two references point to the same object
        //assertEquals("prevHashVal", blockTest.getPreviousBlock().getHash()); //maybe don't need both
    }

    
    /**
     * Test that the Transaction model can be created and used correctly.
     */
    @Test
    void advancedAssertionsTest() { //look at slide 45 in Week 7
        // TODO: Complete this test to demonstrate advanced assertions (assertAll, assertThrows, assertTimeout, etc.)
        // TODO: At least 5 different advanced assertions

        // testing transaction list: add a transaction directly and verify content
        Block block = new Block(3, "prev-hash-number");
        Account ava = new Account("ava", 200);
        Account ab = new Account("ab", 400);
        block.addAccount(ava.getAddress(), ava);
        block.addAccount(ab.getAddress(), ab);

        Transaction transaction = new Transaction("tx1", 10, 1, "note", ava, ab);
        block.getTransactionList().add(transaction);

        assertEquals(1, block.getTransactionList().size());
        Transaction gotTx = block.getTransactionList().get(0);
        assertEquals("tx1", gotTx.getTransactionId());
        assertEquals(10, gotTx.getAmount());
        // assert toString includes the payer/receiver addresses
        assertTrue(gotTx.toString().contains("Payer: ava"));
        assertTrue(gotTx.toString().contains("Receiver: ab"));
        
    }

    // test CommandProcessor
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
}
