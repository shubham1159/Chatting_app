package Chatting_app;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.net.*;  //this is for making  class as a server

public class Server  implements ActionListener {

    JTextField text;

    JPanel a1;

   static Box vertical = Box.createVerticalBox(); //here we have made this so that the messages ban be send just below one another in a vertical order
    static  DataOutputStream dout;
    static JFrame f = new JFrame();  //if we don't want to entend frame we can call it by making objects of these
    Server(){
        f.setLayout(null);  //this is because we want to have our own layout
        f.setSize(450,700);
        f.setLocation(200,50);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.white);

        JPanel p1 = new JPanel();  //this is used to make the green colored panel like wattsapp
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,70);
        p1.setLayout(null);  //we have to make the layout null so that we can have our own wa to set the image on the panel
        f.add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);  //this is done here to change the size of the image to make it visible according to the frame
        ImageIcon i3 = new ImageIcon(i2); //here we have to again change the format of the image as the imageicon
        JLabel back = new JLabel(i3);
        back.setBounds(5,20,25,25);
        p1.add(back);

        back.addMouseListener(new MouseAdapter() {    //here we have to make the ue of the back button
            @Override
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/1.png"));
        Image i5 = i4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);  //this is done here to change the size of the image to make it visible according to the frame
        ImageIcon i6 = new ImageIcon(i5); //here we have to again change the format of the image as the imageicon
        JLabel profile = new JLabel(i6);
        profile.setBounds(40,10,50,50);
        p1.add(profile);

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);  //this is done here to change the size of the image to make it visible according to the frame
        ImageIcon i9 = new ImageIcon(i8); //here we have to again change the format of the image as the imageicon
        JLabel video = new JLabel(i9);
        video.setBounds(290,25,30,20);
        p1.add(video);

        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT);  //this is done here to change the size of the image to make it visible according to the frame
        ImageIcon i12 = new ImageIcon(i11); //here we have to again change the format of the image as the imageicon
        JLabel phone = new JLabel(i12);
        phone.setBounds(350,20,35,30);
        p1.add(phone);

        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10,30,Image.SCALE_DEFAULT);  //this is done here to change the size of the image to make it visible according to the frame
        ImageIcon i15 = new ImageIcon(i14); //here we have to again change the format of the image as the imageicon
        JLabel more = new JLabel(i15);
        more.setBounds(410,20,10,30);
        p1.add(more);

        JLabel name = new JLabel("Aman");
        name.setBounds(110,15,100,18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        p1.add(name);

        JLabel status = new JLabel("Active Now");
        status.setBounds(110,37,100,18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF",Font.BOLD,14));
        p1.add(status);

        a1 = new JPanel();
        a1.setBounds(5,75,440,570);
        f.add(a1);

        text = new JTextField();
        text.setBounds(5,655,310,40);
        text.setFont(new Font("SAN_SERIF",Font.BOLD, 16));
        f.add(text);

        JButton send = new JButton("SEND");
        send.setBounds(320,655,123,40);
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.WHITE);
        send.setFont(new Font("SAN_SERIF",Font.BOLD, 16));
        send.addActionListener(this);
       f.add(send);











        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {  //here we can write the function of buttons

        try {
            String out = text.getText();

            JLabel output = new JLabel(out);

            JPanel p2 = formatLabel(out);  //we have to call the below function thats why we have already mentioned it here


            a1.setLayout(new BorderLayout());
            JPanel right = new JPanel(new BorderLayout());  //this shows the send mssge right hand side
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15)); //space between the messaes send by the first user

            a1.add(vertical, BorderLayout.PAGE_START);  //we want our output  the end of the statring line

            dout.writeUTF(out);

            text.setText(""); //this is used to make our send box empty after sending the mssge

            f.repaint();  //this is used to reload the page means whenever we click send it send the mssge
            f.invalidate();//this is used to reload the page means whenever we click send it send the mssge
            f.validate();//this is used to reload the page means whenever we click send it send the mssge

        }catch (Exception E){
            E.printStackTrace();
        }
    }

    public static JPanel formatLabel(String out){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel output = new JLabel(out);
        output.setFont(new Font("Tahoma",Font.PLAIN,16));
        output.setBackground(new Color(37,211,102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));

        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));

        panel.add(time);

        return panel;
    }

    public static void main(String[] args) {
        new Server();

        try{
            ServerSocket skt = new ServerSocket(6001);
            while(true){
                Socket s = skt.accept();
                DataInputStream din = new DataInputStream(s.getInputStream());   //for recieving up of mssges we need this method
                 dout = new DataOutputStream(s.getOutputStream());  //for sending mssge

                while(true){
                    String msg = din.readUTF(); //for reading up of input
                    JPanel panel = formatLabel(msg);  //this step is uesd to display the mssge on the frame

                    JPanel left = new JPanel(new BorderLayout());  //this is ued to display the receiveed image on the ledt side
                    left.add(panel, BorderLayout.LINE_START);
                    vertical.add(left);
                    f.validate();
                }
            }

        }catch (Exception e){
            e.printStackTrace();

        }
    }
}
