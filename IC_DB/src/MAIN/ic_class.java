package MAIN;

import javax.swing.*;
import java.sql.*;
import java.util.*;

public class ic_class
{
    Scanner sc = new Scanner(System.in);
    public static Connection connect() throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        String path = "jdbc:sqlite:/Users/parshwapatil/ic-chip.db";

        //Connection con = DriverManager.getConnection(path); && return con;
        return DriverManager.getConnection(path);
    }
    public void insert() throws Exception
    {
        //secure the connection
        Connection connectionObject = ic_class.connect();
        //prepare our statement, a DML-Data Manipulation Language Query
        PreparedStatement ps=connectionObject.prepareStatement("insert into ic values(?,?,?,?,?,?,?,?)");

        //to ask and take in the data to be inserted
        System.out.print("Enter the 'Etched Pin Number' on the IC-CHIP : ");
        int EtchedPinNo = sc.nextInt();
        System.out.print("Enter the 'NAME' of the IC-CHIP : ");
        String Name = sc.next();
        System.out.print("Enter the 'Number of Pins' in the IC-CHIP : ");
        int NumPin = sc.nextInt();
        System.out.print("Enter the 'Input Pin Numbers' of the IC-CHIP : ");
        String InputPin = sc.next();
        System.out.print("Enter the 'Output Pin Numbers' of the IC-CHIP : ");
        String OutputPin = sc.next();
        System.out.print("Enter the 'Ground Pin' of the IC-CHIP : ");
        int Ground = sc.nextInt();
        System.out.print("Enter the 'Vcc Pin' of the IC-CHIP : ");
        int VCC = sc.nextInt();
        System.out.print("Enter the 'Number of Gates' of the IC-CHIP : ");
        int NoGates = sc.nextInt();

        //to set the values of the placeholder's
        ps.setInt(1,EtchedPinNo);
        ps.setString(2,Name);
        ps.setInt(3,NumPin);
        ps.setString(4,InputPin);
        ps.setString(5,OutputPin);
        ps.setInt(6,Ground);
        ps.setInt(7,VCC);
        ps.setInt(8,NoGates);

        //to execute this prepared statement we use .executeMethod()
        ps.executeUpdate();

        //closing the resources
        ps.close();
        connectionObject.close();
    }
    public void delete() throws Exception
    {
        //establish the connection and secure it
        Connection connectionObject = ic_class.connect();

        //prepare the statement
        PreparedStatement ps = connectionObject.prepareStatement("delete from ic where pinNo=?");

        //taking input to delete
        System.out.print("Enter the PinNumber of he IC-Chip to be deleted : ");
        int delNum = sc.nextInt();
        //setting the placeholder's value and type
        ps.setInt(1,delNum);

        //execute the statement
        ps.executeUpdate();

        //closing resources
        ps.close();
        connectionObject.close();
    }
    public void update() throws Exception
    {
        //establish and secure the connection
        Connection connectionObject = ic_class.connect();
        ic_class obj = new ic_class();
            //preparing statement
            PreparedStatement ps = connectionObject.prepareStatement("update ic set pinNo=?,name=?,numOfPins=?,inputPins=?,outpuPins=?,ground=?,vcc=?,numGates=? where pinNo=?");
            //to ask and take in the data to be inserted
            System.out.print("Enter the PinNumber of IC-Chip to be updated : ");
            int editPin=sc.nextInt();
            if(obj.searchFlag(editPin))
           {
               System.out.print("Enter  the new 'Etched Pin Number' on the IC-CHIP : ");
               int EtchedPinNo = sc.nextInt();
               System.out.print("Enter  the new 'NAME' of the IC-CHIP : ");
               String Name = sc.next();
               System.out.print("Enter  the new 'Number of Pins' in the IC-CHIP : ");
               int NumPin = sc.nextInt();
               System.out.print("Enter  the new 'Input Pin Numbers' of the IC-CHIP : ");
               String InputPin = sc.next();
               System.out.print("Enter  the new 'Output Pin Numbers' of the IC-CHIP : ");
               String OutputPin = sc.next();
               System.out.print("Enter  the new 'Ground Pin' of the IC-CHIP : ");
               int Ground = sc.nextInt();
               System.out.print("Enter  the new 'Vcc Pin' of the IC-CHIP : ");
               int VCC = sc.nextInt();
               System.out.print("Enter  the new 'Number of Gates' of the IC-CHIP : ");
               int NoGates = sc.nextInt();
               //to set the values of the placeholder's
               ps.setInt(1,EtchedPinNo);
               ps.setString(2,Name);
               ps.setInt(3,NumPin);
               ps.setString(4,InputPin);
               ps.setString(5,OutputPin);
               ps.setInt(6,Ground);
               ps.setInt(7,VCC);
               ps.setInt(8,NoGates);
               ps.setInt(9,editPin);
           }
            //execute the update
            ps.executeUpdate();

            //closing the resources
            ps.close();
        connectionObject.close();
    }

    public boolean searchFlag(int num)
    {
        boolean flag=false;
        try
        {
            Connection connectionObject;
            //calling connection object to create the connection between the "java and database program" through a "Driver".
            connectionObject=ic_class.connect();

            //using this connection to create statement / prepare statement
            PreparedStatement ps = connectionObject.prepareStatement("select * from ic where pinNo=?");
            ps.setInt(1,num);

            //execute the ps query
            ResultSet rs = ps.executeQuery();
            if((rs.getInt("pinNo"))==num)
            {
                ps.close();
                connectionObject.close();
                flag= true;
            }

        }
        catch (Exception e)
        {
            System.out.println("DATA NOT IN THE DATABASE");
        }
        return flag;
    }
    public void search() throws Exception
    {
        Connection connectionObject;
        //calling connection object to create the connection between the "java and database program" through a "Driver".
        connectionObject=ic_class.connect();

        //using this connection to create statement / prepare statement
        PreparedStatement ps = connectionObject.prepareStatement("select * from ic where pinNo=?");

        //setting placeholder's type, value
        System.out.print("Enter the PinNumber of the IC to be searched : ");
        int num=sc.nextInt();
        ps.setInt(1,num);

        //execute the ps query
        ResultSet rs = ps.executeQuery();


        //get the values in variables and print or just print
        System.out.println("Pin Number : "+rs.getInt("pinNo"));
        System.out.println("Name of the IC Chip : "+rs.getString("name"));
        System.out.println("Total Number of Pins : "+rs.getInt("numOfPins"));
        System.out.println("Input Pins : "+rs.getString("inputPins"));
        System.out.println("Output Pins : "+rs.getString("outpuPins"));
        System.out.println("The Ground and VCC are connected to Pin \""+rs.getInt("ground")+"\" & \""+rs.getInt("vcc")+"\" respectively");
        System.out.println("Total Number of Gates {can be used} are : "+rs.getInt("numGates"));

        displayImage(rs.getInt("pinNo"));

        ps.close();
        connectionObject.close();
    }

    public void displayImage(int pinNo)
    {
        JFrame imgFrame = new JFrame(("PIN DIAGRAM - "+pinNo).toUpperCase());

        JPanel imgPanel = new JPanel();
        JLabel imgLabel = new JLabel();
        ImageIcon img = new ImageIcon("/Volumes/Parshwa's SSD-T5/IC-DB/IC_DB/images/"+pinNo+".png");
        imgLabel.setIcon(img);
        imgPanel.add(imgLabel);
        imgFrame.add(imgPanel);
        imgFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        double imgIconWidth = img.getIconWidth();
        double imgIconHeight = img.getIconHeight();
        if(imgIconHeight==-1 || imgIconWidth==-1)
        {
            JOptionPane.showMessageDialog(null,"ERROR finding the Image : Invalid name or not downloaded.".toUpperCase(),"ERROR!!",JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            imgFrame.setSize(img.getIconWidth()+20, img.getIconHeight()+40);
            imgFrame.setVisible(true);
        }
        imgFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        imgFrame.dispose();
    }

    public static void main(String []args)
    {
        Scanner mainSC = new Scanner(System.in);
        ic_class mainObj =  new ic_class();

        int choice ;
        do {
            System.out.println("\nCHOOSE THE OPERATION TO BE PERFORMED : ");
            System.out.println("1.INSERT INTO DATA-BASE\n2.SEARCH FROM DATA-BASE\n3.DELETE FRPM DATA-BASE\n4.UPDATE DATA-BASE\n0.QUIT THE PROGRAM");
            System.out.print("Enter Your Choice : ");
            choice = mainSC.nextInt();
            switch (choice)
            {
                case 1:
                    try
                    {
                        mainObj.insert();
                    }
                    catch (Exception e) {
                        System.out.println("ERROR , during the insertion of record in database , data already present in the database.");
                    }
                break;
                case 2:
                    try
                    {
                        mainObj.search();
                    }
                    catch (Exception e) {
                        System.out.println("ERROR 404, during the searching for the record in database , data not found.");
                    }
                    break;
                case 3:
                    try
                    {
                        mainObj.delete();
                    }
                    catch (Exception e) {
                        System.out.println("ERROR , during the deletion of record in database.");
                    }
                    break;
                case 4:
                    try
                    {
                        mainObj.update();
                    }
                    catch (Exception e) {
                        System.out.println("ERROR , during the updation of record in database.");
                    }
                    break;
                case 0:
                    System.out.println("END OF THE PROGRAM");
                    break;
                default:
                    System.out.println("INVALID CHOICE PLEASE TRY AGAIN WITH VALID OPTION");
            }


        }while (choice!=0);

    }
}

