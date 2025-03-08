package org.example.starvault;

import org.example.starvault.params.DirectoryParam;
import org.example.starvault.service.DirectoryService;
import org.example.starvault.service.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@SpringBootTest
class StarVaultApplicationTests {

    @Autowired
    private RedisTemplate<String, List<String>> redisTemplate;
    @Autowired
    private DirectoryService directoryService;
    @Autowired
    private FileService fileService;
    @Test
    void contextLoads()
    {
        List<String> list = List.of("test1");
        redisTemplate.opsForValue().set("test", list);
        List<String> result = redisTemplate.opsForValue().get("test");
        result.add("test2");
        redisTemplate.opsForValue().set("test", result);
        System.out.println(result);
    }

    @Test
    void deleteAll()
    {
        directoryService.deleteAllDirectory(new DirectoryParam(9L));
    }

}
