package BackEnd;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHelper 
{
	public static final String serverDirPath = "";
	
	/**
	 * Uploads a file to the server's memory and saves the file name to the database
	 * @param name
	 * @param content
	 * @param extension
	 */
	void getFile(String name, byte[] content, String extension)
	{
		File newFile = new File(serverDirPath + name + extension);
		try{
		if(! newFile.exists())
		newFile.createNewFile();
		FileOutputStream writer = new FileOutputStream(newFile);
		BufferedOutputStream bos = new BufferedOutputStream(writer);
		bos.write(content);
		bos.close();
		} 
		catch(IOException e){
		e.printStackTrace();
		}
		//TODO add file name to a database table if we need to
	}
	
	void sendFile()
	{
		//TODO
	}}
