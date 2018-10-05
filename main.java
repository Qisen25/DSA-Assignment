//Main class
//by Kei Wang
public class main
{
    public static void main(String []args)
    {
        try
        {
            UserInterface i = new UserInterface();
            i.run();
        }
        catch(IllegalArgumentException ex)
        {
            System.out.println("Incorrect parameter in file: " + 
                                ex.getMessage());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
