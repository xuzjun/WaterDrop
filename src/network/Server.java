package network;

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

/**
 * @author len
 */
public class Server {

    private static final int PORT = 8090;

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(PORT));

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
    }

    private static void read(SelectionKey selectionKey) throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(64);
        SocketChannel sc = (SocketChannel) selectionKey.channel();
        sc.read(buffer);
        buffer.flip();
        Charset charset = Charset.forName("gbk");
        CharsetDecoder decoder= charset.newDecoder();
        CharBuffer charBuffer= decoder.decode(buffer.asReadOnlyBuffer());
        System.out.println(charBuffer.toString());
        sc.write(buffer);
    }
}
