package com.iiit.spring.sample.kafka.stream;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import com.iiit.spring.sample.kafka.domain.Project;

@Component
public class KafkaStreamService {
	
	@Value("#{kafkaConfig.getStreamInputTopicName()}")
	private String streamInputTopicName;
	
	@Value("#{kafkaConfig.getStreamOutputTopicName()}")
	private String streamOutputTopicName;
	
	@Bean
	public KStream<String, Project> kStream(StreamsBuilder kStreamBuilder) {
		KStream<String, Project> stream = kStreamBuilder.stream(streamInputTopicName, Consumed.with(Serdes.String(), new JsonSerde<>(Project.class)));
    	
		stream.print(Printed.<String, Project>toSysOut().withLabel("KafkaStreamProcessingApplication :: Before :: "));
		
		KStream<String, Project> upperCasedStream = stream.mapValues(project -> project.convertFirstNameToUpperCase());
		
		upperCasedStream.to(streamOutputTopicName, Produced.with(Serdes.String(), new JsonSerde<>(Project.class)));
		
		upperCasedStream.print(Printed.<String, Project>toSysOut().withLabel("KafkaStreamProcessingApplication :: After :: "));
        
        return upperCasedStream;
	}

}
