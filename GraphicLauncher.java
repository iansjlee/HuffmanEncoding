package Trees;
import java.io.*;
import java.util.Scanner;


import java.util.HashMap;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class GraphicLauncher extends BorderPane implements EventHandler<ActionEvent> {

	private File input;
	private File output;


	// organizes the graphical window with 3 buttons
	public  GraphicLauncher(Stage stage) {

		Button inputButton = new Button("Choose Input...");
		inputButton.setOnAction(new EventHandler<ActionEvent>() {
			// action event if input button is clicked; allows the user to choose a file from the computer
			public void handle(ActionEvent event ) {
				FileChooser fc = new FileChooser();

				do {
					input = fc.showOpenDialog(stage);

					if( input == null ) {
						break;
					}

					if(!getExtensionByStringHandling(input.getName()).toString().equals("Optional[txt]") ) {

						Alert alert = new Alert(AlertType.CONFIRMATION, "Input isn't a txt file",  ButtonType.OK);
						alert.showAndWait();

					}
				}while(!getExtensionByStringHandling(input.getName()).toString().equals("Optional[txt]"));


			}
			// this method returns the characters after the last "." in the name of the file as an Optional
			Optional<String> getExtensionByStringHandling (String filename) {
				return Optional.ofNullable(filename)
						.filter(f -> f.contains("."))
						.map(f -> f.substring(filename.lastIndexOf(".") + 1));
			}


		});

		this.setLeft(inputButton);



		Button outputButton = new Button("Choose Output...");
		outputButton.setOnAction(new EventHandler<ActionEvent>() {
			// action event if output button is clicked; allows user to enter name of new file that will be created
			public void handle(ActionEvent event ) {
				FileChooser fc = new FileChooser();
				output = fc.showSaveDialog(stage);
			}
		});

		this.setRight(outputButton);

		Button compress = new Button("Compress...");
		compress.setOnAction(new EventHandler<ActionEvent>() {

			/** if compress is clicked, as long as there is an input and an output, it puts the input file into a string
			 * then it creates a huffman object and passes the string into .compress method and prints the return into the output file
			 */

			public void handle(ActionEvent event ) {

				try {
					if(input!= null && output !=null) {
						@SuppressWarnings("resource")
						Scanner s = new Scanner(input);
						StringBuilder sb = new StringBuilder();
						while( s.hasNextLine()) {
							String line = s.nextLine()+'\n';
							sb.append(line);
						}
						Huffman huffer = new Huffman();
						HashMap<String,String> dictionary = huffer.compress(sb.toString());


						PrintWriter out = new PrintWriter(output, "UTF-8");
						if(dictionary ==null) {
							Alert alert = new Alert(AlertType.CONFIRMATION, "input file was empty",  ButtonType.OK);
							alert.showAndWait();				
						}
						out.print(dictionary);
						out.close();
						Alert alert = new Alert(AlertType.CONFIRMATION, "Created dictionary file " + output.getName(),  ButtonType.OK);
						alert.showAndWait();

					}
					else {
						Alert alert = new Alert(AlertType.CONFIRMATION, "Choose both an input and output file",  ButtonType.OK);
						alert.showAndWait();
					}
				}
				catch(IOException ex ) {

				}
			}
		});
		this.setBottom(compress);
	}

	@Override
	public void handle(ActionEvent event) {


	}



} 