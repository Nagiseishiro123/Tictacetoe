import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class TicTacToe implements ActionListener {
    JFrame frame;
    JPanel title_panel, button_panel;
    JLabel title_label;
    JButton[] buttons = new JButton[9];
    boolean player1_turn = true;

    TicTacToe() {
        // Tạo frame
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());

        // Tạo label
        title_label = new JLabel("Tic Tac Toe");
        title_label.setForeground(Color.white);
        title_label.setFont(new Font("Arial", Font.BOLD, 50));

        // Tạo panel cho label
        title_panel = new JPanel();
        title_panel.setLayout(new FlowLayout());
        title_panel.setBackground(new Color(50, 50, 50));
        title_panel.add(title_label);

        // Tạo panel cho button
        button_panel = new JPanel();
        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(150, 150, 150));

        // Tạo button
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            button_panel.add(buttons[i]);
        }

        // Thêm panel vào frame
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        // Hiển thị frame
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (player1_turn) {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setText("X");
                        buttons[i].setForeground(Color.blue);
                        player1_turn = false;
                    }
                } else {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setText("O");
                        buttons[i].setForeground(Color.red);
                        player1_turn = true;
                    }
                }
                checkForWin();
            }
        }
    }

    public void checkForWin() {
        // Kiểm tra hàng ngang
        if (buttons[0].getText().equals("X") && buttons[1].getText().equals("X") && buttons[2].getText().equals("X")) {
            xWins(0, 1, 2);
        } else if (buttons[3].getText().equals("X") && buttons[4].getText().equals("X") && buttons[5].getText().equals("X")) {
            xWins(3, 4, 5);
        } else if (buttons[6].getText().equals("X") && buttons[7].getText().equals("X") && buttons[8].getText().equals("X")) {
            xWins(6, 7, 8);
        } else if (buttons[0].getText().equals("O") && buttons[1].getText().equals("O") && buttons[2].getText().equals("O")) {
            oWins(0, 1, 2);
        } else if (buttons[3].getText().equals("O") && buttons[4].getText().equals("O") && buttons[5].getText().equals("O")) {
            oWins(3, 4, 5);
        } else if (buttons[6].getText().equals("O") && buttons[7].getText().equals("O") && buttons[8].getText().equals("O")) {
            oWins(6, 7, 8);
        }
        // Kiểm tra hàng dọc
        else if (buttons[0].getText().equals("X") && buttons[3].getText().equals("X") && buttons[6].getText().equals("X")) {
            xWins(0, 3, 6);
        } else if (buttons[1].getText().equals("X") && buttons[4].getText().equals("X") && buttons[7].getText().equals("X")) {
            xWins(1, 4, 7);
        } else if (buttons[2].getText().equals("X") && buttons[5].getText().equals("X") && buttons[8].getText().equals("X")) {
            xWins(2, 5, 8);
       } else if (buttons[0].getText().equals("O") && buttons[3].getText().equals("O") && buttons[6].getText().equals("O")) {
            oWins(0, 3, 6);
        } else if (buttons[1].getText().equals("O") && buttons[4].getText().equals("O") && buttons[7].getText().equals("O")) {
            oWins(1, 4, 7);
        } else if (buttons[2].getText().equals("O") && buttons[5].getText().equals("O") && buttons[8].getText().equals("O")) {
            oWins(2, 5, 8);
        }
        // Kiểm tra hàng chéo
        else if (buttons[0].getText().equals("X") && buttons[4].getText().equals("X") && buttons[8].getText().equals("X")) {
            xWins(0, 4, 8);
        } else if (buttons[2].getText().equals("X") && buttons[4].getText().equals("X") && buttons[6].getText().equals("X")) {
            xWins(2, 4, 6);
        } else if (buttons[0].getText().equals("O") && buttons[4].getText().equals("O") && buttons[8].getText().equals("O")) {
            oWins(0, 4, 8);
        } else if (buttons[2].getText().equals("O") && buttons[4].getText().equals("O") && buttons[6].getText().equals("O")) {
            oWins(2, 4, 6);
        }
        // Kiểm tra hòa
        else {
            boolean tie = true;
            for (int i = 0; i < 9; i++) {
                if (buttons[i].getText().equals("")) {
                    tie = false;
                    break;
                }
            }
            if (tie) {
                JOptionPane.showMessageDialog(frame, "It's a tie!", "Tie Game", JOptionPane.INFORMATION_MESSAGE);
                resetBoard();
            }
        }
    }

    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        JOptionPane.showMessageDialog(frame, "X wins!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
        resetBoard();
    }

    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        JOptionPane.showMessageDialog(frame, "O wins!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
        resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(true);
            buttons[i].setText("");
            buttons[i].setBackground(new JButton().getBackground());
            player1_turn = true;
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}