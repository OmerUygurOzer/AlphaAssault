package GUI;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Omer on 1/17/2016.
 */
public class BaseEditor extends JPanel implements ActionListener{
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;


    private JButton addFrame = new JButton("Add Frame");
    private JButton addCrossing = new JButton("Add");
    private JButton removeCrossing = new JButton("Remove");
    private List<String> crossingsAll = new ArrayList<String>();
    private DefaultListModel<String> crossingListModel = new DefaultListModel<String>();
    private JList<String> crossingsAllUI = new JList<String>(crossingListModel);

    private JComboBox<String> tileType = new JComboBox<String>();
    private JComboBox<String> crossings = new JComboBox<String>();



    private int line = 0;
    private int lineSize = 20;

    public BaseEditor(){

        setLayout(null);
        setBounds(800,0,WIDTH,HEIGHT);


        JLabel tileTypeLabel = new JLabel("Tile Type:");
        tileTypeLabel.setBounds((WIDTH/2) - 60,lineSize*line,120,lineSize);
        add(tileTypeLabel);
        tileType.setBounds((WIDTH/2) + 60 ,lineSize*line,120,lineSize);line++;
        tileType.addItem("2D Tile");
        tileType.addItem("Isometric Tile");
        add(tileType);

        JLabel crossingsLabel = new JLabel("Crossings:");
        crossingsLabel.setBounds((WIDTH/2) - 60,lineSize*line,120,lineSize);
        add(crossingsLabel);
        crossings.setBounds((WIDTH/2) + 60 ,lineSize*line,120,lineSize);
        crossings.addItem("N");
        crossings.addItem("NE");
        crossings.addItem("E");
        crossings.addItem("SE");
        crossings.addItem("S");
        crossings.addItem("SW");
        crossings.addItem("W");
        crossings.addItem("NW");

        addCrossing.setBounds((WIDTH/2) + 180 ,lineSize*line,120,lineSize); addCrossing.addActionListener(this);
        removeCrossing.setBounds((WIDTH/2) + 300 ,lineSize*line,120,lineSize);removeCrossing.addActionListener(this);

        JScrollPane listHolder = new JScrollPane();
        listHolder.setBounds((WIDTH/2) + 420 ,lineSize*line,40,lineSize*crossings.getItemCount());
        listHolder.setViewportView(crossingsAllUI);

        add(crossings);
        add(addCrossing);
        add(removeCrossing);
        add(listHolder);
        line++;

        addFrame.setBounds((WIDTH/2) - 60,lineSize * line,120,lineSize); line++;
        addFrame.addActionListener(this);
        add(addFrame);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(addFrame)){
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Image Files","png", "jpg");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(this.getParent());
            if(returnVal == JFileChooser.APPROVE_OPTION) {
              //DO STUFF
            }
            return;
        }

        if(e.getSource().equals(addCrossing)){
            if(crossingsAll.contains((String)crossings.getSelectedItem())){
                return;
            }
            crossingsAll.add((String)crossings.getSelectedItem());
            crossingListModel.addElement((String)crossings.getSelectedItem());
            System.out.println(crossingsAll);
            return;
        }

        if(e.getSource().equals(removeCrossing)){
            if(!crossingsAll.contains(crossings.getSelectedItem())){
                return;
            }
            crossingsAll.remove(crossings.getSelectedItem());
            crossingListModel.removeElement(crossings.getSelectedItem());
            System.out.println(crossingsAll);
            return;
        }

    }
}
