package com.elephant.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class SocketChanelClient {
	private Selector selector = null;
	private SocketChannel socketClient = null;
	private ByteBuffer readByteBuffer = ByteBuffer.allocate(1024);
	private Charset charset = Charset.forName("utf-8");

	// private ByteBuffer writeByteBuffer = ByteBuffer.allocate(1024);
	// private Charset charset = Charset.forName("GBK");

	public SocketChanelClient(int port) throws IOException {
		selector = Selector.open();
		InetSocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(), 8090);
		socketClient = SocketChannel.open();
		socketClient.connect(address);
		socketClient.configureBlocking(false);
		socketClient.register(selector, SelectionKey.OP_READ);
	}

	public void readCmd() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while (null != (line = reader.readLine())) {
			System.out.println("client input is:" + line);
			
			readByteBuffer.clear();
			readByteBuffer.put(line.getBytes());
			send(socketClient);
			if (line.equals("bye")) {
				socketClient.close();
				break;
			}
		}
	}

	public void work() throws IOException {
		while (selector.select() > 0) {
			Set<SelectionKey> set = selector.selectedKeys();
			Iterator<SelectionKey> iterator = set.iterator();
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				iterator.remove();
				if (key.isConnectable()) {
					SocketChannel client = (SocketChannel) key.channel();
					sayHello(client);
				}
				if (key.isReadable()) {
					SocketChannel client = (SocketChannel) key.channel();
					// send(client);
					String anwer = read(client);
					System.out.println("anwer is:" + anwer);
				}
			}
		}
	}

	private String read(SocketChannel clientChannel) throws IOException {
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		clientChannel.read(byteBuffer);
		byteBuffer.flip();
		return charset.decode(byteBuffer).toString();
		// clientChannel.read(byteBuffer);
		// return byteBuffer.asCharBuffer().toString();
	}

	private void send(SocketChannel client) throws IOException {
		readByteBuffer.flip();

		client.write(readByteBuffer);
	}

	private void sayHello(SocketChannel client) throws IOException {
		socketClient.write(ByteBuffer.wrap("hello".getBytes()));
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		final SocketChanelClient client = new SocketChanelClient(8090);
		new Thread() {
			public void run() {
				try {
					client.readCmd();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
		}.start();
		client.work();
		// client.work();
	}

}
