package com.elephant.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChanelUtil {

	public static String receivethenSend(SocketChannel socketChannel, String toSend) throws IOException {
		String r = receive(socketChannel);
		send(socketChannel, toSend);
		return r;

	}

	public static String sendThenReceive(SocketChannel socketChannel, String toSend) throws IOException {
		send(socketChannel, toSend);
		return receive(socketChannel);

	}

	public static void send(SocketChannel socketChannel, String send) throws IOException {
		ByteBuffer byteBuffer=ByteBuffer.wrap(send.getBytes());
		socketChannel.write(byteBuffer);
	

	}

	public static String receive(SocketChannel socketChannel) throws IOException {
		StringBuffer sb = new StringBuffer();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while (true) {
			buffer.clear();
			if (-1 != socketChannel.read(buffer)) {
				buffer.flip();
				sb.append(new String(buffer.array()));
			} else {
				break;
			}
		}
		return sb.toString();
	}

}
