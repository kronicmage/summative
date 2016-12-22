package zergtel.UI;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import zergtel.core.converter.Converter;
import zergtel.core.converter.Merge;
import zergtel.core.downloader.Downloader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;

/**
 * Created by Shyam on 2016-10-25.
 */
public class ComputerUI extends JFrame implements ActionListener{
    private int start = 0;
    private String url; //this will be used for webview, DO NOT KEEP IN FINAL PRODUCT
    private String options[] = {"PlaceHolder", "File", "Settings", "Lisense", "Version"};
    private String u1, u2, directory, name, format;
    private File f1, f2;
    private Dimension minSize = new Dimension(640,480);
    private JPanel logo = new JPanel();
    private JPanel filler = new JPanel();
    private JPanel commands = new JPanel();
    private JPanel download = new JPanel();
    private JPanel convert = new JPanel();
    private JPanel search = new JPanel();
    private JPanel convertProgress[] = new JPanel[0];
    private JPanel openingPanel = new JPanel();
    private JPanel downloadPanel = new JPanel();
    private JPanel convertPanel = new JPanel();
    private JFXPanel searchPanel = new JFXPanel();
    private WebView youtube;
    private WebEngine youtubeEngine;
    private JButton downloadUrl = new JButton("Download with URL in Searcher");
    private JButton downloadLink = new JButton("Download with URL");
    private JButton converter = new JButton("Convert");
    private JButton merge = new JButton("Merge");
    private JButton searchKW = new JButton("Reload");
    private JOptionPane info = new JOptionPane();
    private JOptionPane failure = new JOptionPane();
    private JOptionPane userI = new JOptionPane();


    public ComputerUI() {
        setTitle("ZergTel VDC");
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(new Color(44, 42, 43));
        setMinimumSize(minSize);
        this.addComponentListener(new ComponentAdapter(){
            public void componentResized(ComponentEvent e){
                Dimension size = ComputerUI.this.getSize();
                if(size.width<minSize.width)
                    size.width = minSize.width;
                if(size.height<minSize.height)
                    size.height = minSize.height;
                ComputerUI.this.setSize(size);
            }
        });
        setVisible(true);

        GroupLayout l = new GroupLayout(getContentPane());
        GroupLayout cL = new GroupLayout(commands);
        GroupLayout dL = new GroupLayout(download);
        GroupLayout cL2 = new GroupLayout(convert);
        GroupLayout sL = new GroupLayout(search);
        GridLayout converterDisplayLayout = new GridLayout(1, convertProgress.length);

        setLayout(l);
        commands.setLayout(cL);
        download.setLayout(dL);
        convert.setLayout(cL2);
        convertPanel.setLayout(converterDisplayLayout);
        search.setLayout(sL); //I made seperate layouts in order to make them resizeable

        download.add(downloadLink);
        download.add(downloadUrl);
        convert.add(converter);
        convert.add(merge);
        search.add(searchKW);

        Platform.runLater(() -> {
            youtube = new WebView();
            youtubeEngine = youtube.getEngine();
            youtubeEngine.load("https://youtube.com");
            searchPanel.setScene(new Scene(youtube));
            youtubeEngine.getLoadWorker().stateProperty().addListener(
                    new ChangeListener<State>() {
                        @Override
                        public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
                            if(newValue == State.SUCCEEDED)
                                url = youtubeEngine.getLocation();
                            System.out.println(url);
                        }
                    }
            );
        });

        searchPanel.setBorder(BorderFactory.createTitledBorder("Searcher"));
        commands.setBorder(BorderFactory.createTitledBorder("Menu"));
        logo.setBorder(BorderFactory.createTitledBorder("ZergTel VDC"));
        download.setBorder(BorderFactory.createTitledBorder("Downloader"));
        convert.setBorder(BorderFactory.createTitledBorder("Converter"));
        search.setBorder(BorderFactory.createTitledBorder("Searcher"));

        l.setHorizontalGroup(l.createSequentialGroup()
                .addGroup(l.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(logo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(commands, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(l.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(filler)
                .addComponent(searchPanel)));
        l.setVerticalGroup(l.createSequentialGroup()
        .addGroup(l.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(logo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filler))
        .addGroup(l.createParallelGroup()
                .addComponent(commands, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchPanel)));

        cL.setHorizontalGroup(cL.createSequentialGroup()
                .addGroup(cL.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(download, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(convert, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(search, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))); //Adding the parameter 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE allows it to force resize, or else it will stay at min size.
        cL.setVerticalGroup(cL.createSequentialGroup()
        .addGroup(cL.createParallelGroup(GroupLayout.Alignment.BASELINE)
        .addComponent(download, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(cL.createParallelGroup(GroupLayout.Alignment.BASELINE)
        .addComponent(convert, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addComponent(search, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        dL.setHorizontalGroup(dL.createSequentialGroup()
        .addGroup(dL.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addComponent(downloadUrl, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(downloadLink, 0 ,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        dL.setVerticalGroup(dL.createSequentialGroup()
        .addGroup(dL.createParallelGroup(GroupLayout.Alignment.CENTER))
        .addComponent(downloadUrl, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(dL.createParallelGroup(GroupLayout.Alignment.CENTER)
        .addComponent(downloadLink, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        cL2.setHorizontalGroup(cL2.createSequentialGroup()
        .addGroup(cL2.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addComponent(converter, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(merge, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        cL2.setVerticalGroup(cL2.createSequentialGroup()
        .addGroup(cL2.createParallelGroup(GroupLayout.Alignment.CENTER)
        .addComponent(converter, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(cL2.createParallelGroup(GroupLayout.Alignment.CENTER)
        .addComponent(merge, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        sL.setHorizontalGroup(sL.createSequentialGroup()
        .addComponent(searchKW, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        sL.setVerticalGroup(sL.createSequentialGroup()
        .addComponent(searchKW, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        downloadUrl.addActionListener(this);
        downloadLink.addActionListener(this);
        converter.addActionListener(this);
        merge.addActionListener(this);
        searchKW.addActionListener(this);

        info.showMessageDialog(null, "Click Download with URL in Searcher once to get instructions, then the rest of the time click it to download, or else click convert/merge to use that function!");
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == downloadUrl) {
            if(start == 0) {
                info.showMessageDialog(null, "Reload the page with the desired download video. Click download while playing the youtube video.");
                start++;
            } else {
                try {
                    Downloader.get(url);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    failure.showMessageDialog(null, "Oh no! Something goofed!", "Error", failure.ERROR_MESSAGE);

                }
            }
        }
        if (e.getSource() == downloadLink) {
            u1 = userI.showInputDialog(null, "Insert the url for the video");
            try {
                Downloader.get(u1);
            } catch (Exception ex){
                ex.printStackTrace();
                failure.showMessageDialog(null, "Oh no! Something goofed!", "Error", failure.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == converter) {
            Converter c = new Converter();
            u1 = userI.showInputDialog(null, "Insert the directory of the file.");
            directory = userI.showInputDialog(null, "Insert the directory of the output file");
            name = userI.showInputDialog(null, "Insert name of the new file (EXCLUDE .FORMAT!!!!!!!) example: test");
            format = userI.showInputDialog(null, "Insert format of the file (EXCLUDE NAME AND PERIOD!!!!!!!!) example mp4");
            f1 = new File(u1);
            c.convert(f1, directory, name, format);
        }
        if(e.getSource() == merge) {
            Merge m = new Merge();
            u1 = userI.showInputDialog(null, "Insert the directory of the video file");
            u2 = userI.showInputDialog(null, "Insert the directory of the audio file");
            directory = userI.showInputDialog(null, "Insert the directory of the output file");
            name = userI.showInputDialog(null, "Insert name of the new file (EXCLUDE .FORMAT!!!!!!!) example: test");
            format = userI.showInputDialog(null, "Insert format of the file (EXCLUDE NAME AND PERIOD!!!!!!!!) example mp4");
            f1 = new File(u1);
            f2 = new File(u2);
            m.merge(f1, f2, directory, name, format);
        }
        if(e.getSource() == searchKW)
            Platform.runLater(() ->
                    youtubeEngine.reload());
    }
}


