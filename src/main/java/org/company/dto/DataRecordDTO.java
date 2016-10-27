package org.company.dto;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;

import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

@CsvDataType
public class DataRecordDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@CsvField(pos = 1)
	private Calendar dateTime;

	@CsvField(pos = 2)
	private Double latitude;

	@CsvField(pos = 3)
	private Double longitude;

	@CsvField(pos = 4)
	private String location;

	@CsvField(pos = 5)
	private String localisedDatetime;

	public DataRecordDTO() {
		super();
	}

	public DataRecordDTO(Calendar date, Double latitude, Double longitude) {
		super();
		this.dateTime = date;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public DataRecordDTO(Calendar date, Double latitude, Double longitude, String localisedDatetime) {
		super();
		this.dateTime = date;
		this.latitude = latitude;
		this.longitude = longitude;
		this.localisedDatetime = localisedDatetime;
	}

	public String getDateString() {
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		return df.format(dateTime.getTime());
	}

	public Calendar getDateTime() {
		return dateTime;
	}

	public void setDateCalendar(Calendar dateCalender) {
		this.dateTime = dateCalender;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getLocalisedDatetime() {
		return localisedDatetime;
	}

	public void setLocalisedDatetime(String localisedDatetime) {
		this.localisedDatetime = localisedDatetime;
	}

	public void setDateTime(Calendar date) {
		this.dateTime = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((localisedDatetime == null) ? 0 : localisedDatetime.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataRecordDTO other = (DataRecordDTO) obj;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (localisedDatetime == null) {
			if (other.localisedDatetime != null)
				return false;
		} else if (!localisedDatetime.equals(other.localisedDatetime))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DataRecordDTO [dateTime=" + dateTime + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", location=" + location + ", localisedDatetime=" + localisedDatetime + "]";
	}

}
