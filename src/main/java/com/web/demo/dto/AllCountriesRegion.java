package com.web.demo.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"id",
	"countries"
})
public class AllCountriesRegion {

	@JsonProperty("id")
	private String id;
	@JsonProperty("countries")
	private List<CountriesDTO> countries = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("countries")
	public List<CountriesDTO> getCountries() {
		return countries;
	}

	@JsonProperty("countries")
	public void setCountries(List<CountriesDTO> countries) {
		this.countries = countries;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
}
