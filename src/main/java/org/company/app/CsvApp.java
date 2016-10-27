package org.company.app;

import java.util.List;
import java.util.TimeZone;

import org.company.dao.CsvFileDAO;
import org.company.dto.DataRecordDTO;
import org.company.util.UtilDate;
import org.company.util.UtilLocale;
import org.company.util.UtilLog;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.maps.GeoApiContext;
import com.google.maps.TimeZoneApi;
import com.google.maps.model.LatLng;

public class CsvApp {

	private static GeoApiContext context = UtilLocale.getContext();

	public static void main(String[] args) throws Exception {
	  System.setProperty("java.net.useSystemProxies", "true");
		List<DataRecordDTO> data = CsvFileDAO.readToCSV(new DataRecordDTO(), "./csv/data.csv");

		for (DataRecordDTO dataRecordDTO : data) {
			dataRecordDTO = getLocalisedDatetime(dataRecordDTO);
		}
		CsvFileDAO.writeToCSV(data, "", "./csv/out.csv");

	}

	private static DataRecordDTO getLocalisedDatetime(DataRecordDTO dataRecordDTO) {
		TimeZone tzLocation = getTimeZone(dataRecordDTO);
		dataRecordDTO.setLocation(tzLocation.getID());
		dataRecordDTO = setOriginalDateTimeWithoutGMTCorrection(tzLocation, dataRecordDTO);
		return dataRecordDTO;
	}

	private static DataRecordDTO setOriginalDateTimeWithoutGMTCorrection(TimeZone tzLocation,DataRecordDTO dataRecordDTO) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
		DateTime original = new DateTime(dataRecordDTO.getDateTime().getTimeInMillis(), DateTimeZone.forID("UTC"));
		DateTime localisedDateTime = new DateTime(dataRecordDTO.getDateTime().getTimeInMillis(), DateTimeZone.forID(tzLocation.getID()));
		dataRecordDTO.setDateTime(UtilDate.stringToCalendar(fmt.print(original), "T"));
		dataRecordDTO.setLocalisedDatetime(fmt.print(localisedDateTime));
		return dataRecordDTO;
	}

	private static TimeZone getTimeZone(DataRecordDTO dataRecordDTO) {
		TimeZone tzLocation = null;
		try {
			tzLocation = TimeZoneApi.getTimeZone(context, new LatLng(dataRecordDTO.getLatitude(), dataRecordDTO.getLongitude())).await();
		} catch (Exception e) {
			UtilLog.logError("Cannot get TimeZone"+e.getMessage()+" \n", e);
		}
		return tzLocation;
	}

}
