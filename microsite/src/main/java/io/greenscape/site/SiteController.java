package io.greenscape.site;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import io.quarkus.qute.Engine;
import io.quarkus.qute.EngineBuilder;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

@Path("/{siteName}")
public class SiteController {

	@PathParam("siteName")
	String siteName;

	@Inject
	Engine engine;

	@Inject
	KubernetesThemeLocator themeLocator;

	@Path("/{s:.*}")
	@GET
//	@Produces(MediaType.TEXT_HTML)
	public Response index(@Context UriInfo uriInfo) {
		URI uri = uriInfo.getAbsolutePath();
		Site site = buildSite();

		Template pageTemplate = engine.getTemplate(site.getDefaultTheme().getName() + ":index.html");
		TemplateInstance templateInstance = pageTemplate.instance();
		templateInstance.data("site", site);
		templateInstance.data("page", site.getDefaultPage());
		MediaType mediaType = MediaType.TEXT_PLAIN_TYPE;

		if (uri.getPath().endsWith(".html") || uri.getPath().endsWith(".htm")) {
			mediaType = MediaType.TEXT_HTML_TYPE;
		}
		Response response = Response.ok(templateInstance).type(mediaType).build();

		return response;
	}

	public void customizeEngine(@Observes EngineBuilder engineBuilder) {
		engineBuilder.addLocator(themeLocator);
	}

	private Site buildSite() {
		// TODO: search DB for site 'siteName'
		Site site = new Site();
		site.setName(siteName);
		site.setTitle(siteName);
		site.setDefault(true);
		site.setDefaultTheme(buildTheme());
		List<Page> pages = new ArrayList<>();
		site.setPages(pages);
		Page page = buildPage();
		pages.add(page);
		return site;
	}

	private Page buildPage() {
		Page page = new Page();
		page.setName("home");
		page.setTitle("Home");
		page.setDefault(true);
		Theme theme = buildTheme();
		page.setTheme(theme);
		return page;
	}

	private Theme buildTheme() {
		Theme theme = new Theme();
		theme.setName("spring-theme");
		return theme;
	}
}
