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

import java.time.Duration;
import java.util.Random;
import java.util.function.Supplier;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

@Component("generateproject")
public class ProjectInformGenerator implements Supplier<Flux<String>> {
	private final Random random = new Random();

	@Override
	public Flux<String> get() {
		return Flux.interval(Duration.ofSeconds(5))
			.onBackpressureDrop()
			.map(tick ->{
				Integer rm = this.random.nextInt(100);
				String projectdata = "发送项目数据：" + Integer.toString(rm);
				System.out.println("================" + projectdata );
				return projectdata;
				} );
	}
}
