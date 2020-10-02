package ru.vasiliydz.organizer.clientbase;

/**
 * Objects of this type can receive {@link BaseResponse BaseResponse} objects and somehow display it
 */
public interface ResponseOutputStream {

	/**
	 * Receives {@link BaseResponse BaseResponse} object and does something to show it
	 * @param response {@link BaseResponse BaseResponse} object to display
	 */
	void putResponse(BaseResponse response);
}
