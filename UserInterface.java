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
        int choose, op, partyOrState, listFilt;
        String fileName, partyAbrev, sname;
        
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
                    listFilt = intPut("==list based on==\n(1) -State\n(2) -Party\n(3) -Division\nchoice:>");
                    listNominees(listFilt);         
                break;
                case 2:
                    sname = stringInput("Enter first few letters or complete surname of nominee: ");
                    partyOrState = intPut("=Filter by=\n(1)state\n(2)party\nchoice:>");
                    searchNominees(sname, partyOrState);          
                break;
                case 3:
                    partyAbrev = stringInput("Enter party abbreviation: ");
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

        System.out.print(prompt);
        input = sc.nextLine();

        return input;
    }

    public void listNominees(int option)
    {
        String state, party, div, all;
        boolean readExecuted = false;

        all = "ALL";
        if(option == 1)
        {
            state = stringInput("Enter a states' abbreviation or Enter ALL to display all states: ");
            f.listByState(state);
            readExecuted = true;
        }
        else if(option == 2)
        {
            party = stringInput("Enter the abbreviation for the party or Enter ALL to display all parties: ");
            f.listByParty(party);
            readExecuted = true;
        }
        else if(option == 3)
        {
            div = stringInput("Enter name of a division or Enter ALL to display all divisions: ");
            f.listByDiv(div);
            readExecuted = true;
        }
        else
        {
            System.out.println("Invalid choice");
        }

        if(readExecuted)
        {
            writeFile("listNominees.csv"); 
        }
    }

    public void searchNominees(String sname, int option)
    {
        String state, party;
        boolean readExecuted = false;

        if(option == 1)
        {
            state = stringInput("Enter a states' abbreviation or Enter ALL to search all states: ");
            f.searchNomBySname(sname, "state", state);
            readExecuted = true;
        }
        else if(option == 2)
        {
            party = stringInput("Enter the abbreviation for the party or Enter ALL to search all parties: ");
            f.searchNomBySname(sname, "party", party);
            readExecuted = true;
        }
        else
        {
            System.out.println("Invalid choice");
        }

        if(readExecuted)
        {
            writeFile("searchNominees.csv");                     
        }
    }

    public void listMarginByParty(String party)
    { 
        double threshold, defaultThreshold;
        int custom;
        boolean noDisplay = true;
        
        custom = intPut("Enter 1 to set custom threshold\nEnter 0 to use default threshold\nchoice:>");

        defaultThreshold = 6;
        if(custom == 1)
        {
            threshold = realInput("Enter threshold:");
            noDisplay = f.listMargin(party, threshold);
            if(noDisplay)
            {
                System.out.println("No margins to display for party: " + party + " with in threshold range..");
            }
        }
        else if(custom == 0)
        {
            noDisplay = f.listMargin(party, defaultThreshold);
            if(noDisplay)
            {
                System.out.println("No margins to display for the " + party + " party with in threshold range...");
            }
        }
        else
        {
            System.out.println("Invalid choice");
        }

        if(!noDisplay)
        {
            writeFile("partyMargins.csv");                     
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

    private void writeFile(String defaultfile)
    {
        String choice, filename;

        choice = stringInput("Save to file? y or n: ");
        if(choice.equals("y"))
        {
            filename = stringInput("Enter file name or hit enter to save to default file(" + defaultfile + ")\nfilename:> " );
            if(filename.trim().isEmpty() || filename == null)
            {
                filename = defaultfile;
            }

            f.writeToFile(filename);
        }
    }

}
