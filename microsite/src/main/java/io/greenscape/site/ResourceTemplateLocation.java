package io.greenscape.site;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Optional;

import io.quarkus.qute.Variant;
import io.quarkus.qute.TemplateLocator.TemplateLocation;

public class ResourceTemplateLocation implements TemplateLocation {
	private final URL resource;
	private final Optional<Variant> variant;

	public ResourceTemplateLocation(URL resource, Variant variant) {
		this.resource = resource;
		this.variant = Optional.ofNullable(variant);
	}

	@Override
	public Reader read() {
		try {
			return new InputStreamReader(resource.openStream(), Charset.forName("utf-8"));
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public Optional<Variant> getVariant() {
		return variant;
	}
}
