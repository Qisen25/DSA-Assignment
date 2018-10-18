//class dealing with user interaction
//by Kei Wang
import java.util.*;
public class UserInterface
{
    private String menu;
    private FileIO f;

    public UserInterface()
    {
        this.menu = "(1)    -List Nominees\n" +
                    "(2)    -Nominee Search\n" +
                    "(3)    -List by Margin\n" +
                    "(4)    -Itinerary by Margin\n" +
                    "(0)    -Quit\n" +
                    "Choice:>";
        this.f =  new FileIO();
    }
  
    //present functions and allows user to use functionality 
    public void run()
    {
        int choose, op;
        String fileName, listFilt, partyAbrev, sname, partyOrState;
        
        //search and read require files in current directory
        readFiles();
         
        do
        {
            //System.out.print(menu);
            choose = intPut(menu);
            switch(choose)
            {
                //exit
                case 0:
                    System.out.println("Bye");
                break;
                case 1:
                    listFilt = stringInput("list based on State, Party or Division?");
                    listNominees(listFilt);          
                break;
                case 2:
                    sname = stringInput("Enter first few letters or complete surname of nominee");
                    partyOrState = stringInput("Filter by party or state?");
                    searchNominees(sname, partyOrState);          
                    
                break;
                case 3:
                    partyAbrev = stringInput("Enter party abbreviation");
                    listMarginByParty(partyAbrev);
                break;
                case 4:
                    //TODO
                break;
                default:
                    System.out.println("invalid choice");
                break;
            }

        }while(choose != 0);

    }

    //integer input
    private int intPut(String prompt)
    {
        int input;
        Scanner sc = new Scanner(System.in);

        System.out.print(prompt);
        input = sc.nextInt();

        return input;
    }

    //double input
    private double realInput(String prompt)
    {
        double input;
        Scanner sc = new Scanner(System.in);

        System.out.print(prompt);
        input = sc.nextDouble();

        return input;
    }

    //string input
    private String stringInput(String prompt)
    {
        String input;
        Scanner sc = new Scanner(System.in);

        System.out.println(prompt);
        input = sc.nextLine();

        return input;
    }

    public void listNominees(String option)
    {
        String state, party, div, all;

        all = "ALL";
        if(option.equalsIgnoreCase("State"))
        {
            state = stringInput("Enter a states' abbreviation or Enter ALL to display all states");
            f.listByState(state);
        }
        else if(option.equalsIgnoreCase("Party"))
        {
            party = stringInput("Enter the abbreviation for the party or Enter ALL to display all parties");
            f.listByParty(party);
        }
        else if(option.equalsIgnoreCase("Division"))
        {
            div = stringInput("Enter name of a division or Enter ALL to display all divisions");
            f.listByDiv(div);
        }
        else
        {
            System.out.println("Invalid choice");
        }
    }

    public void searchNominees(String sname, String option)
    {
        String state, party;

        if(option.equalsIgnoreCase("State"))
        {
            state = stringInput("Enter a states' abbreviation or Enter ALL to display all states");
            f.searchNomBySname(sname, option, state);
        }
        else if(option.equalsIgnoreCase("Party"))
        {
            party = stringInput("Enter the abbreviation for the party or Enter ALL to display all parties");
            f.searchNomBySname(sname, option, party);
        }
        else
        {
            System.out.println("Invalid choice");
        }
    }

    public void listMarginByParty(String party)
    { 
        double threshold, defaultThreshold;
        int custom;
        
        custom = intPut("Enter 1 to set custom threshold or 0 to use default threshold:");

        defaultThreshold = 6;
        if(custom == 1)
        {
            threshold = realInput("Enter threshold:");
            f.listMargin(party, threshold);
        }
        else if(custom == 0)
        {
            f.listMargin(party, defaultThreshold);
        }
        else
        {
            System.out.println("Invalid choice");
        }
    }

    private void readFiles()
    {
        boolean hasFiles;

        hasFiles = f.readDirFiles();

        if(hasFiles)
        {
            System.out.println("required files found and sucessfully read");
        }
        else
        {
            System.out.println("required files not found");
        }
    }
}
