package com.example.rtp202104132350;

//https://blog.csdn.net/qq_41054313/article/details/88424454

public class IntUtil {

    /**
     * int 转 byte[]   低字节在前（低字节序）
     * @param n
     * @return
     */
    public static final byte[] toLH(int n, int length) {
        byte[] b = new byte[length];
//        b[0] = (byte) (n & 0xff);
//        b[1] = (byte) (n >> 8 & 0xff);
//        b[2] = (byte) (n >> 16 & 0xff);
//        b[3] = (byte) (n >> 24 & 0xff);

//        for (int i = 0; i < length; i++) {
//            b[i] = (byte) (n >> (i * 8) & 0xff);
//        }

        for (int i = 0; i < length; i++) {
            b[i] = (byte) ((n >>> i) & 1);
        }
        return b;
    }

    /**
     * int 转 byte[]   高字节在前（高字节序）
     * @param n
     * @return
     */
    public static final byte[] toHH(int n, int length) {
        byte[] b = new byte[length];
//        for (int i = 0; i < length; i++) {
//            b[i] = (byte) (n >> ((length - (i + 1)) * 8) & 0xff);
//        }
//        b[3] = (byte) (n & 0xff);
//        b[2] = (byte) (n >> 8 & 0xff);
//        b[1] = (byte) (n >> 16 & 0xff);
//        b[0] = (byte) (n >> 24 & 0xff);

        for (int i = 0; i < length; i++) {
            b[length - 1 - i] = (byte) ((n >>> i) & 1);
        }
        return b;
    }

    /**
     * byte[] 转 int 低字节在前（低字节序）
     * @param b
     * @return
     */
    public static final int toInt(byte[] b){
        int res = 0;
        for(int i=0;i<b.length;i++){
            res += (b[i] & 0xff) << (i*8);
        }
        return res;
    }

    /**
     * byte[] 转 int 高字节在前（高字节序）
     * @param b
     * @return
     */
    public static final int toInt2(byte[] b){
        int res = 0;
        for(int i=0;i<b.length;i++){
            res += (b[i] & 0xff) << ((3-i)*8);
        }
        return res;
    }
}
