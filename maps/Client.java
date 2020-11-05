import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Client {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("SurvivalMap");
        frame.setSize(1000,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.add(new Map(), BorderLayout.CENTER);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300,600));
        JButton upButton =new JButton("上移");
        JButton downButton=new JButton("下移");
        JButton leftButton=new JButton("左移");
        JButton rightButton=new JButton("右移");
        JButton shotButton=new JButton("射击");
        upButton.setPreferredSize(new Dimension(100,50));
        downButton.setPreferredSize(new Dimension(100,50));
        leftButton.setPreferredSize(new Dimension(100,50));
        rightButton.setPreferredSize(new Dimension(100,50));
        shotButton.setPreferredSize(new Dimension(200,50));
        upButton.addActionListener(new SimpleListener());
        downButton.addActionListener(new SimpleListener());
        leftButton.addActionListener(new SimpleListener());
        rightButton.addActionListener(new SimpleListener());
        shotButton.addActionListener(new SimpleListener());
        panel.add(upButton);
        panel.add(downButton);
        panel.add(leftButton);
        panel.add(rightButton);
        panel.add(shotButton);
        JLabel label1 = new JLabel("方向");
        JLabel label2 = new JLabel("安全");
        JLabel label3 = new JLabel("身份");
        JLabel label4 = new JLabel("当前分数");
        label1.setPreferredSize(new Dimension(200,50));
        label2.setPreferredSize(new Dimension(200,50));
        label3.setPreferredSize(new Dimension(200,50));
        label4.setPreferredSize(new Dimension(200,50));
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        frame.add(panel, BorderLayout.EAST);
        frame.setVisible(true);
    }

    private static class SimpleListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand()) {
                case "上移":
                    System.out.println("向上移动");
                    break;
                case "下移":
                    System.out.println("向下移动");
                    break;
                case "左移":
                    System.out.println("向左移动");
                    break;
                case "右移":
                    System.out.println("向右移动");
                    break;
                case "射击":
                    System.out.println("发射炮弹");
                    break;
            }
        }
    }
}
