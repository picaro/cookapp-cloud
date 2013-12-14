package com.op.cookcloud.exceptions;

public class ServerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServerException() {
	}

	public ServerException(Throwable cause) {
		super(cause instanceof ServerException ? cause.getCause() : cause);
	}

	public ServerException(String message) {
		super(message);
	}

	public ServerException(String message, Throwable cause) {
		super(message, cause instanceof ServerException ? cause.getCause() : cause);
	}

}
