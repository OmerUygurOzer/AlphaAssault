package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Omer on 2/2/2016.
 */
public class ToolsHolder extends JInternalFrame implements ActionListener {

    private LevelHolder levelHolder;

    private JButton zoomIn = new JButton("+");
    private JLabel zoom = new JLabel("Zoom");
    private JButton zoomOut = new JButton("-");


    public ToolsHolder(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
        super(title,resizable,closable,maximizable,iconifiable);
        setLayout(null);
        setSize(240,240);

        zoomOut.setBounds(40,0,45,20);
        zoomOut.addActionListener(this);

        zoom.setBounds(95,0,60,20);

        zoomIn.setBounds(145,0,45,20);
        zoomIn.addActionListener(this);

        add(zoomOut);
        add(zoom);
        add(zoomIn);
    }


    public void setLevelHolder(LevelHolder levelHolder){
        this.levelHolder = levelHolder;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(zoomIn)){
            levelHolder.zoomIn();
        }
        if(e.getSource().equals(zoomOut)){
            levelHolder.zoomOut();
        }

    }
}
