import javax.swing.*;

/**
 * Created by Omer on 12/26/2015.
 */
public class MapEditor {

    private JPanel MapEditor;

    public static void main(String[] args) {
        JFrame frame = new JFrame("MapEditor");
        frame.setContentPane(new MapEditor().MapEditor);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
