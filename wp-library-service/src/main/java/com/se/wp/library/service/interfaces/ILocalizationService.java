package com.se.wp.library.service.interfaces;

import com.se.wp.library.models.LocalizedData;

public interface ILocalizationService {
	public LocalizedData getLocalizedLabels(String language);
}
