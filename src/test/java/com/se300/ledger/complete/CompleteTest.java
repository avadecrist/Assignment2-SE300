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

public class CompleteTest {

    /* TODO: The following
     * 1. Achieve 100% Test Coverage
     * 2. Produce/Print Identical Results to Command Line DriverTest
     * 3. Produce Quality Report
     */


    Ledger testLedger;

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
