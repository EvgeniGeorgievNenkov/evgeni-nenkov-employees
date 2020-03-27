package reading;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReadingImpl implements FileReading {
	
	@Override
	public List<String> read(String filePath) {
		List<String> content = new ArrayList<>();

		File file = new File(filePath);
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (!line.trim().isEmpty()) {
					content.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			// log here...
		}

		return content;
	}

	public void write(String fileContent, String file) {
		try (OutputStream outputStream = new FileOutputStream(file)) {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
			writer.write(fileContent);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
			// log here...
		}
	}
}
