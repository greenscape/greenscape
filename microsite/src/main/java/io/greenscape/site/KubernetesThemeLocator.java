package io.greenscape.site;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.fabric8.kubernetes.api.model.extensions.Ingress;
import io.fabric8.kubernetes.api.model.extensions.IngressSpec;
import io.fabric8.kubernetes.api.model.extensions.IngressStatus;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.quarkus.cache.CacheResult;
import io.quarkus.qute.TemplateLocator;
import io.quarkus.qute.Variant;

@ApplicationScoped
public class KubernetesThemeLocator implements TemplateLocator {

	@Inject
	KubernetesClient kubeClient;

	@Override
	public Optional<TemplateLocation> locate(String path) {
		String[] paths = path.split(":");
		String themeName = paths[0];
		String service = locateService(themeName);
		String templatePath = service + paths[1];
		URL resource;
		try {
			resource = new URL(templatePath);
			return Optional.of(new ResourceTemplateLocation(resource, guessVariant(path)));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@CacheResult(cacheName = "theme")
	private String locateService(String name) {
		String url = kubeClient.getMasterUrl().getHost();

		List<Ingress> ingresses = kubeClient.extensions().ingresses().withLabel("app.kubernetes.io/name", name).list()
				.getItems();
		IngressStatus ingressStatus = ingresses.get(0).getStatus();
		String ip = ingressStatus.getLoadBalancer().getIngress().get(0).getIp();
		String path = ingresses.get(0).getSpec().getRules().get(0).getHttp().getPaths().get(0).getPath();
		return "http://" + ip + path;
	}

	private Variant guessVariant(String path) {
		// TODO we need a proper way to detect the variant
		int suffixIdx = path.lastIndexOf('.');
		if (suffixIdx != -1) {
			String suffix = path.substring(suffixIdx);
			return new Variant(null, parseMediaType(suffix), null);
		}
		return null;
	}

	private String parseMediaType(String suffix) {
		// TODO we need a proper way to parse the media type
		if (suffix.equalsIgnoreCase(".html") || suffix.equalsIgnoreCase(".htm")) {
			return Variant.TEXT_HTML;
		} else if (suffix.equalsIgnoreCase(".xml")) {
			return Variant.TEXT_XML;
		} else if (suffix.equalsIgnoreCase(".txt")) {
			return Variant.TEXT_PLAIN;
		} else if (suffix.equalsIgnoreCase(".json")) {
			return Variant.APPLICATION_JSON;
		} else if (suffix.equalsIgnoreCase(".js")) {
			return "text/javascript";
		}
		return "application/octet-stream";
	}

}
