package network;

import datagram.DatagramDecoder;
import datagram.DatagramEncoder;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author len
 */
public class Server implements Runnable {

    private final int port;
    private final ConcurrentLinkedQueue receiveQueue;
    private final ConcurrentLinkedQueue sendQueue;
    private final DatagramDecoder decoder;
    private final DatagramEncoder encoder;

    public Server(int port,
                  ConcurrentLinkedQueue receiveQueue,
                  ConcurrentLinkedQueue sendQueue,
                  DatagramDecoder decoder,
                  DatagramEncoder encoder) {
        this.port = port;
        this.receiveQueue = receiveQueue;
        this.sendQueue = sendQueue;
        this.decoder = decoder;
        this.encoder = encoder;
    }

    private static void read(SelectionKey selectionKey) throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(64);
        SocketChannel sc = (SocketChannel) selectionKey.channel();
        sc.read(buffer);
        buffer.flip();
        Charset charset = Charset.forName("gbk");
        CharsetDecoder decoder = charset.newDecoder();
        CharBuffer charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
        System.out.println(charBuffer.toString());
        sc.write(buffer);
    }

    @Override
    public void run() {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(port));

            Selector selector = Selector.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                selector.select();

                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                System.out.println("comething happend");
                while (it.hasNext()) {
                    SelectionKey selectionKey = it.next();
                    if (selectionKey.isAcceptable()) {
                        System.out.println("acceptable");
                        ServerSocketChannel socketChannel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel sc = socketChannel.accept();
                        if (sc != null) {
                            ByteBuffer buffer = ByteBuffer.wrap(new String("Hello, client\n").getBytes());

                            sc.write(buffer);
                            sc.configureBlocking(false);
                            sc.register(selector, SelectionKey.OP_READ);
                        } else {
                            System.err.println("socketChannel accept return null.");
                        }
                    } else if (selectionKey.isReadable()) {
                        System.out.println("readable");
                        read(selectionKey);
                    }
                    it.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
