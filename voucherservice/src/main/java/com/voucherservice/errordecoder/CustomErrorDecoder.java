package com.voucherservice.errordecoder;

import java.io.IOException;

import com.voucherservice.exception.ResourceAlreadyExistException;
import com.voucherservice.exception.ResourceNotFoundException;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder{

	@Override
	public Exception decode(String methodKey, Response response) {
		if (response.status() == 404) {
			String responseBody = getResponseBody(response);
			return new ResourceNotFoundException(responseBody);
		} else if (response.status() == 406) {
			String responseBody = getResponseBody(response);
			return new ResourceAlreadyExistException(responseBody);
		}

		// Handle other errors or return default decoder
		return FeignException.errorStatus(methodKey, response);
	}

//	private String getResponseBody(Response response) {
//        try {
//            if (response.body() != null) {
//                return StreamUtils.copyToString(response.body().asInputStream(), Charset.defaultCharset());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "Resource not found"; // Default message if the response body is empty
//    }

	private String getResponseBody(Response response) {
		try {
			if (response.body() != null) {
				try (java.io.Reader reader = response.body().asReader()) {
					// Parse the JSON response and extract the "message" field
					com.fasterxml.jackson.databind.JsonNode root = new com.fasterxml.jackson.databind.ObjectMapper()
							.readTree(reader);
					if (root.has("message")) {
						return root.get("message").asText();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Resource not found"; // Default message if the response body is empty or doesn't contain the "message" field
	}
}
