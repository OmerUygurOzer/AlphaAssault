package GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Created by Omer on 1/31/2016.
 */
public class ObjectBrushHolder extends JInternalFrame implements ListSelectionListener {
    private JLabel selectedBrush = new JLabel();

    private JScrollPane listHolder = new JScrollPane();
    private JList<String> list = new JList<String>();

    private LevelHolder levelHolder;

    public ObjectBrushHolder(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
        super(title, resizable, closable, maximizable, iconifiable);
        setLayout(null);
        setSize(100,200);
        selectedBrush.setBounds(0,0,100,20);
        selectedBrush.setText("------------------");
        listHolder.setViewportView(list);
        listHolder.setBounds(0,20,100,180);

        list.addListSelectionListener(this);

        add(selectedBrush);
        add(listHolder);
    }

    public void setLevelHolder(LevelHolder levelHolder){
        this.levelHolder = levelHolder;
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
