package com.test.jd.file;
 
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * Created with IntelliJ IDEA.
 * User: a549238
 * Date: 12/6/13
 * Time: 3:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class MappedbyteBuffer {
 
    private static final int BYTE_SIZE = 4000000;
    private static final int INT_SIZE = 1000000;
    private static final int INT_BUFF_SIZE = 200000;
 
    private static final String FILE_NAME = "temp.txt";
    private static abstract class Tester
    {
        private String name ;
        public Tester(String name ){ this.name = name;}
        public void runTest()
        {
            System.out.print("name:" +name);
            long  start =System.nanoTime();
            try {
                test();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            System.out.format("    %.2f \n",(System.nanoTime()-start)/1.0e9);
        }
        public abstract void  test() throws IOException;
    }
 
    private static Tester[] tests ={
            new Tester("Stream Writer") {
                @Override
                public void test() throws IOException{
                    DataOutputStream out =new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File(FILE_NAME))));
                    for(int i =0;i<BYTE_SIZE;i++)
                        out.write(i);
                    out.close();
                }
            },
            new Tester("Mapped Writer") {
                @Override
                public void test() throws IOException {
                    FileChannel fc= new RandomAccessFile(FILE_NAME,"rw").getChannel();
                    IntBuffer intBuffer = fc.map(FileChannel.MapMode.READ_WRITE,0,fc.size()).asIntBuffer();
                    for(int i=0;i<INT_SIZE;i++)
                    {
                        intBuffer.put(i);
                    }
                    fc.close();
                }
            },
            new Tester("Channel Writer WithOut Mapped") {
                @Override
                public void test() throws IOException {
                    FileChannel fc= new RandomAccessFile(FILE_NAME,"rw").getChannel();
                    ByteBuffer byteBuffer =ByteBuffer.allocate((int)fc.size());
                    //IntBuffer intBuffer = fc.map(FileChannel.MapMode.READ_WRITE,0,fc.size()).asIntBuffer();
                    for(int i=0;i<INT_SIZE;i++)
                    {
                        byteBuffer.put("12".getBytes());
 
                    }
                    byteBuffer.flip();
                    fc.write(byteBuffer);
                    fc.close();
                }
            },
            new Tester("Stream Read") {
                @Override
                public void test() throws IOException {
                     DataInputStream in =new DataInputStream(new BufferedInputStream(new FileInputStream(FILE_NAME)));
                    for(int i =0 ;i<INT_SIZE;i++)
                        in.readInt();
                    in.close();
                }
            },
            new Tester("Mapped Read") {
                @Override
                public void test() throws IOException {
                    FileChannel fc= new FileInputStream(new File(FILE_NAME)).getChannel();
                    IntBuffer intBuffer =fc.map(FileChannel.MapMode.READ_ONLY,0,fc.size()).asIntBuffer();
                    while(intBuffer.hasRemaining())
                        intBuffer.get();
                    fc.close();
                }
            },
            new Tester("Stream Read/Write") {
                @Override
                public void test() throws IOException {
                    RandomAccessFile file =new RandomAccessFile(FILE_NAME,"rw");
                    file.writeInt(1);
                    for(int i =0;i<INT_BUFF_SIZE;i++)
                    {
                        file.seek(file.length()-4);
                        file.writeInt(file.readInt());
                    }
                    file.close();
                }
            } ,
            new Tester("Mapped Read/write") {
                @Override
                public void test() throws IOException {
                    FileChannel fc= new RandomAccessFile(new File(FILE_NAME),"rw").getChannel();
                    IntBuffer intBuffer =fc.map(FileChannel.MapMode.READ_WRITE,0,fc.size()).asIntBuffer();
                    intBuffer.put(0);
                    for(int i=1;i<INT_BUFF_SIZE;i++)
                        intBuffer.put(intBuffer.get(i-1));
                    fc.close();
                }
            }
    };
 
    public static void main(String[] args)
    {
        for(Tester tester: tests)
            tester.runTest();
        //tests[1].runTest();
    }
}


/*
name:Stream Writer0.16
name:Mapped Writer0.01
name:Stream Read0.10
name:Mapped Read0.01
name:Stream Read/Write2.37
name:Mapped Read/write0.01
 */

