package com.googlecode.yatspec.junit;

import com.googlecode.yatspec.rendering.ScenarioNameRendererFactory;
import com.googlecode.yatspec.state.ScenarioName;
import org.junit.runners.model.FrameworkMethod;

import static java.util.Arrays.asList;

public class DecoratingFrameworkMethod extends FrameworkMethod {
    private final Row row;

    public DecoratingFrameworkMethod(FrameworkMethod method, Row row) {
        super(method.getMethod());
        this.row = row;
    }

    @Override
    public Object invokeExplosively(Object target, Object... params) throws Throwable {
        return super.invokeExplosively(target, (Object[]) row.value());
    }

    @Override
    public String getName() {
        ScenarioName scenarioName = new ScenarioName(super.getName(), asList(row.value()));
        return ScenarioNameRendererFactory.renderer().render(scenarioName);
    }
}