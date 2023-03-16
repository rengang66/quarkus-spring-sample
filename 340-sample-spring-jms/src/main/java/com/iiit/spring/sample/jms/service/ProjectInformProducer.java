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
package com.iiit.spring.sample.jms.service;

import java.time.LocalDateTime;
import java.util.Random;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.jboss.logging.Logger;

import com.iiit.spring.sample.jms.domain.ProjectMessage;


@Component
public class ProjectInformProducer {	
	
	private static final Logger LOGGER = Logger.getLogger(ProjectInformProducer.class.getName());
	
	private final JmsTemplate jmsTemplate;    
    private static int sequence1 = 0;
    private static int sequence2 = 0;
    private Random random = new Random();

    public ProjectInformProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
        jmsTemplate.setPubSubDomain(true);
    }

    @Scheduled(fixedRate = 2000)
    public void sender() {
        // Send a message with a POJO - the template reuses the message converter
    	LOGGER.info("Sending a message.");
        jmsTemplate.convertAndSend("topic1",
                new ProjectMessage("Topic 1 message", sequence1++, false, LocalDateTime.now()));
        
        jmsTemplate.convertAndSend("topic2",
                new ProjectMessage("Topic 2 message", sequence2++, false, LocalDateTime.now()));
       
    }
	
	
}