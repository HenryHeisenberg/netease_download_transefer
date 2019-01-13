import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class Window {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Window window = new Window();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Window() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("网易云缓存转换");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        textField = new JTextField();
        textField.setBounds(112, 21, 253, 29);
        panel.add(textField);
        textField.setColumns(10);

        JLabel label = new JLabel("缓存路径：");
        label.setFont(new Font("SimSun", Font.PLAIN, 16));
        label.setBounds(29, 23, 104, 18);
        panel.add(label);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(112, 60, 253, 29);
        panel.add(textField_2);

        JLabel label_2 = new JLabel("目标路径：");
        label_2.setFont(new Font("SimSun", Font.PLAIN, 16));
        label_2.setBounds(29, 61, 99, 24);
        panel.add(label_2);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(112, 100, 253, 29);
        panel.add(textField_3);

        JLabel label_3 = new JLabel("目标音乐名：");
        label_3.setFont(new Font("SimSun", Font.PLAIN, 16));
        label_3.setBounds(16, 101, 99, 24);
        panel.add(label_3);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(111, 139, 253, 29);
        panel.add(textField_4);

        JLabel label_4 = new JLabel("作者名：");
        label_4.setFont(new Font("SimSun", Font.PLAIN, 16));
        label_4.setBounds(43, 140, 99, 24);
        panel.add(label_4);

        JButton button = new JButton("转换");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cache = textField.getText() + "/";
                // TODO 加一个判断是否为缓存文件
                FindFile.delAllFile(textField.getText());
                String cacheMusicName = FindFile.findFileBySub(textField.getText());
                String localName = textField_2.getText() + "/";
                String musicName = cacheMusicName.replace(".uc", ".mp3");
                Change.transfer(cache, cacheMusicName, localName, musicName);
                Music music = AudioParser.parseMusic(localName + musicName);
                if (textField_3.getText().equals("")) {
                    if (music.getTitle() == null) {
                        JOptionPane.showMessageDialog(frame, "无法得到此音乐信息，请输入音乐名字", "错误提示", JOptionPane.WARNING_MESSAGE);
                        new File(localName + cacheMusicName.replace(".uc", ".mp3")).delete();
                        return;
                    }
                }
                if (music.getAuthod() != null) {
                    musicName = music.getTitle() + "—" + music.getAuthod() + ".mp3";
                } else {
                    musicName = music.getTitle() + ".mp3";
                }
                new File(localName + cacheMusicName.replace(".uc", ".mp3")).delete();
                if (music.getTitle() == null || !textField_3.getText().equals("")) {
                    if (!textField_4.getText().equals("")) {
                        musicName = textField_3.getText() + "—" + textField_4.getText() + ".mp3";
                    } else {
                        musicName = textField_3.getText() + ".mp3";
                    }
                }
                musicName = musicName.replace("/", ";");
                Change.transfer(cache, cacheMusicName, localName, musicName);
                textField_3.setText("");
                textField_4.setText("");
            }
        });
        button.setBounds(176, 197, 97, 23);
        panel.add(button);
        
        JLabel lblBy = new JLabel("By Henry");
        lblBy.setBounds(385, 246, 51, 15);
        panel.add(lblBy);
    }
}
