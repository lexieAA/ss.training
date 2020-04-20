package com.ss.training.object.library;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

public class UpdateFileEntry extends FileAction{
	

	public UpdateFileEntry(int type) {
		super(type);
	}

	public void updateFileEntry(String toUpdateId, String toUpdateInfo) {
		File file = new File(this.getFileMapItem(this.getType()) );
		File tempFile = new File("resources/temp.txt");
		try(PrintWriter printWriter = new PrintWriter(new FileWriter(tempFile));) {
			Files.lines(file.toPath())
				.filter(line -> !line.substring(0, line.indexOf(":")).equals(toUpdateId))
				.forEach(printWriter::println);
			
			printWriter.append(toUpdateId + ":" + toUpdateInfo);
			printWriter.flush();
			printWriter.close();
			file.delete();
			tempFile.renameTo(file);
			System.out.println("Success, entry was updated.");
		} catch (IOException e) {
			System.out.println("Error, was not able to update.");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		UpdateFileEntry updateFile = new UpdateFileEntry(1);
		updateFile.updateFileEntry("1", "alexis");

	}

}
