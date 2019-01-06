package utils;

import java.util.zip.CRC32;

public class Checksum {
    public static long crc32(byte[] bytes){
        CRC32 crc32 = new CRC32();
        crc32.update(bytes);
        long hash_num = crc32.getValue() & 0xffffffffL;
        return hash_num;
    }
}
