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
package com.iiit.spring.sample.streams.service;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

@Component("projectconverter")
public class ProjectInformConverter implements Consumer<Flux<String>> {
	static final double CONVERSION_RATE = 0.88;

	private final InMemoryChannel<String> inMemoryChannel;

	public ProjectInformConverter(InMemoryChannel<String> inMemoryChannel) {
		this.inMemoryChannel = inMemoryChannel;
	}

	@Override
	public void accept(Flux<String> priceInUsd) {
		priceInUsd
			.map(senddata -> {
				//Double p = price * CONVERSION_RATE;
				String receiveData = "收到："+senddata;
				//return p;
				return receiveData;
				})
			.subscribe(this.inMemoryChannel::emitValue);
	}
}
