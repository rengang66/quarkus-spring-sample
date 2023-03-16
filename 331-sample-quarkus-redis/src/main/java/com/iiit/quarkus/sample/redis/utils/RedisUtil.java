/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.iiit.quarkus.sample.redis.utils;



import java.util.Arrays;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.jboss.logging.Logger;

import io.quarkus.redis.client.RedisClient;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;



@Singleton
public class RedisUtil {

	 @Inject
	 RedisClient redisClient;   

    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间（秒）
     * @return true / false
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
            	redisClient.expire(key, "");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Response bgsave(List<String> args);
    
    //Response del(List<String> args);
    
    
    //Response exists(List<String> args);
    
    // Response get(String arg0);
    
    // Response getset(String arg0, String arg1);
    
    //  Response keys(String arg0);

    // Response lset(String arg0, String arg1, String arg2);
    
    // Response mset(List<String> args);
    
    //Response object(List<String> args);
    
    // Response persist(String arg0);
    
    // Response rename(String arg0, String arg1);
    
    //Response save();
    
    //Response set(List<String> args);
    
    // Response sync();
    

}
