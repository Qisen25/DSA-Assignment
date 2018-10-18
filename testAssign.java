public class testAssign
{
    public static void main(String [] args)
    {
        Nominee nom = new Nominee();
        FileIO f = new FileIO();

        f.readDirFiles();
        //f.printAll();
        //try
       // {
            f.sortList("partysname");
            System.out.println("==List by State==");
            f.listByState("NSW");
        //}
        //catch(Exception e)
        //{
         //   System.out.println(e.getMessage());
        //}
        //System.out.println("==List by division==");
        //f.listByDiv("Brand");
        //System.out.println("==List by party==");
        //f.listByParty("ALP");
        //System.out.println("==List margins==");
        //f.listMargin("ALP");
        

    }
}
