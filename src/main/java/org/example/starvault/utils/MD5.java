package org.example.starvault.utils;

import org.springframework.context.annotation.Bean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5
{
    /**
     * 将输入的字符串进行MD5加密。
     *
     * @param input 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String encryptToMD5(String input)
    {
        try
        {
            // 创建MessageDigest实例，指定使用MD5算法
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 将输入字符串转换为字节数组并更新MessageDigest
            md.update(input.getBytes());

            // 执行哈希计算，得到字节数组形式的结果
            byte[] digest = md.digest();

            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest)
            {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }
}
