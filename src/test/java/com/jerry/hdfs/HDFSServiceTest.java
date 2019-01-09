package com.jerry.hdfs;

import com.jerry.hdfs.model.User;
import com.jerry.hdfs.service.HDFSService;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FSDataInputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * @author Jerry.Wu
 * @description:
 * @date 2019/1/9 9:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class HDFSServiceTest {

    @Autowired
    private HDFSService hdfsService;

    /**
     * 测试创建HDFS目录
     */
    @Test
    public void testMkdir(){
        boolean result1 = hdfsService.mkdir("/testDir");
        System.out.println("创建结果：" + result1);
        boolean result2 = hdfsService.mkdir("/testDir/subDir");
        System.out.println("创建结果：" + result2);
    }

    /**
     * 测试上传文件
     */
    @Test
    public void testUploadFile(){
        //测试上传三个文件
        hdfsService.uploadFileToHdfs("D:/a.txt","/testDir");
        hdfsService.uploadFileToHdfs("D:/b.txt","/testDir");
        hdfsService.uploadFileToHdfs("D:/c.txt","/testDir/subDir");
    }

    /**
     * 测试列出某个目录下面的文件
     */
    @Test
    public void testListFiles(){
        List<Map<String,Object>> result = hdfsService.listFiles("/testDir",null);
        result.forEach(fileMap -> {
            fileMap.forEach((key,value) -> {
                System.out.println(key + "--" + value);
            });
            System.out.println();
        });
    }

    /**
     * 测试下载文件
     */
    @Test
    public void testDownloadFile(){
        hdfsService.downloadFileFromHdfs("/testDir/a.txt","D:/download/a.txt");
    }

    /**
     * 测试打开HDFS上面的文件
     */
    @Test
    public void testOpen() throws IOException {
        FSDataInputStream inputStream = hdfsService.open("/testDir/a.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        while((line = reader.readLine())!=null){
            System.out.println(line);
        }
        reader.close();
    }

    /**
     * 测试打开HDFS上面的文件，并转化为Java对象
     */
    @Test
    public void testOpenWithObject() throws IOException {
        User user = hdfsService.openWithObject("/testDir/user.txt", User.class);
        System.out.println(user.toString());
    }

    /**
     * 测试重命名
     */
    @Test
    public void testRename(){
        hdfsService.rename("/testDir/b.txt","/testDir/b_new.txt");
        //再次遍历
        testListFiles();
    }

    /**
     * 测试删除文件
     */
    @Test
    public void testDelete(){
        hdfsService.delete("/testDir/a.txt");
        //再次遍历
        testListFiles();
    }

    /**
     * 测试获取某个文件在HDFS集群的位置
     */
    @Test
    public void testGetFileBlockLocations() throws IOException {
        BlockLocation[] locations = hdfsService.getFileBlockLocations("/testDir/a.txt");
        if(locations != null && locations.length > 0){
            for(BlockLocation location : locations){
                System.out.println(location.getHosts()[0]);
            }
        }
    }

}
