package com.elephant.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;

import tools.date.DateUtil;

public class ServerSocketChannelServer {
	private Selector selector = null;
	private ServerSocketChannel serverSocketChannel = null;

	private Charset charset = Charset.forName("GBK");
	private Map<String, String> cmdMap = Maps.newHashMap();
    private final String  ENDFLAG="bye";

	private ServerSocketChannelServer(int port) throws IOException, InterruptedException {
		selector = Selector.open();
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().setReuseAddress(true);
		 serverSocketChannel.configureBlocking(false);
		serverSocketChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(), port));
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		System.out.println("server started!");
		cmdMap.put("chName", "高其成");
		cmdMap.put("cnName", "gaoqc");
		cmdMap.put("age", "28");
		cmdMap.put("where", "深圳");
		String now = DateUtil.toString(DateUtil.nowDate(), null);
		cmdMap.put("date", now);
		cmdMap.put("time", now);
		cmdMap.put("when", now);
	}

//	public void accpet() {
//		while (true) {
//
//			try {
//
//				SocketChannel clientChannel = serverSocketChannel.accept();
//                 if(clientChannel==null){
//                	 continue;
//                 }
//				System.out.println("receive from:" + clientChannel.socket().getInetAddress() + ", port:"
//						+ clientChannel.socket().getPort());
//				clientChannel.configureBlocking(false);
//				synchronized (gate) {
//					selector.wakeup();
//					clientChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//	}

	public void service() throws IOException {
//		serverSocketChannel.register(selector, SelectionKey.OP_READ);
//          synchronized(gate){
//        	  
//          }
		while (selector.select() > 0) {
			try {
				Set<SelectionKey> set = selector.selectedKeys();
				Iterator<SelectionKey> iterator = set.iterator();
				while (iterator.hasNext()) {
					SelectionKey selectionKey = iterator.next();
					iterator.remove();
					if (selectionKey.isAcceptable()) {
						ServerSocketChannel serverChannel=(ServerSocketChannel)selectionKey.channel();
						SocketChannel clientChannel=serverChannel.accept();
						clientChannel.configureBlocking(false);
						clientChannel.register(selector, SelectionKey.OP_READ);
					} 
					if(selectionKey.isReadable()){
						SocketChannel clientChannel=(SocketChannel)selectionKey.channel();
						
						String msg=read(clientChannel);
						write(selectionKey,clientChannel, msg);
					}
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// private void accept(SelectionKey key) throws IOException {
	// ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
	// SocketChannel client = ssc.accept();
	// System.out.println("client from:" + client.socket().getInetAddress() +
	// ",port:" + client.socket().getPort());
	// client.configureBlocking(false);
	// client.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
	//
	// }

	private String read(SocketChannel socketChannel) throws IOException {
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		socketChannel.read(byteBuffer);
		byteBuffer.flip();
		return decode(byteBuffer);
	}

	private void write(SelectionKey selectionKey,SocketChannel socketChannel, String msg) throws IOException {
		if(ENDFLAG.equalsIgnoreCase(msg)){
			System.out.println("to ...end");
			selectionKey.cancel();
			socketChannel.close();
			return;
		}
		String what = cmdMap.get(msg);
		if (null == what) {
			what = "unknow  msg:" + msg;
		}
		System.out.println("server send msg:" + what);
		ByteBuffer byteBuffer = ByteBuffer.wrap(what.getBytes());
//		byteBuffer.flip();
		while (byteBuffer.hasRemaining()) {
			socketChannel.write(byteBuffer);
		}

	}

	private String decode(ByteBuffer byteBuffer) {
		return charset.decode(byteBuffer).toString();
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		final ServerSocketChannelServer server = new ServerSocketChannelServer(8090);
//		new Thread() {
//			public void run() {
//				server.accpet();
//			};
//		}.start();
		// server.accpet();
		server.service();
	}
}
