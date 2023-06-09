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
package com.iiit.quarkus.sample.security.oauth2;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.quarkus.runtime.Quarkus;

@QuarkusMain
public class ProjectMain implements QuarkusApplication {	
	
	@Override
	public int run(String... args) {
		System.out.println("================ quarkus is running! ================");		
		// 启动程序，等待外部的调用
		Quarkus.waitForExit();
		return 0;
	}

	// main方法启动程序
	public static void main(String... args) {
		Quarkus.run(ProjectMain.class, args);
	}
}
