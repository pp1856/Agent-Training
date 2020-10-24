package com.dt.decision_tree;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileSystemView;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;
import weka.gui.treevisualizer.Colors;
import weka.gui.visualize.PNGWriter;
import weka.gui.visualize.JPEGWriter;
import weka.gui.visualize.PostscriptWriter;
import weka.gui.visualize.plugins.GraphVizTreeVisualization;
import weka.gui.visualize.plugins.TreeVisualizePlugin;

public class DecisionTree {

    public static final String TRAINING_DATASET="dataset.arff";

    public static Instances getDataSet(String fileName) throws IOException {
        int classIdx = 6;
        ArffLoader loader = new ArffLoader();
        loader.setSource(DecisionTree.class.getResourceAsStream("/"+fileName));
        Instances dataSet = loader.getDataSet();
        dataSet.setClassIndex(classIdx);
        return dataSet;
    }

    public static void process() throws Exception {
        Instances trainingDataSet = getDataSet(TRAINING_DATASET);
        J48 j48classifier = new J48();
        j48classifier.setUnpruned(true);

        Evaluation eval = new Evaluation(trainingDataSet);
        eval.crossValidateModel(j48classifier, trainingDataSet, 10, new Random(1));
        j48classifier.buildClassifier(trainingDataSet);

        System.out.println(eval.toSummaryString());
        
        System.out.println(j48classifier);
        System.out.println(eval.toMatrixString());
        System.out.println(eval.toClassDetailsString());

        // Write class
        Charset charset = Charset.forName("UTF-8");
        String home = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
        String filepath = home+"\\Player.java";
        Path path = Paths.get(filepath);
        String data = j48classifier.toSource("Player");
        try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
            writer.write(data, 0, data.length());
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        
        // display classifier 1
        final javax.swing.JFrame jf = 
          new javax.swing.JFrame("Weka Classifier Tree Visualizer: J48");
        jf.setSize(1920, 1040);
        jf.getContentPane().setLayout(new BorderLayout());
        TreeVisualizer tv = new TreeVisualizer(null,
            j48classifier.graph(),
            new PlaceNode2());
        jf.getContentPane().add(tv, BorderLayout.CENTER);
        jf.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                jf.dispose();
            }
        });
        jf.setVisible(true);
        tv.fitToScreen();
       
        GraphVizTreeVisualization gvTreeVisualization = GraphVizTreeVisualization.getSingleton();
        gvTreeVisualization.export(j48classifier.graph(), "png", home+"\\Tree.png");
        
        // display classifier 2
        /*File file = new File(home+"\\Tree.png");
        PNGWriter tree = new PNGWriter(tv, file);
        tree.initialize();
        //tree.setBackground(Color.WHITE);
        try {
            tree.generateOutput();
        } catch (Exception ex) {
            Logger.getLogger(DecisionTree.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
        
    public static void main(String[] ar) {
        try {    
            DecisionTree.process();
        } catch (Exception ex) {
            Logger.getLogger(DecisionTree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
}
