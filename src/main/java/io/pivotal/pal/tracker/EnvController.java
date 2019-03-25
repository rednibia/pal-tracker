package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    private String port;

    public EnvController(@Value("${port:NOT SET}") String port,@Value("${memory.limit:NOT SET}") String memoryLimt,
                         @Value("${cf.instance.index:NOT SET}") String cfInstanceIndex,@Value("${cf.instance.addr:NOT SET}") String cfInstanceAddr) {
        this.port = port;
        this.memoryLimt = memoryLimt;
        this.cfInstanceIndex = cfInstanceIndex;
        this.cfInstanceAddr = cfInstanceAddr;
    }

    private String memoryLimt;
    private String cfInstanceIndex;
    private String cfInstanceAddr;

    @GetMapping("/env")
    public Map<String,String> getEnv(){
        Map<String,String> envMap = new HashMap<>();
        envMap.put("PORT",this.port);
        envMap.put("MEMORY_LIMIT",this.memoryLimt);
        envMap.put("CF_INSTANCE_INDEX",this.cfInstanceIndex);
        envMap.put("CF_INSTANCE_ADDR",this.cfInstanceAddr);
        return envMap;
    }
}
