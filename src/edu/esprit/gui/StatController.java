/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Evenement;
import edu.esprit.services.ServiceEvenement;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * FXML Controller class
 *
 * @author siwar
 */
public class StatController implements Initializable {
     private ChartPanel weightChartPanel;
    @FXML
    private StackPane chartContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         ServiceEvenement se= new ServiceEvenement();
        
         List<Evenement> e = se.getAll();
        // Compute statistics for weight and material
        Map<String, Float> weightStats = e.stream()
                .collect(Collectors.groupingBy(Evenement::getNomEvenement, Collectors.summingInt(Evenement::getNbreParticipant)))
                .entrySet().stream().collect(Collectors.toMap(ev -> ev.getKey(), ev -> ev.getValue().floatValue()));
        // Create dataset for weight chart
        DefaultCategoryDataset weightDataset = new DefaultCategoryDataset();
        weightStats.forEach((material, weight) -> weightDataset.setValue(weight, "nbreParticipant", material));
        // Create chart for weight statistics
        JFreeChart weightChart = ChartFactory.createBarChart("Evenement nbreParticipant Statistics", "nomEvenement", "nbreParticipant", weightDataset);
        weightChartPanel = new ChartPanel(weightChart);
        // Create chart panel and add to container
        SwingNode swingNode = new SwingNode();
        swingNode.setContent(weightChartPanel);
        chartContainer.getChildren().add(swingNode);
        // TODO
    }    
    
}
