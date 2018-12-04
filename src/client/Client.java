package client;

import consts.CommConst;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;

/**
 * @author len
 */
public class Client {

    public static void main(String[] args) {
        ByteBuffer buffer = DatagramMaker.makeTraderLoginReqDatagram(new String("byd0923"), new String("000121"));
        buffer.flip();

        try (SocketChannel socketChannel = SocketChannel.open()) {
            //连接服务端socket
            SocketAddress socketAddress = new InetSocketAddress("localhost", 8090);
            socketChannel.connect(socketAddress);

            socketChannel.write(buffer);
            buffer.clear();

            Selector selector = Selector.open();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);

            selector.select();

            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                SocketChannel sc = (SocketChannel) key.channel();
                if (key.isReadable()) {
                    System.out.println("socket readable.");
                }

                ByteBuffer buffer1 = ByteBuffer.allocate(CommConst.INT_SIZE);
                sc.read(buffer1);
                buffer1.flip();
                int len = buffer1.getInt();
                buffer1.flip();
                System.out.println("data len: " + len);

                ByteBuffer buffer2 = ByteBuffer.allocate(len);
                sc.read(buffer2);
                buffer2.flip();
                Charset charset = Charset.forName("gbk");
                CharsetDecoder decoder = charset.newDecoder();
                CharBuffer charBuffer = decoder.decode(buffer2.asReadOnlyBuffer());
                System.out.println(charBuffer.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

