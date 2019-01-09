package com.jerry.hdfs.service;

import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.PathFilter;

import java.util.List;
import java.util.Map;

/**
 * HDFS操作服务
 * @author Jerry.Wu
 * @description:
 * @date 2019/1/8 9:53
 */
public interface HDFSService {

    /**
     * 创建HDFS目录
     * @param path HDFS的相对目录路径，比如：/testDir
     * @return boolean 是否创建成功
     */
    boolean mkdir(String path);

    /**
     * 上传文件至HDFS
     * @param srcFile 本地文件路径，比如：D:/test.txt
     * @param dstPath HDFS的相对目录路径，比如：/testDir
     */
    void uploadFileToHdfs(String srcFile, String dstPath);

    /**
     * 上传文件至HDFS
     * @param delSrc    是否删除本地文件
     * @param overwrite 是否覆盖HDFS上面的文件
     * @param srcFile   本地文件路径，比如：D:/test.txt
     * @param dstPath   HDFS的相对目录路径，比如：/testDir
     */
    void uploadFileToHdfs(boolean delSrc, boolean overwrite, String srcFile, String dstPath);

    /**
     * 判断文件或者目录是否在HDFS上面存在
     * @param path HDFS的相对目录路径，比如：/testDir、/testDir/a.txt
     * @return boolean
     */
    boolean checkExists(String path);

    /**
     * 获取HDFS上面的某个路径下面的所有文件或目录（不包含子目录）信息
     * @param path HDFS的相对目录路径，比如：/testDir
     * @return java.util.List<java.util.Map   <   java.lang.String   ,   java.lang.Object>>
     */
    List<Map<String, Object>> listFiles(String path, PathFilter pathFilter);

    /**
     * 从HDFS下载文件至本地
     * @param srcFile HDFS的相对目录路径，比如：/testDir/a.txt
     * @param dstFile 下载之后本地文件路径（如果本地文件目录不存在，则会自动创建），比如：D:/test.txt
     */
    void downloadFileFromHdfs(String srcFile, String dstFile);

    /**
     * 打开HDFS上面的文件并返回 InputStream
     * @param path HDFS的相对目录路径，比如：/testDir/c.txt
     * @return FSDataInputStream
     */
    FSDataInputStream open(String path);

    /**
     * 打开HDFS上面的文件并返回byte数组，方便Web端下载文件
     * <p>new ResponseEntity<byte[]>(byte数组, headers, HttpStatus.CREATED);</p>
     * <p>或者：new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(templateFile), headers, HttpStatus.CREATED);</p>
     *
     * @param path HDFS的相对目录路径，比如：/testDir/b.txt
     * @return FSDataInputStream
     */
    byte[] openWithBytes(String path);

    /**
     * 打开HDFS上面的文件并返回String字符串
     * @param path HDFS的相对目录路径，比如：/testDir/b.txt
     * @return FSDataInputStream
     */
    String openWithString(String path);

    /**
     * 打开HDFS上面的文件并转换为Java对象（需要HDFS上门的文件内容为JSON字符串）
     * @param path HDFS的相对目录路径，比如：/testDir/c.txt
     * @return FSDataInputStream
     */
    <T extends Object> T openWithObject(String path, Class<T> clazz);

    /**
     * 重命名
     * @param srcFile 重命名之前的HDFS的相对目录路径，比如：/testDir/b.txt
     * @param dstFile 重命名之后的HDFS的相对目录路径，比如：/testDir/b_new.txt
     */
    boolean rename(String srcFile, String dstFile);

    /**
     * 删除HDFS文件或目录
     * @param path HDFS的相对目录路径，比如：/testDir/c.txt
     * @return boolean
     */
    boolean delete(String path);

    /**
     * 获取某个文件在HDFS集群的位置
     * @param path HDFS的相对目录路径，比如：/testDir/a.txt
     * @return org.apache.hadoop.fs.BlockLocation[]
     */
    BlockLocation[] getFileBlockLocations(String path);

}
