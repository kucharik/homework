package mortoff.line.breaking.model;

import java.util.List;

public class LineBreakingContext {

	private String rawData;
	private Status status;
	private List<String> brokenText;
	
	public String getRawData() {
		return rawData;
	}
	public void setRawData(String rawData) {
		this.rawData = rawData;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public List<String> getBrokenText() {
		return brokenText;
	}
	public void setBrokenText(List<String> brokenText) {
		this.brokenText = brokenText;
	}
	
	
}
