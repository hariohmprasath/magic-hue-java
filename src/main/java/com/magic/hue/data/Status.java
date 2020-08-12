package com.magic.hue.data;

public enum Status {
	ON("ON"), OFF("OFF"), UNKNOWN("UNKNOWN");

	private String status;

	Status(String status) {
		this.status = status;
	}


	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return this.getStatus();
	}
}
