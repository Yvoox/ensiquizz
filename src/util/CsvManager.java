package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import model.Player;

public class CsvManager {
	private final String fileName;
	private File file;

	public CsvManager(String fileName) {
		this.fileName = fileName;
	}


	private String getPath() {
		final File f = new File("");
		return f.getAbsolutePath() + File.separator + fileName;
	}

	public void getResource() {
		final String path = getPath();
		this.file = new File(path);
	}

	public void csvCreation() {
		FileWriter writer;
		File test = new File(fileName);
		if(test.exists()) {
			System.out.println("Csv already exist");
		}
		else {
			System.out.println("Csv doesn't exist. Csv in creation..");
			try {
				writer = new FileWriter(fileName);
				writer.append("NomJoueur,partie1,partie2,partie3,partie4,partie5\n");
				writer.close();
				if(test.exists()) {
					System.out.println("Csv creation succeed");
				}
				else {
					System.out.println("Csv creation failed");
				}
			} catch (IOException e) {
				System.out.println("Error during csv creation");
				e.printStackTrace();
			}
		}
	}



	private List<String> readFile() throws IOException {

		List<String> result = new ArrayList<>();

		FileReader reader = new FileReader(this.file);
		BufferedReader buffer = new BufferedReader(reader);

		for (String line = buffer.readLine(); line != null; line = buffer.readLine()) {
			result.add(line);
		}

		reader.close();
		buffer.close();

		return result;
	}

	private void writeCSV(String entry) {
		StringBuilder content= new StringBuilder();
		try {
			for(String it : readFile()) {
				content.append(it).append("\n");
			}
			content.append(entry).append("\n");
			FileWriter writer = new FileWriter(fileName);
			writer.append(content.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void completeGraph(Player player, BarChart graph) {
		XYChart.Series dataSerie = new XYChart.Series();
		try {
			for(String it : readFile()) {
				if(it.contains(player.getName().get())) {
					String[] data = it.split(",");
					for(int i = 1;i<data.length;i++) {
						dataSerie.getData().add(new XYChart.Data("Partie "+Integer.toString(i-1), Integer.parseInt(data[i])));
					}
					graph.getData().add(dataSerie);
					break;
				}
				else graph.getData().clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void insertNewScore(Player player, int score) {
		
		String content ="";
		String playerRow="";
		String playerName = player.getName().get();
		
		try {
			
			for(String it : readFile()) {
				if(it.contains(playerName)) {
					playerRow+=it;
				}
				else {
					content+=it+"\n";
				}
			}
			playerRow+=","+score+"\n";
			FileWriter writer = new FileWriter(Constantes.FILE_NAME);
			writeCSV(content+playerRow);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createPlayer(Player player) {
		boolean exist = false;
		try {
			for(String it : readFile()) {
				if(it.contains(player.getName().get())) {
					exist = true;
					break;
				}
			}
			if(!exist) writeCSV(player.getName().get());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
