package sopra.formation.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class TestReadWriteExample {

	public static void main(String[] args) {
		lectureInputStream();

//		ecriture();

	}

	private static void lecture() {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader("stagiaires.txt");
			bufferedReader = new BufferedReader(fileReader);
			String line;

			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void lectureInputStream() {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("stagiaires.txt");
			int content;
			while ((content = fis.read()) != -1) { // compliqué pour gérer la rupture de ligne mais faisable :)
				System.out.print((char) content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void lectureReadAllLines() {
		Path filePath = Paths.get("stagiaires.txt");
		try {
			ArrayList<String> lines = new ArrayList<String>();
			lines = (ArrayList<String>) Files.readAllLines(filePath, StandardCharsets.UTF_8);

			for (String line : lines) {
				System.out.println(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void lectureFileAsString(String fileName) {
		String data = "";
		try {
			Path path = Paths.get(fileName);
			byte[] bytes = Files.readAllBytes(path);
			data = new String(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(data); // ne permet pas simplement une séparation par ligne
	}

	public static void lectureStream() {
		String fileName = "test.txt";

		Stream<String> stream = null;
		try {
			stream = Files.lines(Paths.get(fileName)); // méthode un peu complexe à ce stade de la formation => à revoir
														// plus tard
			stream.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}

	private static void lectureScanner() {
		Scanner scan = null;
		String line = "";
		try {
			scan = new Scanner(new File("stagiaires.txt"));
			while (scan.hasNextLine()) {
				line = scan.nextLine();
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (scan != null) {
					scan.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void lectureWithDelimiter() {

		File file = null;
		Scanner scanner = null;
		try {
			file = new File("stagiaires.txt");
			scanner = new Scanner(file);
			scanner.useDelimiter("\n");

			while (scanner.hasNext()) {
				String line = scanner.next();
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}

	private static void ecriture() {
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileWriter = new FileWriter("formateurs.txt");
			bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write("1;M;SULTAN;Eric;e.sultan@ajc-ingenierie.fr;22");
			bufferedWriter.newLine();
			bufferedWriter.write("2;M;TURPIN;Jonathan;j.turpin@gmail.com;12");
			bufferedWriter.newLine();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null) {
					bufferedWriter.close();
				}
				if (fileWriter != null) {
					fileWriter.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void ecritureFilesWriteString() {
		Path filePath = Paths.get("formateurs.txt");
		try {
			Files.writeString(filePath,
					"1;M;SULTAN;Eric;e.sultan@ajc-ingenierie.fr;22" + System.lineSeparator()
							+ "2;M;TURPIN;Jonathan;j.turpin@gmail.com;12" + System.lineSeparator(),
					StandardCharsets.UTF_8, StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void ecritureFilesWrite() {
		String fileName = "formateurs.txt";
		List<String> lignes = Arrays.asList("1;M;SULTAN;Eric;e.sultan@ajc-ingenierie.fr;22",
				"2;M;TURPIN;Jonathan;j.turpin@gmail.com;12");
		Path fichier = Paths.get(fileName);
		try {
			Files.write(fichier, lignes, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void ecriture2() {
		OutputStream out = null;
		try {
			out = new FileOutputStream("formateurs.txt", true);

			String str = "1;M;SULTAN;Eric;e.sultan@ajc-ingenierie.fr;22\n";
			str = str + "2;M;TURPIN;Jonathan;j.turpin@gmail.com;12\n";

			byte[] b = str.getBytes("UTF-8");
			out.write(b);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
