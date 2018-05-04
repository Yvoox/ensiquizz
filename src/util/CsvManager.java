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
	private String fileName;
	private File file;

	public CsvManager(String fileName) {
		this.fileName = fileName;
	}


	private String getPath() {
		final File f = new File("");
		final String path = f.getAbsolutePath() + File.separator + fileName;
		return path;
	}

	public void getResource() {
		final String path = getPath();
		this.file = new File(path);
	}

	public void csvCreation() {
		FileWriter writer;
		File test = new File(Constantes.FILE_NAME);
		if(test.exists()) {
			System.out.println("Csv already exist");
		}
		else {
			System.out.println("Csv doesn't exist. Csv in creation..");
			try {
				writer = new FileWriter(Constantes.FILE_NAME);
				writer.append("NomJoueur,nbPartie,partie1,partie2,partie3,partie4,partie5\n");
				writer.close();
				if(test.exists()) {
					System.out.println("Csv creation succeed");
				}
				else {
					System.out.println("Csv creation failed");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error during csv creation");
				e.printStackTrace();
			}
		}
	}



	public List<String> readFile() throws IOException {

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

	public void writeCSV(String entry) {
		String content="";
		try {
			for(String it : readFile()) {
				content+=it+"\n";
			}
			content+=entry+"\n";
			FileWriter writer = new FileWriter(Constantes.FILE_NAME);
			writer.append(content);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
					for(int i = 2;i<data.length;i++) {
						dataSerie.getData().add(new XYChart.Data("Partie "+Integer.toString(i-1), Integer.parseInt(data[i])));
					}
					graph.getData().add(dataSerie);
					break;
				}
				else graph.getData().clear();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
