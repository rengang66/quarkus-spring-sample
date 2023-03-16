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

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import org.jboss.logging.Logger;

import com.iiit.spring.sample.jms.domain.ProjectMessage;

@Component
public class ProjectInformConsumer  {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectInformConsumer.class);	

	@JmsListener(destination = "topic1", containerFactory = "factory", subscription = "topic1")
	  public void receiveMessage(ProjectMessage msg) {
	    int transfer = (LocalDateTime.now().getNano() - msg.getSent().getNano())/1000;
	    LOGGER.info("Message read from topic 1 : " + msg
	            + " transfer time: "
	            + (transfer) + "µs");
	  }
	  
	  @JmsListener(destination = "topic2", containerFactory = "factory", subscription = "topic2")
	  public void receiveMessage1(ProjectMessage msg) {
	    int transfer = (LocalDateTime.now().getNano() - msg.getSent().getNano())/1000;
	    LOGGER.info("Message read from topic 2 : " + msg
	            + " transfer time: "
	            + (transfer) + "µs");
	  }

}