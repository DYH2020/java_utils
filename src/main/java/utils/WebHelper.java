package utils;

import javax.servlet.http.HttpServletRequest;

public class WebHelper {
    public static String getIP(HttpServletRequest httpServletRequest) {
        String ip = "";
        try {
            String strIPlist = httpServletRequest.getHeader("X-Forwarded-For");
            if (strIPlist != null && !strIPlist.equals("")) {
                for (String item : strIPlist.replace("'", "").split(",")) {
                    ip = item;
                    break;
                }
            }
            if (ip == null || ip.equals("")) {
                ip = httpServletRequest.getHeader("REMOTE_ADDR");
                if (ip == null || ip.equals("")) {
                    ip = httpServletRequest.getRemoteAddr();
                }
                return ip == null ? "" : ip.replace("'", "");
            }
        } catch (Exception e) {
        }
        return ip;
    }
}
