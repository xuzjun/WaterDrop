package network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author len
 */
public class Server implements Runnable {

    private final int port;
    private final LinkedBlockingDeque<SocketAndBuffer> receiveQueue;
    private final LinkedBlockingDeque<SocketAndBuffer> sendQueue;

    public Server(int port,
                  LinkedBlockingDeque<SocketAndBuffer> receiveQueue,
                  LinkedBlockingDeque<SocketAndBuffer> sendQueue) {
        this.port = port;
        this.receiveQueue = receiveQueue;
        this.sendQueue = sendQueue;
    }

    private void read(SelectionKey selectionKey) throws IOException {
        ByteBuffer dataLenBuffer = ByteBuffer.allocate(4);
        SocketChannel sc = (SocketChannel) selectionKey.channel();
        do {
            int r = sc.read(dataLenBuffer);
            if (r == 0) {
                break;
            }
            if (r == -1) {
                sc.close();
                break;
            }
            dataLenBuffer.flip();
            int dataLen = dataLenBuffer.getInt();

            ByteBuffer dataBuffer = ByteBuffer.allocate(4 + dataLen);
            dataBuffer.putInt(dataLen);
            sc.read(dataBuffer);
            dataBuffer.flip();
            receiveQueue.add(new SocketAndBuffer(sc, dataBuffer));
        } while (true);
    }

    private int write(SocketChannel sc, ByteBuffer buffer) {
        try {
            return sc.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
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
                selector.select(100);
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey selectionKey = it.next();
                    if (selectionKey.isAcceptable()) {
                        System.out.println("acceptable");
                        ServerSocketChannel socketChannel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel sc = socketChannel.accept();
                        if (sc != null) {
                            sc.configureBlocking(false);
                            sc.register(selector, SelectionKey.OP_READ);
                        } else {
                            System.err.println("socketChannel accept return null.");
                        }
                    } else if (selectionKey.isReadable()) {
                        System.out.println("readable");
                        this.read(selectionKey);
                    }
                    it.remove();
                }

                SocketAndBuffer socketAndBuffer = sendQueue.poll();
                if (socketAndBuffer != null) {
                    int ret = write(socketAndBuffer.getSocketChannel(), socketAndBuffer.getBuffer());
                    System.out.println("ret [%d]" + ret);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
