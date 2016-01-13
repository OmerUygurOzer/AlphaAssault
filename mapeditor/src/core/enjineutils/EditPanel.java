package core.enjineutils;

import core.fileIO.FileIO;
import core.objects.MapFeatureReflection;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 1/7/2016.
 */
public class EditPanel extends JPanel{
    private static final String MAP_FEATURES = "mapfeatures";
    private static final String UNITS        = "units";
    private static final String ITEMS        = "items";
    private static final String DOODADS      = "doodads";
    private static final String SPAWNERS     = "spawners";
    private static final String BASE         = "base";
    private static final String TRIGGERS     = "triggers";
    private static final String EFFECTS      = "effects";

    private static final int X = 824;
    private static final int Y = 0;

    private static final int WIDTH  = 400;
    private static final int HEIGHT = 768;

    private static final String NAME    = "Name:";
    private static final String DETAILS = "Details";

    private JTabbedPane objectPane;

    private List<File> mfFiles      = new ArrayList<File>();
    private List<File> unitFiles    = new ArrayList<File>();
    private List<File> itemFiles    = new ArrayList<File>();
    private List<File> doodadFiles  = new ArrayList<File>();
    private List<File> spawnerFiles = new ArrayList<File>();
    private List<File> baseFiles    = new ArrayList<File>();
    private List<File> triggerFiles = new ArrayList<File>();
    private List<File> effectFiles  = new ArrayList<File>();



    public EditPanel(){
        setBounds(X,Y,WIDTH,HEIGHT);
        setLayout(null);
        objectPane = new JTabbedPane();
        objectPane.setBounds(0,0,400,HEIGHT);

        JComponent mapFeature = createTab(MAP_FEATURES);
        JComponent units = createTab(UNITS);
        JComponent items = createTab(ITEMS);
        JComponent doodads = createTab(DOODADS);
        JComponent spawners = createTab(SPAWNERS);
        JComponent base = createTab(BASE);
        JComponent triggers = createTab(TRIGGERS);
        JComponent effects = createTab(EFFECTS);



        objectPane.addTab("MF*",mapFeature);
        objectPane.addTab("Units",units);
        objectPane.addTab("Item",items);
        objectPane.addTab("Doodads",doodads);
        objectPane.addTab("Spawners",spawners);
        objectPane.addTab("Base",base);
        objectPane.addTab("Triggers",triggers);
        objectPane.addTab("Effects",effects);


        add(objectPane);

    }

    private  JComponent createTab(String type){

        final JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,40,WIDTH,HEIGHT - 40);

        JLabel nameLabel = new JLabel(NAME);
        nameLabel.setBounds(0,0,40,20);

        final JLabel nameText = new JLabel("-----");
        nameText.setBounds(40,0,60,20);

        final JLabel detailsLabel = new JLabel(DETAILS);
        detailsLabel.setBounds(120,0,80,20);
        final DefaultListModel<String> detailsModel = new DefaultListModel<String>();
        JList detailsList = new JList(detailsModel);
        JScrollPane detailsPane = new JScrollPane();
        detailsPane.setBounds(170,0,220,148);
        detailsPane.setViewportView(detailsList);


        DefaultListModel<String> filesModel = new DefaultListModel<String>();
        JScrollPane objectList = new JScrollPane();
        objectList.setBounds(0,148,WIDTH-10,200);
        JList filesList = new JList(filesModel);
        objectList.setViewportView(filesList);

        final BrushSelector brushSelector = new BrushSelector();
        brushSelector.setBounds(0,348,WIDTH-10,375);




        if(type.equals(MAP_FEATURES)){
            File[] all = new File("C:\\Users\\Omer\\Desktop\\Game Projects\\AlphaAssault\\mapeditor\\mapfiles\\objects\\mapfeatures\\").listFiles();
            for(int i = 0; i < all.length ; i++){
                mfFiles.add(all[i]);
                filesModel.addElement(all[i].getName());
            }

            filesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            filesList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        nameText.setText("-----");
                        detailsModel.clear();
                        JList listPointer = (JList) e.getSource();
                        int selection = listPointer.getSelectedIndex();
                        nameText.setText(mfFiles.get(selection).getName());

                        MapFeatureReflection mfr = MapFeatureReflection.readFromByteArray(FileIO.readFromFile(mfFiles.get(selection)));
                        detailsModel.addElement("Destroyable   :" + mfr.destroyable);
                        detailsModel.addElement("BlocksMovement:" + mfr.blocksMovement);
                        detailsModel.addElement("BlocksBullets :" + mfr.blocksBullets);
                        detailsModel.addElement("BlocksDamage  :" + mfr.blocksDamage);
                        detailsModel.addElement("BlocksAerial  :" + mfr.blocksAerial);
                        detailsModel.addElement("Radius        :" + mfr.radius);
                        detailsModel.addElement("Width         :" + mfr.width);
                        detailsModel.addElement("Height        :" + mfr.height);
                        detailsModel.addElement("Image         : Available");
                        brushSelector.clearBrushes();
                        brushSelector.setTexture(mfr.variety_w,mfr.variety_h,mfr.image);

                    }
                }
            });
        }

        if(type.equals(UNITS)){

        }




        panel.add(objectList);
        panel.add(nameLabel);
        panel.add(nameText);
        panel.add(detailsLabel);
        panel.add(detailsPane);
        panel.add(brushSelector);
        return panel;
    }



}
