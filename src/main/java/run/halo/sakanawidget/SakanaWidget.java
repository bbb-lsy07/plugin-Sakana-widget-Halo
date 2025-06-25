package run.halo.sakanawidget;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.processor.element.IElementModelStructureHandler;
import reactor.core.publisher.Mono;
import run.halo.app.extension.ConfigMap;
import run.halo.app.extension.ExtensionClient;
import run.halo.app.theme.dialect.TemplateHeadProcessor;

import java.util.Optional;

@Component
public class SakanaWidget implements TemplateHeadProcessor {

    private static final String STATIC_PATH = "/plugins/SakanaWidget/assets/res";
    private final ExtensionClient client;

    public SakanaWidget(ExtensionClient client) {
        this.client = client;
    }

    @Override
    public Mono<Void> process(ITemplateContext context, IModel model,
                              IElementModelStructureHandler structureHandler) {
        final IModelFactory modelFactory = context.getModelFactory();

        String version = String.valueOf(System.currentTimeMillis());

        String position = getPluginConfigPosition();
        boolean showControls = getPluginConfigShowControls();
        String controlButtonText = getPluginConfigControlButtonText();
        int idleSway = getPluginConfigIdleSway();

        String injectHtml = """
             <link rel="stylesheet" href="%1$s/sakana.min.css"/>
             <link rel="stylesheet" href="%1$s/css/sakana.css"/>

             <script src="%1$s/sakana.min.js?v=%2$s"></script>
             <script>
                window.addEventListener('load', () => {
                    const widgetDiv = document.createElement('div');
                    widgetDiv.id = 'sakana-widget';
                    widgetDiv.className = 'sakana-float';
                    widgetDiv.style.position = 'fixed';
                    %3$s
                    widgetDiv.style.bottom = '45px';
                    widgetDiv.style.zIndex = '9999';
                    document.body.appendChild(widgetDiv);

                    new SakanaWidget({
                        controls: %4$s,
                        controlButtonText: '%5$s',
                        idleSway: %6$d
                    }).mount('#sakana-widget');
                });
            </script>

             """.formatted(STATIC_PATH, version, position, showControls, controlButtonText, idleSway);

        model.add(modelFactory.createText(injectHtml));
        return Mono.empty();
    }

    private String getPluginConfigPosition() {
        Optional<ConfigMap> configMapOptional = client.fetch(ConfigMap.class, "SakanaWidget-config");
        if (configMapOptional.isPresent()) {
            ConfigMap configMap = configMapOptional.get();
            String position = configMap.getData().get("position");
            if ("bottom-left".equals(position)) {
                return "widgetDiv.style.left = '40px';";
            }
        }
        return "widgetDiv.style.right = '40px';";
    }

    private boolean getPluginConfigShowControls() {
        Optional<ConfigMap> configMapOptional = client.fetch(ConfigMap.class, "SakanaWidget-config");
        if (configMapOptional.isPresent()) {
            ConfigMap configMap = configMapOptional.get();
            String showControls = configMap.getData().get("showControls");
            return Boolean.parseBoolean(showControls);
        }
        return true;
    }

    private String getPluginConfigControlButtonText() {
        Optional<ConfigMap> configMapOptional = client.fetch(ConfigMap.class, "SakanaWidget-config");
        if (configMapOptional.isPresent()) {
            ConfigMap configMap = configMapOptional.get();
            return configMap.getData().get("controlButtonText");
        }
        return "横跳";
    }

    private int getPluginConfigIdleSway() {
        Optional<ConfigMap> configMapOptional = client.fetch(ConfigMap.class, "SakanaWidget-config");
        if (configMapOptional.isPresent()) {
            ConfigMap configMap = configMapOptional.get();
            String idleSway = configMap.getData().get("idleSway");
            try {
                return Integer.parseInt(idleSway);
            } catch (NumberFormatException e) {
                return 10;
            }
        }
        return 10;
    }
}
