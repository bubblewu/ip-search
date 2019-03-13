package com.bubble.ip.search.bean;

import com.bubble.ip.search.tool.ToolKits;

/**
 * @author wugang
 * date: 2019-03-13 17:17
 **/
public class IPLocationBean implements Comparable<IPLocationBean>{
    @Override
    public int compareTo(IPLocationBean o) {
        return this.endIpLong - o.startIpLong > 0 ? 1 : 0;
    }

    private String startIp;
    private String endIp;
    private String location;
    private long startIpLong;
    private long endIpLong;

    public IPLocationBean(String startIp, String endIp, String location) {
        super();
        this.startIp = startIp;
        this.endIp = endIp;
        this.location = location;

        this.startIpLong = ToolKits.ip2Long(startIp);
        this.endIpLong = ToolKits.ip2Long(endIp);
    }


    public String getStartIp() {
        return startIp;
    }

    public void setStartIp(String startIp) {
        this.startIp = startIp;
    }

    public String getEndIp() {
        return endIp;
    }

    public void setEndIp(String endIp) {
        this.endIp = endIp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getStartIpLong() {
        return startIpLong;
    }

    public void setStartIpLong(long startIpLong) {
        this.startIpLong = startIpLong;
    }

    public long getEndIpLong() {
        return endIpLong;
    }

    public void setEndIpLong(long endIpLong) {
        this.endIpLong = endIpLong;
    }

    @Override
    public String toString() {
        return "IPLocationBean{" +
                "startIp='" + startIp + '\'' +
                ", endIp='" + endIp + '\'' +
                ", location='" + location + '\'' +
                ", startIpLong=" + startIpLong +
                ", endIpLong=" + endIpLong +
                '}';
    }
}
