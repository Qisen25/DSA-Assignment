import java.io.*;
//Stack unit test from DSA prac4
public class UnitTestStack
{
	public static void main(String args[])
	{
        // VARIABLE DECLARATIONS
        int iNumPassed = 0;
        int iNumTests = 0;
        DSAStack<String> adt = null;
        String sTestString;
        Object nodeValue;

//---------------------------------------------------------------------------

        System.out.println("\n\nTesting Normal Conditions - Constructor");
        System.out.println("=======================================");

        // TEST 1 : CONSTRUCTOR
        try {
            iNumTests++;
            adt = new DSAStack<String>();
            System.out.print("Testing creation of DSAStack (isEmpty()): ");
            if (adt.isEmpty() == false)
                throw new IllegalArgumentException("should be empty...");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

//---------------------------------------------------------------------------

        System.out.println("\nTest push and pop of 3 items");
        System.out.println("==========================================================");

        // TEST 3 : PUSH
        try {
            iNumTests++;
            System.out.print("Testing push(): ");
            adt.push("abc");
            adt.push("jkl");
            adt.push("xyz");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 3 : COUNT
        try {
            iNumTests++;
            System.out.print("Testing getCount(): ");
            if (adt.getCount() != 3)
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed 3 = " + adt.getCount());
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 5 : PEEK
        try {
            iNumTests++;
            System.out.print("Testing top(): ");
            sTestString = (String)adt.top();
            if (sTestString != "xyz")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed   ");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 6 : DEQUEUE
        try {
            iNumTests++;
            System.out.print("Testing pop(): ");
            sTestString = (String)adt.pop();
            if (sTestString != "xyz")
                throw new IllegalArgumentException("FAILED.");
            sTestString = (String)adt.pop();
            if (sTestString != "jkl")
                throw new IllegalArgumentException("FAILED.");
            sTestString = (String)adt.pop();
            if (sTestString != "abc")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed ");
        } catch(Exception e) { System.out.println("FAILED" + adt.top()); }

        // TEST 7 : IS EMPTY
        try {
            iNumTests++;
            System.out.print("Testing isEmpty(): ");
            if (adt.isEmpty() == false)
                throw new IllegalArgumentException("FAILED1");
            System.out.println("passed, it is empty after pop");
            iNumPassed++;
        } catch(Exception e) { System.out.println("FAILED2" + adt.top()); }


//---------------------------------------------------------------------------

        // PRINT TEST SUMMARY
        System.out.print("\nNumber PASSED: " + iNumPassed + "/" + iNumTests);
        System.out.print(" -> " + (int)(double)iNumPassed/iNumTests*100 + "%\n");
    }
//---------------------------------------------------------------------------
}
