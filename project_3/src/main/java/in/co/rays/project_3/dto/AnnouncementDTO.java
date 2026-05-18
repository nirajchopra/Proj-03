
package in.co.rays.project_3.dto;

import java.util.Date;


public class AnnouncementDTO extends BaseDTO {

	@Override
	public String toString() {
		return "AnnouncementDTO [announcementCode=" + announcementCode + ", title=" + title + ", description="
				+ description + ", publishDate=" + publishDate + "]";
	}

	String announcementCode;
	String title;
	String description;
	Date publishDate;

	public String getAnnouncementCode() {
		return announcementCode;
	}

	public void setAnnouncementCode(String announcementCode) {
		this.announcementCode = announcementCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
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
