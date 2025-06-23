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

        String injectHtml = """
             <link rel="stylesheet" href="%1$s/sakana.min.css"/>

             <div id="sakana-widget" class="sakana-float" style="
                 position: fixed;
                 right: 40px;
                 bottom: 45px;
                 z-index: 5;
             "></div>

             <script src="%1$s/sakana.min.js"></script>
             <script>
                 new SakanaWidget().mount('#sakana-widget');
             </script>

             <style>
                 @media screen and (max-width: 768px) {
                     .sakana-float {
                         display: none !important;
                     }
                 }
             </style>
            """.formatted(STATIC_PATH);

        model.add(modelFactory.createText(injectHtml));
        return Mono.empty();
    }
}