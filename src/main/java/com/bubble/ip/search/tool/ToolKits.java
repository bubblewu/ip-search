package com.bubble.ip.search.tool;

/**
 * 工具包
 *
 * @author wugang
 * date: 2019-03-13 17:19
 **/
public class ToolKits {

    /**
     * 将127.0.0.1形式的IP地址转换成十进制整数，这里没有进行任何错误处理
     *
     * @param strIP 字符串IP
     * @return 十进制整数
     */
    public static long ip2Long(String strIP) {
        long[] ip = new long[4];
        // 先找到IP地址字符串中.的位置
        int position1 = strIP.indexOf(".");
        int position2 = strIP.indexOf(".", position1 + 1);
        int position3 = strIP.indexOf(".", position2 + 1);
        // 将每个.之间的字符串转换成整型
        ip[0] = Long.parseLong(strIP.substring(0, position1));
        ip[1] = Long.parseLong(strIP.substring(position1 + 1, position2));
        ip[2] = Long.parseLong(strIP.substring(position2 + 1, position3));
        ip[3] = Long.parseLong(strIP.substring(position3 + 1));
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    /**
     * 将十进制整数形式转换成127.0.0.1形式的ip地址
     *
     * @param longIp 十进制整数
     * @return ip
     */
    public static String long2IP(long longIp) {
        StringBuffer sb = new StringBuffer();
        // 直接右移24位
        sb.append(String.valueOf((longIp >>> 24)));
        sb.append(".");
        // 将高8位置0，然后右移16位
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
        sb.append(".");
        // 将高16位置0，然后右移8位
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
        sb.append(".");
        // 将高24位置0
        sb.append(String.valueOf((longIp & 0x000000FF)));
        return sb.toString();
    }

    /**
     * ip地址合法性判断
     *
     * @param ip ip地址
     * @return 是否合法
     */
    public static boolean checkIp(String ip) {
        String[] numArray = ip.split("\\.");
        if (numArray.length == 4) {
            for (String num : numArray) {
                try {
                    int numIntValue = Integer.parseInt(num);
                    if (numIntValue < 0 || numIntValue > 255) {
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

}
