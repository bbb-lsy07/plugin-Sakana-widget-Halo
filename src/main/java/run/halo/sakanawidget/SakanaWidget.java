package run.halo.sakanawidget;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.processor.element.IElementModelStructureHandler;
import reactor.core.publisher.Mono;
import run.halo.app.theme.dialect.TemplateHeadProcessor;

@Component
public class SakanaWidget implements TemplateHeadProcessor {

    private static final String STATIC_PATH = "/plugins/SakanaWidget/assets/res";

    @Override
    public Mono<Void> process(ITemplateContext context, IModel model,
        IElementModelStructureHandler structureHandler) {
        final IModelFactory modelFactory = context.getModelFactory();

        String version = String.valueOf(System.currentTimeMillis());

        String injectHtml = """
             <link rel="stylesheet" href="%1$s/sakana.min.css"/>
             <link rel="stylesheet" href="%1$s/css/sakana.css"/>

             <script defer src="%1$s/sakana.min.js?v=%2$s"></script>
             <script>
                 // 动态追加到 body 末尾
                 const widgetDiv = document.createElement('div');
                 widgetDiv.id = 'sakana-widget';
                 widgetDiv.className = 'sakana-float';
                 widgetDiv.style.position = 'fixed';
                 widgetDiv.style.right = '40px';
                 widgetDiv.style.bottom = '45px';
                 widgetDiv.style.zIndex = '9999';
                 document.body.appendChild(widgetDiv);

                 // 确保脚本加载后初始化
                 window.addEventListener('load', () => {
                     new SakanaWidget().mount('#sakana-widget');
                 });
             </script>
             """.formatted(STATIC_PATH, version);

        model.add(modelFactory.createText(injectHtml));
        return Mono.empty();
    }
}
