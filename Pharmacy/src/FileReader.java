import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class FileReader extends JFrame {
    private JLabel label;
    private final List<File> files = new ArrayList<>();
    private final List<File> goodFiles = new ArrayList<>();

    public FileReader() {
        setTitle("Multi File Chooser");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(400, 200));
        panel.setTransferHandler(new TransferHandler() {
            @Serial
            private static final long serialVersionUID = 1L;

            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {
                return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
            }

            @Override
            public boolean importData(TransferHandler.TransferSupport support) {
                if (!canImport(support)) {
                    return false;
                }

                Transferable transferable = support.getTransferable();
                try {
                    List<File> droppedFiles = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                    files.clear();
                    files.addAll(droppedFiles);
                    StringBuilder fileListText = new StringBuilder("<html>არჩეული ფაილები:<br>");

                    for (File file : files) {
                        switch (file.getPath().substring(file.getPath().indexOf(".") + 1).toLowerCase()) {
                            case "pdf", "xls", "docx", "doc", "csv" -> goodFiles.add(file);
                            default ->
                                    System.out.println("wrong file" + file.getPath().substring(file.getPath().indexOf(".") + 1));
                        }
                    }
                    for (File goodFile : goodFiles) {
                        fileListText.append(goodFile.getName()).append("<br>");
                    }

                    label.setText(fileListText.toString());
                    panel.add(label);

                    System.out.println(goodFiles.size());
                    return true;
                } catch (UnsupportedFlavorException | IOException e) {
                    return false;
                }
            }
        });

        label = new JLabel("აქ ჩააგდეთ ფაილები");
        panel.add(label);
        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton submitButton = new JButton("გაშვება");
        submitButton.addActionListener(e -> {
            if (files.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "აქ ჩააგდეთ ფაილები", "No File Selected",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println("Selected files:");
                for (File file : files) {
                    System.out.println(file.getAbsolutePath());
                }
                files.clear();
                label.setText("აქ ჩააგდეთ ფაილები");

            }
        });
        buttonPanel.add(submitButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            FileReader chooser = new FileReader();
            chooser.setVisible(true);
        });
    }
}
