package MAIN;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;

public class processor_class
{
    //main oject to access everything
    static processor_class obj = new processor_class();
    static Color dark = new Color(0xF7CCAC);
    static Color light = new Color(0xFFEDDB);
    static Border borderLight = BorderFactory.createLineBorder(light, 2);
    static Border borderDark = BorderFactory.createLineBorder(dark, 2);

    public static Connection connect() throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        String path="jdbc:sqlite:/Users/parshwapatil/ic-chip.db";
        return DriverManager.getConnection(path);
    }

    public void startWindow() throws  Exception
    {
        JFrame MainFrame = new JFrame("MainFrame");

        MainFrame.setLayout(null);

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBounds(0,0,600,100);
        p1.setBackground(light);
        p1.setBorder(borderDark);
        MainFrame.add(p1);
        JLabel l1 = new JLabel("To Insert Data of the Processor Click Here ->> ");
        l1.setBounds(25,25,325,50);
        p1.add(l1);
        JButton b1 = new JButton("INSERT");
        b1.setBounds(400,30,150,40);
        b1.addActionListener(lb1 ->
        {
            try
            {
                obj.insert();
                MainFrame.setVisible(false);
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null,"ERROR : "+e.toString().toUpperCase(),"ERROR",JOptionPane.ERROR_MESSAGE);
            }
        });
        p1.add(b1);

        JPanel p2 = new JPanel();
        p2.setLayout(null);
        p2.setBounds(0,100,600,100);
        p2.setBackground(dark);
        p2.setBorder(borderLight);
        MainFrame.add(p2);
        JLabel l2 = new JLabel("To Search and Operate the Data Click Here ->> ");
        l2.setBounds(25,25,325,50);
        p2.add(l2);
        JButton b2 = new JButton("OPERATE");
        b2.setBounds(400,30,150,40);
        b2.addActionListener(lb2 -> {
            try
            {
                obj.searchOperate();
                MainFrame.setVisible(false);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"ERROR :  "+e.toString().toUpperCase(),"ERROR",JOptionPane.ERROR_MESSAGE);
            }
        });
        p2.add(b2);

        MainFrame.setSize(600,(p1.getHeight()+p2.getHeight()+28));
        MainFrame.setVisible(true);

    }

    public void insert() throws Exception
    {
        JFrame mf = new JFrame("INSERT RECORD");
        mf.setLayout(null);
        mf.setSize(600,600);
        mf.setVisible(true);

        JPanel namePanel = new JPanel();
        namePanel.setBounds(0,0,600,100);
        namePanel.setBackground(light);
        namePanel.setBorder(borderDark);
        namePanel.setLayout(null);
        JLabel nameLabel = new JLabel("Name of the Processor : ");
        nameLabel.setBounds(25, 25, 225, 50);
        JTextField nameTextField = new JTextField();
        nameTextField.setBounds(300,30,250,40);
        namePanel.add(nameLabel);
        namePanel.add(nameTextField);

        JPanel clockSpeedPanel = new JPanel();
        clockSpeedPanel.setBounds(0,100,600,100);
        clockSpeedPanel.setBorder(borderLight);
        clockSpeedPanel.setBackground(dark);
        clockSpeedPanel.setLayout(null);
        JLabel clockSpeedLabel = new JLabel("Clock Speed of the Processor : ");
        clockSpeedLabel.setBounds(25, 25, 225, 50);
        JTextField clockSpeedTextField = new JTextField();
        clockSpeedTextField.setBounds(300,30,250,40);
        clockSpeedPanel.add(clockSpeedLabel);
        clockSpeedPanel.add(clockSpeedTextField);



        JPanel busWidthPanel = new JPanel();
        busWidthPanel.setBounds(0,200,600,100);
        busWidthPanel.setBorder(borderDark);
        busWidthPanel.setBackground(light);
        busWidthPanel.setLayout(null);
        JLabel busWidthLabel = new JLabel("Bus Width of the Processor : ");
        busWidthLabel.setBounds(25, 25, 225, 50);
        JTextField busWidthTextField = new JTextField();
        busWidthTextField.setBounds(300,30,250,40);
        busWidthPanel.add(busWidthLabel);
        busWidthPanel.add(busWidthTextField);


        JPanel MIPSPanel = new JPanel();
        MIPSPanel.setBounds(0,300,600,100);
        MIPSPanel.setBorder(borderLight);
        MIPSPanel.setBackground(dark);
        MIPSPanel.setLayout(null);
        JLabel MIPSLabel = new JLabel("Million Instructions per Second : ");
        MIPSLabel.setBounds(25, 25, 225, 50);
        JTextField MIPSTextField = new JTextField();
        MIPSTextField.setBounds(300,30,250,40);
        MIPSPanel.add(MIPSLabel);
        MIPSPanel.add(MIPSTextField);

        JPanel powerPanel = new JPanel();
        powerPanel.setBounds(0,400,600,100);
        powerPanel.setBackground(light);
        powerPanel.setBorder(borderDark);
        powerPanel.setLayout(null);
        JLabel powerLabel = new JLabel("Power of the Processor : ");
        powerLabel.setBounds(25, 25, 225, 50);
        JTextField powerTextField = new JTextField();
        powerTextField.setBounds(300,30,250,40);
        powerPanel.add(powerLabel);
        powerPanel.add(powerTextField);



        JPanel bPanel = new JPanel();
        bPanel.setBounds(0,500,600,80);
        bPanel.setBackground(dark);
        bPanel.setBorder(borderLight);
        bPanel.setLayout(null);
        JButton button = new JButton("SUBMIT");
        button.setBounds(50,20,200,40);
        button.addActionListener(l -> {
            try
            {
                Connection con = processor_class.connect();
                PreparedStatement ps = con.prepareStatement("insert into processor values(?,?,?,?,?)");
                ps.setString(1, nameTextField.getText());
                ps.setString(2, clockSpeedTextField.getText());
                ps.setString(3, busWidthTextField.getText());
                ps.setString(4, MIPSTextField.getText());
                ps.setString(5, powerTextField.getText());
                ps.executeUpdate();

                nameTextField.setText(null);
                clockSpeedTextField.setText(null);
                busWidthTextField.setText(null);
                MIPSTextField.setText(null);
                powerTextField.setText(null);
            }
            catch (Exception e)
            {
                System.err.println(e.toString());
                JOptionPane.showMessageDialog(null, e.toString().toUpperCase(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
        bPanel.add(button);
        JButton back = new JButton("BACK");
        back.setBounds(350,20,200,40);
        back.addActionListener(bb->{
            try
            {
                obj.startWindow();
                mf.setVisible(false);
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null,"ERROR : "+e.getMessage().toUpperCase(),"ERROR",JOptionPane.ERROR_MESSAGE);
            }
        });
        bPanel.add(back);


        mf.add(namePanel);
        mf.add(clockSpeedPanel);
        mf.add(busWidthPanel);
        mf.add(MIPSPanel);
        mf.add(powerPanel);
        mf.add(bPanel);
        mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void searchOperate() throws Exception
    {
        JFrame mf = new JFrame("SEARCH-DISPLAY-DELETE RECORD");
        mf.setLayout(null);
        mf.setSize(600,228);
        mf.setVisible(true);

        //search panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(null);
        searchPanel.setBounds(0,0,600,100);
        searchPanel.setBackground(light);
        searchPanel.setBorder(borderDark);
        JLabel searchLabel = new JLabel("Name of the Processor to be searched : ");
        searchLabel.setBounds(25,25,250,50);
        JTextField searchTextField = new JTextField();
        searchTextField.setBounds(300,35,250,30);
        searchPanel.add(searchTextField);
        searchPanel.add(searchLabel);
        mf.add(searchPanel);


        //operation / button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(dark);
        buttonPanel.setBorder(borderLight);
        buttonPanel.setBounds(0,100,600,100);
        JButton button = new JButton("DISPLAY");
        button.setBounds(75, 30, 100, 40);
        buttonPanel.add(button);
        button.addActionListener(l ->{
            try
            {
                Connection con = processor_class.connect();
                PreparedStatement ps = con.prepareStatement("select * from processor where name=?");
                //the number to be searched is present in the textfield get it from there, and set the value
                ps.setString(1,searchTextField.getText());

                ResultSet rs = ps.executeQuery();

                processor_class.displaySearch(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));

                searchTextField.setText(null);
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, "Could not perform the given task : data not found or db is locked \n".toUpperCase()+e.toString().toUpperCase(), "ERROR", JOptionPane.WARNING_MESSAGE);
                System.err.println("ERROR : "+e.getMessage());
            }

        });

        JButton button2 = new JButton("DELETE");
        button2.setBounds(250, 30, 100, 40);
        buttonPanel.add(button2);
        button2.addActionListener(l -> {
            try
            {
                int confirm=JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record!!", "Confirmation Window", JOptionPane.YES_NO_OPTION);
                //if yes it returns '0' and if no returns '1'
                Connection con = processor_class.connect();
                PreparedStatement ps = con.prepareStatement("delete from processor where name=?");
                //the number to be searched is present in the textField get it from there, and set the value
                ps.setString(1,searchTextField.getText());
                //executeQuery always returns a resultSet and while insert update delete use only  execute() / executeUpdate
                ps.executeUpdate();

                if(confirm==0)
                {
                    JOptionPane.showMessageDialog(null, "THE RECORD IS NOW DELETED FROM THE TABLE!!!", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(confirm==1)
                {
                    JOptionPane.showMessageDialog(null, "THE RECORD IS NOT DELETED!!!", "Message", JOptionPane.INFORMATION_MESSAGE);
                }

                searchTextField.setText(null);
            }
            catch (Exception e)
            {
                System.err.println(e.toString().toUpperCase());
            }
        });

        JButton back = new JButton("BACK");
        back.setBounds(425, 30, 100, 40);
        buttonPanel.add(back);
        back.addActionListener(bb ->{
            try {
                obj.startWindow();
                mf.setVisible(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"ERROR : "+e.toString().toUpperCase(),"ERROR",JOptionPane.ERROR_MESSAGE);
            }

        });


        mf.add(buttonPanel);
        mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void displaySearch(String s1,String s2,String s3,String s4,String s5)
    {
        JFrame mf= new JFrame("Displaying Data");
        mf.setLayout(null);
        mf.setVisible(true);
        mf.setSize(600,430);

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBounds(0,0,600,80);
        p1.setBackground(light);
        p1.setBorder(borderDark);
        JLabel l1 = new JLabel("NAME OF PROCESSOR : ");
        l1.setBounds(50,20,250,40);
        p1.add(l1);
        JLabel l11 = new JLabel(s1);
        l11.setBounds(300, 20, 250, 40);
        p1.add(l11);
        mf.add(p1);

        JPanel p2 = new JPanel();
        p2.setLayout(null);
        p2.setBounds(0,80,600,80);
        p2.setBackground(dark);
        p2.setBorder(borderLight);
        JLabel l2 = new JLabel("CLOCK-SPEED OF PROCESSOR : ");
        l2.setBounds(50,20,250,40);
        p2.add(l2);
        JLabel l22 = new JLabel(s2);
        l22.setBounds(300, 20, 250, 40);
        p2.add(l22);
        mf.add(p2);

        JPanel p3 = new JPanel();
        p3.setLayout(null);
        p3.setBounds(0,160,600,80);
        p3.setBackground(light);
        p3.setBorder(borderDark);
        JLabel l3 = new JLabel("BUS-WIDTH OF PROCESSOR : ");
        l3.setBounds(50,20,250,40);
        p3.add(l3);
        JLabel l33 = new JLabel(s3);
        l33.setBounds(300, 20, 250, 40);
        p3.add(l33);
        mf.add(p3);

        JPanel p4 = new JPanel();
        p4.setLayout(null);
        p4.setBounds(0,240,600,80);
        p4.setBackground(dark);
        p4.setBorder(borderLight);
        JLabel l4 = new JLabel("MIPS OF PROCESSOR : ");
        l4.setBounds(50,20,250,40);
        p4.add(l4);
        JLabel l44 = new JLabel(s4);
        l44.setBounds(300, 20, 250, 40);
        p4.add(l44);
        mf.add(p4);

        JPanel p5 = new JPanel();
        p5.setLayout(null);
        p5.setBounds(0,320,600,80);
        p5.setBackground(light);
        p5.setBorder(borderDark);
        JLabel l5 = new JLabel("POWER OF PROCESSOR : ");
        l5.setBounds(50,20,250,40);
        p5.add(l5);
        JLabel l55 = new JLabel(s5);
        l55.setBounds(300, 20, 250, 40);
        p5.add(l55);
        mf.add(p5);
    }


    public static void main(String[] args)
    {
        try
        {
            obj.startWindow();
        } catch (Exception ex)
        {
            System.err.println(ex.toString());
        }
    }

}
