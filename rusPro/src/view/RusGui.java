package view;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.Random;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.vocab;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


public class RusGui extends Application implements EventHandler<ActionEvent> {

	Button button;
	static ArrayList<vocab> vocab = new ArrayList<vocab>();
	Stage window;
	Scene scene;
	Scene listS;
	
	BorderPane main = new BorderPane();
	
	TextArea listOutput = new TextArea();

	
	public static void main(String[] args){
		launch(args);
		
		RusGui rs = new RusGui();
		rs.popVocab();
		//rs.save();
	}
	
	public void engQ(){
		int i = 0;
		int score = 0;
			while(i<10){
			Random ran = new Random();
			int n = ran.nextInt(vocab.size());
			System.out.println(vocab.get(n).getEngWo());
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();
			if (input.equals(vocab.get(n).getRusWo())){
				score++;
				i++;
			}
			else{
				i++;
			}
		}

		System.out.println("You got " + score + "/10");
	}
	
	public void rusQ(){
		int i = 0;
		int score = 0;
			while(i<10){
			Random ran = new Random();
			int n = ran.nextInt(vocab.size());
			System.out.println(vocab.get(n).getRusWo());
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();
			if (input.equals(vocab.get(n).getEngWo())){
				score++;
				i++;
			}
			else{
				i++;
			}
		}
		System.out.println("You got " + score + "/10");
	}
	
	public int ranGen(){
		Random ran = new Random();
		int n = ran.nextInt(vocab.size());
		return n;
	}
	
	public void save(){
		//File file = new File("vocab.dat");
		//file.delete();
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try{
			fos = new FileOutputStream("vocab.dat");
			oos = new ObjectOutputStream(fos);
				for(vocab v: vocab){
				oos.writeObject(v);
				
				}
			fos.close();
			oos.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void read(){
		listOutput.setText("");
		vocab = new ArrayList<vocab>();
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try{
			fis = new FileInputStream("vocab.dat");
			ois = new ObjectInputStream(fis);
			
			while(true){
				Object obj = ois.readObject();
				vocab v = (vocab)obj;
				vocab.add(v);
				listOutput.appendText(v.toString() + "\n");
			}
		}
		catch(EOFException eof){
			System.out.println(eof.getMessage());
	       }
	      catch(Exception e){
	        System.out.println(e.getMessage());
	      }
	      finally{
	        try{
	          fis.close();
	          ois.close();
	        }
	        catch(Exception e){
	           System.out.println(e.getMessage());
	        }
	      }
	}
	
	public void userIDGet(){
		 Scanner sc = new Scanner(System.in);
		 
		 System.out.println("Enter phrase ID: ");
		 int input = sc.nextInt();
		 
		 System.out.println(vocab.get(input).getvID());
		 
	}
	
	public void setVocab(){
		Scanner sc0 = new Scanner(System.in);
		 
		System.out.println("Enter phrase ID: ");
		String input0 = sc0.nextLine();
		 
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter phrase in English: ");
		String input1 = sc.nextLine();
		
		Scanner sc1 = new Scanner(System.in);
		
		System.out.println("Enter phrase in Russian: ");
		String input2 = sc1.nextLine();
		
		vocab v = new vocab(input0, input1, input2);
		vocab.add(v);
	}
	
	public void popVocab(){
		vocab v0 = new vocab("0", "привет", "Hi");
		vocab v1 = new vocab("1", "Вот мой рюкзак, Тим", "Here is my backpack, Tim");
		vocab v2 = new vocab("2", "Папа, это не мой мотор", "Dad, this is not my motor");
		vocab.add(v0);
		vocab.add(v1);
		vocab.add(v2);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		primaryStage.setTitle("Russian Learning");
		
		button = new Button();
		button.setText("Quiz");
		
		button.setOnAction(this);
		
		VBox grid = new VBox();

		VBox listG = new VBox(listOutput);
		
		
		listG.setPadding(new Insets(5, 5, 5, 5));
		listG.setStyle("-fx-background-color:#d7e8e0");
		
		grid.setPadding(new Insets(5, 5, 5, 5));
		
		
		grid.setStyle("-fx-background-color:#d7e8e0");
		
		Label title = new Label("Русский/Russian");
		GridPane.setConstraints(title, 1, 0);
		
		title.setFont(Font.font ("Verdana", 20));
		
		Label title1 = new Label("Русский/Russian");
		GridPane.setConstraints(title1, 1, 0);
		
		Button list = new Button("Listing Page");
		GridPane.setConstraints(list, 1, 3);
		
		Button read = new Button("List Array from file");
		GridPane.setConstraints(list, 1, 6);
		
		Button menu = new Button("Return to Menu");
		GridPane.setConstraints(menu, 1, 3);
		
		Button quizB = new Button("Quiz");
		GridPane.setConstraints(quizB, 1, 6);
		
		Button addA = new Button("Add Phrase to Array");
		GridPane.setConstraints(addA, 1, 9);
		
		Button exit = new Button("Exit");
		GridPane.setConstraints(exit, 1, 12);
		
		grid.getChildren().addAll(title, list, quizB, addA, exit);
		listG.getChildren().addAll(read, menu);
		
	
		
		grid.setAlignment(Pos.CENTER);
		listG.setAlignment(Pos.CENTER);
		
		grid.setSpacing(20);
		listG.setSpacing(50);
		
		scene = new Scene(grid, 600, 500);
		listS = new Scene(listG, 600, 500);
		
		main.setStyle("-fx-background-color:#123456");
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		grid.setPrefWidth(170);
		listG.setPrefWidth(170);
		
		
		
		title.setMinWidth(grid.getPrefWidth());
		title1.setMinWidth(listG.getPrefWidth());
		
		list.setMinWidth(grid.getPrefWidth());
		quizB.setMinWidth(grid.getPrefWidth());
		addA.setMinWidth(grid.getPrefWidth());
		exit.setMinWidth(grid.getPrefWidth());
		
		//LIST BUTTON ACTIONS
		menu.setOnAction(e -> window.setScene(scene));
		read.setOnAction(e ->read());
		//MENU BUTTON ACTIONS
		list.setOnAction(e ->window.setScene(listS));
		exit.setOnAction(e ->window.close());
		
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

}

