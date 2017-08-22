package edu.metrostate.ics340.program_a;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ProgramAChooser extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		Group root = new Group();
		Scene scene = new Scene(root, 800, 525);
		BorderPane bPane = new BorderPane();
		Insets bPaneCenterMargins = new Insets(15, 0, 0, 43);
		TextArea txtOutputArea = new TextArea();
		BorderPane.setMargin(txtOutputArea, bPaneCenterMargins);
		FileChooser fileChooser = new FileChooser();
		File initDir = new File(System.getProperty("user.dir"));

		bPane.setCenter(txtOutputArea);

		fileChooser.setInitialDirectory(initDir);
		fileChooser.setTitle("Open Text File");
		File file = fileChooser.showOpenDialog(stage);

		txtOutputArea.setPrefColumnCount(60);
		txtOutputArea.setPrefRowCount(29);
		txtOutputArea.setEditable(false);
		root.getChildren().add(bPane);

		if (file != null) {
			String fileName = file.getName().replaceAll(".txt", "");
			txtOutputArea.setText(displayTxtFile(file, fileName));
		}
		
		stage.setTitle("Program A");
		stage.setScene(scene);
		stage.show();
	}

	private String displayTxtFile(File file, String fileName)
			throws FileNotFoundException, UnsupportedEncodingException {
		StringBuilder sBuilder = new StringBuilder();

		try (BufferedReader inputReader = new BufferedReader(new FileReader(file));) {
			String text;
			String[] splitTxt;
			PrintWriter writer = new PrintWriter(fileName + "_out.txt");
			while ((text = inputReader.readLine()) != null) {
				sBuilder.append(text + "\n");
				splitTxt = text.split(" ");
				writer.write(splitTxt[splitTxt.length - 1] + System.lineSeparator());
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sBuilder.toString();
	}

}
