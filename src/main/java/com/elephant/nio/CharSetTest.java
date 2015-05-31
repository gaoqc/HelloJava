package com.elephant.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Map;

public class CharSetTest {
	public static void main(String[] args) throws IOException {
		// System.out.println(Charset.isSupported("utf-8"));
		String path = "/Users/Gao/Codes/testData/gongzi.htm";
//		 System.out.println(Files.toString(new File(path),Charset.forName("gbk")));
		System.out.println(getFileText(path, "gbk","utf-8"));

	}

	private static void showAllAvailableCharsets() {
		Map<String, Charset> map = Charset.availableCharsets();
		for (String key : map.keySet()) {
			System.out.println(key + "=" + map.get(key).toString());
		}
	}

	@SuppressWarnings("resource")
	private static String getFileText(String path, String fromEncode,String toEnCode) throws IOException {
		Charset fromCharset = Charset.forName(fromEncode);
		Charset toCharset= Charset.forName(toEnCode);
		FileChannel readChannel = new FileInputStream(new File(path)).getChannel();
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		StringBuffer sb = new StringBuffer();
		while (true) {
			byteBuffer.clear();
			if (-1 == readChannel.read(byteBuffer)) {
				break;
			} else {
				byteBuffer.flip();
				
		        sb.append(new String(toCharset.encode(fromCharset.decode(byteBuffer)).array()));

			}
		}
		return sb.toString();

	}
}
