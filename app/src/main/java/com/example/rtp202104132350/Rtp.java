package com.example.rtp202104132350;

import java.util.Arrays;

/**
 * 官方文档
 * https://tools.ietf.org/html/rfc1889
 * https://www.cnblogs.com/lidabo/p/4388656.html
 * http://t.zoukankan.com/dchipnau-p-5460026.html
 */
public class Rtp {

//    RTP 头的结构:
//
//            0                   1                   2                   3
//            0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
//            +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
//            |V=2|P|X|  CC   |M|     PT      |       sequence number         |
//            +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
//            |                           timestamp                           |
//            +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
//            |           synchronization source (SSRC) identifier            |
//            +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
//            |            contributing source (CSRC) identifiers             |
//            |                             ....                              |
//            +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

    /**
     * V(version)：2 bits，RTP的版本，这里统一为2
     * @return
     */
    private byte[] getV() {
        byte[] vBytes = IntUtil.toHH(2, 2);
        System.out.println("Rtp:getV:" + Arrays.toString(vBytes));
        return vBytes;
    }

    /**
     * P(padding)：1 bit，如果置1，在packet的末尾被填充，填充有时是方便一些针对固定长度的算法的封装
     * @return
     */
    private byte[] getP() {
        byte[] pBytes = IntUtil.toHH(0, 1);
        System.out.println("Rtp:getP:" + Arrays.toString(pBytes));
        return pBytes;
    }

    /**
     * X(extension)：1 bit，如果置1，在RTP Header会跟着一个header extension
     * @return
     */
    private byte[] getX() {
        byte[] xBytes = IntUtil.toHH(0, 1);
        System.out.println("Rtp:getX:" + Arrays.toString(xBytes));
        return xBytes;
    }

    /**
     * CC(CSRC count): 4 bits，表示头部后contributing sources的个数
     * @return
     */
    private byte[] getCC() {
        byte[] CCBytes = IntUtil.toHH(1, 4);
        System.out.println("Rtp:getCC:" + Arrays.toString(CCBytes));
        return CCBytes;
    }

    /**
     * M(marker): 1 bit，具体这位的定义会在一个profile里
     */
    private byte[] getM() {
        byte[] mBytes = IntUtil.toHH(1, 1);
        System.out.println("Rtp:getM:" + Arrays.toString(mBytes));
        return mBytes;
    }

    /**
     * PT(playload type): 7 bits，表示所传输的多媒体的类型，对应的编号在另一份文档rfc3551中有列出(http://tools.ietf.org/html/rfc3551)
     * 除了上表中明确指定PT值的负载类型，还有些负载类型由于诞生的较晚，没有具体的PT值，只能使用动态（dynamic）PT值，即96到127，
     * 这就是为什么大家普遍指定H264的PT值为96。具体PT值的负载类型可以在这里查询到：  RTP Payload Type
     * https://www.ietf.org/assignments/rtp-parameters/rtp-parameters.xml
     * @return
     */
    private byte[] getPT() {
        byte[] PTBytes = IntUtil.toHH(96, 7);
        System.out.println("Rtp:getPT:"+ Arrays.toString(PTBytes));
        return PTBytes;
    }

    /**
     * sequence number: 16 bits，每个RTP packet的sequence number会自动加一，以便接收端检测丢包情况
     */
    private int sequenceNumber = 0;

    /**
     * sequence number: 16 bits，每个RTP packet的sequence number会自动加一，以便接收端检测丢包情况
     * @return
     */
    private byte[] getSequenceNumber() {
        byte[] sequenceNumberBytes = IntUtil.toHH(sequenceNumber++, 16);
        System.out.println("Rtp:getSequenceNumber:"+ Arrays.toString(sequenceNumberBytes));
        return sequenceNumberBytes;
    }

    /**
     * timestamp: 32 bits，时间戳
     */
    private byte[] getTimestamp() {
        byte[] timestampBytes = IntUtil.toHH((int) System.currentTimeMillis(), 32);
        System.out.println("Rtp:getTimestamp:"+ Arrays.toString(timestampBytes));
        return timestampBytes;
    }

//    private byte[] SSRC() {
//        IntUtil.toHH()
//    }
//
//    private byte[] CSRC() {
//
//    }
}
