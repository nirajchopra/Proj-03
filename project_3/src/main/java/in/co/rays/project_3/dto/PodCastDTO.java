package in.co.rays.project_3.dto;

public class PodCastDTO extends BaseDTO{
	
	private long podcastId;
	private String podcastCode;
	private String podcastTitle;
	private String hostName;
	private String status;
	public long getPodcastId() {
		return podcastId;
	}
	public void setPodcastId(long podcastId) {
		this.podcastId = podcastId;
	}
	public String getPodcastCode() {
		return podcastCode;
	}
	public void setPodcastCode(String podcastCode) {
		this.podcastCode = podcastCode;
	}
	public String getPodcastTitle() {
		return podcastTitle;
	}
	public void setPodcastTitle(String podcastTitle) {
		this.podcastTitle = podcastTitle;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
