package com.bubble.ip.search.service;

import com.bubble.ip.search.bean.IPLocationBean;
import com.bubble.ip.search.tool.ToolKits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * IP查询服务
 *
 * @author wugang
 * date: 2019-03-13 17:13
 **/
public class SearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);
    private static final String DATA_FILE = "./data/ip_location_relation.txt";
    private static IPLocationBean[] sortedIpLocations;

    public SearchService() {
        List<IPLocationBean> ipLocationList = getIPLocationList();
        sortedIpLocations = new IPLocationBean[ipLocationList.size()];
        ipLocationList.toArray(sortedIpLocations);
    }

    public String search(String ip) {
        boolean isValid = ToolKits.checkIp(ip);
        if (isValid) {
            long ipLong = ToolKits.ip2Long(ip);
            int startPos = 0;
            int endPos = sortedIpLocations.length - 1;
            int index = getIndex4BinarySearch(sortedIpLocations, startPos, endPos, ipLong);
            if (index > -1) {
                return sortedIpLocations[index].getLocation();
            }
        } else {
            LOGGER.error("ip [{}] is invalid", ip);
            return "ip [" + ip + "] is invalid.";
        }
        return "no result.";
    }

    /**
     * 二分查找
     *
     * @param sortedIpLocations 基础数据
     * @param startPos          开始位置
     * @param endPos            结束位置
     * @param ipLong            十进制整数ip
     * @return ip在基础数据中的index
     */
    private int getIndex4BinarySearch(IPLocationBean[] sortedIpLocations, int startPos, int endPos, long ipLong) {
        if (startPos < 0 || endPos > sortedIpLocations.length - 1 || startPos > endPos) {
            return -1;
        }
        int middle = (startPos + endPos) / 2;
        if (ipLong > sortedIpLocations[middle].getEndIpLong()) {
            startPos = middle + 1;
            return getIndex4BinarySearch(sortedIpLocations, startPos, endPos, ipLong);
        } else if (ipLong < sortedIpLocations[middle].getStartIpLong()) {
            endPos = middle - 1;
            return getIndex4BinarySearch(sortedIpLocations, startPos, endPos, ipLong);
        }
        return middle;
    }

    private List<IPLocationBean> getIPLocationList() {
        List<IPLocationBean> ipLocationList = new ArrayList<>(500000);
        try {
            ipLocationList = Files.readAllLines(Paths.get(DATA_FILE))
                    .stream().map(SearchService::convert)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            Collections.sort(ipLocationList);
        } catch (IOException e) {
            LOGGER.error("load index data error.", e);
        }
        return ipLocationList;
    }

    private static IPLocationBean convert(String line) {
        line = line.trim();
        if (line.length() != 0) {
            String[] columnArray = line.split("\\t");
            if (columnArray.length == 3) {
                return new IPLocationBean(columnArray[0], columnArray[1], columnArray[2]);
            }
        }
        return null;
    }

}
