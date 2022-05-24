import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Random;

public class Game extends JFrame implements KeyListener{
    char current_key;
    Boolean is_fail = false;
    Boolean is_success = false;
    JLabel[][] tab = new JLabel[4][4];
    int[][] grids = new int[4][4];
    Random random = new Random();
    JLabel backlabel;
    Boolean is_not_moving;
    Boolean is_not_merging;
    int [][]move_grid=new int[4][4];
    int [][]merge_grid=new int[4][4];
    int [][]change_grid=new int[4][4];


    public static void main(String arg[]) {
        Game game = new Game();
    }

    public Game() {

        UI_init();
        new Thread(() -> {
            while (true) {
                freshUI();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                }}}).start();

    }


    public void UI_init() {
        //背景图
        setSize(470, 610);
        setTitle("2048");
        setLayout(null);
        backlabel = new JLabel(new ImageIcon("image/panel.png"));
        backlabel.setSize(470, 580);
        getLayeredPane().add(backlabel, Integer.valueOf(0));
        tab = new JLabel[4][4];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                tab[i][j] = new JLabel(new ImageIcon("image/" + grids[i][j] + ".png"));
                tab[i][j].setBounds(110 * j + 20, 110 * i + 130, 100, 100);
                getLayeredPane().add(tab[i][j], Integer.valueOf(100));
            }

        setVisible(true);
        addKeyListener(this);
        setFocusable(true);
    }

    public void freshUI() {

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                getLayeredPane().remove(tab[i][j]);
                tab[i][j] = new JLabel(new ImageIcon("image/" + grids[i][j] + ".png"));
                tab[i][j].setBounds(110 * j + 20, 110 * i + 130, 100, 100);
                getLayeredPane().add(tab[i][j], Integer.valueOf(100));
            }

    }

    public void Game_init() {

        int grid1_row = random.nextInt(4);
        int grid1_col = random.nextInt(4);
        int grid2_row = random.nextInt(4);
        int grid2_col = random.nextInt(4);

        while ((grid1_row == grid2_row) && (grid1_col == grid2_col)) {
            grid2_row = random.nextInt(4);
            grid2_col = random.nextInt(4);
        }

        grids[grid1_row][grid1_col] = random.nextInt(2) + 1;
        grids[grid2_row][grid2_col] = random.nextInt(2) + 1;


    }

    public void move() {
        for (int i=0;i<4;i++){
        move_grid[i]=grids[i].clone();}
        switch (current_key) {
            case 'd': {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (grids[i][j] != 0 && grids[i][j + 1] == 0) {
                            grids[i][j + 1] = grids[i][j];
                            grids[i][j] = 0;
                        }
                    }
                }
                break;
            }

            case 'w': {
                for (int i = 1; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (grids[i][j] != 0 && grids[i - 1][j] == 0) {
                            grids[i - 1][j] = grids[i][j];
                            grids[i][j] = 0;
                        }
                    }
                }
                break;
            }

            case 'a': {
                for (int i = 0; i < 4; i++) {
                    for (int j = 1; j < 4; j++) {
                        if (grids[i][j] != 0 && grids[i][j - 1] == 0) {
                            grids[i][j - 1] = grids[i][j];
                            grids[i][j] = 0;
                        }
                    }
                }
                break;

            }

            case 's': {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (grids[i][j] != 0 && grids[i + 1][j] == 0) {
                            grids[i + 1][j] = grids[i][j];
                            grids[i][j] = 0;
                        }
                    }
                }
                break;
            }
        }

        is_not_moving=Arrays.equals(move_grid[0],grids[0])&&Arrays.equals(move_grid[1],grids[1])&&Arrays.equals(move_grid[2],grids[2])&&Arrays.equals(move_grid[3],grids[3]);
        if (is_not_moving) {merge();}
        else {move();}

    }

    public void merge() {
        for (int i=0;i<4;i++){merge_grid[i]=grids[i].clone();}

        switch (current_key) {
            case 'd': {
                for (int i = 0; i < 4; i++) {
                    for (int j = 1; j < 4; j++) {
                        if (grids[i][j] !=0)
                        { if(j==3){if(grids[i][j] == grids[i][j - 1]){ grids[i][j] = grids[i][j] + 1;grids[i][j - 1] = 0;}}
                        else{if(grids[i][j] == grids[i][j - 1] && grids[i][j] != grids[i][j + 1]){grids[i][j] = grids[i][j] + 1;grids[i][j - 1] = 0;}
                        }
                        }
//
//                        if (grids[i][j] !=0 && grids[i][j] == grids[i][j - 1] && grids[i][j] != grids[i][j + 1]) {
//                            grids[i][j] = grids[i][j] + 1;
//                            grids[i][j - 1] = 0;
//                        }
                    }
                }
                break;

            }
            case 'a': {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (grids[i][j] !=0)
                        { if(j==0){if(grids[i][j] == grids[i][j + 1]){ grids[i][j] = grids[i][j] + 1;grids[i][j + 1] = 0;}}
                            else{if(grids[i][j] == grids[i][j + 1] && grids[i][j] != grids[i][j - 1]){grids[i][j] = grids[i][j] + 1;grids[i][j + 1] = 0;}
                        }
                        }
                    }
                }
                break;
            }

            case 'w': {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (grids[i][j] !=0)
                        { if(i==0){if(grids[i][j] == grids[i+1][j]){ grids[i][j] = grids[i][j] + 1;grids[i+1][j] = 0;}}
                        else{if(grids[i][j] == grids[i+1][j] && grids[i][j] != grids[i-1][j]){grids[i][j] = grids[i][j] + 1;grids[i+1][j] = 0;}
                        }
                        }
//                        if (grids[i][j] !=0 && grids[i][j] == grids[i + 1][j] && grids[i][j] != grids[i - 1][j]) {
//                            grids[i][j] = grids[i][j] + 1;
//                            grids[i + 1][j] = 0;
//                        }
                    }
                }
                break;
            }
            case 's': {
                for (int i = 1; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                            if (grids[i][j] !=0)
                            { if(i==3){if(grids[i][j] == grids[i-1][j]){ grids[i][j] = grids[i][j] + 1;grids[i-1][j] = 0;}}
                            else{if(grids[i][j] == grids[i-1][j] && grids[i][j] != grids[i+1][j]){grids[i][j] = grids[i][j] + 1;grids[i-1][j] = 0;}
                            }

//                        if (grids[i][j] !=0 && grids[i][j] == grids[i - 1][j] && grids[i][j] != grids[i + 1][j]) {
//                            grids[i][j] = grids[i][j] + 1;
//                            grids[i - 1][j] = 0;
                        }
                    }
                }
                break;
            }
        }

        is_not_merging=Arrays.equals(merge_grid[0],grids[0])&&Arrays.equals(merge_grid[1],grids[1])&&Arrays.equals(merge_grid[2],grids[2])&&Arrays.equals(merge_grid[3],grids[3]);
        Boolean move_merge=Arrays.equals(merge_grid[0],move_grid[0])&&Arrays.equals(merge_grid[1],move_grid[1])&&Arrays.equals(merge_grid[2],move_grid[2])&&Arrays.equals(merge_grid[3],move_grid[3]);
        if (is_not_merging && !move_merge) {move();}
        else if(!is_not_merging){merge();}

    }


    public void renew() {

        int grid_row = random.nextInt(4);
        int grid_col = random.nextInt(4);
        while (grids[grid_row][grid_col] != 0) {
            grid_row = random.nextInt(4);
            grid_col = random.nextInt(4);
        }
        grids[grid_row][grid_col] = random.nextInt(2) + 1;

    }

    public void end() {

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                if (grids[i][j] == 11) {
                    is_success = true;
                }
            }
        Boolean is_full = true;
        is_fail = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grids[i][j] == 0) {
                    is_full = false;
                    break;
                }
            }
        }
        if (is_full) {
            //纵着判断
            for (int i = 0; i < 3; i++) {
                {
                    for (int j = 0; j < 4; j++)
                        if (grids[i][j] == grids[i + 1][j]) {
                            is_fail = false;
                            break;
                        }
                }
            }

            for (int i = 0; i < 4; i++) {
                {
                    for (int j = 0; j < 3; j++)
                        if (grids[i][j] == grids[i][j + 1]) {
                            is_fail = false;
                            break;
                        }
                }
            }
            //横着判断

        } else {
            is_fail = false;
        }
    }





    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (Character.toLowerCase(e.getKeyChar())) {
            case 'w': {
                current_key = 'w';
                break;
            }
            case 'd': {
                current_key = 'd';
                break;
            }
            case 's': {
                current_key = 's';
                break;
            }
            case 'a': {
                current_key = 'a';
                break;
            }
            case 'r':{//重新开始
                JDialog myDialog=new JDialog(this,"重新开始");
                myDialog.setBounds(120, 120, 200, 100);
                myDialog.setVisible(true);
                grids=new int[4][4];
                is_fail = false;
                is_success = false;
                Game_init();
                break;
            }
            case 'i':{//开始
                JDialog myDialog=new JDialog(this,"开始");
                myDialog.setBounds(120, 120, 200, 100);
                myDialog.setVisible(true);
                is_fail = false;
                is_success = false;
                Game_init();
                break;
            }

        }

        if (!is_fail && !is_success) {
            for (int i = 0; i < 4; i++) {
                change_grid[i] = grids[i].clone();
            }
            move();

            if (!(is_not_moving = Arrays.equals(change_grid[0], grids[0]) && Arrays.equals(change_grid[1], grids[1]) && Arrays.equals(change_grid[2], grids[2]) && Arrays.equals(change_grid[3], grids[3]))) {
                renew();
                end();

            }
        }

        if(is_fail)
        {
            JDialog myDialog=new JDialog(this,"失败");
            myDialog.setBounds(120, 120, 200, 100);
            myDialog.setVisible(true);
        }

        if(is_success)
        {
            JDialog myDialog=new JDialog(this,"成功");
            myDialog.setBounds(120, 120, 200, 100);
            myDialog.setVisible(true);
        }


    }}






