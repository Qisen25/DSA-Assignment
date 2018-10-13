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
        String fileName, listFilt;
        
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
                    //TODO
                break;
                case 3:
                    //TODO
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
        String state, party, div;

        if(option.equals("State"))
        {
            state = stringInput("Enter a states' abbreviation or Enter ALL to display all states");
            f.listByState(state);
        }
        else if(option.equals("Party"))
        {
            party = stringInput("Enter the abbreviation for the party or Enter ALL to display all parties");
            f.listByParty(party);
        }
        else if(option.equals("Division"))
        {
            div = stringInput("Enter a division or Enter ALL to display all divisions");
            f.listByDiv(div);
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
