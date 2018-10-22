 /***************************************************************************
 *  FILE: UnitTestQueue.java
 *  AUTHOR: Kei Sum Wang - 19126089
 *  PURPOSE: Basic Test Harness For DSAQueue
 ***************************************************************************/

import java.io.*;
//Queue unit test from DSA prac 4
public class UnitTestQueue
{
	public static void main(String args[])
	{
        // VARIABLE DECLARATIONS
        int iNumPassed = 0;
        int iNumTests = 0;
        DSAQueue<String> adt = null;
        String sTestString;
        Object nodeValue;

//---------------------------------------------------------------------------

        System.out.println("\n\nTesting Normal Conditions - Constructor");
        System.out.println("=======================================");

        // TEST 1 : CONSTRUCTOR
        try {
            iNumTests++;
            adt = new DSAQueue<String>();
            System.out.print("Testing creation of DSAQueue (isEmpty()): ");
            if (adt.isEmpty() == false)
                throw new IllegalArgumentException("should be empty...");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

//---------------------------------------------------------------------------

        System.out.println("\nTest enqueue and dequeue");
        System.out.println("==========================================================");

        // TEST 2 : ENQUEUE
        try {
            iNumTests++;
            System.out.print("Testing enqueue(): ");
            adt.enqueue("abc");
            adt.enqueue("jkl");
            adt.enqueue("xyz");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 3 : PEEK
        try {
            iNumTests++;
            System.out.print("Testing peek(): ");
            sTestString = (String)adt.peek();
            if (sTestString != "abc")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed   ");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 4 : GET COUNT
        try {
            iNumTests++;
            System.out.print("Testing getCount(): ");
            if (adt.getCount() != 3)
                throw new IllegalArgumentException("FAILED, count should be 3");
            System.out.println("passed, count = 3");
            iNumPassed++;
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 6 : IS EMPTY
        try {
            iNumTests++;
            System.out.print("Testing isEmpty(): ");
            if (adt.isEmpty() == true)
                throw new IllegalArgumentException("FAILED1");
            System.out.println("passed, should contain items");
            iNumPassed++;
        } catch(Exception e) { System.out.println("FAILED2"); }


        // TEST 7 : DEQUEUE
        try {
            iNumTests++;
            System.out.print("Testing dequeue(): ");
            sTestString = (String)adt.dequeue();
            if (sTestString != "abc")
                throw new IllegalArgumentException("FAILED.");
            sTestString = (String)adt.dequeue();
            if (sTestString != "jkl")
                throw new IllegalArgumentException("FAILED.");
            sTestString = (String)adt.dequeue();
            if (sTestString != "xyz")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 8 : IS EMPTY
        try {
            iNumTests++;
            System.out.print("Testing isEmpty(): ");
            sTestString = (String)adt.dequeue();
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

//---------------------------------------------------------------------------

        // PRINT TEST SUMMARY
        System.out.print("\nNumber PASSED: " + iNumPassed + "/" + iNumTests);
        System.out.print(" -> " + (int)(double)iNumPassed/iNumTests*100 + "%\n");
    }
//---------------------------------------------------------------------------
}
