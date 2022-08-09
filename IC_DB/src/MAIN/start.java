package MAIN;

import java.util.Scanner;

public class start
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int choice;
        do
        {
            System.out.print("Menu : 1)IC-DB 2)Processor-DB\nEnter the 'choice' : ");
            choice=sc.nextInt();

            switch (choice)
            {
                case 1 :
                    ic_class.main(null);
                    break;
                case 2 :
                    processor_class.main(null);
                    break;
                default:
                    System.out.println("INVALID CHOICE , PLEASE TRY AGAIN");
                case 0 :
                    System.out.println("QUITTING THE PROJECT");
            }

        }while(choice!=0);

    }
}
