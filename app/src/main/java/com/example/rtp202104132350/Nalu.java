package com.example.rtp202104132350;

import java.util.Arrays;

public class Nalu {

//    NALU头由一个字节组成,它的语法如下:
//
//            +---------------+
//            |0|1|2|3|4|5|6|7|
//            +-+-+-+-+-+-+-+-+
//            |F|NRI|  Type   |
//            +---------------+

    private static final int NALU_LENGTH = 8;

    /**
     * 获取 nalu的头部
     *
     * @return nalu的头部
     */
    public byte[] getNalu() {
        //定义 已经复制了的长度，下次从这个长度开始复制
        //设置 已经复制了的长度为0
        int destPos = 0;
        //定义 nalu byte 数组 的长度为
        byte[] naluBytes = new byte[NALU_LENGTH];
        System.out.println("getNalu:naluBytes:" + Arrays.toString(naluBytes));

        //获取 f byte 数组
        byte[] fBytes = getF();
        //复制 f byte 数组 到 nalu byte 数组
        System.arraycopy(fBytes, 0, naluBytes, destPos, FORBIDDEN_ZERO_BIT_LENGTH);
        //设置 已经复制了的长度为 fBytes.length
        destPos = destPos + FORBIDDEN_ZERO_BIT_LENGTH;
        System.out.println("getNalu:naluBytes:" + Arrays.toString(naluBytes));

        //获取 nri byte 数组
        byte[] nriBytes = getNRI();
        //复制 nri byte 数组到 nalu byte 数组
        System.arraycopy(nriBytes, 0, naluBytes, destPos, NAL_REF_IDC_LENGTH);
        //设置 已经复制了的长度为 加上 nriBytes.length
        destPos = destPos + NAL_REF_IDC_LENGTH;
        System.out.println("getNalu:naluBytes:" + Arrays.toString(naluBytes));

        //获取 type byte 数组
        byte[] typeBytes = getType();
        //复制 type byte 数组到 nalu byte 数组
        System.arraycopy(typeBytes, 0, naluBytes, destPos, NAL_UNIT_TYPE_LENGTH);
        //设置 已经复制了的长度为 加上 nriBytes.length
        destPos = destPos + NAL_UNIT_TYPE_LENGTH;
        System.out.println("naluBytes:" + Arrays.toString(naluBytes));

        return naluBytes;
    }

    /**
     * F: 1个比特.
     * forbidden_zero_bit. 在 H.264 规范中规定了这一位必须为 0.
     */
    private static final int FORBIDDEN_ZERO_BIT = 0;
    /**
     * forbidden_zero_bit 的数据长度
     */
    private static final int FORBIDDEN_ZERO_BIT_LENGTH = 1;

    public byte[] getF() {
        byte[] fBytes = IntUtil.toHH(FORBIDDEN_ZERO_BIT, FORBIDDEN_ZERO_BIT_LENGTH);
        System.out.println("getF:" + Arrays.toString(fBytes));
        return fBytes;
    }

    /**
     * NRI: 2个比特.
     * nal_ref_idc. 取00~11,似乎指示这个NALU的重要性,如00的NALU解码器可以丢弃它而不影响图像的回放.
     */
    private static final int NAL_REF_IDC = 0;
    /**
     * nal_ref_idc 的数据长度
     */
    private static final int NAL_REF_IDC_LENGTH = 2;

    public byte[] getNRI() {
        byte[] nriBytes = IntUtil.toHH(NAL_REF_IDC, NAL_REF_IDC_LENGTH);
        System.out.println("getNRI" + Arrays.toString(nriBytes));
        return nriBytes;
    }

    /**
     * 类型长度
     */
    private static final int NAL_UNIT_TYPE_LENGTH = 5;

    /**
     * 类型为 单个 nal 单元包
     */
    private static final int NAL_UNIT_TYPE = 1;

    /**
     * Type: 5个比特.
     * nal_unit_type. 这个NALU单元的类型.简述如下:
     * <p>
     * 0     没有定义
     * 1-23  NAL单元  单个 NAL 单元包
     * 24    STAP-A   单一时间的组合包
     * 25    STAP-B   单一时间的组合包
     * 26    MTAP16   多个时间的组合包
     * 27    MTAP24   多个时间的组合包
     * 28    FU-A     分片的单元
     * 29    FU-B     分片的单元
     * 30-31 没有定义
     * <p>
     * h264仅用1-23,24以后的用在RTP H264负载类型头中
     */
    public byte[] getType() {
        byte[] typeBytes = IntUtil.toHH(NAL_UNIT_TYPE, NAL_UNIT_TYPE_LENGTH);
        System.out.println("getType" + Arrays.toString(typeBytes));
        return typeBytes;
    }
}
