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
package com.iiit.quarkus.sample.security.ssl.resource;

import java.util.StringJoiner;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class CustomError {
	private int errorCode;
	private String errorMessage;

	public CustomError() {
	}

	public CustomError(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", CustomError.class.getSimpleName() + "[", "]")
			.add("errorCode=" + this.errorCode)
			.add("errorMessage='" + this.errorMessage + "'")
			.toString();
	}
}
