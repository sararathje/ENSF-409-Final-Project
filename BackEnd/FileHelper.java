package BackEnd;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import Constants.ConnectionConstants;

public class FileHelper implements ConnectionConstants
{	
	/**
	 * Uploads a file to the server's memory and saves the file name to the database
	 * @param name
	 * @param content
	 * @param extension
	 */
	void saveFile(String name, byte[] content, String extension)
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
	}
	
	byte[]  getFile(String name, String extension)
	{
		byte[] content = null;
		try
		{
			if(extension.equals(TXT) || extension.equals(PDF))
	    	{
				File selectedFile = new File(serverDirPath + name + extension);
		    	long length = selectedFile.length();
		    	content = new byte[(int) length];
		    	FileInputStream fis = new FileInputStream(selectedFile);
		    	BufferedInputStream bos = new BufferedInputStream(fis);
		    	bos.read(content, 0, (int)length);
		    	bos.close();
		    	fis.close();
	    	}
			else
			{
				System.err.println("Invalid extension");
			}
    	} 
    	catch (FileNotFoundException e) 
    	{
    	e.printStackTrace();
    	} 
    	catch(IOException e)
    	{
    	e.printStackTrace();
    	}
    	return content;
	}}
