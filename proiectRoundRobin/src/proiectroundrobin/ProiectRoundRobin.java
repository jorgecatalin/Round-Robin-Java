/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proiectroundrobin;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;
import java.lang.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 *
 * @author George
 */
public class ProiectRoundRobin extends Application {
    
    
    public static float timpCompilare=0,timpAsteptare=0;
    public static int compilare[]={0,0,0,0,0},
        asteptare[]={0,0,0,0,0};
    
    
    public static void roundRobin(String p[], int a[], 
                                  int b[], int n) 
    { 
        // result of average times 
        int res = 0; 
        int resc = 0; 
  
        // for sequence storage 
        String seq = new String(); 
  
        // copy the burst array and arrival array 
        // for not effecting the actual array 
        int res_b[] = new int[b.length]; 
        int res_a[] = new int[a.length]; 
  
        for (int i = 0; i < res_b.length; i++) { 
            res_b[i] = b[i]; 
            res_a[i] = a[i]; 
        } 
  
        // critical time of system 
        int t = 0; 
  
        // for store the waiting time 
        int w[] = new int[p.length]; 
  
        // for store the Completion time 
        int comp[] = new int[p.length]; 
  
        while (true) { 
            boolean flag = true; 
            for (int i = 0; i < p.length; i++) { 
  
                // these condition for if 
                // arrival is not on zero 
  
                // check that if there come before qtime 
                if (res_a[i] <= t) { 
                    if (res_a[i] <= n) { 
                        if (res_b[i] > 0) { 
                            flag = false; 
                            if (res_b[i] > n) { 
  
                                // make decrease the b time 
                                t = t + n; 
                                res_b[i] = res_b[i] - n; 
                                res_a[i] = res_a[i] + n; 
                                seq += "->" + p[i]; 
                            } 
                            else { 
  
                                // for last time 
                                t = t + res_b[i]; 
  
                                // store comp time 
                                comp[i] = t - a[i]; 
  
                                // store wait time 
                                w[i] = t - b[i] - a[i]; 
                                res_b[i] = 0; 
  
                                // add sequence 
                                seq += "->" + p[i]; 
                            } 
                        } 
                    } 
                    else if (res_a[i] > n) { 
  
                        // is any have less arrival time 
                        // the coming process then execute them 
                        for (int j = 0; j < p.length; j++) { 
  
                            // compare 
                            if (res_a[j] < res_a[i]) { 
                                if (res_b[j] > 0) { 
                                    flag = false; 
                                    if (res_b[j] > n) { 
                                        t = t + n; 
                                        res_b[j] = res_b[j] - n; 
                                        res_a[j] = res_a[j] + n; 
                                        seq += "->" + p[j]; 
                                    } 
                                    else { 
                                        t = t + res_b[j]; 
                                        comp[j] = t - a[j]; 
                                        w[j] = t - b[j] - a[j]; 
                                        res_b[j] = 0; 
                                        seq += "->" + p[j]; 
                                    } 
                                } 
                            } 
                        } 
  
                        // now the previous porcess according to 
                        // ith is process 
                        if (res_b[i] > 0) { 
                            flag = false; 
  
                            // Check for greaters 
                            if (res_b[i] > n) { 
                                t = t + n; 
                                res_b[i] = res_b[i] - n; 
                                res_a[i] = res_a[i] + n; 
                                seq += "->" + p[i]; 
                            } 
                            else { 
                                t = t + res_b[i]; 
                                comp[i] = t - a[i]; 
                                w[i] = t - b[i] - a[i]; 
                                res_b[i] = 0; 
                                seq += "->" + p[i]; 
                            } 
                        } 
                    } 
                } 
  
                // if no process is come on thse critical 
                else if (res_a[i] > t) { 
                    t++; 
                    i--; 
                } 
            } 
            // for exit the while loop 
            if (flag) { 
                break; 
            } 
        } 
  
        System.out.println("name  ctime  wtime"); 
        for (int i = 0; i < p.length; i++) { 
            System.out.println(" " + p[i] + "    " + comp[i] 
                               + "    " + w[i]); 
            compilare[i]=comp[i];
            asteptare[i]=w[i];
            res = res + w[i]; 
            resc = resc + comp[i]; 
        } 
  
        System.out.println("Average waiting time is "
                           + (float)res / p.length); 
        timpAsteptare=(float)res / p.length;
        System.out.println("Average compilation  time is "
                           + (float)resc / p.length); 
        timpCompilare=(float)resc / p.length;
        System.out.println("Sequence is like that " + seq); 
    } 
    @Override
    public void start(Stage primaryStage) {
        
        String name[] = { "procesul1", "procesul2", "procesul3", "procesul4","procesul5" }; 
  
        // arrival for every process 
        int arrivaltime[] = { 0, 1, 2, 3, 4 }; 
        int bursttime[] = { 5, 5, 5, 5,5 }; 
        int q[] = {44}; 
  
        StackPane root = new StackPane();
        
        Scene scene = new Scene(root,800, 600);
        
        scene.getStylesheets().add("proiectroundrobin/stil.css");
        
        
        primaryStage.setTitle("Round Robin - Popa George Catalin");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        VBox TOT = new VBox();
        
        HBox Jos = new HBox();
        
        VBox Jos1 = new VBox();
        
        Label numeJos =new Label("Nume procese");
        Label numeJos1 =new Label("Proces 1");
        Label numeJos2 =new Label("Proces 2");
        Label numeJos3 =new Label("Proces 3");
        Label numeJos4 =new Label("Proces 4");
        Label numeJos5 =new Label("Proces 5");
        Jos1.getChildren().addAll(numeJos,numeJos1,numeJos2,numeJos3,numeJos4,numeJos5);
        
        VBox Jos2 = new VBox();
        
        Label numeCompilare =new Label("Timp compilare");
        Label numeCompilare1 =new Label("0");
        Label numeCompilare2 =new Label("0");
        Label numeCompilare3 =new Label("0");
        Label numeCompilare4 =new Label("0");
        Label numeCompilare5 =new Label("0");
        Jos2.getChildren().addAll(numeCompilare,numeCompilare1,numeCompilare2,numeCompilare3,numeCompilare4,numeCompilare5);
        
        VBox Jos3 = new VBox();
        
        Label numeAsteptare =new Label("Timp asteptare");
        Label numeAsteptare1 =new Label("0");
        Label numeAsteptare2 =new Label("0");
        Label numeAsteptare3 =new Label("0");
        Label numeAsteptare4 =new Label("0");
        Label numeAsteptare5 =new Label("0");
        Jos3.getChildren().addAll(numeAsteptare,numeAsteptare1,numeAsteptare2,numeAsteptare3,numeAsteptare4,numeAsteptare5);
        
        VBox Jos4 = new VBox();
        
        Label numeTimp =new Label("Timp mediu de asteptare");
        Label numeTimp1 =new Label("timp");
        Label numeTimp2 =new Label("Timp mediu de compilare");
        Label numeTimp3 =new Label("timpc");
        Jos4.getChildren().addAll(numeTimp,numeTimp1,numeTimp2,numeTimp3);
        
        Jos.getChildren().addAll(Jos1,Jos2,Jos3,Jos4);
        
        
        
        
        
        
        
        
        HBox Sus = new HBox();
        
        VBox Sus1 = new VBox();
        
        Label numeProcese =new Label("Nume procese");
        Label numeProcese1 =new Label("Proces 1");
        Label numeProcese2 =new Label("Proces 2");
        Label numeProcese3 =new Label("Proces 3");
        Label numeProcese4 =new Label("Proces 4");
        Label numeProcese5 =new Label("Proces 5");
        Sus1.getChildren().addAll(numeProcese,numeProcese1,numeProcese2,numeProcese3,numeProcese4,numeProcese5);
        
        
        
        
        
        VBox Sus2 = new VBox();
        
        Label numeAparitie =new Label("Timp alocat");
        TextField arrival1 = new TextField("");
        TextField arrival2 = new TextField("");
        TextField arrival3 = new TextField("");
        TextField arrival4 = new TextField("");
        TextField arrival5 = new TextField("");
        
        Sus2.getChildren().addAll(numeAparitie,arrival1,arrival2,arrival3,arrival4,arrival5);
        
        
        
        
        VBox Sus3 = new VBox();
        Label numeQuantum =new Label("Quantum");
        TextField quantumField = new TextField("");
        
        Sus3.getChildren().addAll(numeQuantum,quantumField);
        
        
        
        VBox Sus4 = new VBox();
        Button startButon = new Button("Start");
         startButon.setOnAction(e ->{
                   int b1=Integer.parseInt(arrival1.getText()),
                           b2=Integer.parseInt(arrival2.getText()),
                           b3=Integer.parseInt(arrival3.getText()),
                           b4=Integer.parseInt(arrival4.getText()),
                           b5=Integer.parseInt(arrival5.getText()),
                           quantumul=Integer.parseInt(quantumField.getText());
                   
                   bursttime[0]=b1;
                   bursttime[1]=b2;
                   bursttime[2]=b3;
                   bursttime[3]=b4;
                   bursttime[4]=b5;
                   q[0]=quantumul;
                   
        roundRobin(name, arrivaltime, bursttime, q[0]); 
                   numeCompilare1.setText(Integer.toString(compilare[0]));
                   numeCompilare2.setText(Integer.toString(compilare[1]));
                   numeCompilare3.setText(Integer.toString(compilare[2]));
                   numeCompilare4.setText(Integer.toString(compilare[3]));
                   numeCompilare5.setText(Integer.toString(compilare[4]));
                   
                   numeAsteptare1.setText(Integer.toString(asteptare[0]));
                   numeAsteptare2.setText(Integer.toString(asteptare[1]));
                   numeAsteptare3.setText(Integer.toString(asteptare[2]));
                   numeAsteptare4.setText(Integer.toString(asteptare[3]));
                   numeAsteptare5.setText(Integer.toString(asteptare[4]));
                   
                   
                   numeTimp1.setText(Float.toString(timpAsteptare));
                   numeTimp3.setText(Float.toString(timpCompilare));
                   
                   
                   
                   
                   
        });
        Sus4.getChildren().addAll(startButon);
        
        
        Sus.getChildren().addAll(Sus1,Sus2,Sus3,Sus4);
       
        TOT.getChildren().addAll(Sus,Jos);
        
        root.getChildren().addAll(TOT);
    
        // name of the process 
  
        // cal the function for output 
    
    
    }
        
    public static void main(String[] args) {
        launch(args);
    }
    
}
