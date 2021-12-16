package core;

import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.constants.Banks;

public class Gui {
    private final JDialog mainDialog;
    private final JComboBox<Location> treeSelector;

    private boolean started;

    public Gui() {
        mainDialog = new JDialog();
        mainDialog.setTitle("Beryboy's simple cut");
        mainDialog.setModal(true);
        mainDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainDialog.getContentPane().add(mainPanel);

        JPanel treeSelectionPanel = new JPanel();
        treeSelectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel treeSelectionLabel = new JLabel("Select tree:");
        treeSelectionPanel.add(treeSelectionLabel);

        treeSelector = new JComboBox<>();
        treeSelector.addItem(new Location("Tree", "Tree", true, new Area(3171, 3391, 3158, 3416), Banks.VARROCK_WEST));
        treeSelector.addItem(new Location("Oak", "Oak", true, new Area(3171, 3391, 3158, 3416), Banks.VARROCK_WEST));
        treeSelector.addItem(new Location("Willow", "Willow", false, new Area(3223, 3301, 3219, 3310), null));
        treeSelector.addItem(new Location("Yew", "Yew", true, new Area(3088, 3468, 3085, 3482), Banks.EDGEVILLE));
        treeSelectionPanel.add(treeSelector);

        mainPanel.add(treeSelectionPanel);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            started = true;
            close();
        });
        mainPanel.add(startButton);

        mainDialog.pack();
    }

    public boolean isStarted() {
        return started;
    }

    public Location getSelectedTree() {
        return (Location) treeSelector.getSelectedItem();
    }

    public void open() {
        mainDialog.setVisible(true);
    }

    public void close() {
        mainDialog.setVisible(false);
        mainDialog.dispose();
    }
}



