import java.nio.*;
import java.nio.channels.FileChannel;
import java.io.*;

/* Author:  Prashant Prakash 
 * Created Date : 14th Feb 2015
 * Description:  This Class reads contents from Files.
 */

public class FileDataSource {

	private String file;
	private String content;

	public FileDataSource(File file, String path) {
		this.file = path + file.getName();
	}

	public void readFile() throws IOException {
		FileInputStream fis = new FileInputStream(this.file);
		FileChannel fc = fis.getChannel();
		ByteBuffer buff = ByteBuffer.allocate((int) fc.size());
		fc.read(buff);
		fc.close();
		fis.close();
		this.content = new String(buff.array());
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}
