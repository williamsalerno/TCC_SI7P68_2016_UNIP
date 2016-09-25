package br.com.timetrialfactory.maestro.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileSaver {

	private File imagesFolder;

	public FileSaver(ServletContext context) {
		String imagePath = context.getRealPath("/img/uploaded");
		imagesFolder = new File(imagePath);
	}

	public void saveImage(String gameTitle, MultipartFile image) throws IOException {
		File destiny = new File(imagesFolder, gameTitle + ".png");
		FileOutputStream fileOutputStream = new FileOutputStream(destiny);
		try {
			IOUtils.copy(image.getInputStream(), fileOutputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FileNotFoundException("Mensagem de erro");
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("Mensagem de erro");
		} finally {
			fileOutputStream.close();
			image.getInputStream().close();
		}
	}

}
